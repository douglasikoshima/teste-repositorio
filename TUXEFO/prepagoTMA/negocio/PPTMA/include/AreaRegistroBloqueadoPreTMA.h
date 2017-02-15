#ifndef AREAREGISTROBLOQUEADOHTMA
#define AREAREGISTROBLOQUEADOHTMA

#include "AreaRegistroBloqueadoPrepcTMA.h"
#include "tuxfw.h"

class CAreaRegistroBloqueado
{
public:
	TAreaRegistroBloqueado  tAreaRegistroBloqueado;
    CAreaRegistroBloqueadopc clAreaRegistroBloqueadopc;


    CAreaRegistroBloqueado(void);

    void setCdAreaRegistro(char *pszCdAreaRegistro);
    int getInBloqueado(void);
    bool DDDBloqueado(void);

    bool buscaAreaRegistroBloqueado(void);
};
#endif
