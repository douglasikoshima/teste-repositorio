#ifndef CFILE_H_HEADER_INCLUDED_BD900221
#define CFILE_H_HEADER_INCLUDED_BD900221
#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#include <tuxfw.h>
#include <wchar.h>

//##ModelId=426F957102CA
class CFile
{
  public:	
	  CFile();
	  CFile(FILE*file);
	  ~CFile();
    // Escreve em um arquivo
    int write(char* buffer);

	// Escreve no arquivo um wchar
	int writew(wchar_t*buffer);

    //Faz a leitura de uma linha
    int readLine(char* buffer, int length);

	// Abre o arquivo
	int open(char*modo);

    // escreve no fim de um arquivo
    int append(char*buffer);

	// Coloca o caminho do arquivo
	int setPath(char*path);

	// retorna o caminho
	char*getPath();

	// Remove o arquivo
	int removeFile();

	// Retorna o tamanho do arquivo
	long getSize();

	// retorna o ponteiro do arquivo
	FILE* getFile();

	// fechar o arquivo
	int close();
  protected:
	  FILE *m_file;
	  char m_path[255];
	  char m_modo[5];
	  bool m_openned;
};



#endif /* CFILE_H_HEADER_INCLUDED_BD900221 */
