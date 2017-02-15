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

#ifndef PESSOASEGMENTACAOH
#define PESSOASEGMENTACAOH

#include "Global.h"
#include "PessoaSegmentacaopc.h"

class CPessoaSegmentacao
{

public:

    CPessoaSegmentacao(void);

    TPessoaSegmentacao tPessoaSegmentacao;
    CPessoaSegmentacaopc clPessoaSegmentacaopc;

    bool buscaPessoaSegmentacao(void);
    bool buscaPessoaSegmentacao(TPessoaSegmentacao *ptPessoaSegmentacaoAux);
    void inserePessoaSegmentacao(void);
    void atualizaPessoaSegmentacao(void);
    bool ClassificaInfancia( char * vi_idLinhaTelefonica, char * vi_idPessoa, char * vi_idTipoPessoa );

    void setIdPessoaDePara(char *pszIdPessoaDePara);
    void setIdPessoaSegmentacao(char *pszIdPessoaSegmentacao);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    char *getIdPessoaSegmentacao(void);
    char *getIdPessoaDePara(void);
};

#endif
