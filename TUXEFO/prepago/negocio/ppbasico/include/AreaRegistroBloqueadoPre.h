#ifndef AREAREGISTROBLOQUEADOH
#define AREAREGISTROBLOQUEADOH

#include "tuxfw.h"
#include "AreaRegistroBloqueadoPrepc.h"

class CAreaRegistroBloqueado
{
public:
	TAreaRegistroBloqueado  tAreaRegistroBloqueado;
    CAreaRegistroBloqueadopc clAreaRegistroBloqueadopc;


    CAreaRegistroBloqueado(void);

    void setCdAreaRegistro(char *pszCdAreaRegistro);
    void setNrLinha(char *pszNrLinha);
    int getInBloqueado(void);
    bool DDDBloqueado(void);

    bool buscaAreaRegistroBloqueado(void);
};
#endif
