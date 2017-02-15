#include "AreaRegistroBloqueadoPreTMA.h"

CAreaRegistroBloqueado::CAreaRegistroBloqueado(void) {
    memset(&tAreaRegistroBloqueado, 0x00, sizeof(TAreaRegistroBloqueado));
}

void CAreaRegistroBloqueado::setCdAreaRegistro(char *pszCdAreaRegistro) {
    ULOG("pszCdAreaRegistro[%s]", pszCdAreaRegistro);
    strcpy(tAreaRegistroBloqueado.szCdAreaRegistro, pszCdAreaRegistro);
}

bool CAreaRegistroBloqueado::buscaAreaRegistroBloqueado(void) {
    return clAreaRegistroBloqueadopc.proCBuscaAreaRegistroBloqueado(&tAreaRegistroBloqueado);
}

int CAreaRegistroBloqueado::getInBloqueado(void) {
    ULOG("tAreaRegistroBloqueado.szInBloqueado[%s]", tAreaRegistroBloqueado.szInBloqueado);
    return atoi(tAreaRegistroBloqueado.szInBloqueado);
}

bool CAreaRegistroBloqueado::DDDBloqueado(void) {
    if(clAreaRegistroBloqueadopc.proCBuscaAreaRegistroBloqueado(&tAreaRegistroBloqueado) == true)
    {
        if(this->getInBloqueado() != 0 )
        {
            return true;
        }
        else
        {
            return false;
        }
    }
    else
    {
        return false;
    }
}
