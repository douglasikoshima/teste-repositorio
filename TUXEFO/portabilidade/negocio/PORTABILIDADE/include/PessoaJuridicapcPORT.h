#ifndef PESSOAJURIDICAPCPORT
#define PESSOAJURIDICAPCPORT

#include "PPGlobalPORT.h"

class CPessoaJuridicapc
{
public:
    void proCInserePessoaJuridica(TPessoaJuridica *ptPessoaJuridica);
    void proCAtualizaPessoaJuridica(TPessoaJuridica tPessoaJuridica);
    bool proCBuscaPessoaJuridica(TPessoaJuridica *ptPessoaJuridica);
};

#endif
