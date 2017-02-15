/*****************************************************************************
 *
 * Modulo:    PgnListaIdSub
 * Arquivo:   PgnListaIdSub.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 17/06/2004  C_RECOliveira         Criacao
 * 17/06/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

//Definicao Global
#define PgnListaCPP

//Header de Pgn de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CPgn.h"
#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(PgnListaIdSub);

/**************************************************************************
 *  Funcao de Negocios:  PgnLista
 *
 *  Descricao: chama funcao de negocios
 *
 *  Parametros de Entrada e Saida:
 *  DOMNode		*dnode    Nodos da Arvore DOM XML
 *  XMLGen		*xml_g    XML de entrada
 *
 *  Parametros de Saida:
 *  DOMNode		*dnode    Nodos da Arvore DOM XML
 *  XMLGen		*xml_g    XML de saida
 *
 *************************************************************************/
void implPgnListaIdSub::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implPgnListaIdSub::Execute()");
	
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CPgn oPgn;
	char* cidSubSistema = oSafePointer.getTag( dnode, "idSubSistema", 0 );
	if( cidSubSistema != NULL )
	{
		if( oPgn.ListIdSub( cidSubSistema ) > 0 )
		{
			xml_g->createTag( "SubSistemaPaginasVO" );
			xml_g->addProp( "xmlns", "usuario.fo.vivo.com.br/vo" );
			xml_g->addItem( "idSubSistema", cidSubSistema );
			xml_g->addItem( "dsSubSistema", "" );

			oPgn.GetXml( "PaginaSimplVO", xml_g );

			xml_g->closeTag();
			
			setStatusCode("08I0000","Servico executado com sucesso.");
		}
		else
			setStatusCode("08W0000","Nenhuma página foi encontrada.");
	}
	else
		setStatusCode("08E0000","idSubSistema está nulo.");
	
	ULOG_END("implPgnListaIdSub::Execute()");
	return;
}
