/*****************************************************************************
 *
 * Modulo:    CttFrmRelaciona
 * Arquivo:   CttFrmRelaciona.cpp
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
#define CttFrmRelacionaCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CFlhCmp.h"
#include "../../../negocio/admatdCmm/include/CLin.h"
#include "../../../negocio/admatdCmm/include/CUfo.h"
#include "../../../negocio/admatdCmm/include/CCmp.h"
#include "../../../negocio/admatdCmm/include/CCmpCls.h"
#include "../../../negocio/admatdCmm/include/CCmpFse.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttFrmRelaciona);

/**************************************************************************
 *  Funcao de Negocios:  CttFrmRelaciona
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
void implCttFrmRelaciona::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttFrmRelaciona::Execute()");
	/* Chamada de Funcao de Negocios */
	CContatoFolhaCampo oContatoFolhaCampoExistentes;
	CContatoFolhaCampo oContatoFolhaCampoNovos;
	CContatoFolhaCampo oContatoFolhaCampo;

	char* cidContato = walkTree( dnode, "idContato", 0 );
	char* cidClassificadorCampoAtual = walkTree( dnode, "idClassificadorCampoAtual", 0 );
	char* cidFaseProcessoAtual = walkTree( dnode, "idFaseProcessoAtual", 0 );
	char* cidUser = getUser();
	char* cidUFOperadora;
	char* cidTipoLinha;
	char* cidCampo;
	
	if( strlennull( cidContato ) <= 0 )
	{
		setStatusCode("00E0000","idContato esta nulo");
		ULOG_END("implCttFrmRelaciona::Execute()");
		return;
	}
	if( strlennull( cidClassificadorCampoAtual ) <= 0 )
	{
		setStatusCode("00E0000","idClassificadorCampoAtual esta nulo");
		ULOG_END("implCttFrmRelaciona::Execute()");
		return;
	}
	if( strlennull( cidFaseProcessoAtual ) <= 0 )
	{
		setStatusCode("00E0000","idFaseProcessoAtual esta nulo");
		ULOG_END("implCttFrmRelaciona::Execute()");
		return;
	}
	
	int iUfo;
	int iLin;
	int iCampo;
	oContatoFolhaCampoExistentes.ListId( cidContato, cidFaseProcessoAtual, cidClassificadorCampoAtual );
	for( iUfo = 0;; iUfo++ )
	{
		cidUFOperadora = walkTree( dnode, "idUFOperadora", iUfo );
		if( strlennull( cidUFOperadora ) <= 0 )
			break;
		for( iLin = 0;; iLin++ )
		{
			cidTipoLinha = walkTree( dnode, "idTipoLinha", iLin );
			if( strlennull( cidTipoLinha ) <= 0 )
				break;
			for( iCampo = 0;; iCampo++ )
			{
				cidCampo = walkTree( dnode, "idCampo", iCampo );
				if( strlennull( cidCampo ) <= 0 )
					break;
				if( !oContatoFolhaCampoExistentes.Find( cidContato
													   ,cidUFOperadora
													   ,cidTipoLinha
													   ,cidCampo
													   ,cidFaseProcessoAtual ) )
				{
					oContatoFolhaCampoNovos.Insert( cidContato
												   ,cidUFOperadora
												   ,cidTipoLinha
												   ,cidCampo
												   ,"0"
												   ,cidFaseProcessoAtual
												   ,cidUser );
				}
				else
				{
					oContatoFolhaCampoNovos.AddExistente( cidContato
														 ,cidUFOperadora
														 ,cidTipoLinha
														 ,cidCampo
														 ,cidFaseProcessoAtual );
				}
			}//for( iCampo = 0;; iCampo++ )
		}//for( iLin = 0;; iLin++ )
	}//for( iUfo = 0;; iUfo++ )
	int iTipoErro = 0;
	for( iUfo = 0; iUfo < oContatoFolhaCampoExistentes.Quantidade(); iUfo++ )
	{
		if( oContatoFolhaCampoNovos.Quantidade() > 0 )
		{
			if( !oContatoFolhaCampoNovos.Find( oContatoFolhaCampoExistentes.Registro(iUfo)->cidContato
											  ,oContatoFolhaCampoExistentes.Registro(iUfo)->cidUFOperadora
											  ,oContatoFolhaCampoExistentes.Registro(iUfo)->cidTipoLinha
											  ,oContatoFolhaCampoExistentes.Registro(iUfo)->cidCampo
											  ,oContatoFolhaCampoExistentes.Registro(iUfo)->cidFaseProcesso ) )
			{
				if( oContatoFolhaCampo.Delete( 	oContatoFolhaCampoExistentes.Registro(iUfo)->cidContatoFolhaCampo ) == 2 )
					iTipoErro = 1;
			}
		}
		else
		{
		 	if( oContatoFolhaCampo.Delete( 	oContatoFolhaCampoExistentes.Registro(iUfo)->cidContatoFolhaCampo ) == 2 )
				iTipoErro = 1;
		}
	}
	if( iTipoErro )
		setStatusCode("00W0000","Alguns campos estao relacionados e nao podem ser apagados");
	else
		setStatusCode("00I0000","Operacao concluida com sucesso!");
	ULOG_END("implCttFrmRelaciona::Execute()");
}
