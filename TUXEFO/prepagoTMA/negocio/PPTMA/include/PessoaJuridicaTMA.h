#ifndef PESSOAJURIDICAHTMA
#define PESSOAJURIDICAHTMA

#include "tuxfw.h"
#include "PessoaJuridicapcTMA.h"


class CPessoaJuridica
{
public:
	TPessoaJuridica     tPessoaJuridica;

    CPessoaJuridicapc   clPessoaJuridicapc;

    void setIdPessoa(char *pszIdPessoa);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setStruct(TPessoaJuridica *ptPessoaJuridica);

    char *getIdPessoa(void);

    CPessoaJuridica(void);

	void inserePessoaJuridica(void);
	void atualizaPessoaJuridica(void);

    bool buscaPessoaJuridica(void);
    bool buscaPessoaJuridica(TPessoaJuridica *ptPessoaJuridicaAUX);
};

#endif
