/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:30 $
 **/

#include <tuxfw.h>
#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "stWFAtendimentoContatoComunic.h"

class cWFAtendimentoContatoComunic
{

	st_AtendimentoContatoComunic*	m_stDados;
	st_vlAtendimentoContatoComunic*	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoContatoComunic(st_AtendimentoContatoComunic* dados, st_vlAtendimentoContatoComunic* indicator, XMLGen*xml_g);
		cWFAtendimentoContatoComunic(DOMNode*dnode, XMLGen*xml_g);
		~cWFAtendimentoContatoComunic();

        int incluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
        long incluir(XMLDPR *xmlDpr);
		int alterar();
		int excluir();
		bool consultar();
		bool obtemWFAtdCnC();
		bool obtemWFAtdCnCEx();

	private:
		bool locado;

		void carregaDados();

};
