/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Roberto Borges
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:57 $
 **/ 

//------------------------------------------------------------------------------        

#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"

class cWF_EnBKOAcao : public cWF_Acao
{
    public:
         cWF_EnBKOAcao(DOMNode*, XMLGen* , char *);
         ~cWF_EnBKOAcao(){};
    public:
        void    Executar(); 
};

