/*****************************************************************************
 *
 * Modulo:    BxaIncluir
 * Arquivo:   BxaIncluir.cpp
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
#define BxaIncluirCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CBxa.h"
#include "../../../negocio/admatdCmm/include/CBxaAnt.h"
#include "../../../negocio/admatdCmm/include/CBxaMsg.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(BxaIncluir);

/**************************************************************************
 *  Funcao de Negocios:  BxaIncluir
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
void implBxaIncluir::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implBxaIncluir::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CBaixa oBaixa;
	CIndicadorAnatelBaixa oIndicadorAnatelBaixa;
	CBaixaMensagem oBaixaMensagem;
	int   iCont;
	int   iWarningNome = 0;
	char* cidIndicador;
	char* cidMensagem;
	char* cdsMensagem;
	char* cidTipoComunicacao;
	char* cidBaixaPai = oSafePointer.getTag( dnode, "idBaixaPai", 0 );
	char* cnmBaixa = oSafePointer.getTag( dnode, "nmBaixa", 0 );
	char* cidNomeBaixa = oSafePointer.getTag( dnode, "idNomeBaixa", 0 );
	char* cinFolha = oSafePointer.getTag( dnode, "inFolha", 0 );
	char* cinDisponibilidade = "1";//oSafePointer.getTag( dnode, "inDisponibilidade", 0 );
	char* cidUser = getUser();
	DOMNode* node;
	if( strlennull( cinFolha ) <= 0 )
	{
		setStatusCode("14E0001","inFolha está nulo");
		ULOG_END("implBxaIncluir::Execute()");
		return;
	}
	//if( strlennull( cinDisponibilidade ) <= 0 )
	//{
	//	setStatusCode("14E0002","inDisponibilidade esta nulo");
	//	return;
	//}
	if( ( strlennull( cnmBaixa ) <= 0 ) && 
		( strlennull( cidNomeBaixa ) <= 0 ) )
	{
		if( strlennull( cidNomeBaixa ) <= 0 )
			setStatusCode("14E0003","idNomeBaixa está nulo");
		else
			setStatusCode("14E0004","idBaixa está nulo");
		ULOG_END("implBxaIncluir::Execute()");	
		return;
	}
	//soh verifaca um nome se nao veio idnomebaixa e veio o nome
	if( ( strlennull( cnmBaixa ) > 0 ) && 
		( strlennull( cidNomeBaixa ) <= 0 ) )
	{
		switch( oBaixa.ProcuraNome( cnmBaixa ) )
		{
			case 0://Nao achou o nome
				break;
			case 1://Achou o nome, mas eh diferente (maisculas e minisculas)
				iWarningNome++;
			case 2://Achou o nome exatamente igual (maisculas e minisculas)
				cidNomeBaixa = oBaixa.getIdNome();
				break;
			default://Nao era pra acontecer, mas....
				break;
		}
	}
	switch( oBaixa.Insert( cidBaixaPai, 
						   cidNomeBaixa,
						   cnmBaixa,
						   cinDisponibilidade,
						   cinFolha,
						   cidUser ) )
	{
		case 0://Sucesso
		{
			if( strcmp( cinFolha, "1" ) == 0 )
			{
				//Recupera apenas o node de indicadores associados
				node = walkDOM( dnode, "indicadoresAssociados", 0 );
				//Remove todos os indicadores anatel associados
				oIndicadorAnatelBaixa.EraseBxa( oBaixa.Registro(0)->cidBaixa );
				//Adiciona indicadores, se existirem
				for( iCont = 0;; iCont++ )
				{
					cidIndicador = oSafePointer.getTag( node, "idIndicador", iCont );
					if( strlennull( cidIndicador ) <= 0 )
						break;

					//Insere one by one
					oIndicadorAnatelBaixa.Insert( oBaixa.Registro(0)->cidBaixa
												 ,cidIndicador
												 ,cidUser );

				}
				
				//Recupera apenas o node da Folha, que contem as mensagens
				node = walkDOM( dnode, "AdmFolhaBaixaVO", 0 );
				//Remove todas as mensagens associadas
				oBaixaMensagem.EraseBxa( oBaixa.Registro(0)->cidBaixa );
				//Adiciona mensagens, se existirem
				for( iCont = 0;; iCont++ )
				{
					cidMensagem = oSafePointer.getTag( node, "idMensagemAviso", iCont );
					cdsMensagem = oSafePointer.getTag( node, "dsMensagemAviso", iCont );
					cidTipoComunicacao = oSafePointer.getTag( node, "idTipoComunicacao", iCont );
					if( strlennull( cidTipoComunicacao ) > 0 )
					{
						oBaixaMensagem.Insert( oBaixa.Registro(0)->cidBaixa
											  ,cidTipoComunicacao
						    				  ,cidMensagem
						    				  ,cdsMensagem
											  ,cidUser );
					}
					else
						break;
				}
			}//if( strcmp( cinFolha, "1" ) == 0 )
			if( iWarningNome )
				setStatusCode("14W0001","O nome digitado foi localizado no repositório de nomes e foi usado na criação do item da árvore, porém ele difere em letras maúsculas e minúsculas daquele que foi digitado");
			else
				setStatusCode("14I0000","Operação concluída com sucesso!");
		}//case 0:
		break;
		case 1: setStatusCode("14E0005", oBaixa.GetErro() ); break;
		case 2: setStatusCode("14W0002", oBaixa.GetErro() ); break;
		default: setStatusCode("14E9999", "Erro não listado" ); break;
	}//switch( oBaixa.Insert(...)
	
	ULOG_END("implBxaIncluir::Execute()");
}
