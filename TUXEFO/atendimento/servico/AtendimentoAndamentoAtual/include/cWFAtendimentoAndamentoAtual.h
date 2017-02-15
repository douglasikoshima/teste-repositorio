/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/

#ifndef CWFATENDIMENTOANDAMENTOATUAL
	#define CWFATENDIMENTOANDAMENTOATUAL

#include <tuxfw.h>
#include "stWFAtendimentoAndamentoAtual.h"

class cWFAtendimentoAndamentoAtual
{

	st_AtendimentoAndamentoAtual m_stDados;
	st_vlAtendimentoAndamentoAtual m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoAndamentoAtual(st_AtendimentoAndamentoAtual* dados, st_vlAtendimentoAndamentoAtual* indicator, XMLGen*xml_g);
		cWFAtendimentoAndamentoAtual(DOMNode*dnode, XMLGen*xml_g);
        ~cWFAtendimentoAndamentoAtual() {}
		long incluir();
		int alterar();
		int excluir();
		bool consultar();
		void setIdAndamento(long id);

	private:
		void carregaDados();

};

#endif