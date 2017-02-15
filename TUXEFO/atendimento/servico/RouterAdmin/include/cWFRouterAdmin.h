/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:18 $
 **/ 

#include <tuxfw.h>
#include "stWFRouterAdmin.h"

class cWFRouterAdmin
{
	st_RouterAdmin		m_stDados;
	st_vlRouterAdmin	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFRouterAdmin(DOMNode*dnode, XMLGen*xml_g);
        ~cWFRouterAdmin() { if ( m_stDados.vlScriptSource ) XMLString::release(&m_stDados.vlScriptSource); }

		bool incluir();
		bool alterar();

	private:
		void carregaDados();
};
