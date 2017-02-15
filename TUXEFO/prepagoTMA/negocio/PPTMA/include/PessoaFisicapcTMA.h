#ifndef PESSOAFISICAPCTMA
#define PESSOAFISICAPCTMA

#include "PPGlobalTMA.h"

class CPessoaFisicapc
{
public:
    bool proCBuscaPessoaFisica(TPessoaFisica *ptPessoaFisica);
    void proCInserePessoaFisica(TPessoaFisica *ptPessoaFisica);
    void proCAtualizaPessoaFisica(TPessoaFisica tPessoaFisica);
};

#endif
