#ifndef SALDOPONTOSH
#define SALDOPONTOSH

#include <tuxfw.h>

class CPontosURA{
public:
	/* Construtor/Destrutor */
	CPontosURA();
	virtual ~CPontosURA();
	
	void ConsultaPontos();
	
	//Metodos de acessos aos atributos
	//Setters
	void setDDD(char*);
	void setNrFone(char*);
	void setNrLanctos(int);
	void setDtQuebra(char*);
	void setInLinhas(bool);
	void setInExp(bool);
	//Getters
	DOMNode* getXMLRetorno(void);
	
private:
	char sDDD[256];
	char sNrFone[256];
	char sUser[256];
	char sDtQuebra[256];
	int iNrLanctos;
	bool bLinhas;
	bool bExp;
	DOMNode* xmlRet;
	XercesDOMParser *pParser;
	MemBufInputSource *pMemBuf;
};

#endif
