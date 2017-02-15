/*
 * Serviço GETCAMP
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Util/Util.hpp>
#include <Campanha/Campanha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(GETCAMP);

void implGETCAMP::Execute(DOMNode* dnode, XMLGen* xml_g) {
	tuxfw_getlogger()->debug("GETCAMP");
	CTuxHelperClever helper;
	CCampanha campanha;

	char *idCampanha = helper.walkTree(dnode,"idCampanha", 0);

	tuxfw_getlogger()->debug("executa a regra de negocio");
	tuxfw_getlogger()->debug("idCampanha = %s",idCampanha);

	try
	{		
		campanha.consultarCampanha(idCampanha,xml_g);
	}
	catch(TuxBasicOraException tux)
	{
		tuxfw_getlogger()->debug("Problemas ao executar querys");
		throw new TuxBasicSvcException("11W0007", "Problemas ao executar querys");
	}

	//seta mensagem de retorno - header
	setStatusCode("11I0000", "MSG_OK");
}

