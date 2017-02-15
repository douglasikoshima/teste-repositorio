#ifndef PESSOA_ENDERECOH_PCPORT
#define PESSOA_ENDERECOH_PCPORT

#include "PPGlobalPORT.h"

class CPessoaEnderecopc
{
public:
    void proCInserePessoaEndereco(TPessoaEndereco* ptPessoaEndereco);
    void proCAtualizaPessoaEndereco(TPessoaEndereco* ptPessoaEndereco);
    void proCApagaPessoaEndereco(TPessoaEndereco* ptPessoaEndereco);
    bool proCBuscaPessoaEndereco(TPessoaEndereco* ptPessoaEndereco);
    void proCEnderecoSujo(TPessoaEndereco* ptPessoaEndereco);
};

#endif
