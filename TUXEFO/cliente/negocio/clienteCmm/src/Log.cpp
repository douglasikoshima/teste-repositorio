///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase Log
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:17 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <stdarg.h>
#include <time.h>
#include <stdio.h>
#include <stdlib.h>
#include <ctype.h>
#include <string.h>
#include <tuxfw.h>
#include "../include/Global.h"

/***************************************************************************************/
void Log(int iTipoLog, char *pszFormat, ...)
{
    
    char szLogMessageTUX[LEN_LOG_MESSAGE + LEN_EOS];
	char szLogMessage[LEN_LOG_MESSAGE + LEN_EOS];

	va_list ptArguments;
	struct tm *ptTime;
	time_t tTimeNow;


    if(pszFormat == NULL)
        return;

	va_start(ptArguments, pszFormat);
	vsprintf(szLogMessage, pszFormat, ptArguments);
	va_end(ptArguments);

	time(&tTimeNow);
	ptTime=localtime(&tTimeNow);

	sprintf(szLogMessageTUX, "%02d:%02d:%02d (%d) %s",
                                    ptTime->tm_hour, ptTime->tm_min, ptTime->tm_sec,
                                    strlen(szLogMessage), szLogMessage);

	switch(iTipoLog)
    {
        case LDEBUG:
            tuxfw_getlogger()->debug(szLogMessageTUX);
            break;
	    case LINFORMATION:
            tuxfw_getlogger()->information(szLogMessageTUX);
            break;
	    case LWARNING:
	        tuxfw_getlogger()->warning(szLogMessageTUX);
		    break;
	    case LERROR:
	        tuxfw_getlogger()->error(szLogMessageTUX);
		    break;
	    case LCRITICAL:
		    tuxfw_getlogger()->critical(szLogMessageTUX);
            break;
	    default:
		return;
	}
}
