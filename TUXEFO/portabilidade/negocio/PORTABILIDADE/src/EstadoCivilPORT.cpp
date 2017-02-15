#include "EstadoCivilPORT.h"

CEstadoCivil::CEstadoCivil(void) {
    memset(&tEstadoCivil, 0x00, sizeof(TEstadoCivil));
}

bool CEstadoCivil::buscaEstadoCivil(void) {
    return clEstadoCivilpc.proCBuscaEstadoCivil(&tEstadoCivil);
}

void CEstadoCivil::setSgEstadoCivil(char *pszSgEstadoCivil) {
    strcpy(tEstadoCivil.szSgEstadoCivil, pszSgEstadoCivil);
}

char *CEstadoCivil::getIdEstadoCivil(void) {
    static char szAux[LEN_IDESTADOCIVIL + LEN_EOS];

    strcpy(szAux, tEstadoCivil.szIdEstadoCivil);
    return szAux;
}

char *CEstadoCivil::getSgEstadoCivil(void) {
    static char szAux[LEN_SGESTADOCIVIL + LEN_EOS];

    strcpy(szAux, tEstadoCivil.szSgEstadoCivil);
    return szAux;
}
