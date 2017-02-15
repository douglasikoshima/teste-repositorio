/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:24 $
 **/

#include <tuxfw.h>
#include "../../AndamentoObservacao/include/stWFAndamentoObservacao.h"

class cAndaObs : public TuxBasicSvc
{
    public:
        st_AndamentoObservacao   m_stDados;
        st_vlAndamentoObservacao m_vlDados;

        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    public:
        cAndaObs() {}
        cAndaObs(DOMNode* entrada,XMLGen* saida);
        bool executar(char **codErro,char **msgErro);
} ;
