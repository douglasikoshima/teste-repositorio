/**
 * 
 * @modulo  Commons
 * @usecase Envio de mensagens SMS para o celular do usuário.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: pasilva $ - $Date: 2004/08/25 22:00:49 $
 **/ 
/*#include <tuxfw.h>*/ //devido ao nosso ambiente estar configurado tive de alterar para a linha abaixo
#include <tuxfw/tuxfw.h>
#include <stSMSUtil.h>

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
