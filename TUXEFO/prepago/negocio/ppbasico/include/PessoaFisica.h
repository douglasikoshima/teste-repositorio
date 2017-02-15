///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaFisica
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOAFISICAH
#define PESSOAFISICAH

#include "tuxfw.h"
#include "PessoaFisicapc.h"


class CPessoaFisica
{
public:
	TPessoaFisica   tPessoaFisica;

    CPessoaFisicapc clPessoaFisicapc;

    void setIdPessoa(char *pszIdPessoa);
    void setIdPais(char *pszIdPais);
    void setIdTratamento(char *pszIdTratamento);
    void setIdUsuarioAlteracao(char *pszIdUsuarioAlteracao);

    void setStruct(TPessoaFisica *ptPessoaFisica);

    CPessoaFisica(void);

	void inserePessoaFisica(void);
	void atualizaPessoaFisica(void);
    bool buscaPessoaFisica(void);
    bool buscaPessoaFisica(TPessoaFisica *ptPessoaFisicaAUX);
};

#endif
