/*
 * Serviço documento
 * Versão inicial, 23/02/2006
 */

#include <stdio.h>
#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Documento/Documento.hpp>
#include <Util/Util.hpp>
#include <TuxHelperClever/TuxHelperClever.h>

DECLARE_TUXEDO_SERVICE(LSTDOC);

void implLSTDOC::Execute(DOMNode* dnode, XMLGen* xml_g) {
	tuxfw_getlogger()->debug("LSTDOC");
	CTuxHelperClever helper;
	Documento doc;
	char *linha = helper.walkTree(dnode,"linha", 0);
	char *ddd = helper.walkTree(dnode,"ddd", 0);
	char documento[256];
	int cpfValido = 0;
	memset(&documento,0,sizeof(documento));

	tuxfw_getlogger()->debug("executa a regra de negocio");
	tuxfw_getlogger()->debug("linha = %s",linha);
	tuxfw_getlogger()->debug("ddd = %s",ddd);

	if(linha == NULL || !strcmp(linha,""))
		throw new TuxBasicSvcException("11W0003", "linha não encontrada");
	if(ddd == NULL || !strcmp(ddd,""))
		throw new TuxBasicSvcException("11W0004", "ddd não encontrado");

	try
	{
		tuxfw_getlogger()->debug("recupera o cpf");
		int ret = doc.getDocumento(documento,"CPF",linha,ddd);
	}
	catch(TuxBasicOraException tux)
	{
		throw new TuxBasicSvcException("11W0006", "Erro de acesso a base");
	}

	try
	{
		tuxfw_getlogger()->debug("cpf = %s",documento);
		tuxfw_getlogger()->debug("valida CPF");
		cpfValido = doc.validaCPF(documento);
	}
	catch(...)
	{
		throw new TuxBasicSvcException("11W0007", "Erro interno");
	}

	if(!cpfValido)
		throw new TuxBasicSvcException("11W0002", "CPF encontrado na base inválido");


	tuxfw_getlogger()->debug("finalizando... documento = %s",documento);
	xml_g->createTag( "consultarDocumentoVO" );
	xml_g->addProp( "xmlns", "dados.vol.vivo.com.br/vo" );
	xml_g->addItem("documento",documento);
	xml_g->closeTag();
	//seta mensagem de retorno - header
	setStatusCode("11I0000", "MSG_OK");
}

