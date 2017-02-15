// CorrespDevLinhaTelefonic.h: interface for the 
// CCorrespDevLinhaTelefonic class.
//////////////////////////////////////////////////////////////////////

#ifndef CorrespDevLinhaTelefonic
#define CorrespDevLinhaTelefonic

class CCorrespDevLinhaTelefonic  
{
public:
	// Construtor/Destrutor
	CCorrespDevLinhaTelefonic();
	virtual ~CCorrespDevLinhaTelefonic();

	// Métodos de acesso a banco de dados
	void inclui(void);					// inclui novo relacionamento
	void exclui(void);					// remove o relacionamento
	void altera(void);					// altera os dados do relacionamento

	// Métodos de acesso aos atributos
	// Getters
	int getIdCorrespDevolvidaLinhaTelefon();
	int getIdCorrespondenciaDevolvida();
	int getIdLinhaTelefonica();
	// Setters
	void setIdCorrespDevolvidaLinhaTelefon(int value);
	void setIdCorrespondenciaDevolvida(int value);
	void setIdLinhaTelefonica(int value);
	// Usuário
	void setUsuarioAlteracao(char*);

private:

	EXEC SQL BEGIN DECLARE SECTION;
	int iIdCorrespDevolvidaLinhaTelefon;
	int iIdCorrespondenciaDevolvida;
	int iIdLinhaTelefonica;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
	EXEC SQL END DECLARE SECTION;
};

#endif
