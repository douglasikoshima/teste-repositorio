// Endereco.h: interface for the CEndereco class.
//
//////////////////////////////////////////////////////////////////////



#if			!defined( CENDERECO )

#define		CENDERECO

#include <tuxfw/tuxfw.h>
#include <Defines/Defines.h>
#include <Util/Util.hpp>
#include <Conta/Conta.hpp>

#include <list>
using namespace std; 

#define MAX_TIPO 12

class CEndereco  {
public:
    CEndereco();
    //CEndereco( const CEndereco & value );
    virtual ~CEndereco();

    //CEndereco &	operator =( const CEndereco & value );

	// Métodos de acesso aos atributos
 	// Getters
	int		getIdTipoEndereco();
	int		getIdSistemaOrigem();
	char *	getNmTipoLogradouro();
	char *	getNmTituloLogradouro();
	char *	getNmLogradouro();
	char *	getNrEndereco();
	char *	getDsEnderecoComplemento();
	char *	getNmBairro();
	char *	getNrCep();
	char *	getNmMunicipio();
	int     getIdUf();
	char *  getSgTipoEndereco();
	char *  getDsTipoEndereco();
	int     getIdConta();
	int     getIdPessoaEndereco();
	char *  getTsSinc();
	int     getIdTipoEnderecoCobranca();
	char *  getSgTipoEnderecoCobranca();
	char *  getDsTipoEnderecoCobranca();
	int     getIdTipoComplemento();
	char *  getSgTipoComplemento();
	char *  getDsTipoComplemento();
	int     getQuantTipo(int iTipoEndereco);
	

 	// Setters
	void	setIdTipoEndereco( int value );
	void	setIdSistemaOrigem( int value );
	void	setNmTipoLogradouro( char * value );
	void	setNmTituloLogradouro( char * value );
	void	setNmLogradouro( char * value );
	void	setNrEndereco( char * value );
	void	setDsEnderecoComplemento( char * value );
	void	setNmBairro( char * value );
	void	setNrCep( char * value );
	void	setNmMunicipio( char * value );
	void	setIdUf(int value);
	void	setSgTipoEndereco(char * value);
	void	setDsTipoEndereco(char * value);
	void	setTsSinc( char * );
	void    setIdConta(int value);
	void	setIdPessoaEndereco(int value);
	void    setIdTipoEnderecoCobranca(int   value);
	void    setSgTipoEnderecoCobranca(char *value);
	void    setDsTipoEnderecoCobranca(char *value);

	void    setIdTipoComplemento(int   value);
	void    setSgTipoComplemento(char *value);
	void    setDsTipoComplemento(char *value);

	void    incQuantTipo(int iTipoEndereco);

	list <CConta> &getListaContas();

	// Operação de Negocio (Interface)
	void alterarEndereco( int iIdPessoa );
	void inserirEndereco( int iIdPessoa );
	void associarContas();
	void consultarEndereco( int iIdPessoa );
	void consultarEnderecoCobranca( int iIdPessoa );
	void atualizarContaEndereco(void);
	void excluirEndereco();
	
	static void consultarTipoEnd(list < CEndereco > & listaEnd);
	static void consultarTipoEndCobranca(list < CEndereco > & listaEndCobranca);
	static void consultarTipoComplemento(list < CEndereco > & listaComplemento);
	
private:
	int  m_iIdTipoEndereco;
	int  m_iIdSistemaOrigem;
	char m_cNmTipoLogradouro[ 256 ];
	char m_cNmTituloLogradouro[ 256 ];
	char m_cNmLogradouro[ 256 ];
	char m_cNrEndereco[ 256 ];
	char m_cDsEnderecoComplemento[ 256 ];
	char m_cNmBairro[ 256 ];
	char m_cNrCep[ 256 ];
	char m_cNmMunicipio[ 256 ];
	char m_cDsTipoEndereco [ 256 ];
	char m_cSgTipoEndereco [ 256 ];
	int  m_iIdUf;
	int	 m_iIdConta;
	int  m_iIdPessoaEndereco;
	char m_iTsSinc[50];

	int  m_iIdTipoEnderecoCobranca;
	char m_cSgTipoEnderecoCobranca[256];
	char m_cDsTipoEnderecoCobranca[256];
	
	int  m_iIdTipoComplemento;
	char m_cSgTipoComplemento[256];
	char m_cDsTipoComplemento[256];

	int  m_iQuantTipo[MAX_TIPO];

	list<CConta> listaConta;

	// Métodos de acesso a banco de dados
	void alterarDadosEnderecoDB( int iIdPessoa );
	void inserirDadosEnderecoDB( int iIdPessoa );
	int  verificarEnderecoContaDB();
	void associarContasDB();
	void carregarDadosEnderecoDB( int iIdPessoa );
	void carregarDadosEnderecoCobrancaDB( int iIdPessoa );
	void excluirEnderecoDB();

	static void carregarTipoEnderecoDB( list < CEndereco > & listaEnd );
	static void carregarTipoEnderecoCobrancaDB( list < CEndereco > & listaEndCobranca );
	static void carregarTipoComplementoDB( list < CEndereco > & listaComplemento );
};              
#endif		// !defined( CENDERECO )

