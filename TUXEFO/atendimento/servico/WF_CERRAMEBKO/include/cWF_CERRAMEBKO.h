/**
 * @author  David Ramos Dominguez
 * @version $Revision: 1.1.2.2 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:16 $
 **/

#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------        
// cWF_CERRAMERET 
//------------------------------------------------------------------------------        

class cWF_CERRAMEBKO : public cWF_Acao
{
    public:
        //------------------------------------------------------------------------------        
         cWF_CERRAMEBKO(DOMNode*, XMLGen* , char *);       // Constructor
         ~cWF_CERRAMEBKO(){};                              // Destructor
        //------------------------------------------------------------------------------        
    public:
		//------------------------------------------------------------------------------        
        void    Executar(); 
        //void    CarregaDadosFormulario(DOMNode *dnode);
        //void    gravaAtendimentoFormularioDinamico(long idAtendimento);
        //Collection formularioDinamico;

        //------------------------------------------------------------------------------        
    protected: 
        //------------------------------------------------------------------------------        

};

