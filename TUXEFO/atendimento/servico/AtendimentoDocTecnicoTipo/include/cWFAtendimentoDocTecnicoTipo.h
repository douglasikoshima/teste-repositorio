
#include <tuxfw.h>
#include "stWFAtendimentoDocTecnicoTipo.h"

class cWFAtendimentoDocTecnicoTipo
{

	st_AtendimentoDocTecnicoTipo	m_stDados;
	st_vlAtendimentoDocTecnicoTipo	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoDocTecnicoTipo(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();

	private:
		void carregaDados();

};
