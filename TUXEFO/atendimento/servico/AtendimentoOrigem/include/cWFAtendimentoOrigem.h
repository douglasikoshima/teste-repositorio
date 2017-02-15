/**
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:31 $
 **/ 

#include <tuxfw.h>

#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "stWFAtendimentoOrigem.h"

class cWFAtendimentoOrigem
{

	st_AtendimentoOrigem*	m_stDados;
	st_vlAtendimentoOrigem*	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAtendimentoOrigem(st_AtendimentoOrigem* dados, st_vlAtendimentoOrigem* indicator, XMLGen*xml_g);
		cWFAtendimentoOrigem(DOMNode*dnode, XMLGen*xml_g);
		cWFAtendimentoOrigem();
		~cWFAtendimentoOrigem();
		long incluir(); // Não usar! Mantida apenas para compatibilidade com SSKlunk
		long incluir(XMLDPR *xmlDpr);
		long alterar();
		long excluir();
		bool consultar();
		long ObterAtdOrigem(long sIdAtendimento);
        long ObterAtdOrigemEx(long sIdAtendimento);

	private:
		bool locado;

		void carregaDados();

};
