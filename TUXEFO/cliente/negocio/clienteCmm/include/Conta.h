// Conta.h: interface for the 
 // CConta class.
 //////////////////////////////////////////////////////////////////////
 
#ifndef ContaHH
#define ContaHH

#include "Global.h"

#define MSG_ERR_MEMORIA		"Erro de alocação de memória"
#define NRO_ERR_MEMORIA		"24E0359"

class CConta  
{
public:
 	// Construtor/Destrutor
 	CConta();
	virtual ~CConta(){};

	TConta      tConta;
 
 	// Métodos de acesso a banco de dados
	static char* getContaSisOrigem(char*, int);		// Recupera ID de sistema origem
	char** listaLinhasIntraGrupo(int *); // Recupera as linhas que têm serviço intra grupo

	void proCInsereConta(TConta *ptConta);
	void proCAtualizaConta(TConta tConta);

	void setData(char *);

	void insereConta();
	void atualizaConta();
	void setIdSistemaOrigem(char*);
	char *getIdSistemaOrigem();
	void setIdConta(char*);
	char *getIdConta();
	char *getIdPessoaSistemaOrigem();
	char *getIdEnderecoSistemaOrigem();
	void setIdLayoutConta(char *);
	void setNrConta(char *);
	void setIdContaSistemaOrigem(char *);
	void setCdConta(char *);
	void setIdStatusConta(char *);
	void setIdTipoConta(char *);
	void recuperaIdConta(char *);
};
 
#endif
