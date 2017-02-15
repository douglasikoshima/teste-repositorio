#include "PessoaLinhaHistoricoTMA.h"

CPessoaLinhaHistorico::CPessoaLinhaHistorico(void)  {
    memset(&tPessoaLinhaHistorico, 0x00, sizeof(TPessoaLinhaHistorico));
}

void CPessoaLinhaHistorico::inserePessoaLinhaHistorico(void) {
    clPessoaLinhaHistoricopc.proCInserePessoaLinhaHistorico(&tPessoaLinhaHistorico);
}

void CPessoaLinhaHistorico::setIdLinhaTelefonica(char *pszIdLinhaTelefonica) {
    strcpy(tPessoaLinhaHistorico.szIdLinhaTelefonica, pszIdLinhaTelefonica);
}

void CPessoaLinhaHistorico::setIdPessoaDePara(char *pszIdPessoaDePara) {
    strcpy(tPessoaLinhaHistorico.szIdPessoaDePara, pszIdPessoaDePara);
}

void CPessoaLinhaHistorico::setIdTipoRelacionamento(char *pszIdTipoRelacionamento) {
    strcpy(tPessoaLinhaHistorico.szIdTipoRelacionamento, pszIdTipoRelacionamento);
}

void CPessoaLinhaHistorico::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tPessoaLinhaHistorico.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CPessoaLinhaHistorico::setCdAreaRegistro(char *pszCdAreaRegistro) {
    strcpy(tPessoaLinhaHistorico.szCdAreaRegistro, pszCdAreaRegistro);
}

void CPessoaLinhaHistorico::setNrLinha(char *pszNrLinha) {
    strcpy(tPessoaLinhaHistorico.szNrLinha, pszNrLinha);
}

char *CPessoaLinhaHistorico::getIdPessoaLinhaHistorico(void) {
    static char szIdPessoaLinhaHistorico[LEN_IDPESSOALINHAHISTORICO + LEN_EOS];

    strcpy(szIdPessoaLinhaHistorico, tPessoaLinhaHistorico.szIdPessoaLinhaHistorico);
    return szIdPessoaLinhaHistorico;
}
