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

	// Métodos de acesso a banco de dados
	void inclui(void);					// inclui novo relacionamento
	void exclui(void);					// remove o relacionamento
	void altera(void);					// altera os dados do relacionamento
	static void excluiPorStatus(int); // Exclui todos os objetos de um status de correspondência.

	// Métodos de acesso aos atributos
	// Getters
	int getIdStatusUnidade();
	int getIdUnidade();
	int getIdStatusCorrespondencia();
	// Setters
	void setIdStatusUnidade(int value);
	void setIdUnidade(int value);
	void setIdStatusCorrespondencia(int value);
	// Usuário
	void setUsuarioAlteracao(char*);

private:

	EXEC SQL BEGIN DECLARE SECTION;
	int iIdStatusUnidade;
	int iIdUnidade;
	int iIdStatusCorrespondencia;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
	EXEC SQL END DECLARE SECTION;
};

#endif
