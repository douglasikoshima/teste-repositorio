#ifndef HISTORICO_H
#define HISTORICO_H
#include "../../commons/Log/include/Log.h"
#include "../../commons/CFile/include/CFile.h"
#include <stdlib.h>
#include <string>

using namespace std;

class Historico : public CFile {
	private:
		Log log;
		int writeMessage(char* mensagem);
		char lineBuffer[32768 + 1];
		char nome[256];
	public:
		Historico();
		~Historico();		
		int appendText(char* mensagem, ...);
		char *getNome();
};

#endif