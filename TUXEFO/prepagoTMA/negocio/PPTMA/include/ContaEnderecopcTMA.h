#ifndef CONTAEPCTMA
#define CONTAEPCTMA

#include "PPGlobalTMA.h"

class CContaEnderecopc
{
public:
    bool proCBuscaContaEndereco(TContaEndereco *ptContaEndereco);
    bool proCBuscaContaEnderecoAntigo(TContaEndereco *ptContaEndereco);
    void proCInsereContaEndereco(TContaEndereco *ptContaEndereco);
    void proCAtualizaContaEndereco(TContaEndereco tContaEndereco);
};

#endif
