#ifndef PESSOASEGMENTACAOHTMA
#define PESSOASEGMENTACAOHTMA

#include "PPGlobalTMA.h"
#include "PessoaSegmentacaopcTMA.h"

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
