/*****************************************************************************
 *
 * Modulo:    CttInfRelaciona
 * Arquivo:   CttInfRelaciona.cpp
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
#define CttInfRelacionaCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CCttInf.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttInfRelaciona);

/**************************************************************************
 *  Funcao de Negocios:  CttInfRelaciona
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
void implCttInfRelaciona::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttInfRelaciona::Execute()");
	/* Chamada de Funcao de Negocios */
	CContatoInformacao oContatoInformacao;
	CContatoInformacao oContatoInformacaoVelho;
	DOMNode* dnSelecionados = walkDOM( dnode, "selecionados" );
	int iTipoLinha;
	int iTipoCliente;
	int iUFOperadora;
	int iPosicao;
	char* cnmLink;
	char* cidContato;
	char* cidTipoLinha;
	char* cidTipoCliente;
	char* cidUFOperadora;

	if( dnSelecionados != NULL )
	{
		cidContato = walkTree( dnSelecionados, "idContato", 0 );
		cnmLink = walkTree( dnSelecionados, "nmLink", 0 );
		
		if( strlennull( cidContato ) > 0 )
		{
			if( strlennull( cnmLink ) > 0 )
			{
				oContatoInformacaoVelho.ListId( cidContato );

				for( iTipoLinha = 0;; iTipoLinha++ )
				{
					cidTipoLinha = walkTree( dnSelecionados, "idTipoLinha", iTipoLinha );
					if( strlennull( cidTipoLinha ) <= 0 )
						break;
					for( iUFOperadora = 0;; iUFOperadora++ )
					{
						cidUFOperadora = walkTree( dnSelecionados, "idUFOperadora", iUFOperadora );
						if( strlennull( cidUFOperadora ) <= 0 )
							break;
						for( iTipoCliente = 0;; iTipoCliente++ )
						{
							cidTipoCliente = walkTree( dnSelecionados, "idTipoCliente", iTipoCliente );
							if( strlennull( cidTipoCliente ) <= 0 )
								break;

							//Se achar na lista, entao nao insere, apenas faz update
							if( ( iPosicao = oContatoInformacaoVelho.FindPar( cidUFOperadora
																			 ,cidTipoLinha
																			 ,cidTipoCliente ) ) < 0 )
							{
								oContatoInformacao.Insert( cidContato
														  ,cidUFOperadora
														  ,cidTipoLinha
														  ,cidTipoCliente
														  ,cnmLink
														  ,getUser() );
							}
							else
							{
								oContatoInformacao.Update( oContatoInformacaoVelho.Registro(iPosicao)->cidContatoInformacao
														  ,cidContato
														  ,cidUFOperadora
														  ,cidTipoLinha
														  ,cidTipoCliente
														  ,cnmLink
														  ,getUser() );
							}
						}//for( iTipoCliente = 0;; iTipoCliente++ )
					}//for( iUFOperadora = 0;; iUFOperadora++ )
				}//for( iTipoLinha = 0;; iTipoLinha++ )

				//Apaga os que nao foram inseridos
				//for( int x=0; x < oContatoInformacaoVelho.Quantidade(); x++ )
				//{
				//	if( ( iPosicao = oContatoInformacao.FindPar( oContatoInformacaoVelho.Registro(x)->cidUFOperadora
				//									 		    ,oContatoInformacaoVelho.Registro(x)->cidTipoLinha
				//	    									    ,oContatoInformacaoVelho.Registro(x)->cidTipoCliente ) ) == -1 )
				//	{
				//		oContatoInformacao.Delete( oContatoInformacaoVelho.Registro(iPosicao)->cidContatoInformacao );
				//	}
				//}

				//Retorna a lista completa para atualizar a tela
				oContatoInformacao.Relacao( cidContato, xml_g );

				setStatusCode("00I0000","Operacao concluida com sucesso!");
			}//if( strlennull( cnmLink ) > 0 )
			else
				setStatusCode("00E0000","nmLink esta nulo");
		}//if( strlennull( cidContato ) > 0 )
		else
			setStatusCode("00E0000","idContato esta nulo");
	}//if( dnSelecionados != NULL )
	else
		setStatusCode("00E0000","Tag [selecionados] nao foi encontrada");
		
   ULOG_END("implCttInfRelaciona::Execute()");

}
