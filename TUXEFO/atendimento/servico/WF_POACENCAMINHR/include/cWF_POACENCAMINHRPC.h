/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:20 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#include "../../WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------
// Declarações
//------------------------------------------------------------------------------
class cWF_POACENCAMINHR : public cWF_Acao
{
    public:
         cWF_POACENCAMINHR(DOMNode*, XMLGen* , char *);
         ~cWF_POACENCAMINHR() {};

    public:
        void    Executar(); 
};
