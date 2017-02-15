#ifndef PESSOALINHAHTMA
#define PESSOALINHAHTMA

#include "PPGlobalTMA.h"
#include "tuxfw.h"
#include "PessoaLinhapcTMA.h"

class CPessoaLinha
{

public:

    CPessoaLinha(void);
    ~CPessoaLinha(void);

    TPessoaLinha    tPessoaLinha;
    CPessoaLinhapc  clPessoaLinhapc;

    bool buscaPessoaLinha(void);
    bool buscaPessoaLinha(TPessoaLinha *ptPessoaLinhaAux);
    void atualizaPessoaLinha(void);
    void inserePessoaLinha(void);

    void setIdPessoaDePara(char *pszIdPessoaDePara);
    void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);
    void setIdTipoRelacionamento(char *pszIdTipoRelacionamento);
    void setIdPessoaLinha(char *pszIdPessoaLinha);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setStruct(TPessoaLinha *ptPessoaLinha);

    char *getIdPessoaDePara(void);
    char *getIdPessoaLinha(void);
    char *getIdLinhaTelefonica(void);
    char *getIdTipoRelacionamento(void);

    bool inseriuPessoaLinha(void);

protected:
    bool bInsertPessoaLinha;
};

#endif
