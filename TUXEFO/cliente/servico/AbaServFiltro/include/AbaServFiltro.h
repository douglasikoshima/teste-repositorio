/*
 * Servi�o de preenchimento da aba de servi�os na tela inicial - Filtro
 * Vers�o inicial, 26/07/2004
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
#define XML_OUT_MSG_CONTA_NAO_CLI	 "Conta n�o � dessa pessoa"
#define XML_OUT_MSG_CONTA_NAO_EXISTE "Conta n�o existe"
#define XML_OUT_MSG_LINHA_NAO_CLI    "Linha n�o � dessa pessoa"
#define XML_OUT_MSG_LINHA_NAO_EXISTE "Linha n�o existe"

#define XML_OUT_ID_CONTA_ORIG		"idContaSistemaOrigem"
#define XML_OUT_ID_LINHA_ORIG		"idLinhaSistemaOrigem"

#endif