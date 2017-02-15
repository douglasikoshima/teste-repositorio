/**!
 * 
 * @module  
 * @purpose Define macros to handle config files tags
 * @version $Revision: 1.1.6.1 $
 * @CVS     $Author: jones $ - $Date: 2013/07/10 18:04:18 $
 * @ID      $Id: tuxfwconfigfields.h,v 1.1.6.1 2013/07/10 18:04:18 jones Exp $
 **/


#ifndef TuxFW_ConfigFields
#define TuxFW_ConfigFields


//
// Elementos
#define TUXFW_SVRCONFIG_ROOT										"config"
#define TUXFW_SVRCONFIG_SERVERS										"servers"
#define TUXFW_SVRCONFIG_SERVERS_SERVER								"server"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_NAME							"name"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_LOGLEVEL						"logLevel"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_LOGFILEPATH					"logFilePath"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_LOGFILEPREFIX				"logPrefix"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES						"services"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC					"service"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC_NAME			"name"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC_TYPE			"type"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC_TUXSERVICE		"tuxService"
//
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC_REQUESTQUEUE	"requestQueue"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC_QUEUESPACE		"queueSpace"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC_REPLYQUEUE		"replyQueue"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC_FAILUREQUEUE	"failureQueue"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC_PRIORITY		"priority"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC_DELAY			"delay"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC_TIMEOUT			"timeout"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC_DELIVERYQOS		"deliveryQoS"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC_REPLUQOS		"replyQoS"
//
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC_FLAGS			"flags"
#define TUXFW_SVRCONFIG_SERVERS_SERVER_SERVICES_SVC_FLAGS_FLAG		"flag"

//
//
#endif
