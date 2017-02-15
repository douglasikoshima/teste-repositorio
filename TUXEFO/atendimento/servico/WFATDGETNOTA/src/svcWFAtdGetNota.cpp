#include "tuxfw.h"
#include "../include/AtendimentoConsultor.h"
#include "../include/UtilWorkflow.h"

DECLARE_TUXEDO_SERVICE(ATDGETNOTA);

void implATDGETNOTA::Execute( DOMNode*dnode,XMLGen*xml_g )
{
   ULOG_START("implATDGETNOTA::Execute()");
   
	AtendimentoConsultor obj;
	stPesquisaNotas buf;
	char *idAtendimentoNota = walkTree(dnode,"idAtendimentoNota",0);
	int retorno = 0;
	memset(&buf,0,sizeof(stPesquisaNotas));	
	UtilWorkflow::strnullcpy(buf.idAtendimentoNota,idAtendimentoNota);
	// chamada a execução da pesquisa
	xml_g->createTag("WFAtdNotasVO");
	xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
	xml_g->createTag("WFAtdNotaVO");
	try
	{
		retorno = obj.getDadosNota(&buf,xml_g);
	}catch (TuxBasicOraException ex) {
		ULOGE("sqlca.sqlcode %d",ex.eCode);
		retorno = ex.eCode;		
	}	
	xml_g->closeTag();
	xml_g->closeTag();
	XMLString::release(&idAtendimentoNota);
	switch(retorno)
	{
		case -1:
			setStatusCode("00W0002","Registro não encontrado");
			break;
		case 1:
			setStatusCode("00I0000","Sucesso");
			break;
		default:
			setStatusCode("00W0001","Erro desconhecido");
			break;
	}
	
	ULOG_END("implATDGETNOTA::Execute()");
}