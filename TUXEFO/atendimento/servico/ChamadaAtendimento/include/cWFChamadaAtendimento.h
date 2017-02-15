/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:34 $
 **/ 

#include <tuxfw.h>
#include "stWFChamadaAtendimento.h"

class cWFChamadaAtendimento
{
	st_ChamadaAtendimento*	    m_stDados;
	st_vlChamadaAtendimento*	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
        cWFChamadaAtendimento() { entrada=0;saida=0;locado=false; }
		cWFChamadaAtendimento(st_ChamadaAtendimento* dados, st_vlChamadaAtendimento* status, XMLGen*xml_g);
		cWFChamadaAtendimento(DOMNode*dnode, XMLGen*xml_g);
		~cWFChamadaAtendimento();
		long incluir();
		int alterar();
		int excluir();
		bool consultar();
		bool consultarContato();

	private:
		bool locado;

		void carregaDados();
};
