/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:59 $
 **/

#include <tuxfw.h>
#include "stWFAtendimentoDocumento.h"

class cWFAtendimentoDocumento
{
	st_AtendimentoDocumento	m_stDados;
	st_vlAtendimentoDocumento	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoDocumento(DOMNode*dnode,XMLGen*xml_g);
		cWFAtendimentoDocumento();

		bool incluir();
		bool incluir(st_AtendimentoDocumento *dados,st_vlAtendimentoDocumento *status,XMLGen *xml_g);

		int alterar();
		int excluir();
		bool consultar();

	private:
		void carregaDados();

};
