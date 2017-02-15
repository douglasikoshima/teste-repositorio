/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:54 $
 **/

#include <tuxfw.h>

class cWFTabTipoCarteira : public TuxBasicSvc
{
    public:
        cWFTabTipoCarteira() { entrada=0; saida=0; }
        cWFTabTipoCarteira(DOMNode* entrada,XMLGen* saida);
        ~cWFTabTipoCarteira() {}

    public:
        bool PesquisarTabelaTipoCarteira(DOMNode*entrada, XMLGen* saida);
        bool PesquisarTabelaTipoCarteira();

    public:
        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        void carregaDados() {}
} ;
