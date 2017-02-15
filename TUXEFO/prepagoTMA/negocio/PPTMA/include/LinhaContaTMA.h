#ifndef LINHACONTAHTMA
#define LINHACONTAHTMA

#include "LinhaContapcTMA.h"
#include "tuxfw.h"

class CLinhaConta:private TuxHelper
{

public:

    TLinhaConta tLinhaConta;
    CLinhaContapc clLinhaContapc;

    CLinhaConta(void);

    bool buscaLinhaConta(void);
    bool buscaLinhaConta(TLinhaConta *ptLinhaContaAux);
    void atualizaLinhaConta(void);
    void insereLinhaConta(void);

    char *getIdLinhaConta(void);
    char *getSqSincronismo(void);
    char *getIdConta(void);
    char *getIdLinhaTelefonica(void);
    char *getIdTipoRelacionamento(void);

    void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);
    void setIdConta(char *pszIdConta);
    void setIdTipoRelacionamento(char *pszTipoRelacionamento);
    void setDtLinhaConta(char *pszDtLinhaConta);
    void setIdLinhaConta(char *pszIdLinhaConta);
    void setSqSincronismo(char *pszSqSincronismo);
    void setTsSincronismo(char *pszTsSincronismo);
    void setDtExpiracao(char *pszDtExpiracao);

    void setStruct(TLinhaConta *ptLinhaConta);
};

#endif
