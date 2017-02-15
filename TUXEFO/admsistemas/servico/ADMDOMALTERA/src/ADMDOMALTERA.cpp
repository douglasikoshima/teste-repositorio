#define ADMDOMALTERACPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CDomTbl.h"

DECLARE_TUXEDO_SERVICE(ADMDOMALTERA);

void implADMDOMALTERA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMDOMALTERA::Execute()");
	CSafePointer oSafePointer;
	CTabelaDominio oCDominio;
	char *cidTabelaDominio = NULL;
	char *cnmTabelaDominio = NULL;
	char *cidUser = NULL;
	cidTabelaDominio = oSafePointer.getTag( dnode, "idTabelaDominio", 0 );
	cnmTabelaDominio = oSafePointer.getTag( dnode, "nmTabelaDominio", 0 );
	cidUser = oSafePointer.getTag( dnode, "user", 0 );
	xml_g->createTag("AdmTabelaDominiosVO");
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );
	if(cidTabelaDominio != NULL && cnmTabelaDominio != NULL)
	{
		if(oCDominio.Update(cidTabelaDominio,cnmTabelaDominio,cidUser)  ==1)
		{			
			oCDominio.GetXml("AdmTabelaDominioVO",xml_g); 						
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
	
	ULOG_END("implADMDOMALTERA::Execute()");
}
