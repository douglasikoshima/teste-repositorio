#ifndef PESSOAPCHPORT
#define PESSOAPCHPORT

#include "PPGlobalPORT.h"

class CPessoapc
{
public:
    void proCInserePessoa(TPessoa *ptPessoa);
    void proCAtualizaPessoa(TPessoa *ptPessoa);
    bool proCBuscaPessoa(TPessoa *ptPessoa);
};

#endif
