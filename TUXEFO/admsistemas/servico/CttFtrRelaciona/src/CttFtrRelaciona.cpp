/*****************************************************************************
 *
 * Modulo:    CttFtrRelaciona
 * Arquivo:   CttFtrRelaciona.cpp
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
#define CttFtrRelacionaCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CCttFtr.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(CttFtrRelaciona);

/**************************************************************************
 *  Funcao de Negocios:  CttFtrRelaciona
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
void implCttFtrRelaciona::Execute(DOMNode*dnode,XMLGen*xml_g)
{
   ULOG_START("implCttFtrRelaciona::Execute()");
	/* Chamada de Funcao de Negocios */
	CContatoFiltro oContatoFiltro;
	char* cidContato = walkTree( dnode, "idContato", 0 );
	char* cidFiltro;
	char* cidUser = getUser();
	DOMNode* dnNode;
	int iCont;

	if( strlennull( cidContato ) > 0 )
	{
		dnNode = walkDOM( dnode, "tipoPessoaAssociada", 0 );
		if( dnNode != NULL )
		{
			oContatoFiltro.DeleteTipoRelacionamentoIdContato( cidContato );
			for( iCont = 0;; iCont++ )
			{
				cidFiltro = walkTree( dnNode, "idTipoPessoa", iCont );
				if( strlennull( cidFiltro ) <= 0 )
					break;
				oContatoFiltro.RelacionaTipoRelacionamento( cidContato
					                                       ,cidFiltro
												           ,cidUser );
			}
			dnNode = walkDOM( dnode, "tipoLinhaAssociada", 0 );
			if( dnNode != NULL )
			{
				oContatoFiltro.DeleteTipoLinhaIdContato( cidContato );
				for( iCont = 0;; iCont++ )
				{
					cidFiltro = walkTree( dnNode, "idTipoLinha", iCont );
					if( strlennull( cidFiltro ) <= 0 )
						break;
					oContatoFiltro.RelacionaTipoLinha( cidContato
													  ,cidFiltro
													  ,cidUser );
				}
				dnNode = walkDOM( dnode, "segmentacaoAssociada", 0 );
				if( dnNode != NULL )
				{
					oContatoFiltro.DeleteSegmentacaoIdContato( cidContato );
					for( iCont = 0;; iCont++ )
					{
						cidFiltro = walkTree( dnNode, "idSegmentacao", iCont );
						if( strlennull( cidFiltro ) <= 0 )
							break;
						oContatoFiltro.RelacionaSegmentacao( cidContato
														    ,cidFiltro
														    ,cidUser );
					}
					dnNode = walkDOM( dnode, "tipoCarteiraAssociada", 0 );
					if( dnNode != NULL )
					{
						oContatoFiltro.DeleteTipoCarteiraIdContato( cidContato );
						for( iCont = 0;; iCont++ )
						{
							cidFiltro = walkTree( dnNode, "idTipoCarteira", iCont );
							if( strlennull( cidFiltro ) <= 0 )
								break;
							oContatoFiltro.RelacionaTipoCarteira( cidContato
															     ,cidFiltro
															     ,cidUser );
						}
						oContatoFiltro.Relacao( cidContato, xml_g );
						setStatusCode("00I0000","Operacao concluida com sucesso!");
					}
					else
						setStatusCode("00E0000","Tag [tipoCarteiraAssociada] esta faltando");
				}
				else
					setStatusCode("00E0000","Tag [segmentacaoAssociada] esta faltando");
			}
			else
				setStatusCode("00E0000","Tag [tipoLinhaAssociada] esta faltando");
		}
		else
			setStatusCode("00E0000","Tag [tipoPessoaAssociada] esta faltando");
	}
	else
		setStatusCode("00E0000","idContato esta nulo");
		
	ULOG_END("implCttFtrRelaciona::Execute()");
}
