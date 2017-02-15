#include "LinhaTelefonicaPORT.h"
#include "PPExceptionPORT.h"

CLinhaTelefonica::CLinhaTelefonica(void) {
    memset(&tLinhaTelefonica, 0x00, sizeof(TLinhaTelefonica));
}

void CLinhaTelefonica::insereLinhaTelefonica(void) {
    return clLinhaTelefonicapc.proCInsereLinhaTelefonica(&tLinhaTelefonica);
}


void CLinhaTelefonica::setIdSistemaOrigem(char *pszIdSistemaOrigem) {
    strcpy(tLinhaTelefonica.szIdSistemaOrigem, pszIdSistemaOrigem);
}

void CLinhaTelefonica::setIdLinhasistemaOrigem(char *pszIdLinhasistemaOrigem) {
    strcpy(tLinhaTelefonica.szIdLinhasistemaOrigem, pszIdLinhasistemaOrigem);
}

void CLinhaTelefonica::setIdTipoLinha(char *pszIdTipoLinha) {
    strcpy(tLinhaTelefonica.szIdTipoLinha, pszIdTipoLinha);
}

void CLinhaTelefonica::setInDivulgacaoNrLinha(char *pszInDivulgacaoNrLinha) {
    strcpy(tLinhaTelefonica.szInDivulgacaoNrLinha, pszInDivulgacaoNrLinha);
}

void CLinhaTelefonica::setCdChurnProbabilidade(char *pszCdChurnProbabilidade) {
    strcpy(tLinhaTelefonica.szCdChurnProbabilidade, pszCdChurnProbabilidade);
}

void CLinhaTelefonica::setVlrChurnProbabilidade(char *pszVlrChurnProbabilidade) {
    strcpy(tLinhaTelefonica.szVlrChurnProbabilidade, pszVlrChurnProbabilidade);
}

void CLinhaTelefonica::setIdLinhaBase(char *pszIdLinhaBase) {
    strcpy(tLinhaTelefonica.szIdLinhaBase, pszIdLinhaBase);
}

void CLinhaTelefonica::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tLinhaTelefonica.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CLinhaTelefonica::setInUsuarioInformado(char *pszInUsuarioInformado) {
    strcpy(tLinhaTelefonica.szInUsuarioInformado, pszInUsuarioInformado);
}
