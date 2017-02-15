///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase Conta
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:23 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <memory.h>
#include <stdlib.h>
#include <string.h>
#include <ctype.h>

#include "PrePagoException.h"
#include "Conta.h"
#include "tuxfw.h"

CConta::CConta(void)
{
    memset(&tConta, 0x00, sizeof(TConta));
}

void CConta::limpaConta(void)
{
    memset(&tConta, 0x00, sizeof(TConta));
}

void CConta::insereConta(void)
{
    // Verificar qual o Status Default para insercao da Conta Grupo.
    strcpy(tConta.szIdStatusConta, ID_NULL);

    strcpy(tConta.szIdLayoutConta, ID_NULL);
    strcpy(tConta.szTsSincronismoCiclo, SEM_VALOR);
    strcpy(tConta.szSqSincronismoCiclo, SEM_VALOR);
    strcpy(tConta.szTsSincronismoStatus, SEM_VALOR);
    strcpy(tConta.szSqSincronismoStatus, SEM_VALOR);
    strcpy(tConta.szTsSincronismoPrincipal, SEM_VALOR);
    strcpy(tConta.szSqSincronismoPrincipal, SEM_VALOR);

    clContapc.proCInsereConta(&tConta);
}

void CConta::falsoInsereConta(void)
{
    strcpy(tConta.szIdTipoConta, ID_NULL);
    strcpy(tConta.szIdStatusConta, ID_NULL);
    strcpy(tConta.szIdLayoutConta, ID_NULL);
    strcpy(tConta.szTsSincronismoCiclo, SEM_VALOR);
    strcpy(tConta.szSqSincronismoCiclo, SEM_VALOR);
    strcpy(tConta.szTsSincronismoStatus, SEM_VALOR);
    strcpy(tConta.szSqSincronismoStatus, SEM_VALOR);
    strcpy(tConta.szTsSincronismoPrincipal, SEM_VALOR);
    strcpy(tConta.szSqSincronismoPrincipal, SEM_VALOR);

	clContapc.proCInsereConta(&tConta);
}

bool CConta::atualizaConta(void)
{
	return clContapc.proCAtualizaConta(tConta);
}

bool CConta::apagaConta(void)
{
    return clContapc.proCApagaConta(tConta);
}

bool CConta::buscaConta(void)
{
    return clContapc.proCBuscaConta(&tConta);
}

bool CConta::buscaContaGrupo(void)
{
    return clContapc.proCBuscaContaGrupo(&tConta);
}

bool CConta::buscaConta(TConta *ptContaAux)
{
    return clContapc.proCBuscaConta(ptContaAux);
}

void CConta::setIdSistemaOrigem(char *pszIdSistemaOrigem)
{
    strcpy(tConta.szIdSistemaOrigem, pszIdSistemaOrigem);
}

char *CConta::getIdSistemaOrigem(void)
{
    static char szAux[LEN_IDSISTEMAORIGEM + LEN_EOS];

    strcpy(szAux, tConta.szIdSistemaOrigem);
    return szAux;
}

void CConta::setIdConta(char *pszIdConta)
{
    strcpy(tConta.szIdConta, pszIdConta);
}

char *CConta::getIdConta(void)
{
    static char szAux[LEN_IDCONTA + LEN_EOS];

    strcpy(szAux, tConta.szIdConta);
    return szAux;
}

void CConta::setTsSincronismoCiclo(char *pszTsSincronismoCiclo)
{
    strcpy(tConta.szTsSincronismoCiclo, pszTsSincronismoCiclo);
}

char *CConta::getTsSincronismoCiclo(void)
{
    static char szAux[LEN_TSSINCRONISMOCICLO + LEN_EOS];

    strcpy(szAux, tConta.szTsSincronismoCiclo);
    return szAux;
}

void CConta::setSqSincronismoCiclo(char *pszSqSincronismoCiclo)
{
    strcpy(tConta.szSqSincronismoCiclo, pszSqSincronismoCiclo);
}

char *CConta::getSqSincronismoCiclo(void)
{
    static char szAux[LEN_SQSINCRONISMOCICLO + LEN_EOS];

    strcpy(szAux, tConta.szSqSincronismoCiclo);
    return szAux;
}

void CConta::setSqSincronismoStatus(char *pszSqSincronismoStatus)
{
    strcpy(tConta.szSqSincronismoStatus, pszSqSincronismoStatus);
}

char *CConta::getSqSincronismoStatus(void)
{
    static char szAux[LEN_SQSINCRONISMOSTATUS + LEN_EOS];

    strcpy(szAux, tConta.szSqSincronismoStatus);
    return szAux;
}

void CConta::setTsSincronismoStatus(char *pszTsSincronismoStatus)
{
    strcpy(tConta.szTsSincronismoStatus, pszTsSincronismoStatus);
}

char *CConta::getTsSincronismoStatus(void)
{
    static char szAux[LEN_TSSINCRONISMOSTATUS + LEN_EOS];

    strcpy(szAux, tConta.szTsSincronismoStatus);
    return szAux;
}

void CConta::setSqSincronismoPrincipal(char *pszSqSincronismoPrincipal)
{
    strcpy(tConta.szSqSincronismoPrincipal, pszSqSincronismoPrincipal);
}

char *CConta::getSqSincronismoPrincipal(void)
{
    static char szAux[LEN_SQSINCRONISMOPRINCIPAL + LEN_EOS];

    strcpy(szAux, tConta.szSqSincronismoPrincipal);
    return szAux;
}

void CConta::setTsSincronismoPrincipal(char *pszTsSincronismoPrincipal)
{
    strcpy(tConta.szTsSincronismoPrincipal, pszTsSincronismoPrincipal);
}

char *CConta::getIdContaSistemaOrigem(void)
{
    static char szAux[LEN_IDCONTASISTEMAORIGEM + LEN_EOS];

    strcpy(szAux, tConta.szIdContaSistemaOrigem);
    return szAux;
}

void CConta::setIdLayoutConta(char *pszIdLayoutConta)
{
    strcpy(tConta.szIdLayoutConta, pszIdLayoutConta);
}

void CConta::setNrConta(char *pszNrConta)
{
    strcpy(tConta.szCdConta, pszNrConta);
}

void CConta::setIdContaSistemaOrigem(char *pszIdContaSistemaOrigem)
{
    strcpy(tConta.szIdContaSistemaOrigem, pszIdContaSistemaOrigem);
}

void CConta::setCdConta(char *pszCdConta)
{
    strcpy(tConta.szCdConta, pszCdConta);
}

void CConta::setIdStatusConta(char *pszStatusConta)
{
    strcpy(tConta.szIdStatusConta, pszStatusConta);
}

void CConta::setIdTipoConta(char *pszIdTipoConta)
{
    strcpy(tConta.szIdTipoConta, pszIdTipoConta);
}

void CConta::setCdCicloFaturamento(char *pszCdCicloFaturamento )
{
    strcpy(tConta.szCdCicloFaturamento, pszCdCicloFaturamento);
}

void CConta::setCdDigitoConta(char *pszCdDigitoConta)
{
    strcpy(tConta.szCdDigitoConta, pszCdDigitoConta);
}

void CConta::setCdCicloFaturamentoLP(char *pszCdCicloFaturamentoLP)
{
    strcpy(tConta.szCdCicloFaturamento, pszCdCicloFaturamentoLP);
}

void CConta::setInContaPorEmail(char *pszInContaPorEmail)
{
    strcpy(tConta.szInContaPorEmail, pszInContaPorEmail);
}

void CConta::setNrDiaPeriodoCicloDe(char *pszNrDiaPeriodoCicloDe)
{
    strcpy(tConta.szNrDiaPeriodoCicloDe, pszNrDiaPeriodoCicloDe);
}

void CConta::setNrDiaVencimento(char *pszNrDiaVencimento)
{
    strcpy(tConta.szNrDiaVencimento, pszNrDiaVencimento);
}

void CConta::setDtExpiracao(char *pszDtExpiracao)
{
    strcpy(tConta.szDtExpiracao, pszDtExpiracao);
}

void CConta::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tConta.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CConta::setSgTipoConta(char *pszSgTipoConta)
{
    strcpy(szSgTipoConta, pszSgTipoConta);
}
