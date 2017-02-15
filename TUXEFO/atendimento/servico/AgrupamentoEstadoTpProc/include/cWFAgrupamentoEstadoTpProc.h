/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:48 $
 **/

#ifndef CWFAGRUPAMENTOESTADOTPPROC
	#define CWFAGRUPAMENTOESTADOTPPROC

#include <tuxfw.h>
#include "stWFAgrupamentoEstadoTpProc.h"

class cWFAgrupamentoEstadoTpProc
{

	st_AgrupamentoEstadoTpProc		m_stDados;
	st_vlAgrupamentoEstadoTpProc	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFAgrupamentoEstadoTpProc(st_AgrupamentoEstadoTpProc* dados, st_vlAgrupamentoEstadoTpProc* indicador);
		cWFAgrupamentoEstadoTpProc(DOMNode*dnode, XMLGen*xml_g);
		~cWFAgrupamentoEstadoTpProc();
		bool incluir();
		int  alterar();
		int  excluir();
		bool consultar();
		int  proximoAgrupamento(bool agrupDeveSerDiferente=false);

	private:
		bool locado;
		void carregaDados();

};

#endif