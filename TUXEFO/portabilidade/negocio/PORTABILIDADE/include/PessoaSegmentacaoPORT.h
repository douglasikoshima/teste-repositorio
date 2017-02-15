#ifndef PESSOASEGMENTACAOHPORT
#define PESSOASEGMENTACAOHPORT

#include "PPGlobalPORT.h"
#include "PessoaSegmentacaopcPORT.h"

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

    void setIdPessoaDePara(char *pszIdPessoaDePara);
    void setIdPessoaSegmentacao(char *pszIdPessoaSegmentacao);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    char *getIdPessoaSegmentacao(void);
    char *getIdPessoaDePara(void);
};

#endif
