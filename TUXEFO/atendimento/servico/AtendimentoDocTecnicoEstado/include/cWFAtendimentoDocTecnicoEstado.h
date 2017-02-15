
#include <tuxfw.h>
#include "stWFAtendimentoDocTecnicoEstado.h"

class cWFAtendimentoDocTecnicoEstado
{

	st_AtendimentoDocTecnicoEstado	m_stDados;
	st_vlAtendimentoDocTecnicoEstado	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoDocTecnicoEstado(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();

	private:
		void carregaDados();

};
