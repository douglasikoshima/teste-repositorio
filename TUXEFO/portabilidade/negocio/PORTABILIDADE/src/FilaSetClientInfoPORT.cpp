#include "FilaSetClientInfoPORT.h"

CFilaSetClientInfo::CFilaSetClientInfo(void) {
    memset(&tFilaSetClientInfo, 0x00, sizeof(TFilaSetClientInfo));
}

void CFilaSetClientInfo::atualizaXmlFilaSetClientInfo(void) {
    clFilaSetClientInfopc.proCAtualizaXmlFilaSetClientInfo(tFilaSetClientInfo);
}

void CFilaSetClientInfo::insereFilaSetClientInfo(void) {
    clFilaSetClientInfopc.proCIncluiFilaSetClientInfo(&tFilaSetClientInfo);
}

bool CFilaSetClientInfo::existeFilaSetClientInfo(void) {
    return clFilaSetClientInfopc.proCExisteFilaSetClientInfo(tFilaSetClientInfo);
}

void CFilaSetClientInfo::setIdLinhaTelefonica(char *pszIdLinhaTelefonica) {
    strcpy(tFilaSetClientInfo.szIdLinhaTelefonica, pszIdLinhaTelefonica);
}

void CFilaSetClientInfo::setXml(char *pszXml) {
    strcpy(tFilaSetClientInfo.szXml, pszXml);
}

char *CFilaSetClientInfo::getIdFilaSetClientInfo(void) {
    static char szAux[LEN_IDFILASETCLIENTINFO + LEN_EOS];

    strcpy(szAux, tFilaSetClientInfo.szIdFilaSetClientInfo);
    return szAux;
}

char *CFilaSetClientInfo::getIdLinhaTelefonica(void) {
    static char szAux[LEN_IDLINHATELEFONICA + LEN_EOS];

    strcpy(szAux, tFilaSetClientInfo.szIdLinhaTelefonica);
    return szAux;
}
