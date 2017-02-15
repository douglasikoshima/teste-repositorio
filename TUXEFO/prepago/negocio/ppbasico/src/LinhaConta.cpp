///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase LinhaConta
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "LinhaContapc.h"
#include "Global.h"
#include "LinhaConta.h"
#include "PrePagoException.h"
#include <memory.h>

CLinhaConta::CLinhaConta(void)
{
    memset(&tLinhaConta, 0x00, sizeof(TLinhaConta));
}

char *CLinhaConta::getIdLinhaConta(void)
{
    static char szAux[LEN_IDLINHACONTA + LEN_EOS];

    strcpy(szAux, tLinhaConta.szIdLinhaConta);
    return szAux;
}

char *CLinhaConta::getIdLinhaTelefonica(void)
{
    static char szAux[LEN_IDLINHATELEFONICA + LEN_EOS];

    strcpy(szAux, tLinhaConta.szIdLinhaTelefonica);
    return szAux;
}

char *CLinhaConta::getSqSincronismo(void)
{
    static char szAux[LEN_SQSINCRONISMO + LEN_EOS];

    strcpy(szAux, tLinhaConta.szSqSincronismo);
    return szAux;
}

char *CLinhaConta::getIdConta(void)
{
    static char szAux[LEN_IDCONTA + LEN_EOS];

    strcpy(szAux, tLinhaConta.szIdConta);
    return szAux;
}

void CLinhaConta::setIdLinhaTelefonica(char *pszIdLinhaTelefonica)
{
    strcpy(tLinhaConta.szIdLinhaTelefonica, pszIdLinhaTelefonica);
}

void CLinhaConta::setIdConta(char *pszIdConta)
{
    strcpy(tLinhaConta.szIdConta, pszIdConta);
}

void CLinhaConta::setIdTipoRelacionamento(char *pszTipoRelacionamento)
{
    strcpy(tLinhaConta.szIdTipoRelacionamento, pszTipoRelacionamento);
}

void CLinhaConta::setDtLinhaConta(char *pszDtLinhaConta)
{
    strcpy(tLinhaConta.szDtLinhaConta, pszDtLinhaConta);
}

void CLinhaConta::setIdLinhaConta(char *pszIdLinhaConta)
{
    strcpy(tLinhaConta.szIdLinhaConta, pszIdLinhaConta);
}

void CLinhaConta::setSqSincronismo(char *pszSqSincronismo)
{
    strcpy(tLinhaConta.szSqSincronismo, pszSqSincronismo);
}

void CLinhaConta::setTsSincronismo(char *pszTsSincronismo)
{
    strcpy(tLinhaConta.szTsSincronismo, pszTsSincronismo);
}

void CLinhaConta::setDtExpiracao(char *pszDtExpiracao)
{
    strcpy(tLinhaConta.szDtExpiracao, pszDtExpiracao);
}

void CLinhaConta::setInLinhaMaster(char *pszInLinhaMaster)
{
    strcpy(tLinhaConta.szInLinhaMaster, pszInLinhaMaster);
}

void CLinhaConta::setStruct(TLinhaConta *ptLinhaConta)
{
    memcpy(&tLinhaConta, ptLinhaConta, sizeof(TLinhaConta));
    memset(ptLinhaConta, 0x00, sizeof(TLinhaConta));
}

void CLinhaConta::atualizaLinhaConta(void)
{
    clLinhaContapc.proCAtualizaLinhaConta(tLinhaConta);
}

void CLinhaConta::insereLinhaConta(void)
{
    clLinhaContapc.proCInsereLinhaConta(&tLinhaConta);
}

bool CLinhaConta::buscaLinhaConta(void)
{
    return clLinhaContapc.proCBuscaLinhaConta(&tLinhaConta);
}

bool CLinhaConta::buscaLinhaConta(TLinhaConta *ptLinhaContaAux)
{
    return clLinhaContapc.proCBuscaLinhaConta(ptLinhaContaAux);
}

bool CLinhaConta::buscaLinhaContaCliente(void)
{
    return clLinhaContapc.proCBuscaLinhaContaCliente(&tLinhaConta);
}

void CLinhaConta::apagaLinhaConta(void)
{
    clLinhaContapc.proCApagaLinhaConta(tLinhaConta);
}

void CLinhaConta::falsoInsertLinhaConta(void)
{
    clLinhaContapc.proCInsereLinhaConta(&tLinhaConta);
}

void CLinhaConta::desvinculaContaGrupo(void)
{
    clLinhaContapc.proCDesvinculaContaGrupo(tLinhaConta);
}
