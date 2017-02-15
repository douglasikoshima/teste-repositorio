


#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"

class cWF_PORECOFIVR : public cWF_Acao
{
    public:
         cWF_PORECOFIVR(DOMNode*, XMLGen* , char *);       // Constructor
         ~cWF_PORECOFIVR(){};                              // Destructor
    public:
        void    Executar(); 
};

