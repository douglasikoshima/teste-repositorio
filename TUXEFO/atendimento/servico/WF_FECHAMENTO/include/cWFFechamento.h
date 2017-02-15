
#ifndef __CWF_FECHAMENTO_H__
#define __CWF_FECHAMENTO_H__

#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------        
// cWF_CERRAMEFEC 
//------------------------------------------------------------------------------        

class cWFFechamento : public cWF_Acao
{
    public:
         cWFFechamento(DOMNode*, XMLGen* , char *);
         cWFFechamento(XMLGen*xml_g);
         cWFFechamento() {}
         ~cWFFechamento() {};

    public:
        void Executar();
        void Execute();

    private:
        XMLGen *saida;

};

#endif
