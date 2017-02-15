///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase LinhaConta
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef LINHACONTAH
#define LINHACONTAH

#include "LinhaContapc.h"
#include "tuxfw.h"

class CLinhaConta:private TuxHelper
{

public:

    TLinhaConta tLinhaConta;

    CLinhaContapc clLinhaContapc;

    CLinhaConta(void);

    bool buscaLinhaConta(void);
    bool buscaLinhaConta(TLinhaConta *ptLinhaContaAux);
	//Busca idConta, dado idLinhaTelefonica. Somente para relacionamento de cliente
    bool buscaLinhaContaCliente(void);
    void insereLinhaConta(void);
    void falsoInsertLinhaConta(void);
    void atualizaLinhaConta(void);
    void apagaLinhaConta(void);

    void desvinculaContaGrupo(void);

    char *getIdLinhaConta(void);
    char *getSqSincronismo(void);
    char *getIdConta(void);
    char *getIdLinhaTelefonica(void);

    void setIdLinhaTelefonica(char *pszIdLinhaTelefonica);
    void setIdConta(char *pszIdConta);
    void setIdTipoRelacionamento(char *pszTipoRelacionamento);
    void setDtLinhaConta(char *pszDtLinhaConta);
    void setIdLinhaConta(char *pszIdLinhaConta);
    void setSqSincronismo(char *pszSqSincronismo);
    void setTsSincronismo(char *pszTsSincronismo);
    void setDtExpiracao(char *pszDtExpiracao);
    void setInLinhaMaster(char *pszInLinhaMaster);

    void setStruct(TLinhaConta *ptLinhaConta);
};

#endif
