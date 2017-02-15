#include <stdio.h>
#include <tuxfw.h>
#define   COD_ERR_EXCEPT "13E0000"
#include "../../negocio/CadInSid/include/CadInSid/CadInSid.hpp"
#include "../../negocio/TuxHelperClever/include/TuxHelperClever/TuxHelperClever.h"
#include "../../negocio/UtilCanais/include/UtilCanais/UtilCanais.hpp"


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



DECLARE_TUXEDO_SERVICE(CADASTRAINSID);



static void consultarRegional(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	CCadInSid		 oCadInSid; 
	char		*pcTagXmlIn = NULL;
	
	list< CCadInSid >	lstCadInSid;
		

	try
	{		
		oCadInSid.consultarRegional(lstCadInSid);

		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKMSG);
		
	}
	catch( ... )
	{	
		
		statusCode.setCod(STR_13W0005);				
		statusCode.setMsg("Erro ao consultar regional");
			
	}		

	xml_g->createTag("VOLTAVListaRegionalVO");

	xml_g->addProp("xmlns", "voltav.fo.vivo.com.br/vo");

	while( 0 < lstCadInSid.size() ) 
	{	
		oCadInSid = lstCadInSid.front();

		xml_g->createTag("VOLTAVRegionalVO");

			xml_g->addItem("idOperadora", oCadInSid.getIdGrupoOperadora());
			
			xml_g->addItem("dsOperadora", oCadInSid.getNmGrupoOperadora());			

		xml_g->closeTag();

		lstCadInSid.pop_front();			
	}


	xml_g->closeTag();

}

static void consultarFuncionalidade(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	CCadInSid		 oCadInSid;  
	char		*pcTagXmlIn = NULL;
	list< CCadInSid >	lstCadInSid;

	
	try
	{		
		oCadInSid.consultarFuncionalidade(lstCadInSid);

		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKMSG);
		
	}
	catch( ... )
	{	
		
		statusCode.setCod(STR_13W0005);				
		statusCode.setMsg("Erro ao consultar funcionalidade");
			
	}		


	xml_g->createTag("ListaFuncionalidadeInSidVO");
	

	xml_g->addProp("xmlns", "voltav.fo.vivo.com.br/vo");

	
	while( 0 < lstCadInSid.size() ) 
	{
	
		oCadInSid = lstCadInSid.front();		
				
		xml_g->createTag("FuncionalidadeInSidVO");
				
			xml_g->addItem("idApi", oCadInSid.getIdApi());
			
			xml_g->addItem("nmFuncionalidade", oCadInSid.getNmFuncionalidade());

		xml_g->closeTag();

		lstCadInSid.pop_front();		

	}
	
	xml_g->closeTag();

}


static void consultarPesquisa(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	CCadInSid		 oCadInSid;  
	char*			pcTagXmlIn  = NULL;
	int				iIdCanal = 0;
	int				iIdGrupoOperadora = 0;
	int				iIdApi = 0;
	list< CCadInSid >	lstCadInSid;


	pcTagXmlIn = helper.walkTree(dnode,"idGrupoOperadora", 0);
	
	if (pcTagXmlIn != NULL)
		iIdGrupoOperadora = atoi (pcTagXmlIn);

	pcTagXmlIn	= helper.walkTree(dnode, "idApi", 0);

	if (pcTagXmlIn != NULL)
		iIdApi = atoi (pcTagXmlIn);

	pcTagXmlIn	= helper.walkTree(dnode, "idCanal", 0);

	if (pcTagXmlIn != NULL)	
		iIdCanal = atoi(pcTagXmlIn);

	try
	{		
		oCadInSid.consultarPesquisa(lstCadInSid, iIdGrupoOperadora, iIdApi,  iIdCanal);

		statusCode.setCod(STR_13IOK);
		statusCode.setMsg(STR_13IOKMSG);
		
	}
	catch( ... )
	{	
		
		statusCode.setCod(STR_13W0005);				
		statusCode.setMsg("Erro ao consultar Pesquisa");
			
	}		
	
	xml_g->createTag("ListaPesquisaInSidVO");	

	xml_g->addProp("xmlns", "voltav.fo.vivo.com.br/vo");
	
	
	while( 0 < lstCadInSid.size() ) 
	{
	
		oCadInSid = lstCadInSid.front();
	
  		xml_g->createTag("PesquisaInSidVO");				
				
				xml_g->addItem("idApi", oCadInSid.getIdApi());						

				xml_g->addItem("nmFuncionalidade", oCadInSid.getNmFuncionalidade());

				xml_g->addItem("idGrupoOperadora", oCadInSid.getIdGrupoOperadora());

				xml_g->addItem("nmGrupoOperadora", oCadInSid.getNmGrupoOperadora());

				xml_g->addItem("idCanal", oCadInSid.getIdCanal());

				xml_g->addItem("nmCanal", oCadInSid.getNmCanal());

				xml_g->addItem("nrInSid", oCadInSid.getNrInSid());

			xml_g->closeTag();

		lstCadInSid.pop_front();	
	

	}
	


}

static void alterarInSid(DOMNode* dnode, XMLGen* xml_g, CStatusCode &statusCode)
{

	CTuxHelperClever helper;
	CCadInSid		 oCadInSid;  
	
	char*			pcTagXmlIn  = NULL;
	int				iIdGrupoOperadora = 0;
	int				iIdApi = 0;
	int				iIdCanal = -1;
	int				iNrInSid = 0;
	int				i = 0;

	while ( iIdCanal != 0 )
	{	
	 	iIdCanal = 0;

		pcTagXmlIn  = helper.walkTree(dnode,"idGrupoOperadora", i);

		if (pcTagXmlIn != NULL)
			iIdGrupoOperadora = atoi (pcTagXmlIn);

		pcTagXmlIn	= helper.walkTree(dnode, "idApi", i);

		if (pcTagXmlIn != NULL)
			iIdApi = atoi (pcTagXmlIn);

		pcTagXmlIn	= helper.walkTree(dnode, "idCanal", i);

		if (pcTagXmlIn != NULL)	
			iIdCanal = atoi(pcTagXmlIn);

		pcTagXmlIn	= helper.walkTree(dnode, "nrInSid", i);

		if (pcTagXmlIn != NULL)	
			iNrInSid = atoi(pcTagXmlIn);

		if ( iIdCanal == 0 )
			break;

		try{
			oCadInSid.alterarInSid(iIdGrupoOperadora, iIdApi,  iIdCanal, iNrInSid);			
		}

		catch( ... )
		{	
		
			statusCode.setCod(STR_13W0005);				
			statusCode.setMsg("Erro ao alterar InSid");
			return;
			
		}		

		i++;

	}

		statusCode.setCod(STR_13IOK);
		statusCode.setMsg("Alteração efetuada com sucesso");


}


void implCADASTRAINSID::Execute(DOMNode* dnode, XMLGen* xml_g) 
{
	CTuxHelperClever helper;

	CStatusCode	  statusCode;
	char*         pcTagXmlIn = NULL;


	// Navega o XML e recupera as informacoes obrigatorias
	pcTagXmlIn = helper.walkTree(dnode,"operacao", 0);
	ASSERT_STR(pcTagXmlIn, "operacao");

	// descobrindo qual operação executar
	
	if (!strcmp(pcTagXmlIn, "consultarRegional"))		
		consultarRegional(dnode, xml_g,statusCode);

	else if (!strcmp(pcTagXmlIn, "consultarFuncionalidade"))
		consultarFuncionalidade(dnode, xml_g,statusCode);	

	else if (!strcmp(pcTagXmlIn, "consultarPesquisaInSid"))
		consultarPesquisa( dnode, xml_g, statusCode);

	else if (!strcmp(pcTagXmlIn, "alterarInSid"))
		alterarInSid( dnode, xml_g, statusCode);


	// seta mensagem de retorno - header
    setStatusCode(statusCode.getCod(), statusCode.getMsg());

}

