/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/08/26 19:24:43 $
 * @ID      $Id: tuxfwlogexception.h,v 1.1.4.1 2009/08/26 19:24:43 a5110702 Exp $
 **/

#if !defined(AFX_LOGEXCEPTION_H__66920370_7A0B_4CFF_9FBC_BC75679A9F53__INCLUDED_)
#define AFX_LOGEXCEPTION_H__66920370_7A0B_4CFF_9FBC_BC75679A9F53__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "tuxfwexception.h"

//namespace tuxfw
//{

/**!
 *	LogType - setting type of log data
 */
//##ModelId=41079D2901D4
enum LogType
{
	//##ModelId=41079D2901D6
	LogDebug,
	/**
	***	LogInfo - line generated for information intentions
	***/
	//##ModelId=41079D2901D7
	LogInfo,
	/**
		LogWarning - line generated for warning intentions
	***/
	//##ModelId=41079D2901E4
	LogWarning,
	/**
	***	LogError - line generated to relate a error in server
	***/
	//##ModelId=41079D2901E5
	LogError,
	/**
	***	LogCritical - line generated to relate a critical error in server
	***/
	//##ModelId=41079D2901E6
	LogCritical
};

//##ModelId=41079D2901F4
class TuxLogException
{

public:
	//##ModelId=41079D2901F5
	TuxLogException(char *pmsgErr);
	//##ModelId=41079D2901F7
	virtual ~TuxLogException();
	//##ModelId=41079D2901F9
	char* getErrMsg();
private:
	//##ModelId=41079D290203
	char* errMsg;
};

//}
#endif // !defined(AFX_LOGEXCEPTION_H__66920370_7A0B_4CFF_9FBC_BC75679A9F53__INCLUDED_)
