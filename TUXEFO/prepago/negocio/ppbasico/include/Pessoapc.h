///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase Pessoa
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef PESSOAHPC
#define PESSOAHPC

#include "Global.h"

class CPessoapc
{

public:
    void proCInserePessoa(TPessoa &tPessoa);
    void proCInserePessoaDePara(TPessoaDePara &tPessoaDePara);

    void proCAtualizaPessoa( TPessoa tPessoa );
    bool proCBuscaPessoa(TPessoa &tPessoa);
    bool proCBuscaPessoaDePara( TPessoaDePara &tPessoaDePara );
    bool proCBuscaIdPessoaDePara( TPessoaDePara &tPessoaDePara );
    void proCUnificaPessoa(TPessoa &tPessoa);
    bool proCBuscaPorIdPessoa(TPessoa &tPessoa);
    bool proCBuscaPessoaPorNrGrupo(TPessoa &tPessoa);

};

#endif
