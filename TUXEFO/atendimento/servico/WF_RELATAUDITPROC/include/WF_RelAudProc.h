#include <tuxfw.h>
#define MAX_LENGTH 2048

class WF_RelAudProc : public TuxBasicSvc
{
public:
    // Contrutores e Destrutores
    WF_RelAudProc(DOMNode* entrada, XMLGen* saida);		
    ~WF_RelAudProc(){};
    
    // Outros Métodos Públicos
    int Executar();
    
    // Atributos
    DOMNode* entrada;
    XMLGen* saida;
    TuxHelper tx;
};