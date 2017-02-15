#ifndef APOIOTIPOCOMUNICACAOHPORT
#define APOIOTIPOCOMUNICACAOHPORT

#include "tuxfw.h"
#include "Global.h"
#include "ApoioTipoComunicacaopc.h"

class CApoioTipoComunicacao
{

public:
	TTipoComunicacao tTipoComunicacao;
	CApoioTipoComunicacaopc clTipoComunicacaopc;

    CApoioTipoComunicacao(void);
    bool buscaTipoComunicacao(void);

    void setSgClassificacao(char *pszSgClassificacao);
    void setSgTipoComunicacao(char *pszSgTipoComunicacao);
    void setDsTipoComunicacao(char *pszDsTipoComunicacao);

    char *getIdTipoComunicacao(void);
    char *getSgTipoComunicacao(void);
    char *getSgClassificacao(void);
    char *getDsTipoComunicacao(void);
};

#endif
