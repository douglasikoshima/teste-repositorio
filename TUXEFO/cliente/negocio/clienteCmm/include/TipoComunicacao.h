// TipoComunicacao.h: interface for the 
 // CTipoComunicacao class.
 //////////////////////////////////////////////////////////////////////
 
 #ifndef TipoComunicacao
 #define TipoComunicacao
 
 #define MSG_ERR_MEMORIA		"Erro de aloca��o de mem�ria"
 #define NRO_ERR_MEMORIA		"24E0359"
 
 class CTipoComunicacao  
 {
 public:
 	// Construtor/Destrutor
 	CTipoComunicacao();
 	CTipoComunicacao(int);
 	virtual ~CTipoComunicacao();
 
 	// M�todos de acesso a banco de dados
 	void inclui(void);					// inclui novo objeto
 	void exclui(void);					// remove o objeto
 	void altera(void);					// altera os dados do objeto
 	static CTipoComunicacao* lista(int*); // lista todos os objetos na tabela
 
 	// M�todos de acesso aos atributos
 	// Getters
 	int getIdTipoComunicacao();
 	char* getSgTipoComunicacao();
 	char* getDsTipoComunicacao();
 	// Setters
 	void setIdTipoComunicacao(int value);
 	void setSgTipoComunicacao(char* value);
 	void setDsTipoComunicacao(char* value);
	// Usu�rio
	void setUsuarioAlteracao(char*);
 
 private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iIdTipoComunicacao;
 	char cSgTipoComunicacao[256];
 	char cDsTipoComunicacao[256];

	// Usuario altera��o
	char sIdUsuarioAlteracao[256];
 	EXEC SQL END DECLARE SECTION;
 };
 
 #endif

