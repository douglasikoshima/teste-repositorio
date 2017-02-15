#include "tuxfw.h"
#include "PessoaValorPossivelpcPORT.h"
#include "PessoaValorPossivelPORT.h"

CPessoaValorPossivel::CPessoaValorPossivel(void) {
    memset(&tPessoaValorPossivel, 0x00, sizeof(TPessoaValorPossivel));
}

void CPessoaValorPossivel::apagaEscolaridade(void) {
    clPessoaValorPossivelpc.proCApagaEscolaridade(&tPessoaValorPossivel);  
}

void CPessoaValorPossivel::apagaOcupacao(void) {
    clPessoaValorPossivelpc.proCApagaOcupacao(&tPessoaValorPossivel);  
}

void CPessoaValorPossivel::inserePessoaValorPossivel(void) {
    clPessoaValorPossivelpc.proCInserePessoaValorPossivel(&tPessoaValorPossivel);  
}


void CPessoaValorPossivel::setIdPessoa(char *pszIdPessoa) {
    strcpy(tPessoaValorPossivel.szIdPessoa, pszIdPessoa);
}

void CPessoaValorPossivel::setIdValorPossivel(char *pszIdValorPossivel) {
    strcpy(tPessoaValorPossivel.szIdValorPossivel, pszIdValorPossivel);
}

void CPessoaValorPossivel::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPessoaValorPossivel.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaValorPossivel::setStruct(TPessoaValorPossivel *ptPessoaValorPossivelTmp) {
    memcpy(&tPessoaValorPossivel, ptPessoaValorPossivelTmp, sizeof(TPessoaValorPossivel));
    memset(ptPessoaValorPossivelTmp, 0x00, sizeof(TPessoaValorPossivel));
}

void CPessoaValorPossivel::limpaPessoaValorPossivel(void){
    memset(&tPessoaValorPossivel, 0x00, sizeof(TPessoaValorPossivel));
}

char *CPessoaValorPossivel::getIdPessoaValorPossivel(void) {
    static char szAux[LEN_IDPESSOAVALORPOSSIVEL + LEN_EOS];

    strcpy(szAux, tPessoaValorPossivel.szIdPessoaValorPossivel);
    return szAux;
}