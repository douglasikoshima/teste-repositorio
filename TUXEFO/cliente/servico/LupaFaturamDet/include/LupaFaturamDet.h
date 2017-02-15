/*
 * Serviço de preenchimento da lupa de faturamento do cliente - Detalhes
 * Versão inicial, 16/06/2004
 */

#ifndef LUPAFATURAMFATHH
#define LUPAFATURAMFATHH

#define XML_IN_ID_CONTA				"idConta"
#define XML_IN_TP_DET               "tpDetalhe"
#define XML_IN_NR_CONTA				"nrConta"
#define XML_IN_DS_CICLO_FATURA		"dsCicloFatura"
#define XML_IN_ID_LINHA				"idLinha"

#define XML_OUT_ROOT				"LupaFaturamentoPosVO"
#define XML_OUT_PROP_XMLNS			"xmlns"
#define XML_OUT_PROP_XMLNS_VALUE	"cliente.fo.vivo.com.br/vo"

#define DET_FATURAMENTO				"faturamento"
#define DET_COBRANCA				"cobranca"
#define DET_PAGAMENTO				"pagamento"
#define DET_AJUSTES					"ajustes"
#define DET_CONTA_DETALHADA			"contaDetalhada"
#define DET_LINHA_INTRA_GRUPO       "linhaIntraGrupo"
#define DET_ESTIMATIVAS             "estimativa"

#endif
