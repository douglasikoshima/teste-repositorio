#define ADVLALTERACPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CDom.h"

DECLARE_TUXEDO_SERVICE(ADVLALTERA);

void implADVLALTERA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADVLALTERA::Execute()");
	CSafePointer oSafePointer;
	CDominio oCDominio;
	char *cidDominio = NULL;
	char *cnmDominio = NULL;
	char *cinDisponibilidade = NULL;
	char *cidUFOperadora = NULL;
	char *cidTipoLinha = NULL;
	char *cidTabelaDominio = NULL;
	char *cidUser = getUser();
	xml_g->createTag("AdmDominiosVO");
	xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
	cidDominio = oSafePointer.getTag(dnode,"idDominio");
	cnmDominio = oSafePointer.getTag(dnode,"nmDominio");
	cinDisponibilidade = oSafePointer.getTag(dnode,"inDisponibilidade");
	cidUFOperadora = oSafePointer.getTag(dnode,"idUFOperadora");
	cidTipoLinha = oSafePointer.getTag(dnode,"idTipoLinha");
	cidTabelaDominio = oSafePointer.getTag(dnode,"idTabelaDominio");
	int sql = 0;
	int sql2 = 0;
	sql = oCDominio.Update(
			 cidDominio
			,cidTabelaDominio
			,cidUFOperadora
			,cidTipoLinha
			,cnmDominio
			,cinDisponibilidade
			,cidUser
		);
	switch(sql)
	{
		case 1:
		{
			sql2 = oCDominio.ListPar(cidDominio,
								cnmDominio,
								cinDisponibilidade ,
								cidUFOperadora,
								cidTipoLinha,
								cidTabelaDominio);
			if(sql2 > 0)
			{
				oCDominio.GetXmlListPar("AdmDominioVO",xml_g);
				setStatusCode("00I0000","Sucesso");
			}
			else
			{
				setStatusCode("00W0001","Registro não encontrado");
			}
			break;
		}
		case -2:
		{
			setStatusCode("00W0001","Parâmetro(s) de entrada inválido(s)");	
			break;
		}
		default:
			setStatusCode("00I0001","Problemas ao carregar dados");
			break;
	}
	xml_g->closeTag();
	ULOG_END("implADVLALTERA::Execute()");
}
