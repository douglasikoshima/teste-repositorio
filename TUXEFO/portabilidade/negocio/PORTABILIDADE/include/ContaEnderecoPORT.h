#ifndef CONTAENDERECOHPORT
#define CONTAENDERECOHPORT

#include "tuxfw.h"
#include "ContaEnderecoPORT.h"
#include "ContaEnderecopcPORT.h"


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
    char *getIdConta(void);
    char *getIdTipoEnderecoCobranca(void);

};

#endif
