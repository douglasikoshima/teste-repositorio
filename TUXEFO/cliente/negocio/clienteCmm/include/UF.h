// UF.h: interface for the 
 // CUF class.
 //////////////////////////////////////////////////////////////////////
 
#ifndef UF
#define UF

#define MSG_ERR_MEMORIA		"Erro de aloca��o de mem�ria"
#define NRO_ERR_MEMORIA		"24E0359"

class CUF  
{
public:
 	// Construtor/Destrutor
 	CUF();
 	CUF(int);
 	virtual ~CUF();
 
 	// M�todos de acesso a banco de dados
 	void inclui(void);					// inclui novo objeto
 	void exclui(void);					// remove o objeto
 	void altera(void);					// altera os dados do objeto
 	static CUF* lista(int*);			// lista todos os objetos na tabela
	static int getIdFakeUF(void);		// Recupera ID falso de estado
 
 	// M�todos de acesso aos atributos
 	// Getters
 	int getIdUF();
 	char* getSgUF();
 	char* getNmUF();
 	// Setters
 	void setIdUF(int value);
 	void setSgUF(char* value);
 	void setNmUF(char* value);
	// Usu�rio
	void setUsuarioAlteracao(char*);
 
 private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iIdUF;
 	char cSgUF[256];
 	char cNmUF[256];

	// Usuario altera��o
	char sIdUsuarioAlteracao[256];
 	EXEC SQL END DECLARE SECTION;
 };
 
#endif

