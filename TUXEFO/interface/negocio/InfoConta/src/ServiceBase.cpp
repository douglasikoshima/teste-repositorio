#include <ServiceBase.h>
#include <ImpressaoBoleto.h>
#include <ContaResumida.h>
#include <ContaDetalhada.h>
#include <ContaDetalhadaLocal.h>
#include <ImagemConta.h>

void ServiceBase::setXMLIn(DOMNode* dnode)
{
	this->m_dnode = dnode;
}


void ServiceBase::decodeImage()
{

}

void ServiceBase::finalize()
{
	tuxfw_getlogger()->debug("ServiceBase::finalize()");
	switch(this->m_operation)
	{
		case OPERACAO_BOLETO_IMPRESSO:
			delete ((ImpressaoBoleto*)this);
			break;
		case OPERACAO_CONTA_RESUMIDA:
			delete ((ContaResumida*)this);
			break;
		case OPERACAO_CONTA_DETALHADA:
			delete ((ContaDetalhada*)this);
			break;
		case OPERACAO_CONTA_DETALHADA_LOCAL:
			delete ((ContaDetalhadaLocal*)this);
			break;
		default:
			delete this;
			break;
	}	
}

void ServiceBase::setOperation(int operation)
{
	this->m_operation = operation;
}

ServiceBase* ServiceBase::getInstance(int operacao)
{
	tuxfw_getlogger()->debug("ServiceBase::getInstance()");
	ServiceBase* servico = NULL;
	switch(operacao)
	{
		case OPERACAO_BOLETO_IMPRESSO:
			servico = (ServiceBase*) new ImpressaoBoleto();
			break;
		case OPERACAO_CONTA_RESUMIDA:
			servico = (ServiceBase*) new ContaResumida();
			break;
		case OPERACAO_CONTA_DETALHADA:
			servico = (ServiceBase*) new ContaDetalhada();
			break;
		case OPERACAO_CONTA_DETALHADA_LOCAL:
			servico = (ServiceBase*) new ContaDetalhadaLocal();
			break;
		default:
			servico = NULL;
			break;
	}
	return servico;
}

int ServiceBase::getImagemXenosSemEspera(stCommonFields &field,char* pathOutput, int typeRetorno)
{
	tuxfw_getlogger()->debug("ServiceBase::getImagemXenosSemEspera()");
	
	// se o pathOutput estiver vazio não tenta recuperar imagem
	if(pathOutput == NULL || strlen(pathOutput) == 0 )
		return 0;
	
	// Dependendo da data do ciclo de faturamento pode existir um diretório de input do xenos diferente
	// apenas o boleto pertence sempre a FASE3 independente da data de ciclo.
	char*data =  field.data;
	int fase = 2;
	int dataFase = ImagemConta::getDataFase();
	if(field.docType == DOCUMENT_TYPE_NUM_R || Util::fmtInverteData(data) > dataFase)	
		fase = 3;
	else
		fase = 2;
		
	// chamando método para recuperar a imagem da saída do xenos sem esperar n segundos
	field.out = m_xenos.getImageXenos( pathOutput,typeRetorno, field.docType,fase);
	
	if(field.out.length() > 0)
	{
		// remove arquivos que não serão mais usados
		m_xenos.removeFiles();
		return 1;
	}

	return 0;
}

int ServiceBase::exec(stCommonFields &field,XMLGen* xmlgen)
{
	tuxfw_getlogger()->debug("ServiceBase::exec()");
	DOMNode* domNodeImage = NULL;
	DOMNode* domNode = NULL;
	char pathInputArquivo[255];
	char pathOutputArquivo[255];
	char*pathInput = "";
	char*fault = "";
	int retcode = 1;
	char*canal =  field.canal;
	char*conta =  field.conta;
	char*data =  field.data;
	char*retorno =  field.retorno;
	stGetBillImage stImage;
	char*cPATH_XENOS_TEMP = tuxgetenv("PATH_XENOS_TEMP");
	char TYPE_LOWER_CASE[2];
	char pathInputFila[500];
	char nomeTipoImagem[256];
	int dataFase;
	int fase = 2;

	// adicionar os parametros para controle de log
	m_service.getLog()->setIdContaSistemaOrigem(conta);
	m_service.getLog()->getCanal(canal);
	m_service.getLog()->getParametroLog();
	m_service.getLog()->getSistemaOrigem("ATY");

	switch(field.docType)
	{
		case DOCUMENT_TYPE_NUM_U: strcpy(TYPE_LOWER_CASE,"u"); strcpy(nomeTipoImagem,"detalh"); break;
		case DOCUMENT_TYPE_NUM_B: strcpy(TYPE_LOWER_CASE,"b"); strcpy(nomeTipoImagem,"resumo"); break;
		case DOCUMENT_TYPE_NUM_R: strcpy(TYPE_LOWER_CASE,"r"); strcpy(nomeTipoImagem,"boleto"); break;
	}

	m_mem.addChar(cPATH_XENOS_TEMP);
	m_xenos.setConta(conta);

	// Chamar a inputGetBillDates
	try
	{
		domNode = m_service.inputGetBillDates(conta);
	}
	catch(TuxBasicSvcException tbe)
	{
		tuxfw_getlogger()->debug("Erro de acesso ao ATLYS");
		return ERROR_ATLYS_FORA_DO_AR;
	}
	if(domNode == NULL)
	{			
		return ERROR_BILL_DATE_FAULT;
	}
	
	char*ciclo =  tuxhp.walkAttr(domNode,XML_TAG_BILL_DT,XML_ATTR_CYCLE_END_DT,0);
	char*billDt = NULL;
	char*cicleCd = "";
	bool find = false;
	ListChar list;
	for(int i=0; (billDt = tuxhp.walkAttr(domNode,XML_TAG_BILL_DT,XML_ATTR_CYCLE_END_DT,i))!=NULL;i++)
	{
		list.add(billDt);
		if(strcmp(billDt,data)  == 0)
		{
			tuxfw_getlogger()->debug("Localizou a data passada");
			cicleCd = tuxhp.walkAttr(domNode,XML_TAG_BILL_DT,XML_ATTR_CYCLE_CD,i);
			find = true;
		}
	}

	if(data == NULL || strcmp(data,"") == 0 || !find)
	{
		// tratamento quando não achar a data
		xmlgen->createTag("outputGetBillDates");
		for(int i=0;i<list.sizeOf();i++)		
			xmlgen->addItem(XML_TAG_BILL_DT,(char*)list.get(i));		
		xmlgen->closeTag();
		return ERROR_DATA_NAO_ENCONTRADA;
	}

	// Setar os dados da inputGetBillImage
	stImage.acctNbr = conta;
	stImage.areMore = false;
	stImage.fileFormat = FILE_FORMAT_TYPE_A;
	stImage.cycleCd = cicleCd;
	stImage.documentType = field.cDocType;
	stImage.cycleEndDt = data;	
	stImage.accessNbr = field.accessNbr;


	// verifica qual o tipo de retorno para setar o caminho de input correto
	// Dependendo da data do ciclo de faturamento pode existir um diretório de input do xenos diferente
	// apenas o boleto pertence sempre a FASE3 independente da data de ciclo.
	dataFase = ImagemConta::getDataFase();
	if(field.docType == DOCUMENT_TYPE_NUM_R)
	{
		pathInput = (strcmp(retorno,"XML") == 0)?tuxgetenv("PATH_INPUT_XENOS_XML_FASE3"):tuxgetenv("PATH_INPUT_XENOS_HTML_FASE3");
		fase = 3;
	}
	else
	if(Util::fmtInverteData(data) <= dataFase)
	{
	pathInput = (strcmp(retorno,"XML") == 0)?tuxgetenv("PATH_INPUT_XENOS_XML"):tuxgetenv("PATH_INPUT_XENOS_HTML");
		fase = 2;
	}
	else
	{
		pathInput = (strcmp(retorno,"XML") == 0)?tuxgetenv("PATH_INPUT_XENOS_XML_FASE3"):tuxgetenv("PATH_INPUT_XENOS_HTML_FASE3");
		fase = 3;
	}

	m_xenos.generateRandNumber();
	sprintf(pathInputFila,"%s/online/%s_%s/input_%d%s",pathInput,nomeTipoImagem,TYPE_LOWER_CASE,m_xenos.getRandNumber(),TYPE_LOWER_CASE);
	sprintf(pathInputArquivo,"%s/online/%s_%s/input_%d%s",pathInput,nomeTipoImagem,TYPE_LOWER_CASE,m_xenos.getRandNumber(),TYPE_LOWER_CASE);
	sprintf(pathOutputArquivo,"%s/online/%s_%s/output_%d%s",pathInput,nomeTipoImagem,TYPE_LOWER_CASE,m_xenos.getRandNumber(),TYPE_LOWER_CASE);
	free(pathInput);
	pathInput = pathInputFila;
	tuxfw_getlogger()->debug("pathInput %s",pathInput);


	CFileXenos fileINPUT(pathInput,field.docType,0,conta);
	int open = fileINPUT.open("w+");	
	if(open == 0)
	{
		tuxfw_getlogger()->debug("Não conseguiu criar o arquivo de input");
		return ERROR_INPUT_AFP;
	}

	try
	{
		domNodeImage = m_service.inputGetBillImage(&fileINPUT,&stImage);
	}
	catch(...)
	{
		tuxfw_getlogger()->debug("removendo arquivo de input vazio (3) - exception");
		fileINPUT.removeFile();
		return ERROR_ATLYS_FORA_DO_AR;
	}
	if(domNodeImage == NULL)	
	{
		tuxfw_getlogger()->debug("removendo arquivo de input vazio (2)");
		fileINPUT.removeFile();
		return ERROR_BILL_IMAGE_FAULT;	
	}

	if(fileINPUT.getSize() == 0)
	{
		tuxfw_getlogger()->debug("Tamanho de arquivo input vazio");
		char*imagem = m_mem.getTag(domNodeImage,XML_TAG_BILL_IMAGE,0);		
		Util::decodeTrimBase64(imagem,fileINPUT.getFile());
	}
	fileINPUT.close();

	// montar caminho de input completo
	sprintf(pathInputArquivo,"%s/%s.afp",pathInputArquivo,fileINPUT.getNameSimple());
	// montar caminho de output completo
	sprintf(pathOutputArquivo,"%s/%s",pathOutputArquivo,fileINPUT.getNameSimple());

	// colocar permissão no arquivo de input do xenos
	m_xenos.setInputFilePermission(pathInputArquivo);

	// copiar o caminho de output da imagem do xenos
	strcpy(field.outputPathXenos,pathOutputArquivo);


	return retcode;
}

void ServiceBase::setUser(char*user)
{
	m_service.setUser(user);
}

/**
 * Dado um caminho, recuperamos o nome do arquivo e copiamos para nomeImagem
 * retorna 1 para sucesso e 0 quando encontramos um erro
 **/
int ServiceBase::getNomeArquivo(char* nomeImagem,char* nmPath)
{
	tuxfw_getlogger()->debug("ServiceBase::getNomeArquivo");
	// Montamos o caminho de saída que será cacheado
	size_t found;
	string str = nmPath;
	found=str.find_last_of("/");
	string nomeArquivo = str.substr(found+1);
	sprintf(nomeImagem,"%s",(char*)nomeArquivo.c_str());	
	tuxfw_getlogger()->debug("nome da imagem = %s",nomeImagem);

	if(nomeImagem != NULL && strlen(nomeImagem) > 0)
		return 1;
	else
		return 0;

}
