#ifndef PESSOACOMUNICACAOPCHTMA
#define PESSOACOMUNICACAOPCHTMA

#include "PPGlobalTMA.h"

class CPessoaComunicacaopc
{
public:

    void proCInserePessoaComunicacao(TPessoaComunicacao *ptPessoaComunicacao);
    void proCAtualizaPessoaComunicacao( TPessoaComunicacao tDadosPessoaComunicacao );
    bool proCBuscaPessoaComunicacao(TPessoaComunicacao *ptPessoaComunicacao);
    bool proCBuscaPessoaComunicacaoIdPessoaSgClass(TPessoaComunicacao *ptPessoaComunicacao);
};

#endif
