#ifndef TIPOCOMUNICACAOHPORT
#define TIPOCOMUNICACAOHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "TipoComunicacaopcPORT.h"

class CTipoComunicacao
{

public:
	TTipoComunicacao tTipoComunicacao;
	CTipoComunicacaopc clTipoComunicacaopc;

    CTipoComunicacao(void);
    bool buscaIdTipoComunicacao(void);

    void setSgClassificacao(char *pszSgClassificacao);
    void setSgTipoComunicacao(char *pszSgTipoComunicacao);

    char *getIdTipoComunicacao(void);
    char *getSgTipoComunicacao(void);
    char *getSgClassificacao(void);
};

#endif
