/*---------------------------------------------------------
 * Modulo..: 
 * Servico.: 
 * Data....: Dec-07-2004 - Roberto Borges
 *--------------------------------------------------------*/

#include "CGetHeader.h"

//******************************************************************************************************************
// CGetHeader - Component Implementation
//******************************************************************************************************************


CGetHeader::CGetHeader(SRelatorioCampanha* sRelatorioCampanha, XMLGen* pxml_g)
{

	 idCampanha			= sRelatorioCampanha->pcidCampanha      ;
	 idSubCampanha		= sRelatorioCampanha->pcidSubCampanha	;
	 idCanalCampanha	= sRelatorioCampanha->pcidCanalCampanha	;
	 idGrupo			= sRelatorioCampanha->pcidGrupo			;
	 idRegional			= sRelatorioCampanha->pcidRegional		;
	 dtInicio			= sRelatorioCampanha->pcdtInicio		;
	 dtFim				= sRelatorioCampanha->pcdtFim			;
	 idMotivo			= sRelatorioCampanha->pcidMotivo		;
	 idPergunta			= sRelatorioCampanha->pcidPergunta		;
	 idOperador			= sRelatorioCampanha->pcidOperador		;
	 sgOperador			= sRelatorioCampanha->pcsgOperador		;
	 idAreaRegistro		= sRelatorioCampanha->pcidAreaRegistro	;
	 dtAniversari		= sRelatorioCampanha->pcdtAniversario	;
	 nrLinha			= sRelatorioCampanha->pcnrLinha			;
	 inDistincao		= sRelatorioCampanha->pcidDistincao		;									;
	 xml_g              = pxml_g                                ;
	 idTxUsuario		= sRelatorioCampanha->pcUser; 
}

//------------------------------------------------------------------------------------------------------------------

TString CGetHeader::GetXML(char *Campo, bool tpObrigatorio) 
{
    char* p = walkTree(dnode, Campo, 0); 

	TString ret = (p ? p : "");

	if ( tpObrigatorio && !p )
		XMLException( Campo ); 
	
	XMLString::release(&p);

    return ret; 
}

//------------------------------------------------------------------------------------------------------------------
// XMLException
//------------------------------------------------------------------------------------------------------------------
void CGetHeader::XMLException(char *Campo) 
{
	TString	msg = "ERRO: ** Campo <"; 
			msg += Campo; 
			msg += "> invalido ou nao encontrado **"; 
	throw new TuxBasicSvcException("09E0001",msg.c_str());
}

//------------------------------------------------------------------------------------------------------------------
// SetMessage - Seta a mensagem de retorno
//------------------------------------------------------------------------------------------------------------------

void CGetHeader::SetMessage(char *msg, char *status) 
{
  
}


//------------------------------------------------------------------------------------------------------------------
// Init - Metodo Inicial
//------------------------------------------------------------------------------------------------------------------

bool CGetHeader::Init() 
{

	return true; 
}

//------------------------------------------------------------------------------------------------------------------
// Executar - Virtual 
//------------------------------------------------------------------------------------------------------------------

int CGetHeader::Executar() 
{
	int nTemHeader = 0;
	ULOGI("Init();");
	Init();
	ULOGI("getSysDate();");
	getSysDate(); 
	ULOGI("getData();");
	nTemHeader = getData(); 
	ULOGI("getOperador();");
	try{ getOperador(); } catch(...) {}
	try{ getUsuario(); } catch(...) {}
	ULOGI("getXml();");
	getXml(); 

	return nTemHeader;
}

//------------------------------------------------------------------------------------------------------------------
// Adiciona Header a XML de Saida
//------------------------------------------------------------------------------------------------------------------
void CGetHeader::getXml() 
{
	xml_g->createTag( "RelatorioCampanhaHeaderVO" ); 
	xml_g->addProp("xmlns","campanha.fo.vivo.com.br/vo");

		xml_g->addItem("dtEmissao",				DataEmissao.c_str()		   ); 
		xml_g->addItem("sgCampanha",			Campanha.c_str()		   ); 
		xml_g->addItem("sqVersao",				Versao.c_str()			   ); 
		xml_g->addItem("perfil",				Perfil.c_str()			   ); 
		xml_g->addItem("sgOperador",			Usuario.c_str()			   ); 
		xml_g->addItem("sgUsuario",				Usuario.c_str()			   ); 
		xml_g->addItem("filtroDtInicio",		dtInicio.c_str()		   ); 
		xml_g->addItem("filtroDtFim",			dtFim.c_str()			   ); 
		xml_g->addItem("nmCanal",				Canal.c_str()			   ); 
		xml_g->addItem("subCampanhaDtInicio",	InicioVigencia.c_str()	   ); 
		xml_g->addItem("subCampanhaDtFim",		FimVigencia.c_str()		   ); 
		xml_g->addItem("publicoAlvo",			PublicoAlvo.c_str()		   ); 
		xml_g->addItem("nmSubCampanha",			SubCampanha.c_str()		   ); 
		xml_g->addItem("inDisponibilidade",		StatusSubCamp.c_str()	   ); 
		if( strcmp( sgOperador.c_str(), "-1" ) == 0 )
			xml_g->addItem("nmLoginUsuario",	""                         ); 
		else
			xml_g->addItem("nmLoginUsuario",	sgOperador.c_str()         ); 
		xml_g->addItem("inDistincao",			inDistincao.c_str()		   ); 
		xml_g->addItem("nrMetaDiariaCampanha",	MetaDiaria.c_str()		   ); 
		xml_g->addItem("nmGrupo",				nmGrupo.c_str()			   ); 
		xml_g->addItem("nmRegional",			nmRegional.c_str()		   ); 
		xml_g->addItem("areaRegistro",			cdAreaRegistro.c_str()	   ); 
		//xml_g->addItem("Operadora",				Operadora.c_str()	   ); 
	xml_g->closeTag(); 
}

//------------------------------------------------------------------------------------------------------------------


