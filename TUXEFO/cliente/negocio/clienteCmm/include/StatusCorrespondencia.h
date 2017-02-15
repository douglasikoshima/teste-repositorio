// StatusCorrespondencia.h: interface for the 
// CStatusCorrespondencia class.
//////////////////////////////////////////////////////////////////////

#ifndef StatusCorrespondencia
#define StatusCorrespondencia

EXEC SQL INCLUDE "Unidade.h";

#define MSG_ERR_MEMORIA		"Erro de alocação de memória"
#define NRO_ERR_MEMORIA		"24E0359"

class CStatusCorrespondencia  
{
public:
	// Construtor/Destrutor
	CStatusCorrespondencia();
	virtual ~CStatusCorrespondencia();

	// Métodos de acesso a banco de dados
	int inclui(void);					// inclui novo objeto
	void exclui(void);					// remove o objeto
	int altera(void);					// altera os dados do objeto
	static CStatusCorrespondencia* lista(int *); // lista todos os status possíveis
	static CStatusCorrespondencia* listaComUnidades(int *); // lista todos os status possíveis, trazendo também as unidades
	static CStatusCorrespondencia* listaPorTipoUnidade(int *, char*); // lista todos os status filtrando por tipo unidade
	int checaExclusaoPossivel();		// Checa se é possível excluir
	
	// Métodos comuns
	static int getIdStatusAberto(void);		// Retorna o ID de status em aberto
	static int getIdStatusEncerrado(void);	// Retorna o ID de status encerrado

	// Métodos de acesso aos atributos
	// Getters
	int getIdStatusCorrespondencia();
	char* getSgStatus();
	char* getDsStatus();
	int* getQtDiasValido();
	int* getIdProximoStatus();
	int getInDisponibilidade();
	CUnidade* getUnidades();
	int getNrUnidades();
	// Setters
	void setIdStatusCorrespondencia(int value);
	void setSgStatus(char* value);
	void setDsStatus(char* value);
	void setQtDiasValido(int* value);
	void setIdProximoStatus(int* value);
	void setInDisponibilidade(int value);
	void setUnidades(CUnidade*);
	void setNrUnidades(int);
	// Usuário
	void setUsuarioAlteracao(char*);

private:

	EXEC SQL BEGIN DECLARE SECTION;
	int iIdStatusCorrespondencia;
	char cSgStatus[256];
	char cDsStatus[256];
	int iQtDiasValido;
	int iIdProximoStatus;
	int iInDisponibilidade;

	// Variáveis de indicação do oracle
	short icSgStatus;
	short icDsStatus;
	short iiQtDiasValido;
	short iiIdProximoStatus;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
	EXEC SQL END DECLARE SECTION;

	int iNrUnidades;
	CUnidade* poUnidades;
};

#endif
