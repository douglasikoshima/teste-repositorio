#include "AreaRegistroBloqueadoPre.h"

CAreaRegistroBloqueado::CAreaRegistroBloqueado(void)
{
    memset(&tAreaRegistroBloqueado, 0x00, sizeof(TAreaRegistroBloqueado));
}

void CAreaRegistroBloqueado::setCdAreaRegistro(char *pszCdAreaRegistro)
{
    ULOG("pszCdAreaRegistro[%s]", pszCdAreaRegistro);
    strcpy(tAreaRegistroBloqueado.szCdAreaRegistro, pszCdAreaRegistro);
}

bool CAreaRegistroBloqueado::buscaAreaRegistroBloqueado(void)
{
    return clAreaRegistroBloqueadopc.proCBuscaAreaRegistroBloqueado(&tAreaRegistroBloqueado);
}

int CAreaRegistroBloqueado::getInBloqueado(void)
{
    ULOG("tAreaRegistroBloqueado.szInBloqueado[%s]", tAreaRegistroBloqueado.szInBloqueado);
    return atoi(tAreaRegistroBloqueado.szInBloqueado);
}

bool CAreaRegistroBloqueado::DDDBloqueado(void)
{
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

void CAreaRegistroBloqueado::setNrLinha(char *pszNrLinha)
{
    char szNrLinha[11 + 1];

    ULOG("pszNrLinha[%s]", pszNrLinha);

    memset(szNrLinha, 0x00, sizeof(szNrLinha));
    memcpy(szNrLinha, pszNrLinha, 2);


    this->setCdAreaRegistro(szNrLinha);
}
