#define ADMCLAINSERICPP
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CCmpCls.h"
#include "../../../negocio/admatdCmm/include/CSafePointer.h"

DECLARE_TUXEDO_SERVICE(ADMCLAINSERI);

void implADMCLAINSERI::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMCLAINSERI::Execute()");
   
	CSafePointer oSafePointer;
	CCampoClassificador oCCampoClassificador;
	char* cnmClassificadorCampo = NULL;
	char* cidUser = NULL;
	cnmClassificadorCampo = oSafePointer.getTag( dnode, "nmClassificadorCampo", 0 );
	cidUser = oSafePointer.getTag( dnode, "user", 0 );
	xml_g->createTag("AdmClassificadorCamposVO");
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );
	if(cnmClassificadorCampo != NULL)
	{
		if(oCCampoClassificador.Insert(cnmClassificadorCampo,cidUser) == 1)
		{
			oCCampoClassificador.GetXml("AdmClassificadorCampoVO",xml_g); 						
			setStatusCode( "00I0000", "Sucesso" );
		}
		else
		{
			setStatusCode( "00W0001", "Não foi possível inserir dado(s)" );
		}
	}
	else
	{
		setStatusCode( "00W0002", "Parâmetro(s) de entrada inválido(s)" );
	}
	xml_g->closeTag();
	
	ULOG_END("implADMCLAINSERI::Execute()");
}
