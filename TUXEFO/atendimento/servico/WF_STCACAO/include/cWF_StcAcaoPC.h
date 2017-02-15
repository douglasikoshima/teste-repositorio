/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Roberto Borges
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:29 $
 **/ 

//------------------------------------------------------------------------------        
#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------        
// cWF_Encaminham 
//------------------------------------------------------------------------------        

class cWF_SolicitaCancelamento : public cWF_Acao
{
    public:
        //------------------------------------------------------------------------------        
         cWF_SolicitaCancelamento(DOMNode*, XMLGen* , char *);      // Constructor
         ~cWF_SolicitaCancelamento(){};                             // Destructor
        //------------------------------------------------------------------------------        
    public:
        //------------------------------------------------------------------------------        
        void    Executar(); 
        //------------------------------------------------------------------------------        
};

