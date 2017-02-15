/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:33 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#include "../../WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------
// Declarações
//------------------------------------------------------------------------------
class cWF_POACDEVFILA : public cWF_Acao
{
    public:
         cWF_POACDEVFILA(DOMNode*, XMLGen* , char *);
         ~cWF_POACDEVFILA() {};

    public:
        void    Executar(); 
};
