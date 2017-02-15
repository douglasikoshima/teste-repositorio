/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:30 $
 **/ 

#include <tuxfw.h>
#include "stWFChamadaTelefonica.h"

class cWFChamadaTelefonica {
	st_ChamadaTelefonica*	m_stDados;
	st_vlChamadaTelefonica*	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cWFChamadaTelefonica(st_ChamadaTelefonica* dados, st_vlChamadaTelefonica* indicator, XMLGen*xml_g);
		cWFChamadaTelefonica(DOMNode*dnode, XMLGen*xml_g);
		~cWFChamadaTelefonica();
		unsigned long incluir();
		unsigned long incluirB(char *pszTipoPessoa);
		int alterar();
		int excluir();
		bool consultar();

	private:
		bool locado;
		void carregaDados();
};
