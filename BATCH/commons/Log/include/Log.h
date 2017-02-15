#ifndef LOG
#define  LOG

#include <stdio.h>
#include <time.h>
#include <stddef.h>
#include <stdarg.h>

#define ERROR 1
#define DEBUG 2
#define TUXFW_LOG_LEN_AUX			 32768

class Log
{

	public:

		Log();
		Log(int _nivel);
		
		void logDebug(char* _mensagem);
		void log(char* _mensagem, ...);
		void logError(char* _mensagem);
		void logInformation(char* _mensagem);

		void setNivel(int _nivel);
		int  getNivel();

	private:

		void writeLog(char* _mensagem);

		int nivel;
		char lineBuffer[TUXFW_LOG_LEN_AUX + 1];
};

#endif
