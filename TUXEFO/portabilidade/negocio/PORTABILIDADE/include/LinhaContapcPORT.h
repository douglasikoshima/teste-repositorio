#ifndef LINHACONTAPCHPORT
#define LINHACONTAPCHPORT

#include "PPGlobalPORT.h"

class CLinhaContapc
{
  public:
    void proCInsereLinhaConta(TLinhaConta *ptLinhaConta);
    bool proCBuscaLinhaConta(TLinhaConta *ptLinhaConta);
    void proCAtualizaLinhaConta(TLinhaConta tLinhaConta);
};

#endif
