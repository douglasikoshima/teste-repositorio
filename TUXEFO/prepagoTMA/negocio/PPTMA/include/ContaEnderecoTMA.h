#ifndef CONTAENDERECOHTMA
#define CONTAENDERECOHTMA

#include "tuxfw.h"
#include "ContaEnderecoTMA.h"
#include "ContaEnderecopcTMA.h"


class CContaEndereco
{
public:
	TContaEndereco      tContaEndereco;
	CContaEnderecopc    clContaEnderecopc;

	CContaEndereco(void);

    bool buscaContaEndereco(void);
    bool buscaContaEndereco(TContaEndereco *ptContaEndereco);

    void insereContaEndereco(void);
    void atualizaContaEndereco(void);

    void setIdConta(char *pszIdConta);
    void setIdPessoaEndereco(char *pszIdPessoaEndereco);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setIdContaEndereco(char *pszIdContaEndereco);
    void setIdTipoEnderecoCobranca(char *pszIdTipoEnderecoCobranca);

    char *getIdPessoaEndereco(void);
    char *getIdContaEndereco(void);
};

#endif
