#ifndef PESSOALINHAPREPCPORT
#define PESSOALINHAPREPCPORT

#include "PPGlobalPORT.h"

class CPessoaLinhaPrepc
{
  public:
    void proCInserePessoaLinhaPre(TPessoaLinhaPre tPessoaLinhaPre);
    bool proCBuscaPessoaLinhaPre(TPessoaLinhaPre *ptPessoaLinhaPre);
    void proCAtualizaPessoaLinhaPre(TPessoaLinhaPre tPessoaLinhaPre);
    void proCInsereMudancaTitularidade(char* szCodAreaRegistro, char* szNrLinha);
};

#endif
