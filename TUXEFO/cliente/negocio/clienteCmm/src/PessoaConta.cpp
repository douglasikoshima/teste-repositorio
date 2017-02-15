///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaConta
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:17 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include <memory.h>
#include <string.h>
#include <stdio.h>
#include "../include/PessoaConta.h"

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


void CPessoaConta::setIdPessoaDePara(char *pszIdPessoaDePara)
{
    strcpy(tPessoaConta.szIdPessoaDePara, pszIdPessoaDePara);
}
