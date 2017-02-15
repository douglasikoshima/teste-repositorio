///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaLinhaPre
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOALINHAPREPC
#define PESSOALINHAPREPC

#include "Global.h"

class CPessoaLinhaPrepc
{
  public:
    void proCInserePessoaLinhaPre(TPessoaLinhaPre tPessoaLinhaPre);
    bool proCBuscaPessoaLinhaPre(TPessoaLinhaPre *ptPessoaLinhaPre);
    void proCAtualizaPessoaLinhaPre(TPessoaLinhaPre tPessoaLinhaPre);
    void proCInsereMudancaTitularidade(char* szCodAreaRegistro, char* szNrLinha);
};

#endif
