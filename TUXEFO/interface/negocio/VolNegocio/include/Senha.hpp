// Senha.h: interface for the CSenha class.
//
//////////////////////////////////////////////////////////////////////

#ifndef CSENHA
#define CSENHA

//extern class CPessoa;

class CSenha  
{
public:
    CSenha();
    virtual ~CSenha();

	// Métodos de acesso aos atributos
 	// Getters
	int getIdPessoa();
 	char *getDsFraseSecreta();
 	char *getDsLembreteFraseSecreta();
 	char *getDsLembreteSenha();
	char *getSenha();

 	// Setters
	void setIdPessoa(int value);
	void setDsFraseSecreta(char* value);
 	void setDsLembreteFraseSecreta(char* value);
 	void setDsLembreteSenha(char* value);
	void setSenha(char* value);

	// Operação de Negocio (Interface)
	char* consultarLembreteFraseSecreta(int idPessoa);
	char* consultarFraseSecreta(int idPessoa);
	char* obterLembreteSenha(int idPessoa);
	char* consultarDadosSessaoDB(int, int);
	void alterarFraseSecreta(int idPessoa, char* dsFraseSecreta, char* dsLembreteFraseSecreta);

private:

  //  CPessoa* m_pPessoa;

	// Dados de Negocio
	int  m_iIdPessoa;
	char m_cSenha[256];
	char m_cDsSenha[256];
	char m_cDsFraseSecreta[256];
    char m_cDsLembreteFraseSecreta[256];
	char m_cDsLembreteSenha[256];

	// Métodos de acesso a banco de dados
 	void carregarDadosSenhaDB(void);
 	void alterarDadosSenhaDB(void);
};

#endif // CSENHA
