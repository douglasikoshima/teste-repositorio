// AtlysLFContaDetalhada.pcpp: implementation for the 
// CAtlysLFContaDetalhada class.
//////////////////////////////////////////////////////////////////////

#include <tuxfw.h>
#include "../include/Global.h"
#include "../include/Funcoes.h"
#include "../include/AtlysLFContaDetalhada.h"

//
// Construtor e Destrutor
CAtlysLFContaDetalhada::CAtlysLFContaDetalhada(int iIdConta, char* pcNrConta, char* pcDsCicloFatura) {

	// Monta XML com ID da conta
	XMLGen oXml;
	XMLGen oRet;
	int iSz;

	oXml.setRoot(XML_INT_MSG_BODY);
	oXml.addItem(XML_INT_ID_CONTA, iIdConta);
	oXml.addItem(XML_INT_NR_CONTA, pcNrConta);
	oXml.addItem(XML_INT_DS_CICLO_FATURA, pcDsCicloFatura);

	// Chama o serviço de recuperar informações 
	// implAtlysLFContaDetalhada oSvc = new implAtlysLFContaDetalhada("AtlysLFContaDetalhada");
	// oSvc.Execute(oXml.getDOM(), &oRet);
	// Ini XML DUMB
	oRet.createTag("LFContaDetalhada");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("dsTipoConta","Ligação local");
	oRet.addItem("nrDestino","1199998888");
	oRet.addItem("vlTarifa","0,99");
	oRet.addItem("dsDuracao","1:01");
	oRet.addItem("dsOrigemSMS","");
	oRet.closeTag();
	oRet.createTag("LFContaDetalhada");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("dsTipoConta","Ligação local");
	oRet.addItem("nrDestino","1199998888");
	oRet.addItem("vlTarifa","0,99");
	oRet.addItem("dsDuracao","1:01");
	oRet.addItem("dsOrigemSMS","");
	oRet.closeTag();
	oRet.createTag("LFContaDetalhada");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("dsTipoConta","Ligação local");
	oRet.addItem("nrDestino","1199998888");
	oRet.addItem("vlTarifa","0,99");
	oRet.addItem("dsDuracao","1:01");
	oRet.addItem("dsOrigemSMS","");
	oRet.closeTag();
	// Fim XML DUMB

	cLFContaDetalhada = oRet.retrieveXML(&iSz);
}

CAtlysLFContaDetalhada::~CAtlysLFContaDetalhada() {
	if (cLFContaDetalhada)
		free(cLFContaDetalhada);
}

//
// Metodos getter
char* CAtlysLFContaDetalhada::getXMLLFContaDetalhada(){
	return cLFContaDetalhada;
}
