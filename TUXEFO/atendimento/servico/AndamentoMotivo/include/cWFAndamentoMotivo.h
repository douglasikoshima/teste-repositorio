/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.3 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:17 $
 **/


#ifndef CWFANDAMENTOMOTIVO
    #define CWFANDAMENTOMOTIVO

#include<tuxfw.h>
#include "stWFAndamentoMotivo.h"

class cWFAndamentoMotivo
{

	st_AndamentoMotivo	m_stDados;
	st_vlAndamentoMotivo	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		void setIdAndamento(long id);
		cWFAndamentoMotivo(DOMNode*dnode, XMLGen*xml_g);
        cWFAndamentoMotivo(st_AndamentoMotivo* dados, st_vlAndamentoMotivo* status, XMLGen*xml_g);		bool incluir();
		int alterar();
		int excluir();
		bool consultar();
		int obterMotivoCancelamento(int idAgrupamentoEstadoTpProc);

	private:
		void carregaDados();

};

#endif
