#include "../include/Historico.hpp"
#include <stdlib.h>
#include <string.h>

Historico::Historico() {
	log.setNivel(2);
	log.logDebug("criando arquivo de historico de log");
}

Historico::~Historico() {
	log.logDebug("removendo arquivo de historico de log");
}

char* Historico::getNome() {
	memset(this->nome, 0 , sizeof(this->nome));
	struct tm *ptTime;
	time_t tTimeNow;
	time(&tTimeNow);
	ptTime=localtime(&tTimeNow);	
	sprintf( this->nome, "%04d%02d%02d%02d%02d%02d%s%c", 
						ptTime->tm_year + 1900, 
						ptTime->tm_mon + 1, 
						ptTime->tm_mday, 			
						ptTime->tm_hour, 
						ptTime->tm_min, 
						ptTime->tm_sec, 
						".log",
						'\0'
					);
	return this->nome;
}

int Historico::writeMessage(char* mensagem)
{
	char buffer[32768 + 1];
	struct tm *ptTime;
	time_t tTimeNow;
	time(&tTimeNow);
	ptTime=localtime(&tTimeNow);
	memset(buffer, 0 , sizeof(buffer));
	sprintf( buffer, "%04d-%02d-%02d %02d:%02d:%02d | %s%c", 
						ptTime->tm_year + 1900, 
						ptTime->tm_mon + 1, 
						ptTime->tm_mday, 			
						ptTime->tm_hour, 
						ptTime->tm_min, 
						ptTime->tm_sec, 
						mensagem,
						strstr(mensagem,"\n") ? '\0':'\n'
					);
	return this->append(buffer);
}

int Historico::appendText(char *pszFormat, ...)
{
	memset(lineBuffer,0,sizeof(lineBuffer));
	va_list ptArguments;
	va_start(ptArguments, pszFormat);
	vsnprintf(lineBuffer, TUXFW_LOG_LEN_AUX,pszFormat, ptArguments);
	va_end(ptArguments);
	return writeMessage( lineBuffer );
}

