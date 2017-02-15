
#ifndef CWFATENDIMENTOFILA
	#define CWFATENDIMENTOFILA

#include <tuxfw.h>
#include "../../Atendimento/include/stWFAtendimento.h"
#include "../include/stWFAtendimentoFila.h"
#include "../../../commons/Collection/include/Collection.h"

class cWFAtendimentoFila
{
	st_Atendimento			m_stDados;
	st_vlAtendimento		m_vlDados;

	st_AtendimentoFila		m_stFila;
	st_vlAtendimentoFila	m_vlFila;

    	DadosAtendimento dadosAtendimento;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
        cWFAtendimentoFila();
        ~cWFAtendimentoFila();
	cWFAtendimentoFila(st_Atendimento* dados, st_vlAtendimento* indicator, XMLGen*xml_g);
	cWFAtendimentoFila(DOMNode*dnode, XMLGen*xml_g);
		bool consultarFila();
		bool consultarFila(int idPessoaUsuario);
		bool consultarFila(Collection* _grupos);
 		bool consultarFila(Collection* _grupos, int idPessoaUsuario);
	       	bool consultarBox();
		bool consultarBox(int idPessoaUsuario);
		bool consultarRelacionamento();
		bool consultarMassa();

	private:
		void carregaDados();
		void carregaDadosConsultaFila();
};

#endif
