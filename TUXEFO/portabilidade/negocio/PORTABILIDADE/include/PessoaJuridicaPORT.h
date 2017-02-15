#ifndef PESSOAJURIDICAHPORT
#define PESSOAJURIDICAHPORT

#include "tuxfw.h"
#include "PessoaJuridicapcPORT.h"


class CPessoaJuridica
{
public:
	TPessoaJuridica     tPessoaJuridica;

    CPessoaJuridicapc   clPessoaJuridicapc;

    void setIdPessoa(char *pszIdPessoa);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setIdCFOP(char *pszIdCFOP);
    void setStruct(TPessoaJuridica *ptPessoaJuridica);

    char *getIdPessoa(void);

    CPessoaJuridica(void);

	void inserePessoaJuridica(void);
	void atualizaPessoaJuridica(void);

    bool buscaPessoaJuridica(void);
    bool buscaPessoaJuridica(TPessoaJuridica *ptPessoaJuridicaAUX);
};

#endif
