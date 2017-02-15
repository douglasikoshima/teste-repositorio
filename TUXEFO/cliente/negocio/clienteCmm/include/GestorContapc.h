#ifndef GESTORCONTAPCH
#define GESTORCONTAPCH

#include "Global.h"

class CGestorContapc
{

public:

    bool proCBuscaGestorConta(TGestorConta *ptGestorConta);
    void proCInsereGestorConta(TGestorConta *ptGestorConta);
    void proCAtualizaGestorConta(TGestorConta *ptGestorConta);
    void proCApagaGestorConta(TGestorConta *ptGestorConta);
};

#endif
