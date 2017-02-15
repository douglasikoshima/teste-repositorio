#ifndef PESSOALINHAPRETMA
#define PESSOALINHAPRETMA

#include "PPGlobalTMA.h"
#include "PessoaLinhaPrepcTMA.h"

class CPessoaLinhaPre
{
public:
    TPessoaLinhaPre tPessoaLinhaPre;
    CPessoaLinhaPrepc clPessoaLinhaPrepc;
    
    CPessoaLinhaPre(void);  
    void inserePessoaLinhaPre(void);
    bool buscaPessoaLinhaPre(TPessoaLinhaPre *ptPessoaLinhaPre);
    bool buscaPessoaLinhaPre(void);
    void atualizaPessoaLinhaPre(void);

    void setIdPessoaLinha(char *pszIdPessoaLinha);
    void setInMudancaTitularidade(char *pszInMudancaTitularidade);
    void setInSincronismo(char *pszInSincronismo);
    void setInUsuarioNaoInformado(char *pszInUsuarioNaoInformado);

    char *getIdPessoaLinha(void);

    void insereMudancaTitularidade(char *pszNrTelefone);

    void setStruct(TPessoaLinhaPre *ptPessoaLinhaPre);
};
#endif
