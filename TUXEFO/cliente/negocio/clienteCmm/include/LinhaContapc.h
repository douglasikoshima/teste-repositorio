///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase LinhaConta
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef LINHACONTAPC
#define LINHACONTAPC

#include "Global.h"

class CLinhaContapc
{
  public:
    void proCInsereLinhaConta(TLinhaConta *ptLinhaConta);
    bool proCBuscaLinhaConta(TLinhaConta *ptLinhaConta);
    void proCAtualizaLinhaConta(TLinhaConta tLinhaConta);
    void proCApagaLinhaConta(TLinhaConta tLinhaConta);
};

#endif
