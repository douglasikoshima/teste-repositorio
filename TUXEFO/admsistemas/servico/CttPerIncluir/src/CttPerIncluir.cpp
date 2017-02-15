/*****************************************************************************
 *
 * Modulo:    CttPerIncluir
 * Arquivo:   CttPerIncluir.cpp
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
#define CttPerIncluirCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCttPes.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttPerIncluir);

/**************************************************************************
 *  Funcao de Negocios:  CttPerIncluir
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
void implCttPerIncluir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttPerIncluir::Execute()");
	CSafePointer oSafePointer;
	CPesquisaSatisfacaoUF oPesquisaSatisfacaoUF;
	CPesquisaSatisfacaoUF oPesquisaSatisfacaoUFNovos;
	CPesquisaSatisfacaoUF oPesquisaSatisfacaoUFExistentes;

	DOMNode* nodeRelacionados = walkDOM( dnode, "relacionados", 0 );
	char* cidContato = oSafePointer.getTag( dnode, "idContato", 0 );
	char* cidQuestionario = oSafePointer.getTag( dnode, "idQuestionarioAtual", 0 );
	char* cidTipoPessoa = oSafePointer.getTag( dnode, "idTipoPessoaAtual", 0 );
	char* cidUser = getUser();
	//Esta variavel controla a quantidade de insercoes que falharam porque ja foram excluidas
	//Por outro usuario em outra estacao, mas os dados ja haviam sido carregados na tela
	int   iWarningDeExclusao = 0;

	char* cidUFOperadora;
	if( nodeRelacionados == NULL )
	{
		setStatusCode("14E0001","Está faltando a TAG [relacionados]");
		ULOG_END("implCttPerIncluir::Execute()");
		return;
	}
	if( strlennull( cidContato ) <= 0 )
	{
		setStatusCode("14E0002","idContato está nulo");
		ULOG_END("implCttPerIncluir::Execute()");
		return;
	}
	if( strlennull( cidQuestionario ) <= 0 )
	{
		setStatusCode("14E0003","idQuestionarioAtual está nulo");
		ULOG_END("implCttPerIncluir::Execute()");
		return;
	}
	if( strlennull( cidTipoPessoa ) <= 0 )
	{
		setStatusCode("14E0004","idTipoPessoaAtual está nulo");
		ULOG_END("implCttPerIncluir::Execute()");
		return;
	}
	int iUfo;
	
	oPesquisaSatisfacaoUFExistentes.ListContatoQuestionario( 
                                                          cidContato, 
                                                          cidQuestionario, 
                                                          cidTipoPessoa );
   
   oPesquisaSatisfacaoUF.UFDelete( cidContato, cidQuestionario, cidTipoPessoa );

	for( iUfo = 0;; iUfo++ )
	{
		cidUFOperadora = oSafePointer.getTag( nodeRelacionados, "idUFOperadora", iUfo );
		if( strlennull( cidUFOperadora ) <= 0 )
			break;
      
      /*
		if( oPesquisaSatisfacaoUFExistentes.FindPar( cidContato
					                                ,cidUFOperadora
													,cidQuestionario
													,cidTipoPessoa ) > 0 )
		{
			oPesquisaSatisfacaoUFNovos.AddExistentes( cidContato,
													  cidUFOperadora,
													  cidQuestionario,
													  cidTipoPessoa );
		}
		else
		{
			if( oPesquisaSatisfacaoUFNovos.Insert( cidContato,
												   cidUFOperadora,
												   cidQuestionario,
												   cidTipoPessoa,
  												   cidUser ) > 0 )
			{
				iWarningDeExclusao++;
			}
		}
      */
      
      oPesquisaSatisfacaoUFNovos.Insert( cidContato,
                                         cidUFOperadora,
                                         cidQuestionario,
                                         cidTipoPessoa,
                                         cidUser );
      
	}//for( iUfo = 0;; iUfo++ )
	/*
   for( int iCont = 0; iCont < oPesquisaSatisfacaoUFExistentes.Quantidade(); iCont++ )
	{
		if( !oPesquisaSatisfacaoUFNovos.FindPar( oPesquisaSatisfacaoUFExistentes.Registro(iCont)->cidContato
			                                    ,oPesquisaSatisfacaoUFExistentes.Registro(iCont)->cidUFOperadora
			                                    ,oPesquisaSatisfacaoUFExistentes.Registro(iCont)->cidPesquisaSatisfacao
			                                    ,oPesquisaSatisfacaoUFExistentes.Registro(iCont)->cidTipoPessoa ) )
		{
			oPesquisaSatisfacaoUF.Delete( oPesquisaSatisfacaoUFExistentes.Registro(iCont)->cidPesquisaSatisfacaoUF );
		}
	}
   */
	if( iWarningDeExclusao <= 0 )
		setStatusCode("14I0000","Operação realizada com sucesso!");
	else
		setStatusCode("14W0001","Questionário apagado em outra estação, dados não foram relacionados");
		
	ULOG_END("implCttPerIncluir::Execute()");
}

