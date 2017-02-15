#ifndef PESSOA_CONTA_PCPORT
#define PESSOA_CONTA_PCPORT

#include "PPGlobalPORT.h"

class CPessoaContapc
{

public:
    void proCAtualizaPessoaConta(TPessoaConta tPessoaConta);
    void proCInserePessoaConta(TPessoaConta *ptPessoaConta);
    bool proCBuscaPessoaConta(TPessoaConta *ptPessoaConta);
    void proCApagaPessoaConta(TPessoaConta tPessoaConta);
};

#endif
