// Endereco.h: interface for the CEndereco class.
//
//////////////////////////////////////////////////////////////////////



#if			!defined( CENDERECO )

#define		CENDERECO

#include <tuxfw.h>
#include "Defines.h"
#include "Util.hpp"

#include <list>
using namespace std; 

class CEndereco  {
public:
    CEndereco();
    CEndereco( const CEndereco & value );
    virtual ~CEndereco();

    CEndereco &	operator =( const CEndereco & value );

	// Métodos de acesso aos atributos
 	// Getters
	int		getIdTipoEndereco();
	char *	getNmTipoLogradouro();
	char *	getNmTituloLogradouro();
	char *	getNmLogradouro();
	char *	getNrEndereco();
	char *	getDsEnderecoComplemento();
	char *	getNmBairro();
	char *	getNrCep();
	char *	getNmMunicipio();

	char * getSgTipoEndereco();
	char * getDsTipoEndereco();
	

 	// Setters
	void	setIdTipoEndereco( int value );
	void	setNmTipoLogradouro( char * value );
	void	setNmTituloLogradouro( char * value );
	void	setNmLogradouro( char * value );
	void	setNrEndereco( char * value );
	void	setDsEnderecoComplemento( char * value );
	void	setNmBairro( char * value );
	void	setNrCep( char * value );
	void	setNmMunicipio( char * value );

	void setSgTipoEndereco(char * value);
	void setDsTipoEndereco(char * value);


	// Operação de Negocio (Interface)
	void alterarEndereco( int iIdPessoa );
	void inserirEndereco( int iIdPessoa );
	void consultarEndereco( int iIdPessoa );

	static void consultarTipoEnd(list < CEndereco > & listaEnd);
	
private:
	int  m_iIdTipoEndereco;
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


	// Métodos de acesso a banco de dados
	void alterarDadosEnderecoDB( int iIdPessoa );
	void inserirDadosEnderecoDB( int iIdPessoa );
	void carregarDadosEnderecoDB( int iIdPessoa );
	static void carregarTipoEnderecoDB( list < CEndereco > & listaEnd );
};
#endif		// !defined( CENDERECO )

