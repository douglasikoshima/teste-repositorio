#ifndef __EMAILH__
#define __EMAILH__
#include<tuxfw.h>

#define ERRO_LINHA_NAO_ENCONTRADA    100
#define ERRO_EMAIL_NAO_ENCONTRADO    101
#define ERRO_LINHA_POSPAGA			 102

#define ERRO_MSG_LINHA_NAO_ENCONTRADA    "Linha não encontrada"
#define ERRO_MSG_EMAIL_NAO_ENCONTRADO    "E-mail não cadastrado"
#define ERRO_MSG_LINHA_POSPAGA			 "Linha é pós-paga"

/**
 * Classe Email para retornar os e-mails do cliente
 */
class CEmail
{
	public:
		CEmail();
		virtual ~CEmail();
		int getEmail(XMLGen*gen,char* linha,char* ddd);	
};

#endif
