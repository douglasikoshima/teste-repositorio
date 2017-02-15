/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:33:29 $
 **/

#ifndef CWFATDINBOX
	#define CWFATDINBOX

#include <tuxfw.h>
#include "stWFAtdInBox.h"

class cWFAtdInBox
{

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:

	st_AtdInBox	m_stFila;
	st_vlAtdInBox	m_vlFila;

        cWFAtdInBox();
        ~cWFAtdInBox();
	cWFAtdInBox(DOMNode*dnode, XMLGen*xml_g);
        bool AtualizaProcessos( int idPessoaUsuario );
        bool consultarBox( int idPessoaUsuario );
        bool consultarBoxAdq( int idPessoaUsuario );
		void consultarBoxAdqCliente(DOMNode* dnode, XMLGen* xml_g); // Adaptação do módulo de clientes
		bool consultarBoxEnc( int idPessoaUsuario);

	private:
		void carregaDados();
};

#endif
