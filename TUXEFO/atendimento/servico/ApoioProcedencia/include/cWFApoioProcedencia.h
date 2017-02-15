
#include <tuxfw.h>
#include "stWFApoioProcedencia.h"

class cWFApoioProcedencia
{

	st_ApoioProcedencia	m_stDados;
	st_vlApoioProcedencia	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFApoioProcedencia(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();

	private:
		void carregaDados();

};
