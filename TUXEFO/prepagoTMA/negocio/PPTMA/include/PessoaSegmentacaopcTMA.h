#ifndef PESSOASEGMENTACAOHPCTMA
#define PESSOASEGMENTACAOHPCTMA

#include "PPGlobalTMA.h"

class CPessoaSegmentacaopc
{
  public:
    bool proCBuscaPessoaSegmentacao(TPessoaSegmentacao *ptPessoaSegmentacao);
    void proCInserePessoaSegmentacao(TPessoaSegmentacao *ptPessoaSegmentacao);
    void proCAtualizaPessoaSegmentacao(TPessoaSegmentacao *ptPessoaSegmentacao);
    bool proCClassificaInfancia( char * vi_idLinhaTelefonica, char * vi_idPessoa , char * vi_idTipoPessoa );
};

#endif
