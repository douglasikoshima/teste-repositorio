/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:39 $
 **/ 

#include <tuxfw.h>
#include "stWFAtendimentoPesquisaURA.h"

class cWFAtendimentoPesquisaURA
{

	st_AtendimentoPesquisaURA	m_stDados;
	st_vlAtendimentoPesquisaURA	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoPesquisaURA(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();

	private:
		void carregaDados();
};
