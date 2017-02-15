#ifndef TXPB_PLUGIN_PPS_H
#define TXPB_PLUGIN_PPS_H 1


#include "../../PlugInBE/include/PlugInBE.h"

/* 
** Constantes de configuracao
**
**  
** Codigos de erro: 24[EWI]2000 a 24[EWI]2999
**
**
*/

// Macros gerais para o PPS
#define SERVICE_PPS                         "AcessoPPS"
#define SERVICE_PPS_EXT                     "AcessoPPSExt"          // Nome do servico para obter o extrato
#define BUFFER_SIZE_PPS_ERRMSG              2048
#define DATE_FORMAT_PPS_IN                  "YYYY-MM-DD"
#define DATE_FORMAT_PPS_OUT                 "YYYY-MM-DD"


// Tags de entrada no PPS
#define TAG_XML_PPS_IN_ROOT				    "ROOT"
#define TAG_XML_PPS_IN_TRANS                "TRANSACAO"
#define TAG_XML_PPS_IN_CELULAR              "CELULAR"
#define TAG_XML_PPS_IN_LOGIN                "LOGIN"
#define TAG_XML_PPS_IN_IP                   "IP"
#define TAG_XML_PPS_IN_DTINI                "DTINI"
#define TAG_XML_PPS_IN_DTFIM                "DTFIM"
#define TAG_XML_PPS_IN_ACAO                 "ACAO"
#define TAG_XML_PPS_IN_MOTIVO               "MOTIVO"
#define TAG_XML_PPS_IN_BKO                  "BKO"
#define TAG_XML_PPS_IN_CODIGO               "CODIGO"
#define TAG_XML_PPS_IN_DATA                 "DATA"
#define TAG_XML_PPS_IN_ORIGEM               "ORIGEM"
#define TAG_XML_PPS_IN_DESTINO              "DESTINO"
#define TAG_XML_PPS_IN_TEXTO                "TEXTO"
#define TAG_XML_PPS_IN_NOM                  "NOM"
#define TAG_XML_PPS_IN_LOGR                 "LOGR"
#define TAG_XML_PPS_IN_END                  "END"
#define TAG_XML_PPS_IN_NUM                  "NUM"
#define TAG_XML_PPS_IN_CMPL                 "CMPL"
#define TAG_XML_PPS_IN_BRO                  "BRO"
#define TAG_XML_PPS_IN_MUNIC                "MUNIC"
#define TAG_XML_PPS_IN_CEP                  "CEP"
#define TAG_XML_PPS_IN_CID                  "CID"
#define TAG_XML_PPS_IN_UF                   "UF"
#define TAG_XML_PPS_IN_DOCTIPO1             "DOCTIPO1"
#define TAG_XML_PPS_IN_DOCTIPO2             "DOCTIPO2"
#define TAG_XML_PPS_IN_DOCNUM1              "DOCNUM1"
#define TAG_XML_PPS_IN_DOCNUM2              "DOCNUM2"
#define TAG_XML_PPS_IN_DTEXP                "DTEXP"
#define TAG_XML_PPS_IN_OE                   "OE"
#define TAG_XML_PPS_IN_ESTEXP               "ESTEXP"
#define TAG_XML_PPS_IN_CNAE                 "CNAE"
#define TAG_XML_PPS_IN_TIPO_PESSOA          "TIPO_PESSOA"
#define TAG_XML_PPS_IN_LDILDN               "LDILDN"
#define TAG_XML_PPS_IN_NOTAFISCAL           "NOTAFISCAL"
#define TAG_XML_PPS_IN_DATAPEDIDO           "DATAPEDIDO"
#define TAG_XML_PPS_IN_CLASSE_MOVIMENTO     "CLASSE_MOVIMENTO"


// Tags de saida no PPS
#define TAG_XML_PPS_OUT_ROOT                "ROOT"
#define TAG_XML_PPS_OUT_ERR                 "ERR"
#define TAG_XML_PPS_OUT_ERRMSG              "ERR_MENS"
#define TAG_XML_PPS_OUT_MODELO              "MODELO"
#define TAG_XML_PPS_OUT_MARCA               "MARCA"
#define TAG_XML_PPS_OUT_ESN                 "ESN"
#define TAG_XML_PPS_OUT_CD                  "CD"
#define TAG_XML_PPS_OUT_ESTADO              "ESTADO"
#define TAG_XML_PPS_OUT_VALIDADE_REAL       "VALIDADE_REAL"
#define TAG_XML_PPS_OUT_VALIDADE            "VALIDADE"
#define TAG_XML_PPS_OUT_SALDO_DIARIO        "SALDO_DIARIO"
#define TAG_XML_PPS_OUT_SALDO_ON_NET        "SALDO_ON_NET"
#define TAG_XML_PPS_OUT_SALDO_REAL          "SALDO_REAL"
#define TAG_XML_PPS_OUT_REG                 "REG"
#define TAG_XML_PPS_OUT_ESCOLHIDO           "ESCOLHIDO"
#define TAG_XML_PPS_OUT_PROMOCAO            "PROMOCAO"
#define TAG_XML_PPS_OUT_DTINI               "DTINI"
#define TAG_XML_PPS_OUT_DTFIM               "DTFIM"
#define TAG_XML_PPS_OUT_SERVICO             "SERVICO"
#define TAG_XML_PPS_OUT_ATRIBUTO            "ATRIBUTO"
#define TAG_XML_PPS_OUT_EVENTO              "EVENTO"
#define TAG_XML_PPS_OUT_DATA                "DATA"
#define TAG_XML_PPS_OUT_ORIGEM              "ORIGEM"
#define TAG_XML_PPS_OUT_VALOR               "VALOR"
#define TAG_XML_PPS_OUT_DATACLIENTE         "DATACLIENTE"
#define TAG_XML_PPS_OUT_DATAPROCESSO        "DATAPROCESSO"
#define TAG_XML_PPS_OUT_CAIXA               "CAIXA"
#define TAG_XML_PPS_OUT_MOVIMENTO           "MOVIMENTO"
#define TAG_XML_PPS_OUT_NOTAFISCAL          "NOTAFISCAL"
#define TAG_XML_PPS_OUT_FLAG                "FLAG"
#define TAG_XML_PPS_OUT_NF                  "NF"
#define TAG_XML_PPS_OUT_SERIE               "SERIE"
#define TAG_XML_PPS_OUT_DTEMI               "DTEMI"
#define TAG_XML_PPS_OUT_CFOP                "CFOP"
#define TAG_XML_PPS_OUT_NOME                "NOME"
#define TAG_XML_PPS_OUT_END                 "END"
#define TAG_XML_PPS_OUT_CEP                 "CEP"
#define TAG_XML_PPS_OUT_MUNIC               "MUNIC"
#define TAG_XML_PPS_OUT_UF                  "UF"
#define TAG_XML_PPS_OUT_CELULAR             "CELULAR"
#define TAG_XML_PPS_OUT_LOCAL               "LOCAL"
#define TAG_XML_PPS_OUT_QUITACAO            "QUITACAO"
#define TAG_XML_PPS_OUT_VQUITACAO           "VQUITACAO"
#define TAG_XML_PPS_OUT_BASE_ICMS           "BASE_ICMS"
#define TAG_XML_PPS_OUT_ICMS                "ICMS"
#define TAG_XML_PPS_OUT_PER_ICMS            "PER_ICMS"
#define TAG_XML_PPS_OUT_BASE_FUST           "BASE_FUST"
#define TAG_XML_PPS_OUT_FUST                "FUST"
#define TAG_XML_PPS_OUT_BASE_FUNTEL         "BASE_FUNTEL"
#define TAG_XML_PPS_OUT_FUNTEL              "FUNTEL"
#define TAG_XML_PPS_OUT_SERV1               "SERV1"
#define TAG_XML_PPS_OUT_VSERV1              "VSERV1"
#define TAG_XML_PPS_OUT_TNOTA               "TNOTA"
#define TAG_XML_PPS_OUT_VPAGO               "VPAGO"
#define TAG_XML_PPS_OUT_HASHCODE            "HASHCODE"
#define TAG_XML_PPS_OUT_IE                  "IE"
#define TAG_XML_PPS_OUT_CPF                 "CPF"
#define TAG_XML_PPS_OUT_CNPJ                "CNPJ"
#define TAG_XML_PPS_OUT_CLASSE_MOVIMENTO    "CLASSE_MOVIMENTO"


// Valores para tags de saida no PPS
#define TAG_XML_PPS_IN_VAL_LOGIN                                   "FO"
#define TAG_XML_PPS_IN_VAL_IP                                      "127.0.0.1"
// Servicos
#define TAG_XML_PPS_IN_VAL_TRANS_VOICEMAIL                         "VOICEMAIL"
#define TAG_XML_PPS_IN_VAL_TRANS_CALLID                            "CALLID"
#define TAG_XML_PPS_IN_VAL_TRANS_WARN                              "WARN"
#define TAG_XML_PPS_IN_VAL_TRANS_CALLWAIT                          "CALLWAIT"
#define TAG_XML_PPS_IN_VAL_TRANS_CALLFWD                           "CALLFWD"
#define TAG_XML_PPS_IN_VAL_TRANS_UNBLOCKDDI                        "UNBLOCKDDI"
#define TAG_XML_PPS_IN_VAL_TRANS_UNBLOCKDDD                        "UNBLOCKDDD"
#define TAG_XML_PPS_IN_VAL_TRANS_WAP                               "WAP"
// Transacoes
#define TAG_XML_PPS_IN_VAL_TRANS_GET_DETALHE_LINHA                 "1000"
#define TAG_XML_PPS_IN_VAL_TRANS_GET_DETALHES_SALDO                "1000"
#define TAG_XML_PPS_IN_VAL_TRANS_GET_ESN                           "1000"
#define TAG_XML_PPS_IN_VAL_TRANS_GET_EXTRATO                       "105"
#define TAG_XML_PPS_IN_VAL_TRANS_GET_FAVORITOS                     "1008"
#define TAG_XML_PPS_IN_VAL_TRANS_GET_HISTORICO_ATENDIMENTO         "1001"
#define TAG_XML_PPS_IN_VAL_TRANS_GET_HISTORICO_MOVIMENTOS          "1003"
#define TAG_XML_PPS_IN_VAL_TRANS_GET_NOTA_FISCAL                   "1006"
#define TAG_XML_PPS_IN_VAL_TRANS_GET_PROMOCOES                     "1009"
#define TAG_XML_PPS_IN_VAL_TRANS_GET_SERVICOS                      "1002"
#define TAG_XML_PPS_IN_VAL_TRANS_SET_CLIENTE                       "2"
#define TAG_XML_PPS_IN_VAL_TRANS_SET_FAVORITO                      "3013"
#define TAG_XML_PPS_IN_VAL_TRANS_SET_INTERCEPTACAO                 "3012"
#define TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_VOICEMAIL             "3002"
#define TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_CALLID                "3003"
#define TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_WARN                  "3001"
#define TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_CALLWAIT              "3008"
#define TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_CALLFWD               "3009"
#define TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_UNBLOCKDDI            "3006"
#define TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_UNBLOCKDDD            "3005"
#define TAG_XML_PPS_IN_VAL_TRANS_SET_SERVICO_WAP                   "3007"
#define TAG_XML_PPS_IN_VAL_TRANS_SET_SUSPENDE_CELULAR              "2006"


// Valores para tags de saida no PPS
#define TAG_XML_PPS_OUT_VAL_SALDO_DIARIO                           "Saldo Diário"
#define TAG_XML_PPS_OUT_VAL_SALDO_ON_NET                           "Saldo On Net"
#define TAG_XML_PPS_OUT_VAL_SALDO_REAL                             "Saldo Real"


// Codigos de erro para o PPS
#define ECD_PPS_GW_NOT_RESPOND                     "24E2001"
#define ECD_PPS_GW_RETURN_ERROR                    "24E2002" 
#define ECD_TAG_XML_PPS_OUT_NE_ROOT                "24E2003"
#define ECD_TAG_XML_PPS_OUT_NE_ERR                 "24E2004"
#define ECD_TAG_XML_PPS_OUT_NE_ERRMSG              "24E2005"
        

// Mensagens de erro para o PPS
#define EMSG_PPS_GW_NOT_RESPOND                    "PPS não retornou resposta"
#define EMSG_PPS_GW_RETURN_ERROR                   "PPS retornou erro"
#define EMSG_TAG_XML_PPS_OUT_NE_ROOT               "PPS não retornou a TAG ROOT"
#define EMSG_TAG_XML_PPS_OUT_NE_ERR                "PPS não retornou a TAG ERR"
#define EMSG_TAG_XML_PPS_OUT_NE_ERRMSG             "PPS não retornou a TAG ERRMSG"




class PlugInPPS : public PlugInBE
{
public:

	PlugInPPS(DOMNode* , XMLGen*);
	
	~PlugInPPS();

	void getDetalheLinha();
	void getDetalhesSaldo();
	void getHistoricoMovimentos();
	void getExtrato();
	void getPromocoes();
	void getServicos();
	void getFavoritos();
	void getHistoricoAtendimento();
	void getAjustes();
	void getEstimativa();
	void getEstimativaSaldo();
	void getFormaPagamento();
	void getFidelizacao();
	void getHistoricoFaturamentos();
	void getImpedimentos();
	void getInfoContaCobranca();
	void getPagamentos();
	void getInfoFaturamento();
	void getURASegundaViaConta();
	void getURABoletoFax();
	void getURAConta();
	void getESN();
	void getNotaFiscal();
	void getServicosURA();

	void setServico();
	void setSuspendeCelular();
	void setFavorito();
	void setInterceptacao();
	void setCliente();

private:

	char* traduzAtributoToNomeServico(char*);
	char* traduzNomeServicoToDescricao(char*);
	char* getServicoStatus(char*);
	void  trataErro(DOMNode *);
};


#endif
