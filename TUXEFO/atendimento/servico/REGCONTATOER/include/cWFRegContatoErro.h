/**
 * @author  Eder Jani Martins
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:35 $
 **/ 

#ifndef __CWF_REGCONTATOERRO_H__
#define __CWF_REGCONTATOERRO_H__

#include <tuxfw.h>
#include "stWFRegContatoErro.h"

class cWFRegContatoErro
{

    st_RegContatoLogErro m_stDados;
    st_vlRegContatoLogErro m_vlDados;

    DOMNode* entrada;

    TuxHelper tx;

    public:
        cWFRegContatoErro() {entrada=0;}
        cWFRegContatoErro(DOMNode*dnode, char* oIdPessoaUsuario);
		cWFRegContatoErro(DOMNode*dnode, char* oIdPessoaUsuario, char* oMensagemErro);
        ~cWFRegContatoErro() {};

		unsigned long incluir();
		void executar();

    private:
        void  carregaDados();
		char  pcMensagemErro[1024+1];
		char  pcIdPessoaUsuario[256+1];
		char* remoteACall(char* nomeServico, char* sMsgIn, int lFlags);

};

#endif
