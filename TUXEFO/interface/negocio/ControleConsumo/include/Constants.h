#ifndef CONSTANTS_H
#define CONSTANTS_H

////////////////////////////////////////////////////////////////
// Constantes do atlys apis

#define SERVICE_ATLYS                           "ATLYSHTTP"
#define SERVICO_CONTROLE_CONSUMO				"CONTROLE DE CONSUMO"



#define XML_TAG_INPUT_SEARCH_SUBS_BY_ATTR		"inputSearchSubscriptionByAttribute"
#define XML_TAG_INPUT_GET_SUBS_INFO				"inputGetSubscriptionInfo"
#define XML_TAG_INPUT_MAINTAIN_SUBS_SERVICES	"inputMaintainSubscriptionServices"
#define XML_TAG_INPUT_ON_DEMAND_MOVISTAR		"inputOnDemandMovistarNtfctn"

#define XML_TAG_OUTPUT_SEARCH_SUBS_BY_ATTR		"outputSearchSubscriptionByAttribute"
#define XML_TAG_OUTPUT_GET_SUBS_INFO			"outputGetSubscriptionInfo"
#define XML_TAG_OUTPUT_MAINTAIN_SUBS_SERVICES	"outputMaintainSubscriptionServices"
#define XML_TAG_OUTPUT_ON_DEMAND_MOVISTAR		"outputOnDemandMovistarNtfctn"


////////////////////////////////////////////////////////////////
// Constantes de atributos

#define XML_ATTR_ACCT_NBR						"acctNbr"
#define XML_ATTR_ARE_MORE						"areMore"
#define XML_ATTR_START_KEY						"startKey"
#define XML_ATTR_ACCESSNBR						"accessNbr"
#define XML_ATTR_ATTR_NAME						"attrName"
#define XML_ATTR_ATTR_VALUE						"attrValue"
#define XML_ATTR_SBSCRPID						"sbscrpId"
#define XML_ATTR_ERRCD							"errCd"
#define XML_ATTR_MSG							"msg"
#define XML_ATTR_ATTR_NM						"attrNm"
#define XML_ATTR_SVC_ID							"svcId"
#define XML_ATTR_SVC_NM							"svcNm"
#define XML_ATTR_CHG_TYPE_CD					"chgTypeCd"
#define XML_ATTR_SVC_TYP						"svcTyp"

////////////////////////////////////////////////////////////////
// Constantes de erros

#define ERROR_PARSE_EXCEPTION					200

////////////////////////////////////////////////////////////////
// Constantes de mensagens de erros

#define ERROR_MSG_PARSE_EXCEPTION					"Dados de entrada inválidos"

////////////////////////////////////////////////////////////////
// Constantes de tags

#define XML_TAG_INPUT_OPERACAO						"OPERACAO"
#define XML_TAG_FAULT								"fault"
#define XML_TAG_UPDATE_SVC_ATTRS					"updateSvcAttrs"
#define XML_TAG_ADD_ATTRS							"addlAttrs"
#define XML_TAG_SVC_NM								"svcNm"
#define XML_TAG_SVC_TYP								"svcTyp"
#define XML_TAG_SVC_ID								"svcId"
#define XML_TAG_SVC_ATTR							"svcAttr"
#define XML_TAG_SBSCRP_SEARCH						"sbscrpSearch"
#define XML_TAG_SVC_ASGM_INFO						"svcAsgmInfo"
#define XML_TAG_MEMBER_SVCS							"memberSvcs"
#define XML_TAG_CHARGE								"charge"
#define XML_TAG_PRIMARY_SVC							"primarySvc"





#endif