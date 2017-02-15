/**
 * 
 * @modulo  Commons
 * @usecase Envio de e-mails.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:03 $
 **/ 

#ifndef __C_EMAIL_UTIL_H__
#define __C_EMAIL_UTIL_H__

#include <tuxfw.h>
#include "stEmailUtil.h"

class cEmailUtil:public TuxBasicSvc
{

    st_EmailUtil    m_stDados;
    st_vlEmailUtil  m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cEmailUtil();
        cEmailUtil(DOMNode*dnode, XMLGen*xml_g);
        cEmailUtil(st_EmailUtil *saida,st_vlEmailUtil *status,XMLGen*xml_g);
        void setRemetente(char* remetente);
        void setDestinatario(char* destinatario);
        void setAssunto(char* assunto);
        void setMensagem(char* mensagem);
        void enviar();

    private:
        void carregaDados();
};

#endif
