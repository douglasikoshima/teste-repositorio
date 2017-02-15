/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Marcelo Nunes
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:07 $
 **/ 

//------------------------------------------------------------------------------        

#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"

class cWF_POACEOFINTERC : public cWF_Acao
{
    public:
         cWF_POACEOFINTERC(DOMNode*, XMLGen* , char *);       // Constructor
         ~cWF_POACEOFINTERC(){};                              // Destructor
    public:
        void    Executar(); 
};

