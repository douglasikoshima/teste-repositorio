// ValorLivre.h: interface for the 
// CValorLivre class.
//////////////////////////////////////////////////////////////////////
 
#ifndef ValorLivre
#define ValorLivre
#define NRO_ERR_MEMORIA              "24E0359"
#define MSG_ERR_MEMORIA              "Erro de alocação de memória"

class CValorLivre
{
public:
	// Construtor/Destrutor
	CValorLivre();
	virtual ~CValorLivre();
 
 	// Métodos de acesso a banco de dados
 	void inclui(void);					// inclui novo relacionamento
 	void exclui(void);					// remove o relacionamento
 	void altera(void);					// altera os dados do relacionamento
	void alteraPorUsuario(void); 
 	void excluiPorUsuario(void); 

	static CValorLivre* lista(int *,char *,char *);

 	// Métodos de acesso aos atributos
 	// Getters
 	int getIdValorLivre();
 	int getIdPessoa();
 	int getIdAtributo();
 	char* getDsValor();
 	// Setters
 	void setIdValorLivre(int value);
 	void setIdPessoa(int value);
 	void setIdAtributo(int value);
 	void setDsValor(char* value);
	// Usuário
	void setUsuarioAlteracao(char*);
 
 private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iIdValorLivre;
 	int iIdPessoa;
 	int iIdAtributo;
 	char cDsValor[256];

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
 	EXEC SQL END DECLARE SECTION;
 };
 
#endif
