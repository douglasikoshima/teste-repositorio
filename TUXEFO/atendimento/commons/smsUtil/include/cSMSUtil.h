/**
 * 
 * @modulo  Commons
 * @usecase Envio de mensagens SMS para o celular do usuário.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:13 $
 **/ 

#ifndef __C_SMS_UTIL_H__
#define __C_SMS_UTIL_H__

#include <tuxfw.h>
#include "stSMSUtil.h"

class cSMSUtil:public TuxBasicSvc
{

    st_SMSUtil  m_stDados;
    st_vlSMSUtil    m_vlDados;

    DOMNode* entrada;
    XMLGen*  saida;

    TuxHelper tx;

    public:
        cSMSUtil();
        cSMSUtil(DOMNode*dnode, XMLGen*xml_g);
        cSMSUtil(st_SMSUtil *dados,st_vlSMSUtil *status,XMLGen*xml_g);

        void setDestinatario(char* destinatario);
        void setMensagem(char* mensagem);
        void enviar();

    private:
        char* idPessoaUsuario;
        void carregaDados();
};

#endif
