#ifndef TXPB_PLUGIN_ATLYS_H
#define TXPB_PLUGIN_ATLYS_H 1

#include "../../PlugInBE/include/PlugInBE.h"
#include <iostream>
#include <string>
#include <vector>
#include <map>
#include <algorithm>
#include <functional>
/* 
** Constantes de configuracao
**
**  
** Codigos de erro: 24[EWI]1000 a 24[EWI]1999
**
**
*/ 




// Macros gerais para o ATLYS
#define SERVICE_ATLYS                                  "AcessoATLYS"           // Nome do serviço ATLYS
#define SERVICE_ATLYS_URA                              "AcessoATLYSURA"        // Nome do serviço ATLYS
#define XML_ACCS_NBR                                   "ACCESS_NBR"            // Constante para número de acesso
#define XML_FCPF                                       "FCPF"                  // Constante para CPF no atlys
#define DATE_FORMAT_ATL_IN                             "YYYY-MM-DD"
#define DATE_FORMAT_ATL_OUT                            "YYYY-MM-DD"

// Macros com números de erro do ATLYS                 
#define ERR_ATL_NO_REC                                 "40E5113"           // Conta não encontrada a partir da linha
#define ERR_ATL_NO_BILL                                "42E9004"           // Não tem faturamento, conta nova.
#define ERR_ATL_CONTA_NAO_COB                          "42E6024"           // Conta não está em cobrança

// TAGs de entrada no ATLYS
#define XML_ATL_INPUT_ACCESSNBR                        "inputGetAccessEquipmentByAccessNumber"
#define XML_ATL_INPUT_ACCT_NOTES                       "inputSearchAccountNotes"
#define XML_ATL_INPUT_GET_ADJUSTMENTS                  "inputGetAdjustments"
#define XML_ATL_INPUT_COLL_ACCT_STATE                  "inputGetCollAcctStateV2"
#define XML_ATL_INPUT_PAYMT_METHOD                     "inputGetPaymentMethod"
#define XML_ATL_INPUT_BILLV2                           "inputGetBillV2"
#define XML_ATL_REF_DATA                               "inputGetReferenceDataV2"
#define XML_ATL_TABLE_NAME                             "tableNm"
#define XML_ATL_INPUT_REPRINT_INVOICE                  "inputReprintInvoiceByAccessNbr"
#define XML_ATL_INPUT_PYMT_METHOD_ACCT                 "inputGetPaymentMethodsByAccount"
#define XML_ATL_ACCOUNT_BY_ATTR                        "inputSearchAccountByAttribute"
#define XML_ATL_INPUT_GET_PAYMENT_DETAILS_BY_ACCOUNT   "inputGetPaymentDetailsByAccount"
#define XML_ATL_INPUT_GET_ACCT_AGED_BALANCES           "inputGetAcctAgedBalances"
#define XML_ATL_INPUT_SUBSCRIPTION_INFO                "inputGetSubscriptionInfo"
#define XML_ATL_INPUT_SERVICES_REF_DATAV1              "inputGetServicesRefDataV1"
#define XML_ATL_INPUT_HOTLINE_SUBSCRIPTION             "inputHotlineSubscription"
#define XML_ATL_INPUT_REMOVE_SUBSCRIPTION_HOTLINE      "inputRemoveSubscriptionHotline"
#define XML_ATL_INPUT_GET_ACCOUNT_INFOV3               "inputGetAccountInfoV3"
#define XML_ATL_INPUT_GET_USAGE_BALANCE_V1             "inputGetUsageSummaryAndBalanceV1"
#define XML_ATL_INPUT_GET_MEMBER_SERVICES              "inputGetMemberServices"
#define XML_ATL_INPUT_PENDING_PYMT_RSTRL               "inputPendingPymtRstrl"
#define XML_ATL_INPUT_GET_DOCUMENT_DETAILS_BY_ACCOUNT  "inputGetDocumentDetailsByAccount"
#define XML_ATL_INPUT_GET_BILL_DATES				   "inputGetBillDates"
#define XML_ATL_INPUT_GET_USAGE_DETAIL                 "inputGetUsageDetail"
#define XML_ATL_INPUT_RESET_VOICEMAIL				   "inputResetVoiceMail"
#define XML_ATL_INPUT_MARK_INVOICE_AS_PAID			   "inputMarkInvoiceAsPaid"
#define XML_ATL_INPUT_SEARCH_SBSCRP_BY_ATTRIBUTE	   "inputSearchCustAcctSbscrpByAttribute"
#define XML_ATL_INPUT_SERVICE_CHARGE				   "inputGetServiceCharge"



// Atributos de entrada no ATLYS
#define XML_ATL_ACCT_NBR                               "acctNbr"
#define XML_ATL_ACCT_ACCESSNBR                         "accessNbr"
#define XML_ATL_VAL                                    "val"
#define XML_ATL_ATTR_NAME                              "attrName"
#define XML_ATL_ATTR_VALUE                             "attrValue"
#define XML_ATL_FROM_DT                                "fromDt"
#define XML_ATL_TO_DT                                  "toDt"
#define XML_ATL_DT_INICIO                              "dataIni"
#define XML_ATL_DT_FIM                                 "dataFim"
#define XML_ATL_ATTR_HI_PRIORITY_ONLY                  "hiPriorityOnly"
#define XML_ATL_ACCT_SBSCRID                           "sbscrpId"
#define XML_ATL_SVCASGMINFO                            "svcAsgmInfo"
#define XML_ATL_ACCT_SVCNAME                           "svcName"
#define XML_ATL_ACCT_SVCNMTYPE                         "svcNmType"
#define XML_ATL_BILLUNBILLED                           "billUnbilledInd"
#define XML_ATL_DATA_SOURCE                            "dataSource"
#define XML_ATL_REQ_DT_TM                              "reqDtTm"
#define XML_ATL_INVOICE_MONTH                          "invoiceMonth"
#define XML_ATL_PYMT_DT                                "pymtDt"
#define XML_ATL_PYMT_AMT                               "pymtAmt"
#define XML_ATL_BANK_CD                                "bankCd"

// TAGs de saída no ATLYS
#define XML_ATL_FAULT                                  "fault"
#define XML_ATL_PYMTDET                                "pymtDet"
#define XML_ATL_ADJ                                    "adj"
#define XML_ATL_PYMT                                   "pymt"
#define XML_ATL_ACCT_NOTE                              "acctNote"
#define XML_ATL_PCSEQPATTR                             "pcsEqpAttr"
#define XML_ATL_OUTPUT_COLL_ACCT_STATE                 "outputGetCollAcctStateV2"
#define XML_ATL_OUTPUT_SEARCH_ACCOUNT_NOTES            "outputSearchAccountNotes"
#define XML_ATL_TOTAL_AMT_DUE                          "totalAmtDue"
#define XML_ATL_BASE                                   "base"
#define XML_ATL_BILL                                   "bill"
#define XML_ATL_REF_DATA_LST                           "refDataLst"
#define XML_ATL_OUTPUT_PAYMT_METHOD                    "outputGetPaymentMethod"
#define XML_ATL_PYMT_METHOD                            "paymentMethod"
#define XML_ATL_ACCT                                   "acct"
#define XML_ATL_SEARCH_CUSTOMER                        "searchCustomer"
#define XML_ATL_PRI_CUST_ID                            "priCustIdentifier"
#define XML_ATL_OUT_GET_AGED_BALANCES                  "outputGetAcctAgedBalances"
#define XML_ATL_PRIMARYSVC                             "primarySvc"
#define XML_ATL_SVC_REF_DATA                           "svcRefData"
#define XML_ATL_OUTPUT_GET_ACCOUNT_INFOV3              "outputGetAccountInfoV3"
#define XML_ATL_OUTPUT_GET_USAGE_BALANCE_V1            "outputGetUsageSummaryAndBalanceV1"
#define XML_ATL_OUTPUT_REMOVE_SUBS_HOT_LINE            "outputRemoveSubscriptionHotline"
#define XML_ATL_ESTIMATED_BALANCE                      "estimatedBalance"
#define XML_ATL_EST_AMT_TO_BE_BILLED				   "estAmtToBeBilled"
#define XML_ATL_ESTBAL                                 "estBal"
#define XML_ATL_CUR_BALL                               "curBal"
#define XML_ATL_AMOUNT_DUE                             "amountDue"
#define XML_ATL_ACCT_BC                                "acctBC"
#define XML_ATL_OUTPUT_GET_PAYMENT_DETAILS_BY_ACCOUNT  "outputGetPaymentDetailsByAccount"
#define XML_ATL_USG_SUMMARY                            "usgSummary"
#define XML_ATL_USAGE_ACCUM                            "usageAccum"
#define XML_ATL_DETAILS                                "details"
#define XML_ATL_OUTPUT_PENDING_PYMT_RSTRL              "outputPendingPymtRstrl"
#define XML_ATL_OUTPUT_MARK_INVOICE_AS_PAID            "outputMarkInvoiceAsPaid"
#define XML_ATL_DOCINFO								   "docInfo"
#define XML_ATL_USG_DTL                                "usgDtl"
#define XML_ATL_CHG                                    "CHG"


// Atributos de saída no ATLYS
#define XML_ATL_ERR_CD                                 "errCd"
#define XML_ATL_MSG                                    "msg"
#define XML_ATL_PYMT_REVERSAL                          "pymtReversal"
#define XML_ATL_CREDIT                                 "credit"
#define XML_ATL_AMT                                    "amt"
#define XML_ATL_DS_AJUSTE                              "desc"
#define XML_ATL_BILLED                                 "billed"
#define XML_ATL_NOTE_DATE                              "noteDate"
#define XML_ATL_NOTE_TIME                              "noteTime"
#define XML_ATL_NOTE_PRI                               "hiPriorityFlag"
#define XML_ATL_NOTE_DESC                              "noteText"
#define XML_ATL_SEQ_NBR                                "seqNbr"
#define XML_ATL_ESN                                    "esn"
#define XML_ATL_PUB_ID                                 "pubId"
#define XML_ATL_COLL_DATE                              "clctnEffDt"
#define XML_ATL_PYMT_CODE                              "pymtTypeCd"
#define XML_ATL_PYMT_TYPE_CODE                         "pymtTypeCode"
#define XML_ATL_CYCLE_CD                               "cycleCd"
#define XML_ATL_CODE                                   "code"
#define XML_ATL_DESC                                   "desc"
#define XML_ATL_LAST_BILL_DATE                         "lastBillDt"
#define XML_ATL_PYMT_DUE_DATE                          "pymtDueDt"
#define XML_ATL_ACTNBR                                 "actNbr"
#define XML_ATL_AMNT_DUE                               "amntDue"
#define XML_ATL_ACT_DUE_DATE                           "actDueDt"
#define XML_ATL_BILL_CYCLE_END_DATE                    "billCycleEndDt"
#define XML_ATL_FIRST_NAME                             "firstNm"
#define XML_ATL_MID_NAME                               "midNm"
#define XML_ATL_LAST_NAME                              "lastNm"
#define XML_ATL_TYPE_CD                                "typCd"
#define XML_ATL_TXT                                    "txt"
#define XML_ATL_TRUE                                   "true"
#define XML_ATL_FALSE                                  "false"
#define XML_ATL_CREATE_DT_TM                           "createDtTm"
#define XML_ATL_BAL_BILL_AMNT                          "balBillAmt"
#define XML_ATL_BAL_CURRENT_AMNT                       "balCurrentAmt"
#define XML_ATL_BAL_30_DAYS                            "bal30DayAmt"
#define XML_ATL_BAL_60_DAYS                            "bal60DayAmt"
#define XML_ATL_BAL_90_DAYS                            "bal90DayAmt"
#define XML_ATL_BAL_OVER_90_DAYS                       "balOver90DayAmt"
#define XML_ATL_RECEIVE_DT                             "receiveDt"
#define XML_ATL_OUT_SVCEXPRDT                          "svcExprDt"
#define	XML_ATL_OUT_SVCEFFDT						   "svcEffDt"
#define XML_ATL_OUT_SVCNM                              "svcNm"
#define XML_ATL_CHARGE                                 "charge"
#define XML_ATL_CHARGE_CD                              "chargeCd"
#define XML_ATL_CHARGE_AMOUNT                          "chgAmt"
#define XML_ATL_AUTO_EXPR_FLAG                         "autoExprFlag"
#define XML_ATL_ADMINSVC							   "adminSvcAttrs"
#define XML_ATL_SVCATTRNM							   "svcAttrNm"
#define XML_ATL_EVENT_TYPE                             "eventType"
#define XML_ATL_INCL_UNITS_USED                        "inclUnitsUsed"
#define XML_ATL_DOC_TYPE_CD							   "docTypeCd"
#define XML_ATL_DOC_TYPE_DESC						   "docTypeDesc"
#define XML_ATL_DOC_RMNG_AMT						   "docRmngAmt"
#define XML_ALT_CYCLEENDDT							   "cycleEndDt"
#define XML_ATL_EVENT_DT                               "EVENT_DT"
#define XML_ATL_CALLNG_ACCESS_NBR                      "CALLNG_ACCESS_NBR"
#define XML_ATL_CALLED_ACCESS_NBR                      "CALLED_ACCESS_NBR"
#define XML_ATL_SVC_TYPE_CD                            "SVC_TYPE_CD"
#define XML_ATL_ROUND_UNIT_QTY                         "ROUND_UNIT_QTY"
#define XML_ATL_PRICE_AMT                              "PRICE_AMT"


// Valores de atributos
#define XML_VAL_UNBILLED							   "U"
#define XML_VAL_VOICE                                  "V"
#define XML_VAL_SMS                                    "M"
#define XML_VAL_DADOS_C                                "C"
#define XML_VAL_DADOS_X                                "X"
#define XML_VAL_DADOS_DOWNLOAD                         "XL"
#define XML_DATA_SOURCE                                "E"

// Constantes de erro
#define NRO_ERR_ATLYS                                  "24E5999"
#define MSG_ERR_ATLYS                                  "Erro de comunicação com o Atlys"
#define MSG_ERR_ATLYS_ALLOC                            "Erro de alocacao de memoria"

#define NRO_ID_CONTA_SIS_ORG_NE                        "24E1001"
#define NRO_ID_CONTA_SIS_ORG_VV                        "24E1002"
#define NRO_ID_CONTA_SIS_ORG_VI                        "24E1003"
#define NRO_NR_LINHA_SIS_ORG_NE                        "24E1004"
#define NRO_NR_LINHA_SIS_ORG_VV                        "24E1005"
#define NRO_NR_LINHA_SIS_ORG_VI                        "24E1006"
#define NRO_CONTA_NAO_ENCONTRADA                       "24E1007"
#define NRO_DT_INICIO_NAO_ENCONTRADA_NE                "24E1008"
#define NRO_DT_INICIO_NAO_ENCONTRADA_VV                "24E1009"
#define NRO_DT_INICIO_NAO_ENCONTRADA_VI                "24E1010"
#define NRO_DT_FIM_NAO_ENCONTRADA_NE                   "24E1011"
#define NRO_DT_FIM_NAO_ENCONTRADA_VV                   "24E1012"
#define NRO_DT_FIM_NAO_ENCONTRADA_VI                   "24E1013"

#define ECD_ATL_TNE_OPERACAO                           "24E1014"
#define ECD_ATL_TVV_OPERACAO                           "24E1015"
#define ECD_ATL_TVI_OPERACAO                           "24E1016"


#define ECD_ATL_TNE_CALLCIRCLE                           "24E1017"
#define ECD_ATL_TNE_PRIMARYSVC                           "24E1018"
#define ECD_ATL_TNE_PRIMARYSVC_SVCNM                     "24E1019"
#define ECD_ATL_TNE_PRIMARYSVC_SVCNM_NOT_NULL            "24E1020"
#define ECD_ATL_TNE_REFDATALST_CODE                      "24E1021"
#define ECD_ATL_TNE_REFDATALST_DESC                      "24E1022"
#define ECD_ATL_TNE_OUTGETPAYMENTMETHOD_PYMTTYPECD       "24E1023"
#define ECD_ATL_TNE_ESTIMATEDBALANCE_ESTBAL_BASE_AMNTDUE "24E1024"
#define ECD_ATL_TNE_PRIMARYSVC_CALLCIRCLE                "24E1025"
#define ECD_ATL_TNE_OUTPUTGETBILLDATES                   "24E1026"
#define ECD_ATL_TVI_CDAREAREGISTRO                       "24E1027"
#define ECD_ATL_TVI_NRLINHA                              "24E1028"
#define ECD_ATL_TNE_OUTPUTGETACCOUNTINFOV3               "24E1029"
#define ECD_ATL_TNE_ESTIMATEDBALANCE                     "24E1030"
#define ECD_ATL_TNE_ESTBAL                               "24E1031"
#define ECD_ATL_TNE_OUTPUTGETUSAGEBALANCEV1              "24E1032"
#define ECD_ATL_TNE_CUR_BALL                             "24E1033"
#define ECD_ATL_TNE_AMOUNT_DUE                           "24E1034"
#define ECD_ATL_TNE_ACCT_BC                              "24E1035"
#define ECD_ATL_TNE_FAULT                                "24E1036"
#define ECD_ATL_TNE_USG_SUMMARY                          "24E1037"
#define ECD_ATL_TNE_USAGE_ACCUM                          "24E1038"
#define	ECD_ATL_TNE_TOTAL_AMT_DUE						 "24E1044"	
#define ECD_ATL_TNE_EST_AMT_TO_BE_BILLED				 "24E1045"	

//Código para serviço que faz parte de pacote
#define ECD_ATL_TNE_SERVICO_PACOTE						 "24E1039"

//Retorno após pesquisa de Contas
#define ECD_ATL_TNE_CONTAS_0                             "24E1041"
#define ECD_ATL_TNE_CONTAS_MAIOR_1                       "24E1042"
#define ECD_ATL_TNE_CONTAS_ERRO_LEITURA                  "24E1043"
#define ECD_ATL_TNE_SEM_CONTAS							 "24E1046"



#define ECD_ATL_TNE_GEN                                  "24E1999"

// ATLYS envia erro na execução
#define ECD_ATL_FAULT                                    "24E9001"
#define ECD_ATL_CODE_HOTLINE_NOT_SET                     "24E9002"
#define ECD_ATL_CODE_ACCOUNT_NOT_FOUND                   "24E9003"
#define ECD_ATL_CODE_ACCOUNT_NOT_IN_NPD                  "24E9004"
#define ECD_ATL_CODE_INVALID_INVOICE_DATA                "24E9005"
#define ECD_ATL_CODE_ACCOUNT_ALREADY_RESTORED            "24E9006"
#define ECD_ATL_CODE_NO_ACCOUNT                          "24E9007"
#define	ECD_ATL_CODE_INVALID_ACCESS_NUMBER				 "24E1262"

#define EDC_ATL_CODE_CAMPO_INVALIDO						 "24E9008"
#define	EDC_ATL_CODE_FALTA_CAMPO_OBRIGATORIO			 "24E9009"
#define EDC_ATL_CODE_DATA_INVALIDA						 "24E9010"
#define EDC_ATL_CODE_ERRO_APLICACAO						 "24E9011"
#define EDC_ATL_CODE_FATURA_INFORMADA					 "24E9012"
#define EDC_ATL_CODE_FATURA_EXCLUIDA					 "24E9013"
#define EDC_ATL_CODE_FATURA_NAO_LOCALIZADA				 "24E9014"
#define EDC_ATL_CODE_MULTIPLAS_FATURAS					 "24E9015"
#define EDC_ATL_CODE_VALOR_INVALIDO						 "24E9016"
#define EDC_ATL_CODE_BLOQUEIO_COBRANCA					 "24E9017"
#define EDC_ATL_CODE_STATUS_SUSPENSO					 "24E9018"
#define EDC_ATL_CODE_STATUS_HOTLINE						 "24E9019"
#define EDC_ATL_CODE_STATUS_DESABILITADO				 "24E9020"
#define EDC_ATL_CODE_STATUS_TROCA_NUM_PENDENTE			 "24E9021"
#define EDC_ATL_CODE_STATUS_OUTROS						 "24E9022"
#define EDC_ATL_CODE_STATUS_INVALIDO					 "24E9023"
#define ECD_ATL_CODE_SUBSCRIPTION_NOT_FOUND              "24E9024"
 
#define EMSG_ATL_TNE_FAULT                                "TAG fault não encontrada"
#define EMSG_ATL_TNE_CALLCIRCLE                           "TAG callCircle não encontrada"
#define EMSG_ATL_TNE_PRIMARYSVC                           "TAG primarySvc não encontrada"
#define EMSG_ATL_TNE_PRIMARYSVC_SVCNM                     "TAG PrimarySvc:svcNm não encontrada"
#define EMSG_ATL_TNE_PRIMARYSVC_SVCNM_NOT_NULL            "Valor para a busca de  PrimarySvc:svcNm, nao pode ser NULL"
#define EMSG_ATL_TNE_REFDATALST_CODE                      "TAG refDataLst:code não encontrada"
#define EMSG_ATL_TNE_REFDATALST_DESC                      "TAG refDataLst:desc não encontrada"
#define EMSG_ATL_TNE_OUTGETPAYMENTMETHOD_PYMTTYPECD       "TAG outputGetPaymentMethod:pymtTypeCd não encontrada"
#define EMSG_ATL_TNE_ESTIMATEDBALANCE_ESTBAL_BASE_AMNTDUE "TAG estimatedBalance:estBal:base:amntDue não encontrada"
#define EMSG_ATL_TNE_PRIMARYSVC_CALLCIRCLE                "TAG primarySvc:callCircle não encontrada"
#define EMSG_ATL_TNE_OUTPUTGETBILLDATES                   "TAG outputGetBillDates não encontrada"
#define EMSG_ATL_TVI_CDAREAREGISTRO                       "TAG CdAreaRegistro não encontrada"
#define EMSG_ATL_TVI_NRLINHA                              "TAG NrLinha não encontrada"
#define EMSG_ATL_TNE_OUTPUTGETACCOUNTINFOV3               "TAG outputGetAccountInfoV3 não encontrada"
#define EMSG_ATL_TNE_OUTPUTGETUSAGEBALANCEV1              "TAG outputGetUsageSummaryAndBalanceV1 não encontrada"
#define EMSG_ATL_TNE_ESTIMATEDBALANCE                     "TAG estimatedBalance não encontrada"
#define EMSG_ATL_TNE_TOTAL_AMT_DUE						  "TAG totalAmtDue não encontrada"	
#define EMSG_ATL_TNE_EST_AMT_TO_BE_BILLED				  "TAG estAmtToBeBilled não encontrada"
#define EMSG_ATL_TNE_ESTBAL                               "TAG estBal não encontrada"
#define EMSG_ATL_TNE_CUR_BALL                             "TAG curBal não encontrada"
#define EMSG_ATL_TNE_AMOUNT_DUE                           "TAG amountDue não encontrada"
#define EMSG_ATL_TNE_ACCT_BC                              "TAG acctBC não encontrada"
#define EMSG_ATL_TNE_USG_SUMMARY                          "TAG usgSummary não encontrada"
#define EMSG_ATL_TNE_USAGE_ACCUM                          "TAG usageAccum não encontrada"
#define EMSG_ATL_TNE_EVENT_TYPE                           "TAG eventType não encontrada"
#define EMSG_ATL_TNE_EVENT                                "TAG do evento não encontrada"
#define EMSG_ATL_TNE_CHG                                  "TAG CHG não encontrada"

#define EMSG_ATL_TNE_EVENT_DT                             "ATRIBUTO EVENT_DT não encontrado"
#define EMSG_ATL_TNE_CALLNG_ACCESS_NBR                    "ATRIBUTO CALLNG_ACCESS_NBR não encontrado"
#define EMSG_ATL_TNE_CALLED_ACCESS_NBR                    "ATRIBUTO CALLED_ACCESS_NBR não encontrado"
#define EMSG_ATL_TNE_SVC_TYPE_CD                          "ATRIBUTO svcTypeCd não encontrado"


#define EMSG_ATL_TNE(TNE)                              "TAG "TNE" nao encontrada."
#define EMSG_ATL_TVV(TVV)                              "TAG "TVV" com valor vazio."
#define EMSG_ATL_TVI(TVI)                              "TAG "TVI" com valor invalido."

#define MSG_ID_CONTA_SIS_ORG_NE                        "TAG idcontasistemaorigem não encontrada"
#define MSG_ID_CONTA_SIS_ORG_VV                        "TAG idcontasistemaorigem com valor vazio"
#define MSG_ID_CONTA_SIS_ORG_VI                        "TAG idcontasistemaorigem com valor inválido"
#define MSG_NR_LINHA_SIS_ORG_NE                        "Numero da linha esta nula"
#define MSG_NR_LINHA_SIS_ORG_VV                        "Numero da linha esta vazia"
#define MSG_NR_LINHA_SIS_ORG_VI                        "Numero da linha invalida"
#define MSG_CONTA_NAO_ENCONTRADA                       "A conta desta linha não foi encontrada"
#define MSG_DT_INIC_NAO_ENCONTRADA_NE                  "Data inicio nao encontrada"
#define MSG_DT_INIC_NAO_ENCONTRADA_VV                  "Data inicio com valor vazio"
#define MSG_DT_INIC_NAO_ENCONTRADA_VI                  "Data inicio com valor inválido"
#define MSG_DT_FIM_NAO_ENCONTRADA_NE                   "Data fim nao encontrada"
#define MSG_DT_FIM_NAO_ENCONTRADA_VV                   "Data fim com valor vazio"
#define MSG_DT_FIM_NAO_ENCONTRADA_VI                   "Data fim com valor inválido"

#define MSG_ATL_TNE_CONTAS_0                           "Não há contas pendentes"
#define MSG_ATL_TNE_CONTAS_MAIOR_1                     "Não é possível efetuar religue com mais de uma conta em aberto"
#define MSG_ATL_TNE_CONTAS_ERRO_LEITURA                "Erro ao ler quantidade de contas pendentes"


// códigos de erro do ATLYS
#define ERR_ATL_CODE_HOTLINE_NOT_SET                   3613
#define ERR_ATL_CODE_ACCOUNT_NOT_FOUND                 1316
#define ERR_ATL_CODE_ACCOUNT_NOT_IN_NPD                4219
#define ERR_ATL_CODE_INVALID_INVOICE_DATA              4222
#define ERR_ATL_CODE_ACCOUNT_ALREADY_RESTORED          4307
#define ERR_ATL_CODE_NO_ACCOUNT                        4308
#define ERR_ATL_CODE_INVALID_ACCESS_NUMBER			   1262
#define ERR_ATL_CODE_MULTIPLAS_FATURAS				   4335	

// mensagens de erro
#define MSG_ATL_CODE_HOTLINE_NOT_SET                   "Linha não está em Hotline"
#define MSG_ATL_CODE_ACCOUNT_NOT_FOUND                 "Número de conta indicado não foi encontrado"
#define MSG_ATL_CODE_ACCOUNT_NOT_IN_NPD                "A conta não está em status ativo ou NPD"
#define MSG_ATL_CODE_INVALID_INVOICE_DATA              "As informações fornecidas sobre a fatura são inválidas"
#define MSG_ATL_CODE_ACCOUNT_ALREADY_RESTORED          "A conta já foi restaurada de cobrança por pagamentos pendentes para este período"
#define MSG_ATL_CODE_NO_ACCOUNT                        "Nenhuma conta em cobrança coincide com os critério de pesquisa"
#define MSG_ATL_INVALID_ACCESS_NUMBER					"Status da linha é inválido para realização dessa operação"
#define MSG_ATL_SEM_CONTAS								"Não existe contas."

#define MSG_ATL_CODE_CAMPO_INVALIDO						"Valor de campo inválido"	
#define MSG_ATL_CODE_FALTA_CAMPO_OBRIGATORIO			"Está faltando um campo obrigatório"
#define MSG_ATL_CODE_DATA_INVALIDA						"Data do informe de pagamento deve ser na data de hoje ou antes dela"
#define MSG_ATL_CODE_ERRO_APLICACAO						"Erro na aplicação"
#define MSG_ATL_CODE_FATURA_INFORMADA					"Informe não pode ser realizado. Fatura informada anteriormente"
#define MSG_ATL_CODE_FATURA_EXCLUIDA					"Informe não pode ser realizado. Fatura excluída de Cobrança"
#define MSG_ATL_CODE_FATURA_NAO_LOCALIZADA				"Fatura não localizada"
#define MSG_ATL_CODE_MULTIPLAS_FATURAS					"Múltiplas Faturas localizadas"
#define MSG_ATL_CODE_VALOR_INVALIDO						"Valor pago diverge do saldo remanescente da fatura" 
#define MSG_ATL_CODE_BLOQUEIO_COBRANCA					"Bloqueio por Cobrança - Cliente continua com bloqueio total"
#define MSG_ATL_CODE_STATUS_SUSPENSO					"Status Suspenso"
#define MSG_ATL_CODE_STATUS_HOTLINE						"Status Hotline"
#define MSG_ATL_CODE_STATUS_DESABILITADO				"Status Desabilitado"
#define MSG_ATL_CODE_STATUS_TROCA_NUM_PENDENTE			"Status de troca de número pendente"
#define MSG_ATL_CODE_STATUS_OUTROS						"Status desconhecido"
#define MSG_ATL_CODE_STATUS_INVALIDO					"Status nulo"
#define MSG_ATL_CODE_SUBSCRIPTION_NOT_FOUND             "Subscrição inválida"

// chaves do Parametro
#define	PAR_CAIXA_POSTAL								"ATLYS_SERV_VOICEMAIL"
#define PAR_IDENTIFICADOR								"ATLYS_SERV_CALLID"
#define PAR_BLOQUEIO_DDI								"ATLYS_SERV_UNBLOCKDDI"
#define PAR_BLOQUEIO_DDD								"ATLYS_SERV_UNBLOCKDDD"

#define PAR_CHAMADA_ESPERA								"ATLYS_SERV_CALLWAIT"
#define PAR_TRANSF_CHAMADAS								"ATLYS_SERV_CALLFWD"
#define PAR_SERVICO_WAP									"ATLYS_SERV_WAP"

#define COD_FO_CAIXA_POSTAL								"VOICEMAIL"
#define COD_FO_IDENTIFICADOR							"CALLID"
#define COD_FO_CHAMADA_ESPERA							"CALLWAIT"		
#define COD_FO_DESVIO_CHAMADAS							"CALLFWD"
#define COD_FO_BLOQUEIO_DDD								"BLOCKDDD"
#define COD_FO_BLOUQEIO_DDI								"BLOCKDDI"
#define COD_FO_SERVICO_WAP								"WAP"					
#define COD_FO_FAVORITOS								"FVT"				
#define COD_FO_BASICO_NUMERO							"BN"
#define COD_FO_CONFERENCIA								"CONF"
#define COD_FO_ANTI_IDENTIF_CHAMADA						"ANTI IDENTIF CHAMADA"
#define	COD_FO_TORPEDO_EMPRESAS							"TORPEDOEMPRESAS"
#define	COD_FO_BLOQ_LIGACOES_COBRAR						"BLOQCOBRAR"

//utilizado apenas no gethistoricofaturamento

struct stFaturamento
{

	string docDueDt;  //será ordenado pelo vencimento da fatura.   
	string docTypeCd;
	string docTypeDesc;
	string docRmngAmt;
	string cfnclPblnId;
	string cCycleEndDt;
	string cCycleCd;
	string docDueAmt;
};


// criação de um container
typedef map<string, stFaturamento> FATURAMENTO;


 
using namespace std;

class PlugInATLYS : public PlugInBE
{
public:

	PlugInATLYS(DOMNode*, XMLGen*);
	PlugInATLYS(DOMNode*, XMLGen*, char*);
	
	~PlugInATLYS();

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
	void getNotaFiscal(){};
	void getDetalhesConsumo();
	void getTipoFatura();
	void getServicosURA();

	void setServico();
	void setSuspendeCelular();
	void setReligueCelular();
	void setFavorito();
	void setInterceptacao();
	void setCliente();
	void setCaixaPostal();
	void setTipoFatura();
	

private:

	char* traduzFormaPagto(char*);
	int   checaDebitoAutomatico(char*);
	void  montaXMLEndereco(char*);
	void  formataData(char*);
    void  formataHora(char*);
    char* formataDataHora(char *, char *);
	char* traduzNomeServicoToATLYS(char*, int );
	void  callSetServico(char*);
	void  trataErro(DOMNode*, char* = NULL);
	void  trataErroF2(DOMNode*, char* = NULL);
	void  getAtributosTorpedo(int iNrLinha, char* cNmServico, char *cSubject, char* cNickName);
	void  Insere(char *docDueDt, char *docTypeCd, char *docTypeDesc, char *docRmngAmt, char *cfnclPblnId, char *cCycleEndDt, char *cCycleCd, char *docDueAmt);
	void  updateService(char*nomeServico,char*cSbscrpId,DOMNode*auxDOMSvcAsgmInfo,DOMNode*auxDOMPrimarySvc,char*pcSrvTyp);
	
	bool   bOperacaoInterna;
	double m_saldoParcial;
	double m_minutosUtilizados;
	int    m_qtContasAberto;
	int	   m_qtContasVencidas;

	/*utilizado apenas no gethistoricofaturamento.*/ 




};


#endif
