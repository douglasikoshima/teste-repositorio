#include <stdio.h>
#include <stdlib.h>
#include <tuxfw.h>
// #include <XMLImpl.h>

	/************************************************************************
	 * Includes para as classes comuns
	 ************************************************************************/
#include "../../negocio/CampanhaCmm/include/CSubCampanhaHistorico.h"
#define ULOG tuxfw_getlogger()->debug

DECLARE_TUXEDO_SERVICE(UPDSUBCMPHISTO);

void implUPDSUBCMPHISTO::Execute(DOMNode*dnode,XMLGen*xml_g)
{
	/************************************************************************
	 * Declara Variaveis
	 ************************************************************************/
	char* idSubcampanhaHistorico;
    char* sqApresentacao;
    char * cidUser = getUser();
    int iSubno = 0;
    int iNroObjCta = 0;
    DOMNode *pNoConta;        
	int	iResult = 0;
	
	/************************************************************************
	 * Declara Estruturas
	 ************************************************************************/
	CSubCampanhaHistorico oSubCampanhaHistorico;

	/************************************************************************
	 * Processamento Principal
	 ************************************************************************/

	iNroObjCta = 0;
	dnode = walkDOM(dnode, "CanalUtil", iNroObjCta);
	for(iNroObjCta = 0; (pNoConta = walkDOM(dnode, "ApoioCanalVO", &iSubno, iNroObjCta)) ; iSubno=0, iNroObjCta++)
	{
		idSubcampanhaHistorico	= walkTree( dnode, "idSubcampanhaHistorico", iNroObjCta ); 
		sqApresentacao			= walkTree( dnode, "sqApresentacao", iNroObjCta );                 
		/************************************************************************
		* Verifica Ponteiros Nulos
		************************************************************************/    
		if( strlen( idSubcampanhaHistorico ) <= 0 )
		{
			setStatusCode("05E0000","idSubcampanhaHistorico esta nulo");
			return;
		}

		if( strlen( sqApresentacao ) <= 0 )
		{
			setStatusCode("05E0000","sqApresentacao esta nulo");
			return;
		}

		ULOG("Log de idSubcampanhaHistorico:  :%s ",idSubcampanhaHistorico);
		ULOG("Log de sqApresentacao:  :%s ",sqApresentacao);

		/* Chamada de Funcao de Negocios */
		iResult = oSubCampanhaHistorico.Update(	idSubcampanhaHistorico, 
												sqApresentacao, 
												cidUser ) ;
		if( iResult > 0)
		{
			setStatusCode("05I0000","Operacao concluida com sucesso!");
		}       	
		else 
		{
			setStatusCode("05E0000","Falha na atualizacao de SubCampanhaHistorico");
			return;
		}
		
		idSubcampanhaHistorico = 0;
		sqApresentacao=0;
	}

	setStatusCode("05I0000","Operacao concluida com sucesso!");

	xml_g->createTag("tns:retornoVO");
	xml_g->addProp("xmlns:tns","retornotux.fo.vivo.com.br/vo");
	xml_g->addProp("xmlns:xsi","http://www.w3.org/2001/XMLSchema-instance");
	xml_g->addItem("valor", idSubcampanhaHistorico);
	xml_g->closeTag();
	setStatusCode("05I0000","SubcampanhaHistoricoScript atualizado");

	/************************************************************************
	 * Desalocacao de Ponteiros
	 ************************************************************************/
}
