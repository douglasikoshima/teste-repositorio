#ifndef __C_EMAIL__
#define __C_EMAIL__

#include <Util/Util.hpp>

#include <vector>
#include <list>
using namespace std;

#define ERRO_LINHA_NAO_ENCONTRADA    100
#define ERRO_EMAIL_NAO_ENCONTRADO    101
#define ERRO_LINHA_POSPAGA			 102

#define ERRO_MSG_LINHA_NAO_ENCONTRADA    "Linha não encontrada"
#define ERRO_MSG_EMAIL_NAO_ENCONTRADO    "E-mail não cadastrado"
#define ERRO_MSG_LINHA_POSPAGA			 "Linha é pós-paga"

class CEmail
{

public:
	CEmail();
	~CEmail();

	// Métodos de acesso aos atributos
 	// Getters
	int		getIdEmail();
	int		getIdTipoEmail();
	char   *getDsTipoEmail();		
	char   *getDsEmail();

 	// Setters
	void	setIdEmail(int value);
	void	setIdTipoEmail(int value);
	void	setDsTipoEmail(char *value);
	void	setDsEmail(char *value);

	// Operação de Negocio (Interface)
	void	consultarTiposEmail(list< CEmail > & lstEmail);
	void	incluirEmail(long iIdPessoa);
	void	alterarEmail();
	void	excluirEmail();
	bool	verificarEmail(long iIdPessoa);
	bool	verificarTipoEmail();
	int     getEmail(XMLGen*gen,char* linha,char* ddd);	
	int     consultarIdPessoa(int iCdAreaRegistro, int iNrLinha);

private:

	// Dados de Negocio
	int		m_iIdEmail;
	int		m_iIdTipoEmail;
	char	m_cDsTipoEmail[256];
	char	m_cDsEmail[256];

    // Métodos de acesso a banco de dados
	void	consultarTiposEmailDB(list< CEmail > & lstEmail);	
	void	incluirEmailDB(long iIdPessoa);
	void	alterarEmailDB();
	void	excluirEmailDB();
	bool	verificarEmailDB(long iIdPessoa);
	bool	verificarTipoEmailDB();

};

#endif
