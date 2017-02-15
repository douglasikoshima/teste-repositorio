// HistoricoStatus.h: interface for the 
// CHistoricoStatus class.
//////////////////////////////////////////////////////////////////////
 
#ifndef HistoricoStatus
#define HistoricoStatus
 
class CHistoricoStatus  
{
public:
	// Construtor/Destrutor
	CHistoricoStatus();
	virtual ~CHistoricoStatus();
 
 	// Métodos de acesso a banco de dados
 	void inclui(void);					// inclui novo relacionamento
 	void exclui(void);					// remove o relacionamento
 	void altera(void);					// altera os dados do relacionamento
 
 	// Métodos de acesso aos atributos
 	// Getters
 	int getIdHistoricoStatus();
 	int getIdPessoaUsuario();
 	int getIdCorrespondenciaDevolvida();
 	int getIdStatusCorrespondencia();
 	char* getDtStatus();
 	// Setters
 	void setIdHistoricoStatus(int value);
 	void setIdPessoaUsuario(int value);
 	void setIdCorrespondenciaDevolvida(int value);
 	void setIdStatusCorrespondencia(int value);
 	void setDtStatus(char* value);
	// Usuário
	void setUsuarioAlteracao(char*);
 
 private:
 
 	EXEC SQL BEGIN DECLARE SECTION;
 	int iIdHistoricoStatus;
 	int iIdPessoaUsuario;
 	int iIdCorrespondenciaDevolvida;
 	int iIdStatusCorrespondencia;
 	char cDtStatus[11];
 
 	// Variáveis de indicação do oracle
 	short icDtStatus;

	// Usuario alteração
	char sIdUsuarioAlteracao[256];
 	EXEC SQL END DECLARE SECTION;
 };
 
#endif

