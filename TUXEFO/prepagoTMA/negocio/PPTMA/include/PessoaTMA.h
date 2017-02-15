#ifndef PESSOAHTMA
#define PESSOAHTMA

#include "tuxfw.h"
#include "PessoapcTMA.h"

class CPessoa:private TuxHelper
{
public:
	TPessoa tPessoa;
	CPessoapc clPessoapc;

    CPessoa(void);

    void setIdPessoa(char *pszIdPessoa);
    void setIdSistemaOrigem(char *pszIdSistemaOrigem);
    void setIdPessoaSistemaOrigem(char *pszIdPessoaSistemaOrigem);
    void setIdPessoaSistemaOrigem(char *pszPrefix, char *pszIdPessoaSistemaOrigem);
    void setTsSincronismo(char *pszTsSincronismo);
    void setSqSincronismo(char *pszSqSincronismo);
    void setDsCargoContato(char *pszDsCargoContato);
    void setDsDeptoContato(char *pszDsDeptoContato);
    void setInFalecimentoInformado(char *pszInFalecimentoInformado);
    void setIdUf(char *pszIdUf);
    void setIdTipoCarteira(char *pszIdTipoCarteira);
    void setIdProbInadimplencia(char *pszIdProbInadimplencia);
    void setIdChurnProbabilidade(char *pszIdChurnProbabilidade);
    void setIdTipoPessoa(char *pszIdTipoPessoa);
    void setStruct(TPessoa *ptPessoa);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    char *getIdPessoa(void);
    char *getIdTipoPessoa(void);
    char *getIdSistemaOrigem(void);
    char *getIdTipoCarteira(void);
    char *getSqSincronismo(void);
    char *getTsSincronismo(void);
    char *getIdPessoaSistemaOrigem(void);
    char *getNmPessoa(void);

    bool buscaPessoa(TPessoa *ptPessoaAUX);
    bool buscaPessoa(void);
    void atualizaPessoa(void);
    void inserePessoa(void);
};

#endif
