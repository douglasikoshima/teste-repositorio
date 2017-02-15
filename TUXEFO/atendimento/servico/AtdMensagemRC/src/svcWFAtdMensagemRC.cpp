/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:40 $
 **/


#include "../../../commons/routerLib/include/RouterClient.h"
#include "../../AtdMensagemRC/include/cWFAtdMsgRC.h"

//DECLARE_TUXEDO_SERVICE(WFATDMSGRC);
DECLARE_TUXEDO_SERVICE(PSQMSGRC);

////////////////////////////////////////////////////////////////////////
// SERVIÇO DESATIVADO (continua publicado).USE O PSQMSGCRI
// Set/2005 - Cassio

void implPSQMSGRC::Execute(DOMNode*dnode,XMLGen*xml_g)
{

    cWFAtdMsgRC atd(dnode, xml_g);

    char *user = getUser();

    if ( !user )
    {
        setStatusCode("04E0000","Tag 'user' obrigatória e não fornecida");
        return;
    }

    int idPessoaUsuario = atoi( user );

    xml_g->createTag("RWFAtendimentosVO");
        xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");


    atd.consultarMensagems(idPessoaUsuario,atd.obterIdAtendimento());
    
    xml_g->closeTag();

    setStatusCode("04I0000","Processo PSQMSGRC concluído.");
}
