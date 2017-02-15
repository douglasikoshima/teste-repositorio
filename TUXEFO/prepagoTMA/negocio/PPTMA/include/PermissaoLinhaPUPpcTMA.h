#ifndef PERMISSAOLINHAPUPPCTMA
#define PERMISSAOLINHAPUPPCTMA

#include "PPGlobalTMA.h"

class CPermissaoLinhaPUPpc
{
public:
    bool proCBuscaPermissaoLinhaPUP(TPermissaoLinhaPUP *ptPermissaoLinhaPUP);
    void proCInserePermissaoLinhaPUP(TPermissaoLinhaPUP *ptPermissaoLinhaPUP);
    void proCApagaPermissaoLinhaPUP(TPermissaoLinhaPUP *ptPermissaoLinhaPUP);
    void proCAlteraCadastroLinhaPUP(TPermissaoLinhaPUP *ptPermissaoLinhaPUP);
};

#endif
