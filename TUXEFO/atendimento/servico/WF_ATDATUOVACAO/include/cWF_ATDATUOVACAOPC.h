/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2010/02/05 13:09:33 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#include "../../WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------
// Declara��es
//------------------------------------------------------------------------------
class cWF_ATDATUOVACAO : public cWF_Acao
{
    public:
         cWF_ATDATUOVACAO(DOMNode*, XMLGen* , char *);
         ~cWF_ATDATUOVACAO() {};

    public:
        void    Executar(); 
};