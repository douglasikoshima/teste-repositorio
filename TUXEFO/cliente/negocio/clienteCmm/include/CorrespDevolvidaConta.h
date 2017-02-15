// CorrespDevolvidaConta.h: interface for the 
// CCorrespDevolvidaConta class.
//////////////////////////////////////////////////////////////////////

#ifndef CorrespDevolvidaConta
#define CorrespDevolvidaConta

class CCorrespDevolvidaConta  
{
public:
	// Construtor/Destrutor
	CCorrespDevolvidaConta();
	virtual ~CCorrespDevolvidaConta();

	// Métodos de acesso a banco de dados
	void inclui(void);					// inclui novo relacionamento
	void exclui(void);					// remove o relacionamento
	void altera(void);					// altera os dados do relacionamento

	// Métodos de acesso aos atributos
	// Getters
	int getIdCorrespondenciaDevolvidaCont();
	int getIdCorrespondenciaDevolvida();
	int getIdConta();
	// Setters
	void setIdCorrespondenciaDevolvidaCont(int value);
	void setIdCorrespondenciaDevolvida(int value);
	void setIdConta(int value);
	// Usuário
	void setUsuarioAlteracao(char*);

private:

	EXEC SQL BEGIN DECLARE SECTION;
	int iIdCorrespondenciaDevolvidaCont;
	int iIdCorrespondenciaDevolvida;
	int iIdConta;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
	EXEC SQL END DECLARE SECTION;
};

#endif
