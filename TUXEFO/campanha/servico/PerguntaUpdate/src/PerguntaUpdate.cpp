#include <stdio.h>
#include <tuxfw.h>

/************************************************************************
 * Includes para as classes comuns
 ************************************************************************/
#include "../../negocio/CampanhaCmm/include/CPergunta.h"
#include "../../negocio/CampanhaCmm/include/CCampanhaQuestionario.h"

DECLARE_TUXEDO_SERVICE(UPDPERGUNTA);

void implUPDPERGUNTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	/************************************************************************
	 * Declara Variaveis
	 ************************************************************************/
	char* cidTipoApresentacaoPergunta = walkTree( dnode, "idApresentacao", 0 ); 
	char* cidPergunta                 = walkTree( dnode, "idPergunta"   , 0 ); 
	char* cdsPergunta                 = walkTree( dnode, "dsPergunta"   , 0 ); 
	char* cdsScriptPergunta           = walkTree( dnode, "dsScriptPergunta" , 0 ); 
	//char* csqApresentacao             = walkTree( dnode, "sqApresentacao"   , 0 ); 
	char* cinEncerramento             = walkTree( dnode, "inEncerramento", 0 ); 
	char* cinDisponibilidade          = walkTree( dnode, "inDisponibilidade", 0 ); 
	char* cinObrigatoria              = walkTree( dnode, "inObrigatoria", 0 ); 

		/************************************************************************
	 * Declara Estruturas
	 ************************************************************************/
	CPergunta oPergunta;
 
	CCampanhaQuestionario oCampanhaQuestionario;

	
	/************************************************************************
	 * Declara Ponteiros
	 ************************************************************************/
	
	/************************************************************************
	 * Verifica Ponteiros Nulos
	 ************************************************************************/
	if( strlen( cidTipoApresentacaoPergunta ) <= 0 )
	{
		setStatusCode("00E0000","idApresentacao esta nulo");
		return;
	}
	if( strlen( cidPergunta ) <= 0 )
	{
		setStatusCode("00E0000","idPergunta esta nulo");
		return;
	}
	if( strlen( cdsPergunta ) <= 0 )
	{
		setStatusCode("00E0000","dsPergunta esta nulo");
		return;
	}
	if( strlen( cdsScriptPergunta ) <= 0 )
	{
		setStatusCode("00E0000","dsScriptPergunta esta nulo");
		return;
	}
	//if( strlen( csqApresentacao ) <= 0 )
	//{
//		setStatusCode("00E0000","sqApresentacao esta nulo");
		//return;
	//}
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
	if( strlen( cinObrigatoria ) <= 0 )
	{
		setStatusCode("00E0000","inObrigatoria esta nulo");
		return;
	}

	/************************************************************************
	 * Inicializa Ponteiros
	 ************************************************************************/

	/************************************************************************
	 * Processamento Principal
	 ************************************************************************/
	if(oCampanhaQuestionario.ExistePergunta(dnode,cdsScriptPergunta,1,cidPergunta) > 0)
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
	else
	{
	/* Chamada de Funcao de Negocios */
	 oPergunta.Update( cidPergunta,
		                  cidTipoApresentacaoPergunta, 
					      cdsPergunta, 
					      cdsScriptPergunta, 
					      cinEncerramento,
					      cinDisponibilidade,
					      cinObrigatoria ); 

		
		
		xml_g->createTag("tns:retornoVO");
		xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
		xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
		xml_g->addItem("descricao","idPergunta");
		xml_g->addItem("valor", cidPergunta);
		xml_g->closeTag();
		setStatusCode("05I0000","Operacao concluida com sucesso!");	
	}

	

	/************************************************************************
	 * Desalocacao de Ponteiros
	 ************************************************************************/
}
