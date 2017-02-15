/**
 * @author  David Ramos Dominguez
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2011/08/12 17:11:16 $
 **/

#ifndef SQLCA
#define SQLCA_NONE

#include<sqlca.h>
#include<sqlda.h>
#include<ctype.h>

#endif

#include <tuxfw.h>
#include"../../../commons/queryMacro.h"
#include"stWF_CERRAMEBKO.h"


struct stBaixaHistorico
{
	long idAtendimento;
	int  idBxa;
	int  inEnvEmail;
	int  inEnvSms;
	int  inEnvTel;
	char dsMsg[256];
	char dsCom[256];
};

struct stMensagemBaixa
{
	int  idBxa;
	int  idMsgBxa;
	int  idBaixaMensagem;
	
};


class cWF_CERRAMEBKOPC
{
	public:
		cWF_CERRAMEBKOPC();
		int obtemPrazoANATEL();
		void dataAtual(char* data);		
		void dataAndamento(char* data);
		int inclusaoBaixaHistorico(int entrada);
		void sacaridBaixaHistorico(stMensagemBaixa* datos);
		int TotalAtendimentoBaixaActual(long idAtendimento);
		
	private:
		void sql_error_WFGravaAtendimento( sqlca * sqlca );
};
