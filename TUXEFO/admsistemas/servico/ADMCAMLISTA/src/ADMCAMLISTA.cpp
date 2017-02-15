#define ADMCAMLISTACPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCmp.h"

DECLARE_TUXEDO_SERVICE(ADMCAMLISTA);

void implADMCAMLISTA::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
   ULOG_START("implADMCAMLISTA::Execute()");
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
	// Recupera os campos do XML
	cidCampo = oSafePointer.getTag(dnode,"idCampo");
	cnmCampo = oSafePointer.getTag(dnode,"nmCampo");
	cidTipoDadoCampo = oSafePointer.getTag(dnode,"idTipoDadoCampo");
	cidMascaraApresentacaoCampo = oSafePointer.getTag(dnode,"idMascaraApresentacaoCampo");
	cidLayoutApresentacaoCampo = oSafePointer.getTag(dnode,"idLayoutApresentacaoCampo");
	cnrTamanho = oSafePointer.getTag(dnode,"nrTamanho");
	cinDisponibilidade = oSafePointer.getTag(dnode,"inDisponibilidade");
	cidClassificadorCampo = oSafePointer.getTag(dnode,"idClassificadorCampo");
	cinFiltro = oSafePointer.getTag(dnode,"inFiltro");
	cinPesquisa = oSafePointer.getTag(dnode,"inPesquisa");
	cinObrigatorio = oSafePointer.getTag(dnode,"inObrigatorio");
	xml_g->createTag("AdmCamposContatoVO");
	xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
	if(oCCampo.ListPar(cidCampo,
		cnmCampo,
		cidTipoDadoCampo,
		cidMascaraApresentacaoCampo,
		cidLayoutApresentacaoCampo,
		cnrTamanho,
		cinDisponibilidade,
		cidClassificadorCampo,
		cinFiltro,
		cinPesquisa,
		cinObrigatorio) > 0)
	{
		oCCampo.GetXmlListAll("AdmCampoContatoVO",xml_g);
		setStatusCode("00I0000","Sucesso");
	}
	else
	{
		setStatusCode("00I0001","Nenhum registro encontrado");
	}	
	xml_g->closeTag();	
	
	ULOG_END("implADMCAMLISTA::Execute()");	
}