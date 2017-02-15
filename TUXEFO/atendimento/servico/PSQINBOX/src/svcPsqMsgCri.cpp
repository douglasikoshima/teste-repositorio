/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:37 $
 **/

#include <tuxfw.h>
#include "../../AtdMensagemCri/include/cWFAtdMsgCri.h"

DECLARE_TUXEDO_SERVICE(PSQMSGCRI)

void implPSQMSGCRI::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implPSQMSGCRI::Execute()");

	char * p;
	long    idAtendimento = 0;

	cWFAtdMsgCri atd(dnode, xml_g);
    char * user = getUser();

	if ( !user )
	{
	    setStatusCode("04E0000","Tag 'user' obrigatória e não fornecida");
	    ULOG_END("implPSQMSGCRI::Execute()");
	    return;
	}

	
	int idPessoaUsuario = atoi( user );

   if ( p=tx.walkTree( dnode, "idAtendimento", 0 ),p )
   {
      idAtendimento = atol(p);
      XMLString::release(&p);
   }

	xml_g->createTag("RWFAtendimentosVO");
		xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");

	atd.consultarMensagems( idPessoaUsuario,idAtendimento );
	
	xml_g->closeTag();

	setStatusCode("04I0000","Consulta Concluída.");
	
	ULOG_END("implPSQMSGCRI::Execute()");

}
