/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2011/04/06 23:54:50 $
 * @ID      $Id: tuxfwerrors.h,v 1.1.4.1 2011/04/06 23:54:50 a5114878 Exp $
 **/

#ifndef TUXFW_ERRORS
#define TUXFW_ERRORS

//namespace tuxfw
//{

//
// return constants
#define TUXFWRET_OK 1
#define TUXFWRET_ERROR -1

/// *****************************************************************
//  Constantes para mensagens de erro geradas pelo framework tuxedo
/// *****************************************************************
#define ERR_TUX_NOT_IMPL_CD				"99E0000"
#define ERR_TUX_NOT_IMPL_MSG			"Service Not Implemented"
#define ERR_TUX_DOM_EXCP_CD				"99E0001"
#define ERR_TUX_DOM_EXCP_MSG			"DOM Exception"
#define ERR_TUX_SYS_OUTOFMEM_CD			"99E0002"
#define ERR_TUX_SYS_OUTOFMEM_MSG		"Out of Memory"
#define ERR_TUX_XML_MALFORMED_CD		"99E0003"
#define ERR_TUX_XML_MALFORMED_MSG		"Malformed XML String"
#define ERR_TUX_XML_MALFORMEH_CD		"99E0004"
#define ERR_TUX_XML_MALFORMEH_MSG		"Malformed XML Header String"
#define ERR_TUX_XML_CANNOT_GEN_CD		"99E0005"
#define ERR_TUX_XML_CANNOT_GEN_MSG		"Cannot generate any XML"
#define ERR_TUX_REMOTE_CALL_PREP_CD		"99E0006"
#define ERR_TUX_REMOTE_CALL_PREP_MSG	"Error preparing remote call"
#define ERR_TUX_REMOTE_CALL_CD			"99E0007"
#define ERR_TUX_REMOTE_CALL_MSG			"Error processing tpcall[%s] tpstrerror(%d), tpurcode[%d]"
#define ERR_TUX_TPALLOC_RET_CD			"99E0008"
#define ERR_TUX_TPALLOC_RET_MSG			"Error allocating tuxedo buffer"
#define ERR_TUX_REMOTE_CALLTYPE_CD		"99E0009"
#define ERR_TUX_REMOTE_CALLTYPE_MSG		"Invalid remote call type (%d)"
#define ERR_TUX_BASICSVCFAIL_CD			"99E0010"	
#define ERR_TUX_BASICSVCFAIL_MSG		"Basic service activations returninf TPFAIL"
#define ERR_TUX_REMCALL_LOADSVCPARM_CD	"99E0011"	
#define ERR_TUX_REMCALL_LOADSVCPARM_MSG	"Error loading service custom paramters"
#define ERR_TUX_SVRCTX_GETSVCPARAM1_CD	"99E0012"	
#define ERR_TUX_SVRCTX_GETSVCPARAM1_MSG	"Configuration not loaded"
#define ERR_TUX_SVRCTX_GETSVCPARAM2_CD	"99E0013"	
#define ERR_TUX_SVRCTX_GETSVCPARAM2_MSG	"No service parameters in server configuration"
#define ERR_TUX_SVRCTX_GETSVCPARAM3_CD	"99E0014"	
#define ERR_TUX_SVRCTX_GETSVCPARAM3_MSG	"Invalid service configuration"
#define ERR_TUX_XML_EXCP_CD				"99E0015"
#define ERR_TUX_XML_EXCP_MSG			"XML Exception"
#define ERR_TUX_HELPER_WLKDOMMERR_CD	"99E0016"
#define ERR_TUX_HELPER_WLKDOMMERR_MSG	"tuxhelper_walkdom: Erros in XML processing"
#define ERR_TUX_XMLGEN_TRSPCCHARS_CD	"99E0017"
#define ERR_TUX_XMLGEN_TRSPCCHARS_MSG	"xmlgen: Error translating special chars, resulting field too long"
#define ERR_TUX_BASICSVC_MAXERRORCODESIZE_CD "99E0018"
#define ERR_TUX_BASICSVC_MAXERRORCODESIZE_MSG "tuxbasicsvc:wrong error code size"
#define ERR_TUX_BASICSVC_WRONGUSEOFTUXEXCP_CD "99E0019"
#define ERR_TUX_BASICSVC_WRONGUSEOFTUXEXCP_MSG "tuxbasicsvc:wrong use of TuxException, user [throw new TuxException(...)]"
#define ERR_TUX_XMLGEN_SPCCHARSINTAGORPROP_CD	"99E0020"
#define ERR_TUX_XMLGEN_SPCCHARSINTAGORPROP_MSG	"xmlgen: Error tag or property cannot have special chars [%s]."
#define ERR_TUX_XMLGEN_INVALIDPARAM_CD "99E0021"
#define ERR_TUX_XMLGEN_INVALIDPARAM_MSG "xmlgen: Invalid parameter"
#define ERR_TUX_XMLGEN_NOOPENTAG_CD "99E0022"
#define ERR_TUX_XMLGEN_NOOPENTAG_MSG "xmlgen: Cannot act with no open tag"
#define ERR_TUX_MSG_OUTOFMEM_CD "99E0023"
#define ERR_TUX_MSG_OUTOFMEM_MSG "Out of memory parsing message in TuxMessage"
#define ERR_TUX_MSG_MALFORMED_CD "99E0024"
#define ERR_TUX_MSG_MALFORMED_MSG "Malformed XML while parsing TuxMessage"
#define ERR_TUX_MSG_DOM_EXCP_CD "99E0025"
#define ERR_TUX_MSG_DOM_EXCP_MSG "DOM Exception in TuxMessage"
#define ERR_TUX_MSG_NOEXEC_TPFAIL_CD "99E0026"
#define ERR_TUX_MSG_NOEXEC_TPFAIL_MSG "PING, no exec FAIL"
#define ERR_TUX_MSG_NOEXEC_SUCESS_CD "99W0027"
#define ERR_TUX_MSG_NOEXEC_SUCESS_MSG "PING, no exec SUCCESS"
#define ERR_TUX_MSG_NOPING_CD "99I0028"
#define ERR_TUX_MSG_NOPING_MSG "PING, execute"
//
#define ERR_TUX_MSG_TUXHELPERGETNODEASSTR_CD "99E0029"
#define ERR_TUX_MSG_TUXHELPERGETNODEASSTR_MSG "Exception in TuxHelper::getNodeAsString"
//
#define ERR_TUX_MSG_TUXMESSAGENOHEADER_CD "99E0030"
#define ERR_TUX_MSG_TUXMESSAGENOHEADER_MSG "Exception in TuxMessage::parseXMLHeader no header found"
#define ERR_TUX_MSG_TUXMESSAGENOBODY_CD "99E0031"
#define ERR_TUX_MSG_TUXMESSAGENOBODY_MSG "Exception in TuxMessage::parseXMLHeader no body found"
#define ERR_TUX_REMCALL_LOADSVCPARMFLG_CD	"99E0032"	
#define ERR_TUX_REMCALL_LOADSVCPARMFLG_MSG	"Error loading service custom paramters, invalid flag"
//
#define ERR_TUX_BASICSVC_TOMANYRETURNDATA_CD     "99E0033"
#define ERR_TUX_BASICSVC_TOMANYRETURNDATA_MSG    "Excesso de dados de retorno,"
//
#define ERR_TUX_BASICSVC_UNKNOWNTEXT_CD     "99E9998"
#define ERR_TUX_BASICSVC_UNKNOWNTEXT_MSG    "Unknown exception [%s]"
#define ERR_TUX_BASICSVC_UNKNOWN_CD     "99E9999"
#define ERR_TUX_BASICSVC_UNKNOWN_MSG    "Unknown exception"


#define TUXFW_ERRLOC_TPSVRINIT 1;
#define TUXFW_ERRLOC_TPSVRDONE 2;
#define TUXFW_ERRLOC_INITSERVERCONTEXT 3;



//}
#endif