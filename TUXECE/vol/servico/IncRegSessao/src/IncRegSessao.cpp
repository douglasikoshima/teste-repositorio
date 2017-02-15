#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Util/Util.hpp>
#include <TuxHelperClever/TuxHelperClever.h>
#include <Relacionamento/Relacionamento.hpp>
#include <PessoaAbstract/PessoaAbstract.hpp>
#include <Lojista/Lojista.hpp>



static char *STR_REGISTRARLOGOUT		 = "registrarLogout";
static char *STR_REGISTRARIP			 = "registrarIP";
static char *STR_CONSULTARIP			 = "consultarIP";

static char *STR_11IOK                   = "11I0000";
static char *STR_11IOKMSG                = "Registro Efetuado com Sucesso";
static char *STR_11IOKMSGRgIP            = "IP Registrado com Sucesso";
static char *STR_11IOKMSGCsIP            = "Consulta de IP efetuada com Sucesso";

static char *STR_11W0001                 = "11W0001";
static char *STR_11W0002                 = "11W0002";
static char *STR_11WNOREG				 = "Registro Não pode ser Efetuada";
static char *STR_11WTAGINV				 = "Valor inválido da tag idTempoSessao";
static char *STR_11WNOREGIP				 = "Consulta de IP Não efetuada";

static char *STR_11W9999                 = "11W9999";
static char *STR_11WEXCDESC              = "ERRO DESCONHECIDO";

DECLARE_TUXEDO_SERVICE(INCREGSESSAO);

static void registrarLogout(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CRelacionamento oRelacionamento;

	char*   pcTagXmlIn = NULL;
	long	lIdTempoSessao = 0;
	int		iInTimeout = 0;
	int     iIdTerminal = 0;
	char    cNrIp[256]="";
	CLojista oLojista;
	
	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"idTempoSessao", 0);
	ASSERT_LONG(lIdTempoSessao, pcTagXmlIn, "idTempoSessao");

	pcTagXmlIn = helper.walkTree(dnode,"inTimeout", 0);
	if (pcTagXmlIn != NULL && atoi(pcTagXmlIn) == 1) 
		iInTimeout = 1;

	pcTagXmlIn = helper.walkTree(dnode,"idTerminal", 0);
	if (pcTagXmlIn != NULL) 
		iIdTerminal = atoi(pcTagXmlIn);




	if (iIdTerminal != 0){

		oLojista.setIdTerminal(iIdTerminal);
		oLojista.consultarDados();
		strcpy(cNrIp, oLojista.getNrIpTerminal(cNrIp));

		


	}


	if (lIdTempoSessao <= 0){
		statusCode.setCod(STR_11W0002);
		statusCode.setMsg(STR_11WTAGINV);
	}
	else
	{
		oRelacionamento.setIdTempoSessao(lIdTempoSessao);
		oRelacionamento.setInTimeout(iInTimeout);
		oRelacionamento.setNrIP(cNrIp);
		try	
		{
			//Atualiza o horário de logout
			oRelacionamento.registrarLogoutSessao();

			// seta mensagem de retorno - header	
			statusCode.setCod(STR_11IOK);
			statusCode.setMsg(STR_11IOKMSG);
		}

		catch(TuxBasicSvcException)
		{
					
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg(STR_11WNOREG);
		}
		catch(TuxBasicOraException &e)
		{
			// removendo warning
			e.eCode = 0;

			statusCode.setCod(STR_11W0001);
			statusCode.setMsg(STR_11WNOREG);
		}
		catch(...)
		{
			statusCode.setCod(STR_11W9999);
			statusCode.setMsg(STR_11WEXCDESC);
		}
	}

	// Monta o XML de saída
	xml_g->createTag( "INCREGSESSAOVO" );
	xml_g->addProp( "xmlns", "acessos.vol.vivo.com.br/vo" );
	xml_g->closeTag();
}

static void registrarIP(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CRelacionamento oRelacionamento;

	char*   pcTagXmlIn = NULL;
	long	lIdTempoSessao = 0;
	char	cNrIP[256]="";
	
	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"idTempoSessao", 0);
	ASSERT_LONG(lIdTempoSessao, pcTagXmlIn, "idTempoSessao");
	//
	pcTagXmlIn = helper.walkTree(dnode,"nrIP", 0);
	ASSERT_STR(pcTagXmlIn, "nrIP");
	strcpy(cNrIP, pcTagXmlIn);


	if (lIdTempoSessao <= 0){
		statusCode.setCod(STR_11W0002);
		statusCode.setMsg(STR_11WTAGINV);
	}
	else
	{
		oRelacionamento.setIdTempoSessao(lIdTempoSessao);
		oRelacionamento.setNrIP(cNrIP);

		try	
		{
			//Atualiza o IP
			oRelacionamento.registrarIP();

			// seta mensagem de retorno - header	
			statusCode.setCod(STR_11IOK);
			statusCode.setMsg(STR_11IOKMSGRgIP);
		}

		catch(TuxBasicSvcException)
		{
					
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg(STR_11WNOREG);
		}
		catch(TuxBasicOraException &e)
		{
			// removendo warning
			e.eCode = 0;

			statusCode.setCod(STR_11W0001);
			statusCode.setMsg(STR_11WNOREG);
		}
		catch(...)
		{
			statusCode.setCod(STR_11W9999);
			statusCode.setMsg(STR_11WEXCDESC);
		}
	}

	// Monta o XML de saída
	xml_g->createTag( "INCREGSESSAORGIPVO" );
	xml_g->addProp( "xmlns", "acessos.vol.vivo.com.br/vo" );
	xml_g->closeTag();
}

static void consultarIP(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{
	CTuxHelperClever helper;

	CRelacionamento oRelacionamento;

	char*   pcTagXmlIn = NULL;
	long	lIdTempoSessao = 0;
	
	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"idTempoSessao", 0);
	ASSERT_LONG(lIdTempoSessao, pcTagXmlIn, "idTempoSessao");


	if (lIdTempoSessao <= 0){
		statusCode.setCod(STR_11W0002);
		statusCode.setMsg(STR_11WTAGINV);
	}
	else
	{
		oRelacionamento.setIdTempoSessao(lIdTempoSessao);

		try	
		{
			//Atualiza o IP
			oRelacionamento.consultarIP();

			// seta mensagem de retorno - header	
			statusCode.setCod(STR_11IOK);
			statusCode.setMsg(STR_11IOKMSGCsIP);
		}

		catch(TuxBasicSvcException)
		{
					
			// seta mensagem de retorno - header
			statusCode.setCod(STR_11W0001);
			statusCode.setMsg(STR_11WNOREGIP);
		}
		catch(TuxBasicOraException &e)
		{
			// removendo warning
			e.eCode = 0;

			statusCode.setCod(STR_11W0001);
			statusCode.setMsg(STR_11WNOREGIP);
		}
		catch(...)
		{
			statusCode.setCod(STR_11W9999);
			statusCode.setMsg(STR_11WEXCDESC);
		}
	}

	// Monta o XML de saída
	xml_g->createTag( "INCREGSESSAOCSIPVO" );
	xml_g->addProp( "xmlns", "acessos.vol.vivo.com.br/vo" );
	xml_g->addItem( "nrIP", CUtil::trim(oRelacionamento.getNrIP()) );
	xml_g->closeTag();
}

void implINCREGSESSAO::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

	CStatusCode	  statusCode;
	char*         pcTagXmlIn = NULL;

	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"operacao", 0);
	ASSERT_STR(pcTagXmlIn, "operacao");

	// descobrindo qual operação executar
	if (!strcmp(pcTagXmlIn, STR_REGISTRARLOGOUT))
		registrarLogout(dnode, xml_g, statusCode);		
	else if (!strcmp(pcTagXmlIn, STR_REGISTRARIP))
		registrarIP(dnode, xml_g, statusCode);		
	else if (!strcmp(pcTagXmlIn, STR_CONSULTARIP))
		consultarIP(dnode, xml_g, statusCode);		

	

	// seta mensagem de retorno - header
   setStatusCode(statusCode.getCod(), statusCode.getMsg());

}
