#ifndef TXPB_COMUNICACAO_USUARIO_H
#define TXPB_COMUNICACAO_USUARIO_H 1

#include <tuxfw.h>

#define TXPB_COMUSER_IDTPCOM           6
#define TXPB_COMUSER_IDTPCOM_SMS       1
#define TXPB_COMUSER_IDTPCOM_EMAIL     2

#define TXPB_COMUSER_SMTPSEND_SUBJECT  "VIVO Informa"



class CComunicacaoUsuario
{
	public:

		CComunicacaoUsuario(void);
		CComunicacaoUsuario(DOMNode*);
		
		void setCdAreaRegistro(char*);
		void setNrLinha(char*);
		void setIdTipoRelacionamento(char*);
		void setCdMsg(char*);
		
		char* getCdAreaRegistro(void);
		char* getNrLinha(void);
		char* getIdTipoRelacionamento(void);
		char* getCdMsg(void);

		int comunicarUsuario(void);

	private:

		char mvc_cdAreaRegistro[256];
		char mvc_nrLinha[256];
		char mvc_idTipoRelacionamento[256];
		char mvc_cdMsg[256];

		
		char mvc_idPessoa[256];
		int  mi_tipoDispCom;
		char mvc_dsContato[256];

		void setIdPessoa(char*);
		void setIdPessoa(int);
		void setTipoDispCom(int);
		void setDsContato(char*);

		char* getIdPessoa(void);
		int   getTipoDispCom(void);
		char* getDsContato(void);


		int consultarIdPessoa(void);
		int consultarDispComunicacao(void);
		int consultarEmail(void);
};



#endif