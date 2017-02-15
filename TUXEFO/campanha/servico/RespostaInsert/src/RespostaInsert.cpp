#include <stdio.h>
#include <tuxfw.h>

/************************************************************************
 * Includes para as classes comuns
 ************************************************************************/
#include "../../negocio/CampanhaCmm/include/CResposta.h"
#include "../../negocio/CampanhaCmm/include/CRspPxm.h"

DECLARE_TUXEDO_SERVICE(INSRESPOSTA);

void implINSRESPOSTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  /************************************************************************
   * Declara Variaveis
   ************************************************************************/
	int   idResp;
	char* cidPergunta        = walkTree( dnode, "idPergunta", 0 ); 
	char* cdsResposta        = walkTree( dnode, "dsResposta"   , 0 ); 
	char* cdsScriptResposta  = walkTree( dnode, "dsScriptResposta" , 0 ); 
	char* csqApresentacao    = walkTree( dnode, "sqApresentacao"   , 0 ); 
	char* cinEncerramento    = walkTree( dnode, "inEncerramento", 0 ); 
	char* cinDisponibilidade = walkTree( dnode, "inStatus", 0 ); 
	char* cidProximaPergunta = walkTree( dnode, "idProximaPergunta", 0 ); 
	char  cidResposta[50];
  /************************************************************************
   * Declara Estruturas
   ************************************************************************/
	CResposta oResposta;
	CRespostaProximaPergunta oRespostaProximaPergunta;

  /************************************************************************
   * Declara Ponteiros
   ************************************************************************/

  /************************************************************************
   * Verifica Ponteiros Nulos
   ************************************************************************/
	if( strlen( cidPergunta ) <= 0 )
    {
      setStatusCode("05E0000","idPergunta esta nulo");
      return;
    }
	if( strlen( cdsResposta ) <= 0 )
    {
      setStatusCode("05E0000","dsResposta esta nulo");
      return;
    }
	if( strlen( cdsScriptResposta ) <= 0 )
    {
      setStatusCode("05E0000","dsScriptResposta esta nulo");
      return;
    }
	if( strlen( csqApresentacao ) <= 0 )
    {
      setStatusCode("05E0000","sqApresentacao esta nulo");
      return;
    }
	if( strlen( cinEncerramento ) <= 0 )
    {
      setStatusCode("05E0000","inEncerramento esta nulo");
      return;
    }
	if( strlen( cinDisponibilidade ) <= 0 )
    {
      setStatusCode("05E0000","inStatus esta nulo");
      return;
    }
	if( strlen( cidProximaPergunta ) <= 0 )
    {
      setStatusCode("05E0000","idProximaPergunta esta nulo");
      return;
    }

  /************************************************************************
   * Inicializa Ponteiros
   ************************************************************************/

  /************************************************************************
   * Processamento Principal
   ************************************************************************/

  /* Chamada de Funcao de Negocios */
  idResp = oResposta.Insert( cidPergunta, 
			     cdsResposta, 
			     cdsScriptResposta, 
			     csqApresentacao,
			     cinEncerramento,
			     cinDisponibilidade );

	if( idResp > 0)
	{
		setStatusCode("05I0000","Operacao concluida com sucesso!");
		// Inserindo proxima pergunta
			if (atoi(cidProximaPergunta) != 0)
			{
				// Convertendo idResposta
				sprintf(cidResposta,"%d",idResp);

				if( oRespostaProximaPergunta.UpdIns( cidResposta, 
							                		 cidProximaPergunta, 
													 getUser() ) )
					{
						setStatusCode("05I0000","Operacao concluida com sucesso!");
					}
				else
				{
					setStatusCode("05E0000","Falha na insercao de RespostaProximaPergunta");
					return;
				}

			}
			else
			{
					setStatusCode("05I0000","Insercao concluida com sucesso!");
			}	

	}
	else
	{
	
			
			xml_g->createTag("tns:retornoVO");
			xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
			xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

			xml_g->addItem("descricao","Script Resposta já cadastrado");
			xml_g->addItem("valor","-1");
			xml_g->closeTag();
			setStatusCode("05I0000","Script Resposta já cadastrado");
			return;
		

	}
		

 
	
	xml_g->createTag("tns:retornoVO");
	xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

	xml_g->addItem("descricao","idResposta");
	xml_g->addItem("valor",idResp);
	xml_g->closeTag();

	setStatusCode("05I0000","Script Resposta já cadastrado");

  /************************************************************************
   * Desalocacao de Ponteiros
   ************************************************************************/
}
