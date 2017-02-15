// AtlysLFEstimativa.pcpp: implementation for the 
// CAtlysLFEstimativa class.
//////////////////////////////////////////////////////////////////////

#include <tuxfw.h>
#include "../include/Global.h"
#include "../include/Funcoes.h"
#include "../include/AtlysLFEstimativa.h"

//
// Construtor e Destrutor
CAtlysLFEstimativa::CAtlysLFEstimativa(int iIdConta, char* pcNrConta, char* pcDsCicloFatura) {

	// Monta XML com ID da conta
	XMLGen oXml;
	XMLGen oRet;
	int iSz;

	oXml.setRoot(XML_INT_MSG_BODY);
	oXml.addItem(XML_INT_ID_CONTA, iIdConta);
	oXml.addItem(XML_INT_NR_CONTA, pcNrConta);
	oXml.addItem(XML_INT_DS_CICLO_FATURA, pcDsCicloFatura);

	// Chama o serviço de recuperar informações 
	// implAtlysLFEstimativa oSvc = new implAtlysLFEstimativa("AtlysLFEstimativa");
	// oSvc.Execute(oXml.getDOM(), &oRet);
	// Ini XML DUMB
	oRet.createTag("LFEstimativa");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("dsFormaPagamento","DEBITO AUTOMATICO");
	oRet.addItem("dsEstimativaSaldo","350,98");
	oRet.closeTag();
	// Fim XML DUMB

	cLFEstimativa = oRet.retrieveXML(&iSz);
}

CAtlysLFEstimativa::~CAtlysLFEstimativa() {
	if (cLFEstimativa)
		free(cLFEstimativa);
}

//
// Metodos getter
char* CAtlysLFEstimativa::getXMLLFEstimativa(){
	return cLFEstimativa;
}
