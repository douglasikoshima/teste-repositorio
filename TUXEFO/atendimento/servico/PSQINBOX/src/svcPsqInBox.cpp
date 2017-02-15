/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:47 $
 **/

#include <tuxfw.h>
#include "../../Atendimento/include/cWFAtendimento.h"

bool proCConsultaGruposUsuario(int* _idPessoaUsuario, XMLGen* saida);

DECLARE_TUXEDO_SERVICE(PSQINBOX)

void implPSQINBOX::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implPSQINBOX::Execute()");

	cWFAtendimento atd(dnode, xml_g);
    char *user = getUser();
	int idGrupo = 0;

    if ( !user )
    {
	    setStatusCode("04E0000","Tag 'user' obrigatória e não fornecida");
	    ULOG_END("implPSQINBOX::Execute()");
        return;
    }

	int idPessoaUsuario = atoi( user );

	xml_g->createTag("RWFAtendimentosVO");
		xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");

		atd.consultarBox(idPessoaUsuario);

	xml_g->closeTag();

	setStatusCode("04I0000","Consulta Concluída.");

	ULOG_END("implPSQINBOX::Execute()");
}
