// Senha.h: interface for the CSenha class.
//
//////////////////////////////////////////////////////////////////////

#ifndef CSENHA
#define CSENHA

class CPessoaAbstract;

class CSenha  
{
public:

    CSenha();
	CSenha(CPessoaAbstract *p);
    virtual ~CSenha();

	// Métodos de acesso aos atributos
 	// Getters
	int   getIdSenha();
	int   getIdTipoSenha();
 	char *getDsFraseSecreta();
 	char *getDsLembreteFraseSecreta();
 	char *getDsLembreteSenha();
	char *getSenha();
	int   getQtTentFraseSecreta();
	int   getStatusFraseSecreta();
	char *getCdSenha();

 	// Setters
	void setIdSenha(int value);
	void setIdTipoSenha(int value);
	void setDsFraseSecreta(char* value);
 	void setDsLembreteFraseSecreta(char* value);
 	void setDsLembreteSenha(char* value);
	void setSenha(char* value);
	void setQtTentFraseSecreta(int value);
	void setStatusFraseSecreta(int value);
	void setCdSenha(char *value);

	// Operação de Negocio (Interface)
	void consultarLembreteFraseSecreta(long idPessoa, long idPessoaLinha, int idTipoRelacionamento);
	void excluirFraseSecreta(long idPessoa, long idPessoaLinha, int idTipoRelacionamento, int idCanal, int idPessoaUsuario);
	void alterarQtTentFraseSecreta(long idPessoa, long idPessoaLinha, int idTipoRelacionamento);
	void bloquearFraseSecreta(long idPessoa, long idPessoaLinha, int idTipoRelacionamento, int idCanal, int idPessoaUsuario, int inRegistrarHistorico);
	
	void validarSenha();
	int validarSenhaWS(char*ddd,char*linha,char*senha);
	void alterarSenha();
	void alterarFraseSecreta(long idPessoa, long idPessoaLinha, int idTipoRelacionamento, int idCanal, int idPessoaUsuario);
	void registrarPrimeiroAcesso(int idCanal, int idPessoaUsuario, long idPessoaLinha, int iIdTerminal);

private:

	CPessoaAbstract *m_pPessoaAbstract;

	// Dados de Negocio
	int  m_iIdSenha;
	int  m_iIdTipoSenha;
	char m_cSenha[256];
	char m_cDsFraseSecreta[256];
    char m_cDsLembreteFraseSecreta[256];
	char m_cDsLembreteSenha[256];
	char m_cdSenha[256];
	int  m_iQtTentFraseSecreta;
	int  m_StatusFraseSecreta;

	// Métodos de acesso a banco de dados
 	void carregarDadosSenhaDB(long idPessoa, long idPessoaLinha, int idTipoRelacionamento);
	void excluirFraseSecretaDB(long idPessoa, long idPessoaLinha, int idTipoRelacionamento, int idCanal, int idPessoaUsuario);
	void alterarQtTentFraseSecretaDB(long idPessoa, long idPessoaLinha, int idTipoRelacionamento);
	void bloquearFraseSecretaDB(long idPessoa, long idPessoaLinha, int idTipoRelacionamento, int idCanal, int idPessoaUsuario, int inRegistrarHistorico);
	void validarSenhaLojistaDB(void);
	void alterarSenhaLojistaDB(void);
 	void alterarDadosSenhaDB(long idPessoa, long idPessoaLinha, int idTipoRelacionamento, int idCanal, int idPessoaUsuario);
	void registrarPrimeiroAcessoDB(int idCanal, int idPessoaUsuario, long idPessoaLinha, int iIdTerminal);
	
};

#endif // CSENHA
