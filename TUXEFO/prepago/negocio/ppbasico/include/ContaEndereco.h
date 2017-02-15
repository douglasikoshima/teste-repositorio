///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase ContaEndereco
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef CONTAENDERECOH
#define CONTAENDERECOH

#include "tuxfw.h"
#include "ContaEndereco.h"
#include "ContaEnderecopc.h"


class CContaEndereco
{
public:
	TContaEndereco      tContaEndereco;

	CContaEnderecopc    clContaEnderecopc;

	CContaEndereco(void);

    bool buscaContaEnderecoAntigo(void);

    bool buscaContaEndereco(void);
    bool buscaContaEndereco(TContaEndereco *ptContaEndereco);

    void insereContaEndereco(void);
    void atualizaContaEndereco(void);
    void apagaContaEndereco(void);

    void setIdConta(char *pszIdConta);
    void setIdPessoaEndereco(char *pszIdPessoaEndereco);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setIdContaEndereco(char *pszIdContaEndereco);
    void setIdTipoEnderecoCobranca(char *pszIdTipoEnderecoCobranca);

    char *getIdPessoaEndereco(void);
};

#endif
