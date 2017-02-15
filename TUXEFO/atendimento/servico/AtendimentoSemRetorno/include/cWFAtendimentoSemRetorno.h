/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:32 $
 **/ 

#include <tuxfw.h>
#include "stWFAtendimentoSemRetorno.h"

class cWFAtendimentoSemRetorno
{

	st_AtendimentoSemRetorno	m_stDados;
	st_vlAtendimentoSemRetorno	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoSemRetorno(DOMNode*dnode, XMLGen*xml_g);
		bool incluir();
		int excluir();
		bool consultar();

	private:
		void carregaDados();

};
