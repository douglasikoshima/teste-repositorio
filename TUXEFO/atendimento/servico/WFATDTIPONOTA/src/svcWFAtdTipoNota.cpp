#include "tuxfw.h"
#include "../include/AtendimentoTipoNota.h"
#include "../include/UtilWorkflow.h"

DECLARE_TUXEDO_SERVICE(ATDTIPONOTA);

void implATDTIPONOTA::Execute( DOMNode*dnode,XMLGen*xml_g )
{
   ULOG_START("implATDTIPONOTA::Execute()");
   
	int retorno = 0;
	AtendimentoTipoNota obj;
	xml_g->createTag("WFAtdTipoNotasVO");
	xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
	retorno = obj.listNotas(xml_g);
	xml_g->closeTag();
	if(retorno == 1)
		setStatusCode("00I0000","Sucesso");
	else
		setStatusCode("00I0000","Falha ao listar dados");
		
   ULOG_END("implATDTIPONOTA::Execute()");

}