#include "ContaEnderecoTMA.h"

CContaEndereco::CContaEndereco(void) {
    memset(&tContaEndereco, 0x00, sizeof(TContaEndereco));
}

bool CContaEndereco::buscaContaEndereco(void) {
    return(clContaEnderecopc.proCBuscaContaEndereco(&tContaEndereco));
}

bool CContaEndereco::buscaContaEndereco(TContaEndereco *ptContaEndereco) {
    return(clContaEnderecopc.proCBuscaContaEndereco(ptContaEndereco));
}

void CContaEndereco::insereContaEndereco(void) {
	clContaEnderecopc.proCInsereContaEndereco(&tContaEndereco);
}

void CContaEndereco::atualizaContaEndereco(void) {
	clContaEnderecopc.proCAtualizaContaEndereco(tContaEndereco);
}


void CContaEndereco::setIdConta(char *pszIdConta) {
    strcpy(tContaEndereco.szIdConta, pszIdConta);
}

void CContaEndereco::setIdPessoaEndereco(char *pszIdPessoaEndereco) {
    strcpy(tContaEndereco.szIdPessoaEndereco, pszIdPessoaEndereco);
}

void CContaEndereco::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tContaEndereco.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CContaEndereco::setIdContaEndereco(char *pszIdContaEndereco) {
    strcpy(tContaEndereco.szIdContaEndereco, pszIdContaEndereco);
}

void CContaEndereco::setIdTipoEnderecoCobranca(char *pszIdTipoEnderecoCobranca) {
    strcpy(tContaEndereco.szIdTipoEnderecoCobranca, pszIdTipoEnderecoCobranca);
}

char *CContaEndereco::getIdPessoaEndereco(void) {
    static char szAux[LEN_IDPESSOAENDERECO + LEN_EOS];

    strcpy(szAux, tContaEndereco.szIdPessoaEndereco);
    return szAux;
}

char *CContaEndereco::getIdContaEndereco(void) {
    static char szAux[LEN_IDCONTAENDERECO + LEN_EOS];

    strcpy(szAux, tContaEndereco.szIdContaEndereco);
    return szAux;
}
