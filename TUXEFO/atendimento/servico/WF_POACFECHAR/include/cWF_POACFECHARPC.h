/**
 * 
 * @modulo  Atendimento
 * @usecase Atendimento
 * @author  Cassio
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/ 

//------------------------------------------------------------------------------
// Includes
//------------------------------------------------------------------------------

#include "../../WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------
// Declarações
//------------------------------------------------------------------------------
class cWF_POACFECHAR : public cWF_Acao
{
    public:
         cWF_POACFECHAR(DOMNode*, XMLGen* , char *);
         ~cWF_POACFECHAR() {};

    public:
        void Executar();

    private:
        bool RegistrarContato(XMLGen *pXMLGen,TString &statusText,TString &idAtendimentoProtocolo);
};
