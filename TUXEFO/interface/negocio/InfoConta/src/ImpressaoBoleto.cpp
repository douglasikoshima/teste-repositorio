#include "ImpressaoBoleto.h"

ImpressaoBoleto::ImpressaoBoleto()
{
	this->m_operation = OPERACAO_BOLETO_IMPRESSO;
	m_service.getLog()->setDsProcesso("Conta Boleto");
}

ImpressaoBoleto::~ImpressaoBoleto()
{

}

int ImpressaoBoleto::execute(DOMNode* dnode, XMLGen* xmlgen)
{
	tuxfw_getlogger()->debug("ImpressaoBoleto::execute()");

	ImagemConta imagemConta;
	bool existConta = false;
	bool cache = false;
	int retcode = 0;
	int tipoRetorno = TIPO_RETORNO_HTML;

	char*canal =  m_mem.getTag(dnode,XML_TAG_INPUT_CANAL,0);
	char*conta =  m_mem.getTag(dnode,XML_TAG_INPUT_CONTA,0);
	char*data =  m_mem.getTag(dnode,XML_TAG_INPUT_DATA,0);
	char*retorno =  m_mem.getTag(dnode,XML_TAG_INPUT_RETORNO,0);
	char*cod_area =  m_mem.getTag(dnode,XML_TAG_INPUT_COD_AREA,0);

	stImagemConta imgConta;
	memset(&imgConta,0,sizeof(stImagemConta));
	strcpy(imgConta.idContaSistemaOrigem,conta);
	strcpy(imgConta.dtFimCiclo,data);
	strcpy(imgConta.sgTipoImagem,DOCUMENT_TYPE_R);
	if(retorno!=NULL && strcmp(retorno,"XML")==0)
	{
		imgConta.inXml = 1;
		tipoRetorno = TIPO_RETORNO_XML;
	}
	else
	{
		imgConta.inXml = 0;
		tipoRetorno = TIPO_RETORNO_HTML;
	}

	stCommonFields field;
	memset(&field,0,sizeof(field));
	field.canal = canal;
	field.cDocType = DOCUMENT_TYPE_R;
	field.conta = conta;
	field.data = data;
	field.docType = DOCUMENT_TYPE_NUM_R;
	field.out = "";
	field.retorno = retorno;
	field.accessNbr="";


	// Verificação de imagem no disco, para melhorar a performance
	try
	{
		memset(this->m_sgUF,0,strlen(this->m_sgUF));
		if(imagemConta.getAreaRegistro(cod_area,this->m_sgUF) == 0)
			tuxfw_getlogger()->debug("Problemas ao retornar Área de registro");
		// verifica se a imagem foi gravada na base
		existConta = imagemConta.selectImagemConta(imgConta);
	}
	catch(TuxBasicOraException tboe)
	{
		tuxfw_getlogger()->debug("erro de oracle");
	}

	// O caminho da imagem foi gravado na base
	if(existConta)
	{
		tuxfw_getlogger()->debug("A imagem ainda não foi processada pelo xenos e foi requisitada pelo cliente");
		tuxfw_getlogger()->debug("NMPATHDETALHADA = %s",imgConta.nmPathDetalhada);
		tuxfw_getlogger()->debug("NMPATHRESUMIDA = %s",imgConta.nmPathResumida);
		tuxfw_getlogger()->debug("NMPATHBOLETO = %s",imgConta.nmPathBoleto);			
		char nomeImagemBoleto[255];	// Nome da imagem final a ser gravada
		memset(&nomeImagemBoleto,0,255);
		
		// Invocar método que recupera imagem de saída do xenos aplicando correções no HTML
		if(this->getImagemXenosSemEspera(field,imgConta.nmPathBoleto,tipoRetorno))
		{
			// Recuperar o nome da imagem
			this->getNomeArquivo(nomeImagemBoleto,imgConta.nmPathBoleto);
			// método gera saída XML e comprime arquivo criado no diretório de armazenamento
			if(strcmp(retorno,"XML") == 0)
				this->gerarSaidaXMLFile((char*)field.out.c_str(),xmlgen,nomeImagemBoleto);	
			else
				this->gerarSaidaHTMLFile(field.out,xmlgen,nomeImagemBoleto);
			strcpy(imgConta.nmImagem,nomeImagemBoleto);
			// sucesso, remover a imagem do banco
			if(imagemConta.callRemoverImagem())
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


	retcode = this->exec(field,xmlgen);

	if(retcode == ERROR_DATA_NAO_ENCONTRADA)
		return 1;
	else
	if(retcode != 1)
		return retcode;

	try
	{
		strcpy(imgConta.nmPathBoleto,field.outputPathXenos);
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

int ImpressaoBoleto::gerarSaidaXML(char*buffer,XMLGen* xmlgen)
{
	tuxfw_getlogger()->debug("ImpressaoBoleto::gerarSaidaXML");
	//tuxfw_getlogger()->debug("buffer passado = %s",buffer);
	DOMNode*domNode = m_mem.createDOMNode(buffer);
	if(domNode == NULL)
	{
		return -8;
	}
	xmlgen->createTag("ARG");
	xmlgen->createTag("DADOS");
	xmlgen->createTag("BoletoPagamentoBean");
	xmlgen->addProp("class","br.com.vivo.sce.beans.pospago.BoletoPagamentoBean");
	xmlgen->createTag("beanSP");
	xmlgen->addProp("class","br.com.vivo.sce.beans.sp.boleto.BoletoBeanSP");

	// tag Cliente
	xmlgen->createTag("cliente");
	xmlgen->addProp("class","br.com.vivo.sce.beans.sp.boleto.DadosCliente");

	char*P1_NOMECLI = m_mem.getTag(domNode,"P1_NOMECLI",0);
	char*P1_ENDCLI = m_mem.getTag(domNode,"P1_ENDCLI",0);
	char*P1_COMPL = m_mem.getTag(domNode,"P1_COMPL",0);
	char*P1_BAIRROCLI = m_mem.getTag(domNode,"P1_BAIRROCLI",0);
	char*P1_CIDADE = m_mem.getTag(domNode,"P1_CIDADE",0);
	char*P1_CEP = m_mem.getTag(domNode,"P1_CEP",0);
	
	xmlgen->addItem("bairro",P1_BAIRROCLI);
	xmlgen->addItem("cep",P1_CEP);
	xmlgen->addItem("cidade",P1_CIDADE);
	xmlgen->addItem("codigo","0");
	xmlgen->addItem("descricao","OK");
	xmlgen->addItem("endereco",P1_ENDCLI);
	xmlgen->addItem("nome",P1_NOMECLI);
	xmlgen->addItem("numberRedelivery","0");
	xmlgen->addItem("numeroCelular","0");
	xmlgen->addItem("severidade","5");


	xmlgen->closeTag();


	// tag dadosConta
	xmlgen->createTag("dadosConta");
	xmlgen->addProp("class","br.com.vivo.sce.beans.sp.boleto.DadosConta");

	char*P1_NUMEROCONTA = m_mem.getTag(domNode,"P1_NUMEROCONTA",0);
	char*P1_EMISSAO = m_mem.getTag(domNode,"P1_EMISSAO",0);
	char*P1_MES = m_mem.getTag(domNode,"P1_MES",0);
	char*P1_PERIODO = m_mem.getTag(domNode,"P1_PERIODO",0);
	char*P1_VENCIMENTO = m_mem.getTag(domNode,"P1_VENCIMENTO",0);
	char*P1_2VIA = m_mem.getTag(domNode,"P1_2VIA",0);

	xmlgen->addItem("codigo","0");
	xmlgen->addItem("conta",P1_NUMEROCONTA);
	xmlgen->addItem("descricao","OK");
	xmlgen->addItem("emissao",P1_EMISSAO);
	xmlgen->addItem("mes",P1_MES);
	xmlgen->addItem("mesFormatado","");
	xmlgen->addItem("numberRedelivery","0");
	xmlgen->addItem("periodo",P1_PERIODO);
	xmlgen->addItem("segundaVia",P1_2VIA);
	xmlgen->addItem("severidade","5");
	xmlgen->addItem("vencimento",P1_VENCIMENTO);

	xmlgen->closeTag();

	// tag informacoesVivo	
	xmlgen->createTag("informacoesVivo");
	xmlgen->addProp("class","br.com.vivo.sce.beans.sp.boleto.InformacoesVivo");

	char*P1_NRVIVO = m_mem.getTag(domNode,"P1_NRVIVO",0);
	char*P1_ATENDVIVO = m_mem.getTag(domNode,"P1_ATENDVIVO",0);
	char*P1_MSGVIVO = m_mem.getTag(domNode,"P1_MSGVIVO",0);
	char*P1_MSG2VIVO = m_mem.getTag(domNode,"P1_MSG2VIVO",0);
	char*P1_SITEVIVO = m_mem.getTag(domNode,"P1_SITEVIVO",0);

	xmlgen->addItem("codigo","0");
	xmlgen->addItem("descricao","OK");
	xmlgen->addItem("msg1",P1_MSGVIVO);
	xmlgen->addItem("msg2",P1_MSG2VIVO);
	xmlgen->addItem("nomeAutoAtendimento",P1_ATENDVIVO);
	xmlgen->addItem("nrAutoAtendimento",P1_NRVIVO);
	xmlgen->addItem("numberRedelivery","0");
	xmlgen->addItem("severidade","5");
	xmlgen->addItem("site",P1_SITEVIVO);

	xmlgen->closeTag();

	// tag lançamentos
	DOMNode*P1_LANCAMENTO = NULL;
	char*P1_CABEC = m_mem.getTag(domNode,"P1_CABEC",0);
	xmlgen->createTag("lancamentos");
	xmlgen->addProp("class","br.com.vivo.sce.beans.sp.boleto.Lancamentos");
	xmlgen->addItem("cabecalho",P1_CABEC);
	xmlgen->addItem("codigo","0");
	xmlgen->addItem("descricao","OK");
	xmlgen->addItem("numberRedelivery","0");
	xmlgen->addItem("severidade","5");
	xmlgen->createTag("lancamentos");
	xmlgen->addProp("class","java.util.ArrayList");
	int total=0;
	for(total=0; (P1_LANCAMENTO = tuxhp.walkDOM(domNode,"P1_LANCAMENTO",total)) != NULL;total++)
	{
	}
	char ctotal[16];
	sprintf(ctotal,"%d",total);
	xmlgen->addProp("total",ctotal);

	for(int i=0; (P1_LANCAMENTO = tuxhp.walkDOM(domNode,"P1_LANCAMENTO",i)) != NULL;i++)
	{
		xmlgen->createTag("Lancamento");
		xmlgen->addProp("class","br.com.vivo.sce.beans.sp.boleto.Lancamento");
		char*P1_DESCRICAO = m_mem.getTag(P1_LANCAMENTO,"P1_DESCRICAO",0);
		char*P1_VALOR = m_mem.getTag(P1_LANCAMENTO,"P1_VALOR",0);
		xmlgen->addItem("codigo","0");
		xmlgen->addItem("descricao",P1_DESCRICAO);
		xmlgen->addItem("numberRedelivery","0");
		xmlgen->addItem("severidade","5");
		xmlgen->addItem("valor",P1_VALOR);
		xmlgen->closeTag();
	}
	// lançamentos
	xmlgen->closeTag();
	// lançamentos
	xmlgen->closeTag();

	// Operadora
	xmlgen->createTag("operadora");
	xmlgen->addProp("class","br.com.vivo.sce.beans.sp.boleto.Operadora");

	char*P1_OPER = m_mem.getTag(domNode,"P1_OPER",0);
	char*P1_ENDOPER = m_mem.getTag(domNode,"P1_ENDOPER",0);
	char*P1_CIDOPER = m_mem.getTag(domNode,"P1_CIDOPER",0);
	char*P1_CEPOPER = m_mem.getTag(domNode,"P1_CEPOPER",0);
	char*P1_IEOPER = m_mem.getTag(domNode,"P1_IEOPER",0);
	char*P1_CNPJOPER = m_mem.getTag(domNode,"P1_CNPJOPER",0);

	xmlgen->addItem("cep",P1_CEPOPER);
	xmlgen->addItem("cidade",P1_CIDOPER);
	xmlgen->addItem("cnpj",P1_CNPJOPER);
	xmlgen->addItem("codigo","0");
	xmlgen->addItem("descricao","OK");
	xmlgen->addItem("endereco",P1_ENDOPER);
	xmlgen->addItem("inscricaoEstadual",P1_IEOPER);
	xmlgen->addItem("nome",P1_OPER);
	xmlgen->addItem("numberRedelivery","0");
	xmlgen->addItem("severidade","5");
	xmlgen->addItem("sgUF",this->m_sgUF);

	xmlgen->closeTag();

	// Operadora
	xmlgen->createTag("rodape");
	xmlgen->addProp("class","br.com.vivo.sce.beans.sp.boleto.Rodape");

	char*P1_NOME = m_mem.getTag(domNode,"P1_NOME",0);
	char*P1_NRCONTA = m_mem.getTag(domNode,"P1_NRCONTA",0);
	P1_MES = m_mem.getTag(domNode,"P1_MES",1);
	char*P1_DEBAUTO = m_mem.getTag(domNode,"P1_DEBAUTO",0);
	char*P1_VENCTO = m_mem.getTag(domNode,"P1_VENCTO",0);
	char*P1_TOTPAGAR = m_mem.getTag(domNode,"P1_TOTPAGAR",0);
	char*P1_LEGCOD = m_mem.getTag(domNode,"P1_LEGCOD",0);


	xmlgen->addItem("codigo","0");
	xmlgen->addItem("codigoBarras",P1_LEGCOD);
	xmlgen->addItem("debitoAutomatico",P1_DEBAUTO);
	xmlgen->addItem("descricao","OK");
	xmlgen->addItem("mes",P1_MES);
	xmlgen->addItem("nomeCliente",P1_NOME);
	xmlgen->addItem("numberRedelivery","0");
	xmlgen->addItem("numeroConta",P1_NRCONTA);
	xmlgen->addItem("severidade","5");
	xmlgen->addItem("valorTotal",P1_TOTPAGAR);
	xmlgen->addItem("vencimento",P1_VENCTO);
 
    xmlgen->closeTag();




	// encerra XML
	xmlgen->closeTag();
	xmlgen->closeTag();
	xmlgen->closeTag();
	xmlgen->closeTag();

	return 1;
}

int ImpressaoBoleto::gerarSaidaXMLFile(char*buffer,XMLGen* xmlgen)
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

int ImpressaoBoleto::gerarSaidaXMLFile(char*buffer,XMLGen* xmlgen,char*nomeArquivo)
{
	XMLGen gen;
	int length = 0;
	char path[255+1];
	memset(&path,0,256);
	sprintf(path,"%s/%s.xml.gz",tuxgetenv("PATH_XENOS_COMMON"),nomeArquivo);
	this->gerarSaidaXML(buffer,&gen);
	Util::compressFile(gen.retrieveXML(&length),path);
	// arg dados
	xmlgen->createTag("ARG");
	xmlgen->createTag("DADOS");
	xmlgen->addItem("PATH",path);
	xmlgen->closeTag();
	xmlgen->closeTag();	
	return 1;
}


int ImpressaoBoleto::gerarSaidaHTML(string out,XMLGen* xmlgen)
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
int ImpressaoBoleto::gerarSaidaHTMLFile(string out,XMLGen* xmlgen)
{
	tuxfw_getlogger()->debug("ImpressaoBoleto::gerarSaidaHTMLFile");
	Util::compressFile((char*)out.c_str(),m_xenos.getPathFile());
	// arg dados
	xmlgen->createTag("ARG");
	xmlgen->createTag("DADOS");	
	xmlgen->addItem("PATH",m_xenos.getPathFile());
	xmlgen->closeTag();
	xmlgen->closeTag();	
	return 1;
}

int ImpressaoBoleto::gerarSaidaHTMLFile(string out,XMLGen* xmlgen,char*nomeArquivo)
{
	tuxfw_getlogger()->debug("ImpressaoBoleto::gerarSaidaHTMLFile");
	char path[255+1];
	memset(&path,0,256);
	sprintf(path,"%s/%s.html.gz",tuxgetenv("PATH_XENOS_COMMON"),nomeArquivo);
	Util::compressFile((char*)out.c_str(),path);
	// arg dados
	xmlgen->createTag("ARG");
	xmlgen->createTag("DADOS");	
	xmlgen->addItem("PATH",path);
	xmlgen->closeTag();
	xmlgen->closeTag();	
	return 1;
}

int ImpressaoBoleto::gerarSaidaCache(char*buffer,int tipo,XMLGen* xmlgen)
{
	tuxfw_getlogger()->debug("ImpressaoBoleto::gerarSaidaCache");
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