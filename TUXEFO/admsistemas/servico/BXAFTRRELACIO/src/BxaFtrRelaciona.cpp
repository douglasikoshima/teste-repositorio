/*****************************************************************************
 *
 * Modulo:    BxaFtrRelaciona
 * Arquivo:   BxaFtrRelaciona.cpp
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
#define BxaFtrRelacionaCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CBxaFtr.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(BXAFTRRELACIO);

/**************************************************************************
 *  Funcao de Negocios:  BxaFtrRelaciona
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
void implBXAFTRRELACIO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implBXAFTRRELACIO::Execute()");
	/* Chamada de Funcao de Negocios */
	CSafePointer oSafePointer;
	CBaixaFiltro oBaixaFiltro;
	char* cidBaixa = oSafePointer.getTag( dnode, "idBaixa", 0 );
	char* cidFiltro;
	char* cidUser = getUser();
	DOMNode* dnNode;
	int iCont;
	int iPrePros = 0;
	//int iNaoClassificado;
	int iProspectNaoCliente = 0;
	int iClienteUsuario = 0;

	if( strlennull( cidBaixa ) > 0 )
	{
		//Verificando:
		//Linha não classificado soh pode ser associado com nao cliente e prospect
		//Linha pre e pos soh podem serem assiciadas com cliente e usuario
/*
		dnNode = walkDOM( dnode, "tipoLinhaAssociada", 0 );
		if( dnNode != NULL )
		{
			for( iCont = 0;; iCont++ )
			{
				cidFiltro = oSafePointer.getTag( dnNode, "idTipoLinha", iCont );
				if( strlennull( cidFiltro ) <= 0 )
					break;
				if( oBaixaFiltro.ListIdTipoLinha( cidFiltro ) > 0 )
				{
					if( ( strcmp( oBaixaFiltro.Registro()->csgFiltro, "POS" ) == 0 )
					  ||( strcmp( oBaixaFiltro.Registro()->csgFiltro, "PRÉ" ) == 0 ) )
						iPrePros = 1;

					if( strcmp( oBaixaFiltro.Registro()->csgFiltro, "NC" ) == 0 )
						iNaoClassificado = 1;
				}
			}
		}
		dnNode = walkDOM( dnode, "tipoPessoaAssociada", 0 );
		if( dnNode != NULL )
		{
			oBaixaFiltro.DeleteTipoRelacionamentoIdBaixa( cidBaixa );
			for( iCont = 0;; iCont++ )
			{
				cidFiltro = oSafePointer.getTag( dnNode, "idTipoPessoa", iCont );
				if( strlennull( cidFiltro ) <= 0 )
					break;
				if( oBaixaFiltro.ListIdTipoRelacionamento( cidFiltro ) > 0 )
				{
					if( ( strcmp( oBaixaFiltro.Registro()->csgFiltro, "P" ) == 0 )
					  ||( strcmp( oBaixaFiltro.Registro()->csgFiltro, "NC" ) == 0 ) )
						iProspectNaoCliente = 1;
					if( ( strcmp( oBaixaFiltro.Registro()->csgFiltro, "C" ) == 0 )
					  ||( strcmp( oBaixaFiltro.Registro()->csgFiltro, "U" ) == 0 ) )
						iClienteUsuario = 1;
				}
			}
		}
		if( iProspectNaoCliente && iPrePros )
		{
			setStatusCode("14W0001","Prospect e Não cliente não podem ser associados com linhas Pré ou Pós.");
			return;
		}
		if( iClienteUsuario && iNaoClassificado )
		{
			setStatusCode("14W0002","Cliente e Usuário não podem ser associados com linhas Não Classificadas");
			return;
		}
*/
		//Inicio da Gravacao		
		dnNode = walkDOM( dnode, "tipoPessoaAssociada", 0 );
		if( dnNode != NULL )
		{
			oBaixaFiltro.DeleteTipoRelacionamentoIdBaixa( cidBaixa );
			for( iCont = 0;; iCont++ )
			{
				cidFiltro = oSafePointer.getTag( dnNode, "idTipoPessoa", iCont );
				if( strlennull( cidFiltro ) <= 0 )
					break;
				oBaixaFiltro.RelacionaTipoRelacionamento( cidBaixa
					                                       ,cidFiltro
												           ,cidUser );
			}
			dnNode = walkDOM( dnode, "tipoLinhaAssociada", 0 );
			if( dnNode != NULL )
			{
				oBaixaFiltro.DeleteTipoLinhaIdBaixa( cidBaixa );
				for( iCont = 0;; iCont++ )
				{
					cidFiltro = oSafePointer.getTag( dnNode, "idTipoLinha", iCont );
					if( strlennull( cidFiltro ) <= 0 )
						break;
					oBaixaFiltro.RelacionaTipoLinha( cidBaixa
													  ,cidFiltro
													  ,cidUser );
				}
				dnNode = walkDOM( dnode, "segmentacaoAssociada", 0 );
				if( dnNode != NULL )
				{
					oBaixaFiltro.DeleteSegmentacaoIdBaixa( cidBaixa );
					for( iCont = 0;; iCont++ )
					{
						cidFiltro = oSafePointer.getTag( dnNode, "idSegmentacao", iCont );
						if( strlennull( cidFiltro ) <= 0 )
							break;
						oBaixaFiltro.RelacionaSegmentacao( cidBaixa
														    ,cidFiltro
														    ,cidUser );
					}
					dnNode = walkDOM( dnode, "tipoCarteiraAssociada", 0 );
					if( dnNode != NULL )
					{
						oBaixaFiltro.DeleteTipoCarteiraIdBaixa( cidBaixa );
						for( iCont = 0;; iCont++ )
						{
							cidFiltro = oSafePointer.getTag( dnNode, "idTipoCarteira", iCont );
							if( strlennull( cidFiltro ) <= 0 )
								break;
							oBaixaFiltro.RelacionaTipoCarteira( cidBaixa
															     ,cidFiltro
															     ,cidUser );
						}
						oBaixaFiltro.Relacao( cidBaixa, xml_g );
						setStatusCode("14I0000","Operacao concluida com sucesso!");
					}
					else
						setStatusCode("14E0001","Tag [tipoCarteiraAssociada] está faltando");
				}
				else
					setStatusCode("14E0002","Tag [segmentacaoAssociada] está faltando");
			}
			else
				setStatusCode("14E0003","Tag [tipoLinhaAssociada] está faltando");
		}
		else
			setStatusCode("14E0004","Tag [tipoPessoaAssociada] está faltando");
	}
	else
		setStatusCode("14E0005","idBaixa está nulo");
	ULOG_END("implBXAFTRRELACIO::Execute()");
}
