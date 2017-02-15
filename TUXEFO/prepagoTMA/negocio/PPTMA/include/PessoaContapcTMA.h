#ifndef PESSOA_CONTA_PCTMA
#define PESSOA_CONTA_PCTMA

#include "PPGlobalTMA.h"

class CPessoaContapc
{

public:
    void proCAtualizaPessoaConta(TPessoaConta tPessoaConta);
    void proCInserePessoaConta(TPessoaConta *ptPessoaConta);
    bool proCBuscaPessoaConta(TPessoaConta *ptPessoaConta);
    void proCApagaPessoaConta(TPessoaConta tPessoaConta);
};

#endif
