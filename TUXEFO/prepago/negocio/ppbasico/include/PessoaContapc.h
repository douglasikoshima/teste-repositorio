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

#ifndef PESSOA_CONTA_PC
#define PESSOA_CONTA_PC

class CPessoaContapc
{

public:
    void proCAtualizaPessoaConta(TPessoaConta tPessoaConta);
    void proCInserePessoaConta(TPessoaConta *ptPessoaConta);
    bool proCBuscaPessoaConta(TPessoaConta *ptPessoaConta);
    void proCApagaPessoaConta(TPessoaConta tPessoaConta);
};

#endif
