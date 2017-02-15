///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaConta
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOA_CONTAH
#define PESSOA_CONTAH

#include "Global.h"
#include "PessoaContapc.h"

class CPessoaConta
{
public:
    TPessoaConta    tPessoaConta;

    CPessoaContapc  clPessoaContapc;

    CPessoaConta(void);

    bool buscaPessoaConta(void);
    bool buscaPessoaConta(TPessoaConta *ptPessoaContaAux);
    void atualizaPessoaConta(void);
    void inserePessoaConta(void);
    void apagaPessoaConta(void);

    void setIdConta(char *pszIdConta);
    void setIdTipoRelacionamento(char *pszIdTipoRelacionamento);
    void setIdPessoaDePara(char *pszIdPessoaDePara);
    void setDtPessoaConta(char *pszDtPessoaConta);
    void setIdPessoaConta(char *pszIdPessoaConta);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    char *getIdPessoaDePara(void);
};

#endif