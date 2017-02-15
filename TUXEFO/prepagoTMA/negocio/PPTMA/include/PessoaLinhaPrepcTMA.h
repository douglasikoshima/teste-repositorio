#ifndef PESSOALINHAPREPCTMA
#define PESSOALINHAPREPCTMA

#include "PPGlobalTMA.h"

class CPessoaLinhaPrepc
{
  public:
    void proCInserePessoaLinhaPre(TPessoaLinhaPre tPessoaLinhaPre);
    bool proCBuscaPessoaLinhaPre(TPessoaLinhaPre *ptPessoaLinhaPre);
    void proCAtualizaPessoaLinhaPre(TPessoaLinhaPre tPessoaLinhaPre);
    void proCInsereMudancaTitularidade(char* szCodAreaRegistro, char* szNrLinha);
};

#endif
