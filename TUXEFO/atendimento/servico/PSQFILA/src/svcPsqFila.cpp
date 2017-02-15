 /**
 * @modulo  Atendimento
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/10/26 23:32:55 $
 **/

#include <tuxfw.h>
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../include/cPsqFilaPC.h"
#include"../../../commons/Collection/include/Collection.h"

extern bool proCConsultaWFGruposFilaProcesso( int* _idPessoaUsuario, Collection* _grupos );

DECLARE_TUXEDO_SERVICE(PSQFILA)

void implPSQFILA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implPSQFILA::Execute()");

	cWFAtendimento atd(dnode, xml_g);

	int idGrupo = 0;
	int idPessoaUsuario = atoi( getUser() );

    char *inPortout = walkTree( dnode, "inPortout", 0 );
    char *inMeuCliente = walkTree( dnode, "inMeuCliente", 0 );
    char *inMassa = walkTree( dnode, "inMassa", 0 );

	int idUsuario = atoi( getUser() );

	xml_g->createTag("AtendimentoInformacaoVO");
		xml_g->addProp("xmlns",    "workflow.fo.vivo.com.br/vo");
		xml_g->addProp("xmlns:ns2","admsistemas.fo.vivo.com.br/vo");
		xml_g->addProp("xmlns:ns3","usuario.fo.vivo.com.br/vo");

    if ( inPortout )
    {
		atd.consultarFilaPortOut( /*grupos*/0, idUsuario );
        XMLString::release(&inPortout);
    }
    else if ( inMeuCliente )
    {
		atd.consultarFilaMeuCliente( /*grupos*/0, idUsuario );
        XMLString::release(&inMeuCliente);
    }
	else if ( inMassa )
    {
		atd.consultarMassa();
        XMLString::release(&inMassa);
    }
	else
	{
		atd.consultarFila( /*grupos*/0, idUsuario );
	}

	xml_g->closeTag();

	setStatusCode("04I0000","Consulta Concluída.");

	ULOG_END("implPSQFILA::Execute()");
}
