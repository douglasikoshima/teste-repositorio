// TipoDocumento.h: interface for the 
 // CTipoDocumento class.
 //////////////////////////////////////////////////////////////////////
 
 #ifndef TipoDocumento
 #define TipoDocumento
 
 #define MSG_ERR_MEMORIA		"Erro de alocação de memória"
 #define NRO_ERR_MEMORIA		"24E0359"
 
 class CTipoDocumento  
 {
 public:
 	// Construtor/Destrutor
 	CTipoDocumento();
 	CTipoDocumento(int);
 	virtual ~CTipoDocumento();
 
 	// Métodos de acesso a banco de dados
 	void inclui(void);					// inclui novo objeto
 	void exclui(void);					// remove o objeto
 	void altera(void);					// altera os dados do objeto
 	static CTipoDocumento* lista(int*); // lista todos os objetos na tabela
 	static CTipoDocumento* listaFiltrado(int*); // Lista os documentos que tem o campo VISUALIZA em 1
    static CTipoDocumento* listaPorIdTipoPessoa(int* iNroObjetos, char *pId); // lista todos os objetos na tabela por Id

 	// Métodos de acesso aos atributos
 	// Getters
 	int getIdTipoDocumento();
 	char* getSgTipoDocumento();
 	char* getDsTipoDocumento();
	int getIdTipoPessoa();
 	// Setters
 	void setIdTipoDocumento(int value);
 	void setSgTipoDocumento(char* value);
 	void setDsTipoDocumento(char* value);
	void setIdTipoPessoa(int value);
	// Usuário
	void setUsuarioAlteracao(char*);
 
 private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iIdTipoDocumento;
 	char cSgTipoDocumento[256];
 	char cDsTipoDocumento[256];
	int iIdTipoPessoa;
 
 	// Variáveis de indicação do oracle
 	short icSgTipoDocumento;
 	short icDsTipoDocumento;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
 	EXEC SQL END DECLARE SECTION;
 };
 
 #endif

