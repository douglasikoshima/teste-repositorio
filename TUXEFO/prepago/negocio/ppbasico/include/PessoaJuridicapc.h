///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaJuridica
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOAJURIDICAPC
#define PESSOAJURIDICAPC

#include "Global.h"

class CPessoaJuridicapc
{
public:
    void proCInserePessoaJuridica(TPessoaJuridica *ptPessoaJuridica);
    void proCAtualizaPessoaJuridica(TPessoaJuridica tPessoaJuridica);
    bool proCBuscaPessoaJuridica(TPessoaJuridica *ptPessoaJuridica);
};

#endif
