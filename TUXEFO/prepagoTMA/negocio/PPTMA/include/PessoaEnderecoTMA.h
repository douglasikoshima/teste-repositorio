#ifndef PESSOA_ENDERECOHTMA
#define PESSOA_ENDERECOHTMA

#include "tuxfw.h"
#include "PessoaEnderecopcTMA.h"

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
    bool  buscaPessoaEndereco( TPessoaEndereco &tPessoaEnderecoAUX );
    bool  existePessoaEndereco(const char *idPessoa,char *idPessoaEndereco);
    void  enderecoSujo(void);
    void  ProcuraSPED(char * nrCEP, char * nmLograd, char * nmBairro, char * nmCidade);
    void  BuscaSiglaUF( int idUF );

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
    void  excluirEndereco(const char *idPessoa);

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
    char* getSgUF(void);
	char* getIdPais(void);

    char* getCdLogradouro(void);
    char* getInCNL(void);
    char* getCdIbge(void);
};

#endif
