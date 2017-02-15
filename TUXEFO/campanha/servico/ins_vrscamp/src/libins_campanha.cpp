///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  campanha
 * @usecase INSVRSCAMP
 * @author  Eder Jani Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:57 $
 **/
///////////////////////////////////////////////////////////////////////////////


//Definicao Global
#define INSVRSCAMPCPP

//Header de Srv de Infra-Estrutura
#include<tuxfw.h>
#include "../../negocio/CampanhaCmm/include/CMotivoCampanha.h"
#include "../../negocio/CampanhaCmm/include/CSubCampanhaHistorico.h"
#include "../../negocio/CampanhaCmm/include/CCanalCampanha.h"
#include "../../negocio/CampanhaCmm/include/CListaCanalCampanha.h"
#include "../../negocio/CampanhaCmm/include/CCampanhaQuestionario.h"

//Macro de Definicao do Framework Tuxedo
DECLARE_TUXEDO_SERVICE(INSVRSCAMP);

/**************************************************************************
 *  Funcao de Negocios:  INSVRSCAMP
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
void implINSVRSCAMP::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	/* Chamada de Funcao de Negocios */
	CMotivoCampanha oMotivoCampanhaNew;
	CMotivoCampanha oMotivoCampanhaOld;
	CSubCampanhaHistorico oSubCampanhaHistorico;
	CCanalCampanha oCanalCampanhaOld;
	CCanalCampanha oCanalCampanhaNew;
	CListaCanalCampanha oListaCanalCampanhaNew;
	CListaCanalCampanha oListaCanalCampanhaOld;
	CCampanhaQuestionario oCampanhaQuestionarioNew;
	CCampanhaQuestionario oCampanhaQuestionarioOld;

	int iCont;
	int iCont2;
	int iCont3;

	char* cidSubCampanhaHistoricoOrigem = walkTree( dnode, "idSubCampanhaHistoricoOrigem", 0 );
	char* cidUser = getUser();

	if( strlennull( cidSubCampanhaHistoricoOrigem ) <= 0 )
	{
		setStatusCode("05E0001","idSubCampanhaHistoricoOrigem está nulo");
		return;
	}
	
	if( oSubCampanhaHistorico.ListId( cidSubCampanhaHistoricoOrigem ) > 0 )
	{
		//Duplica a subcampanhahistorico, atualiza a versao e armazena o novo registro
		//na estrutura interna da classe
		oSubCampanhaHistorico.Duplicar( cidSubCampanhaHistoricoOrigem,
										cidUser );

		//Recupera todos os motivos antigos e insere para o registro novo
		oMotivoCampanhaOld.ListPorSubCampanhaHistorico( cidSubCampanhaHistoricoOrigem );
		for( iCont = 0; iCont < oMotivoCampanhaOld.Quantidade(); iCont++ )
		{
			oMotivoCampanhaNew.Insert(  oSubCampanhaHistorico.Registro()->cidSubCampanhaHistorico,
										oMotivoCampanhaOld.Registro(iCont)->cidTipoStatusCampanha,
										oMotivoCampanhaOld.Registro(iCont)->cidTipoMotivoCampanha,
										oMotivoCampanhaOld.Registro(iCont)->cidTipoSubMotivoCampanha,
										oMotivoCampanhaOld.Registro(iCont)->cinAtivo,
										cidUser );
		}//for( iCont = 0; oMotivoCampanhaOld.Quantidade(); iCont++ )

		//Recupera canalcampanha
		oCanalCampanhaOld.ListId( cidSubCampanhaHistoricoOrigem );
		//Faz a copia para a nova campanha
		for( iCont = 0; iCont < oCanalCampanhaOld.Quantidade(); iCont++ )
		{
			//Efetivamente realiza a copia (Salva os novos registro na estrutura intena de oCanalCampanhaNew )
			oCanalCampanhaNew.Insert( oSubCampanhaHistorico.Registro()->cidSubCampanhaHistorico,
									  oCanalCampanhaOld.Registro(iCont)->cidCanalUFOperadora,
									  oCanalCampanhaOld.Registro(iCont)->csqApresentacao,
									  oCanalCampanhaOld.Registro(iCont)->cinDisponibilidade,
									  oCanalCampanhaOld.Registro(iCont)->cinAtivo,
									  cidUser );
			//Copia a parametrizacao de antigo para o novo
			oCanalCampanhaNew.CopiaParametrizacao( oCanalCampanhaOld.Registro(iCont)->cidCanalCampanha,
				                                   oCanalCampanhaNew.Registro(iCont)->cidCanalCampanha,
												   cidUser );

			//Recupera todos os questionarios do antivo CanalCampanha
			oCampanhaQuestionarioOld.ListIdCanalCampanha( oCanalCampanhaOld.Registro(iCont)->cidCanalCampanha );
			for( iCont2 = 0;iCont2 < oCampanhaQuestionarioOld.Quantidade(); iCont2++ )
			{
				//Insere, para cada registro existente, uma copia do questionario
				oCampanhaQuestionarioNew.Insert( oCanalCampanhaNew.Registro(iCont)->cidCanalCampanha, 
												 oCampanhaQuestionarioOld.Registro(iCont2)->cidPergunta,
												 oCampanhaQuestionarioOld.Registro(iCont2)->cinAtivo,
												 cidUser );
			}//for( iCont2 = 0; oCampanhaQuestionarioOld.Quantidade(); iCont2++ )
			//Recupera todos as listas do antivo CanalCampanha
			oListaCanalCampanhaOld.ListId( oCanalCampanhaOld.Registro(iCont)->cidCanalCampanha );
			for( iCont3 = 0; iCont3 < oListaCanalCampanhaOld.Quantidade(); iCont3++ )
			{
				//Insere, para cada registro existente, uma copia da lista
				oListaCanalCampanhaNew.Insert(  oListaCanalCampanhaOld.Registro(iCont3)->cidLista,
												oCanalCampanhaNew.Registro(iCont)->cidCanalCampanha, 
												oListaCanalCampanhaOld.Registro(iCont3)->cinAtivo,
												cidUser );
			}//for( iCont2 = 0; oListaCanalCampanhaOld.Quantidade(); iCont2++ )
		}//for( iCont = 0; oCanalCampanhaOld.Quantidade(); iCont++ )
	


		xml_g->createTag("tns:retornoVO");
			xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
			xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
			xml_g->addItem("descricao","idsubcampanhahistorico");
			xml_g->addItem("valor",oSubCampanhaHistorico.Registro()->cidSubCampanhaHistorico);
		xml_g->closeTag();
		setStatusCode("05I0000", "Operação realizada com sucesso" );
	}//if( oSubCampanhaHistorico.ListId( cidSubCampanhaHistoricoOrigem ) < 0 )
	else
	{
		setStatusCode("05E0002", "idSubCampanhaHistoricoOrigem não foi encontrado na base" );
	}//else if( oSubCampanhaHistorico.ListId( cidSubCampanhaHistoricoOrigem ) < 0 )
}
