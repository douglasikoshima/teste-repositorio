#include "PessoaJuridicaPORT.h"

CPessoaJuridica::CPessoaJuridica(void) {
    memset(&tPessoaJuridica, 0x00, sizeof(TPessoaJuridica));
}

void CPessoaJuridica::setIdPessoa(char *pszIdPessoa ) {
    strcpy(tPessoaJuridica.szIdPessoa, pszIdPessoa);
}

void CPessoaJuridica::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPessoaJuridica.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaJuridica::setIdCFOP(char *pszIdCFOP) {
    strcpy(tPessoaJuridica.szIdCFOP, pszIdCFOP);
}

void CPessoaJuridica::inserePessoaJuridica(void) {
    clPessoaJuridicapc.proCInserePessoaJuridica(&tPessoaJuridica);
}

void CPessoaJuridica::atualizaPessoaJuridica(void) {
    clPessoaJuridicapc.proCAtualizaPessoaJuridica(tPessoaJuridica);
}

bool CPessoaJuridica::buscaPessoaJuridica(void) {
    return clPessoaJuridicapc.proCBuscaPessoaJuridica(&tPessoaJuridica);
}

bool CPessoaJuridica::buscaPessoaJuridica(TPessoaJuridica *ptPessoaJuridicaAUX) {
    return clPessoaJuridicapc.proCBuscaPessoaJuridica(ptPessoaJuridicaAUX);
}

char *CPessoaJuridica::getIdPessoa(void) {
    static char szAux[LEN_IDPESSOA + LEN_EOS];

    strcpy(szAux, tPessoaJuridica.szIdPessoa);
    return szAux;
}

void CPessoaJuridica::setStruct(TPessoaJuridica *ptPessoaJuridica) {
    memcpy(&tPessoaJuridica, ptPessoaJuridica, sizeof(TPessoaJuridica));
    memset(ptPessoaJuridica, 0x00, sizeof(TPessoaJuridica));
}
