#include "PessoaDeParaPORT.h"
#include "PPExceptionPORT.h"

CPessoaDePara::CPessoaDePara(void) {
    memset(&tPessoaDePara, 0x00, sizeof(TPessoaDePara));
}

void CPessoaDePara::setIdPessoa(char *pszIdPessoa) {
    strcpy(tPessoaDePara.szIdPessoa, pszIdPessoa);
}

void CPessoaDePara::setIdPessoaDePara(char *pszIdPessoaDePara) {
    strcpy(tPessoaDePara.szIdPessoaDePara, pszIdPessoaDePara);
}

void CPessoaDePara::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPessoaDePara.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaDePara::setIdPessoaOrigem(char *pszIdPessoaOrigem) {
    strcpy(tPessoaDePara.szIdPessoaOrigem, pszIdPessoaOrigem);
}


char *CPessoaDePara::getIdPessoa(void) {
    static char szAUX[LEN_IDPESSOA + LEN_EOS];

    strcpy(szAUX, tPessoaDePara.szIdPessoa);
    return szAUX;
}

char *CPessoaDePara::getIdPessoaDePara(void) {
    static char szAUX[LEN_IDPESSOADEPARA + LEN_EOS];

    strcpy(szAUX, tPessoaDePara.szIdPessoaDePara);
    return szAUX;
}


bool CPessoaDePara::buscaPessoa(TPessoaDePara *ptPessoaDeParaAUX) {
    return clPessoaDeParapc.proCBuscaPessoaDePara(ptPessoaDeParaAUX);
}

bool CPessoaDePara::buscaPessoaDePara(void) {
    return clPessoaDeParapc.proCBuscaPessoaDePara(&tPessoaDePara);
}

void CPessoaDePara::atualizaPessoaDePara(void) {
    clPessoaDeParapc.proCAtualizaPessoaDePara(&tPessoaDePara);
}

void CPessoaDePara::inserePessoaDePara(void) {
    clPessoaDeParapc.proCInserePessoaDePara(&tPessoaDePara);
}

void CPessoaDePara::setStruct(TPessoaDePara *ptPessoaDePara) {
    memcpy(&tPessoaDePara, ptPessoaDePara, sizeof(TPessoaDePara));
    memset(ptPessoaDePara, 0x00, sizeof(TPessoaDePara));
}
