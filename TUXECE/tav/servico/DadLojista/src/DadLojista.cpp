#include <stdio.h>
#include <tuxfw/tuxfw.h>
#define   COD_ERR_EXCEPT "13E0000"
#include <Defines/Defines.h>
#include <Util/Util.hpp>
#include <Senha/Senha.hpp>
#include <PessoaAbstract/PessoaAbstract.hpp>
#include <Lojista/Lojista.hpp>
#include <Venda/Venda.hpp>
#include <TuxHelperClever/TuxHelperClever.h>
#include <Linha/Linha.hpp>
#include <Parametro/Parametro.hpp>

static char *STR_VALIDARSENHA            = "validarSenha";
static char *STR_ALTERARSENHA			 = "alterarSenha";
static char *STR_CONSULTARLOJA		     = "consultarLoja";
static char *STR_CONSULTARRELATORIOLOJA  = "consultarRelatorioLoja";
static char *STR_REGISTRARTRANSACAO      = "registrarTransacao";
static char *STR_VALIDARREGIONAL		 = "validarRegional";
static char *STR_ALTERARESTADOVENDA      = "alterarEstadoVenda";
static char *STR_CONSULTARCONTATO		 = "consultarContato";
static char *STR_CONSULTARDSCUPOM		 = "consultarDsCupom";
static char *STR_CONSULTARIP		     = "consultarIP";
static char *STR_CONSULTARSIGLAUF	     = "consultarSiglaUF";
static char *STR_CONSULTARMENSAGEM		 = "consultarMensagem";

static char *STR_13IOK                   = "13I0000";
static char *STR_13IOKMSG                = "CONSULTA EFETUADA COM SUCESSO";
static char *STR_13IOKALTMSG             = "ALTERACAO EFETUADA COM SUCESSO";
static char *STR_13IOKVALSENMSG          = "SENHA VALIDADA COM SUCESSO";
static char *STR_13IOKREGMSG             = "REGISTRO EFETUADO COM SUCESSO";


static char *STR_13W0001                 = "13W0001";
static char *STR_13W0002                 = "13W0002";
static char *STR_13W0003                 = "13W0003";
static char *STR_13W0004                 = "13W0004";
static char *STR_13W0005                 = "13W0005";
static char *STR_13W0006                 = "13W0006";

static char *STR_13WNOTALTMSG            = "ALTERACAO NAO EFETUADA";
static char *STR_13WNOTCON               = "CONSULTA NAO EFETUADA";
static char *STR_13WNOTIDUFOPER			 = "IDUFOPERADORA NAO ENCONTRADO NO BANCO";
static char *STR_13WNOTDSCUPOM			 = "DESCRICAO NAO ENCONTRADA";


static char *STR_13WNOTAG				 = "Xml de entrada inválido, Tag obrigatória nrsap ou idSitefVenda";

static char *STR_13W9999                 = "13W9999";
static char *STR_13WEXCDESC              = "ERRO DESCONHECIDO";

DECLARE_TUXEDO_SERVICE(DADLOJISTA);

static void ValidarSenha(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	char*  pcTagXmlIn = NULL;
	int    iIdTerminal = -1;

	CPessoaAbstract *oPessoa = CPessoaAbstract::CriaPessoa(CPessoaAbstract::TP_LOJISTA);

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"idTerminal", 0);
	ASSERT_INT(iIdTerminal, pcTagXmlIn, "idTerminal");

	oPessoa->AsLojista()->setIdTerminal(iIdTerminal);

	//
	pcTagXmlIn = helper.walkTree(dnode,"senha", 0);
	ASSERT_STR(pcTagXmlIn, "senha");

	oPessoa->getSenha()->setSenha(pcTagXmlIn);

	try
	{
		oPessoa->getSenha()->validarSenha();

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKVALSENMSG);
	}
	catch(TuxBasicSvcException &e)
	{
		
		if(!strcmp(e.getCode(), "13E0001"))
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_13W0003);
			statusCode.setMsg("ALTERAR SENHA");			
		}
		else
		{
			// seta mensagem de retorno - header
			statusCode.setCod(STR_13W0001);
			statusCode.setMsg("SENHA INVALIDA");
		}
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_13W0002);
		statusCode.setMsg("LOJA NAO CADASTRADA");
	}
	catch(...)
	{
		statusCode.setCod(STR_13W9999);
		statusCode.setMsg(STR_13WEXCDESC);
	}

	// Monta o XML de saída
	xml_g->createTag("validarSenhaLojaVO");
	xml_g->addProp("xmlns", "lojas.tav.vivo.com.br/vo");
	xml_g->closeTag();

	delete oPessoa;
}

static void AlterarSenha(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	char*  pcTagXmlIn = NULL;
	int    iIdTerminal = -1;

	CPessoaAbstract *oPessoa = CPessoaAbstract::CriaPessoa(CPessoaAbstract::TP_LOJISTA);

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"idTerminal", 0);
	ASSERT_INT(iIdTerminal, pcTagXmlIn, "idTerminal");

	oPessoa->AsLojista()->setIdTerminal(iIdTerminal);

	//
	pcTagXmlIn = helper.walkTree(dnode,"senha", 0);
	ASSERT_STR(pcTagXmlIn, "senha");

	oPessoa->getSenha()->setSenha(pcTagXmlIn);

	try
	{
		oPessoa->getSenha()->alterarSenha();

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKALTMSG);
	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		statusCode.setCod(STR_13W0001);
		statusCode.setMsg(STR_13WNOTALTMSG);
	}
	catch(...)
	{
		statusCode.setCod(STR_13W9999);
		statusCode.setMsg(STR_13WEXCDESC);
	}

	// Monta o XML de saída
	xml_g->createTag("alterarSenhaLojaVO");
	xml_g->addProp("xmlns", "lojas.tav.vivo.com.br/vo");
	xml_g->closeTag();

	delete oPessoa;	
}

static void ConsultarLoja(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	char*  pcTagXmlIn = NULL;
	char   cCdLojaOperadoraCartao[32];
	char   cNrTerminal[32];
	char   cSgUF[3];
	char   dsCor[255+1];
	
	CPessoaAbstract *oPessoa = CPessoaAbstract::CriaPessoa(CPessoaAbstract::TP_LOJISTA);

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"ipTerminal", 0);
	ASSERT_STR(pcTagXmlIn, "ipTerminal");
		
	oPessoa->AsLojista()->setNrIpTerminal(pcTagXmlIn);

	// Monta o XML de saída
	xml_g->createTag("consultarLojaVO");
	xml_g->addProp("xmlns", "lojas.tav.vivo.com.br/vo");

	try
	{
		oPessoa->AsLojista()->consultarDados();

		xml_g->addItem("idTerminal", oPessoa->AsLojista()->getIdTerminal());
		xml_g->addItem("idPessoaDePara", oPessoa->getIdPessoaDePara());
		xml_g->addItem("cdLojaOperadoraCartao", oPessoa->AsLojista()->getCdLojaOperadoraCartao(cCdLojaOperadoraCartao));
		xml_g->addItem("nrTerminal", oPessoa->AsLojista()->getNrTerminal(cNrTerminal));
		xml_g->addItem("idUFOperadora", oPessoa->AsLojista()->getIdUFOperadora());
		xml_g->addItem("sgUF", oPessoa->AsLojista()->getSgUF(cSgUF));
		xml_g->addItem("dsCor", CUtil::trim(oPessoa->AsLojista()->getDsCor(dsCor)));
		xml_g->addItem("inSitefAtivoRecarga", oPessoa->AsLojista()->getInSitefAtivoRecarga());
		xml_g->addItem("inSitefAtivoPagamento", oPessoa->AsLojista()->getInSitefAtivoPagamento());
		
		
		///inSitefAtivo
		
		// seta mensagem de retorno - header
		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKMSG);
	}
	catch(TuxBasicOraException &e)
	{
		if(NO_DATA_FOUND == e.eCode)
		{
			statusCode.setCod(STR_13W0001);
			statusCode.setMsg("ESTA LOJA NAO POSSUI DADOS CADASTRADOS");
		}
		else
		{
			statusCode.setCod(STR_13W0002);
			statusCode.setMsg(STR_13WNOTCON);
		}
	}
	catch(...)
	{
		statusCode.setCod(STR_13W9999);
		statusCode.setMsg(STR_13WEXCDESC);
	}

	xml_g->closeTag();

	delete oPessoa;
}

static void ConsultarRelatorioLoja(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{	
	CTuxHelperClever helper;

	char*  pcTagXmlIn = NULL;
	int    iIdTerminal = -1;
	int    iIdPessoaDePara = -1;

	bool   blTemInf = false;
	char   cGet[32];
	CVenda oVenda;

	CPessoaAbstract *oPessoa = CPessoaAbstract::CriaPessoa(CPessoaAbstract::TP_LOJISTA);
	list<CVenda>     listaVendas;

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"idTerminal", 0);
	ASSERT_INT(iIdTerminal, pcTagXmlIn, "idTerminal");

	oPessoa->AsLojista()->setIdTerminal(iIdTerminal);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idPessoaDePara", 0);
	ASSERT_INT(iIdPessoaDePara, pcTagXmlIn, "idPessoaDePara");

	oPessoa->setIdPessoaDePara(iIdPessoaDePara);

	//
	pcTagXmlIn = helper.walkTree(dnode,"dataInicial", 0);
	ASSERT_STR(pcTagXmlIn, "dataInicial");

	oPessoa->AsLojista()->getVenda()->setDataInicial(pcTagXmlIn);
	//
	pcTagXmlIn = helper.walkTree(dnode,"dataFinal", 0);
	ASSERT_STR(pcTagXmlIn, "dataFinal");

	oPessoa->AsLojista()->getVenda()->setDataFinal(pcTagXmlIn);

	// Monta o XML de saída
	xml_g->createTag("consultarRelatorioLojaVO");
	xml_g->addProp("xmlns", "lojas.tav.vivo.com.br/vo");

	try
	{
		oPessoa->AsLojista()->getVenda()->consultarVendas(listaVendas);

		while(listaVendas.size() > 0) 
		{
			blTemInf = true;

			oVenda = listaVendas.front();

			xml_g->createTag("RelatorioLoja");
				xml_g->addItem("dataVenda", oVenda.getDataVenda(cGet));
				xml_g->addItem("bandeira", oVenda.getBandeira(cGet));
				xml_g->addItem("nsu", oVenda.getNsuHost());
				xml_g->addItem("nrcart", oVenda.getNrCartao(cGet));
				xml_g->addItem("valor", oVenda.getValor(cGet));
				xml_g->addItem("nrsap", oVenda.getNrSAP(cGet));
				xml_g->addItem("idTipo", oVenda.getIdTipo());
				xml_g->addItem("tipo", oVenda.getTipo(cGet));
				xml_g->addItem("inEstadoVenda", oVenda.getInEstadoVenda());
				xml_g->addItem("nrOrdem",CUtil::trim(oVenda.getNrOrdem(cGet)));
			xml_g->closeTag();

			listaVendas.pop_front();
		}

		if(blTemInf)
		{
			// seta mensagem de retorno - header	
			statusCode.setCod(STR_13IOK);
			statusCode.setMsg(STR_13IOKMSG);
		}
		else
		{
			// seta mensagem de retorno - header	
			statusCode.setCod(STR_13W0001);
			statusCode.setMsg("NAO HA DADOS CADASTRADOS");
		}

	}
	catch(TuxBasicOraException &e)
	{
		// removendo warning
		e.eCode = 0;

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_13W0002);
		statusCode.setMsg(STR_13WNOTCON);
	}
	catch(...)
	{
		// seta mensagem de retorno - header	
		statusCode.setCod(STR_13W9999);
		statusCode.setMsg(STR_13WEXCDESC);
	}
	
	xml_g->closeTag();

}

static void RegistrarTransacao(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	char*  pcTagXmlIn = NULL;
	int    iIdTerminal = -1;
	int    iIdPessoaDePara = -1;
	int    iIdTipoLinha = -1;
	int	   iCdAreaRegistro = -1;
	int	   iNrLinha = -1;
	int    iInEstadoVenda = -1;

	CPessoaAbstract *oPessoa = CPessoaAbstract::CriaPessoa(CPessoaAbstract::TP_LOJISTA);

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"idTerminal", 0);
	ASSERT_INT(iIdTerminal, pcTagXmlIn, "idTerminal");

	oPessoa->AsLojista()->setIdTerminal(iIdTerminal);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idPessoaDePara", 0);
	ASSERT_INT(iIdPessoaDePara, pcTagXmlIn, "idPessoaDePara");

	oPessoa->setIdPessoaDePara(iIdPessoaDePara);
	
	//
	pcTagXmlIn = helper.walkTree(dnode,"dataVenda", 0);
	ASSERT_STR(pcTagXmlIn, "dataVenda");

	oPessoa->AsLojista()->getVenda()->setDataVenda(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"bandeira", 0);
	ASSERT_STR(pcTagXmlIn, "bandeira");

	oPessoa->AsLojista()->getVenda()->setBandeira(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"nsu", 0);
	ASSERT_STR(pcTagXmlIn, "nsu");

	oPessoa->AsLojista()->getVenda()->setNrNsu(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"nrcart", 0);
	ASSERT_STR(pcTagXmlIn, "nrcart");

	oPessoa->AsLojista()->getVenda()->setNrCartao(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"valor", 0);
	ASSERT_STR(pcTagXmlIn, "valor");
	
	oPessoa->AsLojista()->getVenda()->setValor(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"nrsap", 0);
	ASSERT_STR(pcTagXmlIn, "nrsap");

	oPessoa->AsLojista()->getVenda()->setNrSAP(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"idTipo", 0);
	ASSERT_INT(iIdTipoLinha, pcTagXmlIn, "idTipo");

	oPessoa->AsLojista()->getVenda()->setIdTipo(iIdTipoLinha);

	//
	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);
	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");

	oPessoa->AsLojista()->getVenda()->setCdAreaRegistro(iCdAreaRegistro);

	//
	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);
	ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha");

	oPessoa->AsLojista()->getVenda()->setNrLinha(iNrLinha);

	//
	pcTagXmlIn = helper.walkTree(dnode,"inEstadoVenda", 0);
	ASSERT_INT(iInEstadoVenda, pcTagXmlIn, "inEstadoVenda");

	oPessoa->AsLojista()->getVenda()->setInEstadoVenda(iInEstadoVenda);

	//
	pcTagXmlIn = helper.walkTree(dnode,"nrOrdem", 0);
	ASSERT_STR(pcTagXmlIn, "nrOrdem");

	oPessoa->AsLojista()->getVenda()->setNrOrdem(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"dsMsgErro", 0);
	
	if(pcTagXmlIn)
		oPessoa->AsLojista()->getVenda()->setDsMsgErroVenda(pcTagXmlIn);

	try
	{
		oPessoa->AsLojista()->getVenda()->inserir();

		// seta mensagem de retorno - header	
		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKREGMSG);
	}
	catch(...)
	{
		// seta mensagem de retorno - header	
		statusCode.setCod(STR_13W0001);
		statusCode.setMsg("REGISTRO NAO EFETUADO");
	}
	
	// Monta o XML de saída
	xml_g->createTag("registrarTransacaoLojaVO");
	xml_g->addProp("xmlns", "lojas.tav.vivo.com.br/vo");
	xml_g->addItem("idSitefVenda", oPessoa->AsLojista()->getVenda()->getIdVenda());
	xml_g->closeTag();
}

static void ValidarRegional(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	char*  pcTagXmlIn = NULL;
	int    iIdTerminal = -1;
	int	   iCdAreaRegistro = -1;

	CPessoaAbstract *oPessoa = CPessoaAbstract::CriaPessoa(CPessoaAbstract::TP_LOJISTA);

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"ipTerminal", 0);
	ASSERT_STR(pcTagXmlIn, "ipTerminal");
		
	oPessoa->AsLojista()->setNrIpTerminal(pcTagXmlIn);

	//
	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);
	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");


	try
	{
		oPessoa->AsLojista()->consultarRegional(iCdAreaRegistro);

		if (oPessoa->AsLojista()->getIdGrupoOperadora() == oPessoa->AsLojista()->getIdGrupoOperadoraLinha())
		//if (oPessoa->AsLojista()->getIdUFOperadora() == oPessoa->AsLojista()->getIdUFOperadoraLinha())
		{
			// seta mensagem de retorno - header	
			statusCode.setCod(STR_13IOK);
			statusCode.setMsg("Linha e Terminal pertencem a mesma regional.");
		}
		else
		{
			// seta mensagem de retorno - header	
			statusCode.setCod(STR_13W0001);
			statusCode.setMsg("Linha e Terminal sao de regionais distintas.");
		}
	}
	catch(...)
	{
		// seta mensagem de retorno - header	
		statusCode.setCod(STR_13W0002);
		statusCode.setMsg("VALIDACAO NAO EFETUADA");
	}
	
	// Monta o XML de saída
	xml_g->createTag("validarRegionalVO");
	xml_g->addProp("xmlns", "lojas.tav.vivo.com.br/vo");
	xml_g->closeTag();
}

static void AlterarEstadoVenda(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	char*  pcTagXmlIn = NULL;
	int    iInEstadoVenda = -1;
	char   cNrSap[11];
	int    iIdSitefVenda;
	char   cBandeira[16];
	char   cNsu[7];
	char   cNrcart[21];
	char   cValor[32];
	char   cNrOrdem[21];
	int   iNrAutorizador = 0;
	char  nsuHostAutorizador[16];

	CPessoaAbstract *oPessoa = CPessoaAbstract::CriaPessoa(CPessoaAbstract::TP_LOJISTA);

	//Deve ser passada a Tag nrsap ou idSitefVenda
	pcTagXmlIn = helper.walkTree(dnode,"nrsap", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0') 
		strcpy(cNrSap, CUtil::trim(pcTagXmlIn));
	else
		strcpy(cNrSap, "");
	oPessoa->AsLojista()->getVenda()->setNrSAP(cNrSap);

	//Deve ser passada a Tag nrsap ou idSitefVenda
	pcTagXmlIn = helper.walkTree(dnode,"idSitefVenda", 0);
	if (pcTagXmlIn != NULL) 
		iIdSitefVenda = atoi(pcTagXmlIn);
	else
		iIdSitefVenda = 0;
	oPessoa->AsLojista()->getVenda()->setIdVenda(iIdSitefVenda);


	//
	pcTagXmlIn = helper.walkTree(dnode,"inEstadoVenda", 0);
	ASSERT_INT(iInEstadoVenda, pcTagXmlIn, "inEstadoVenda");

	oPessoa->AsLojista()->getVenda()->setInEstadoVenda(iInEstadoVenda);

	//
	pcTagXmlIn = helper.walkTree(dnode,"dsMsgErro", 0);
	
	if(pcTagXmlIn)
		oPessoa->AsLojista()->getVenda()->setDsMsgErroVenda(pcTagXmlIn);


	//
	pcTagXmlIn = helper.walkTree(dnode,"bandeira", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0') 
		strcpy(cBandeira, CUtil::trim(pcTagXmlIn));
	else
		strcpy(cBandeira, "");
	oPessoa->AsLojista()->getVenda()->setBandeira(cBandeira);

	//
	pcTagXmlIn = helper.walkTree(dnode,"nsu", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0') 
		strcpy(cNsu, CUtil::trim(pcTagXmlIn));
	else
		strcpy(cNsu, "");
	oPessoa->AsLojista()->getVenda()->setNrNsu(cNsu);

	//
	pcTagXmlIn = helper.walkTree(dnode,"nrcart", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0') 
		strcpy(cNrcart, CUtil::trim(pcTagXmlIn));
	else
		strcpy(cNrcart, "");
	oPessoa->AsLojista()->getVenda()->setNrCartao(cNrcart);

	//
	pcTagXmlIn = helper.walkTree(dnode,"valor", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0') 
		strcpy(cValor, CUtil::trim(pcTagXmlIn));
	else
		strcpy(cValor, "");
	oPessoa->AsLojista()->getVenda()->setValor(cValor);

	//
	pcTagXmlIn = helper.walkTree(dnode,"nrOrdem", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0') 
		strcpy(cNrOrdem, CUtil::trim(pcTagXmlIn));
	else
		strcpy(cNrOrdem, "");
	oPessoa->AsLojista()->getVenda()->setNrOrdem(cNrOrdem);


	pcTagXmlIn = helper.walkTree(dnode,"dtProcVenda", 0);
	
	if(pcTagXmlIn)
		oPessoa->AsLojista()->getVenda()->setDtProcVenda(pcTagXmlIn);

	pcTagXmlIn = helper.walkTree(dnode,"nsuAutorizador", 0);

	iNrAutorizador = atoi(pcTagXmlIn);
	
	if(iNrAutorizador)
		oPessoa->AsLojista()->getVenda()->setNsuAutorizador(iNrAutorizador);

	
	pcTagXmlIn = helper.walkTree(dnode,"nsuHostAutorizador", 0);
	if(pcTagXmlIn)
		oPessoa->AsLojista()->getVenda()->setNsuHostAutorizador(pcTagXmlIn);


	if ( strcmp(cNrSap, "") == 0 && iIdSitefVenda == 0){
		// seta mensagem de retorno - header	
		statusCode.setCod(STR_13W0002);
		statusCode.setMsg(STR_13WNOTAG);
	}else{
		try
		{
			oPessoa->AsLojista()->getVenda()->alteraEstadoVenda();

			// seta mensagem de retorno - header	
			statusCode.setCod(STR_13IOK);
			statusCode.setMsg(STR_13IOKALTMSG);
		}
		catch(...)
		{
			// seta mensagem de retorno - header	
			statusCode.setCod(STR_13W0001);
			statusCode.setMsg(STR_13WNOTALTMSG);
		}
	}
	
	// Monta o XML de saída
	xml_g->createTag("alterarEstadoVendaVO");
	xml_g->addProp("xmlns", "lojas.tav.vivo.com.br/vo");
	xml_g->closeTag();

}


static void ConsultarContato(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CLinha oLinha;
	CParametro oParametro;

	int iIdPessoaDeParaCliente;
	int iIdPessoaDeParaUsuario;
	int iRet;

	char* pcTagXmlIn=NULL;
	int iCdAreaRegistro;
	int iNrLinha;
	int iIdContatoSAP=0;
	int iIdContatoBILLING=0;
	char chrIdGrupoEstorno[256];
	char cCdContatoSAP[256]="";
	char cCdContatoBILLING[256]="";

	//
	pcTagXmlIn = helper.walkTree(dnode,"cdAreaRegistro", 0);
	ASSERT_INT(iCdAreaRegistro, pcTagXmlIn, "cdAreaRegistro");
	//
	pcTagXmlIn = helper.walkTree(dnode,"nrLinha", 0);
	ASSERT_INT(iNrLinha, pcTagXmlIn, "nrLinha");
	//
	pcTagXmlIn = helper.walkTree(dnode,"cdContatoSAP", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0')
		strcpy(cCdContatoSAP, pcTagXmlIn);
	//
	pcTagXmlIn = helper.walkTree(dnode,"cdContatoBILLING", 0);
	if (pcTagXmlIn != NULL && *pcTagXmlIn != '\0')
		strcpy(cCdContatoBILLING, pcTagXmlIn);

	oLinha.setCdAreaRegistro(iCdAreaRegistro);
	oLinha.setNrLinha(iNrLinha);

	try{	
		// Obtendo IdPessoaDePara do Cliente
		oLinha.getPessoa()->setIdTipoRelacionamento(PESSOA_CLIENTE);

		oLinha.getPessoa()->obterIdPessoa(oLinha.getCdAreaRegistro(),
										 oLinha.getNrLinha());

		iIdPessoaDeParaCliente = oLinha.getPessoa()->getIdPessoaDePara();

		// Obtendo IdPessoaDePara do Usuario
		oLinha.getPessoa()->setIdTipoRelacionamento(PESSOA_USUARIO);

		oLinha.getPessoa()->obterIdPessoa(oLinha.getCdAreaRegistro(),
										 oLinha.getNrLinha());

		iIdPessoaDeParaUsuario = oLinha.getPessoa()->getIdPessoaDePara();

		// Devolvendo idTipoRelacionamento original		
		oLinha.getPessoa()->setIdTipoRelacionamento(PESSOA_CLIENTE);

		if (strcmp(cCdContatoSAP,"") != 0){
			oParametro.setChave(cCdContatoSAP); 
			oParametro.consultar(); 
			iIdContatoSAP = atoi(oParametro.getConsulta()); 
		}

		if (strcmp(cCdContatoBILLING,"") != 0){
			oParametro.setChave(cCdContatoBILLING); 
			oParametro.consultar(); 
			iIdContatoBILLING = atoi(oParametro.getConsulta()); 
		}
	}
	catch (...)
	{
		throw TuxBasicSvcException(STR_13W9999,STR_13WNOTCON);
	}

	try
	{
		iRet = oLinha.obterDadosLinhaPessoaSessao();
	}
	catch (...)
	{
		throw TuxBasicSvcException(STR_13W9999,STR_13WNOTCON);
	}

	xml_g->createTag("consultarContatoVO");
	xml_g->addProp("xmlns", "lojas.tav.vivo.com.br/vo");
	
	xml_g->addItem("idChamadaTelefonica", 0);
	
	strcpy(chrIdGrupoEstorno, oParametro.GetIdGrupoEstorno());
	xml_g->addItem("idGrupoAbertura", chrIdGrupoEstorno);

	xml_g->addItem("inResponsavelAbertura", oLinha.getPessoa()->getIdTipoRelacionamento());
	xml_g->addItem("nmContato", oLinha.getPessoa()->getNomeCliente());
	xml_g->addItem("observacao", "");

	//xml_g->addItem("inTipoPessoa", 1234); //Não é Obrigatório
	//xml_g->addItem("nrTelefone", ); //Será passado pelo java
	xml_g->addItem("tpOperacao", TP_OPERACAO_ENCAMINHAMENTO);
	xml_g->addItem("idProcedencia", oLinha.getIdProcedencia());

	//xml_g->addItem("idCanal", 1234); //Será passado pelo java
	xml_g->addItem("idPessoaCliente", iIdPessoaDeParaCliente);
	xml_g->addItem("idPessoaUsuario", iIdPessoaDeParaUsuario);
	if (iIdContatoSAP==0){
		xml_g->addItem("idContatoSAP", "");
	}else{
		xml_g->addItem("idContatoSAP", iIdContatoSAP);
	}
	if (iIdContatoBILLING==0){
		xml_g->addItem("idContatoBILLING", "");	
	}else{
		xml_g->addItem("idContatoBILLING", iIdContatoBILLING);	
	}
	xml_g->addItem("idTipoCarteira", oLinha.getPessoa()->getIdTipoCarteira()); 
	xml_g->addItem("idSegmentacao", oLinha.getIdSegmentacao());
	//xml_g->addItem("idTipoComunicacao", 1234); //Não é Obrigatório

	xml_g->closeTag();

	// seta mensagem de retorno - header	
	statusCode.setCod(STR_13IOK);
	statusCode.setMsg(STR_13IOKMSG);
}

static void ConsultarDsCupom(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;
	CLinha		  oLinha; 
	char*         pcTagXmlIn = NULL;
	int           iIdUFOperadora;
	char		  chrObsRecargas[256];
	int			  iIdUF;

	chrObsRecargas[0] = NULL;
	iIdUF = -1;

	//Navega no xml
	pcTagXmlIn = helper.walkTree(dnode,"idUFOperadora", 0);
	ASSERT_INT(iIdUFOperadora, pcTagXmlIn, "idUFOperadora");

	oLinha.setIdUFOperadora(iIdUFOperadora); 

	try
	{
		iIdUF = oLinha.consultarIdUF(); 
		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKMSG);
	}
	catch(TuxBasicOraException &e)
	{
	
		if(NO_DATA_FOUND == e.eCode){		
				
			statusCode.setCod(STR_13W0005);				
			statusCode.setMsg(STR_13WNOTIDUFOPER);
			
		}else{
			statusCode.setCod(STR_13W9999);				
			statusCode.setMsg(STR_13WEXCDESC);				
			}
	}		
	if (iIdUF != -1){	 // se nao existe iduf entao nao faz a busca da descrição	

		oLinha.setIdUF(iIdUF);
		try{

			strcpy(chrObsRecargas, oLinha.consultarObsRecarga());
			
			strcpy(chrObsRecargas,  CUtil::trim(chrObsRecargas));
		} 
		catch (TuxBasicOraException &e1){

			if(NO_DATA_FOUND == e1.eCode){		
					
				statusCode.setCod(STR_13W0006);				
				statusCode.setMsg(STR_13WNOTDSCUPOM);
				
			}else{
				statusCode.setCod(STR_13W9999);				
				statusCode.setMsg(STR_13WEXCDESC);					
			}		
		}
	}
	xml_g->createTag("consultarDsCupomVO");

	xml_g->addProp("xmlns", "lojas.tav.vivo.com.br/vo");
	
	if (strlen(chrObsRecargas) > 0)
		xml_g->addItem("DSCUPOM", chrObsRecargas);

	xml_g->closeTag();
	
}


static void ConsultarTerminal(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;
	CLojista		  lojista; 
	char*         ipTerminal = NULL;
	char*         ddd = NULL;

	//Navega no xml
	ipTerminal = helper.walkTree(dnode,"ipTerminal", 0);
	ddd = helper.walkTree(dnode,"ddd", 0);

	try
	{
		lojista.consultarTerminal(ipTerminal,ddd);
		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKMSG);
	}
	catch(TuxBasicOraException &e)
	{	
		if(NO_DATA_FOUND == e.eCode){		
				
			statusCode.setCod(STR_13W0005);				
			statusCode.setMsg("Terminal não autorizado");
			
		}else{
			statusCode.setCod(STR_13W9999);				
			statusCode.setMsg(STR_13WEXCDESC);				
			}
	}		

}

static void ConsultarSiglaUF(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;
	CLojista		  lojista; 
	char*         ddd = NULL;
	char sgUF[256];
	memset(&sgUF,0,256);

	//Navega no xml
	ddd = helper.walkTree(dnode,"ddd", 0);

	try
	{
		lojista.consultarSiglaUF(ddd,sgUF);
		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKMSG);
	}
	catch(TuxBasicOraException &e)
	{	
		if(NO_DATA_FOUND == e.eCode){		
				
			statusCode.setCod(STR_13W0005);				
			statusCode.setMsg("Erro ao consultar sigla UF");
			
		}else{
			statusCode.setCod(STR_13W9999);				
			statusCode.setMsg(STR_13WEXCDESC);				
			}
	}		

	xml_g->createTag("consultarSiglaUFVO");

	xml_g->addProp("xmlns", "lojas.tav.vivo.com.br/vo");
	
	xml_g->addItem("sgUF", sgUF);

	xml_g->closeTag();

}

static void ConsultarMensagem(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;
	CLojista		  lojista; 

	try
	{
		lojista.consultarMensagensSitef(xml_g);
		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKMSG);
	}
	catch(TuxBasicOraException &e)
	{	
		if(NO_DATA_FOUND == e.eCode){		
				
			statusCode.setCod(STR_13W0005);				
			statusCode.setMsg("Não existe mensagens");
			
		}else{
			statusCode.setCod(STR_13W9999);				
			statusCode.setMsg(STR_13WEXCDESC);				
			}
	}		

}

void implDADLOJISTA::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

	CStatusCode	  statusCode;
	char*         pcTagXmlIn = NULL;

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"operacao", 0);
	ASSERT_STR(pcTagXmlIn, "operacao");

	// descobrindo qual operação executar
	if(!strcmp(pcTagXmlIn, STR_VALIDARSENHA))

		ValidarSenha(dnode, xml_g, statusCode);

	else if(!strcmp(pcTagXmlIn, STR_ALTERARSENHA))

		AlterarSenha(dnode, xml_g, statusCode);

	else if(!strcmp(pcTagXmlIn, STR_CONSULTARLOJA))

		ConsultarLoja(dnode, xml_g, statusCode);

	else if(!strcmp(pcTagXmlIn, STR_CONSULTARRELATORIOLOJA))

		ConsultarRelatorioLoja(dnode, xml_g, statusCode);

	else if(!strcmp(pcTagXmlIn, STR_REGISTRARTRANSACAO))

		RegistrarTransacao(dnode, xml_g, statusCode);

	else if(!strcmp(pcTagXmlIn, STR_VALIDARREGIONAL))

		ValidarRegional(dnode, xml_g, statusCode);

	else if(!strcmp(pcTagXmlIn, STR_ALTERARESTADOVENDA))

		AlterarEstadoVenda(dnode, xml_g, statusCode);

	else if (!strcmp(pcTagXmlIn, STR_CONSULTARCONTATO))  
		ConsultarContato(dnode, xml_g, statusCode);

	else if (!strcmp(pcTagXmlIn, STR_CONSULTARDSCUPOM))  
		ConsultarDsCupom(dnode, xml_g, statusCode);

	else if (!strcmp(pcTagXmlIn, STR_CONSULTARIP))  
		ConsultarTerminal(dnode, xml_g, statusCode);

	else if(!strcmp(pcTagXmlIn,STR_CONSULTARSIGLAUF))
		ConsultarSiglaUF(dnode,xml_g,statusCode);

	else if(!strcmp(pcTagXmlIn,STR_CONSULTARMENSAGEM))
		ConsultarMensagem(dnode,xml_g,statusCode);

	// seta mensagem de retorno - header
    setStatusCode(statusCode.getCod(), statusCode.getMsg());

}

