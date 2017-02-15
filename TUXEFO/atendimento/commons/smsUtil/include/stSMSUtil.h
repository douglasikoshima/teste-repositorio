/**
 * 
 * @modulo  Commons
 * @usecase Envio de mensagens SMS para o celular do usuário.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:13 $
 **/ 

#ifndef stSMSUtil
	#define stSMSUtil

struct st_SMSUtil
{
	char destinatario[256];
	char mensagem[1000];
};

struct st_vlSMSUtil
{
	short destinatario;
	short mensagem;	
};

#endif
