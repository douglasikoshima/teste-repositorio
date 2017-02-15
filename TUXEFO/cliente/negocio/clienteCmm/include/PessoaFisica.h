///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  PrePAgo
 * @usecase PessoaFisica
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @author  Roberto Borges dos Santos
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:26 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOAFISICAH
#define PESSOAFISICAH

#include <tuxfw.h>
#include "PessoaFisicapc.h"

class CPessoaFisica
{
public:
	TPessoaFisica   tPessoaFisica;
    CPessoaFisicapc clPessoaFisicapc;
    void setIdPessoa(char *pszIdPessoa );
    CPessoaFisica(void);

    void mapeiaPessoaFisica(void);
	void inserePessoaFisica(void);
	void atualizaPessoaFisica(void);
    bool buscaPessoaFisica(void);
    bool buscaPessoaFisica(TPessoaFisica *ptPessoaFisicaAUX);
	void setData( TPessoaFisicaXML ); 
	void getData( TPessoaFisicaXML *xmlp); 
};

#endif
