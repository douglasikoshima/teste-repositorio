///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase PessoaSegmentacao
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1.152.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2015/09/24 17:55:46 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOASEGMENTACAOHPC
#define PESSOASEGMENTACAOHPC

#include "Global.h"

class CPessoaSegmentacaopc
{
  public:
    bool proCBuscaPessoaSegmentacao(TPessoaSegmentacao *ptPessoaSegmentacao);
    void proCInserePessoaSegmentacao(TPessoaSegmentacao *ptPessoaSegmentacao);
    void proCAtualizaPessoaSegmentacao(TPessoaSegmentacao *ptPessoaSegmentacao);
    bool proCClassificaInfancia( char * vi_idLinhaTelefonica, char * vi_idPessoa , char * vi_idTipoPessoa );
};

#endif
