#include "PessoaFisicaPORT.h"

CPessoaFisica::CPessoaFisica(void) {
    memset(&tPessoaFisica, 0x00, sizeof(TPessoaFisica));
}

void CPessoaFisica::setIdPessoa(char *pszIdPessoa) {
    strcpy(tPessoaFisica.szIdPessoa, pszIdPessoa);
}

void CPessoaFisica::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPessoaFisica.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaFisica::setStruct(TPessoaFisica *ptPessoaFisica) {
    memcpy(&tPessoaFisica, ptPessoaFisica, sizeof(TPessoaFisica));
    memset(ptPessoaFisica, 0x00, sizeof(TPessoaFisica));
}


void CPessoaFisica::setDtNascimento(char *pszDtNascimento) {
    strcpy(tPessoaFisica.szDtNascimento, pszDtNascimento);
}

void CPessoaFisica::setIdEstadoCivil(char *pszIdEstadoCivil) {
    strcpy(tPessoaFisica.szIdEstadoCivil, pszIdEstadoCivil);
}

void CPessoaFisica::setIdSexo(char *pszIdSexo) {
    strcpy(tPessoaFisica.szIdSexo, pszIdSexo);
}

void CPessoaFisica::setIdPais(char *pszIdPais) {
    strcpy(tPessoaFisica.szIdPais, pszIdPais);
}

void CPessoaFisica::setIdTratamento(char *pszIdTratamento) {
    strcpy(tPessoaFisica.szIdTratamento, pszIdTratamento);
}

void CPessoaFisica::inserePessoaFisica(void) {
    clPessoaFisicapc.proCInserePessoaFisica(&tPessoaFisica);
}

void CPessoaFisica::atualizaPessoaFisica(void) {
    clPessoaFisicapc.proCAtualizaPessoaFisica(tPessoaFisica);
}

bool CPessoaFisica::buscaPessoaFisica(void) {
    return clPessoaFisicapc.proCBuscaPessoaFisica(&tPessoaFisica);
}

bool CPessoaFisica::buscaPessoaFisica(TPessoaFisica *ptPessoaFisicaAUX) {
    return clPessoaFisicapc.proCBuscaPessoaFisica(ptPessoaFisicaAUX);
}

char *CPessoaFisica::getIdEstadoCivil(void) {
    static char szAux[LEN_IDESTADOCIVIL + LEN_EOS];

    strcpy(szAux, tPessoaFisica.szIdEstadoCivil);
    return szAux;
}

char *CPessoaFisica::getDtNascimento(void) {
    static char szAux[LEN_DTNASCIMENTO + LEN_EOS];

    strcpy(szAux, tPessoaFisica.szDtNascimento);
    return szAux;
}

char *CPessoaFisica::getIdSexo(void) {
    static char szAux[LEN_IDSEXO + LEN_EOS];

    strcpy(szAux, tPessoaFisica.szIdSexo);
    return szAux;
}

char *CPessoaFisica::getIdPessoa(void) {
    static char szAux[LEN_IDPESSOA + LEN_EOS];

    strcpy(szAux, tPessoaFisica.szIdPessoa);
    return szAux;
}
