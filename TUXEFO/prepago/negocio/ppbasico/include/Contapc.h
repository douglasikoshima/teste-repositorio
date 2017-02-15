///////////////////////////////////////////////////////////////////////////////
/**
 * @modulo  Sincronismo
 * @usecase Conta
 * @author  Renato Striitzel Russo
 * @author  Carlos Eduardo Barbosa Braga
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:15 $
 **/
///////////////////////////////////////////////////////////////////////////////

#ifndef CONTAPCH
#define CONTAPCH

class CContapc
{
public:
    bool proCApagaConta(TConta tConta);
    void proCInsereConta(TConta *ptConta);
    bool proCAtualizaConta(TConta tConta);
    bool proCBuscaConta(TConta *ptConta);
    bool proCBuscaContaGrupo(TConta *ptConta);
};

#endif
