/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:37 $
 **/

#include <tuxfw.h>
#include "../../AtdMensagemCri/include/cWFAtdMsgCri.h"

////////////////////////////////////////////////////////////////////////
//
// Este serviço esta sendo usado para exibir mensagens no inbox do BKO
// não importando a origem da mensagem, seja ela de CRI ou de C.Prévio.
// Devido a isto o serviço de pesquisa mensagens de C.Prévio não esta
// mais sendo usado.
//
// Set/2005

DECLARE_TUXEDO_SERVICE(PSQMSGCRI)

void implPSQMSGCRI::Execute( DOMNode * dnode, XMLGen * xml_g )
{
	ULOG_START("implPSQMSGCRI::Executar()");

    char * user = getUser();

	if ( !user )
	{
	    setStatusCode("04E0000","Tag 'user' obrigatória e não fornecida");
	    return;
	}

	char * p;
    cWFAtdMsgCri atd(dnode, xml_g);
	long idAtendimento = 0;
	
	int idPessoaUsuario = atoi( user );

    if ( p=walkTree( dnode, "idAtendimento", 0 ),p )
    {
        idAtendimento = atol(p);
        XMLString::release(&p);
    }

	xml_g->createTag("RWFAtendimentosVO");
	xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");

	atd.consultarMensagems( idPessoaUsuario,idAtendimento );
	
	xml_g->closeTag();

	setStatusCode("04I0000","Consulta Concluída.");
	
	ULOG_END("implPSQMSGCRI::Executar()");

}
