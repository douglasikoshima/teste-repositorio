/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1 $
 * @CVS     $Author: rrusso $ - $Date: 2006/06/07 22:20:20 $
 * @ID      $Id: tuxfwbasicsvc.h,v 1.1 2006/06/07 22:20:20 rrusso Exp $
 **/

#ifndef TuxFW_TuxBasicSvc
#define TuxFW_TuxBasicSvc

//namespace tuxfw
//{

#define TUXFW_BASICSVC_MAXERRORCODESIZE 7
#define TUXFW_PING_NOPING 0
#define TUXFW_PING_NOEXEC_SUCESS 1
#define TUXFW_PING_NOEXEC_TPFAIL 2
#define TUXFW_PING_NOEXEC_BD 3


#if !defined(WIN32)
#include<sys/time.h>
#endif
/*****************************************************************************
	Tuxedo basic class
*****************************************************************************/
//##ModelId=41079D2A0000
class TuxBasicSvc:public TuxHelper
{
public:

	//	Constructor and Destructor
	//##ModelId=41079D2A000F
	TuxBasicSvc(char* pTuxSvcName =0L);
	//##ModelId=41079D2A0011
	~TuxBasicSvc();

	//
	//	Virtual Method - Service Execute
	//		XMLIn => XML node called "msgBody"
	//		XMLOut => XML retrieved from service to aggregate on XML
	//##ModelId=41079D2A0012
	virtual void Execute(DOMNode*dnode,XMLGen*xmlg); // throw(TuxException);
	//
	// Internal service call for Tuxedo
	//##ModelId=41079D2A0016
	int callService(void*pdata,char**pret,int*plen,char*ptuxNm);
	//
	// remote invocation of simple remote services
	//int remoteCall(char* pServiceName, DOMNode* pXMLIn,DOMNode* pXMLOut) throw(TuxException);
	//##ModelId=41079D2A0022
	int remoteCall(char* pServiceName, XMLGen* pXMLIn, TuxMessage* pMessageOut) throw (TuxException);
	//
	//##ModelId=41079D2A0026
	int remoteCall( TuxRemoteService* service ) throw(TuxException);

	//##ModelId=41079D2A002E
	int outputLog(char*pcode, char* pmsg);
/*!
 * Generates messages in the logdevice configured at the server startup
 * @param type - log entry type : LogDebug, LogInfo, LogWaening, LogCritical
 * @param pszFormat - format string like printf
 * @param ... - variable arguments like printf
 */
	//##ModelId=41079D2A0031
	void outputLog( LogType type, char *pszFormat, ...);


protected:
	//
	//	getCorrID - retrieve ID from msgHdr (optional)
	//	
	//##ModelId=41079D2A0035
	char*getCorrID();
	//
	//	getHost - retrieve Host from msgHdr (optional)
	//##ModelId=41079D2A003E
	char*getHost();
	/*****************************************************************************
		getVersion - retrieve Version from msgHdr (optional)
	*****************************************************************************/
	//##ModelId=41079D2A003F
	char*getVersion();
	/*****************************************************************************
		getSequence - retrieve Sequence from msgHdr (optional)
	*****************************************************************************/
	//##ModelId=41079D2A0040
	char*getSequence();
	//
	//getOperation - retrieve Operation from msgHdr (optional)
	//##ModelId=41079D2A0041
	char*getOperation();
	/*****************************************************************************
		getEntity - retrieve Entity from msgHdr (optional)
	*****************************************************************************/
	//##ModelId=41079D2A004E
	char*getEntity();
	/*****************************************************************************
		getUser - retrieve user key from msgHdr
	*****************************************************************************/
	//##ModelId=41079D2A004F
	char*getUser();
	/*****************************************************************************
		getSubSystem - retrieve subsystem from msgHdr
	*****************************************************************************/
	//##ModelId=41079D2A0050
	char*getSubSystem();
	/*****************************************************************************
	 * 
		getCreationTimeStamp - retrieve Creation Time Stamp key from msgHdr
	*****************************************************************************/
	//##ModelId=41079D2A0051
	char*getCreationTimeStamp();
	/*****************************************************************************
		getProcessTimeStamp - retrieve Process Time Stamp key from msgHdr
	*****************************************************************************/
	//##ModelId=41079D2A005D
	char*getProcessTimeStamp();
	/*****************************************************************************
		getUUID - retrieve unique id key from msgHdr
	*****************************************************************************/
	//##ModelId=41079D2A005E
	char*getUUID();
	/*****************************************************************************
		getHdrAttr - retrieve any attribute from msgHdr
	*****************************************************************************/
	//##ModelId=41079D2A005F
	char*getHdrAttr(char*pattr);
	// 
	//	getService - retrieve service name from msgHdr
	//##ModelId=41079D2A0061
	char*getService();
	/*****************************************************************************
		setStatusCode - set status code of execution.
				statusCode => Text in format

				00I0000
				|||||||
				|||++++--> a numeric ReturnCode of function (4 numeric digits)
				||+------> flag indicate: "I"nformation "W"arning "E"rror
				||			I - cause Information log (commit if in transaction context)
				||			W - cause Warning log (commit if in transaction context)
				||			E - cause Error log (rollback if in transaction context)
				++-------> two digits of subsystem ID
		Parameters:
			statusCode => composed status code
			statusText => text message of composed status code.
							If this not provided, the function queries the database
							to find the correct message text.
	*****************************************************************************/
	//##ModelId=41079D2A006D
	int setStatusCode(char*pCode,char*pMsg);
	//##ModelId=41079D2A0070
	int setStatusCode(char*pCode);
	//
	//	setStatusCodeOra - set status code of Oracle errors (numeric error)
	//##ModelId=41079D2A0072
	int setStatusCodeOra(int iCode);
	//
	//setStatusCodeOra - set status code of Oracle errors (ORA-XXXXX)
	//##ModelId=41079D2A007E
	int setStatusCodeOra(char*pCode);
	//
	// setStatusCodeOra - set status code of Oracle errors (numeric error)
	//##ModelId=41079D2A0080
	int setStatusCodeOra(int iCode,char*mi);
	//
	//	queryStatusCode - retrieve internal status code
	//##ModelId=41079D2A0083
	int queryStatusCode();
	//
	// getTuxServiceName - retrieve internal Tuxedo service name
	//##ModelId=41079D2A008C
	int getTuxServiceName();
	//
	// retorna o valor do ping 
	int getPing();
private:
	//##ModelId=41079D2A008E
	TuxServerContext* serverContext;
	//##ModelId=41079D2A0092
	int translateSubsystemCode(char *pin,char * *pout,int reserved);
	//##ModelId=41079D2A009F
	int translateOraECDToString(int icd,char * *ppmsg,char * *ppcd);
	//##ModelId=41079D2A00AB
	const char*oraGetLiteralStrErrorAndCode(int icd,char * *pErrCd);
	//##ModelId=41079D2A00AE
	const char*oraGetLiteralStrError(char *pErrCd);
	//##ModelId=41079D2A00BB
	const char*binSearch(int rfID,char * *pErr);
	//##ModelId=41079D2A00BE
	int getMessageString(char *pcd);
	//##ModelId=41079D2A00C0
	int analyser(char *pCd);
	//##ModelId=41079D2A00CC
	int oraanalyser(int icd);
	//##ModelId=41079D2A00CE
	int getMid(int uppr,int lwr);
	//##ModelId=41079D2A00DA
	int retrieveStatus();
	//##ModelId=41079D2A00DB
	int parseXMLHeader(DOMNode *pnode);
	//##ModelId=41079D2A00DD
	int retrieveXMLMessage(XMLGen *pxmlin,XMLGen *pxmlout);
	// seta o valor de ping 
	void setPing(int ping);
	//##ModelId=4107A0F3032C
	char*pusr;
	//##ModelId=4107A0F3033C
	char*psvc;
	//##ModelId=4107A0F3034B
	char*ptsn;
	//##ModelId=4107A0F3035B
	char*pcid;
	//##ModelId=4107A0F3036B
	char*phst;
	//##ModelId=4107A0F3037A
	char* pcd;
	//##ModelId=4107A0F3038A
	char*pmsg;
	//##ModelId=41079D2A00EC
	DOMNode*acomm;
	// ping
	int m_ping;
	//
	// Campo de autoreply para header da mensagem FO
	char*pentity;
/**++
2004-07-19 - Ivan Mentone - add support to serverElapsedTime header item
--**/
#if !defined(WIN32)
	//##ModelId=4107AFB800FB
	struct timeval btmvl;
	//##ModelId=4107AFB8010A
	struct timeval etmvl;
#endif
	//##ModelId=41079D2A00F0
	int code;
};

//}
#endif

