/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:49 $
 **/

#include <tuxfw.h>

class cWFTabTipoSequencia : public TuxBasicSvc
{
    public:
        cWFTabTipoSequencia() { entrada=0; saida=0; }
        cWFTabTipoSequencia(DOMNode* entrada,XMLGen* saida);
        ~cWFTabTipoSequencia() {}

    public:
        bool PesquisarTabelaTipoSequencia(DOMNode*entrada, XMLGen* saida);
        bool PesquisarTabelaTipoSequencia();

    public:
        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        void carregaDados() {}
} ;
