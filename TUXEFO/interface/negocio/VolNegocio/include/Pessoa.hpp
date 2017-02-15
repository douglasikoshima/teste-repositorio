// Pessoa.h: interface for the CPessoa class.
//
//////////////////////////////////////////////////////////////////////

#include "Senha.hpp"
#include "Endereco.hpp"
#include "Relacionamento.hpp"
#include "Processo.hpp"

#include "Util.hpp"
#include <list>
using namespace std;

#ifndef CPESSOA
#define CPESSOA

class CPessoa  {
	
public:
	CPessoa();
    virtual ~CPessoa();

	// Metodo da Classe Pessoa para atender pedido de Instanciação de outras classes
	// que possue associação com CPessoa.
    static CPessoa* createInstance();


	// Métodos de acesso aos atributos
 	// Getters
 	int			getIdPessoa();
 	char *		getNmPessoa();
	char *		getDsContato();
 	int			getIdUsuarioAlteracao();
	char *		getDtUltimaAlteracao();
	int getIdTipoRelacionamento();
	char* getSgTipoRelacionamento();
	char* getNomeCliente();
	char* getNomeUsuario();
	int getIdConta(void);
	char* getIdSistOrig(void);

    CEndereco*	getEndereco();

	char* getDataSectionValues(int, int);
 	// Setters
	void setIdTipoRelacionamento(int);
	void setSgTipoRelacionamento(char *);
 	void setIdPessoa(int value);
 	void setNmPessoa(char *value);
	void setNomeUsuario(char *value);
	void setNomeCliente(char *value);
 	void setDsContato(char *value);
 	void setIdUsuarioAlteracao(int value);
	void setDtUltimaAlteracao(char *value);
	void setEndereco( const CEndereco & value );
	void setIdConta(int);
	void setIdSistOrig(char *);

	// Operação de Negocio (Interface)
	void consultarNome(void);
	void consultarNomeUsuario(int, int);
	void consultarNomeCliente(int, int);
	void consultarEMail(void);
	void alterarEMail(void);
	
	// Acesso a Interface de CSenha - Delegação
	char* consultarLembreteFraseSecreta(void);
	char* consultarFraseSecreta(void);
	char* obterLembreteSenha(int codArea, int nroLinha);
	void alterarFraseSecreta(char* dsFraseSecreta, char* dsLembreteFraseSecreta);          
	void obterDadosSessao(int, int, char* );
	void obterIdPessoa(int, int);
	
	// Acesso a Interface de CEndereco - Delegação
	void alterarEndereco();
	void consultarEndereco();
	void inserirEndereco();
//Acessoa a Interface de CEndereco
	//
	void consultarHistQtdRelac( list < CRelacionamento > & listaRelac, char *, char * );
	void consultarHistQtdRelacCanal( list < CRelacionamento > & listaRelac, int, int );
	void consultarHistRangeDate( list < CRelacionamento > & listaRelac, char *, char *, char *);

//Acessoa a Interface de CProcessamento
	void consultarRangeDateProcessos(  list< CProcesso > & lstOProc, 	char *  cDataInicio, char * cDataFinal);
	void consultarUltimosProcessos(	list< CProcesso > & listaOProc, char * cQtProc);

private:

    // Instancia da Classe CSenha (Impl. da Composição com Senha, 1 pra 1)
	CSenha m_oSenha;
//	CRelacionamento m_oRelacionamento;
    // Referencia da Classe CEndereco (Impl. da Composição com Endereço, 1 pra N).
    CEndereco*	m_oEndereco;

    CRelacionamento m_oRelac;


	CProcesso m_oProc;
	// Atributos de Negocio
 	int  m_iIdPessoa;
	int  m_iIdConta;
	char  m_cIdSistOrig[255+1];
	char m_cNmPessoa[256];
	char m_cNomeUsuario[100];
	char m_cNomeCliente[100];
	char m_cDsContato[ 256 ];
 	int  m_iIdUsuarioAlteracao;
	char m_cDtUltimaAlteracao[ 51 ];
	int m_iIdTipoRelacionamento;
	char m_cSgTipoRelacionamento[2];


	// Métodos de acesso a banco de dados
 	void consultarIdPessoaDB(int codArea, int nroLinha);
	void consultarDadosSessaoDB(int iArea, int iMobNro, char*);
	void consultarNmPessoaDB(void);
 	void consultarDsContatoDB(void);
 	void alterarDsContatoDB(void);
	void consultarNomeUsuarioDB(int, int);
	void consultarNomeClienteDB(int, int);
};
#endif		// !defined( CPESSOA )

