/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:09 $
 **/


#ifndef __CWFAGLACAO_H__
#define __CWFAGLACAO_H__

//------------------------------------------------------------------------------        
#include "../../../../atendimento/servico/WF_ACAO/include/cWF_AcaoPC.h"

//------------------------------------------------------------------------------        
class cWF_AglAcao : public cWF_Acao
{
    public:
         cWF_AglAcao(DOMNode*,XMLGen*,char *);
         ~cWF_AglAcao() {};

    public:
        void Executar();

    private:
        void carregaDados(DOMNode* entrada);
};

#endif // __CWFAGLACAO_H__
