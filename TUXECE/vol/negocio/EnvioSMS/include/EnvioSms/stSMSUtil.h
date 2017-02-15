/**
 * 
 * @modulo  Commons
 * @usecase Envio de mensagens SMS para o celular do usuário.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: pasilva $ - $Date: 2004/08/25 22:00:49 $
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
