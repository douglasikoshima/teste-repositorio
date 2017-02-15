#include "PessoaLinhapcPORT.h"
#include "PessoaLinhaPORT.h"

CPessoaLinha::CPessoaLinha(void) {
    memset(&tPessoaLinha, 0x00, sizeof(TPessoaLinha));
    bInsertPessoaLinha=false;
}

CPessoaLinha::~CPessoaLinha(void) {
}

bool CPessoaLinha::buscaPessoaLinha(void) {
    return clPessoaLinhapc.proCBuscaPessoaLinha(&tPessoaLinha);  
}

bool CPessoaLinha::buscaPessoaLinha(TPessoaLinha *ptPessoaLinhaAux) {
    return clPessoaLinhapc.proCBuscaPessoaLinha(ptPessoaLinhaAux);
}

void  CPessoaLinha::atualizaPessoaLinha(void) {
    clPessoaLinhapc.proCAtualizaPessoaLinha(tPessoaLinha);
    this->bInsertPessoaLinha=true;
}

void  CPessoaLinha::inserePessoaLinha(void) {
    clPessoaLinhapc.proCInserePessoaLinha(&tPessoaLinha);
    this->bInsertPessoaLinha=true;
}

void CPessoaLinha::setIdLinhaTelefonica(char *pszIdLinhaTelefonica) {
    strcpy(tPessoaLinha.szIdLinhaTelefonica, pszIdLinhaTelefonica);
}

void CPessoaLinha::setIdPessoaDePara(char *pszIdPessoaDePara) {
    strcpy(tPessoaLinha.szIdPessoaDePara, pszIdPessoaDePara);
}

void CPessoaLinha::setIdTipoRelacionamento(char *pszIdTipoRelacionamento) {
    strcpy(tPessoaLinha.szIdTipoRelacionamento, pszIdTipoRelacionamento);
}

void CPessoaLinha::setIdPessoaLinha(char *pszIdPessoaLinha) {
    strcpy(tPessoaLinha.szIdPessoaLinha, pszIdPessoaLinha);
}

void CPessoaLinha::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPessoaLinha.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

char *CPessoaLinha::getIdPessoaDePara(void) {
    static char szAux[LEN_IDPESSOADEPARA + LEN_EOS];

    strcpy(szAux, tPessoaLinha.szIdPessoaDePara);
    return szAux;
}

char *CPessoaLinha::getIdTipoRelacionamento(void) {
    static char szAux[LEN_IDTIPORELACIONAMENTO + LEN_EOS];

    strcpy(szAux, tPessoaLinha.szIdTipoRelacionamento);
    return szAux;
}

char *CPessoaLinha::getIdPessoaLinha(void) {
    static char szAux[LEN_IDPESSOALINHA + LEN_EOS];

    strcpy(szAux, tPessoaLinha.szIdPessoaLinha);
    return szAux;
}

char *CPessoaLinha::getIdLinhaTelefonica(void) {
    static char szAux[LEN_IDLINHATELEFONICA + LEN_EOS];

    strcpy(szAux, tPessoaLinha.szIdLinhaTelefonica);
    return szAux;
}

void CPessoaLinha::setStruct(TPessoaLinha *ptPessoaLinha){
    memcpy(&tPessoaLinha, ptPessoaLinha, sizeof(TPessoaLinha));
    memset(ptPessoaLinha, 0x00, sizeof(TPessoaLinha));
}

bool CPessoaLinha::inseriuPessoaLinha(void) {
    return bInsertPessoaLinha;
}
