/*****************************************************************************
 *
 * Modulo:    PgnRemover
 * Arquivo:   PgnRemover.cpp
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
#define PgnRemoverCPP

//Header de Pgn de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CPgn.h"
#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(PgnRemover);

/**************************************************************************
 *  Funcao de Negocios:  PgnRemover
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
void implPgnRemover::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implPgnRemover::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CPgn oPgn;
	int nTipo = 0;
	char* cidSubSistema;
	switch (oPgn.PgnRemover(dnode,xml_g, getUser()))
	{
		case 0: 
			nTipo = 1;
		case 1:
			cidSubSistema = oSafePointer.getTag( dnode, "idSubSistema", 0 );
			xml_g->createTag("ListaPaginasUsuarioVO");
			xml_g->addProp( "xmlns", "usuario.fo.vivo.com.br/vo" );
			xml_g->addItem("idSistema", "" );
			if( strlennull( cidSubSistema ) <= 0 )
			{
				if( oPgn.ListIdSub( cidSubSistema ) )
					oPgn.GetXml( "PaginaUsuarioVO", xml_g );
			}
			xml_g->closeTag();
			if( nTipo == 1 )
				setStatusCode("08I0000","Servico executado com sucesso.");
			else
				setStatusCode("08W1000","Este registro contem dependencia e nao pode ser excluido.");
			break;
		case 2: setStatusCode("08E0200","Campo de entrada idPagina esta nulo."); break;
		default: setStatusCode("08E9999","Erro nao classificado."); break;
	}
	ULOG_END("implPgnRemover::Execute()");
	return;
}
