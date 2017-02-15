/**
 * 
 * @modulo  Commons
 * @usecase Envio de mensagens SMS para o celular do usuário.
 * @author  Renato Teixeira
 * @version $Revision: 1.1.1.1 $
 * @CVS     $Author: akurak $ - $Date: 2007/07/04 13:54:23 $
 **/ 

#ifndef stSMSUtil
	#define stSMSUtil

	struct st_SMSUtil {
		char* destinatario;
		char* mensagem;
	};

	struct st_vlSMSUtil {
		short destinatario;
		short mensagem;	
	};
#endif
