/*****************************************************************************
 *
 * Modulo:    atendimento
 * Arquivo:   WFORDPERFIL.cpp
 * Proposito: alterar a ordem de um perfilgrupo de um contato
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 25/09/2005  C_EDMartins           Criacao
 *
 ****************************************************************************/

//Definicao Global
#define WFORDPERFILCPP

//Header de Imn de Infra-Estrutura
#include <WFORDPERFIL.h>
#include <tuxfw.h>

extern int MoverPerfil( char* pzcidContatoPerfil, int iinMove, char* pzcidUser );

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(WFORDPERFIL);

/**************************************************************************
 *  Funcao de Negocios:  ImnMover
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
void implWFORDPERFIL::Execute(DOMNode*dnode,XMLGen*xml_g)
{
    ULOG_START("implWFORDPERFIL::Execute()");
    
	/* Chamada de Funcao de Negocios */
	char cidContatoPerfil[21+1];
	char cinMove[21+1];
	char cidUser[21+1];

	char* pzcidContatoPerfil = walkTree( dnode, "idContatoPerfil", 0 );
	char* pzcinMove = walkTree( dnode, "inMover", 0 );
	char* pzcidUser = getUser();

	memset( cidContatoPerfil, 0, sizeof( cidContatoPerfil ) );
	memset( cinMove, 0, sizeof( cinMove ) );
	memset( cidUser, 0, sizeof( cidUser ) );

	if( STRLENNULL( pzcidContatoPerfil ) > 0 )
		strcpy( cidContatoPerfil, pzcidContatoPerfil );
	if( STRLENNULL( pzcinMove ) > 0 )
		strcpy( cinMove, pzcinMove );
	if( STRLENNULL( pzcidUser ) > 0 )
		strcpy( cidUser, pzcidUser );

	XMLString::release( &pzcidContatoPerfil );
	XMLString::release( &pzcinMove );
	XMLString::release( &pzcidUser );

	int   inMove;
	if( STRLENNULL( cinMove ) > 0 )
	{
		if( strcmp( cinMove, "0" ) == 0 )
			inMove = 0;//Para cima
		else
			inMove = 1;//Para baixo

		switch( MoverPerfil( cidContatoPerfil, inMove, cidUser ) ) 
		{
			case 0:	setStatusCode("09I0000","Servico executado com sucesso."); break;
			case 1: setStatusCode("09E0001","idContatoPerfil está nulo"); break;
			case 2: setStatusCode("09E0002","ID do usuário está nulo"); break;
			case 4: setStatusCode("09W0002","Não há como mover apenas um item"); break;
			case 5: setStatusCode("09W0003","Esta operação não pode ser realizada"); break;
			default: setStatusCode("09E9999","Erro não esperado"); break;
		}
	}
	else
		setStatusCode("09E0003","inMover está nulo");

    ULOG_END("implWFORDPERFIL::Execute()");
	return;
}
