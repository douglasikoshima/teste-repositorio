/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:59 $
 **/

#include <tuxfw.h>
#include "stWFAtendimentoRelIndResumo.h"

class cWFAtdRelRes : public TuxBasicSvc
{
    public:
        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    public:
        cWFAtdRelRes(DOMNode* entrada,XMLGen* saida);
        bool Executar();

    private:
        void carregaDados() {}
} ;

