// AtlysLFPagamento.pcpp: implementation for the 
// CAtlysLFPagamento class.
//////////////////////////////////////////////////////////////////////

#include <tuxfw.h>
#include "../include/Global.h"
#include "../include/Funcoes.h"
#include "../include/AtlysLFPagamento.h"

//
// Construtor e Destrutor
CAtlysLFPagamento::CAtlysLFPagamento(int iIdConta, char* pcNrConta, char* pcDsCicloFatura) {

	// Monta XML com ID da conta
	XMLGen oXml;
	XMLGen oRet;
	int iSz;

	oXml.setRoot(XML_INT_MSG_BODY);
	oXml.addItem(XML_INT_ID_CONTA, iIdConta);
	oXml.addItem(XML_INT_NR_CONTA, pcNrConta);
	oXml.addItem(XML_INT_DS_CICLO_FATURA, pcDsCicloFatura);

	// Chama o serviço de recuperar informações 
	// implAtlysLFPagamento oSvc = new implAtlysLFPagamento("AtlysLFPagamento");
	// oSvc.Execute(oXml.getDOM(), &oRet);
	// Ini XML DUMB
	oRet.createTag("LFPagamento");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("dtPagamento","01/04/2004");
	oRet.addItem("vlPagamento","156,88");
	oRet.addItem("dsTipoPagamento","Quitado");
	oRet.addItem("dsReferencia","05/04/2004");
	oRet.addItem("dsMotivoReversao","Erro de processamento");
	oRet.addItem("dtReversao","01/05/2004");
	oRet.closeTag();
	oRet.createTag("LFPagamento");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("dtPagamento","01/04/2004");
	oRet.addItem("vlPagamento","156,88");
	oRet.addItem("dsTipoPagamento","Quitado");
	oRet.addItem("dsReferencia","05/04/2004");
	oRet.addItem("dsMotivoReversao","Erro de processamento");
	oRet.addItem("dtReversao","01/05/2004");
	oRet.closeTag();
	// Fim XML DUMB

	cLFPagamento = oRet.retrieveXML(&iSz);
}

CAtlysLFPagamento::~CAtlysLFPagamento() {
	if (cLFPagamento)
		free(cLFPagamento);
}

//
// Metodos getter
char* CAtlysLFPagamento::getXMLLFPagamento(){
	return cLFPagamento;
}
