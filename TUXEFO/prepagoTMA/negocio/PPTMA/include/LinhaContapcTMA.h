#ifndef LINHACONTAPCHTMA
#define LINHACONTAPCHTMA

#include "PPGlobalTMA.h"

class CLinhaContapc
{
  public:
    void proCInsereLinhaConta(TLinhaConta *ptLinhaConta);
    bool proCBuscaLinhaConta(TLinhaConta *ptLinhaConta);
    void proCAtualizaLinhaConta(TLinhaConta tLinhaConta);
};

#endif
