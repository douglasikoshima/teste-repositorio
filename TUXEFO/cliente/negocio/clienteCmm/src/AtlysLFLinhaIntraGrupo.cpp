// AtlysLFLinhaIntraGrupo.pcpp: implementation for the 
// CAtlysLFLinhaIntraGrupo class.
//////////////////////////////////////////////////////////////////////

#include <tuxfw.h>
#include "../include/Global.h"
#include "../include/Funcoes.h"
#include "../include/AtlysLFLinhaIntraGrupo.h"

//
// Construtor e Destrutor
CAtlysLFLinhaIntraGrupo::CAtlysLFLinhaIntraGrupo(int iIdConta, char* pcNrConta, char* pcDsCicloFatura) {

	// Monta XML com ID da conta
	XMLGen oXml;
	XMLGen oRet;
	int iSz;

	oXml.setRoot(XML_INT_MSG_BODY);
	oXml.addItem(XML_INT_ID_CONTA, iIdConta);
	oXml.addItem(XML_INT_NR_CONTA, pcNrConta);
	oXml.addItem(XML_INT_DS_CICLO_FATURA, pcDsCicloFatura);

	// Chama o serviço de recuperar informações 
	// implAtlysLFLinhaIntraGrupo oSvc = new implAtlysLFLinhaIntraGrupo("AtlysLFLinhaIntraGrupo");
	// oSvc.Execute(oXml.getDOM(), &oRet);
	// Ini XML DUMB
	oRet.createTag("LFLinhaIntraGrupo");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("nrLinha","11 99878787");
	oRet.closeTag();
	oRet.createTag("LFLinhaIntraGrupo");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("nrLinha","11 99878342");
	oRet.closeTag();
	// Fim XML DUMB

	cLFLinhaIntraGrupo = oRet.retrieveXML(&iSz);
}

CAtlysLFLinhaIntraGrupo::~CAtlysLFLinhaIntraGrupo() {
	if (cLFLinhaIntraGrupo)
		free(cLFLinhaIntraGrupo);
}

//
// Metodos getter
char* CAtlysLFLinhaIntraGrupo::getXMLLFLinhaIntraGrupo(){
	return cLFLinhaIntraGrupo;
}
