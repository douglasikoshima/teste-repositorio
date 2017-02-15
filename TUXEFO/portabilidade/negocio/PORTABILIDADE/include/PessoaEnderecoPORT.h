#ifndef PESSOA_ENDERECOHPORT
#define PESSOA_ENDERECOHPORT

#include "tuxfw.h"
#include "PessoaEnderecopcPORT.h"

class CPessoaEndereco:private TuxHelper
{
public:
	TPessoaEndereco     tPessoaEndereco;
	CPessoaEnderecopc	clPessoaEnderecopc;

    CPessoaEndereco( void );
    ~CPessoaEndereco( void );

    void  inserePessoaEndereco( void );
    void  apagaPessoaEndereco( void );
    void  atualizaPessoaEndereco( void );
    bool  buscaPessoaEndereco( void );
    bool  buscaPessoaEndereco(TPessoaEndereco *ptPessoaEnderecoAUX);
    void  enderecoSujo(void);

    void  setIdPessoaEndereco( char* pszIdPessoaEndereco );
    void  setIdSistemaOrigem( char* iIdSistemaOrigem );
    void  setIdPessoa( char* pszIdPessoa );
    void  setIdTipoEndereco( char* pszIdTipoEndereco );
    void  setTsSincronismo( char* pszTsSincronismo );
    void  setSqSincronismo( char* pszSqSincronismo );
    void  setNrSequencia( char* pszNrSequencia);
    void  setIdEnderecoSistemaOrigem( char* pszIdEnderecoSistemaOrigem );
    void  setIdPais(char *pszIdPais);
    void  setIdUf(char *pszIdUf);
    void  setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void  setInEnderecoSujo(char *pszInEnderecoSujo);
    void  setStruct(TPessoaEndereco *ptPessoaEndereco);


    char* getIdPessoa(void);
    char* getIdSistemaOrigem(void);
    char* getSqSincronismo( void );
    char* getIdEnderecoSistemaOrigem( void );
    char* getIdPessoaSistemaOrigem( void );
    char* getIdPessoaEndereco( void );
    char* getIdTipoEndereco(void);
    char* getInEnderecoSujo(void);
	char* getNmTituloLogradouro(void);
	char* getNmTipoLogradouro(void);
	char* getNmLogradouro(void);
	char* getNrEndereco(void);
	char* getDsEnderecoComplemento(void);
	char* getNmBairro(void);
	char* getNmMunicipio(void);
	char* getNrCep(void);
	char* getIdUF(void);
	char* getIdPais(void);
};

#endif
