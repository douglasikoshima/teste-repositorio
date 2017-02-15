/*****************************************************************************
 * Arquivo:   GRPSKLDEL.cpp
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 07/10/2005  C_edmartins           Criacao
 *
 ****************************************************************************/

//Definicao Global
#define GRPSKLDELCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CSafePointer.h"
#include "../../../negocio/acessoCmm/include/CContatoFolhaUsuario.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(GRPSKLDEL);

void implGRPSKLDEL::Execute(DOMNode*dnXmlIn,XMLGen*XmlSaida)
{
	ULOG_START("implGRPSKLDEL::Execute()");
	CSafePointer oSafePointer;
	CContatoFolhaUsuario oContatoFolhaUsuario;

	char* pzcidSkillUsuario;
	
	for(int x=0;;x++)
	{
		pzcidSkillUsuario = oSafePointer.getTag( dnXmlIn, "idSkillUsuario", x );
		if( strlennull( pzcidSkillUsuario ) <= 0 )
			break;

		oContatoFolhaUsuario.Delete( pzcidSkillUsuario );
	}
	setStatusCode("14I0000","Serviço finalizado com sucesso!");
	ULOG_END("implGRPSKLDEL::Execute()");
}