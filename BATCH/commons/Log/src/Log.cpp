/**
 * Classe para registro de log do processo BAT.
 */

#include <stdlib.h>
#include <string.h>

#include "../include/Log.h"

Log::Log()
{
	Log::setNivel(ERROR);
}


Log::Log(int _nivel)
{
	Log::setNivel( _nivel );
}

void Log::logDebug(char* _mensagem)
{
	if (nivel == DEBUG)
	{
		Log::writeLog( _mensagem );
	}
}

void Log::logError(char* _mensagem)
{
		Log::writeLog( _mensagem );
}

void Log::logInformation(char* _mensagem)
{
		Log::writeLog( _mensagem );
}


void Log::writeLog(char* _mensagem)
{

	struct tm *ptTime;
	time_t tTimeNow;

	time(&tTimeNow);
	ptTime=localtime(&tTimeNow);


	printf( "%04d-%02d-%02d %02d:%02d:%02d -- %s%c", 
						ptTime->tm_year + 1900, 
						ptTime->tm_mon + 1, 
						ptTime->tm_mday, 			
						ptTime->tm_hour, 
						ptTime->tm_min, 
						ptTime->tm_sec, 
						_mensagem,
						strstr(_mensagem,"\n") ? '\0':'\n'
					);
	
}

void Log::log(char *pszFormat, ...)
{
	memset(lineBuffer,0,sizeof(lineBuffer));
	va_list ptArguments;
	va_start(ptArguments, pszFormat);
	vsnprintf(lineBuffer, TUXFW_LOG_LEN_AUX,pszFormat, ptArguments);
	va_end(ptArguments);
	writeLog( lineBuffer );
}

void Log::setNivel(int _nivel)
{
	nivel = _nivel;
}

int Log::getNivel()
{
	return nivel;
}
