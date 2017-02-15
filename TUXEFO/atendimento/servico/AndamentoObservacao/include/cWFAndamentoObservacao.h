/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/

#include<tuxfw.h>

#include "../../AtendimentoGerarXMLDPR/include/stWFAtendimentoGerarXMLDPR.h"
#include "stWFAndamentoObservacao.h"

#ifndef CWFANDAMENTOOBSERVACAO
	#define CWFANDAMENTOOBSERVACAO

class cWFAndamentoObservacao
{
	public:
        cWFAndamentoObservacao();
		cWFAndamentoObservacao(st_AndamentoObservacao* dados, st_vlAndamentoObservacao* indicator, XMLGen*xml_g);
		cWFAndamentoObservacao(DOMNode*dnode, XMLGen*xml_g);
        ~cWFAndamentoObservacao() {}
        int incluir(); // Não usar, mantida apenas para comptabilidade com SSKlunk
		int incluir(XMLDPR *xmlDpr);
		int alterar();
		int excluir();
		void setIdAndamento(long id);
        bool consultarComentarioHist();
		bool consultar();
        bool consultar(st_AndamentoObservacao *dados,st_vlAndamentoObservacao *status
                      ,DOMNode *_entrada,XMLGen *_saida);
	private:
		bool locado;

	private:
		void carregaDados();

	public:
	    st_AndamentoObservacao m_stDados;
	    st_vlAndamentoObservacao m_vlDados;

	    DOMNode* entrada;
	    XMLGen*  saida;

	    TuxHelper tx;
};

#endif

