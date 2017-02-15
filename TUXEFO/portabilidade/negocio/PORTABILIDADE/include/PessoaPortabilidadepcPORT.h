#ifndef PESSOAPORTABILIDADEPCHPORT
#define PESSOAPORTABILIDADEPCHPORT

#include "PPGlobalPORT.h"

class CPessoaPortabilidadepc
{

public:

    bool proCBuscaPessoaPortabilidade(TPessoaPortabilidade *ptPessoaPortabilidade);
    bool proCInserePessoaPortabilidade(TPessoaPortabilidade *ptPessoaPortabilidade);
    void proCAtualizaPessoaPortabilidade(TPessoaPortabilidade *ptPessoaPortabilidade);
};

#endif
