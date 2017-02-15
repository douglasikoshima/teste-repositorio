/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/

#ifndef CWFANDAMENTOTRANSFERENCIA
    #define CWFANDAMENTOTRANSFERENCIA

#include<tuxfw.h>
#include "stWFAndamentoTransferencia.h"

class cWFAndamentoTransferencia
{

	st_AndamentoTransferencia	m_stDados;
	st_vlAndamentoTransferencia	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
        cWFAndamentoTransferencia() {}
		cWFAndamentoTransferencia(DOMNode*dnode, XMLGen*xml_g);
		cWFAndamentoTransferencia(st_AndamentoTransferencia* , st_vlAndamentoTransferencia* , XMLGen*);
        ~cWFAndamentoTransferencia() {}

		bool incluir();
		bool consultar();
		int alterar();
		int excluir();
        long obtemTrf(long idAtendimento,XMLGen* saida);
        long obtemTrf();

		void setIdAndamento(long id);

	private:
		void carregaDados();	

};

#endif