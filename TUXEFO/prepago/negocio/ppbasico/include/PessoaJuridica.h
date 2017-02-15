///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaJuridica
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOAJURIDICAH
#define PESSOAJURIDICAH

#include "tuxfw.h"
#include "PessoaJuridicapc.h"


class CPessoaJuridica
{
public:
	TPessoaJuridica     tPessoaJuridica;

    CPessoaJuridicapc   clPessoaJuridicapc;

    void setIdPessoa(char *pszIdPessoa);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);
    void setStruct(TPessoaJuridica *ptPessoaJuridica);

    CPessoaJuridica(void);

	void inserePessoaJuridica(void);
	void atualizaPessoaJuridica(void);

    bool buscaPessoaJuridica(void);
    bool buscaPessoaJuridica(TPessoaJuridica *ptPessoaJuridicaAUX);
};

#endif
