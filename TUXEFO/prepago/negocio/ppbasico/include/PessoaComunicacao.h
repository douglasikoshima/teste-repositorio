///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaComunicacao
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOACOMUNICACAOH
#define PESSOACOMUNICACAOH

#include "tuxfw.h"
#include "PessoaComunicacaopc.h"

class CPessoaComunicacao : private TuxHelper
{

public:
    TPessoaComunicacao      tPessoaComunicacao;
    TPessoaComunicacaoArr   tPessoaComunicacaoArr;

    CPessoaComunicacaopc    clPessoaComunicacaopc;

    CPessoaComunicacao( void );
    ~CPessoaComunicacao( void );

    void setIdPessoaComunicacao( char* pszIdPessoaComunicacao );
    void setIdSistemaOrigem( char* pszIdSistemaOrigem );
    void setIdPessoa( char* pszIdPessoa );
    void setIdPessoa( int iIdPessoa );
    void setIdTipoComunicacao( char* pszIdTipoComunicacao );
    void setDsContato( char* pszDsContato );
    void setTsSincronismo( char* pszTsSincronismo );
    void setSqSincronismo( char* pszSqSincronismo );
    void setIdComunicacaoSistemaOrigem( char* pszIdComunicacaoSistemaOrigem );
    void setIdComunicacaoSistemaOrigem( char* pszIdComunicacaoSistemaOrigem, char* pszPrefix );
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setStruct(TPessoaComunicacao *ptPessoaComunicacao);

    char* getSqSincronismo( void );
    char* getIdTipoComunicacao( void );
    char* getIdPessoaSistemaOrigem( void );
    char* getIdComunicacaoSistemaOrigem( void );

    void inserePessoaComunicacao( void );
    void apagaPessoaComunicacao( void );
    void atualizaPessoaComunicacao( void );

    bool buscaPessoaComunicacao( void );
    bool buscaPessoaComunicacao( TPessoaComunicacao &tPessoaComunicacaoAUX );

	//Recupera o os tipos de comunicacao de uma pessoa
    bool buscaPessoaComunicacaoPorIdPessoa( void );
    bool buscaTodosPessoaComunicacaoPorIdPessoa( void );

    //Métodos get, baseados em TPessoaComunicacaoArr
    char* getIdPessoaComunicacao( int iIndex );
	char* getIdPessoa( int iIndex );
	char* getIdTipoComunicacao( int iIndex );
	char* getIdSistemaOrigem( int iIndex );
	char* getNrSequencia( int iIndex );
	char* getDsContato( int iIndex );
	char* getDtCadastro( int iIndex );
	char* getTsSincronismo( int iIndex );
	char* getSqSincronismo( int iIndex );
	char* getIdComunicacaoSistemaOrigem( int iIndex );
	char* getDtExpiracao( int iIndex );
	char* getInComunicacaoPreferencial( int iIndex );
	char* getIdUsuarioAlteracao( int iIndex );
	char* getDtUltimaAlteracao( int iIndex );
	char* getNmContato( int iIndex );

	//Retorna a quantidade de elementos em TPessoaEnderecoArr
	int getQuantidade( void ) { return tPessoaComunicacaoArr.iQuantidade; };

};

#endif
