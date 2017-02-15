/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:51 $
 **/

#include <tuxfw.h>
#include "stWFAgrupEstadoAtividade.h"

class cWFAgrupamentoEstadoAtividade
{

	st_AgrupamentoEstadoAtividade	m_stDados;
	st_vlAgrupamentoEstadoAtividade	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAgrupamentoEstadoAtividade(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();

	private:
		void carregaDados();

};
