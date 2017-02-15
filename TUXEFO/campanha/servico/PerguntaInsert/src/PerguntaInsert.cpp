#include <stdio.h>
#include <stdlib.h>

/************************************************************************
 * Includes para as classes comuns
 ************************************************************************/
#include <tuxfw.h>
#include "../../negocio/CampanhaCmm/include/CPergunta.h"
#include "../../negocio/CampanhaCmm/include/CCampanhaQuestionario.h"

DECLARE_TUXEDO_SERVICE(INSPERGUNTA);

void implINSPERGUNTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	/************************************************************************
	* Declara Variaveis
	************************************************************************/
	char* cidTipoApresentacaoPergunta = walkTree( dnode, "idApresentacao", 0 ); 
	char* cdsPergunta                 = walkTree( dnode, "dsPergunta"   , 0 ); 
	char* cdsScriptPergunta           = walkTree( dnode, "dsScriptPergunta" , 0 ); 
	char* csqApresentacao             = walkTree( dnode, "sqApresentacao"   , 0 ); 
	char* cinEncerramento             = walkTree( dnode, "inEncerramento", 0 ); 
	char* cinDisponibilidade          = walkTree( dnode, "inDisponibilidade", 0 ); 
	char* cinObrigatoria              = walkTree( dnode, "inObrigatoria", 0 ); 
	char* cidCanalCampanha;
	char* cinAtivo;
	char* cidUser = getUser();
	int iNroObjCta = 0;
	DOMNode* pNoConta;
	DOMNode* dnCanalUtil;
	int	iCampanhaQuestionarioExistente = 0;
	
	/************************************************************************
	* Declara Estruturas
	************************************************************************/
	CPergunta oPergunta;
	CCampanhaQuestionario oCampanhaQuestionario;

	/************************************************************************
	* Declara Ponteiros
	************************************************************************/
	if(oCampanhaQuestionario.ExistePergunta(dnode,cdsScriptPergunta,0,"null") > 0)
	{
  		xml_g->createTag("tns:retornoVO");
		xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
		xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");

		xml_g->addItem("descricao","Já existe uma pergunta cadastrada nesta sub campanha com este mesmo script");
		xml_g->addItem("valor","-1");
		xml_g->closeTag();
		setStatusCode("05I0000","Script Pergunta já cadastrado");
		return;
	}
	/************************************************************************
	 * Verifica Ponteiros Nulos
	 ************************************************************************/
	if( strlen( cidTipoApresentacaoPergunta ) <= 0 )
	{
		setStatusCode("05E0000","idApresentacao esta nulo");
		return;
	}
	if( strlen( cdsPergunta ) <= 0 )
	{
		setStatusCode("05E0000","dsPergunta esta nulo");
		return;
	}
	if( strlen( cdsScriptPergunta ) <= 0 )
	{
		setStatusCode("05E0000","dsScriptPergunta esta nulo");
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
	if( strlen( cinObrigatoria ) <= 0 )
	{
		setStatusCode("05E0000","inObrigatoria esta nulo");
		return;
	}

	/************************************************************************
	 * Inicializa Ponteiros
	 ************************************************************************/

	/************************************************************************
	 * Processamento Principal
	 ************************************************************************/

	/* Insere uma pergunta, sem retorno, caso de erro causa uma exception */
	oPergunta.Insert( 
                          cidTipoApresentacaoPergunta, 
					      cdsPergunta, 
					      cdsScriptPergunta, 
					      csqApresentacao,
					      cinEncerramento,
					      cinDisponibilidade,
					      cinObrigatoria 
				     );

	xml_g->createTag("tns:retornoVO");
	xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	/************************************************************************
	* Processamento secundario
	************************************************************************/
	int iMaxCanal=0;
	int iMaxCanalUtil=0;
	dnCanalUtil = walkDOM(dnode, "CanalUtil", 0);
	if( dnCanalUtil != NULL )
	{
		for(iNroObjCta = 0; (pNoConta = walkDOM(dnCanalUtil, "ApoioCanalVO", iNroObjCta)) != NULL ; iNroObjCta++)
		{
			cidCanalCampanha = walkTree( pNoConta, "idCanalCampanha", 0 ); 
			cinAtivo		 = walkTree( pNoConta, "cnlcmpinAtivo", 0 );                 

			if( strlen( cidCanalCampanha ) <= 0 )
			{
				setStatusCode("05E0000","cidCanalCampanha esta nulo");
				return;
			}

			if( strlen( cinAtivo ) <= 0 )
			{
				setStatusCode("05E0000","cinAtivo esta nulo");
				return;
			}

			ULOG("Log de cidCanalCampanha:  :%s ",cidCanalCampanha);
			ULOG("Log de cidPergunta:  :%s ",oPergunta.Registro(0)->cidPergunta);

			if(! oCampanhaQuestionario.ExistePerguntaEnCanal( 
			                                                  cidCanalCampanha,
			                                                  oPergunta.Registro(0)->cidPergunta 
			                                                ) 
			  )
			{
				/* Chamada de Funcao de Negocios */
				oCampanhaQuestionario.Insert(
                                               cidCanalCampanha, 
                                               oPergunta.Registro(0)->cidPergunta, 
                                               cidUser 
                                             ) ;
				iMaxCanalUtil=oPergunta.GetMaxSQAPresentacao(cidCanalCampanha);
				
				iMaxCanal=(iMaxCanalUtil>iMaxCanal)?iMaxCanalUtil:iMaxCanal;
				


			}//if(! oCampanhaQuestionario.ExistePerguntaEnCanal(...)
			else
			{
				iCampanhaQuestionarioExistente = 1;
			}//else if(! oCampanhaQuestionario.ExistePerguntaEnCanal(...)

			char buf[21];
			sprintf( buf, "%d", iMaxCanal );  
			oPergunta.UpdateSQApresentacao( oPergunta.Registro(0)->cidPergunta,buf);
		}//for(iNroObjCta = 0; (pNoConta = walkDOM(dnCanalUtil, "ApoioCanalVO", iNroObjCta)) != NULL; iNroObjCta++)

		if( iCampanhaQuestionarioExistente )
		{
			xml_g->addItem("descricao","Script Pergunta já cadastrado na CampanhaQuestionario");
			xml_g->addItem("valor","-1");
			setStatusCode("05I0000","Script Pergunta já cadastrado na CampanhaQuestionario");
		}
		else
		{
			xml_g->addItem("descricao","idPergunta");
			xml_g->addItem("valor", oPergunta.Registro(0)->cidPergunta);
			setStatusCode("05I0000","Operacao concluida com sucesso!");
		}
	}//if( dnCanalUtil != NULL )
	else
	{
		setStatusCode("05E0000","Está faltando a tag <CanalUtil>");
	}//else if( dnCanalUtil != NULL )

	xml_g->closeTag();
		
	/************************************************************************
	 * Desalocacao de Ponteiros
	 ************************************************************************/
}
