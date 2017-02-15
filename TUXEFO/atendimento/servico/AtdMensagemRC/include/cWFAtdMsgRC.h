/**
 * 
 * @modulo  Workflow
 * @usecase Workflow
 * @author  Cassio M Garcia
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:20 $
 **/

#ifndef CWFATDMSGRC
    #define CWFATDMSGRC

#include <tuxfw.h>
#include "stWFAtdMsgRC.h"

class cWFAtdMsgRC
{

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

    st_AtdMsgRC m_stFila;
    st_vlAtdMsgRC m_vlFila;

	public:

        cWFAtdMsgRC();
    	cWFAtdMsgRC(DOMNode*dnode, XMLGen*xml_g);
	    cWFAtdMsgRC(st_AtdMsgRC* dados, st_vlAtdMsgRC* status, XMLGen*xml_g);
	    cWFAtdMsgRC(st_AtdMsgRC* dados, st_vlAtdMsgRC* status);
        ~cWFAtdMsgRC() {}

	    long incluir();

        bool consultarMensagems(int idPessoaUsuario, long idAtendimento);

        long obterIdAtendimento() { return m_stFila.idAtendimento; }
        int obterIdPessoaUsuario() { return m_stFila.idPessoaUsuario; }
                
	private:
		void carregaDados();
};

#endif
