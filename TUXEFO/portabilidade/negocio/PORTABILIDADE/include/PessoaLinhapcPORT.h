#ifndef PESSOALINHAHPCPORT
#define PESSOALINHAHPCPORT

#include "PPGlobalPORT.h"

class CPessoaLinhapc
{
  public:
    bool proCBuscaPessoaLinha(TPessoaLinha *ptPessoaLinha);
    void proCInserePessoaLinha(TPessoaLinha *ptPessoaLinha);
    void proCAtualizaPessoaLinha(TPessoaLinha tPessoaLinha);
};

#endif
