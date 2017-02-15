/*
 * Serviço documento
 * Versão inicial, 23/02/2006
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Util/Util.hpp>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(GETPROCLINHA);

void implGETPROCLINHA::Execute(DOMNode* dnode, XMLGen* xml_g) {
	tuxfw_getlogger()->debug("GETPROCLINHA");
	CTuxHelperClever helper;
	CLinha clinha; 
	char *linha = helper.walkTree(dnode,"linha", 0);
	char *ddd = helper.walkTree(dnode,"ddd", 0);
	xml_g->createTag("ComprovanteCancelVO"); 
	xml_g->addProp("xmlns","servicos.vol.vivo.com.br/vo");
	clinha.consultarProcessoCancelamento(ddd,linha,xml_g);
	xml_g->closeTag();
	//seta mensagem de retorno - header
	setStatusCode("11I0000", "MSG_OK");
}

