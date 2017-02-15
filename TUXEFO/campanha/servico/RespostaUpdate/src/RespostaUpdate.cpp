#include <stdio.h>
#include <tuxfw.h>

/************************************************************************
 * Includes para as classes comuns
 ************************************************************************/
#include "../../negocio/CampanhaCmm/include/CResposta.h"
#include "../../negocio/CampanhaCmm/include/CRspPxm.h"

DECLARE_TUXEDO_SERVICE(UPDRESPOSTA);

void implUPDRESPOSTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
  /************************************************************************
   * Declara Variaveis
   ************************************************************************/
  char* cidResposta        = walkTree( dnode, "idResposta", 0 ); 
  char* cidPergunta        = walkTree( dnode, "idPergunta", 0 ); 
  char* cdsResposta        = walkTree( dnode, "dsResposta", 0 ); 
  char* cdsScriptResposta  = walkTree( dnode, "dsScriptResposta" , 0 ); 
  char* csqApresentacao    = walkTree( dnode, "sqApresentacao", 0 ); 
  char* cinEncerramento    = walkTree( dnode, "inEncerramento", 0 ); 
  char* cinDisponibilidade = walkTree( dnode, "inStatus", 0 ); 
  char* cidProximaPergunta = walkTree( dnode, "idProximaPergunta", 0 ); 
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
  if( strlen( cidResposta ) <= 0 )
    {
      setStatusCode("00E0000","idResposta esta nulo");
      return;
    }
  if( strlen( cidPergunta ) <= 0 )
    {
      setStatusCode("00E0000","idPergunta esta nulo");
      return;
    }
  if( strlen( cdsResposta ) <= 0 )
    {
      setStatusCode("00E0000","dsResposta esta nulo");
      return;
    }
  if( strlen( cdsScriptResposta ) <= 0 )
    {
      setStatusCode("00E0000","dsScriptResposta esta nulo");
      return;
    }
  if( strlen( csqApresentacao ) <= 0 )
    {
      setStatusCode("00E0000","sqApresentacao esta nulo");
      return;
    }
  if( strlen( cinEncerramento ) <= 0 )
    {
      setStatusCode("00E0000","inEncerramento esta nulo");
      return;
    }
  if( strlen( cinDisponibilidade ) <= 0 )
    {
      setStatusCode("00E0000","inStatus esta nulo");
      return;
    }
  if( strlen( cidProximaPergunta ) <= 0 )
    {
      setStatusCode("00E0000","idProximaPergunta esta nulo");
      return;
    }

  /************************************************************************
   * Inicializa Ponteiros
   ************************************************************************/

  /************************************************************************
   * Processamento Principal
   ************************************************************************/
 int iRet=0;
  /* Chamada de Funcao de Negocios */
  iRet=oResposta.Update( cidResposta,
				         cidPergunta, 
						 cdsResposta, 
						 cdsScriptResposta, 
						 csqApresentacao,
						 cinEncerramento,
						 cinDisponibilidade ) ;
    
  if(iRet <=0)
  {
			xml_g->createTag("tns:retornoVO");
			xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
			xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

			xml_g->addItem("descricao","Ja existe Resposta Com esta descrição");
			xml_g->addItem("valor","-1");
			xml_g->closeTag();
			setStatusCode("05I0000","Resposta já cadastrada");
			return;
		
  }
  else
  {
			xml_g->createTag("tns:retornoVO");
			xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
			xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

			xml_g->addItem("descricao","Ja existe Resposta Com esta descrição");
			xml_g->addItem("valor",iRet);
			xml_g->closeTag();
			setStatusCode("05I0000","Resposta já cadastrada");
  
		setStatusCode("00I0000","Operacao concluida com sucesso!");
  }

  

  // if (id != zero) => atualizar proxima pergunta
  if ( atoi(cidProximaPergunta) > 0)
  {
    if( oRespostaProximaPergunta.UpdIns( cidResposta,
					 cidProximaPergunta,  
					 getUser() ) )
	{
      setStatusCode("00I0000","Operacao concluida com sucesso!");
	}
    else
	{
      
		setStatusCode("00E0000","Falha na Update de RespostaProximaPergunta");
	}
  }
  else 
  {
      if(oRespostaProximaPergunta.DelIdRespProximaPergunta(cidResposta,cidPergunta))
                {
                        setStatusCode("00I0000","Exclusao concluida com sucesso!");
                }
                else 
                {
                         setStatusCode("00E0000","Erro no UPDATE");
                }

  }
  /************************************************************************
   * Desalocacao de Ponteiros
   ************************************************************************/
}
