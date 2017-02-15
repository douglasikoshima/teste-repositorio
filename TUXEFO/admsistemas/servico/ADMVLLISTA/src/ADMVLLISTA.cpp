#define ADMVLLISTACPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CDom.h"

DECLARE_TUXEDO_SERVICE(ADMVLLISTA);

void implADMVLLISTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMVLLISTA::Execute()");
	CSafePointer oSafePointer;
	CDominio oCDominio;
	char *cidDominio = NULL;
	char *cnmDominio = NULL;
	char *cinDisponibilidade = NULL;
	char *cidUFOperadora = NULL;
	char *cidTipoLinha = NULL;
	char *cidTabelaDominio = NULL;
	xml_g->createTag("AdmDominiosVO");
	xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
	cidDominio = oSafePointer.getTag(dnode,"idDominio");
	cnmDominio = oSafePointer.getTag(dnode,"nmDominio");
	cinDisponibilidade = oSafePointer.getTag(dnode,"inDisponibilidade");
	cidUFOperadora = oSafePointer.getTag(dnode,"idUFOperadora");
	cidTipoLinha = oSafePointer.getTag(dnode,"idTipoLinha");
	cidTabelaDominio = oSafePointer.getTag(dnode,"idTabelaDominio");
	int sql = 0;
	sql = oCDominio.ListPar(cidDominio,
		cnmDominio,
		cinDisponibilidade,
		cidUFOperadora,
		cidTipoLinha,
		cidTabelaDominio);
	if(sql > 0)
	{
		oCDominio.GetXmlListPar("AdmDominioVO",xml_g);
		setStatusCode("00I0000","Sucesso");
	}
	else
	{
		setStatusCode("00I0000","Nenhum registro encontrado");
	}	
	xml_g->closeTag();
	ULOG_END("implADMVLLISTA::Execute()");
}
