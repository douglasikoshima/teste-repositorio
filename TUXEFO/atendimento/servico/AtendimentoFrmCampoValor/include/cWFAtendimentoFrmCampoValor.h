/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:47 $
 **/

#include <tuxfw.h>
#include "stWFAtendimentoFrmCampoValor.h"

class cWFAtendimentoFrmCampoValor
{

	st_AtendimentoFrmCampoValor	m_stDados;
	st_vlAtendimentoFrmCampoValor	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoFrmCampoValor(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();
		bool ObtemAtdFrmCpVl();
		bool ObtemAtdFrmCpVlEx();

	private:
		void carregaDados();

};
