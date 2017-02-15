 /**
 * @modulo  Atendimento
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:48 $
 **/

#include <tuxfw.h>
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../include/cPsqFilaPCRC.h"
#include "../include/cWFAtdInBoxRC.h"
#include "../../../commons/Collection/include/Collection.h"


extern bool proCConsultaWFGruposFilaProcesso( int* _idPessoaUsuario, Collection* _grupos );

DECLARE_TUXEDO_SERVICE(PSQFILARC)

void implPSQFILARC::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implPSQFILARC::Execute()");
   ULOG("novo servico ");   
   
   char *pAux = NULL;
   pAux = walkTree(dnode, "inTipoPesquisa", 0) ;
	/* incidencia 3067 */
   if(strlen(pAux) > 0)
   {
       ULOG("inTipoPesquisa -> [%s]",pAux);
       ULOG("Criando o OBJETO cWFAtdInBoxRC ");
       cWFAtdInBoxRC atd(dnode, xml_g);
       char *user = getUser();
       
       if ( !user )
       {
           setStatusCode("04E0000","Tag 'user' obrigat¾ria e nÒo fornecida");
           ULOG_END("implPSQFILARC::Execute()");
           return;
       }
       
       int idPessoaUsuario = atoi( user );
       
//       xml saida cri        
//       xml_g->createTag("RWFAtendimentosVO");
//       xml_g->addProp("xmlns", "workflow.fo.vivo.com.br/vo");
//       if ( atd.m_stFila.inAbaPesquisa > 0 )
//       {
//          if ( atd.m_stFila.inAbaPesquisa == 6)
//          {
//             atd.consultarBoxAdq(idPessoaUsuario);
//          }
//       }
       
   	 xml_g->createTag("AtendimentoInformacaoVO");
		 xml_g->addProp("xmlns",    "workflow.fo.vivo.com.br/vo");
		 xml_g->addProp("xmlns:ns2","admsistemas.fo.vivo.com.br/vo");
		 xml_g->addProp("xmlns:ns3","usuario.fo.vivo.com.br/vo");
		 
       atd.consultarBoxAdq(idPessoaUsuario);		 
        
       xml_g->closeTag();     
        
        // limpa a memoria do ponteiro
        // XMLString::release(&pAux);
   }   
	else
   {
      ULOG("inTipoPesquisa -> [%s]",pAux);
      ULOG("Criando o OBJETO cWFAtendimento ");
		cWFAtendimento atd(dnode, xml_g);

		int idGrupo = 0;
		int idPessoaUsuario = atoi( getUser() );

		Collection *grupos = 0;

		char *p = walkTree( dnode, "idGrupo", 0 );
		char *inMassa = walkTree( dnode, "inMassa", 0 );

		int idUsuario = atoi( getUser() );
		
		if ( p == NULL )
		{

			grupos = new Collection();

			cPsqFilaPCRC pf;

			pf.obtemGrupos(&idUsuario, grupos);
		}
		else
		{
			XMLString::release(&p);
		}

   	xml_g->createTag("AtendimentoInformacaoVO");
		xml_g->addProp("xmlns",    "workflow.fo.vivo.com.br/vo");
		xml_g->addProp("xmlns:ns2","admsistemas.fo.vivo.com.br/vo");
		xml_g->addProp("xmlns:ns3","usuario.fo.vivo.com.br/vo");

		if ( inMassa == NULL )
			atd.consultarFilaRC( grupos, idUsuario );
		else
		{
			atd.consultarMassaRC();
			XMLString::release(&inMassa);
		}
		xml_g->closeTag();

		delete grupos;
   }
    
    
    
	setStatusCode("04I0000","Consulta Resposta ao Cliente Concluída.");

	ULOG_END("implPSQFILARC::Execute()");
}
