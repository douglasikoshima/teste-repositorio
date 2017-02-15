#include "LinhaBasePORT.h"
#include "PPExceptionPORT.h"

CLinhaBase::CLinhaBase(void) {
    memset(&tLinhaBase, 0x00, sizeof(TLinhaBase));
}

void CLinhaBase::insereLinhaBase(void) {
    return clLinhaBasepc.proCInsereLinhaBase(&tLinhaBase);
}


void CLinhaBase::setIdAreaRegistro(char *pszIdAreaRegistro) {
    strcpy(tLinhaBase.szIdAreaRegistro, pszIdAreaRegistro);
}

void CLinhaBase::setNrLinha(char *pszNrLinha) {
    strcpy(tLinhaBase.szNrLinha, pszNrLinha);
}

void CLinhaBase::setNrMin(char *pszNrMin) {
    strcpy(tLinhaBase.szNrMin, pszNrMin);
}

void CLinhaBase::setNrDigitoLinha(char *pszNrDigitoLinha) {
    strcpy(tLinhaBase.szNrDigitoLinha, pszNrDigitoLinha);
}

void CLinhaBase::setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao) {
    strcpy(tLinhaBase.szIdUsuarioAlteracao, pszIdUsuarioAlteracao);
}

void CLinhaBase::setIdEstadoLinha(char *pszIdEstadoLinha) {
    strcpy(tLinhaBase.szIdEstadoLinha, pszIdEstadoLinha);
}

void CLinhaBase::setDtEstadoLinha(char *pszDtEstadoLinha) {
    strcpy(tLinhaBase.szDtEstadoLinha, pszDtEstadoLinha);
}

void CLinhaBase::setDsMotivoEstado(char *pszDsMotivoEstado) {
    strcpy(tLinhaBase.szDsMotivoEstado, pszDsMotivoEstado);
}

void CLinhaBase::setIdNumeroSistemaOrigem(char *pszIdNumeroSistemaOrigem) {
    strcpy(tLinhaBase.szIdNumeroSistemaOrigem, pszIdNumeroSistemaOrigem);
}

void CLinhaBase::setIdSistemaOrigem(char *pszIdSistemaOrigem) {
    strcpy(tLinhaBase.szIdSistemaOrigem, pszIdSistemaOrigem);
}
