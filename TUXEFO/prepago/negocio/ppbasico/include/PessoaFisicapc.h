///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaFisica
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOAFISICAPC
#define PESSOAFISICAPC

#include "Global.h"

class CPessoaFisicapc
{
public:
    bool proCBuscaPessoaFisica(TPessoaFisica *ptPessoaFisica);
    void proCInserePessoaFisica(TPessoaFisica *ptPessoaFisica);
    void proCAtualizaPessoaFisica(TPessoaFisica tPessoaFisica);
};

#endif
