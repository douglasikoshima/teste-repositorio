
#include "../../DocumentoTecnico/include/cWFDocumentoTecnico.h"

#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"


DECLARE_TUXEDO_SERVICE(LSTDOCTECATD);

void implLSTDOCTECATD::Execute( DOMNode *dnode, XMLGen *xml_g )
{
    ULOG_START("implLSTDOCTECATD::Execute()");
    
    xml_g->createTag( "AtendimentoWorkflowVO" );
        xml_g->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
        xml_g->addProp( "xmlns:ns2","admsistemas.fo.vivo.com.br/vo" );
        xml_g->createTag( "AtendimentoWorkflowTecnicoVO" );
            cWFDocumentoTecnico obj( dnode,xml_g );
            obj.consultarAtendimento();
        xml_g->closeTag();
    xml_g->closeTag();

    setStatusCode( "07I0000","Atendimento Pesquisa Relacionamento Efetuado com Sucesso" );
    
    ULOG_END("implLSTDOCTECATD::Execute()");

}
