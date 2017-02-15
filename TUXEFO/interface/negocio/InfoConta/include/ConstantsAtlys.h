#ifndef CONSTANTSATLYS_H
#define CONSTANTSATLYS_H

////////////////////////////////////////////////////////////////
// Constantes do atlys apis

#define SERVICE_ATLYS                           "AcessoATLYS"
#define XML_TAG_INPUT_GET_BILL_DATES			"inputGetBillDates"
#define XML_TAG_INPUT_GET_BILL_IMAGE			"inputGetBillImage"
#define XML_TAG_INPUT_SEARCH_SUBS_BY_ACCOUNT	"inputSearchSubscriptionByAccountNumber"
#define XML_TAG_OUTPUT_SEARCH_SUBS_BY_ACCOUNT	"outputSearchSubscriptionByAccountNumber"
#define XML_TAG_OUTPUT_GET_BILL_IMAGE			"outputGetBillImage"


////////////////////////////////////////////////////////////////
// Constantes de atributos

#define XML_ATTR_ACCT_NBR						"acctNbr"
#define XML_ATTR_CYCLE_CD						"cycleCd"
#define XML_ATTR_CYCLE_ST_DT					"cycleStDt"
#define XML_ATTR_CYCLE_END_DT					"cycleEndDt"
#define XML_ATTR_INVOICE_NUM					"invoiceNum"
#define XML_ATTR_DOCUMENT_TYPE					"documentType"
#define XML_ATTR_ARE_MORE						"areMore"
#define XML_ATTR_START_KEY						"startKey"
#define XML_ATTR_AFP_FILE						"afpFile"
#define XML_ATTR_FILE_FORMAT					"fileFormat"
#define XML_ATTR_ACCESSNBR						"accessNbr"


////////////////////////////////////////////////////////////////
// Constantes de TAGS


#define XML_TAG_BILL_DT							"billDt"
#define XML_TAG_BILL_IMAGE						"billImage"
#define XML_TAG_FAULT							"fault"

////////////////////////////////////////////////////////////////
// Constantes de valores para entrada

#define DOCUMENT_TYPE_B							"B"  // This is the value for BILL  
#define DOCUMENT_TYPE_C1						"C1" // This is the value for COLLECTIONS_1  
#define DOCUMENT_TYPE_C2						"C2" // This is the value for COLLECTIONS_2  
#define DOCUMENT_TYPE_U							"U"  // This is BUD.  
#define DOCUMENT_TYPE_R							"R"  // This is REM.  
#define DOCUMENT_TYPE_H							"H"  // This is HIRCHRPT.  
#define DOCUMENT_TYPE_I							"I"  // This is PCE_INI.  
#define DOCUMENT_TYPE_S							"S"  // This is PCE_SCRIPT.  
#define DOCUMENT_TYPE_PE						"PE" // This is PCE_ERROR_MSG.  
#define DOCUMENT_TYPE_PC						"PC" // This is PCE_CRITERIA_CORE.  
#define DOCUMENT_TYPE_PT						"PT" // This is PCE_CRITERIA_TELESP.  
#define DOCUMENT_TYPE_BB						"BB" // This is BUD_BATCH.  
#define DOCUMENT_TYPE_RB						"RB" // This is REM_BATCH.  

#define DOCUMENT_TYPE_NUM_B							1  // This is the value for BILL  
#define DOCUMENT_TYPE_NUM_C1						2  // This is the value for COLLECTIONS_1  
#define DOCUMENT_TYPE_NUM_C2						3  // This is the value for COLLECTIONS_2  
#define DOCUMENT_TYPE_NUM_U							4  // This is BUD.  
#define DOCUMENT_TYPE_NUM_R							5  // This is REM.  
#define DOCUMENT_TYPE_NUM_H							6  // This is HIRCHRPT.  
#define DOCUMENT_TYPE_NUM_I							7  // This is PCE_INI.  
#define DOCUMENT_TYPE_NUM_S							8  // This is PCE_SCRIPT.  
#define DOCUMENT_TYPE_NUM_PE						9  // This is PCE_ERROR_MSG.  
#define DOCUMENT_TYPE_NUM_PC						10 // This is PCE_CRITERIA_CORE.  
#define DOCUMENT_TYPE_NUM_PT						11 // This is PCE_CRITERIA_TELESP.  
#define DOCUMENT_TYPE_NUM_BB						12 // This is BUD_BATCH.  
#define DOCUMENT_TYPE_NUM_RB						13 // This is REM_BATCH.  

#define FILE_FORMAT_TYPE_A						"A"
#define FILE_FORMAT_TYPE_C						"CAF"



#endif