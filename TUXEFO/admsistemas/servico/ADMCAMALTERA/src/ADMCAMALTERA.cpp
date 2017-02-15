#define ADMCAMALTERACPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCmp.h"

DECLARE_TUXEDO_SERVICE(ADMCAMALTERA);

void implADMCAMALTERA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
    ULOG_START("implADMCAMALTERA::Execute()");
	CSafePointer oSafePointer;	
	CCampo oCCampo;
	char* cidCampo = NULL;
	char* cnmCampo = NULL;
	char* cidTipoDadoCampo = NULL;
	char* cidMascaraApresentacaoCampo = NULL;
	char* cidLayoutApresentacaoCampo = NULL;
	char* cnrTamanho = NULL;
	char* cinDisponibilidade = NULL;
	char* cidClassificadorCampo = NULL;
	char* cinFiltro = NULL;
	char* cinPesquisa = NULL;
	char* cinObrigatorio = NULL;
	char* cidUser = NULL;
	int update = 0;
	xml_g->createTag("AdmCamposContatoVO");
	xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
	// Recupera os campos do XML
	cidCampo = oSafePointer.getTag(dnode,"idCampo");
	cnmCampo = oSafePointer.getTag(dnode,"nmCampo");
	cidTipoDadoCampo = oSafePointer.getTag(dnode,"idTipoDadoCampo");
	cidMascaraApresentacaoCampo = oSafePointer.getTag(dnode,"idMascaraApresentacao");
	cidLayoutApresentacaoCampo = oSafePointer.getTag(dnode,"idLayoutApresentacaoCampo");
	cnrTamanho = oSafePointer.getTag(dnode,"nrTamanho");
	cinDisponibilidade = oSafePointer.getTag(dnode,"inDisponibilidade");
	cidClassificadorCampo = oSafePointer.getTag(dnode,"idClassificadorCampo");
	cinFiltro = oSafePointer.getTag(dnode,"inFiltro");
	cinPesquisa = oSafePointer.getTag(dnode,"inPesquisa");
	cinObrigatorio = oSafePointer.getTag(dnode,"inObrigatorio");
	cidUser = getUser();
	update = oCCampo.Update(cidCampo
						,cnmCampo
						,cidTipoDadoCampo
						,cidMascaraApresentacaoCampo
						,cidLayoutApresentacaoCampo
						,cnrTamanho
						,cinDisponibilidade
						,cidClassificadorCampo
						,cinFiltro
						,cinPesquisa
						,cinObrigatorio
						,cidUser);
	if(update > 0)
	{
		if(oCCampo.ListPar(cidCampo) > 0)
		{
			oCCampo.GetXmlListAll("AdmCampoContatoVO",xml_g);
		}
		setStatusCode("00I0000","Sucesso");
	}
	else
	if(update == 0)
	{
		setStatusCode("00W0001","Par�metro(s) de entrada inv�lido(s)");
	}
	else
	{
		setStatusCode("00W0004","N�o foi poss�vel atualizar dado(s)");
	}
	xml_g->closeTag();
	ULOG_END("implADMCAMALTERA::Execute()");
}