/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:17 $
 **/



#include <tuxfw.h>
#include "../../AtdInBoxCri/include/cWFAtdInBox.h"


DECLARE_TUXEDO_SERVICE(PSQPROCCRI)

void implPSQPROCCRI::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implPSQPROCCRI::Executar()");

	//
	// Desvio para atender a incidência 3220
	//
	char *pAux = NULL;
	// Se esta tag for passada, então o XML IN é para consultarBoxAdqCliente
	if( pAux = walkTree(dnode, "Pesquisa", 0) ) 
	{  
		ULOG("Recebeu TAG Pesquisa");
		XMLString::release(&pAux);
		ULOG("Chamando consultarBoxAdqCliente");
		cWFAtdInBox atd;
		atd.consultarBoxAdqCliente(dnode, xml_g);
		setStatusCode("24I0000", "Execução OK");
		ULOG("PRONTO - Chamado o servico de pesquisa inbox CRI");
		return;
	}
	
	// Se não segue fluxo normal
	cWFAtdInBox atd(dnode, xml_g);
    	char *user = getUser();

	if ( !user )
	{
	    setStatusCode("04E0000","Tag 'user' obrigatória e não fornecida");
	    return;
	}

	int idPessoaUsuario = atoi( user );

	xml_g->createTag("RWFAtendimentosVO");
		xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");

	
    if ( atd.m_stFila.inAbaPesquisa == 0 )  //  Usuario Disponivel
    {
       atd.AtualizaProcessos( idPessoaUsuario );
    }
    else // Pesquisa in Box
    {
      if ( atd.m_stFila.inAbaPesquisa > 0 )
      {
         if ( atd.m_stFila.inAbaPesquisa != 6)
         {
			 if (atd.m_stFila.inAbaPesquisa == 3) // Encaminhar
				atd.consultarBoxEnc(idPessoaUsuario);
             else
				atd.consultarBox(idPessoaUsuario);
         }
         else
         {
            atd.consultarBoxAdq(idPessoaUsuario);
         }
      }
    }

    xml_g->closeTag();

    setStatusCode("04I0000","Consulta Concluída.");

    ULOG_END("implPSQPROCCRI::Executar()");
}
