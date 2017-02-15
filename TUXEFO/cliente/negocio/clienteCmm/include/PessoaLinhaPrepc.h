///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Cliente
 * @usecase PessoaLinhaPre
 * @author  Renato Striitzel Russo
 * @author  Robinson Vieira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOALINHAPREPC
#define PESSOALINHAPREPC

#include "GlobalPre.h"

class CPessoaLinhaPrepc
{
  public:
    bool proCBuscaPessoaLinha(TPessoaLinha *ptPessoaLinha);
    void proCInserePessoaLinha(TPessoaLinha *ptPessoaLinha);
    void proCAtualizaPessoaLinha(TPessoaLinha tPessoaLinha);
    void proCApagaPessoaLinha(TPessoaLinha tPessoaLinha);
};

#endif
