/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:42 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#include "../../WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------
// Declarações
//------------------------------------------------------------------------------
class cWF_POALTDADOSPRO : public cWF_Acao
{
    public:
         cWF_POALTDADOSPRO(DOMNode*, XMLGen* , char *);
         ~cWF_POALTDADOSPRO() {};

    public:
        void Executar();
};
