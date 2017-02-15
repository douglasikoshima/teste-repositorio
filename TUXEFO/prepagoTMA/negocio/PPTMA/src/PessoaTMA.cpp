#include "PessoaTMA.h"

CPessoa::CPessoa(void) {
    memset(&tPessoa, 0x00, sizeof(TPessoa));
}

void CPessoa::setIdPessoa(char *pszIdPessoa) {
    strcpy(tPessoa.szIdPessoa, pszIdPessoa);
}

void CPessoa::setIdSistemaOrigem(char *pszIdSistemaOrigem) {
     strcpy(tPessoa.szIdSistemaOrigem, pszIdSistemaOrigem);
}

void CPessoa::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPessoa.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoa::setIdPessoaSistemaOrigem(char *pszIdPessoaSistemaOrigem) {
    strcpy(tPessoa.szIdPessoaSistemaOrigem, pszIdPessoaSistemaOrigem);
}

void CPessoa::setTsSincronismo(char *pszTsSincronismo) {
    strcpy(tPessoa.szTsSincronismo, pszTsSincronismo);
}

void CPessoa::setSqSincronismo(char *pszSqSincronismo) {
    strcpy(tPessoa.szSqSincronismo, pszSqSincronismo);
}

void CPessoa::setIdTipoPessoa(char *pszIdTipoPessoa) {
    strcpy(tPessoa.szIdTipoPessoa, pszIdTipoPessoa);
}

void CPessoa::setDsCargoContato(char *pszDsCargoContato) {
    strcpy(tPessoa.szDsCargoContato, pszDsCargoContato);
}

void CPessoa::setDsDeptoContato(char *pszDsDeptoContato) {
    strcpy(tPessoa.szDsDeptoContato, pszDsDeptoContato);
}

void CPessoa::setInFalecimentoInformado(char *pszInFalecimentoInformado) {
    strcpy(tPessoa.szInFalecimentoInformado, pszInFalecimentoInformado);
}

void CPessoa::setIdUf(char *pszIdUf) {
    strcpy(tPessoa.szIdUf, pszIdUf);
}

void CPessoa::setIdTipoCarteira(char *pszIdTipoCarteira) {
    strcpy(tPessoa.szIdTipoCarteira, pszIdTipoCarteira);
}

void CPessoa::setIdProbInadimplencia(char *pszIdProbInadimplencia) {
    strcpy(tPessoa.szIdProbInadimplencia, pszIdProbInadimplencia);
}

void CPessoa::setIdChurnProbabilidade(char *pszIdChurnProbabilidade) {
    strcpy(tPessoa.szIdChurnProbabilidade, pszIdChurnProbabilidade);
}


char *CPessoa::getIdPessoa(void) {
    static char szAUX[LEN_IDPESSOA + LEN_EOS];

    strcpy(szAUX, tPessoa.szIdPessoa);
    return szAUX;
}

char *CPessoa::getIdTipoPessoa(void) {
    static char szAUX[LEN_IDTIPOPESSOA + LEN_EOS];

    strcpy(szAUX, tPessoa.szIdTipoPessoa);
    return szAUX;
}

char *CPessoa::getIdSistemaOrigem(void) {
    static char szAUX[LEN_IDSISTEMAORIGEM + LEN_EOS];

    strcpy(szAUX, tPessoa.szIdSistemaOrigem);
    return szAUX;
}

char *CPessoa::getIdTipoCarteira(void) {
    static char szAUX[LEN_IDTIPOCARTEIRA + LEN_EOS];

    strcpy(szAUX, tPessoa.szIdTipoCarteira);
    return szAUX;
}

char *CPessoa::getSqSincronismo(void) {
    static char szAUX[LEN_SQSINCRONISMO + LEN_EOS];

    strcpy(szAUX, tPessoa.szSqSincronismo);
    return szAUX;
}

char *CPessoa::getTsSincronismo(void) {
    static char szAUX[LEN_TSSINCRONISMO + LEN_EOS];

    strcpy(szAUX, tPessoa.szTsSincronismo);
    return szAUX;
}

char *CPessoa::getIdPessoaSistemaOrigem(void) {
    static char szAUX[LEN_IDPESSOASISTEMAORIGEM + LEN_EOS];

    strcpy(szAUX, tPessoa.szIdPessoaSistemaOrigem);
    return szAUX;
}

char *CPessoa::getNmPessoa(void) {
    static char szAUX[LEN_NMPESSOA + LEN_EOS];

    strcpy(szAUX, tPessoa.szNmPessoa);
    return szAUX;
}


///////////////////////////////////////////////////////////////////////////////
// Métodos de negócio.
///////////////////////////////////////////////////////////////////////////////
bool CPessoa::buscaPessoa(TPessoa *ptPessoaAUX) {
    return (clPessoapc.proCBuscaPessoa(ptPessoaAUX));
}

bool CPessoa::buscaPessoa(void) {
    return (clPessoapc.proCBuscaPessoa(&tPessoa));
}

void CPessoa::atualizaPessoa(void) {
    clPessoapc.proCAtualizaPessoa(&tPessoa);
}

void CPessoa::inserePessoa(void) {
    clPessoapc.proCInserePessoa(&tPessoa);
}

void CPessoa::setStruct(TPessoa *ptPessoa) {
    memcpy(&tPessoa, ptPessoa, sizeof(TPessoa));
    memset(ptPessoa, 0x00, sizeof(TPessoa));
}
