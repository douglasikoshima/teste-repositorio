///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePago
 * @usecase PessoaValorPossivel
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOAVALORPOSSIVELH
#define PESSOAVALORPOSSIVELH

#include "tuxfw.h"
#include "PessoaValorPossivelpc.h"


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

    void setStruct(TPessoaValorPossivel *ptPessoaValorPossivelTmp);
    void limpaPessoaValorPossivel(void);
};

#endif
