#include "PessoaPorHistPORT.h"

CPessoaPortabilidadeHist::CPessoaPortabilidadeHist(void) {
	memset(&tPessoaPortabilidadeHist, 0x00, sizeof(TPessoaPortabilidadeHist));
}

void CPessoaPortabilidadeHist::inserePessoaPortabilidadeHist(void) {
    clPessoaPortabilidadeHistpc.proCInserePessoaPortabilidadeHist(&tPessoaPortabilidadeHist);
}


void CPessoaPortabilidadeHist::setIdPessoaPortabilidadeHist(char *pszIdPessoaPortabilidadeHist) {
    strcpy(tPessoaPortabilidadeHist.szIdPessoaPortabilidadeHist, pszIdPessoaPortabilidadeHist);
}

void CPessoaPortabilidadeHist::setIdTipoLinha(char *pszIdTipoLinha) {
    strcpy(tPessoaPortabilidadeHist.szIdTipoLinha, pszIdTipoLinha);
}

void CPessoaPortabilidadeHist::setCdAreaRegistro(char *pszCdAreaRegistro) {
    strcpy(tPessoaPortabilidadeHist.szCdAreaRegistro, pszCdAreaRegistro);
}

void CPessoaPortabilidadeHist::setNrLinha(char *pszNrLinha) {
    strcpy(tPessoaPortabilidadeHist.szNrLinha, pszNrLinha);
}

void CPessoaPortabilidadeHist::setIdTipoPessoa(char *pszIdTipoPessoa) {
    strcpy(tPessoaPortabilidadeHist.szIdTipoPessoa, pszIdTipoPessoa);
}

void CPessoaPortabilidadeHist::setNmPessoa(char *pszNmPessoa) {
    strcpy(tPessoaPortabilidadeHist.szNmPessoa, pszNmPessoa);
}

void CPessoaPortabilidadeHist::setIdTipoDocumento(char *pszIdTipoDocumento) {
    strcpy(tPessoaPortabilidadeHist.szIdTipoDocumento, pszIdTipoDocumento);
}

void CPessoaPortabilidadeHist::setNrDocumento(char *pszNrDocumento) {
    strcpy(tPessoaPortabilidadeHist.szNrDocumento, pszNrDocumento);
}

void CPessoaPortabilidadeHist::setIdTipoEndereco(char *pszIdTipoEndereco) {
    strcpy(tPessoaPortabilidadeHist.szIdTipoEndereco, pszIdTipoEndereco);
}

void CPessoaPortabilidadeHist::setNmTipoLogradouro(char *pszNmTipoLogradouro) {
    strcpy(tPessoaPortabilidadeHist.szNmTipoLogradouro, pszNmTipoLogradouro);
}

void CPessoaPortabilidadeHist::setNmLogradouro(char *pszNmLogradouro) {
    strcpy(tPessoaPortabilidadeHist.szNmLogradouro, pszNmLogradouro);
}

void CPessoaPortabilidadeHist::setNrEndereco(char *pszNrEndereco) {
    strcpy(tPessoaPortabilidadeHist.szNrEndereco, pszNrEndereco);
}

void CPessoaPortabilidadeHist::setNmMunicipio(char *pszNmMunicipio) {
    strcpy(tPessoaPortabilidadeHist.szNmMunicipio, pszNmMunicipio);
}

void CPessoaPortabilidadeHist::setNmBairro(char *pszNmBairro) {
    strcpy(tPessoaPortabilidadeHist.szNmBairro, pszNmBairro);
}

void CPessoaPortabilidadeHist::setNrCep(char *pszNrCep) {
    strcpy(tPessoaPortabilidadeHist.szNrCep, pszNrCep);
}

void CPessoaPortabilidadeHist::setDsAcaoPortabilidade(char *pszDsAcaoPortabilidade) {
    strcpy(tPessoaPortabilidadeHist.szDsAcaoPortabilidade, pszDsAcaoPortabilidade);
}

void CPessoaPortabilidadeHist::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPessoaPortabilidadeHist.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaPortabilidadeHist::setSgTipoPortabilidade(char *pszSgTipoPortabilidade) {
    strcpy(tPessoaPortabilidadeHist.szSgTipoPortabilidade, pszSgTipoPortabilidade);
}


char *CPessoaPortabilidadeHist::getIdPessoaPortabilidadeHist(void) {
    static char szAux[LEN_IDPESSOAPORTABILIDADEHISTPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szIdPessoaPortabilidadeHist);
    return szAux;
}

char *CPessoaPortabilidadeHist::getIdTipoLinha(void) {
    static char szAux[LEN_IDTIPOLINHAPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szIdTipoLinha);
    return szAux;
}

char *CPessoaPortabilidadeHist::getCdAreaRegistro(void) {
    static char szAux[LEN_CDAREAREGISTROPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szCdAreaRegistro);
    return szAux;
}

char *CPessoaPortabilidadeHist::getNrLinha(void) {
    static char szAux[LEN_NRLINHAPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szNrLinha);
    return szAux;
}

char *CPessoaPortabilidadeHist::getIdTipoPessoa(void) {
    static char szAux[LEN_IDTIPOPESSOAPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szIdTipoPessoa);
    return szAux;
}

char *CPessoaPortabilidadeHist::getNmPessoa(void) {
    static char szAux[LEN_NMPESSOAPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szNmPessoa);
    return szAux;
}

char *CPessoaPortabilidadeHist::getIdTipoDocumento(void) {
    static char szAux[LEN_IDTIPODOCUMENTOPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szIdTipoDocumento);
    return szAux;
}

char *CPessoaPortabilidadeHist::getNrDocumento(void) {
    static char szAux[LEN_NRDOCUMENTOPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szNrDocumento);
    return szAux;
}

char *CPessoaPortabilidadeHist::getIdTipoEndereco(void) {
    static char szAux[LEN_IDTIPOENDERECOPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szIdTipoEndereco);
    return szAux;
}

char *CPessoaPortabilidadeHist::getNmTipoLogradouro(void) {
    static char szAux[LEN_NMTIPOLOGRADOUROPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szNmTipoLogradouro);
    return szAux;
}

char *CPessoaPortabilidadeHist::getNmLogradouro(void) {
    static char szAux[LEN_NMLOGRADOUROPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szNmLogradouro);
    return szAux;
}

char *CPessoaPortabilidadeHist::getNrEndereco(void) {
    static char szAux[LEN_NRENDERECOPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szNrEndereco);
    return szAux;
}

char *CPessoaPortabilidadeHist::getNmMunicipio(void) {
    static char szAux[LEN_NMMUNICIPIOPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szNmMunicipio);
    return szAux;
}

char *CPessoaPortabilidadeHist::getNmBairro(void) {
    static char szAux[LEN_NMBAIRROPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szNmBairro);
    return szAux;
}

char *CPessoaPortabilidadeHist::getNrCep(void) {
    static char szAux[LEN_NRCEPPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szNrCep);
    return szAux;
}

char *CPessoaPortabilidadeHist::getDsAcaoPortabilidade(void) {
    static char szAux[LEN_DSACAOPORTABILIDADEPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szDsAcaoPortabilidade);
    return szAux;
}

char *CPessoaPortabilidadeHist::getIdUsuarioAlteracao(void) {
    static char szAux[LEN_IDUSUARIOALTERACAOPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szIdUsuarioAlteracao);
    return szAux;
}

char *CPessoaPortabilidadeHist::getSgTipoPortabilidade(void) {
    static char szAux[LEN_SGTIPOPORTABILIDADEPORT + LEN_EOS];

    strcpy(szAux, tPessoaPortabilidadeHist.szSgTipoPortabilidade);
    return szAux;
}


void CPessoaPortabilidadeHist::clearStruct(void) {
    memset(&tPessoaPortabilidadeHist, 0x00, sizeof(TPessoaPortabilidadeHist));
}
