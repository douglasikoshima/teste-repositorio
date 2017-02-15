#include "GestorConta.h"

CGestorConta::CGestorConta(void) {
	memset(&tGestorConta, 0x00, sizeof(TGestorConta));
}

bool CGestorConta::buscaGestorConta(void) {
    return clGestorContapc.proCBuscaGestorConta(&tGestorConta);
}

void CGestorConta::insereGestorConta(void) {
    return clGestorContapc.proCInsereGestorConta(&tGestorConta);
}

void CGestorConta::atualizaGestorConta(void) {
    return clGestorContapc.proCAtualizaGestorConta(&tGestorConta);
}

void CGestorConta::apagaGestorConta(void) {
    return clGestorContapc.proCApagaGestorConta(&tGestorConta);
}


void CGestorConta::setIdNrCPF(char *pszIdNrCPF) {
    strcpy(tGestorConta.szIdNrCPF, pszIdNrCPF);
}

void CGestorConta::setDsTipoGestor(char *pszDsTipoGestor) {
    strcpy(tGestorConta.szDsTipoGestor, pszDsTipoGestor);
}

void CGestorConta::setNmNome(char *pszNmNome) {
    strcpy(tGestorConta.szNmNome, pszNmNome);
}

void CGestorConta::setNmNomeMeio(char *pszNmNomeMeio) {
    strcpy(tGestorConta.szNmNomeMeio, pszNmNomeMeio);
}

void CGestorConta::setNmSobreNome(char *pszNmSobreNome) {
    strcpy(tGestorConta.szNmSobreNome, pszNmSobreNome);
}
////
void CGestorConta::setNmCargo(char *pszNmCargo) {
    strcpy(tGestorConta.szNmCargo, pszNmCargo);
}

void CGestorConta::setNmLogradouro(char *pszNmLogradouro) {
    strcpy(tGestorConta.szNmLogradouro, pszNmLogradouro);
}

void CGestorConta::setNrEndereco(char *pszNrEndereco) {
    strcpy(tGestorConta.szNrEndereco, pszNrEndereco);
}

void CGestorConta::setNmEnderecoComplemento(char *pszNmEnderecoComplemento) {
    strcpy(tGestorConta.szNmEnderecoComplemento, pszNmEnderecoComplemento);
}

void CGestorConta::setNmBairro(char *pszNmBairro) {
    strcpy(tGestorConta.szNmBairro, pszNmBairro);
}

void CGestorConta::setNmCidade(char *pszNmCidade) {
    strcpy(tGestorConta.szNmCidade, pszNmCidade);
}

void CGestorConta::setNrCEP(char *pszNrCEP) {
    strcpy(tGestorConta.szNrCEP, pszNrCEP);
}

void CGestorConta::setIdUF(char *pszIdUF) {
    strcpy(tGestorConta.szIdUF, pszIdUF);
}

void CGestorConta::setDsEmail(char *pszDsEmail) {
    strcpy(tGestorConta.szDsEmail, pszDsEmail);
}



////
void CGestorConta::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tGestorConta.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}


char *CGestorConta::getIdNrCPF(void) {
    static char szAux[LEN_IDNRCPF + LEN_EOS];

    strcpy(szAux, tGestorConta.szIdNrCPF);
    return szAux;
}

char *CGestorConta::getDsTipoGestor(void) {
    static char szAux[LEN_DSTIPOGESTOR + LEN_EOS];

    strcpy(szAux, tGestorConta.szDsTipoGestor);
    return szAux;
}

char *CGestorConta::getNmNome(void) {
    static char szAux[LEN_NMNOMEGC + LEN_EOS];

    strcpy(szAux, tGestorConta.szNmNome);
    return szAux;
}

char *CGestorConta::getNmNomeMeio(void) {
    static char szAux[LEN_NMNOMEMEIOGC + LEN_EOS];

    strcpy(szAux, tGestorConta.szNmNomeMeio);
    return szAux;
}

char *CGestorConta::getNmSobreNome(void) {
    static char szAux[LEN_NMSOBRENOMEGC + LEN_EOS];

    strcpy(szAux, tGestorConta.szNmSobreNome);
    return szAux;
}

char *CGestorConta::getNmCargo(void) {
    static char szAux[LEN_NMCARGO + LEN_EOS];

    strcpy(szAux, tGestorConta.szNmCargo);
    return szAux;
}

char *CGestorConta::getDtUltimaAlteracao(void) {
    static char szAux[LEN_DTULTIMAALTERACAO + LEN_EOS];

    strcpy(szAux, tGestorConta.szDtUltimaAlteracao);
    return szAux;
}

char *CGestorConta::getDsEmail(void) {
    static char szAux[LEN_DSEMAIL + LEN_EOS];

    strcpy(szAux, tGestorConta.szDsEmail);
    return szAux;
}
