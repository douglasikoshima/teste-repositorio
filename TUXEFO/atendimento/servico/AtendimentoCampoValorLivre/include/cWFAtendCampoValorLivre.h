/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:28 $
 **/

#include <tuxfw.h>
#include "stWFAtendCampoValorLivre.h"

class cWFAtendimentoCampoValorLivre
{

	st_AtendimentoCampoValorLivre	m_stDados;
	st_vlAtendimentoCampoValorLivre	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoCampoValorLivre(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();

	private:
		void carregaDados();

};
