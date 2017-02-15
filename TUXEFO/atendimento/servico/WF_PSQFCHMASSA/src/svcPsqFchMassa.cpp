
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../../../commons/SmallString.h"

#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"


DECLARE_TUXEDO_SERVICE(PSQFCHMASSA);

void implPSQFCHMASSA::Execute( DOMNode *dnode, XMLGen *xml_g )
{
    ULOG_START("implPSQFCHMASSA::Executar()");
    
    xml_g->createTag( "AtendimentoInformacaoVO" );
        xml_g->addProp( "xmlns","workflow.fo.vivo.com.br/vo" );
        xml_g->addProp( "xmlns:ns1","workflow.fo.vivo.com.br/vo" );
        xml_g->addProp( "xmlns:ns2","admsistemas.fo.vivo.com.br/vo" );
        xml_g->addProp( "xmlns:ns3","usuario.fo.vivo.com.br/vo" );
        cWFAtendimento obj( dnode,xml_g );
        obj.consultarMassa();
    xml_g->closeTag();

    setStatusCode( "07I0000","Pesquisa Fechamento em Massa Efetuada com Sucesso" );
    
    ULOG_END("implPSQFCHMASSA::Executar()");

}
