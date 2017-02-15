#ifndef PESSOASEGMENTACAOHPCPORT
#define PESSOASEGMENTACAOHPCPORT

#include "PPGlobalPORT.h"

class CPessoaSegmentacaopc
{
  public:
    bool proCBuscaPessoaSegmentacao(TPessoaSegmentacao *ptPessoaSegmentacao);
    void proCInserePessoaSegmentacao(TPessoaSegmentacao *ptPessoaSegmentacao);
    void proCAtualizaPessoaSegmentacao(TPessoaSegmentacao *ptPessoaSegmentacao);
};

#endif
