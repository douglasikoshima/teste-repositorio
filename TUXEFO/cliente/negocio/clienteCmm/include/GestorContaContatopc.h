#ifndef GESTORCONTACONTATOPCH
#define GESTORCONTACONTATOPCH

#include "Global.h"

class CGestorContaContatopc
{

public:

    bool proCBuscaGestorContaContato(TGestorContaContato *ptGestorContaContato);
    void proCInsereGestorContaContato(TGestorContaContato *ptGestorContaContato);
    void proCAtualizaGestorContaContato(TGestorContaContato *ptGestorContaContato);
    void proCApagaGestorContaContato(TGestorContaContato *ptGestorContaContato);
};

#endif
