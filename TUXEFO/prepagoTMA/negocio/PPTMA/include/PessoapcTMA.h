#ifndef PESSOAPCHTMA
#define PESSOAPCHTMA

#include "PPGlobalTMA.h"

class CPessoapc
{
public:
    void proCInserePessoa(TPessoa *ptPessoa);
    void proCAtualizaPessoa(TPessoa *ptPessoa);
    bool proCBuscaPessoa(TPessoa *ptPessoa);
};

#endif
