#ifndef APOIOPARAMETROH
#define APOIOPARAMETROH

#include "Global.h"

class ApoioParametro
{
public:

    TApoioParametro tApoioParametro;

    ApoioParametro(char *pszIdUsuarioAlteracao);
    ~ApoioParametro(void);

    void setCdParametro(char *pszCdParametro);
    void setDsParametro(char *pszDsParametro);
    void setDsValorParametro(char *pszDsValorParametro);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    char *getCdParametro(void);
    char *getDsValorParametro(void);


    void atualizaApoioParametro(void);
    bool buscaApoioParametro(void);
};

#endif
