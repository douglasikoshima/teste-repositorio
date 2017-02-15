/*****************************************************************************
 *
 * Modulo:    BXAUFORELACIO
 * Arquivo:   BXAUFORELACIO.cpp
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
#define BXAUFORELACIOCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CBxaUfo.h"
#include "../../../negocio/admatdCmm/include/CBxa.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(BXAUFORELACIO);

/**************************************************************************
 *  Funcao de Negocios:  BXAUFORELACIO
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
void implBXAUFORELACIO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implBXAUFORELACIO::Execute()");
	/* Chamada de Funcao de Negocios */
	int iCont;
	CSafePointer oSafePointer;
	CBaixaUfoperadora oBaixaUfoperadora;
	CBaixaUfoperadora oBaixaUfoperadoraVelha;
	CBaixa oBaixa;
	char* cidUFOperadora;
	char  cinDisponibilidade[255+1];
	char* cidBaixa = oSafePointer.getTag( dnode, "idBaixa", 0 );
	char* cindeterminado = oSafePointer.getTag( dnode, "indeterminado", 0 );
	char* cdtInicioVigencia = oSafePointer.getTag( dnode, "dtInicioVigencia", 0 );
	char* cdtFimVigencia = oSafePointer.getTag( dnode, "dtFimVigencia", 0 );
	char* cidUser = getUser();
	
	if( strlennull( cidBaixa ) <= 0 )
	{
		setStatusCode("14E0001","idBaixa está nulo");
		ULOG_END("implBXAUFORELACIO::Execute()");
		return;
	}
	if( strlennull( cindeterminado ) <= 0 )
	{
		setStatusCode("14E0002","indeterminado está nulo");
		ULOG_END("implBXAUFORELACIO::Execute()");
		return;
	}
	if( strlennull( cdtInicioVigencia ) <= 0 )
	{
		setStatusCode("14E0003","dtInicioVigencia está nulo");
		ULOG_END("implBXAUFORELACIO::Execute()");
		return;
	}
	if( strcmp( cindeterminado, "1" ) == 0 )
	{
		if( strlennull( cdtFimVigencia ) <= 0 )
		{
			setStatusCode("14E0004","dtFimVigencia está nulo");
			ULOG_END("implBXAUFORELACIO::Execute()");
			return;
		}
	}
	else
		cdtFimVigencia = 0;//Soh para ter certeza que estara NULL

	if( oBaixa.TemFolha( cidBaixa ) )
	{
		//Lista todos os registros
		oBaixaUfoperadoraVelha.ListIdBaixa( cidBaixa ); 
		//Apaga todas as UFOperadoras do idBaixa recebido
		//oBaixaUfoperadora.EraseFlh( cidBaixa );
		
		//Adicona as novas relacoes
		for( iCont = 0;;iCont++)
		{
			cidUFOperadora = oSafePointer.getTag( dnode, "idUFOperadora", iCont );
			if( strlennull( cidUFOperadora ) <= 0 )
				break;
			memset( cinDisponibilidade, 0, sizeof( cinDisponibilidade ) );
			strcpy( cinDisponibilidade ,oSafePointer.getTag( dnode, "inDisponibilidade", iCont ) );
			if( strlennull( cinDisponibilidade ) <= 0 )
				strcpy( cinDisponibilidade, "0" );

			//Soh Insere se nao achar nada
			if( oBaixaUfoperadoraVelha.Find( cidBaixa, cidUFOperadora ) == -1 )
			{
				oBaixaUfoperadora.Insert( cidBaixa
										   ,cidUFOperadora
										   ,cdtInicioVigencia
										   ,cdtFimVigencia
										   ,cinDisponibilidade
										   ,cidUser );
			}
			else
			{
				oBaixaUfoperadora.Adicionar( "", cidBaixa, cidUFOperadora );
			}

		}//for( iCont = 0;;iCont++)
		
		//Apaga os itens que nao esta na nova lista
		for( iCont = 0; iCont < oBaixaUfoperadoraVelha.Quantidade(); iCont++ )
		{
			if( oBaixaUfoperadora.Find( oBaixaUfoperadoraVelha.Registro(iCont)->cidBaixa
				                         ,oBaixaUfoperadoraVelha.Registro(iCont)->cidUFOperadora ) == -1 )
			{
				oBaixaUfoperadora.Delete( oBaixaUfoperadoraVelha.Registro(iCont)->cidBaixaUFOperadora );
			}
		}

		if( oBaixaUfoperadora.Relacao( cidBaixa, xml_g ) )
			setStatusCode("14I0000","Servico executado com sucesso");
		else
			setStatusCode("14E0005","idBaixa está nulo");
	}
	else
		setStatusCode("14W0001","Não se pode incluir UFOperadora em uma pasta");
		
	ULOG_END("implBXAUFORELACIO::Execute()");
}
