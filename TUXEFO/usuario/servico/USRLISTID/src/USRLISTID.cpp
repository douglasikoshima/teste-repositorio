/*****************************************************************************
 *
 * Modulo:    USRLISTID
 * Arquivo:   USRLISTID.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c 
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 24/01/2008  C_CGarcia             Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

/*****************************************************************************
 * Definicao Global
 ****************************************************************************/

#define USRLISTIDCPP

/*****************************************************************************
 * Header de Classe de Infra-Estrutura
 ****************************************************************************/

#include "../../../negocio/acessoCmm/include/CAcs.h"
#include <tuxfw.h> 
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

/*****************************************************************************
 * Macro de Definicao do Framework Tuxedo
 ****************************************************************************/

DECLARE_TUXEDO_SERVICE(USRLISTID);

/**************************************************************************
 *  Funcao de Negocios:  USRLISTID
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

/*****************************************************************************
 * Funcao Local
 ****************************************************************************/

void implUSRLISTID::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implUSRLISTID::Execute()");
	
	CSafePointer oSafePointer;
	char*     cnmUrlPagina;
	char*     cinAtivo;
	char*     cinSair;
	char*     csessionId;
	char*     cIdUser = getUser();
	int       nStatusSessao = 0;
	CAcs      oAcesso;

	//Quando vem a pagina � o controle de acesso pagina-a-pagina
	//Quando nao vem � para carregar todo o perfil na hora de login (isto no FO)
	cnmUrlPagina = oSafePointer.getTag( dnode, "pagina", 0 );
	//Esta TAG indica que � para verificar se o usuario esta ou nao ativo
	cinAtivo = oSafePointer.getTag( dnode, "inAtivo", 0 );
	//inSair = 0, indica que o usu�rio est� efetuando logout na aplicacao
	cinSair = oSafePointer.getTag( dnode, "inSair", 0 );
	//sessionId tem o ID �nico da sessao
	csessionId = oSafePointer.getTag( dnode, "sessionId", 0 );
	
	if( strlennull( csessionId ) <= 0 )
	{
		setStatusCode("08E0000","TAG <sessionId> � obrigat�ria. Est� vazia ou n�o existe");
	}
	else
	{
		if( strlennull( cnmUrlPagina ) > 0 )
		{
			setStatusCode("08E0000","TAG <pagina> n�o � mais utilizado "
                            "pelo controle de acesso. Favor atualizar a chamada.");
		}//else if( strlen( cnmUrlPagina ) > 0 )
		else
		{
			//N�o verifica se o usu�rio est� ou n�o logado. Apenas insere ou atualiza um novo sessionId
			//Quaquer mensagem ser� dada pelo VerificaAtualizalogin(...) dentro do per�odo APOIO.PARAMETRO.CDPARAMETRO = 'TEMPO_LOGIN_CONCOMITANTE'
			oAcesso.login( cIdUser, csessionId );

			//Monsta um XML com todo os perfil do usuario, ele eh chamado apenas uma vez no momento de login
			xml_g->createTag( "RuleVO" );
			xml_g->addProp( "xmlns", "usuario.fo.vivo.com.br/vo" );
	
			if( oAcesso.UsuarioAtivo( cIdUser ) > 0 )
			{
				if( oAcesso.ListId( cIdUser,xml_g ) > 0 )
                {
					setStatusCode("08I0000","Sucesso");
                }
				else
                {
					setStatusCode("08W0000","Usu�rio sem permiss�o. "
                                   "Entre em contato com o help desk para verificar seu perfil!");
                }
			}
			else
            {
				setStatusCode("08W0000","Usu�rio Inativo. Entre em contato com o help desk");
            }

			xml_g->closeTag();//RuleVO
		}//if( strlennull( cnmUrlPagina ) > 0 )
	}//else if( strlennull( csessionId ) <= 0 )

	ULOG_END("implUSRLISTID::Execute()");
}
