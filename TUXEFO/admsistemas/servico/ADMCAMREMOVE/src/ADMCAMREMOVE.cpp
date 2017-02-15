#define ADMCAMREMOVECPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCmp.h"

DECLARE_TUXEDO_SERVICE(ADMCAMREMOVE);

void implADMCAMREMOVE::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMCAMREMOVE::Execute()");
   
	CSafePointer oSafePointer;
	CCampo oCCampo;
	char* cidCampo = NULL;
	char* cidTipoDadoCampo = NULL;
	char* cidMascaraApresentacaoCampo = NULL;
	char* cidLayoutApresentacaoCampo = NULL;
	char* cidClassificadorCampo = NULL;
	int sql = 0;
	// Recupera os campos do XML
	cidCampo = oSafePointer.getTag(dnode,"idCampo");
	cidTipoDadoCampo = oSafePointer.getTag(dnode,"idTipoDadoCampo");
	cidMascaraApresentacaoCampo = oSafePointer.getTag(dnode,"idMascaraApresentacao");
	cidLayoutApresentacaoCampo = oSafePointer.getTag(dnode,"idLayoutApresentacaoCampo");
	cidClassificadorCampo = oSafePointer.getTag(dnode,"idClassificadorCampo");
	sql = oCCampo.Delete(cidCampo);
	if(sql > 0)
	{
		setStatusCode("00I0000","Sucesso");
	}
	else
	if(sql == 0)
	{
		setStatusCode("00W0001","Parâmetro(s) de entrada inválido(s)");
	}
	else
	if(sql == -1)
	{
		setStatusCode("00W0002","Registro possui dependência(s)");
	}
	else
	{
		setStatusCode("00W0004","Não foi possível remover dado(s)");
	}
	
	ULOG_END("implADMCAMREMOVE::Execute()");
}