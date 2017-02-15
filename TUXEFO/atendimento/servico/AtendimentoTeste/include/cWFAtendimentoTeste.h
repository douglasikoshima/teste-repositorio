/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:33 $
 **/ 

#include <tuxfw.h>
#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "stWFAtendimentoTeste.h"

class cWFAtendimentoTeste
{

	st_AtendimentoTeste	m_stDados;
	st_vlAtendimentoTeste	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoTeste(DOMNode*dnode, XMLGen*xml_g);
		cWFAtendimentoTeste(){};
        long incluir(XMLDPR *xmlDpr);
        int incluir(); // ** NAO USAR - MANTIDO PARA COMPATIBILIDADE COM O SSKLUNK **
		long incluir(st_AtendimentoTeste* dados, st_vlAtendimentoTeste* status, XMLDPR *xmlDpr);
		bool incluirTesteResposta(st_AtendimentoTeste* dados, st_vlAtendimentoTeste* status, XMLGen* saidaIns);
		int alterar();
		int excluir();
		bool consultar();

	private:
		void carregaDados();

};
