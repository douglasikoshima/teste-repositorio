#ifndef UTIL_H_HEADER_INCLUDED_BD90793C
#define UTIL_H_HEADER_INCLUDED_BD90793C
#include <stdio.h>
#include <time.h>
#include <string.h>
#include <stdlib.h>
#include "../../commons/include/vectorlist.h"
#include <zlib.h>
#include <tuxfw.h>
#include <xercesc/util/Base64.hpp>
#include <string>

using namespace std;

typedef CVectorList<char> ListChar;

class Util
{
  public:
	// retorna a data de hoje em inteiro yyyymmdd
    static int getTime();
	// retorna a data em dia e hora yyyymmddhhmmss
    static int getTimeHour();
	// Remove todos os caracteres find de buffer
	static char*removeChars(char*buffer,char find);
	// Retorna o Mês correspondente ao número
	static char*formatMonth(char*date);
	// Faz um Split
	static ListChar* split(char*buffer,char *splitChar);
	// formata data
	static void formataData(char* pcData);
	// comprime um arquivo
	static int compressFile(char *buffer, char *outfilename);
	// Decodifica um texto de Base64
	static int decodeBase64(char*buffer,FILE*file);
	// Prepara uma string para ser decodificada e decodifica
	static int decodeTrimBase64(char*buffer,FILE*file);
	// retira a mascara da data inverte e retorna um inteiro
	static int fmtInverteData(char*pdate);
	// substituir uma sequencia de string por outra sequencia
	std::string replaceAll(string &str,string find,string replaced);
	// substituir string
	std::string replaceAllString(string &str,string find,string replaced);
};



#endif /* UTIL_H_HEADER_INCLUDED_BD90793C */
