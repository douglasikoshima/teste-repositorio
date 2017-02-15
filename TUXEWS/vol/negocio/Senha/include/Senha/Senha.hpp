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
	char *getNmPessoa();
	int   getIdTipoLinha();

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
	void setNmPessoa(char *value);
	void setIdTipoLinha(int value);

	// Operação de Negocio (Interface)
	void consultarLembreteFraseSecreta(int idPessoa, int idPessoaLinha, int idTipoRelacionamento);
	void excluirFraseSecreta(int idPessoa, int idPessoaLinha, int idTipoRelacionamento, int idCanal, int idPessoaUsuario);
	void alterarQtTentFraseSecreta(int idPessoa, int idPessoaLinha, int idTipoRelacionamento);
	void bloquearFraseSecreta(int idPessoa, int idPessoaLinha, int idTipoRelacionamento, int idCanal, int idPessoaUsuario, int inRegistrarHistorico);
	
	void validarSenha();
	int validarSenhaWS(char*ddd,char*linha,char*senha);
	void alterarSenha();
	void alterarFraseSecreta(int idPessoa, int idPessoaLinha, int idTipoRelacionamento, int idCanal, int idPessoaUsuario);
	void registrarPrimeiroAcesso(int idCanal, int idPessoaUsuario, int idPessoaLinha, int iIdTerminal);
	void consultarDadosCliente(char*ddd,char*linha);
	
	// DEMANDA 23357 - SAPO
	void setNmNomeCompleto(char *value);
	void setDtNascimento(char *value);
	void setSgSexo(char *value);
	char *		getNmNomeCompleto();	
	char *		getDtNascimento();	
	char *		getSgSexo();	
	
	// OS 1476 
	void setEmail(char *value);
	char *		getEmail();
	
	// Alteração VivoPlay
	int zerarQtdTentativaSenha( int idLinhaTelefonica, int idtipoStatusSenha );

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
	char m_nmPessoa[256];
	int  m_idTipoLinha;
	
	// DEMANDA 23357 - SAPO
	char m_cNmNomeCompleto[255 + 1];
	char m_cDtNascimento[255 + 1];
	char m_cSgSexo[255 + 1];
	
	// OS 1476
	char m_e_mail[255+1];

	// Métodos de acesso a banco de dados
 	void carregarDadosSenhaDB(int idPessoa, int idPessoaLinha, int idTipoRelacionamento);
	void excluirFraseSecretaDB(int idPessoa, int idPessoaLinha, int idTipoRelacionamento, int idCanal, int idPessoaUsuario);
	void alterarQtTentFraseSecretaDB(int idPessoa, int idPessoaLinha, int idTipoRelacionamento);
	void bloquearFraseSecretaDB(int idPessoa, int idPessoaLinha, int idTipoRelacionamento, int idCanal, int idPessoaUsuario, int inRegistrarHistorico);
	void validarSenhaLojistaDB(void);
	void alterarSenhaLojistaDB(void);
 	void alterarDadosSenhaDB(int idPessoa, int idPessoaLinha, int idTipoRelacionamento, int idCanal, int idPessoaUsuario);
	void registrarPrimeiroAcessoDB(int idCanal, int idPessoaUsuario, int idPessoaLinha, int iIdTerminal);
	
};

#endif // CSENHA
