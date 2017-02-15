// Pais.h: interface for the 
// CPais class.
/////////////////////////////////////////////////////////////////////
 
#ifndef Pais
#define Pais

#define MSG_ERR_MEMORIA		"Erro de aloca��o de mem�ria"
#define NRO_ERR_MEMORIA		"24E0359"

class CPais  
{
public:
 	// Construtor/Destrutor
 	CPais();
 	CPais(int);
 	virtual ~CPais();
 
 	// M�todos de acesso a banco de dados
 	void inclui(void);					// inclui novo objeto
 	void exclui(void);					// remove o objeto
 	void altera(void);					// altera os dados do objeto
 	static CPais* lista(int*);			// lista todos os objetos na tabela
 
 	// M�todos de acesso aos atributos
 	// Getters
 	int getIdPais();
 	char* getSgPais();
 	char* getNmPais();
 	// Setters
 	void setIdPais(int value);
 	void setSgPais(char* value);
 	void setNmPais(char* value);
 	// Usu�rio
	void setUsuarioAlteracao(char*);

private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iIdPais;
 	char cSgPais[256];
 	char cNmPais[256];

	// Usuario altera��o
	char sIdUsuarioAlteracao[256];
 	EXEC SQL END DECLARE SECTION;
 };
 
#endif

