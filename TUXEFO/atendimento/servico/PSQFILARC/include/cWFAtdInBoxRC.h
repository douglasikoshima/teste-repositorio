/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:41 $
 **/

#ifndef CWFATDINBOXRC
	#define CWFATDINBOXRC

#include <tuxfw.h>
#include "stWFAtdInBoxRC.h"

class cWFAtdInBoxRC
{

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:

	    st_AtdInBoxRC	    m_stFila;
	    st_vlAtdInBoxRC	m_vlFila;

        cWFAtdInBoxRC();
        ~cWFAtdInBoxRC();
	    cWFAtdInBoxRC(DOMNode*dnode, XMLGen*xml_g);
        bool AtualizaProcessos( int idPessoaUsuario );
        bool consultarBoxAdq( int idPessoaUsuario );


	private:
		void carregaDados();
};

#endif
