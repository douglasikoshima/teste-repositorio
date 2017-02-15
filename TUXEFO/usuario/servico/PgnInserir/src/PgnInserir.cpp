/*****************************************************************************
 *
 * Modulo:    PgnInserir
 * Arquivo:   PgnInserir.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 17/06/2004  C_RECOliveira         Criacao
 * 17/06/2004  C_EDJMartins          Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

//Definicao Global
#define PgnInserirCPP

//Header de Pgn de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CPgn.h"
#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(PgnInserir);

/**************************************************************************
 *  Funcao de Negocios:  PgnInserir
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
void implPgnInserir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implPgnInserir::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CPgn oPgn;
	char* cidSubSistema;
	char* cidUser = getUser();
	switch (oPgn.PgnInserir(dnode,xml_g, cidUser )) {
		case 0:	
			cidSubSistema = oSafePointer.getTag( dnode, "idSubSistema", 0 );
			if( cidSubSistema != NULL )
			{
				if( oPgn.ListIdSub( cidSubSistema ) )
				{
					xml_g->createTag("ListaPaginasUsuarioVO");
					xml_g->addProp( "xmlns", "usuario.fo.vivo.com.br/vo" );

					xml_g->addItem("idSistema", "" );

					oPgn.GetXml( "PaginaUsuarioVO", xml_g );
					setStatusCode("08I0000","Serviço executado com sucesso."); 

					xml_g->closeTag();
				}
				else
					setStatusCode("08I0000","Não foram encontradas nunhuma página."); 
			}
			break;			
		case 1: setStatusCode("08E0100","Problemas na QUERY do ORACLE."); break;
		case 2: setStatusCode("08E0200","idSubsistema está nulo."); break;
		case 3: setStatusCode("08E0300","nmURL está nulo."); break;
		case 4: setStatusCode("08E0400","inDisponibilidade está nulo."); break;
		case 5: setStatusCode("08E0500","nmPagina está nulo."); break;
		case 6: setStatusCode("08W0600","Não pode haver dois nomes iguais de página no mesmo subsistema"); break;
		default: setStatusCode("08E9999","Erro não classificado."); break;
	}
	ULOG_END("implPgnInserir::Execute()");
	return;
}
