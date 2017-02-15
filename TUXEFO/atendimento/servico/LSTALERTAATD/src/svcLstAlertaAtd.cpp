/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:37 $
 **/

#include <tuxfw.h>
#include "../../Atendimento/include/cWFAtendimento.h"

DECLARE_TUXEDO_SERVICE(LSTALERTAATD)

void implLSTALERTAATD::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implLSTALERTAATD::Execute()");
    
    char *p = walkTree( dnode, "idAtendimento", 0 );
	long idAtendimento = 0;

    if ( p )
    {
	    idAtendimento = atol(p);
        XMLString::release(&p);
    }

    p = walkTree( dnode, "isSimplificado", 0 );
	int isSimplificado = 0;

    if ( p )
    {
	    isSimplificado = atoi(p);
        XMLString::release(&p);
    }

	xml_g->createTag("AtendimentoVO");
	xml_g->addProp("xmlns","workflow.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:ns2","admsistemas.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:ns3","usuario.fo.vivo.com.br/vo");

	cWFAtendimento atd;

	atd.consultarAlerta(&idAtendimento, isSimplificado, xml_g);

	xml_g->closeTag();

	setStatusCode("04I0000","Consulta Concluída.");

    ULOG_END("implLSTALERTAATD::Execute()");
}
