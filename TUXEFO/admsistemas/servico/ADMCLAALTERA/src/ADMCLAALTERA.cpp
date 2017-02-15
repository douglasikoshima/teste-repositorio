#define ADMCLAALTERACPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCmpCls.h"

DECLARE_TUXEDO_SERVICE(ADMCLAALTERA);

void implADMCLAALTERA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMCLAALTERA::Execute()");
   
	CSafePointer oSafePointer;
	CCampoClassificador oCCampoClassificador;
	char* cidClassificadorCampo = NULL;
	char* cnmClassificadorCampo = NULL;
	char* cidUser = NULL;
	cidClassificadorCampo = oSafePointer.getTag( dnode, "idClassificadorCampo", 0 );
	cnmClassificadorCampo = oSafePointer.getTag( dnode, "nmClassificadorCampo", 0 );
	cidUser = oSafePointer.getTag( dnode, "user", 0 );
	xml_g->createTag("AdmClassificadorCamposVO");
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );
	if(cidClassificadorCampo != NULL && cnmClassificadorCampo != NULL)
	{
		if(oCCampoClassificador.Update(cidClassificadorCampo,cnmClassificadorCampo,cidUser) == 1)
		{			
			oCCampoClassificador.GetXml("AdmClassificadorCampoVO",xml_g); 						
			setStatusCode( "00I0000", "Sucesso" );
		}
		else
		{
			setStatusCode( "00W0001", "Não foi possível atualizar dado(s)" );
		}
	}
	else
	{
		setStatusCode( "00W0002", "Parâmetro(s) de entrada inválido(s)" );
	}
	xml_g->closeTag();
	
	ULOG_END("implADMCLAALTERA::Execute()");
}
