/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:09 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#include "../../WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------
// Declarações
//------------------------------------------------------------------------------
class cWF_POACINIRETE : public cWF_Acao
{
    public:
         cWF_POACINIRETE(DOMNode*, XMLGen* , char *);
         ~cWF_POACINIRETE() {};

    public:
        void    Executar(); 
};
