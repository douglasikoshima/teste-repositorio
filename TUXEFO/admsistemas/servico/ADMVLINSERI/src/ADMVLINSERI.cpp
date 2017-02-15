#define ADMVLINSERICPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CDom.h"

DECLARE_TUXEDO_SERVICE(ADMVLINSERI);

void implADMVLINSERI::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMVLINSERI::Execute()");
	CSafePointer oSafePointer;
	CDominio oCDominio;
	char *cnmDominio = NULL;
	char *cinDisponibilidade = NULL;
	char *cidUser = getUser();
	char *cidUFOperadora = NULL;
	char *cidTipoLinha = NULL;
	char *cidTabelaDominio = NULL;
	int sql = 0;
	xml_g->createTag("AdmDominiosVO");
	xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
	cidTabelaDominio = oSafePointer.getTag(dnode,"idTabelaDominio");
	cnmDominio = oSafePointer.getTag(dnode,"nmDominio");
	cinDisponibilidade = oSafePointer.getTag(dnode,"inDisponibilidade");
	cidUFOperadora = oSafePointer.getTag(dnode,"operadoraArray");
	cidTipoLinha = oSafePointer.getTag(dnode,"tipoLinhaArray");
	if(cidUFOperadora == NULL || cidTipoLinha == NULL)
	{
		setStatusCode("00W0001","Parâmetro(s) de entrada inválido(s)");
		xml_g->closeTag();
	}
	for(int i=0;;i++)
	{
		cidUFOperadora = oSafePointer.getTag(dnode,"operadoraArray",i);			
		if(cidUFOperadora != NULL)
		{
			for(int j=0;;j++)
			{				
				cidTipoLinha = oSafePointer.getTag(dnode,"tipoLinhaArray",j);
				if(cidTipoLinha != NULL)
				{
					sql = oCDominio.Insert(
							 cidTabelaDominio
							,cidUFOperadora
							,cidTipoLinha
							,cnmDominio
							,cinDisponibilidade
							,cidUser
							);
					if(sql < 0)
					{
						setStatusCode("00W0002","Registro duplicado");
						xml_g->closeTag();
						ULOG_END("implADMVLINSERI::Execute()");
						return;
					}
				}
				else
				{
					break;
				}
			}			
		}
		else
		{
			break;
		}
	}
	sql = oCDominio.ListAllById();
	if(sql >= 0)
	{
		oCDominio.GetXmlListPar("AdmDominioVO",xml_g);
		setStatusCode("00I0000","Sucesso");
	}
	else
	{
		setStatusCode("00I0001","Problemas ao Listar dados");
	}
	xml_g->closeTag();
	ULOG_END("implADMVLINSERI::Execute()");
}
