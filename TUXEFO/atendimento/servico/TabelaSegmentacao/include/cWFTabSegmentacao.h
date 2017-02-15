/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:53 $
 **/

#include <tuxfw.h>

class cWFTabSegment : public TuxBasicSvc
{
    public:
        cWFTabSegment() { entrada=0; saida=0; }
        cWFTabSegment(DOMNode* entrada,XMLGen* saida);
        ~cWFTabSegment() {}

    public:
        bool PesquisarTabelaSegmentacao(DOMNode*entrada, XMLGen* saida);
        bool PesquisarTabelaSegmentacao();

    public:
        DOMNode* entrada;
        XMLGen*  saida;

        TuxHelper tx;

    private:
        void carregaDados() {}
} ;
