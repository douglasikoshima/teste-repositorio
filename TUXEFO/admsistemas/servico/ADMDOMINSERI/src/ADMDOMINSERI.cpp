#define ADMDOMINSERICPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CDomTbl.h"

DECLARE_TUXEDO_SERVICE(ADMDOMINSERI);

void implADMDOMINSERI::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMDOMINSERI::Execute()");
	CSafePointer oSafePointer;
	CTabelaDominio oCDominio;
	char *cnmTabelaDominio = NULL;
	char *cidUser = NULL;
	cnmTabelaDominio = oSafePointer.getTag( dnode, "nmTabelaDominio", 0 );
	cidUser = oSafePointer.getTag( dnode, "user", 0 );
	xml_g->createTag("AdmTabelaDominiosVO");
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );
	if(cnmTabelaDominio != NULL)
	{
		if(oCDominio.Insert(cnmTabelaDominio,cidUser)  ==1)
		{			
			oCDominio.GetXml("AdmTabelaDominioVO",xml_g); 						
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
	
	ULOG_END("implADMDOMINSERI::Execute()");
	
}