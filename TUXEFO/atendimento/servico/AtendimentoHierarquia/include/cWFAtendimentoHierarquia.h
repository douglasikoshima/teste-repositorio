/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:31 $
 **/ 

#include <tuxfw.h>
#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "stWFAtendimentoHierarquia.h"

class cWFAtendimentoHierarquia
{

	st_AtendimentoHierarquia	m_stDados;
	st_vlAtendimentoHierarquia	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoHierarquia(DOMNode*dnode, XMLGen*xml_g);
        int incluir(XMLDPR *xmlDpr);
        int incluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
		int alterar();
		long excluir();
		bool consultar();

	private:
		void carregaDados();

};
