#ifndef PESSOAVALORPOSSIVELHPORT
#define PESSOAVALORPOSSIVELHPORT

#include "PessoaValorPossivelpcPORT.h"


class CPessoaValorPossivel
{
public:
	TPessoaValorPossivel        tPessoaValorPossivel;
    CPessoaValorPossivelpc      clPessoaValorPossivelpc;

    CPessoaValorPossivel(void);

    void apagaEscolaridade(void);
    void apagaOcupacao(void);
    void inserePessoaValorPossivel(void);

    void setIdPessoa(char *pszIdPessoa);
    void setIdValorPossivel(char *pszIdValorPossivel);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    char *getIdPessoaValorPossivel(void);

    void setStruct(TPessoaValorPossivel *ptPessoaValorPossivelTmp);
    void limpaPessoaValorPossivel(void);
};

#endif
