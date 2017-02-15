// AtlysLFFaturamento.pcpp: implementation for the 
// CAtlysLFFaturamento class.
//////////////////////////////////////////////////////////////////////

#include <tuxfw.h>
#include "../include/Global.h"
#include "../include/Funcoes.h"
#include "../include/AtlysLFFaturamento.h"

//
// Construtor e Destrutor
CAtlysLFFaturamento::CAtlysLFFaturamento(int iIdConta, char* pcNrConta, char* pcDsCicloFatura) {

	// Monta XML com ID da conta
	XMLGen oXml;
	XMLGen oRet;
	int iSz;

	oXml.setRoot(XML_INT_MSG_BODY);
	oXml.addItem(XML_INT_ID_CONTA, iIdConta);
	oXml.addItem(XML_INT_NR_CONTA, pcNrConta);
	oXml.addItem(XML_INT_DS_CICLO_FATURA, pcDsCicloFatura);

	// Chama o serviço de recuperar informações 
	// implAtlysLFFaturamento oSvc = new implAtlysLFFaturamento("AtlysLFFaturamento");
	// oSvc.Execute(oXml.getDOM(), &oRet);
	// Ini XML DUMB
	oRet.createTag("LFFaturamento");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("nrMes",10);
	oRet.addItem("nrAno",2003);
	oRet.addItem("dsCiclo","Ciclo 1");
	oRet.addItem("dtVencimento","01/11/2003");
	oRet.addItem("vlTotal","350,00");
	oRet.addItem("dsStatus","OK");
	oRet.closeTag();
	oRet.createTag("LFFaturamento");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("nrMes",11);
	oRet.addItem("nrAno",2003);
	oRet.addItem("dsCiclo","Ciclo 1");
	oRet.addItem("dtVencimento","01/12/2003");
	oRet.addItem("vlTotal","300,00");
	oRet.addItem("dsStatus","OK");
	oRet.closeTag();
	// Fim XML DUMB

	cLFFaturamento = oRet.retrieveXML(&iSz);
}

CAtlysLFFaturamento::~CAtlysLFFaturamento() {
	if (cLFFaturamento)
		free(cLFFaturamento);
}

//
// Metodos getter
char* CAtlysLFFaturamento::getXMLLFFaturamento(){
	return cLFFaturamento;
}
