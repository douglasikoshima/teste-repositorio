/* MFile.h: Classe para acesso a arquivo
*/
 
#ifndef MFILEH
#define  MFILEH

class MFile
{

private:
	char m_szPath[255];
	char m_szLine[81];
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
};

#endif
