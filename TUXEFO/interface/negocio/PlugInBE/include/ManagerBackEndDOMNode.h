#ifndef TXPB_MANANGER_BACKEND_DOMNODE_H
#define TXPB_MANANGER_BACKEND_DOMNODE_H 1


#include <tuxfw.h>

#include <list>

using namespace std;


class BackEndDOMNode
{
public:

	// Construtor e Destrutor
	BackEndDOMNode();
	~BackEndDOMNode();

	// set Operations
	void setDOMNode(XercesDOMParser*, MemBufInputSource*);

	// get Operation
	DOMNode* getDOMNode();


	// Desaloca memoria do DOMNode
	void releaseDOMNode();

	// Sobrecarga de operadores
	BackEndDOMNode& operator = (const BackEndDOMNode&);

private:

	// Atributos para Manipulação de DOMDocment
	XercesDOMParser*   m_pParser; // PARSER
	MemBufInputSource* m_pMemBuf; // BUFFER	
};


// Class do Gerenciador de Alocaçao:
// Mantem os DOMNodes Alocados Numa Lista, fazendo a Desalocação de Memoria ao Final do Escopo do PlugIn
class ManagerBackEndDOMNode
{
public:

	ManagerBackEndDOMNode();
	~ManagerBackEndDOMNode();

	// Factory dos DOMNodes
	DOMNode* createDOMNode(char *);

private:

	list<BackEndDOMNode>  m_lstDOMNodes;
};


#endif
