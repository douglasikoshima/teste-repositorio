
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../../commons/SmallString.h"

#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"


DECLARE_TUXEDO_SERVICE(ATDPSQREL);

void implATDPSQREL::Execute( DOMNode *dnode, XMLGen *xml_g )
{

    ULOG_START("implATDPSQREL::Execute()");
    
    xml_g->createTag( "AtendimentoRelacionamentosVO" );
        xml_g->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
        xml_g->addProp( "xmlns:ns1","workflow.fo.vivo.com.br/vo" );
        xml_g->addProp( "xmlns:ns2","admsistemas.fo.vivo.com.br/vo" );
        xml_g->addProp( "xmlns:ns3","usuario.fo.vivo.com.br/vo" );
        cWFAtendimento obj( dnode,xml_g );
        obj.consultarRelacionamento();
    xml_g->closeTag();

    setStatusCode( "07I0000","Atendimento Pesquisa Relacionamento Efetuado com Sucesso" );
    
    ULOG_END("implATDPSQREL::Execute()");

}
