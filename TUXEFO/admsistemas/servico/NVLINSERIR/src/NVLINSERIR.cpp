#define NVLINSERIRCPP

#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CNvl.h"
#include "../../../negocio/admatdCmm/include/CNH.h"

DECLARE_TUXEDO_SERVICE(NVLINSERIR);

void implNVLINSERIR::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implNVLINSERIR::Execute()");
	CSafePointer oSafePointer;
	CNivelHierarquia oNivelHierarquia;
	CNivel oNivel;
	
	char* cidNivelPai = oSafePointer.getTag(dnode,"idNivelPai",0);
	char* cidNivel = oSafePointer.getTag(dnode,"idNivel",0);
	char* cdsNivel = oSafePointer.getTag(dnode,"dsNivel",0);

	if( strlennull( cidNivelPai ) <= 0 )
	{
		setStatusCode("14E0000","idNivelPai est� nulo");
		ULOG_END("implNVLINSERIR::Execute()");
		return;
	}

	if( strlennull( cdsNivel ) <= 0 )
	{
		setStatusCode("14E0000","dsNivel est� nulo");
		ULOG_END("implNVLINSERIR::Execute()");
		return;
	}

	char* cidUsuarioAlteracao = getUser();

	switch( oNivel.Insert( cdsNivel, cidUsuarioAlteracao) )
	{
		case -1:
			setStatusCode("14W0000","J� existe esta descri��o ou registro, tente novamente com outra diferente.");
		break;
		
		case 0:
			setStatusCode("14E0000","Erro durante a execu��o do servi�o.");
		break;

		default: 
			{	
				cidNivel = oNivel.Registro(0)->cidNivel;
				if( oNivelHierarquia.Insert( cidNivel, cidNivelPai, cidUsuarioAlteracao ) )
					setStatusCode("14I0000","Sucesso na execu��o!");
				else
					setStatusCode("14E0000","Erro durante a execu��o do servi�o.");
			}
		break;
		
	}
	ULOG_END("implNVLINSERIR::Execute()");
}
