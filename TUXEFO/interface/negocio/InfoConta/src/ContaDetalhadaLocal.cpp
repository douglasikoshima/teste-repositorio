#include "ContaDetalhadaLocal.h"

ContaDetalhadaLocal::ContaDetalhadaLocal()
{
	tuxfw_getlogger()->debug("ContaDetalhadaLocal::ContaDetalhadaLocal()");
	this->m_operation = OPERACAO_CONTA_DETALHADA_LOCAL;
	m_service.getLog()->setDsProcesso("Conta Detalhada Local");
}

ContaDetalhadaLocal::~ContaDetalhadaLocal()
{
	tuxfw_getlogger()->debug("ContaDetalhadaLocal::~ContaDetalhadaLocal()");
}

int ContaDetalhadaLocal::execute(DOMNode* dnode, XMLGen* xmlgen)
{
	tuxfw_getlogger()->debug("ContaDetalhadaLocal::execute()");

	ImagemConta imagemConta;
	bool existConta = false;
	bool cache = false;
	int retcode = 0;

	char*canal =  m_mem.getTag(dnode,XML_TAG_INPUT_CANAL,0);
	char*conta =  m_mem.getTag(dnode,XML_TAG_INPUT_CONTA,0);
	char*data =  m_mem.getTag(dnode,XML_TAG_INPUT_DATA,0);
	char*retorno =  m_mem.getTag(dnode,XML_TAG_INPUT_RETORNO,0);

	char*accessNbr = m_mem.getTag(dnode,"NUM_LINE",0);
	char*codArea = m_mem.getTag(dnode,"COD_AREA",0);
	char telefone[10+1];
	sprintf(telefone,"%s%s",codArea,accessNbr);


	tuxfw_getlogger()->debug("accessNbr %s",telefone);

	stImagemConta imgConta;
	memset(&imgConta,0,sizeof(stImagemConta));
	strcpy(imgConta.idContaSistemaOrigem,conta);
	strcpy(imgConta.dtFimCiclo,data);
	strcpy(imgConta.sgTipoImagem,"L");
	if(retorno!=NULL && strcmp(retorno,"XML")==0)
		imgConta.inXml = 1;
	else
		imgConta.inXml = 0;


	stCommonFields field;
	memset(&field,0,sizeof(field));
	field.canal = canal;
	field.cDocType = DOCUMENT_TYPE_U;
	field.conta = conta;
	field.data = data;
	field.docType = DOCUMENT_TYPE_NUM_U;
	field.out = "";
	field.retorno = retorno;
	field.accessNbr = telefone;

	try
	{
			existConta = imagemConta.selectImagemConta(imgConta);
	}
	catch(TuxBasicOraException tboe)
	{
		tuxfw_getlogger()->debug("erro de oracle");
	}

	// O caminho da imagem foi gravado na base
	if(existConta)
	{
		// Imagem já foi processado pelo xenos
		if(imgConta.inProcessado == 1)
		{
			tuxfw_getlogger()->debug("Existe a imagem, e já foi processada pelo xenos %s",imgConta.nmImagem);
		if(this->gerarSaidaCache(imgConta.nmImagem,imgConta.inXml,xmlgen) == 1)
				return 1;			
		} 
		else // imagem ainda não foi processada pelo xenos
		{
			tuxfw_getlogger()->debug("A imagem ainda não foi processada pelo xenos");
			tuxfw_getlogger()->debug("NMPATHDETALHADA = %s",imgConta.nmPathDetalhada);
			tuxfw_getlogger()->debug("NMPATHRESUMIDA = %s",imgConta.nmPathResumida);
			tuxfw_getlogger()->debug("NMPATHBOLETO = %s",imgConta.nmPathBoleto);			
			char nomeImagemDetalhada[255];	// Nome da imagem final a ser gravada
			memset(&nomeImagemDetalhada,0,255);			
			// Invocar método que recupera imagem de saída do xenos aplicando correções no HTML
			if(this->getImagemXenosSemEspera(field,imgConta.nmPathResumida))
			{
				// Recuperar o nome da imagem
				this->getNomeArquivo(nomeImagemDetalhada,imgConta.nmPathResumida);
				// método gera saída XML e comprime arquivo criado no diretório de armazenamento
				this->gerarSaidaHTMLFile(field.out,xmlgen,conta,nomeImagemDetalhada);				
				// gravamos o path de saída da imagem comprimida na base
				strcpy(imgConta.nmImagem,nomeImagemDetalhada);
				// sucesso, gravamos a imagem finalizada
				if(imagemConta.callProcessamento(1))
		{
			return 1;
		}
				else
				{
					return ERROR_GRAVAR_IMAGEM;
				}
			} 
			else // retornar um erro, porque a imagem ainda não foi processada pelo xenos
			{
				return ERROR_AGUARDANDO_XENOS;
			}

		}
	}

	retcode = this->exec(field,xmlgen);
	if(retcode == ERROR_DATA_NAO_ENCONTRADA)
		return 1;
	else
	if(retcode != 1)
		return retcode;

	try
	{		
		strcpy(imgConta.nmPathDetalhada,field.outputPathXenos);
		imagemConta.callProcessamento(0);
		return ERROR_AGUARDANDO_XENOS;
	}
	catch(TuxBasicOraException tboe)
	{
		tuxfw_getlogger()->debug("erro de oracle ao salvar dados de imagem");
		return 99999; // tratar erro quando não conseguir gravar imagem
	}

	return retcode;
}

int ContaDetalhadaLocal::gerarSaidaHTML(string out,XMLGen* xmlgen)
{
	xmlgen->createTag("ARG");
	xmlgen->createTag("DADOS");
	xmlgen->createTag("outputGetBillImage");		
	xmlgen->addCData((char*)out.c_str());
	xmlgen->closeTag();
	xmlgen->closeTag();
	xmlgen->closeTag();
	return 1;
}

int ContaDetalhadaLocal::gerarSaidaHTMLFile(string out,XMLGen* xmlgen,char*conta)
{
	tuxfw_getlogger()->debug("ContaDetalhadaLocal::gerarSaidaHTMLFile");
	int pos = 0;
	pos = out.find(conta,pos);
	if(pos != -1)
	{
		string find = "xxxxxxxx";
		string replaced = conta;
		out.replace(pos,find.length(),replaced);	
	}
	Util::compressFile((char*)out.c_str(),m_xenos.getPathFile());
	// arg dados
	xmlgen->createTag("ARG");
	xmlgen->createTag("DADOS");	
	xmlgen->addItem("PATH",m_xenos.getPathFile());
	xmlgen->closeTag();
	xmlgen->closeTag();	

	return 1;
}

int ContaDetalhadaLocal::gerarSaidaCache(char*buffer,int tipo,XMLGen* xmlgen)
{
	tuxfw_getlogger()->debug("ContaDetalhadaLocal::gerarSaidaCache");
	CFile file;
	FILE*f = NULL;
	char path[300];
	char* extensao = (tipo)?"xml.gz":"html.gz";
	sprintf(path,"%s/%s.%s",tuxgetenv("PATH_XENOS_COMMON"),buffer,extensao);
	file.setPath(path);
	file.open("r+");
	f = file.getFile();
	file.close();
	if(f != NULL)
	{
		xmlgen->createTag("ARG");
		xmlgen->createTag("DADOS");	
		xmlgen->addItem("PATH",path);
		xmlgen->closeTag();
		xmlgen->closeTag();	
		return 1;
	}
	return 0;
}


int ContaDetalhadaLocal::gerarSaidaXML(char*buffer,XMLGen* xmlgen)
{
	tuxfw_getlogger()->debug("ContaDetalhadaLocal::gerarSaidaXML");
	//tuxfw_getlogger()->debug("buffer passado = %s",buffer);
	DOMNode*domNode = m_mem.createDOMNode(buffer);
	if(domNode == NULL)
	{
		return -8;
	}	

	DOMNode*domNodeCHAMADAS = NULL;
	xmlgen->createTag("ARG");
	xmlgen->createTag("DADOS");
	xmlgen->createTag("ContaDetalhadaBean");
	xmlgen->addProp("class","br.com.vivo.sce.beans.pospago.ContaDetalhadaBean");	
	xmlgen->addItem("codigo","0");
	xmlgen->addItem("descricao","OK");
	xmlgen->addItem("numberRedelivery","0");
	xmlgen->addItem("severidade","5");
	xmlgen->createTag("contaSP");
	xmlgen->addProp("class","br.com.vivo.sce.beans.sp.conta.ContaBean");
	xmlgen->addItem("codigo","0");
	xmlgen->addItem("descricao","OK");
	xmlgen->addItem("numberRedelivery","0");
	xmlgen->addItem("severidade","5");

	// chamadas locais
	if(domNode!=NULL)
	{
		DOMNode*domNodeCHAMADASLocais = NULL;
		xmlgen->createTag("chamadasLocais");
		xmlgen->addProp("class","br.com.vivo.sce.beans.sp.conta.ChamadasLocaisBean");
		xmlgen->addItem("codigo","0");
		xmlgen->addItem("descricao","OK");
		xmlgen->addItem("numberRedelivery","0");
		xmlgen->addItem("severidade","5");
		xmlgen->addItem("foneParser","");
		int totalLocal = 0;
		char ctotalLocal[16];
		for(int i=0; (domNodeCHAMADASLocais = tuxhp.walkDOM(domNode,"CHAMADAS",i)) != NULL;i++)
		{
			DOMNode*domNodeCHAMADA = NULL;
			for(int j=0; (domNodeCHAMADA = tuxhp.walkDOM(domNodeCHAMADASLocais,"CHAMADA",j)) != NULL;j++)
			{
				totalLocal++;
			}
		}
		sprintf(ctotalLocal,"%d",totalLocal);
		xmlgen->createTag("chamadas");
		xmlgen->addProp("total",ctotalLocal);
		xmlgen->addProp("class","java.util.ArrayList");	
		for(int i=0; (domNodeCHAMADASLocais = tuxhp.walkDOM(domNode,"CHAMADAS",i)) != NULL;i++)
		{
			DOMNode*domNodeCHAMADA = NULL;
			for(int j=0; (domNodeCHAMADA = tuxhp.walkDOM(domNodeCHAMADASLocais,"CHAMADA",j)) != NULL;j++)
			{							
				
				xmlgen->createTag("ChamadaLocalBean");
				xmlgen->addProp("class","br.com.vivo.sce.beans.sp.conta.ChamadaLocalBean");
				xmlgen->addItem("dataLigacao",m_mem.getTag(domNodeCHAMADA,"datalig",0));
				xmlgen->addItem("horaInicio",m_mem.getTag(domNodeCHAMADA,"hrinicio",0));
				xmlgen->addItem("destino",m_mem.getTag(domNodeCHAMADA,"destino",0));
				xmlgen->addItem("numeroChamado",m_mem.getTag(domNodeCHAMADA,"numchamado",0));
				xmlgen->addItem("tarifa",m_mem.getTag(domNodeCHAMADA,"tarifa",0));
				xmlgen->addItem("duracao",m_mem.getTag(domNodeCHAMADA,"duracao",0));
				xmlgen->addItem("valor",m_mem.getTag(domNodeCHAMADA,"valor",0));
				xmlgen->closeTag();		
			}		
		}
		xmlgen->closeTag(); // chamadas
		xmlgen->closeTag();
	}
	xmlgen->closeTag(); // contaSP
	xmlgen->closeTag(); // ChamadasLongaDistanciaBean
	xmlgen->closeTag(); // ContaDetalhadaBean
	xmlgen->closeTag(); // DADOS
	xmlgen->closeTag(); // ARG
	return 1;
}


int ContaDetalhadaLocal::gerarSaidaXMLFile(char*buffer,XMLGen* xmlgen)
{
	XMLGen gen;
	int length = 0;
	this->gerarSaidaXML(buffer,&gen);
	Util::compressFile(gen.retrieveXML(&length),m_xenos.getPathFile());
	// arg dados
	xmlgen->createTag("ARG");
	xmlgen->createTag("DADOS");
	xmlgen->addItem("PATH",m_xenos.getPathFile());
	xmlgen->closeTag();
	xmlgen->closeTag();	
	return 1;
}

int ContaDetalhadaLocal::gerarSaidaHTMLFile(string out,XMLGen* xmlgen,char*conta,char*nomeArquivo)
{
	tuxfw_getlogger()->debug("ContaDetalhadaLocal::gerarSaidaHTMLFile");
	char path[255+1];
	memset(&path,0,256);
	sprintf(path,"%s/%s.html.gz",tuxgetenv("PATH_XENOS_COMMON"),nomeArquivo);

	int pos = 0;
	pos = out.find(conta,pos);
	if(pos != -1)
	{
		string find = "xxxxxxxx";
		string replaced = conta;
		out.replace(pos,find.length(),replaced);	
	}
	Util::compressFile((char*)out.c_str(),path);
	// arg dados
	xmlgen->createTag("ARG");
	xmlgen->createTag("DADOS");	
	xmlgen->addItem("PATH",path);
	xmlgen->closeTag();
	xmlgen->closeTag();	

	return 1;
}