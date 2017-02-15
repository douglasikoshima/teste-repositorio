#include "PessoaDocumentoTMA.h"
#include "PessoaDocumentopcTMA.h"

CPessoaDocumento::CPessoaDocumento(void) {
    memset(&tPessoaDocumento, 0x00, sizeof(TPessoaDocumento));
    this->bApagouPessoaDocumento=false;
}

bool CPessoaDocumento::buscaPessoaDocumento(TPessoaDocumento *ptPessoaDocumentoAux) {
	return(clPessoaDocumentopc.proCBuscaPessoaDocumento(ptPessoaDocumentoAux));
}

bool CPessoaDocumento::buscaPessoaDocumento(void) {
	return(clPessoaDocumentopc.proCBuscaPessoaDocumento(&tPessoaDocumento));
}

bool CPessoaDocumento::buscaPessoaPessoaDocumento(const char *nrDocumento
                                                 ,const char *idTipoDocumento
                                                 ,const char *nmNome
                                                 ,const char *nmNomeMeio
                                                 ,const char *nmSobreNome
                                                 ,char *idPessoa) {
    return(clPessoaDocumentopc.proCBuscaPessoaPessoaDocumento(nrDocumento,idTipoDocumento,nmNome,nmNomeMeio,nmSobreNome,idPessoa));
}

void CPessoaDocumento::inserePessoaDocumento(void) {
    clPessoaDocumentopc.proCInserePessoaDocumento(&tPessoaDocumento);
}

bool CPessoaDocumento::existePessoaDocumento(const char *idDocumento,const char *idPessoa) {
    return clPessoaDocumentopc.proCExistePessoaDocumento(idDocumento,idPessoa);
}

void CPessoaDocumento::apagaPessoaDocumentoEspecifico(const char *idDocumento,const char *idPessoa) {
    clPessoaDocumentopc.proCApagaPessoaDocumentoEspecifico(idDocumento,idPessoa);
}

void CPessoaDocumento::atualizaPessoaDocumento(void) {
    clPessoaDocumentopc.proCAtualizaPessoaDocumento(tPessoaDocumento);
}

void CPessoaDocumento::apagaPessoaDocumento(void) {
    clPessoaDocumentopc.proCApagaPessoaDocumento(&tPessoaDocumento);
    this->bApagouPessoaDocumento=true;
}

void CPessoaDocumento::setIdDocumento(char *pszIdDocumento) {
    strcpy(tPessoaDocumento.szIdDocumento, pszIdDocumento);
}

void CPessoaDocumento::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPessoaDocumento.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaDocumento::setIdPessoa(char *pszIdPessoa) {
    strcpy(tPessoaDocumento.szIdPessoa, pszIdPessoa);
}

void CPessoaDocumento::setSqSincronismo(char *pszSqSincronismo) {
    strcpy(tPessoaDocumento.szSqSincronismo, pszSqSincronismo);
}

void CPessoaDocumento::setTsSincronismo(char *pszTsSincronismo) {
    strcpy(tPessoaDocumento.szTsSincronismo, pszTsSincronismo);
}

void CPessoaDocumento::setIdDocumentoSistemaOrigem(char *pszIdDocumentoSistemaOrigem) {
    strcpy(tPessoaDocumento.szIdDocumentoSistemaOrigem, pszIdDocumentoSistemaOrigem);
}

void CPessoaDocumento::setIdSistemaOrigem(char *pszIdSistemaOrigem) {
    strcpy(tPessoaDocumento.szIdSistemaOrigem, pszIdSistemaOrigem);
}

void CPessoaDocumento::setIdPessoaDocumento(char *pszIdPessoaDocumento) {
    strcpy(tPessoaDocumento.szIdPessoaDocumento, pszIdPessoaDocumento);
}

void CPessoaDocumento::setStruct(TPessoaDocumento *ptPessoaDocumento) {
    memcpy(&tPessoaDocumento, ptPessoaDocumento, sizeof(TPessoaDocumento));
    memset(ptPessoaDocumento, 0x00, sizeof(TPessoaDocumento));
}


char *CPessoaDocumento::getIdSistemaOrigem(void) {
    static char szAux[LEN_IDSISTEMAORIGEM + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szIdSistemaOrigem);
    return szAux;
}

char *CPessoaDocumento::getTsSincronismo(void) {
    static char szAux[LEN_TSSINCRONISMO + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szTsSincronismo);
    return szAux;
}

char *CPessoaDocumento::getIdDocumento(void) {
    static char szAux[LEN_IDDOCUMENTO + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szIdDocumento);
    return szAux;
}

char *CPessoaDocumento::getIdPessoa(void) {
    static char szAux[LEN_IDPESSOA + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szIdPessoa);
    return szAux;
}

char *CPessoaDocumento::getSqSincronismo(void) {
    static char szAux[LEN_SQSINCRONISMO + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szSqSincronismo);
    return szAux;
}

char *CPessoaDocumento::getIdDocumentoSistemaOrigem(void) {
    static char szAux[LEN_IDDOCUMENTOSISTEMAORIGEM + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szIdDocumentoSistemaOrigem);
    return szAux;
}

char *CPessoaDocumento::getIdPessoaDocumento(void) {
    static char szAux[LEN_IDPESSOADOCUMENTO + LEN_EOS];

    strcpy(szAux, tPessoaDocumento.szIdPessoaDocumento);
    return szAux;
}

bool CPessoaDocumento::apagouPessoaDocumento(void) {
    return this->bApagouPessoaDocumento;
}
