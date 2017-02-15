#ifndef PESSOA_CONTAHTMA
#define PESSOA_CONTAHTMA

#include "tuxfw.h"
#include "PPGlobalTMA.h"
#include "PessoaContapcTMA.h"

class CPessoaConta
{
public:
    TPessoaConta tPessoaConta;
    CPessoaContapc clPessoaContapc;

    CPessoaConta(void);

    bool buscaPessoaConta(void);
    bool buscaPessoaConta(TPessoaConta *ptPessoaContaAux);
    void atualizaPessoaConta(void);
    void inserePessoaConta(void);
    void apagaPessoaConta(void);

    void setIdConta(char *pszIdConta);
    void setIdTipoRelacionamento(char *pszIdTipoRelacionamento);
    void setIdPessoaDePara(char *pszIdPessoaDePara);
    void setDtPessoaConta(char *pszDtPessoaConta);
    void setIdPessoaConta(char *pszIdPessoaConta);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    char *getIdPessoaConta(void);
    char *getIdPessoaDePara(void);
};

#endif
