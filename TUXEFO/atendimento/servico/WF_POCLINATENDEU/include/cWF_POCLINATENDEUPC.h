/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:39 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#include "../../WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------
// Declarações
//------------------------------------------------------------------------------
class cWF_POCLINATENDEU : public cWF_Acao
{
    public:
         cWF_POCLINATENDEU(DOMNode*, XMLGen* , char *);
         ~cWF_POCLINATENDEU() {};

    public:
        void Executar();
};
