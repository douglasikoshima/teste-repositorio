// SistemaOrigem.h: interface for the 
 // CSistemaOrigem class.
 //////////////////////////////////////////////////////////////////////
 
 #ifndef SistemaOrigem
 #define SistemaOrigem
 
 #define MSG_ERR_MEMORIA		"Erro de alocação de memória"
 #define NRO_ERR_MEMORIA		"24E0359"
 
 class CSistemaOrigem  
 {
 public:
 	// Construtor/Destrutor
 	CSistemaOrigem();
 	CSistemaOrigem(int);
 	virtual ~CSistemaOrigem();
 
 	// Métodos de acesso a banco de dados
 	void inclui(void);					// inclui novo objeto
 	void exclui(void);					// remove o objeto
 	void altera(void);					// altera os dados do objeto
 	static CSistemaOrigem* lista(int*); // lista todos os objetos na tabela
	static int getIdSistemaFO(void);	// Retorna ID de sistema para cadastro de prospect
 
 	// Métodos de acesso aos atributos
 	// Getters
 	int getIdSistemaOrigem();
 	char* getSgSistemaOrigem();
 	char* getDsSistemaOrigem();
 	// Setters
 	void setIdSistemaOrigem(int value);
 	void setSgSistemaOrigem(char* value);
 	void setDsSistemaOrigem(char* value);
	// Usuário
	void setUsuarioAlteracao(char*);
 
 private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iIdSistemaOrigem;
 	char cSgSistemaOrigem[256];
 	char cDsSistemaOrigem[256];

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
 	EXEC SQL END DECLARE SECTION;
 };
 
 #endif