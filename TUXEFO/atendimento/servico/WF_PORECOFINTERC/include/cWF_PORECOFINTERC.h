


#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"

class cWF_PORECOFINTERC : public cWF_Acao
{
    public:
         cWF_PORECOFINTERC(DOMNode*, XMLGen* , char *);       // Constructor
         ~cWF_PORECOFINTERC(){};                              // Destructor
    public:
        void    Executar(); 
};

