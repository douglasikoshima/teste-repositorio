#ifndef TXPB_TUXPROXYBE_H
#define TXPB_TUXPROXYBE_H 1


/*
 *	Códigos de Erro/Aviso/Informação para o TuxProxy
 *	-------------------------------
 *
 *	Formato do código: CCLDDDD
 *
 *	CC:   Código do Sub-Sistema.
 *	L:    E - Erro, W - Aviso, I - Informação.
 *	DDDD: Código do Erro/Aviso/Informação.
 *
 *	CC - 24 (TuxProxy)
 *
 *	0000 a 0999 - Intervalo disponivel para o TuxProxyBE (Geral)
 *	1000 a 1999 - Intervalo disponivel para o PlugInAltys
 *	2000 a 2999 - Intervalo disponivel para o PlugInPPS
 *	3000 a 3999 - Intervalo disponivel para o PlugInNGIN
 *
 *
 *  ECD - Codigo de erro
 *  EMSG - Mensagem de erro
 *
 *  WCD - Codigo de aviso
 *  WMSG - Mensagem de aviso
 *
 *  ICD - Codigo de informacao
 *  IMSG - Mensagem de informacao
 *
 *	NE - Nao existe
 *	VV - Valor vazio
 *	VI - Valor invalido
 */


// Constantes para o XML de entrada
#define TAG_XML_IN_PROXY_LINHA                       "ProxyLinha"
#define TAG_XML_IN_PROXY_OPERACAO                    "ProxyOperacao"
#define WRAPPER_ATLYS								 "AcessoATLYS"


// Codigos de erro (0000 a 0099)
#define ECD_TAG_XML_IN_NE_PROXY_LINHA                "24E0001"
#define ECD_TAG_XML_IN_VV_PROXY_LINHA                "24E0002"
#define ECD_TAG_XML_IN_VI_PROXY_LINHA                "24E0003"
#define ECD_TAG_XML_IN_NE_PROXY_OPERACAO             "24E0004"
#define ECD_TAG_XML_IN_VV_PROXY_OPERACAO             "24E0005"
#define ECD_TAG_XML_IN_VI_PROXY_OPERACAO             "24E0006"

// Mensagens de erro
#define EMSG_TAG_XML_IN_NE_PROXY_LINHA               "TAG ProxyLinha não encontrada"
#define EMSG_TAG_XML_IN_VV_PROXY_LINHA               "TAG ProxyLinha com valor vazio"
#define EMSG_TAG_XML_IN_VI_PROXY_LINHA               "TAG ProxyLinha com valor inválido"
#define EMSG_TAG_XML_IN_NE_PROXY_OPERACAO            "TAG ProxyOperacao não encontrada"
#define EMSG_TAG_XML_IN_VV_PROXY_OPERACAO            "TAG ProxyOperacao com valor vazio"
#define EMSG_TAG_XML_IN_VI_PROXY_OPERACAO            "TAG ProxyOperacao com valor inválido"

// Codigos de informacao
#define ICD_EXECUTION_OK                       "24I0000"

// Mensagens de informacao
#define IMSG_EXECUTION_OK                      "Execução OK"


// Constantes para as operacoes
// Get
#define opGetDetalheLinha           "getDetalheLinha"
#define opGetDetalhesSaldo          "getDetalhesSaldo"
#define opGetHistoricoMovimentos    "getHistoricoMovimentos"
#define opGetExtrato                "getExtrato"
#define opGetPromocoes              "getPromocoes"
#define opGetServicos               "getServicos"
#define opGetFavoritos              "getFavoritos"
#define opGetHistoricoAtendimento   "getHistoricoAtendimento"
#define opGetAjustes                "getAjustes"
#define opGetEstimativa             "getEstimativa"
#define opGetEstimativaSaldo        "getEstimativaSaldo"
#define opGetFormaPagamento         "getFormaPagamento"
#define opGetFidelizacao            "getFidelizacao"
#define opGetHistoricoFaturamentos  "getHistoricoFaturamentos"
#define opGetImpedimentos           "getImpedimentos"
#define opGetInfoContaCobranca      "getInfoContaCobranca"
#define opGetPagamentos             "getPagamentos"
#define opGetInfoFaturamento        "getInfoFaturamento"
#define opGetURASegundaViaConta     "getURASegundaViaConta"
#define opGetURABoletoFax           "getURABoletoFax"
#define opGetURAConta               "getURAConta"
#define opGetESN                    "getESN"
#define opGetNotaFiscal             "getNotaFiscal"
#define opGetPlano                  "getPlano"
#define opGetTarifaReduzida         "getTarifaReduzida"
#define opGetDetalhesConsumo        "getDetalhesConsumo"


// Set
#define opSetServico					"setServico"
#define opSetSuspendeCelular			"setSuspendeCelular"
#define opSetReligueCelular				"setReligueCelular"
#define opSetFavorito					"setFavorito"
#define opSetInterceptacao				"setInterceptacao"
#define opSetCliente					"setCliente"
#define opSetPlano						"setPlano"
#define opSetTarifaReduzida				"setTarifaReduzida"
#define opSetCaixaPostal				"setCaixaPostal"

#endif
