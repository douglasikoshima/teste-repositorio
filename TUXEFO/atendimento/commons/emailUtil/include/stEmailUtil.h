/**
 * 
 * @modulo  Commons
 * @usecase Envio de e-mails.
 * @author  Renato Teixeira
 * @version $Revision: 1.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/07/31 15:34:03 $
 **/ 

#ifndef stEmailUtil
	#define stEmailUtil

struct st_EmailUtil
{
	char remetente[256];
	char destinatario[256];
	char assunto[256];
	char mensagem[256];
};

struct st_vlEmailUtil
{
	short remetente;
	short destinatario;
	short assunto;
	short mensagem;	
};

#endif
