#ifndef PESSOASEGMENTACAOHISTORICOHPCPORT
#define PESSOASEGMENTACAOHISTORICOHPCPORT

#include "PPGlobalPORT.h"

class CPessoaSegmentacaoHistoricopc
{
  public:
    bool proCBuscaPessoaSegmentacaoHistorico(TPessoaSegmentacaoHistorico *ptPessoaSegmentacaoHistorico);
    void proCInserePessoaSegmentacaoHistorico(TPessoaSegmentacaoHistorico *ptPessoaSegmentacaoHistorico);
};

#endif
