/*
 * Serviço de preenchimento da aba de serviços na tela inicial - Filtro
 * Versão inicial, 26/07/2004
 */

#ifndef ABASERVFILTROHH
#define ABASERVFILTROHH

#define XML_IN_NR_CONTA				"nrConta"
#define XML_IN_NR_LINHA             "nrLinha"
#define XML_IN_ID_PESSOA            "idPessoa"

#define XML_OUT_ROOT				"AbaServicosFiltroVO"
#define XML_OUT_PROP_XMLNS			"xmlns"
#define XML_OUT_PROP_XMLNS_VALUE	"cliente.fo.vivo.com.br/vo"

#define XML_OUT_MESSAGE				 "mensagem"
#define XML_OUT_MSG_CONTA_NAO_CLI	 "Conta não é dessa pessoa"
#define XML_OUT_MSG_CONTA_NAO_EXISTE "Conta não existe"
#define XML_OUT_MSG_LINHA_NAO_CLI    "Linha não é dessa pessoa"
#define XML_OUT_MSG_LINHA_NAO_EXISTE "Linha não existe"

#define XML_OUT_ID_CONTA_ORIG		"idContaSistemaOrigem"
#define XML_OUT_ID_LINHA_ORIG		"idLinhaSistemaOrigem"

#endif