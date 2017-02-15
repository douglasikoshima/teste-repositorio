/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:35:11 $
 **/

#ifndef CWFTABUFOPER
	#define CWFTABUFOPER

#include <tuxfw.h>

class cWFTabUFOper
{
	public:
        cWFTabUFOper() { entrada=0; saida=0; };
		cWFTabUFOper(DOMNode*dnode, XMLGen*xml_g);

        bool PesquisarTabelaUFRegional(DOMNode*entrada,XMLGen* saida);
        bool PesquisarTabelaUFRegional();
        bool PesquisarOperadoras(DOMNode*entrada,XMLGen* saida);
        bool PesquisarOperadoras();

	public:
	    DOMNode* entrada;
	    XMLGen*  saida;

	    TuxHelper tx;

	private:
        void carregaDados() {}
};

#endif
