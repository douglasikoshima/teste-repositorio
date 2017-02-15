/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:22 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#include "../../WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------
// Declarações
//------------------------------------------------------------------------------
class cWF_POACEFETIVAR : public cWF_Acao
{
    public:
         cWF_POACEFETIVAR(DOMNode*, XMLGen* , char *);
         ~cWF_POACEFETIVAR() {};

    public:
        void    Executar(); 
};
