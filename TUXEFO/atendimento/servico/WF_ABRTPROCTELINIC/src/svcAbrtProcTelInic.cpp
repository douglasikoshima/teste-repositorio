
#include "../include/cAbrtProcTelInic.h"
#include "../../ChamadaTelefonica/include/cWFChamadaTelefonica.h"

#include "../../../commons/msgPadrao.h"
#include "../../../commons/routerLib/include/RouterClient.h"


DECLARE_TUXEDO_SERVICE(ABRTPRCTELINI);

void implABRTPRCTELINI::Execute( DOMNode *dnode, XMLGen *xml_g )
{
    ULOG_START("implABRTPRCTELINI::Execute()");
    cWFChamadaTelefonica   objChamTel( dnode,xml_g );
    
    objChamTel.incluir();

    setStatusCode("07I0000","Inclusão de Chamada efetuada com Sucesso");
    ULOG_END("implABRTPRCTELINI::Execute()");

}
