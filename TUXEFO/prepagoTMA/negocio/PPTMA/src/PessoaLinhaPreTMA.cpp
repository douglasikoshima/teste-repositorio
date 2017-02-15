#include "PessoaLinhaPreTMA.h"
#include "tuxfw.h"


CPessoaLinhaPre::CPessoaLinhaPre(void) {
    memset(&tPessoaLinhaPre, 0x00, sizeof(TPessoaLinhaPre));
}

void CPessoaLinhaPre::setIdPessoaLinha(char *pszIdPessoaLinha) {
    strcpy(tPessoaLinhaPre.szIdPessoaLinha, pszIdPessoaLinha);
}

void CPessoaLinhaPre::setInMudancaTitularidade(char *pszInMudancaTitularidade) {
    strcpy(tPessoaLinhaPre.szInMudancaTitularidade, pszInMudancaTitularidade);
}

void CPessoaLinhaPre::setInSincronismo(char *pszInSincronismo) {
    strcpy(tPessoaLinhaPre.szInSincronismo, pszInSincronismo);
}

void CPessoaLinhaPre::setInUsuarioNaoInformado(char *pszInUsuarioNaoInformado) {
    strcpy(tPessoaLinhaPre.szInUsuarioNaoInformado, pszInUsuarioNaoInformado);
}

void CPessoaLinhaPre::inserePessoaLinhaPre(void) {
    clPessoaLinhaPrepc.proCInserePessoaLinhaPre(tPessoaLinhaPre);
}

bool CPessoaLinhaPre::buscaPessoaLinhaPre(TPessoaLinhaPre *ptPessoaLinhaPre) {
    return clPessoaLinhaPrepc.proCBuscaPessoaLinhaPre(ptPessoaLinhaPre);
}

bool CPessoaLinhaPre::buscaPessoaLinhaPre(void) {
    return clPessoaLinhaPrepc.proCBuscaPessoaLinhaPre(&tPessoaLinhaPre);
}

void CPessoaLinhaPre::atualizaPessoaLinhaPre(void) {
    return clPessoaLinhaPrepc.proCAtualizaPessoaLinhaPre(tPessoaLinhaPre);
}

char *CPessoaLinhaPre::getIdPessoaLinha(void) {
    static char szAux[LEN_IDPESSOALINHA + LEN_EOS];

    strcpy(szAux, tPessoaLinhaPre.szIdPessoaLinha);
    return szAux;
}

void CPessoaLinhaPre::insereMudancaTitularidade(char *pszNrTelefone) {
    char szCdAreaRegistro[LEN_CDAREAREGISTRO + LEN_EOS];
    char szNrLinha[LEN_NRLINHA + LEN_EOS];

    memset(szCdAreaRegistro, 0x00, sizeof(szCdAreaRegistro));
    memcpy(szCdAreaRegistro, pszNrTelefone, 2);

    memset(szNrLinha, 0x00, sizeof(szNrLinha));
    strcpy( szNrLinha, (char*)&pszNrTelefone[2] );

    return clPessoaLinhaPrepc.proCInsereMudancaTitularidade(szCdAreaRegistro, szNrLinha);
}

void CPessoaLinhaPre::setStruct(TPessoaLinhaPre *ptPessoaLinhaPre) {
    memcpy(&tPessoaLinhaPre, ptPessoaLinhaPre, sizeof(TPessoaLinhaPre));
    memset(ptPessoaLinhaPre, 0x00, sizeof(TPessoaLinhaPre));
}
