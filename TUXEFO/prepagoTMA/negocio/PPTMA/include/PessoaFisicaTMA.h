#ifndef PESSOAFISICAHTMA
#define PESSOAFISICAHTMA

#include "tuxfw.h"
#include "PessoaFisicapcTMA.h"


class CPessoaFisica
{
public:
	TPessoaFisica   tPessoaFisica;

    CPessoaFisicapc clPessoaFisicapc;

    void setIdPessoa(char *pszIdPessoa);
    void setDtNascimento(char *pszDtNascimento);
    void setIdTratamento(char *pszIdTratamento);
    void setIdEstadoCivil(char *pszIdEstadoCivil);
    void setIdPais(char *pszIdPais);
    void setIdSexo(char *pszIdSexo);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    char *getIdEstadoCivil(void);
    char *getDtNascimento(void);
    char *getIdSexo(void);
    char *getIdPessoa(void);

    void setStruct(TPessoaFisica *ptPessoaFisica);

    CPessoaFisica(void);

	void inserePessoaFisica(void);
	void atualizaPessoaFisica(void);
    bool buscaPessoaFisica(void);
    bool buscaPessoaFisica(TPessoaFisica *ptPessoaFisicaAUX);
};

#endif
