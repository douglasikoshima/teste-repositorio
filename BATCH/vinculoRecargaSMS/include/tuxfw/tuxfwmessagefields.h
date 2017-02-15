/**!
 * 
 * @modulo  tuxfwmessagefields.h
 * @purpose Macro constants for message fiels to avoid mixed-case mismatch in use
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2010/09/06 19:00:59 $
 * @ID      $Id: tuxfwmessagefields.h,v 1.1.4.1 2010/09/06 19:00:59 a5116174 Exp $
 **/

//
// As constantes aqui mapeiam o conteudo de fo-msg-schema.xsd

//
// Elementos
#define TUXFW_MESSAGEFIELD_MSG					"msg"
#define TUXFW_MESSAGEFIELD_MSGHDR				"msgHdr"
#define TUXFW_MESSAGEFIELD_MSGBODY				"msgBody"
//
// Campos do Header
#define TUXFW_MESSAGEFIELD_USER					"user"
#define TUXFW_MESSAGEFIELD_SERVICE				"service"
#define TUXFW_MESSAGEFIELD_UUID					"uuid"
#define TUXFW_MESSAGEFIELD_HOST					"host"
#define TUXFW_MESSAGEFIELD_ENTITY				"entity"
#define TUXFW_MESSAGEFIELD_CREATIONTIMESTAMP	"creationTimestamp"
#define TUXFW_MESSAGEFIELD_PROCESSTIMESTAMP		"processTimestamp"
#define TUXFW_MESSAGEFIELD_SEQUENCE				"sequence"
#define TUXFW_MESSAGEFIELD_FIRSTREC				"firstRec"
#define TUXFW_MESSAGEFIELD_STATUSCODE			"statusCode"
#define TUXFW_MESSAGEFIELD_STATUSTEXT			"statusText"
#define TUXFW_MESSAGEFIELD_STATUSTYPE			"statusType"
#define TUXFW_MESSAGEFIELD_CORRID				"corrid"
#define TUXFW_MESSAGEFIELD_VERSION				"version"
#define TUXFW_MESSAGEFIELD_OPERATION			"operation"
#define TUXFW_MESSAGEFIELD_TIMEOUT				"timeout"
#define TUXFW_MESSAGEFIELD_TRANTIMEOUT			"tranTimeout"
#define TUXFW_MESSAGEFIELD_NUMBERRECS			"numberRecs"
#define TUXFW_MESSAGEFIELD_SERVERELAPSEDTIME	"serverElapsedTime"
#define TUXFW_MESSAGEFIELD_TOPIC				"topic"
#define TUXFW_MESSAGEFIELD_REPLYTOPIC			"replyTopic"
#define TUXFW_MESSAGEFIELD_SUBSYSTEM			"subSystem"
#define TUXFW_MESSAGEFIELD_ERRCOUNT				"errCount"
#define TUXFW_MESSAGEFIELD_LASTERRORTIMESTAMP	"lastErrorTimestamp"
#define TUXFW_MESSAGEFIELD_LASTSTATUSCODE		"lastStatusCode"
#define TUXFW_MESSAGEFIELD_QUEUESPACE			"queueSpace"
#define TUXFW_MESSAGEFIELD_REQUESTQUEUE			"requestQueue"
#define TUXFW_MESSAGEFIELD_FAILUREQUEUE			"failureQueue"
#define TUXFW_MESSAGEFIELD_REPLUQUEUE			"replyQueue"
#define TUXFW_MESSAGEFIELD_DELAY				"delay"
#define TUXFW_MESSAGEFIELD_TRACELEVEL			"traceLevel"
#define TUXFW_MESSAGEFIELD_CODERRO				"codErro"
#define TUXFW_MESSAGEFIELD_MSGDIRECTION			"msgDirection"
#define TUXFW_MESSAGEFIELD_PRIORITY				"priority"
#define TUXFW_MESSAGEFIELD_PING					"ping"
#define TUXFW_MESSAGEFIELD_LOGLEVEL				"loglevel"
