/*
 * Serviço LstLinha
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Util/Util.hpp>
#include <Linha/Linha.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTLINHA);

void implLSTLINHA::Execute(DOMNode* dnode, XMLGen* xml_g) {
	tuxfw_getlogger()->debug("LSTLINHA");
	CTuxHelperClever helper;
	CLinha olinha;
	stLinhaIntermed dadosLinha;
	int existeLinha = 0;
	memset(&dadosLinha,0,sizeof(dadosLinha));
	char *linha = helper.walkTree(dnode,"cdNumTelefone", 0);
	char *ddd = helper.walkTree(dnode,"cdDDD", 0);

	tuxfw_getlogger()->debug("executa a regra de negocio");
	tuxfw_getlogger()->debug("linha = %s",linha);
	tuxfw_getlogger()->debug("ddd = %s",ddd);

	if(linha == NULL || !strcmp(linha,""))
		throw new TuxBasicSvcException("11W0003", "linha não encontrada");
	if(ddd == NULL || !strcmp(ddd,""))
		throw new TuxBasicSvcException("11W0004", "ddd não encontrado");

	try
	{
		olinha.consultarDadosLinhaIntermed(ddd,linha,&dadosLinha);
	}
	catch(TuxBasicOraException tux)
	{
		tuxfw_getlogger()->debug("Problemas ao executar querys");
		throw new TuxBasicSvcException("11W0007", "Problemas ao executar querys");
	}

	xml_g->createTag( "IntermediarioRetorno" );
	xml_g->addProp( "xmlns", "intermediario.vol.vivo.com.br/vo" );
	xml_g->addItem("existe","1");
	xml_g->addItem("sgTipoLinha",dadosLinha.sgTipoLinha);
	xml_g->addItem("sgSistemaOrigem",dadosLinha.sgSistemaOrigem);
	xml_g->addItem("idLinhaSistemaOrigem",dadosLinha.idLinhaSistemaOrigem);

	xml_g->closeTag();
	//seta mensagem de retorno - header
	setStatusCode("11I0000", "MSG_OK");
}

