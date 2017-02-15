


#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"

class cWF_POACEOFIVR : public cWF_Acao
{
    public:
         cWF_POACEOFIVR(DOMNode*, XMLGen* , char *);       // Constructor
         ~cWF_POACEOFIVR(){};                              // Destructor
    public:
        void    Executar(); 
};

