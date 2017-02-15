/* MFile.h: Classe para acesso a arquivo
*/
 
#ifndef MFILEH
#define  MFILEH


#include <stdio.h>
#include <string.h>
#include <stdlib.h>
#ifdef WIN32
   #include <tuxfw.h>
#endif
#include <wchar.h>

class MFile
{

private:
	char m_szPath[1025];
	char m_szLine[1525];
	int  m_iStatus; /*-1=Sem Nome 0=fechado 1=aberto 2=em uso */
	FILE *m_arq; 	
public:
	MFile();
	/*~MFile();*/
	int abrir();
	int getLine(char *);
	int fechar();
	int setPath(char *);
	int getStatus();
	char* getPath();
	int removeFile();
	
};

#endif
