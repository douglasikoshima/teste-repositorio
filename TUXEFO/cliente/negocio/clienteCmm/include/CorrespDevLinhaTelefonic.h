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

	// M�todos de acesso a banco de dados
	void inclui(void);					// inclui novo relacionamento
	void exclui(void);					// remove o relacionamento
	void altera(void);					// altera os dados do relacionamento

	// M�todos de acesso aos atributos
	// Getters
	int getIdCorrespDevolvidaLinhaTelefon();
	int getIdCorrespondenciaDevolvida();
	int getIdLinhaTelefonica();
	// Setters
	void setIdCorrespDevolvidaLinhaTelefon(int value);
	void setIdCorrespondenciaDevolvida(int value);
	void setIdLinhaTelefonica(int value);
	// Usu�rio
	void setUsuarioAlteracao(char*);

private:

	EXEC SQL BEGIN DECLARE SECTION;
	int iIdCorrespDevolvidaLinhaTelefon;
	int iIdCorrespondenciaDevolvida;
	int iIdLinhaTelefonica;

	// Usuario altera��o
	char sIdUsuarioAlteracao[256];
	EXEC SQL END DECLARE SECTION;
};

#endif
