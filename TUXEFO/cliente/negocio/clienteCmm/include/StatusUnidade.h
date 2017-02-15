// StatusUnidade.h: interface for the 
// CStatusUnidade class.
//////////////////////////////////////////////////////////////////////

#ifndef StatusUnidade
#define StatusUnidade

class CStatusUnidade  
{
public:
	// Construtor/Destrutor
	CStatusUnidade();
	virtual ~CStatusUnidade();

	// M�todos de acesso a banco de dados
	void inclui(void);					// inclui novo relacionamento
	void exclui(void);					// remove o relacionamento
	void altera(void);					// altera os dados do relacionamento
	static void excluiPorStatus(int); // Exclui todos os objetos de um status de correspond�ncia.

	// M�todos de acesso aos atributos
	// Getters
	int getIdStatusUnidade();
	int getIdUnidade();
	int getIdStatusCorrespondencia();
	// Setters
	void setIdStatusUnidade(int value);
	void setIdUnidade(int value);
	void setIdStatusCorrespondencia(int value);
	// Usu�rio
	void setUsuarioAlteracao(char*);

private:

	EXEC SQL BEGIN DECLARE SECTION;
	int iIdStatusUnidade;
	int iIdUnidade;
	int iIdStatusCorrespondencia;

	// Usuario altera��o
	char sIdUsuarioAlteracao[256];
	EXEC SQL END DECLARE SECTION;
};

#endif
