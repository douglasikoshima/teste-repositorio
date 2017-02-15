#include "../include/WF_RelAudProc.h"
#include "../../../commons/msgPadrao.h"

DECLARE_TUXEDO_SERVICE(RELAUDPROC);

void implRELAUDPROC::Execute( DOMNode*dnode, XMLGen*xml_g )
{
    ULOG_START("implRELAUDPROC::Executar()");
    WF_RelAudProc rap(dnode, xml_g);
	int resultado = rap.Executar();

    switch(resultado)
	{
	case 0:
		setStatusCode("00I0000","Operação concluida");
		break;
	case -1:
		setStatusCode("00E9000","FILE EXCEPTION ERROR");
		break;
	case -2:
		setStatusCode("00E9000","INFORMED DATA ERROR");
		break;
	case -3:
		setStatusCode("00E9000","DATABASE ERROR");
		break;
	case -4:
		setStatusCode("00I0000","Operação concluida");
		//setStatusCode("00E9000","RECORDS EXCEPTION");
		break;		
	default:
		setStatusCode("00E9000","Falha na execução");
		break;
	}  
	ULOG_END("implRELAUDPROC::Executar()");
}