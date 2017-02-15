/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2010/02/05 13:09:33 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#include "../../WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------
// Declarações
//------------------------------------------------------------------------------
class cWF_ATDCANOVACAO : public cWF_Acao
{
    public:
         cWF_ATDCANOVACAO(DOMNode*, XMLGen* , char *);
         ~cWF_ATDCANOVACAO() {};

    public:
        void    Executar(); 
};
