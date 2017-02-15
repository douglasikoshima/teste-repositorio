// TipoApresentacaoPergunta.h: interface for the 
// CTipoApresentacaoPergunta class.
//////////////////////////////////////////////////////////////////////

#ifndef TipoApresentacaoPergunta
#define TipoApresentacaoPergunta

#define MSG_ERR_MEMORIA		"Erro de alocação de memória"
#define NRO_ERR_MEMORIA		"24E0359"

class CTipoApresentacaoPergunta  
{
public:
	// Construtor/Destrutor
	CTipoApresentacaoPergunta();
	virtual ~CTipoApresentacaoPergunta();

	// Métodos de acesso a banco de dados
	void inclui(void);					// inclui novo objeto
	void exclui(void);					// remove o objeto
	void altera(void);					// altera os dados do objeto
	static CTipoApresentacaoPergunta* lista(int*); // lista todos os objetos na tabela

	// Métodos de acesso aos atributos
	// Getters
	int getIdTipoApresentacaoPergunta();
	char* getSgTipoApresentacaoPergunta();
	char* getDsTipoApresentacaoPergunta();
	// Setters
	void setIdTipoApresentacaoPergunta(int value);
	void setSgTipoApresentacaoPergunta(char* value);
	void setDsTipoApresentacaoPergunta(char* value);
	// Usuário
	void setUsuarioAlteracao(char*);

private:

	EXEC SQL BEGIN DECLARE SECTION;
	int iIdTipoApresentacaoPergunta;
	char cSgTipoApresentacaoPergunta[256];
	char cDsTipoApresentacaoPergunta[256];

	// Variáveis de indicação do oracle
	short icSgTipoApresentacaoPergunta;
	short icDsTipoApresentacaoPergunta;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
	EXEC SQL END DECLARE SECTION;
};

#endif