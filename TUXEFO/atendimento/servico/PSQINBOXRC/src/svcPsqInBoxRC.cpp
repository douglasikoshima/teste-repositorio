/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:36 $
 **/

#include <tuxfw.h>
#include "../../Atendimento/include/cWFAtendimento.h"

bool proCConsultaGruposUsuario(int* _idPessoaUsuario, XMLGen* saida);

DECLARE_TUXEDO_SERVICE(PSQINBOXRC)

void implPSQINBOXRC::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implPSQINBOXRC::Execute()");


	cWFAtendimento atd(dnode, xml_g);
    char *user = getUser();
	int idGrupo = 0;

    if ( !user )
    {
	    setStatusCode("04E0000","Tag 'user' obrigat�ria e n�o fornecida");
        return;
    }

	int idPessoaUsuario = atoi( user );

	xml_g->createTag("RWFAtendimentosVO");
		xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");

		atd.consultarBoxRC(idPessoaUsuario);

	xml_g->closeTag();

	setStatusCode("04I0000","Consulta Conclu�da.");

	ULOG_END("implPSQINBOXRC::Execute()");
}
