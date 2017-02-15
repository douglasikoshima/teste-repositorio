///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase PessoaValorPossivel
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @author  Eder Martins
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/
///////////////////////////////////////////////////////////////////////////////

#include "tuxfw.h"
#include "PessoaValorPossivelpc.h"
#include "PessoaValorPossivel.h"

CPessoaValorPossivel::CPessoaValorPossivel(void)
{
    memset(&tPessoaValorPossivel, 0x00, sizeof(TPessoaValorPossivel));
}

void CPessoaValorPossivel::apagaEscolaridade(void)
{
    clPessoaValorPossivelpc.proCApagaEscolaridade(&tPessoaValorPossivel);  
}

void CPessoaValorPossivel::apagaOcupacao(void)
{
    clPessoaValorPossivelpc.proCApagaOcupacao(&tPessoaValorPossivel);  
}

void CPessoaValorPossivel::inserePessoaValorPossivel(void)
{
    clPessoaValorPossivelpc.proCInserePessoaValorPossivel(&tPessoaValorPossivel);  
}


void CPessoaValorPossivel::setIdPessoa(char *pszIdPessoa)
{
    strcpy(tPessoaValorPossivel.szIdPessoa, pszIdPessoa);
}

void CPessoaValorPossivel::setIdValorPossivel(char *pszIdValorPossivel)
{
    strcpy(tPessoaValorPossivel.szIdValorPossivel, pszIdValorPossivel);
}

void CPessoaValorPossivel::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao)
{
    strcpy(tPessoaValorPossivel.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaValorPossivel::setStruct(TPessoaValorPossivel *ptPessoaValorPossivelTmp)
{
    memcpy(&tPessoaValorPossivel, ptPessoaValorPossivelTmp, sizeof(TPessoaValorPossivel));
    memset(ptPessoaValorPossivelTmp, 0x00, sizeof(TPessoaValorPossivel));
}

void CPessoaValorPossivel::limpaPessoaValorPossivel(void)
{
    memset(&tPessoaValorPossivel, 0x00, sizeof(TPessoaValorPossivel));
}
