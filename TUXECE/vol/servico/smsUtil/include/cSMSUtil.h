/**
 * 
 * @modulo  Commons
 * @usecase Envio de mensagens SMS para o celular do usuário.
 * @author  Renato Teixeira
 * @version $Revision: 1.2 $
 * @CVS     $Author: drdantas $ - $Date: 2004/09/15 18:56:08 $
 **/ 
#include <tuxfw.h>
#include "stSMSUtil.h"

class cSMSUtil:public TuxBasicSvc
{

	st_SMSUtil	m_stDados;
	st_vlSMSUtil	m_vlDados;

	DOMNode* entrada;
	XMLGen*  saida;

	TuxHelper tx;

	public:
		cSMSUtil();
		cSMSUtil(DOMNode*dnode, XMLGen*xml_g);
		void setDestinatario(char* destinatario);
		void setMensagem(char* mensagem);
		void enviar();

	private:
		char* idPessoaUsuario;
		void carregaDados();
};
