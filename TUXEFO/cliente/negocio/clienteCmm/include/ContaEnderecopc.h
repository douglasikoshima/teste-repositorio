///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase ContaEndereco
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef CONTAEPC
#define CONTAEPC

#include "Global.h"

class CContaEnderecopc
{
public:
    void proCApagaContaEndereco(TContaEndereco tContaEndereco);
    bool proCBuscaContaEndereco(TContaEndereco *ptContaEndereco);
    void proCInsereContaEndereco(TContaEndereco *ptContaEndereco);
    void proCAtualizaContaEndereco(TContaEndereco tContaEndereco);
};

#endif
