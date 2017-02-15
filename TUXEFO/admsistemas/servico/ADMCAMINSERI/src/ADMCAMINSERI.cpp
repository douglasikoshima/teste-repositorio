#define ADMCAMINSERICPP

#include<tuxfw.h>
#include "../../../negocio/admatdCmm/include/CSafePointer.h"
#include "../../../negocio/admatdCmm/include/CCmp.h"

DECLARE_TUXEDO_SERVICE(ADMCAMINSERI);

void implADMCAMINSERI::Execute(DOMNode*dnode,XMLGen*xml_g)
{	
    ULOG_START("implADMCAMINSERI::Execute()");
	CSafePointer oSafePointer;
	CCampo oCCampo;
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
	int insert = 0;
	xml_g->createTag("AdmCamposContatoVO");
	xml_g->addProp("xmlns","admsistemas.fo.vivo.com.br/vo");
	// Recupera os campos do XML
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
	insert = oCCampo.Insert(cnmCampo
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
	if(insert > 0)
	{
		char cidCampo[21+1];
		oCCampo.GetCurrentID(cidCampo);
		if(oCCampo.ListPar(cidCampo) > 0)
		{
			oCCampo.GetXmlListAll("AdmCampoContatoVO",xml_g);
		}
		setStatusCode("00I0000","Sucesso");
	}
	else
	if(insert == 0)
	{
		setStatusCode("00W0001","Parâmetro(s) de entrada inválido(s)");
	}
	else
	if(insert == -1)
	{
		setStatusCode("00W0002","Registro duplicado");
	}
	else
	if(insert == -2 || insert == -3)
	{
		setStatusCode("00W0003","Registro possui dependência(s)");
	}
	else
	{
		setStatusCode("00W0004","Não foi possível inserir dado(s)");
	}
	xml_g->closeTag();
	ULOG_END("implADMCAMINSERI::Execute()");
}