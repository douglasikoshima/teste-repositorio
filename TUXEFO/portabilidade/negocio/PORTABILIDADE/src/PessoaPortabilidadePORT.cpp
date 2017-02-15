#include "PessoaPortabilidadePORT.h"

CPessoaPortabilidade::CPessoaPortabilidade(void) {
	memset(&tPessoaPortabilidade, 0x00, sizeof(TPessoaPortabilidade));
}

bool CPessoaPortabilidade::buscaPessoaPortabilidade(void) {
    return clPessoaPortabilidadepc.proCBuscaPessoaPortabilidade(&tPessoaPortabilidade);
}

bool CPessoaPortabilidade::buscaPessoaPortabilidade(TPessoaPortabilidade *ptPessoaPortabilidade) {
    return clPessoaPortabilidadepc.proCBuscaPessoaPortabilidade(ptPessoaPortabilidade);
}

bool CPessoaPortabilidade::inserePessoaPortabilidade(void) {
    return clPessoaPortabilidadepc.proCInserePessoaPortabilidade(&tPessoaPortabilidade);
}

void CPessoaPortabilidade::atualizaPessoaPortabilidade(void) {
    clPessoaPortabilidadepc.proCAtualizaPessoaPortabilidade(&tPessoaPortabilidade);
}


void CPessoaPortabilidade::setInSincronizado(char *pszInSincronizado) {
    strcpy(tPessoaPortabilidade.szInSincronizado, pszInSincronizado);
}

void CPessoaPortabilidade::setIdTipoPessoa(char *pszIdTipoPessoa) {
    strcpy(tPessoaPortabilidade.szIdTipoPessoa, pszIdTipoPessoa);
}

void CPessoaPortabilidade::setIdPessoaDePara(char *pszIdPessoaDePara) {
    strcpy(tPessoaPortabilidade.szIdPessoaDePara, pszIdPessoaDePara);
}

void CPessoaPortabilidade::setIdTipoLinha(char *pszIdTipoLinha) {
    strcpy(tPessoaPortabilidade.szIdTipoLinha, pszIdTipoLinha);
}

void CPessoaPortabilidade::setCdAreaRegistro(char *pszCdAreaRegistro) {
    strcpy(tPessoaPortabilidade.szCdAreaRegistro, pszCdAreaRegistro);
}

void CPessoaPortabilidade::setNrLinha(char *pszNrLinha) {
    strcpy(tPessoaPortabilidade.szNrLinha, pszNrLinha);
}

void CPessoaPortabilidade::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPessoaPortabilidade.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaPortabilidade::setIdAcaoPortabilidade(char *pszIdAcaoPortabilidade) {
    strcpy(tPessoaPortabilidade.szIdAcaoPortabilidade, pszIdAcaoPortabilidade);
}


char *CPessoaPortabilidade::getInSincronizado(void) {
    static char szAux[LEN_INSINCRONIZADO + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidade.szInSincronizado);
    return szAux;
}

char *CPessoaPortabilidade::getIdTipoPessoa(void) {
    static char szAux[LEN_IDTIPOPESSOA + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidade.szIdTipoPessoa);
    return szAux;
}

char *CPessoaPortabilidade::getIdPessoaDePara(void) {
    static char szAux[LEN_IDPESSOADEPARA + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidade.szIdPessoaDePara);
    return szAux;
}

char *CPessoaPortabilidade::getIdTipoLinha(void) {
    static char szAux[LEN_IDTIPOLINHA + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidade.szIdTipoLinha);
    return szAux;
}

char *CPessoaPortabilidade::getCdAreaRegistro(void) {
    static char szAux[LEN_CDAREAREGISTRO + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidade.szCdAreaRegistro);
    return szAux;
}

char *CPessoaPortabilidade::getNrLinha(void) {
    static char szAux[LEN_NRLINHA + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidade.szNrLinha);
    return szAux;
}

char *CPessoaPortabilidade::getIdUsuarioAlteracao(void) {
    static char szAux[LEN_IDUSUARIOALTERACAO + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidade.szIdUsuarioAlteracao);
    return szAux;
}


void CPessoaPortabilidade::clearStruct(void) {
    memset(&tPessoaPortabilidade, 0x00, sizeof(TPessoaPortabilidade));
}
