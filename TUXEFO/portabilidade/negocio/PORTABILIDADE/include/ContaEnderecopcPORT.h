#ifndef CONTAEPCPORT
#define CONTAEPCPORT

#include "PPGlobalPORT.h"

class CContaEnderecopc
{
public:
    bool proCBuscaContaEndereco(TContaEndereco *ptContaEndereco);
    bool proCBuscaContaEnderecoAntigo(TContaEndereco *ptContaEndereco);
    void proCInsereContaEndereco(TContaEndereco *ptContaEndereco);
    void proCAtualizaContaEndereco(TContaEndereco tContaEndereco);
};

#endif
