#ifndef CONTATOFUNCIONALIDADEHPORT
#define CONTATOFUNCIONALIDADEHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "ContatoFuncionalidadepcPORT.h"

class CContatoFuncionalidade
{

public:
	TContatoFuncionalidade tContatoFuncionalidade;
	CContatoFuncionalidadepc clContatoFuncionalidadepc;

    CContatoFuncionalidade(void);
    bool buscaContatoFuncionalidade(void);

    void  setCdFuncionalidade(char *pszCdFuncionalidade);
    char *getCdFuncionalidade(void);
    char *getIdContato(void);
};

#endif
