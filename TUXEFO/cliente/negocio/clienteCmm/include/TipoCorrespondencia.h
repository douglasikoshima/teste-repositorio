// TipoCorrespondencia.h: interface for the 
 // CTipoCorrespondencia class.
 //////////////////////////////////////////////////////////////////////
 
 #ifndef TipoCorrespondencia
 #define TipoCorrespondencia
 
 #define MSG_ERR_MEMORIA		"Erro de alocação de memória"
 #define NRO_ERR_MEMORIA		"24E0359"
 
 class CTipoCorrespondencia  
 {
 public:
 	// Construtor/Destrutor
 	CTipoCorrespondencia();
 	CTipoCorrespondencia(int);
 	virtual ~CTipoCorrespondencia();
 
 	// Métodos de acesso a banco de dados
 	int inclui(void);					// inclui novo objeto
 	void exclui(void);					// remove o objeto
 	int  altera(void);					// altera os dados do objeto
 	static CTipoCorrespondencia* lista(int*); // lista todos os objetos na tabela
	int checaExclusaoPossivel();		// Checa se é possível excluir
 
 	// Métodos de acesso aos atributos
 	// Getters
 	int getIdTipoCorrespondencia();
 	char* getSgTipoCorrespondencia();
 	char* getDsTipoCorrespondencia();
 	int getInDisponibilidade();
 	// Setters
 	void setIdTipoCorrespondencia(int value);
 	void setSgTipoCorrespondencia(char* value);
 	void setDsTipoCorrespondencia(char* value);
 	void setInDisponibilidade(int value);
	// Usuário
	void setUsuarioAlteracao(char*);
 
 private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iIdTipoCorrespondencia;
 	char cSgTipoCorrespondencia[256];
 	char cDsTipoCorrespondencia[256];
	int iInDisponibilidade;
 
 	// Variáveis de indicação do oracle
 	short icSgTipoCorrespondencia;
 	short icDsTipoCorrespondencia;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
 	EXEC SQL END DECLARE SECTION;
};
 
#endif