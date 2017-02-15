// AtlysLFAjustes.pcpp: implementation for the 
// CAtlysLFAjustes class.
//////////////////////////////////////////////////////////////////////

#include <tuxfw.h>
#include "../include/Global.h"
#include "../include/Funcoes.h"
#include "../include/AtlysLFAjustes.h"

//
// Construtor e Destrutor
CAtlysLFAjustes::CAtlysLFAjustes(int iIdConta, char* pcNrConta, char* pcDsCicloFatura) {

	// Monta XML com ID da conta
	XMLGen oXml;
	XMLGen oRet;
	int iSz;

	oXml.setRoot(XML_INT_MSG_BODY);
	oXml.addItem(XML_INT_ID_CONTA, iIdConta);
	oXml.addItem(XML_INT_NR_CONTA, pcNrConta);
	oXml.addItem(XML_INT_DS_CICLO_FATURA, pcDsCicloFatura);

	// Chama o serviço de recuperar informações 
	// implAtlysLFAjustes oSvc = new implAtlysLFAjustes("AtlysLFAjustes");
	// oSvc.Execute(oXml.getDOM(), &oRet);
	// Ini XML DUMB
	oRet.createTag("LFAjustes");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("dtAjuste","01/01/2004");
	oRet.addItem("vlAjuste","18,95");
	oRet.addItem("dsAjuste","Ajustado por erro de ciclo");
	oRet.addItem("inPersonalizada","TRUE");
	oRet.addItem("inFaturado","TRUE");
	oRet.closeTag();
	oRet.createTag("LFAjustes");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("dtAjuste","01/01/2003");
	oRet.addItem("vlAjuste","180,54");
	oRet.addItem("dsAjuste","Ajustado por erro de ciclo");
	oRet.addItem("inPersonalizada","TRUE");
	oRet.addItem("inFaturado","TRUE");
	oRet.closeTag();
	// Fim XML DUMB

	cLFAjustes = oRet.retrieveXML(&iSz);
}

CAtlysLFAjustes::~CAtlysLFAjustes() {
	if (cLFAjustes)
		free(cLFAjustes);
}

//
// Metodos getter
char* CAtlysLFAjustes::getXMLLFAjustes(){
	return cLFAjustes;
}
