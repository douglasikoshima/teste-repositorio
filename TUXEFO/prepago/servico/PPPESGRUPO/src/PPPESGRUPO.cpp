///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  prepago
 * @usecase PPPESGRUPO
 * @author  Eder Jani Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:24 $
 **/
///////////////////////////////////////////////////////////////////////////////

//-- FRAMEWORK ----------------------------------------------------------------
#include <tuxfw.h>
#include <Global.h>
#include <PrePagoException.h>
#include <Pessoa.h>

DECLARE_TUXEDO_SERVICE(PPPESGRUPO);

void implPPPESGRUPO::Execute(DOMNode *dnode, XMLGen *xml_g)
{
    ULOG_START("PPPESGRUPO");

//-- Instanciandos as classes necessarias ---------------------------------------------------------------------	
	//Classe de manipulacao e montagem de XML de retorno
	CPessoa oPessoa;

	//Estrutura com campos basicos do PrePagoPessoaVO
	TPessoa tPessoa;

	//Recupera algumas tag necessarias no XML
	GETXML( tPessoa, nrGrupo );

//-- Abre a tag principal ManterPrePagoVO ---------------------------------------------------------------------	
	xml_g->createTag("PesquisaGrupoOpcoesVO");
	xml_g->addProp("xmlns", "cliente.fo.vivo.com.br/vo" );

	if( oPessoa.buscaPessoaPorNrGrupo( tPessoa ) )
		xml_g->addItem( "idPessoaGrupo", tPessoa.szIdPessoa );
	else
		xml_g->addItem( "idPessoaGrupo", "" );
		
	xml_g->addItem( "nrGrupo", tPessoa.nrGrupo  );

	xml_g->closeTag();//PesquisaGrupoOpcoesVO

    ULOG_END("PPPESGRUPO");

    setStatusCode( "12I0000", "Sucesso" );
}
