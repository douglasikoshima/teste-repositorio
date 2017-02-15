// PessoaValorPossivel.h: interface for the 
// CPessoaValorPossivel class.
//////////////////////////////////////////////////////////////////////
 
#ifndef PessoaValorPossivel
#define PessoaValorPossivel
 
class CPessoaValorPossivel  
{
public:
	// Construtor/Destrutor
	CPessoaValorPossivel();
	virtual ~CPessoaValorPossivel();
 
 	// M�todos de acesso a banco de dados
 	void inclui(void);					// inclui novo relacionamento
 	void exclui(void);					// remove o relacionamento
 	void altera(void);					// altera os dados do relacionamento
	static void limpaPorAtributoEPessoa(int, int); // Remove todos os objetos da pessoa/atributo
 
 	// M�todos de acesso aos ValorPossivels
 	// Getters
 	int getIdPessoaValorPossivel();
 	int getIdPessoa();
 	int getIdValorPossivel();
 	// Setters
 	void setIdPessoaValorPossivel(int value);
 	void setIdPessoa(int value);
 	void setIdValorPossivel(int value);
	// Usu�rio
	void setUsuarioAlteracao(char*);
 
 private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iIdPessoaValorPossivel;
 	int iIdPessoa;
 	int iIdValorPossivel;

	// Usuario altera��o
	char sIdUsuarioAlteracao[256];
 	EXEC SQL END DECLARE SECTION;
 };
 
#endif