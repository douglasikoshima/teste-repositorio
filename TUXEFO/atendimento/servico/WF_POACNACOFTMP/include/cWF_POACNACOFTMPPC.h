/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:04 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#include "../../WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------
// Declarações
//------------------------------------------------------------------------------
class cWF_POACNACOFTMP : public cWF_Acao
{
    public:
         cWF_POACNACOFTMP(DOMNode*, XMLGen* , char *);
         ~cWF_POACNACOFTMP() {};

    public:
        void    Executar(); 
};
