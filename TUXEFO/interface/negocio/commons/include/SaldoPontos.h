#ifndef SALDOPONTOSH
#define SALDOPONTOSH

#include <tuxfw.h>

class CSaldoPontos{
public:
	/* Construtor/Destrutor */
	CSaldoPontos();
	virtual ~CSaldoPontos();
	
	void ConsultaPontos();
	
	//Metodos de acessos aos atributos
	//Setters
	void setDDD(char*);
	void setNrFone(char*);
	void setUser(char*);
	void setNrLanctos(int);
	//Getters
	DOMNode* getXMLRetorno(void);
	
private:
	char sDDD[255];
	char sNrFone[255];
	char sUser[255];
	int iNrLanctos;
	DOMNode* xmlRet;
	XercesDOMParser *pParser;
	MemBufInputSource *pMemBuf;
};

#endif
