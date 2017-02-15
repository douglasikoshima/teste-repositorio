// AtlysAbaServicos.pcpp: implementation for the 
// CAtlysAbaServicos class.
//////////////////////////////////////////////////////////////////////

#include <tuxfw.h>
#include "../include/Global.h"
#include "../include/Funcoes.h"
#include "../include/AtlysAbaServicos.h"

//
// Construtor e Destrutor
CAtlysAbaServicos::CAtlysAbaServicos(int iIdConta, int* iIdLinha) {

	// Monta XML com ID da conta
	XMLGen oXml;
	XMLGen oRet;
	int iSz;

	oXml.setRoot(XML_INT_MSG_BODY);
	oXml.addItem(XML_INT_ID_CONTA, iIdConta);
	if (iIdLinha)
		oXml.addItem(XML_INT_ID_LINHA, *iIdLinha);

	// Chama o serviço de recuperar informações 
	// implAtlysAbaServicos oSvc = new implAtlysAbaServicos("AtlysAbaServicos");
	// oSvc.Execute(oXml.getDOM(), &oRet);
	// Ini XML DUMB
	oRet.createTag("ListaServicos");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("dtNota","01/04/2004 20:05:00");
	oRet.addItem("dsNota","BLOQUEIO POR ROUBO");
	oRet.addItem("inPrioridade","S");
	oRet.addItem("nrSequencia","1");
	oRet.closeTag();
	oRet.createTag("ListaServicos");
	oRet.addItem("idConta",iIdConta);
	oRet.addItem("dtNota","03/04/2004 20:05:00");
	oRet.addItem("dsNota","DESBLOQUEIO");
	oRet.addItem("inPrioridade","S");
	oRet.addItem("nrSequencia","2");
	oRet.closeTag();
	// Fim XML DUMB

	cAbaServicos = oRet.retrieveXML(&iSz);
}

CAtlysAbaServicos::~CAtlysAbaServicos() {
	if (cAbaServicos)
		free(cAbaServicos);
}

//
// Metodos getter
char* CAtlysAbaServicos::getXMLAbaServicos(){
	return cAbaServicos;
}
