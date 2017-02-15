/*****************************************************************************
 *
 * Modulo:    UsrAcesso
 * Arquivo:   UsrAcesso.cpp
 * Proposito: servico do framework tuxedo que aciona servico pro*c 
 * Historico:
 * Data        Autor                 Descricao
 * ----------  --------------------  -----------------------------------------
 * 18/05/2004  C_RECOliveira         Criacao
 * 18/05/2004  C_EDMartins           Criacao
 *
 ****************************************************************************/
/**************************************************************************
 * Notas:
 *
 **************************************************************************/

/*****************************************************************************
 * Definicao Global
 ****************************************************************************/

#define UsrAcessoCPP

/*****************************************************************************
 * Header de Classe de Infra-Estrutura
 ****************************************************************************/

#include "../../../negocio/acessoCmm/include/CAcs.h"
#include <tuxfw.h> 
#include "../../../negocio/acessoCmm/include/CSafePointer.h"

/*****************************************************************************
 * Macro de Definicao do Framework Tuxedo
 ****************************************************************************/

DECLARE_TUXEDO_SERVICE(UsrAcesso);

/**************************************************************************
 *  Funcao de Negocios:  UsrAcesso
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

void implUsrAcesso::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	ULOG_START("implUsrAcesso::Execute()");
	
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
		if( strlennull( cinSair ) > 0 )
		{
			if( strcmp( cinSair, "1" ) == 0 )
			{
				//Apaga o indicador de login com a mesma sess�o, se houver outra diferente n�o apaga, pois
				//alguem pode ter se logado a�s ele

                // Deu erro com esta vers�o. Cassio 08/12/2006
				// oAcesso.logout( cIdUser, csessionId );
				oAcesso.logout( cIdUser );
				setStatusCode("08I0000","Sucesso");

			}
			else if( strcmp( cinSair, "0" ) == 0 )
			{
				//Verifica se o usu�rio est� ou n�o logado, se tiver retorna um warning para exibir um popup na tela, caso contrario um sotaque
				//No entanto eu devolvo um warning quando o usu�rio � o segundo a se logar dentro do periodo estipulado
				//Mas mesmo com este warning o login � realizado.
				switch( oAcesso.UsuarioLogado( cIdUser ) )
				{
					case -1:
						setStatusCode("08E0000", oAcesso.getMensagem());
						break;
					case 0:
						setStatusCode("08I0000", oAcesso.getMensagem());
						break;
					case 1:
						setStatusCode("08W0000", oAcesso.getMensagem());
						break;
					default:
						setStatusCode("08E0000", "Erro desconhecido no retorno de CAcs::UsuarioLogado()");
				};
			}
			else
			{
				setStatusCode("08E0000","TAG <inSair> com valor inv�lido");
			}
		
		}//if( strlennull( cinSair ) > 0 )
		else
		{
			if( strlennull( cinAtivo ) > 0 )
			{
				xml_g->createTag( "RuleVO" );
				xml_g->addProp( "xmlns", "usuario.fo.vivo.com.br/vo" );

				//Dentro de um per�odo estabelicido em APOIO.PARAMETRO.CDPARAMETRO = 'TEMPO_LOGIN_CONCOMITANTE'
				//Verificamos se a nossa sess�o � a mesma, se mudou ou n�o existe mostra uma mensagem de alerta
				//0 para sessao valida (se a sessao eh valida entao TEM que verificar se o login esta ativo)
				//2 para sessao expirada (sessionId � o mesmo mais o tempo expirou)
				//3 para sessao invalida (sessionId n�o foi achado na base ou n�o bate )
				nStatusSessao = oAcesso.VerificaAtualizalogin( cIdUser, csessionId );
				if( nStatusSessao == 0 )
				{
					//O sistema verifica de tempos em tempos se o usuario ainda esta ativo
					//0 se inativo
					//1 se ativo
					if( oAcesso.UsuarioAtivo( cIdUser ) > 0 )
						nStatusSessao = 1;
				}
				//0 usuario inativo
			    //1 usuario ativo
				//2 para sessao expirada
				//3 para sessao invalida
				xml_g->addItem("us", nStatusSessao );

				xml_g->closeTag();//RuleVO

				if( nStatusSessao == -1 )
					setStatusCode("08E0000", oAcesso.getMensagem());
				else
					setStatusCode("08I0000", oAcesso.getMensagem());
			}
            //
            // Esta parte foi movida para o servi�o UsrListID, pois a rotina oAcesso.ListId
            // esta consumindo muito tempo de processamento compromentendo outras funcionalidades
            // do servi�o.
            //
			// else
			// {
			// 	if( strlennull( cnmUrlPagina ) > 0 )
			// 	{
			// 		setStatusCode("08E0000","TAG <pagina> n�o � mais utilizado pelo controle de acesso. Favor atualizar a chamada.");
			// 	}//else if( strlen( cnmUrlPagina ) > 0 )
			// 	else
			// 	{
			// 		//N�o verifica se o usu�rio est� ou n�o logado. Apenas insere ou atualiza um novo sessionId
			// 		//Quaquer mensagem ser� dada pelo VerificaAtualizalogin(...) dentro do per�odo APOIO.PARAMETRO.CDPARAMETRO = 'TEMPO_LOGIN_CONCOMITANTE'
			// 		oAcesso.login( cIdUser, csessionId );
            // 
			// 		//Monsta um XML com todo os perfil do usuario, ele eh chamado apenas uma vez no momento de login
			// 		xml_g->createTag( "RuleVO" );
			// 		xml_g->addProp( "xmlns", "usuario.fo.vivo.com.br/vo" );
			// 
			// 		if( oAcesso.UsuarioAtivo( cIdUser ) > 0 )
			// 		{
			// 			if( oAcesso.ListId( cIdUser,xml_g ) > 0 )
			// 				setStatusCode("08I0000","Sucesso");
			// 			else
			// 				setStatusCode("08W0000","Usu�rio sem permiss�o. Entre em contato com o help desk para verificar seu perfil!");
			// 		}
			// 		else
			// 			setStatusCode("08W0000","Usu�rio Inativo. Entre em contato com o help desk");
            // 
			// 		xml_g->closeTag();//RuleVO
			// 	}//if( strlennull( cnmUrlPagina ) > 0 )
			// }//if( strlennull( cinAtivo ) > 0 )
		}//else if( strlennull( cinSair ) > 0 )
	}//else if( strlennull( csessionId ) <= 0 )	
	ULOG_END("implUsrAcesso::Execute()");
}
