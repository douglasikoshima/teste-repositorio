// AtlysLFCobranca.pcpp: implementation for the 
// CAtlysLFCobranca class.
//////////////////////////////////////////////////////////////////////

#include <tuxfw.h>
#include "../include/Global.h"
#include "../include/Funcoes.h"
#include "../include/AtlysLFCobranca.h"

//
// Construtor e Destrutor
CAtlysLFCobranca::CAtlysLFCobranca(int iIdConta, char* pcNrConta, char* pcDsCicloFatura) {

	// Monta XML com ID da conta
	XMLGen oXml;
	XMLGen oRet;
	int iSz;

	oXml.setRoot(XML_INT_MSG_BODY);
	oXml.addItem(XML_INT_ID_CONTA, iIdConta);
	oXml.addItem(XML_INT_NR_CONTA, pcNrConta);
	oXml.addItem(XML_INT_DS_CICLO_FATURA, pcDsCicloFatura);

	// Chama o serviço de recuperar informações 
	// implAtlysLFCobranca oSvc = new implAtlysLFCobranca("AtlysLFCobranca");
	// oSvc.Execute(oXml.getDOM(), &oRet);
	// Ini XML DUMB
	oRet.createTag("LFCobranca");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("dtFaturamento","01/01/2004");
	oRet.addItem("vlFaturado","100,00");
	oRet.addItem("vlAbertoAtual","300,00");
	oRet.addItem("vl30Dias","140,00");
	oRet.addItem("vl60Dias","190,00");
	oRet.addItem("vl90Dias","240,00");
	oRet.addItem("vlMaisDias","300,00");
	oRet.closeTag();
	oRet.createTag("LFCobranca");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("dtFaturamento","01/02/2004");
	oRet.addItem("vlFaturado","101,00");
	oRet.addItem("vlAbertoAtual","301,00");
	oRet.addItem("vl30Dias","141,00");
	oRet.addItem("vl60Dias","191,00");
	oRet.addItem("vl90Dias","241,00");
	oRet.addItem("vlMaisDias","302,00");
	oRet.closeTag();
	// Fim XML DUMB

	cLFCobranca = oRet.retrieveXML(&iSz);
}

CAtlysLFCobranca::~CAtlysLFCobranca() {
	if (cLFCobranca)
		free(cLFCobranca);
}

//
// Metodos getter
char* CAtlysLFCobranca::getXMLLFCobranca(){
	return cLFCobranca;
}
