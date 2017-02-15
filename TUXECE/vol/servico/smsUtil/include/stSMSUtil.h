/**
 * 
 * @modulo  Commons
 * @usecase Envio de mensagens SMS para o celular do usuário.
 * @author  Renato Teixeira
 * @version $Revision: 1.2 $
 * @CVS     $Author: drdantas $ - $Date: 2004/09/15 18:56:09 $
 **/ 

#ifndef stSMSUtil
	#define stSMSUtil

	struct st_SMSUtil {
		char destinatario[256];
		char mensagem[1000];
	};

	struct st_vlSMSUtil {
		short destinatario;
		short mensagem;	
	};
#endif
