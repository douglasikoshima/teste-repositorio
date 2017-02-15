///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase PessoaJuridica
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOAJURIDICA
#define PESSOAJURIDICA

#include <tuxfw.h>
#include "PessoaJuridicapc.h"


class CPessoaJuridica : private TuxHelper
{
public:
	TPessoaJuridica     tPessoaJuridica;
	TPessoaJuridicaXML	tPessoaJuridicaXML; 
    CPessoaJuridicapc   clPessoaJuridicapc;

    void setIdPessoa(char *pszIdPessoa );

    CPessoaJuridica(void);

    void SetData(TPessoaJuridicaXML); 
	void inserePessoaJuridica(void);
	void atualizaPessoaJuridica(void);

    bool buscaPessoaJuridica(void);
    bool buscaPessoaJuridica(TPessoaJuridica *ptPessoaJuridicaAUX);
};

#endif
