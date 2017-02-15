/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Renato Teixeira
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/

#ifndef CWFATDMSGCRI
	#define CWFATDMSGCRI

#include <tuxfw.h>
#include "stWFAtdMsgCri.h"

class cWFAtdMsgCri
{

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

    st_AtdMsgCri	m_stFila;
    st_vlAtdMsgCri	m_vlFila;

	public:

        cWFAtdMsgCri();
    	cWFAtdMsgCri(DOMNode*dnode, XMLGen*xml_g);
	    cWFAtdMsgCri(st_AtdMsgCri* dados, st_vlAtdMsgCri* status, XMLGen*xml_g);
	    cWFAtdMsgCri(st_AtdMsgCri* dados, st_vlAtdMsgCri* status);
        ~cWFAtdMsgCri() {}

	    bool incluir();

        bool consultarMensagems(int idPessoaUsuario, long idAtendimento );
                
	private:
		void carregaDados();
};

#endif
