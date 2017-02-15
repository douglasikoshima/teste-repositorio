/*
 * Serviço LSTCAMPANHA
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Util/Util.hpp>
#include <Campanha/Campanha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTCAMPANHA);

void implLSTCAMPANHA::Execute(DOMNode* dnode, XMLGen* xml_g) {
	tuxfw_getlogger()->debug("LSTCAMPANHA");
	CTuxHelperClever helper;
	CCampanha campanha;
	char *cdAreaRegistro = helper.walkTree(dnode,"cdAreaRegistro", 0);
	char *idTipoLinha = helper.walkTree(dnode,"idTipoLinha", 0);
	char *idSegmentacao = helper.walkTree(dnode,"idSegmentacao", 0);
	char *nrLinha = helper.walkTree(dnode,"nrLinha", 0);
	try
	{
		campanha.consultarCampanhas(cdAreaRegistro,idTipoLinha,idSegmentacao,nrLinha,xml_g);
	}
	catch(TuxBasicOraException tux)
	{
		tuxfw_getlogger()->debug("Problemas ao executar querys");
		throw new TuxBasicSvcException("11W0007", "Problemas ao executar querys");
	}

	//seta mensagem de retorno - header
	setStatusCode("11I0000", "MSG_OK");
}

