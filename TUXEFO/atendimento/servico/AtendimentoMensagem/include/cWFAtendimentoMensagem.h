/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:47 $
 **/ 

#include <tuxfw.h>
#include "stWFAtendimentoMensagem.h"

#ifndef CWFATENDIMENTOMENSAGEM
    #define CWFATENDIMENTOMENSAGEM

class cWFAtendimentoMensagem
{

	st_AtendimentoMensagem	m_stDados;
	st_vlAtendimentoMensagem	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoMensagem(DOMNode*dnode, XMLGen*xml_g);
		cWFAtendimentoMensagem(st_AtendimentoMensagem* , st_vlAtendimentoMensagem* , XMLGen* );
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();
		int obterMensagem();
		int obterAlertaDinamica();

	private:
		void carregaDados();

};

#endif