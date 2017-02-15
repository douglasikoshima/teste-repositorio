/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:02 $
 **/ 

#include <tuxfw.h>
#include "stWFFluxoFuncao.h"

class cWFFluxoFuncao
{

	st_FluxoFuncao	m_stDados;
	st_vlFluxoFuncao	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
        cWFFluxoFuncao() { entrada=0;saida=0; }
		cWFFluxoFuncao(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();
        bool consultar(st_FluxoFuncao* dados,st_vlFluxoFuncao* status,char *order,XMLGen* saida);
	private:
		void carregaDados();

};
