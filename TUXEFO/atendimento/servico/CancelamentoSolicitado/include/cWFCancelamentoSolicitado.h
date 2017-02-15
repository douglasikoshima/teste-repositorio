/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:47 $
 **/ 

#ifndef __CWF_CANCELAMENTOSOLICITADO_H__
#define __CWF_CANCELAMENTOSOLICITADO_H__

#include <tuxfw.h>
#include "stWFCancelamentoSolicitado.h"

class cWFCancelamentoSolicitado
{

	st_CancelamentoSolicitado	m_stDados;
	st_vlCancelamentoSolicitado	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFCancelamentoSolicitado(DOMNode*dnode, XMLGen*xml_g);
        cWFCancelamentoSolicitado(st_CancelamentoSolicitado* dados, st_vlCancelamentoSolicitado* status, XMLGen*xml_g);
		bool incluir();
		int alterar();
		int excluir();
		bool consultar();

	private:
		void carregaDados();

};

#endif
