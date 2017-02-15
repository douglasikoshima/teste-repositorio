/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:33 $
 **/

#ifndef CWFATENDIMENTOSUSPEITO
    #define CWFATENDIMENTOSUSPEITO

#include <tuxfw.h>
#include "stWFAtendimentoSuspeito.h"

class cWFAtendimentoSuspeito
{

	st_AtendimentoSuspeito	m_stDados;
	st_vlAtendimentoSuspeito	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoSuspeito(DOMNode*dnode, XMLGen*xml_g);
        cWFAtendimentoSuspeito(st_AtendimentoSuspeito* dados, st_vlAtendimentoSuspeito* status, XMLGen*xml_g);
		long incluir();
		int alterar();
		int excluir();
		bool consultar();
		int obterUsuarioSuspeito();

	private:
		void carregaDados();

};

#endif