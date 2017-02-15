 /**
 * @modulo  Atendimento
 * @author  Eder Jani Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:08 $
 **/

#include <tuxfw.h>
#include "../../Atendimento/include/cWFAtendimento.h"
#include "../include/cPsqFilaCRIPC.h"
#include "../../../commons/Collection/include/Collection.h"

extern bool proCConsultaWFGruposFilaProcesso( int* _idPessoaUsuario, Collection* _grupos );

DECLARE_TUXEDO_SERVICE(PSQFILACRI)

void implPSQFILACRI::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implPSQFILACRI::Executar()");
    
	int   idUsuario = atoi( getUser() );

	//char *idGrupo = walkTree( dnode, "idGrupo", 0 );

	Collection *grupos = 0;

	//	Atribui dnode e xml_g internamente na classe cWFAtendimento e verifica todos as
	//	TAGs
	ULOG("PSQFILACRI::Lendo o XML de entrada");
	cWFAtendimento atd(dnode, xml_g);

    ////////////////////////////////////////////////////////////////////////////
    // Esta Collection de grupos não esta sendo usada pela rotina de busca
    // de processos da fila de CRI. - Cassio - Set/2005
    ////////////////////////////////////////////////////////////////////////////
    //
	//ULOG("PSQFILACRI::idGrupo[%s]", idGrupo);
	//if( idGrupo == NULL )
	//{
	//	// Se estiver nulo, cria esta Collection
	//	grupos = new Collection();
    //
	//	// classe para recuperar os grupos CRI
	//	cPsqFilaCRIPC pf;
    //
	//	// Recupera os Grupos CRI
	//	ULOG("PSQFILACRI::Antes obtemGrupos(...)");
	//	pf.obtemGrupos(&idUsuario, grupos);
	//	ULOG("PSQFILACRI::Apos obtemGrupos(...)");
	//}
    //
    ////////////////////////////////////////////////////////////////////////////

	ULOG("PSQFILACRI::Iniciando a montagem do XML OUT");
	xml_g->createTag("AtendimentoInformacaoVO");
	
	xml_g->addProp("xmlns",    "workflow.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:ns2","admsistemas.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:ns3","usuario.fo.vivo.com.br/vo");

	//Realiza a pesquisa de FILA CRI
	atd.consultarFilaCRI( grupos, idUsuario );
	
	xml_g->closeTag();//AtendimentoInformacaoVO
	ULOG("PSQFILACRI::Finalizando a montagem do XML OUT");

	ULOG("PSQFILACRI::Iniciando a limpeza das variaveis");
    
    //Libera os ponteiros
	//XMLString::release(&idGrupo);

	//Apaga as classes dinamicas
    delete grupos;
	ULOG("PSQFILACRI::Finalizando a limpeza das variaveis");

	//Estatus de retorno
	setStatusCode("04I0000","PSQFILACRI::Consulta Concluída.");
	
	ULOG_END("implPSQFILACRI::Executar()");
}
