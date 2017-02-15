#include "../include/svcmain.h"

DECLARE_TUXEDO_SERVICE(TuxConsAllGrp);

void implTuxConsAllGrp::Execute(DOMNode*XMLIn,XMLGen*XMLOut)
{
   ULOG_START("implTuxConsAllGrp::Execute()");  
   
	if( !Grupo_SelecionarTodos( XMLIn,XMLOut ) )
		throw new TuxBasicSvcException("00E0001","=> CONSULTA: TODOS GRUPOS");


	setStatusCode("00I0000","Success Execution");
	
	ULOG_END("implTuxConsAllGrp::Execute()");  
}

