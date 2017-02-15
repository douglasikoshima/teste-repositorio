///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase LinhaConta
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef LINHACONTAH
#define LINHACONTAH

#include "LinhaContapc.h"
#include <tuxfw.h>

class CLinhaConta:private TuxHelper
{

public:

    TLinhaConta tLinhaConta;

    CLinhaContapc clLinhaContapc;

    CLinhaConta(void);

    void mapeiaLinhaConta(DOMNode *dnode);
    bool buscaLinhaConta(void);
    bool buscaLinhaConta(TLinhaConta *ptLinhaContaAux);
    void insereLinhaConta(void);
    void falsoInsertLinhaConta(void);
    void atualizaLinhaConta(void);
    void apagaLinhaConta(void);

    char *getNrConta(void);
    char *getIdLinhaSistemaOrigem(void);
    char *getIdLinhaConta(void);
    char *getIdPessoaSistemaOrigem(void);
    char *getSqSincronismo(void);

    void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);
    void setIdConta(char *pszIdConta);
    void setIdTipoRelacionamento(char *pszTipoRelacionamento);
    void setDtLinhaConta(char *pszDtLinhaConta);
    void setIdLinhaConta(char *pszIdLinhaConta);
    void setSqSincronismo(char *pszSqSincronismo);
    void setDtExpiracao(char *pszDtExpiracao);
};

#endif
