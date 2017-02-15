#ifndef INCLUDE_XENOSPLUGINBASE1_H_HEADER_INCLUDED_BD906BD9
#define INCLUDE_XENOSPLUGINBASE1_H_HEADER_INCLUDED_BD906BD9
#include <stdio.h>
#include "CFileXenos.h"
#include "Constants.h"
#include "MemoryManager.h"
#include <string>
#include <unistd.h>
#include "Util.h"

#define SIZE_PATH_UNIX 200

using namespace std;


class XenosPluginBase
{
  protected:

	// Retorna o conteúdo do HTML gerado pelo Xenos
	string readFile(char*pathFile);

	// retorna uma string com o HTML correto
	std::string decodeHTMLXenos(char*buffer,char*pathImage,char*css,int type,char*pathImage2,int fase);

    // Coloca o arquivo no diretório de input do Xenos
    bool setInputFile(CFileXenos *file);

    // Recebe o arquivo do xenos decodificado
    bool getOutputFile(CFileXenos &file);

	// substituir uma sequencia de string por outra sequencia
	std::string replaceAll(string &str,string find,string replaced);

	// substituir string
	std::string replaceAllString(string &str,string find,string replaced);

  private:
	  std::string replace(string html);
	  bool verificarToken(char*token,int *k,int indice);
	  bool isNumeric(char*d);

  private:
	CFileXenos *m_inputFile;
	CFileXenos *m_outputFile;
	Util m_util;
  protected:
    CMemoryManager m_mem;

};



#endif /* INCLUDE_XENOSPLUGINBASE1_H_HEADER_INCLUDED_BD906BD9 */
