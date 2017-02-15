#include "Portabilidade.h"

CPortabilidade::CPortabilidade(void) {
	memset(&tPortabilidade, 0x00, sizeof(TPortabilidade));
}

bool CPortabilidade::buscaPortabilidade(void) {
	if ( tPortabilidade.szIdLinhaTelefonica[0] )
		return clPortabilidadepc.proCBuscaPortabilidadePorIdLinhaTelef(&tPortabilidade);
	else
    return clPortabilidadepc.proCBuscaPortabilidade(&tPortabilidade);
}


void CPortabilidade::setNrLinha(char *pszNrLinha) {
    strcpy(tPortabilidade.szNrLinha, pszNrLinha);
}

void CPortabilidade::setCdAreaRegistro(char *pszCdAreaRegistro) {
    strcpy(tPortabilidade.szCdAreaRegistro, pszCdAreaRegistro);
}


char *CPortabilidade::getIdPessoa(void) {
    static char szAux[LEN_IDPESSOA + LEN_EOS];

    strcpy(szAux, tPortabilidade.szIdPessoa);
    return szAux;
}

char *CPortabilidade::getIdPessoaDePara(void) {
    static char szAux[LEN_IDPESSOADEPARA + LEN_EOS];

    strcpy(szAux, tPortabilidade.szIdPessoaDePara);
    return szAux;
}

char *CPortabilidade::getSgTipoPessoa(void) {
    static char szAux[LEN_SGTIPOPESSOA + LEN_EOS];

    strcpy(szAux, tPortabilidade.szSgTipoPessoa);
    return szAux;
}

char *CPortabilidade::getNmPessoa(void) {
    static char szAux[LEN_NMPESSOA + LEN_EOS];

    strcpy(szAux, tPortabilidade.szNmPessoa);
    return szAux;
}

char *CPortabilidade::getCdAreaRegistro(void) {
    static char szAux[LEN_CDAREAREGISTROPORT + LEN_EOS];

    strcpy(szAux, tPortabilidade.szCdAreaRegistro);
    return szAux;
}

char *CPortabilidade::getNrLinha(void) {
    static char szAux[LEN_NRLINHAPORT + LEN_EOS];

    strcpy(szAux, tPortabilidade.szNrLinha);
    return szAux;
}

char *CPortabilidade::getIdTipoLinha(void) {
    static char szAux[LEN_IDTIPOLINHA + LEN_EOS];

    strcpy(szAux, tPortabilidade.szIdTipoLinha);
    return szAux;
}

char *CPortabilidade::getDsTipoLinha(void) {
    static char szAux[LEN_DSTIPOLINHA + LEN_EOS];

    strcpy(szAux, tPortabilidade.szDsTipoLinha);
    return szAux;
}

char *CPortabilidade::getIdPessoaEndereco(void) {
    static char szAux[LEN_IDPESSOAENDERECO + LEN_EOS];

    strcpy(szAux, tPortabilidade.szIdPessoaEndereco);
    return szAux;
}

char *CPortabilidade::getDsEndereco(void) {
    static char szAux[LEN_NMTIPOLOGRADOURO + LEN_NMLOGRADOURO + LEN_EOS];

    strcpy(szAux, tPortabilidade.szDsEndereco);
    return szAux;
}

char *CPortabilidade::getNrEndereco(void) {
    static char szAux[LEN_NRENDERECO + LEN_EOS];

    strcpy(szAux, tPortabilidade.szNrEndereco);
    return szAux;
}

char *CPortabilidade::getDsEnderecoComplemento(void) {
    static char szAux[LEN_DSENDERECOCOMPLEMENTO + LEN_EOS];

    strcpy(szAux, tPortabilidade.szDsEnderecoComplemento);
    return szAux;
}

char *CPortabilidade::getNmBairro(void) {
    static char szAux[LEN_NMBAIRRO + LEN_EOS];

    strcpy(szAux, tPortabilidade.szNmBairro);
    return szAux;
}

char *CPortabilidade::getNmMunicipio(void) {
    static char szAux[LEN_NMMUNICIPIO + LEN_EOS];

    strcpy(szAux, tPortabilidade.szNmMunicipio);
    return szAux;
}

char *CPortabilidade::getNrCEP(void) {
    static char szAux[LEN_NRCEP + LEN_EOS];

    strcpy(szAux, tPortabilidade.szNrCEP);
    return szAux;
}

char *CPortabilidade::getIdUF(void) {
    static char szAux[LEN_IDUF + LEN_EOS];

    strcpy(szAux, tPortabilidade.szIdUF);
    return szAux;
}

char *CPortabilidade::getSgUF(void) {
    static char szAux[LEN_SGUF + LEN_EOS];

    strcpy(szAux, tPortabilidade.szSgUF);
    return szAux;
}

char *CPortabilidade::getNmUF(void) {
    static char szAux[LEN_NMUF + LEN_EOS];

    strcpy(szAux, tPortabilidade.szNmUF);
    return szAux;
}

char *CPortabilidade::getIdPais(void) {
    static char szAux[LEN_IDPAIS + LEN_EOS];

    strcpy(szAux, tPortabilidade.szIdPais);
    return szAux;
}

char *CPortabilidade::getSgPais(void) {
    static char szAux[LEN_SGPAIS + LEN_EOS];

    strcpy(szAux, tPortabilidade.szSgPais);
    return szAux;
}

char *CPortabilidade::getDsPais(void) {
    static char szAux[LEN_DSPAIS + LEN_EOS];

    strcpy(szAux, tPortabilidade.szDsPais);
    return szAux;
}

char *CPortabilidade::getDsNacionalidade(void) {
    static char szAux[LEN_DSNACIONALIDADE + LEN_EOS];

    strcpy(szAux, tPortabilidade.szDsNacionalidade);
    return szAux;
}

char *CPortabilidade::getIdDocumento(void) {
    static char szAux[LEN_IDDOCUMENTO + LEN_EOS];

    strcpy(szAux, tPortabilidade.szIdDocumento);
    return szAux;
}

char *CPortabilidade::getDsTipoDocumento(void) {
    static char szAux[LEN_DSTIPODOCUMENTO + LEN_EOS];

    strcpy(szAux, tPortabilidade.szDsTipoDocumento);
    return szAux;
}

char *CPortabilidade::getNrDocumento(void) {
    static char szAux[LEN_NRDOCUMENTO + LEN_EOS];

    strcpy(szAux, tPortabilidade.szNrDocumento);
    return szAux;
}

void CPortabilidade::setIdLinhaTelefonica(char *pszIdLinhaTelefonica) {
    strcpy(tPortabilidade.szIdLinhaTelefonica, pszIdLinhaTelefonica);
}

char *CPortabilidade::getIdLinhaTelefonica(void) {
    static char szAux[LEN_IDLINHATELEFONICA + LEN_EOS];

    strcpy(szAux, tPortabilidade.szIdLinhaTelefonica);
    return szAux;
}

