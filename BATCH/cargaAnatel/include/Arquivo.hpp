#ifndef ARQUIVO_H
#define ARQUIVO_H
#include "../../commons/Log/include/Log.h"
#include <stdlib.h>
#include <string>

using namespace std;

class Arquivo {
	private:
		string nome;
		string arquivo;	
		string descompactado;
		Log log;
	public:
		Arquivo();
		~Arquivo();
		void setNome(char*);
		void setArquivo(char*,char*);
		void setPathDescompactado(char*);
		char* getArquivo();
		char* getNome();
		char* getPathDescompactado();
};

#endif