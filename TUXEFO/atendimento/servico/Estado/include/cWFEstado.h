#include "tuxfw.h"
#include "stWFEstado.h"

class cWFEstado
{

	st_Estado	m_stDados;
	st_vlEstado	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFEstado(st_Estado* dados, st_vlEstado* indicator, XMLGen*xml_g);
		cWFEstado(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();
		bool PesquisaTodos();

	private:
		void carregaDados();

};
