#ifndef TXPB_PLUGIN_NGIN_H
#define TXPB_PLUGIN_NGIN_H 1


#include "../../PlugInBE/include/PlugInBE.h"

/* 
** Constantes de configuracao
**
**  
** Codigos de erro: 24[EWI]3000 a 24[EWI]3999
**
**
*/
// Macros gerais para o NGIN
#define SERVICE_NGIN                                   "AcessoNGIN"            // Nome do servico NGIN
#define SERVICE_NGIN_EXTRATO                           "Ext"                   // Sufixo para identificar nome do servico NGIN usada para extrato
#define DATE_FORMAT_NGN_IN                             "DD/MM/YYYY"            // Formato da data usada no NGIN (envio)
#define DATE_FORMAT_NGN_OUT                            "DD/MM/YYYY"            // Formato da data usada no NGIN (recebimento)
#define DATE_FORMAT_NGN_OUT_SALDO                      "DD-MM-YYYY"
#define BUFFER_SIZE_NGN_XML_IN_GET                     2048                    // Tamanho do buffer do XML de entrada no NGIN (metodos get) 
#define BUFFER_SIZE_NGN_XML_IN_SET                     4096                    // Tamanho do buffer do XML de entrada no NGIN (metodos set)
#define BUFFER_SIZE_NGN_ERRMSG                         512                     // Tamanho do buffer de mensagem de erro do NGIN (retorno de erro do GW)

#define ESN_DEC_LEN                                    11
#define ESN_DEC_P1                                     3
#define ESN_DEC_P2                                     8

// Valores da TAG 'service' para o NGIN
#define XML_NGN_SVC_GET_ESNCTN                         "mblGetEsnCtn"
#define XML_NGN_SVC_GET_BALANCE                        "mblGetBalance"
#define XML_NGN_SVC_GET_RECHARGE_HIST                  "mblGetRechargeHistory"
#define XML_NGN_SVC_GET_DETAIL_REPORT                  "mblGetDetailedReport"
#define XML_NGN_SVC_GET_PROMOTIONS                     "mblGetPromotions"
#define XML_NGN_SVC_GET_SERVICES                       "mblGetServices"
#define XML_NGN_SVC_GET_FAVORITE_NRS                   "mblGetFavoriteNbrs"
#define XML_NGN_SVC_CHANGE_SERVICE                     "mblChangeService"
#define XML_NGN_SVC_RESET_VOICE_MAIL                   "mblResetVoiceMail"
#define XML_NGN_SVC_CHANGE_SUSP_MOBILE                 "mblChangeSuspendMobile"
#define XML_NGN_SVC_CHANGE_FAVORITE_NR                 "mblChangeFavoriteNbr"
#define XML_NGN_SVC_SET_INTERCEPTION                   "mblSetInterception"
#define XML_NGN_SVC_SET_CLIENT_INFO                    "mblSetClientInfo"
#define XML_NGN_SVC_GET_PLANO                          "mblGetProfile"
#define XML_NGN_SVC_SET_PLANO_ANT                      "mblChangeProfile"
#define XML_NGN_SVC_SET_PLANO                          "mblGenChangeProfile"
#define XML_NGN_SVC_GET_TARIFA_REDUZIDA                "mblGetDiaV"
#define XML_NGN_SVC_SET_TARIFA_REDUZIDA                "mblChangeDiaV"
#define XML_NGN_SVC_GET_FAVORITE                       "mblGetFavNumber"
#define XML_NGN_SVC_SET_FAVORITE                       "mblChangeFavNumber"
#define XML_NGN_SVC_GET_FAVORITE_NUMBERS			   "mblGetFavoriteNumbers"
#define XML_NGN_SVC_SET_FAVORITE_NUMBERS			   "mblChangeFavoriteNumbers"
#define XML_NGN_SVC_GET_EQUIPMENT_NET_INFO			   "mblGetEquipmentNetworkInfo"
#define XML_NGN_SVC_TRANS_RESET_VOICE_MAIL			   "mblTransResetVoiceMailPwd"
#define XML_NGN_GET_POST_PAID_INVOICE_ID_HISTORY       "invGetPostPaidInvoiceIdHistory"
#define XML_NGN_GET_POST_PAID_INVOICE_ACCOUNT		   "mblGetPostPaidInvoiceAccount"
#define XML_NGN_GET_POST_PAID_INVOICE_ID_DETAIL		   "invGetPostPaidInvoiceIdDetail"
#define XML_NGN_SVC_TRANS_CHANGE_SERVICE			   "mblTransChangeService"

// Tags de entrada no NGIN
#define XML_NGN_IN_ATTRIBUTE                           "attribute"             // TAG 'attribute'
#define XML_NGN_IN_FIELD	                           "field"             // TAG 'attribute'
#define XML_NGN_IN_VALUE							   "value"
#define XML_NGN_IN_ATTRIBUTE_NAME                      "name"                  // Atributo 'name' da TAG 'attribute'
#define XML_NGN_IN_REQUEST							   "request"
#define XML_NGN_IN_SERVICE							   "service"

#define XML_NGN_IN_RECORD							   "record"

// Valores do attributo 'name' na TAG 'attribute' de entrada no NGIN
#define XML_NGN_ATTNM_IN_SID                           "InSid"
#define XML_NGN_ATTNM_SID							   "sid"
#define XML_NGN_ATTNM_ID_USER                          "idUser"
#define XML_NGN_IN_USERNAME							   "InUserName"
#define XML_NGN_IN_PROFILE							   "InProfile"
#define XML_NGN_IN_REASONCODE						   "InReasonCode"
#define XML_NGN_ATTNM_ID_TRANS                         "idTrans"
#define XML_NGN_IN_CELLNUMBER						   "InCellNumber"
#define XML_NGN_IN_MEDIA							   "InMedia"
#define XML_NGN_IN_OPERATOR							   "InOperator"
#define XML_NGN_IN_NEXTPROFILE						   "InNextProfile"
#define XML_NGN_ATTNM_ID_LINHA                         "idLinha"
#define XML_NGN_ATTNM_IN_START_DT                      "inStartDate"
#define XML_NGN_ATTNM_IN_END_DT                        "inEndDate"
#define XML_NGN_ATTNM_ID_OPER                          "idOper"
#define XML_NGN_ATTNM_ID_SERVICO                       "idServico"
#define XML_NGN_ATTNM_IN_REASON_CD                     "inReasonCode"
#define XML_NGN_ATTNM_IN_OBS                           "inObs"
#define XML_NGN_ATTNM_IN_SERIAL_NR                     "inSerialNbr"
#define XML_NGN_ATTNM_IN_CITY_NM                       "inCityName"
#define XML_NGN_ATTNM_IN_POLICE_REPORT_NR              "inPoliceReportNr"
#define XML_NGN_ATTNM_IN_POLICE_REPORT_DT              "inPoliceReportDate"
#define XML_NGN_ATTNM_IN_POLICE_STATION                "inPoliceStation"
#define XML_NGN_ATTNM_IN_NEW_NR                        "inNewNbr"
#define XML_NGN_ATTNM_IN_OLD_NR                        "inOldNbr"
#define XML_NGN_ATTNM_ACCOUNT_TYPE                     "accountType"
#define XML_NGN_ATTNM_CD_TP_ENTIDADE                   "codTipoentidade"
#define XML_NGN_ATTNM_NOME                             "nome"
#define XML_NGN_ATTNM_NOME_ABREVIADO                   "nomeAbreviado"
#define XML_NGN_ATTNM_CONFIDENCIAL                     "confidencial"
#define XML_NGN_ATTNM_NIF                              "nif"
#define XML_NGN_ATTNM_TP_NIF                           "tipoNif"
#define XML_NGN_ATTNM_BI                               "bi"
#define XML_NGN_ATTNM_TP_BI                            "tipoBi"
#define XML_NGN_ATTNM_RG_DT_EMISS                      "rgDataEmiss"
#define XML_NGN_ATTNM_RG_ORG_EXP                       "rgOrgExp"
#define XML_NGN_ATTNM_RG_STATE                         "rgState"
#define XML_NGN_ATTNM_DT_NASCIMENTO                    "dataNascimento"
#define XML_NGN_ATTNM_ESTADO_CIVIL                     "estadoCivil"
#define XML_NGN_ATTNM_CD_SEXO                          "codSexo"
#define XML_NGN_ATTNM_TELEFONE                         "telefone"
#define XML_NGN_ATTNM_FAX                              "fax"
#define XML_NGN_ATTNM_EMAIL                            "eMail"
#define XML_NGN_ATTNM_AGREG_FAMILIAR                   "agregFamiliar"
#define XML_NGN_ATTNM_PASSAPORTE                       "passaporte"
#define XML_NGN_ATTNM_TP_PASSAPORTE                    "tipoPassaporte"
#define XML_NGN_ATTNM_CARTA_CONDUCAO                   "cartaConducao"
#define XML_NGN_ATTNM_TP_CARTA_COND                    "tipoCartaCond"
#define XML_NGN_ATTNM_AO_CUIDADO_DE                    "aoCuidadoDe"
#define XML_NGN_ATTNM_OBS                              "Obs"
#define XML_NGN_ATTNM_CONSERVATORIA_REGISTRO           "conservatoriaRegisto"
#define XML_NGN_ATTNM_REGISTRO_COMERCIAL               "registoComercial"
#define XML_NGN_ATTNM_PROFISSAO                        "profissao"
#define XML_NGN_ATTNM_HABILITACOES                     "habilitacoes"
#define XML_NGN_ATTNM_TELEMOVEL                        "telemovel"
#define XML_NGN_ATTNM_TP_CONTA                         "tipoConta"
#define XML_NGN_ATTNM_SUB_TIPO                         "subTipo"
#define XML_NGN_ATTNM_RENDA_MENSAL                     "rendaMensal"
#define XML_NGN_ATTNM_CC                               "cc"
#define XML_NGN_ATTNM_BANK                             "bank"
#define XML_NGN_ATTNM_IEPJ                             "iepj"
#define XML_NGN_ATTNM_NAME_SUFFIX                      "nameSuffix"
#define XML_NGN_ATTNM_FIRST_NAME                       "firstName"
#define XML_NGN_ATTNM_LAST_NAME                        "lastName"
#define XML_NGN_ATTNM_NOME_CONTACTO                    "nomeContacto"
#define XML_NGN_ATTNM_CARTEIRA_TRABALHO                "carteiraTrabalho"
#define XML_NGN_ATTNM_ARRUAMENTO                       "arruamento"
#define XML_NGN_ATTNM_COMPLEMENTO                      "complemento"
#define XML_NGN_ATTNM_BAIRRO                           "bairro"
#define XML_NGN_ATTNM_CD_POSTAL                        "codPostal"
#define XML_NGN_ATTNM_CD_POSTAL2                       "codPostal2"
#define XML_NGN_ATTNM_CIDADE                           "cidade"
#define XML_NGN_ATTNM_ESTADO                           "estado"
#define XML_NGN_ATTNM_INDICATIVO_PAIS                  "indicativoPais"
#define XML_NGN_ATTNM_TP_ENDERECO                      "tipoEndereco"
#define XML_NGN_ATTNM_NUMERO                           "numero"
#define XML_NGN_ATTNM_ATTENDANCE_PASSWD                "attendancePasswd"
#define XML_NGN_ATTNM_CDDD                             "CDDD"
#define XML_NGN_ATTNM_MSISDN						   "MSISDN"
#define XML_NGN_ATTNM_TIMESTAMP						   "timestamp"
#define XML_NGN_ATTNM_NUM_TELF                         "NUMTELF"
#define XML_NGN_ATTNM_NOVO_PLANO                       "NOVPLAN"
#define XML_NGN_ATTNM_DIA_TARIFA_REDUZIDA              "DIAV"
#define XML_NGN_ATTNM_OPERACAO                         "OPERACAO"
#define XML_NGN_ATTNM_FAVORITO_INCLUIR                 "FAVORITOINCLUIR"
#define XML_NGN_ATTNM_FAVORITO_EXCLUIR                 "FAVORITOEXCLUIR"
#define XML_NGN_ATTNM_IN_MSISDN						   "inMSISDN"
#define XML_NGN_ATTNM_ININVOICE_ACCOUNT				   "inInvoiceAccount"
#define XML_NGN_ATTNM_OUT_INVOICE_ID				   "outInvoiceId"
#define XML_NGN_ATTNM_OUT_REF_PERIOD				   "outRefPeriod"
#define XML_NGN_ATTNM_OUT_BILLING_CYCLE			       "outBillingCycle"
#define XML_NGN_ATTNM_OUT_EXPIRE_DATE                  "outExpireDate"
#define XML_NGN_ATTNM_OUT_TOTAL_VALUE				   "outTotalValue"
#define XML_NGN_ATTNM_OUT_INVOICE_ID_STATUS			   "outInvoiceIdStatus"
#define XML_NGN_ATTNM_IN_INVOICE_ID     			   "inInvoiceId"
#define XML_NGN_ATTNM_IN_TEL_CONTATO			       "InTelContato"

// Valores da tag 'attribute'
#define XML_NGN_ATTNM_VAL_IN_SID                       "9500"                  // Identificador de Servico


// Tags de saida no NGIN
#define XML_NGN_OUT_RESPONSE                           "response"              // TAG 'response'
#define XML_NGN_OUT_RESPONSE_SUCCEED                   "succeed"               // Atributo 'succeed' da TAG 'response'
#define XML_NGN_OUT_BODY                               "body"
#define XML_NGN_OUT_RECORD                             "record"
#define XML_NGN_OUT_FIELD                              "field"                 // TAG 'field'
#define XML_NGN_OUT_FIELD_NAME                         "name"                  // Atributo 'name' da TAG 'field'
#define XML_NGN_OUT_FIELD_VALUE                        "value"                 // Atributo 'value' da TAG 'field'
#define XML_NGN_OUT_ERROR                              "error"                 // TAG 'error'
#define XML_NGN_OUT_ERROR_CODE                         "code"                  // Atributo 'code' da TAG 'error'
#define XML_NGN_OUT_ERROR_DESC                         "descriptor"            // Atributo 'description' da TAG 'error'


// Valores do attribute 'name' na TAG 'field' de saida no NGIN
#define XML_NGN_FLDNM_RESPONSE_TM                      "outResponseTime"
#define XML_NGN_FLDNM_CELL_MODEL_CD                    "outCellModelCode"
#define XML_NGN_FLDNM_CELL_MODEL_DESC                  "outCellModelDesc"
#define XML_NGN_FLDNM_CELL_MANUFACTURER                "outCellManufacturer"
#define XML_NGN_FLDNM_ESN                              "outESN"
#define XML_NGN_FLDNM_RECHARGE_VALUE                   "outRechargeValue"
#define XML_NGN_FLDNM_RECHARGE_DT                      "outRechargeDate"
#define XML_NGN_FLDNM_RECHARGE_PROCESS_DT              "outRechargeProcessDate"
#define XML_NGN_FLDNM_SOURCE                           "outSource"
#define XML_NGN_FLDNM_PROMOTION_DESC                   "outPromotionDescription"
#define XML_NGN_FLDNM_PROMOTION_SUBSCRIPT_DT           "outPromotionSubscriptionDate"
#define XML_NGN_FLDNM_PROMOTION_START_DT               "outPromotionStartDate"
#define XML_NGN_FLDNM_PROMOTION_END_DT                 "outPromotionEndDate"
#define XML_NGN_FLDNM_NAME                             "outName"
#define XML_NGN_FLDNM_CODE                             "outCode"
#define XML_NGN_FLDNM_SERVICE_DESC                     "outServiceDescription"
#define XML_NGN_FLDNM_STATUS                           "outStatus"
#define XML_NGN_FLDNM_VALID_PERIOD                     "outValidPeriod"
#define XML_NGN_FLDNM_FAVORITE_NR                      "outFavoriteNbr"
#define XML_NGN_FLDNM_ID_SERVICO                       "outIdServico"
#define XML_NGN_FLDNM_BALANCE_TYPE                     "outBalanceType"
#define XML_NGN_FLDNM_BALANCE_DESC                     "outBalanceDesc"
#define XML_NGN_FLDNM_BALANCE                          "outBalance"
#define XML_NGN_FLDNM_VALID_DATE                       "outValidDate"
#define XML_NGN_OUT_INVOICE_ACCOUNT					   "outInvoiceAccount"

#define XML_NGN_FLDNM_QT_CAD_REST                      "QUANTCADREST"
#define XML_NGN_FLDNM_QT_ALT_GRAT                      "QUANTALTGRAT"
#define XML_NGN_FLDNM_VL_FAV_INCL                      "VLFAVINCL"
#define XML_NGN_FLDNM_VL_FAV_ALT                       "VLFAVALT"
#define XML_NGN_FLDNM_FAV_VIG                          "FAVVIG"

#define XML_NGN_FLDNM_IND_TARIFA_PLANO                 "INDTARIFDIAV"
#define XML_NGN_FLDNM_VL_TARIFA_PLANO                  "VLTARIFDIAV"
#define XML_NGN_FLDNM_PLANO_ATUAL                      "PLANOATUAL"

#define XML_NGN_FLDNM_IND_TARIFA_REDUZIDA              "INDTARIFDIAV"
#define XML_NGN_FLDNM_VL_TARIFA_REDUZIDA               "VLTARIFDIAV"
#define XML_NGN_FLDNM_DIA_TARIFA_REDUZIDA              "DIAV"

#define XML_NGN_FLDNM_VAL_ALT_FAVORITOS                "VLFAVALT"
#define XML_NGN_FLDNM_VAL_INC_FAVORITOS                "VLFAVINCL"
#define XML_NGN_FLDNM_FAVORITOS_VIGENTES               "FAVVIG"
#define XML_NGN_FLDNM_QT_CAD_RESTANTES                 "QUANTCADREST"
#define XML_NGN_FLDNM_QT_ALT_GRATUIRAS                 "QUANTALTGRAT"


// Valores dos atributos
#define XML_NGN_VAL_FAMILYFRIENDS                      "FAMILYFRIENDS"
#define XML_NGN_VAL_FAVORNOVPLN						   "FAVORNOVPLN"


#define XML_NGN_VAL_PLANO_TODA_HORA                    13
#define XML_NGN_VAL_PLANO_DIA                          14
#define XML_NGN_VAL_PLANO_NOITE                        15

#define XML_NGN_VAL_ALT_PLANO_DIA                      1
#define XML_NGN_VAL_ALT_PLANO_NOITE                    2
#define XML_NGN_VAL_ALT_PLANO_TODA_HORA                3

#define XML_NGN_VAL_TARIFA_REDUZINDA_SEGUNDA           0
#define XML_NGN_VAL_TARIFA_REDUZINDA_SABADO            5

#define XML_NGN_VAL_GRUPO                              "G"
#define XML_NGN_VAL_PRIVADO                            "P"

#define XML_NGN_VAL_AVENIDA                            "AV"
#define XML_NGN_VAL_PRACA                              "PCA"
#define XML_NGN_VAL_ALAMEDA                            "AL"
#define XML_NGN_VAL_RUA                                "R"
#define XML_NGN_VAL_PASSAGEM                           "PAS"
#define XML_NGN_VAL_VIELA                              "VL"
#define XML_NGN_VAL_ESTRADA                            "ESTR"
#define XML_NGN_VAL_PARQUE                             "PQ"
#define XML_NGN_VAL_LADEIRA                            "LAD"
#define XML_NGN_VAL_LARGO                              "LGO"
#define XML_NGN_VAL_GALERIA                            "GLR"
#define XML_NGN_VAL_PONTE                              "PTE"
#define XML_NGN_VAL_RODOVIA                            "ROD"
#define XML_NGN_VAL_VIADUTO                            "VD"
#define XML_NGN_VAL_TRAVESSA                           "TRAV"
#define XML_NGN_VAL_PRAIA                              "PRA"
#define XML_NGN_VAL_ACESSO                             "ACS"
#define XML_NGN_VAL_JARDIM                             "JD"
#define XML_NGN_VAL_TREVO                              "TRV"
#define XML_NGN_VAL_VIA                                "VIA"
#define XML_NGN_VAL_BECO                               "BC"

/*  Codigos e mensagens de erro para o NGIN.
 *
 *  TNE - TAG XML nao existe.
 *  TVV - TAG XML com valor vazio.
 *  TVI - TAG XML com valor invalido.
 */

// Mensagens de erro de GW para o NGIN
#define EMSG_NGN_GW_NOT_RESPOND                        "GWNGIN nao respondeu"
#define EMSG_NGN_GW_RETURN_ERROR				       "GWNGIN retornou erro"


// Codigos de erro de verificacao do GW para o NGIN
#define ECD_NGN_GW_NOT_RESPOND                         "24E3001"        // GWNGIN nao retornou resposta.
#define ECD_NGN_GW_RETURN_ERROR                        "24E3002"		// GWNGIN retornou erro.


#define ECD_NGN_PARAMETROS_INVALIDOS_EXTR				"17001"
#define ECD_NGN_CONF_FAVORITOS_NAO_EXISTENTE			"90016"
#define ECD_NGN_CUSTO_OPERACAO_NAO_CONFIGURADO			"90077"
#define ECD_NGN_SERVICO_NAO_SUBSCRITO					"90006"
#define ECD_NGN_SALDO_INSUFICIENTE						"161207"
#define	ECD_NGN_CONTADOR_NAO_CONFIGURADO				"120024"
#define	ECD_NGN_REGRA_CUSTO_NAO_CONFIGURADA				"120025"
#define ECD_CUSTO_NAO_VALIDO							"120026"
#define ECD_NGN_PARAMETROS_INVALIDOS					"120037"
#define ECD_NGN_SALDO_ALTERACOES_GRATUITAS				"120038"
#define ECD_NGN_OPERACAO_NAO_PERMITIDA					"120052"
#define ECD_NGN_NAO_PERMITIDA_DESB_LDI_BLOQ_LDN			"90048"
#define ECD_NGN_SERVICO_SUSPENSO						"90010"
#define ECD_NGN_SERVICO_DESATIVADO_HERANCA				"90011"
#define ECD_NGN_SERVICO_SUSPENSO_						"90013"
#define ECD_NGN_TRANS_FAVORITOS_SERVICE_JA_ATIVO		"90002"
#define ECD_NGN_SET_FAVORITOS_SERVICE_NGIN_N_SUBSCRITO	"145004"



// Codigos de erro de verificacao de TAGs para o NGIN
#define ECD_NGN_OUT_TNE_RESPONSE                       "24E3003"        // XML_NGN_OUT_RESPONSE
#define ECD_NGN_OUT_TNE_BODY                           "24E3004"        // XML_NGN_OUT_BODY
#define ECD_NGN_OUT_TNE_RECORD                         "24E3005"        // XML_NGN_OUT_RECORD

// Codigos de erros genéricos
#define ECD_NGN_OUT_TNE_ESN                            "24E3900"        

// Mensagens de erro
#define EMSG_NGN_OUT_TNE_RESPONSE                      "Tag response não encontrada" 
#define EMSG_NGN_OUT_TNE_BODY                          "Tag body não encontrada"
#define EMSG_NGN_OUT_TNE_RECORD                        "Tag record não encontrada"

#define EMSG_NGN_OUT_FLDNM_ESN                         "Tag ESN com valor inválido"

#define ECD_BLOQUEIO_DDD_INVALIDO								  "24E9006"

#define ECD_SALDO_INSUFICIENTE_ATIVACAO							  "24E9007"

#define ECD_CLIENTE_SEM_PLANO_BOA_HORA							  "24E9008"

#define ECD_CELULAR_NAO_COMPATIVEL								  "24E9009"

#define ECD_RECARGA_INDISPONIVEL_VISUALIZACAO					  "24E9010"

#define ECD_SERVICO_ATIVADO_PACOTE								  "24E9011"

#define ECD_DDD_NAO_CADASTRADO_COMO_AUTORIZADO					  "24E9012"

#define ECD_REGIONAL_NAO_CADASTRADA_COMO_AUTORIZADA				  "24E9013"

#define ECD_SERVICO_NAO_SUBSCRITO								  "24E9014"

#define ECD_NAO_PERMITIDA_DESB_LDI_BLOQ_LDN						  "24E9015"

#define ECD_CONTADOR_NAO_CONFIGURADO						      "24E9016"

#define	ECD_REGRA_CUSTO_NAO_CONFIGURADA							  "24E9017"

#define ECD_SALDO_ALTERACOES_GRATUITAS							  "24E9018"

#define ECD_PARAMETROS_INVALIDOS							      "24E9019"

#define ECD_SERVICO_SUSPENSO						              "24E9020"

#define ECD_SERVICO_DESATIVADO_HERANCA							  "24E9021"

#define ECD_SERVICO_SUSPENSO_									  "24E9022"

#define ECD_CUSTO_NAO_VALIDO_TUXEDO								  "24E9023"

#define ECD_OPERACAO_NAO_PERMITIDA								  "24E9024"

#define ECD_SALDO_INSUFICIENTE									  "24E9025"


#define EMSG_BLOQUEIO_DDD_INVALIDO								  "Para realizar o Bloqueio LDN/LDI é necessário estar com o Bloqueio LDI ativo."

#define EMSG_SALDO_INSUFICIENTE_ATIVACAO						  "Saldo insuficiente para realizar esta operação."

#define EMSG_RECARGA_INDISPONIVEL_VISUALIZACAO					  "Não há recargas disponíveis para visualização."

#define EMSG_CELULAR_NAO_COMPATIVEL								  "Serviço não realizado. Verifique se o seu celular é compatível com o plano solicitado."

#define EMSG_CLIENTE_SEM_PLANO_BOA_HORA 						  "Para utilizar o Favoritos verifique se o celular possui saldo, se está ativo e cadastrado em um dos planos Vivo Boa Hora."

#define EMSG_SERVICO_ATIVADO_PACOTE								  "Não é possível efetuar a desativação. Este serviço faz parte do pacote."

#define EMSG_DDD_NAO_CADASTRADO_COMO_AUTORIZADO					  "O DDD não foi cadastrado na tabela de autorização para favoritos."

#define EMSG_REGIONAL_NAO_CADASTRADA_COMO_AUTORIZADA			  "A REGIONAL não foi cadastrada na tabela de autorização para favoritos."

#define EMSG__SERVICO_NAO_SUBSCRITO								  "NGIN retornou serviço nao subscrito"

#define EMSG_NAO_PERMITIDA_DESB_LDI_BLOQ_LDN					  "Nao é permitida a Liberação LDI e Bloqueio LDN."

#define EMSG_CONTADOR_NAO_CONFIGURADO						      "Contador não configurado."

#define	EMSG_REGRA_CUSTO_NAO_CONFIGURADA						  "Regra de Custo não configurada."

#define EMSG_SALDO_ALTERACOES_GRATUITAS							  "Saldo de alterações gratuitas não actualizado."

#define EMSG_PARAMETROS_INVALIDOS							      "Parametros inválidos em operação sobre saldo de alterações gratuitas."

#define EMSG_SERVICO_SUSPENSO						              "O serviço já está suspenso."

#define EMSG_SERVICO_DESATIVADO_HERANCA							  "O serviço já está desativado por herança."

#define EMSG_SERVICO_SUSPENSO_									  "O serviço está suspenso."

#define EMSG_CUSTO_NAO_VALIDO_TUXEDO							  "Custos não válidos."

#define EMSG_OPERACAO_NAO_PERMITIDA								  "Operação não permitida."

#define EMSG_SALDO_INSUFICIENTE									  "CORE, Balance is not enough"


class PlugInNGIN : public PlugInBE
{
public:

	PlugInNGIN(DOMNode* , XMLGen*, char*, char*);

	~PlugInNGIN();

	void getDetalheLinha();
	void getDetalhesSaldo();
	void getHistoricoMovimentos();
	void getExtrato();
	void getPromocoes();
	void getServicos();
	void getFavoritos();
	void getHistoricoAtendimento();
	void getESN();
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
	void getNotaFiscal();
	void getTarifaReduzida();
	void getPlano();
    void getAvailableServices();
	void getDetalheAparelho();
	void getTipoFatura(){};
	void getDetalheFatura();
	void getVelocidade();
	void getServicosURA();

	void setServico();
	void setSuspendeCelular();
	void setFavorito();
	void setInterceptacao();
	void setCliente();
	void setTarifaReduzida();
	void setPlano();
	void setCaixaPostal();
	void setTipoFatura(){};


private:

	int   iIdPlano;
	int   iIdDiaTarifaReduzida;
	bool  bOperacaoInterna;
	char  mc_serviceName[TXPB_SERVICE_NAME_SIZE];

	char* getServicoStatus(char*);
	char* traduzNomeToDescricao(char*);
	char* traduzNomeServicoToDescricao(char*);
	void  trataErro(DOMNode *);
	void  trataErroURA(DOMNode *);

	///
	char* getServiceNameExt();

	///
	void  getFavoritosPlanosNovos();
	void  getFavoritosPlanosAntigos();
	void  getFavoritosRequest();
	void  getFavoritosbyService(const char* pcServiceName, DOMNode **po_response, char *c_area, char *c_linha, char* pc_Linha, char* pc_idUser);

	///
	void  setFavoritoPlanosNovos();
	void  setFavoritoPlanosAntigos();
	void  setFavoritoRequest();

	///
	char* traduzTipoEndereco(char *pc_tipoEnderecoFinal, char *pc_tipoEndereco);

	///
	char* convertESNDecToHex(char *pc_EsnHex, char *pc_EsnDec);
};


#endif
