#ifndef PESSOADEPARAHPORT
#define PESSOADEPARAHPORT

#include "tuxfw.h"
#include "PessoaDeParapcPORT.h"

class CPessoaDePara:private TuxHelper
{
public:
	TPessoaDePara tPessoaDePara;
	CPessoaDeParapc clPessoaDeParapc;

    CPessoaDePara(void);


    void setIdPessoa(char *pszIdPessoa);
    void setIdPessoaDePara(char *pszIdPessoaDePara);
    void setIdPessoaOrigem(char *pszIdPessoaOrigem);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    
    char *getIdPessoa(void);
    char *getIdPessoaDePara(void);
    
    
    bool buscaPessoa(TPessoaDePara *ptPessoaDeParaAUX);
    bool buscaPessoaDePara(void);
    void atualizaPessoaDePara(void);
    void inserePessoaDePara(void);
    void setStruct(TPessoaDePara *ptPessoaDePara);
};

#endif
