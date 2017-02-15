#include "tuxfw.h"
#include "../include/svcWFAtdGetConsul.h"
#include "../include/AtendimentoConsultor.h"

DECLARE_TUXEDO_SERVICE(ATDGETCONSUL);

void implATDGETCONSUL::Execute( DOMNode*dnode,XMLGen*xml_g )
{
   ULOG_START("implATDGETCONSUL::Execute()");
   
	char *numTelefone;
	char *idAtendimento;
	char *cdDDD;
	char *tipoConsulta;
	int retorno = 0;
	struct stData buf;

	AtendimentoConsultor obj;

	numTelefone = walkTree(dnode,"cdNumTelefone",0);
	idAtendimento = walkTree(dnode,"idAtendimento",0);
	cdDDD = walkTree(dnode,"cdDDD",0);
	tipoConsulta = walkTree(dnode,"tipoConsulta",0);
	memset(&buf,0,sizeof(stData));

	if(tipoConsulta == NULL || !strcmp(tipoConsulta,""))
	{
		retorno = MSG_CODE_PARAMETROS_INVALIDOS;
	}

	try
	{
		if(!strcmp(tipoConsulta,"1"))
		{
			strcpy(buf.nrLinha,(numTelefone!=NULL)?numTelefone:"");
			strcpy(buf.cdAreaRegistro,(cdDDD!=NULL)?cdDDD:"");
			retorno = obj.getConsultorByLinha(&buf);			
		}
		else if(!strcmp(tipoConsulta,"2"))
		{
			strcpy(buf.idAtendimento,(idAtendimento!=NULL)?idAtendimento:"");
			retorno = obj.getConsultorByProcesso(&buf);
			ULOG("nmLoginUsuarioCTI: %s",buf.nmLoginUsuarioCTI);
			ULOG("cdAreaRegistro: %s",buf.cdAreaRegistro);
			ULOG("nrLinha: %s",buf.nrLinha);
		}
		else
			retorno = MSG_CODE_PARAMETROS_INVALIDOS;
	}
	catch(TuxBasicOraException tboe)
	{
		ULOG("Erro ao criar nota %d",tboe.eCode);
		retorno = MSG_CODE_ERRO_DATABASE;
	}

	// gerar o xml de retorno
	xml_g->createTag("AtendimentoConsultorVO");
	xml_g->addProp("xmlns","tuxProxyBE/vo");
	xml_g->addItem("nmLoginUsuarioCTI",buf.nmLoginUsuarioCTI);
	// por linha
	if(!strcmp(tipoConsulta,"1"))
	{
		xml_g->addItem("idAtendimento",buf.idAtendimento);
	}
	else if(!strcmp(tipoConsulta,"2"))
	{
		xml_g->addItem("nrLinha",buf.nrLinha);
	}	
	xml_g->closeTag();

	XMLString::release(&numTelefone);
	XMLString::release(&idAtendimento);
	XMLString::release(&cdDDD);
	XMLString::release(&tipoConsulta);

	switch(retorno)
	{
		case MSG_CODE_PARAMETROS_INVALIDOS:
			setStatusCode(MSG_PARAMETROS_INVALIDOS,MSG_TEXT_PARAMETROS_INVALIDOS);
		break;
		case MSG_CODE_CONSULTOR_NAO_ENCONTRADO:
			setStatusCode(MSG_CONSULTOR_NAO_ENCONTRADO,MSG_TEXT_CONSULTOR_NAO_ENCONTRADO);
		break;
		case MSG_CODE_ERRO_DATABASE:
			setStatusCode(MSG_ERRO_DATABASE,MSG_TEXT_ERRO_DATABASE);
			break;
		default:
			setStatusCode("00I0000","Sucesso!");
			break;
	}
	
	ULOG_END("implATDGETCONSUL::Execute()");
}