/*****************************************************************************
 *
 * Modulo:    CttUfoRelaciona
 * Arquivo:   CttUfoRelaciona.cpp
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
#define CttUfoRelacionaCPP

//Header de Srv de Infra-Estrutura
#include <tuxfw.h>
#include "../../../negocio/admatdCmm/include/CCttUfo.h"
#include "../../../negocio/admatdCmm/include/CCtt.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttUfoRelaciona);

/**************************************************************************
 *  Funcao de Negocios:  CttUfoRelaciona
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
void implCttUfoRelaciona::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttUfoRelaciona::Execute()");
	/* Chamada de Funcao de Negocios */
	int iCont;
	CContatoUfoperadora oContatoUfoperadora;
	CContatoUfoperadora oContatoUfoperadoraVelha;
	CContato oContato;
	char* cidUFOperadora;
	char  cinDisponibilidade[255+1];
	char* cidContato = walkTree( dnode, "idContato", 0 );
	char* cindeterminado = walkTree( dnode, "indeterminado", 0 );
	char* cdtInicioVigencia = walkTree( dnode, "dtInicioVigencia", 0 );
	char* cdtFimVigencia = walkTree( dnode, "dtFimVigencia", 0 );
	char* cidUser = getUser();
	
	if( strlennull( cidContato ) <= 0 )
	{
		setStatusCode("00E0000","idContato esta nulo");
		ULOG_END("implCttUfoRelaciona::Execute()");
		return;
	}
	if( strlennull( cindeterminado ) <= 0 )
	{
		setStatusCode("00E0000","indeterminado esta nulo");
		ULOG_END("implCttUfoRelaciona::Execute()");
		return;
	}
	if( strlennull( cdtInicioVigencia ) <= 0 )
	{
		setStatusCode("00E0000","dtInicioVigencia esta nulo");
		ULOG_END("implCttUfoRelaciona::Execute()");
		return;
	}
	if( strcmp( cindeterminado, "1" ) == 0 )
	{
		if( strlennull( cdtFimVigencia ) <= 0 )
		{
			setStatusCode("00E0000","dtFimVigencia esta nulo");
			ULOG_END("implCttUfoRelaciona::Execute()");
			return;
		}
	}
	else
		cdtFimVigencia = 0;//Soh para ter certeza que estara NULL

	if( oContato.TemFolha( cidContato ) )
	{
		//Lista todos os registros
		oContatoUfoperadoraVelha.ListIdContato( cidContato ); 
		//Apaga todas as UFOperadoras do idContato recebido
		//oContatoUfoperadora.EraseFlh( cidContato );
		
		//Adicona as novas relacoes
		for( iCont = 0;;iCont++)
		{
			cidUFOperadora = walkTree( dnode, "idUFOperadora", iCont );
			if( strlennull( cidUFOperadora ) <= 0 )
				break;
			memset( cinDisponibilidade, 0, sizeof( cinDisponibilidade ) );
			strcpy( cinDisponibilidade ,walkTree( dnode, "inDisponibilidade", iCont ) );
			if( strlennull( cinDisponibilidade ) <= 0 )
				strcpy( cinDisponibilidade, "0" );

			//Soh Insere se nao achar nada
			if( oContatoUfoperadoraVelha.Find( cidContato, cidUFOperadora ) == -1 )
			{
				oContatoUfoperadora.Insert( cidContato
										   ,cidUFOperadora
										   ,cdtInicioVigencia
										   ,cdtFimVigencia
										   ,cinDisponibilidade
										   ,cidUser );
			}
			else
			{
				oContatoUfoperadora.Adicionar( "", cidContato, cidUFOperadora );
			}

		}//for( iCont = 0;;iCont++)
		
		//Apaga os itens que nao esta na nova lista
		for( iCont = 0; iCont < oContatoUfoperadoraVelha.Quantidade(); iCont++ )
		{
			if( oContatoUfoperadora.Find( oContatoUfoperadoraVelha.Registro(iCont)->cidContato
				                         ,oContatoUfoperadoraVelha.Registro(iCont)->cidUFOperadora ) == -1 )
			{
				oContatoUfoperadora.Delete( oContatoUfoperadoraVelha.Registro(iCont)->cidContatoUFOperadora );
			}
		}

		if( oContatoUfoperadora.Relacao( cidContato, xml_g ) )
			setStatusCode("00I0000","Servico executado com sucesso");
		else
			setStatusCode("00E0000","idContato esta nulo");
	}
	else
		setStatusCode("00W0000","Nao se pode incluir UFOperadora em uma pasta");
	ULOG_END("implCttUfoRelaciona::Execute()");
}
