// TipoCarteira.h: interface for the 
 // CTipoCarteira class.
 //////////////////////////////////////////////////////////////////////
 
 #ifndef TipoCarteira
 #define TipoCarteira
 
 #define MSG_ERR_MEMORIA		"Erro de alocação de memória"
 #define NRO_ERR_MEMORIA		"24E0359"
 
 class CTipoCarteira  
 {
 public:
 	// Construtor/Destrutor
 	CTipoCarteira();
 	CTipoCarteira(int);
 	virtual ~CTipoCarteira();
 
 	// Métodos de acesso a banco de dados
 	void inclui(void);					// inclui novo objeto
 	void exclui(void);					// remove o objeto
 	void altera(void);					// altera os dados do objeto
 	static CTipoCarteira* lista(int*);	// lista todos os objetos na tabela
	static int getIdTipoNaoDefinido();	// Retorna o ID de tipo de carteira não definido
 
 	// Métodos de acesso aos atributos
 	// Getters
 	int getIdTipoCarteira();
 	char* getSgTipoCarteira();
 	char* getDsTipoCarteira();
	int getVlPeso();
 	// Setters
 	void setIdTipoCarteira(int value);
 	void setSgTipoCarteira(char* value);
 	void setDsTipoCarteira(char* value);
	void setVlPeso(int value);
	// Usuário
	void setUsuarioAlteracao(char*);
 
 private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iIdTipoCarteira;
 	char cSgTipoCarteira[256];
 	char cDsTipoCarteira[256];
	int iVlPeso;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
 	EXEC SQL END DECLARE SECTION;
 };
 
 #endif