///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaEndereco
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOA_ENDERECOH
#define PESSOA_ENDERECOH

#include "PessoaEnderecopc.h"
#include "Global.h"

class CPessoaEndereco : private TuxHelper
{
public:
	TPessoaEndereco     tPessoaEndereco;
	TPessoaEnderecoArr  tPessoaEnderecoArr;

	CPessoaEnderecopc	clPessoaEnderecopc;

    CPessoaEndereco( void );
    ~CPessoaEndereco( void );

    void  falsoInserePessoaEndereco( void );
    void  falsoInserePessoaEndereco( TPessoaEndereco &tPessoaEnderecoAUX  );
    void  inserePessoaEndereco( void );
    void  apagaPessoaEndereco( void );
    void  atualizaPessoaEndereco( void );
    void  BuscaSped(
                        char * szcdCEP, 
                        char * sznmLogradouro,
                        char * sznmBairro,
                        char * szMunicipio,
                        char * szCdLogradouro ,
                        char * szInCNL ,
                        char * szCdIBGE
                   );
    void  atualizaIdTipoEnderecoPessoaEndereco( void );
    bool  buscaPessoaEndereco( void );
    bool  buscaPessoaEndereco( TPessoaEndereco &tPessoaEnderecoAUX );
	//Recupera enderecos validos de uma pessoa
    bool  buscaPessoaEnderecoPorIdPessoa( void );
	bool  buscaPessoaEnderecoContaPorIdPessoa( char* pzcIdConta );
    bool  buscaEnderecoContaPorIdPessoa( char* pzcIdConta );
    void  enderecoSujo(void);

    void  setIdPessoaEndereco( char* pszIdPessoaEndereco );
    void  setIdSistemaOrigem( char* iIdSistemaOrigem );
    void  setIdPessoa( char* pszIdPessoa );
    void  setIdPessoa( int iIdPessoa );
    void  setIdTipoEndereco( char* pszIdTipoEndereco );
    void  setTsSincronismo( char* pszTsSincronismo );
    void  setSqSincronismo( char* pszSqSincronismo );
    void  setNrSequencia( char* pszNrSequencia);
    void  setIdEnderecoSistemaOrigem( char* pszIdEnderecoSistemaOrigem );
    void  setIdPais(char *pszIdPais);
    void  setIdUf(char *pszIdUf);
    void  setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void  setInEnderecoSujo(char *pszInEnderecoSujo);

	//Metodos recuperam itens de TPessoaEndereco
    char* getSqSincronismo( void );
    char* getIdEnderecoSistemaOrigem( void );
    char* getIdPessoaSistemaOrigem( void );
    char* getIdPessoaEndereco( void );
    char* getIdTipoEndereco(void);
    char* getInEnderecoSujo(void);

    void setStruct(TPessoaEndereco *ptPessoaEndereco);

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

    //Sobrecarga para recuperar itens de TPessoaEnderecoArr
	char* getIdPessoaEndereco(int iIndex);
    char* getIdTipoEndereco(int iIndex);
	char* getNmTituloLogradouro(int iIndex);
	char* getNmTipoLogradouro(int iIndex);
	char* getNmLogradouro(int iIndex);
	char* getNrEndereco(int iIndex);
	char* getDsEnderecoComplemento(int iIndex);
	char* getNmBairro(int iIndex);
	char* getNmMunicipio(int iIndex);
	char* getNrCep(int iIndex);
	char* getIdUF(int iIndex);
	char* getIdPais(int iIndex);
	char* getInEnderecoPreferencial(int iIndex);

	//Retorna a quantidade de elementos em TPessoaEnderecoArr
	int getQuantidade( void ) { return tPessoaEnderecoArr.iQuantidade; };

	void Zera( void );
};

#endif
