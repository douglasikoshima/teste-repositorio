#ifndef PERMISSAOLINHAPUPPC
#define PERMISSAOLINHAPUPPC

#include "Global.h"

class CPermissaoLinhaPUPpc
{
public:
    bool proCBuscaPermissaoLinhaPUP(TPermissaoLinhaPUP *ptPermissaoLinhaPUP);
    void proCInserePermissaoLinhaPUP(TPermissaoLinhaPUP *ptPermissaoLinhaPUP);
    void proCApagaPermissaoLinhaPUP(TPermissaoLinhaPUP *ptPermissaoLinhaPUP);
    void proCAlteraCadastroLinhaPUP(TPermissaoLinhaPUP *ptPermissaoLinhaPUP);
};

#endif
