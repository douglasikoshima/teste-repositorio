/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:25 $
 **/

#include <tuxfw.h>
#include "stWFAtendimentoCampoDominio.h"

class cWFAtendimentoCampoDominio
{

	st_AtendimentoCampoDominio	m_stDados;
	st_vlAtendimentoCampoDominio	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoCampoDominio(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();

	private:
		void carregaDados();

};
