// Unidade.h: interface for the 
 // CUnidade class.
 //////////////////////////////////////////////////////////////////////
 
#ifndef Unidade
#define Unidade

#define MSG_ERR_MEMORIA		"Erro de alocação de memória"
#define NRO_ERR_MEMORIA		"24E0359"

class CUnidade  
{
public:
 	// Construtor/Destrutor
 	CUnidade();
 	CUnidade(int);
 	virtual ~CUnidade();
 
 	// Métodos de acesso a banco de dados
 	void inclui(void);					// inclui novo objeto
 	void exclui(void);					// remove o objeto
 	void altera(void);					// altera os dados do objeto
 	static CUnidade* lista(int*);			// lista todos os objetos na tabela
	static int getIdFakeUnidade(void);		// Recupera ID falso de estado
 
 	// Métodos de acesso aos atributos
 	// Getters
 	int getIdUnidade();
 	char* getCdUnidade();
 	char* getNmUnidade();
 	// Setters
 	void setIdUnidade(int value);
 	void setCdUnidade(char* value);
 	void setNmUnidade(char* value);
 private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iIdUnidade;
 	char cCdUnidade[256];
 	char cNmUnidade[256];
 	EXEC SQL END DECLARE SECTION;
 };
 
 #endif