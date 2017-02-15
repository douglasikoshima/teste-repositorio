// Pessoa.h: interface for the CPessoa class.
//
//////////////////////////////////////////////////////////////////////

#include <Senha/Senha.hpp>
#include <Endereco/Endereco.hpp>
#include <Relacionamento/Relacionamento.hpp>
#include <Processo/Processo.hpp>
#include <Util/Util.hpp>
#include <Email/Email.hpp>

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
    long			getIdPessoa();
	int			getIdPessoaDePara();
 	char *		getNmPessoa();
	char *		getDsContato();
 	int			getIdUsuarioAlteracao();
	char *		getDtUltimaAlteracao();
	int         getIdTipoRelacionamento();
	char*       getSgTipoRelacionamento();
	char*       getNomeCliente();
	char*       getNomeUsuario();
	int         getIdConta(void);
	char*       getIdLinhaSistemaOrigem(void);
	char*		getIdContaSistemaOrigem(void);
	long         getIdPessoaByCpf(void);
	char*       getCpf(void);
	long        getIdPessoaLinha(void);
	char*       getDataSectionValues(int, int);
	int         getIdTipoCarteira(void);
	int         getIdTipoPessoa(void);
	long        getIdPessoaCliente();
	int         getTipoComunicacao();
	int			getIdTipoCliente();
	int			getIdTipoUsuario();

	char*		getNrDocumento();
	char*		getDsTipoDocumento();
	int getNrLinha();
	int getCdAreaRegistro();

	list <CEndereco> &getListaEnderecos();
	list <CEmail>  &getListaEmails();

    CEndereco*	     getEndereco();
	CRelacionamento* getRel();
	CSenha&          getSenha();
	CEmail*			 getEmail();

 	// Setters
	void setIdTipoRelacionamento(int);
	void setSgTipoRelacionamento(char *);
 	void setIdPessoa(long value);
	void setIdPessoaDePara(int value);
 	void setNmPessoa(char *value);
	void setNomeUsuario(char *value);
	void setNomeCliente(char *value);
 	void setDsContato(char *value);
 	void setIdUsuarioAlteracao(int value);
	void setDtUltimaAlteracao(char *value);
	void setEndereco( const CEndereco & value );
	void setIdConta(int);
	void setIdLinhaSistemaOrigem(char *);
	void setCpf(char *);
	void setIdPessoaLinha(long);
	void setIdTipoCarteira(int);
	void setIdTipoPessoa(int);
	void setIdPessoaCliente(long);
	void setTipoComunicacao(int value);
	void setIdEmail(int value);
	void setIdTipoEmail(int value);
	void setIdTipoCliente (int value);
	void setIdTipoUsuario (int value);
	void setIdContaSistemaOrigem(char *);
	void setNrDocumento(char *);
	void setDsTipoDocumento(char *);
	void setNrLinha(int nrLinha);
	void setCdAreaRegistro(int cdAreaRegistro);
	
  void set_dtHabilitacao(char * value );
  void set_dtAlteracaoStatus(char * value );
  char *  get_dtHabilitacao();
  char *  get_dtAlteracaoStatus();
	
	

	// Operação de Negocio (Interface)
	void consultarNome(void);
	void consultarNomeUsuario(int, int);
	void consultarNomeCliente(int, int);
	void consultarEMail(void);
	void consultarDocumento();
	void alterarEMail(void);
	void inserirEMail(void);
	
	// Acesso a Interface de CSenha - Delegação
	char* consultarLembreteFraseSecreta(void);
	//char* consultarFraseSecreta(void);
	//void alterarFraseSecreta(char* dsFraseSecreta, char* dsLembreteFraseSecreta); 
	void inserirFraseSecreta(char*, char*);
	void obterDadosPessoaSessao(int, int, char*);
	void obterDadosPessoaSessaoSemSenha( int, int );
	void obterIdPessoa(int, int);
	void obterIdPessoaLinha(int cdAreaRegistro, int nrLinha);
	
	// Acesso a Interface de CEndereco - Delegação
	void alterarEndereco();
	void consultarEnderecos();
	void consultarEnderecoCobranca();
	void inserirEndereco();
	//Acessoa a Interface de CEndereco
	//
	void consultarHistQtdRelac( list < CRelacionamento > & listaRelac, char *, char * );
	void consultarHistQtdRelacCanal( list < CRelacionamento > & listaRelac, int, int );
	void consultarHistRangeDate( list < CRelacionamento > & listaRelac, char *, char *, char *);

	//Acessoa a Interface de CProcessamento
	void  consultarRangeDateProcessos(  list< CProcesso > & lstOProc, 	char *  cDataInicio, char * cDataFinal);
	void  consultarUltimosProcessos(	list< CProcesso > & listaOProc, char * cQtProc);
	char* getSenhaData( );

	//
	void  consultarDispComunicacao();

	//Acessos a Interface de CEmail
	void consultarEmails();
	void consultaTbDocumento(char *chrCpf, int *intIdDocumento);
	void consultarTipoPessoa(int, int , int);
	void consultaDadosPessoaPP(char* cNrDocumento,  int iIdDocumento, XMLGen* xml_g);
	void consultaSiglasCadastro( XMLGen* xml_g);
	bool consultarEnderecoPremio();

	
	// DEMANDA SAPO
	char m_cNmNome[255 + 1]; // primeiro nome
	char m_cDtNascimento[255 + 1];
	char m_cSgSexo[255 + 1];
	
	void setNmNome(char *value);
	void setDtNascimento(char *value);
	void setSgSexo(char *value);
	char *		getNmNome();	
	char *		getDtNascimento();	
	char *		getSgSexo();		

private:

    // Instancia da Classe CSenha (Impl. da Composição com Senha, 1 pra 1)
	CSenha m_oSenha;
//	CRelacionamento m_oRelacionamento;
    // Referencia da Classe CEndereco (Impl. da Composição com Endereço, 1 pra N).
    CEndereco*	m_oEndereco;
    CRelacionamento *m_oRelac;
	CEmail *m_oEmail;


	CProcesso m_oProc;

	list <CEndereco> listaEnderecos;
	list <CEmail> listaEmails;


	// Atributos de Negocio
	long  m_iIdPessoa;
	int  m_iIdPessoaDePara;
	long  m_iIdPessoaLinha;
	int  m_iIdConta;
	long m_iIdPessoaCliente;
	char m_cIdLinhaSistemaOrigem[255+1];
	char m_cIdContaSistemaOrigem[255+1];
	
	char m_dtHabilitacao[64];
	char m_dtAlteracaoStatus[64];

	char m_cNmPessoa[256];
	char m_cNomeUsuario[256];
	char m_cNomeCliente[256];
	char m_cDsContato[ 256 ];
 	int  m_iIdUsuarioAlteracao;
	char m_cCpf[11+1];
	char m_cDtUltimaAlteracao[ 51 ];
	int  m_iIdTipoRelacionamento;
	char m_cSgTipoRelacionamento[2];
	int  m_iIdTipoPessoa;
	int  m_iIdTipoCarteira;
	int  m_iTipoComunicacao;
	int  m_iIdEmail;
	int  m_iIdTipoEmail;
	int  m_iIdTipoCliente;
	int	 m_iIdTipoUsuario;
	char m_cNrDocumento[255 + 1];
	char m_cDsTipoDocumento[255 + 1];
	int m_nrLinha;
	int m_cdAreaRegistro;
	

	
	


	// Métodos de acesso a banco de dados
 	void consultarIdPessoaDB(int codArea, int nroLinha);
	void consultarIdPessoaLinhaDB(int cdAreaRegistro, int nrLinha);
	void consultarDadosSessaoDB(int iArea, int iMobNro, char*);
	void consultarDadosSessaoSemSenhaDB( int iArea, int iMobNro );
	void consultarNmPessoaDB(void);
 	void consultarDsContatoDB(void);
 	void alterarDsContatoDB(void);
	void inserirDsContatoDB(void);
	void consultarNomeUsuarioDB(int, int);
	void consultarNomeClienteDB(int, int);
	long  getIdPessoaByCpfDB(void);

	void consultarDispComunicacaoDB();
	void consultarEnderecosDB();
	void consultarEmailsDB();
};
#endif		// !defined( CPESSOA )

