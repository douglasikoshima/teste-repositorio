///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase PessoaValorPossivel
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOAVALORPOSSIVELHPC
#define PESSOAVALORPOSSIVELHPC

#include "Global.h"

class CPessoaValorPossivelpc
{

public:
    void proCApagaEscolaridade(TPessoaValorPossivel *ptPessoaValorPossivel);
    void proCApagaOcupacao(TPessoaValorPossivel *ptPessoaValorPossivel);
    void proCInserePessoaValorPossivel(TPessoaValorPossivel *ptPessoaValorPossivel);
};

#endif
