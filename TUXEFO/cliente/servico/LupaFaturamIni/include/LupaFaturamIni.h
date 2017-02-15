/*
 * Serviço de preenchimento da lupa de faturamento do cliente
 * Versão inicial, 07/06/2004
 */

#ifndef LUPAFATURAMINIHH
#define LUPAFATURAMINIHH

#define XML_IN_ID_PESSOA			"idPessoa"
#define XML_IN_NR_CONTA				"nrConta"
#define XML_IN_NR_FONE				"nrLinha"

#define XML_OUT_ROOT				"LupaFaturamentoPosVO"			
#define XML_OUT_PROP_XMLNS			"xmlns"
#define XML_OUT_PROP_XMLNS_VALUE	"cliente.fo.vivo.com.br/vo"

#define XML_OUT_CONTAS_FATURAMENTO	"LFContasFaturamento"
#define XML_OUT_ID_CONTA			"idConta"
#define XML_OUT_ID_CONTA_SIS_ORIG   "idcontasistemaorigem"
#define XML_OUT_NR_CONTA			"nrConta"
#define XML_OUT_DS_CICLO_FATURA		"dsCicloFatura"
#define XML_OUT_DT_VENCTO			"dtVencimento"

#define XML_OUT_LINHAS				"LFLinhas"
#define XML_OUT_ID_LINHA			"idLinha"
#define XML_OUT_NR_COD_AREA			"nrCodArea"
#define XML_OUT_NR_LINHA			"nrLinha"
#define XML_OUT_DS_ESTADO_LINHA		"dsEstadoLinha"

#endif
