/**
 * @author  David Ramos Dominguez
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:13 $
 **/

/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Roberto Borges
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:13 $
 **/ 

//------------------------------------------------------------------------------        

#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------        
// cWF_CERRAMERET 
//------------------------------------------------------------------------------        

class cWF_CERRAMERET : public cWF_Acao
{
    public:
        //------------------------------------------------------------------------------        
         cWF_CERRAMERET(DOMNode*, XMLGen* , char *);       // Constructor
         ~cWF_CERRAMERET(){};                              // Destructor
        //------------------------------------------------------------------------------        
    public:
		//------------------------------------------------------------------------------        
        void    Executar(); 
        //------------------------------------------------------------------------------        
    protected: 
        //------------------------------------------------------------------------------        

};

