/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:06 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#include "../../WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------
// Declarações
//------------------------------------------------------------------------------
class cWF_POACNAOACOF : public cWF_Acao
{
    public:
         cWF_POACNAOACOF(DOMNode*, XMLGen* , char *);
         ~cWF_POACNAOACOF() {};

    public:
        void    Executar(); 
};
