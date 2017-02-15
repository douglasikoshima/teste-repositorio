#ifndef PESSOAFISICAPCPORT
#define PESSOAFISICAPCPORT

#include "PPGlobalPORT.h"

class CPessoaFisicapc
{
public:
    bool proCBuscaPessoaFisica(TPessoaFisica *ptPessoaFisica);
    void proCInserePessoaFisica(TPessoaFisica *ptPessoaFisica);
    void proCAtualizaPessoaFisica(TPessoaFisica tPessoaFisica);
};

#endif
