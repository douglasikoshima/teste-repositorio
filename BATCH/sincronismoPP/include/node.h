#ifndef NODE
#define NODE
#include "../../commons/Log/include/Log.h"

class Node 
{
  public:
	Node();
	Node(char*id);
	Node(char*id, Log *log,int*objAlocado,int*objDesalocado, int debug);
	~Node();	
	void setId(char*id);
	void setStatus(char *status);
	void incrementaTentativa();
	void addItem(Node *item);
	void setNext(Node *item);
	void setLog(Log *log);
	void logar(char*str);
	void setPrev(Node*node);
	void setFirstElement(Node*node);
	char* getId();
	char* getStatus();	
	Node* getLista();
	Node* getNext();
	Node* getPrev();
	Node* getFirstElement();
	int getQtTentativas();
	int getObjetoAlocado();
	int getObjetoDesalocado();
	int getTipo();	
	int getSize();
	bool isProcessado();
	void setProcessado(bool processado);
	void setTipo(int tipo);
  private:
	char id[40];
	char status[2];	
	int qtTentativas;	
	Node *lista;
	Node *first;
	Node *next;
	Node *prev;
	Log *log;
	int *objetoAlocado;
	int *objetoDesalocado;
	int size;
	bool processado;
	int debug;
	int tipo;
};



#endif 
