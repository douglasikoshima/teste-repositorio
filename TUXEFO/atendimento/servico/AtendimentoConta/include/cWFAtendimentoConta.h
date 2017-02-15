/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/

#include <tuxfw.h>
#include "stWFAtendimentoConta.h"

class cWFAtendimentoConta {

	st_AtendimentoConta*	m_stDados;
	st_vlAtendimentoConta*	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoConta(st_AtendimentoConta* dados, st_vlAtendimentoConta* indicator, XMLGen*xml_g);
		cWFAtendimentoConta(DOMNode*dnode, XMLGen*xml_g);
        cWFAtendimentoConta() {entrada=0,saida=0;m_stDados=0;m_vlDados=0;}
		~cWFAtendimentoConta();
		long incluir();
		int alterar();
		int excluir();
		bool consultar();
		bool consultarEx();
        bool obterAtendimentoConta(long idAtendimento,AtendimentoConta *atdConta);

	private:
		bool locado;

		void carregaDados();
};
