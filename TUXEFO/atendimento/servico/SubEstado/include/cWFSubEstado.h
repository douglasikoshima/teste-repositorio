#include <tuxfw.h>
#include "stWFSubEstado.h"

class cWFSubEstado
{

	st_WFSubEstado		m_stDados;
	st_vlWFSubEstado	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFSubEstado(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int  alterar();
		int excluir();
		bool consultar();
		int consultarEstado();

	private:
		void carregaDados();

};
