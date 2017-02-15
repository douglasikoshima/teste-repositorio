/*****************************************************************************
 *
 * Modulo:    PgnListaPar
 * Arquivo:   PgnListaPar.cpp
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
#define PgnListaParCPP

//Header de Pgn de Infra-Estrutura
#include "../../../negocio/acessoCmm/include/CPgn.h"
#include <tuxfw.h>
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(PgnListaPar);

/**************************************************************************
 *  Funcao de Negocios:  PgnListaPar
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
void implPgnListaPar::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implPgnListaPar::Execute()");
	
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CPgn oPgn;
	char cnmPagina[255+1];
	char cnmUrl[255+1];
	char cIndisponib[21+1];
	char cidSubSistema[21+1];
	char cidSistema[21+1];

	strcpy( cnmPagina, oSafePointer.getTag( dnode, "nmPagina", 0 ) );
	strcpy( cnmUrl, oSafePointer.getTag( dnode, "nmUrl", 0 ) );
	strcpy( cIndisponib, oSafePointer.getTag( dnode, "inDisponib", 0 ) );
	strcpy( cidSubSistema, oSafePointer.getTag( dnode, "idSubSistema", 0 ) );
	strcpy( cidSistema, oSafePointer.getTag( dnode, "idSistema", 0 ) );

	xml_g->createTag("ListaPaginasUsuarioVO");
	xml_g->addProp( "xmlns", "usuario.fo.vivo.com.br/vo" );
	xml_g->addItem("idSistema", cidSistema );
	if( oPgn.ListPar( cnmPagina
		             ,cnmUrl
					 ,cIndisponib
					 ,cidSubSistema
					 ,cidSistema ) > 0 )
	{
		oPgn.GetXml( "PaginaUsuarioVO", xml_g );
	} 
	xml_g->closeTag();
	setStatusCode("08I0000","Servico executado com sucesso."); 
	
	ULOG_END("implPgnListaPar::Execute()");
}
