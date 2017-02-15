#ifndef PESSOA_CONTAHPORT
#define PESSOA_CONTAHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "PessoaContapcPORT.h"

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
    char *getIdConta(void);
    char *getIdTipoRelacionamento(void);
};

#endif
