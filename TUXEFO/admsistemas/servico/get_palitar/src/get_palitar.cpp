#define GetPalitarCPP

#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CSistemaOrigem.h"
#include "../../../negocio/admatdCmm/include/CProcedencia.h"
#include "../../../negocio/admatdCmm/include/CCfgPalitagem.h"


//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(GETPALITAR);

void implGETPALITAR::Execute( DOMNode * dnode,XMLGen * xml_g )
{
    ULOG_START("implGETPALITAR::Execute()");
	CSafePointer oSafePointer;
    CSistemaOrigem oSistemaOrigem;
    CProced oProcedencia;
    CCfgPalitagem oCfgPalitagem;
    
    //char* cidQuestionario = oSafePointer.getTag( dnode, "idQuestionario", 0 );
    // if( strlennull( cidQuestionario ) <= 0 )
    // {
        // setStatusCode("14E0001","idQuestionario está nulo");
        // ULOG_END("implGETPALITAR::Execute()");
        // return;
    // }
    char* sidContato = oSafePointer.getTag(dnode,"idContato",0);
    if( strlennull( sidContato ) <= 0 )
    {
        setStatusCode("14E0000","sidContato esta nulo");
        ULOG_END("implGETPALITAR::Execute()");
        return;
    }
    xml_g->createTag("AdmContatoPalitagemVO");
    xml_g->addProp("xmlns", "admsistemas.fo.vivo.com.br/vo" );
        oCfgPalitagem.ListAll( sidContato );
        oCfgPalitagem.GetXml( sidContato, xml_g );
        xml_g->createTag("Listas");
            oSistemaOrigem.ListAll();
            oSistemaOrigem.GetXml( xml_g );
            oProcedencia.ListAll();
            oProcedencia.GetXml( xml_g );
        xml_g->closeTag();
    xml_g->closeTag();
    setStatusCode("14I0000","Operacao concluida com sucesso!");
    ULOG_END("implGETPALITAR::Execute()");
}
