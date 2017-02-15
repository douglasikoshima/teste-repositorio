#define ADMDOMPRAINCCPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCmpDom.h"
#include "../../../negocio/admatdCmm/include/CCmp.h"

DECLARE_TUXEDO_SERVICE(ADMDOMPRAINC);

void implADMDOMPRAINC::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMDOMPRAINC::Execute()");
   
	CSafePointer oSafePointer;
	CCampo oCCampo;
	CCampoDominio oCCampoDominio;	
	char* cidCampo = NULL;
	char* cdsQuery = NULL;
	char* cnmAtributoIdentificador = NULL;
	char* cnmAtributoDescritivo = NULL;
	char* cidTabelaDominio = NULL;
	char* cidUser = getUser();
	int sql = 0;
	xml_g->createTag("AdmCamposContatoVO");
	xml_g->addProp( "xmlns", "admsistemas.fo.vivo.com.br/vo" );	
	cidCampo = oSafePointer.getTag(dnode,"idCampo");
	cdsQuery = oSafePointer.getTag(dnode,"dsQuery");
	cnmAtributoIdentificador = oSafePointer.getTag(dnode,"nmAtributoIdentificador");
	cnmAtributoDescritivo = oSafePointer.getTag(dnode,"nmAtributoDescritivo");
	cidTabelaDominio = oSafePointer.getTag(dnode,"idTabelaDominio");		
	sql = oCCampoDominio.Insert(cidCampo,
		cdsQuery,
		cnmAtributoIdentificador,
		cnmAtributoDescritivo,
		cidTabelaDominio,cidUser);

	if(sql == 0)
	{
		if(oCCampo.ListPar(cidCampo) > 0)
		{
			oCCampo.GetXmlListAll("AdmCampoContatoVO",xml_g);
		}
		setStatusCode("00I0000","Sucesso");
	}
	else
	if(sql == -1)
	{
		if(oCCampoDominio.Update(cidCampo,
						cdsQuery,
						cnmAtributoIdentificador,
						cnmAtributoDescritivo,
						cidTabelaDominio,cidUser))
		{
			if(oCCampo.ListPar(cidCampo) > 0)
			{
				oCCampo.GetXmlListAll("AdmCampoContatoVO",xml_g);
			}
			setStatusCode("00I0000","Sucesso");
		}
		else
		{
			setStatusCode("00W0005","Não foi possível atualizar dado(s)");
		}
	}
	else
	if(sql == -2)
	{
		setStatusCode("00W0004","Registro possui dependência(s)");
	}
	else
	if(sql == -5)
	{
		setStatusCode("00W0002","Parâmetro(s) de entrada inválido(s)");
	}
	else
	{
		setStatusCode("00W0001","Não foi possível inserir dado(s)");
	}
	xml_g->closeTag();
	
	ULOG_END("implADMDOMPRAINC::Execute()");

}
