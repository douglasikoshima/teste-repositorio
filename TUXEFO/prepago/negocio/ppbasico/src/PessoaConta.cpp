///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaConta
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <memory.h>
#include <string.h>
#include <stdio.h>
#include "PessoaConta.h"

CPessoaConta::CPessoaConta(void)
{
    memset(&tPessoaConta, 0x00, sizeof(TPessoaConta));
}

void CPessoaConta::atualizaPessoaConta(void)
{
    clPessoaContapc.proCAtualizaPessoaConta(tPessoaConta);
}

void CPessoaConta::inserePessoaConta(void)
{
    clPessoaContapc.proCInserePessoaConta(&tPessoaConta);
}

bool CPessoaConta::buscaPessoaConta(void)
{
    return(clPessoaContapc.proCBuscaPessoaConta(&tPessoaConta));
}

bool CPessoaConta::buscaPessoaConta(TPessoaConta *ptPessoaContaAux)
{
    return(clPessoaContapc.proCBuscaPessoaConta(ptPessoaContaAux));
}

void CPessoaConta::apagaPessoaConta(void)
{
    clPessoaContapc.proCApagaPessoaConta(tPessoaConta);
}


void CPessoaConta::setIdConta(char *pszIdConta)
{
    strcpy(tPessoaConta.szIdConta, pszIdConta);
}

void CPessoaConta::setIdTipoRelacionamento(char *pszIdTipoRelacionamento)
{
    strcpy(tPessoaConta.szIdTipoRelacionamento, pszIdTipoRelacionamento);
}

void CPessoaConta::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tPessoaConta.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaConta::setIdPessoaDePara(char *pszIdPessoaDePara)
{
    strcpy(tPessoaConta.szIdPessoaDePara, pszIdPessoaDePara);
}

void CPessoaConta::setDtPessoaConta(char *pszDtPessoaConta)
{
    strcpy(tPessoaConta.szDtPessoaConta, pszDtPessoaConta);
}

void CPessoaConta::setIdPessoaConta(char *pszIdPessoaConta)
{
    strcpy(tPessoaConta.szIdPessoaConta, pszIdPessoaConta);
}

char *CPessoaConta::getIdPessoaDePara(void)
{
    static char szAux[LEN_IDPESSOADEPARA + LEN_EOS];

    strcpy(szAux, tPessoaConta.szIdPessoaDePara);
    return szAux;
}
