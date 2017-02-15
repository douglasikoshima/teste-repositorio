///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase Conta
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef CONTAH
#define CONTAH

#include "Global.h"
#include "Contapc.h"
#include "tuxfw.h"

class CConta:private TuxHelper
{
    int     iTipoStruct;

public:
    TConta      tConta;

    CContapc    clContapc;
    char        szSgTipoConta[LEN_SGTIPOCONTA + LEN_EOS];

    CConta(void);

    bool buscaConta(void);
    bool buscaContaGrupo(void);
    bool buscaConta(TConta *ptContaAux);

    void insereConta(void);
    void falsoInsereConta(void);
    bool apagaConta(void);
    bool atualizaConta(void);

    void limpaConta(void);

    void setIdSistemaOrigem(char *pszIdSistemaOrigem);
    char *getIdSistemaOrigem(void);

    void setIdConta(char *pszIdConta);
    char *getIdConta(void);

    void setTsSincronismoCiclo(char *pszTsSincronismoCiclo);
    char *getTsSincronismoCiclo(void);

    void setSqSincronismoCiclo(char *pszSqSincronismoCiclo);
    char *getSqSincronismoCiclo(void);

    void setSqSincronismoStatus(char *pszSqSincronismoStatus);
    char *getSqSincronismoStatus(void);

    void setTsSincronismoStatus(char *pszTsSincronismoStatus);
    char *getTsSincronismoStatus(void);

    void setSqSincronismoPrincipal(char *pszSqSincronismoPrincipal);
    char *getSqSincronismoPrincipal(void);

    void setTsSincronismoPrincipal(char *pszTsSincronismoPrincipal);

    char *getIdPessoaSistemaOrigem(void);
    char *getIdEnderecoSistemaOrigem(void);
    char *getDtPessoaConta(void);
    char *getIdContaSistemaOrigem(void);

    void setIdLayoutConta(char *pszIdLayoutConta);
    void setNrConta(char *pszNrConta);
    void setIdContaSistemaOrigem(char *pszIdContaSistemaOrigem);
    void setCdConta(char *pszCdConta);
    void setIdStatusConta(char *pszStatusConta);
    void setIdStatusConta( void );
    void setIdTipoConta(char *pszIdTipoConta);
    void setCdCicloFaturamento(void);
    void setCdCicloFaturamento( char *pszCdCicloFaturamento );
    void setIdUsuarioAlteracao( char *pszIdUsuarioAlteracao );

    void setCdDigitoConta(char *pszCdDigitoConta);
    void setCdCicloFaturamentoLP(char *pszCdCicloFaturamentoLP);
    void setInContaPorEmail(char *pszInContaPorEmail);
    void setNrDiaPeriodoCicloDe(char *pszNrDiaPeriodoCicloDe);
    void setNrDiaVencimento(char *pszNrDiaVencimento);
    void setDtExpiracao(char *pszDtExpiracao);

    // Tratamento de Conta Grupo.
    void setSgTipoConta(char *pszSgTipoConta);
};

#endif
