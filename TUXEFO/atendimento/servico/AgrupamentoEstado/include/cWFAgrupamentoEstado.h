#include <tuxfw.h>
#include "stWFAgrupamentoEstado.h"

class cWFAgrupamentoEstado
{

	st_WFAgrupamentoEstado		m_stDados;
	st_vlWFAgrupamentoEstado	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAgrupamentoEstado(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();
		bool consultarDetalhado();

	private:
		void carregaDados();

};
