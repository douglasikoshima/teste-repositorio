#ifndef PESSOACOMUNICACAOHPORT
#define PESSOACOMUNICACAOHPORT

#include "tuxfw.h"
#include "PessoaComunicacaopcPORT.h"

class CPessoaComunicacao : private TuxHelper
{

public:
    TPessoaComunicacao      tPessoaComunicacao;
    CPessoaComunicacaopc    clPessoaComunicacaopc;

    CPessoaComunicacao( void );
    ~CPessoaComunicacao( void );

    void setIdPessoaComunicacao( char* pszIdPessoaComunicacao );
    void setIdSistemaOrigem( char* pszIdSistemaOrigem );
    void setIdPessoa( char* pszIdPessoa );
    void setIdTipoComunicacao( char* pszIdTipoComunicacao );
    void setDsContato( char* pszDsContato );
    void setTsSincronismo( char* pszTsSincronismo );
    void setSqSincronismo( char* pszSqSincronismo );
    void setIdComunicacaoSistemaOrigem( char* pszIdComunicacaoSistemaOrigem );
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setNmContato(char *pszNmContato);

    void setStruct(TPessoaComunicacao *ptPessoaComunicacao);

    char* getIdPessoa( void );
    char* getIdPessoaComunicacao( void );
    char* getSqSincronismo( void );
    char* getIdSistemaOrigem( void );
    char* getIdTipoComunicacao( void );
    char* getIdPessoaSistemaOrigem( void );
    char* getIdComunicacaoSistemaOrigem( void );
    char* getNmContato( void );
    char* getDsContato( void );

    void inserePessoaComunicacao( void );
    void atualizaPessoaComunicacao( void );
    bool buscaPessoaComunicacao( void );
    bool buscaPessoaComunicacao( TPessoaComunicacao *ptPessoaComunicacao);
    bool buscaPessoaComunicacaoIdPessoaSgClass(TPessoaComunicacao *ptPessoaComunicacao);
};

#endif
