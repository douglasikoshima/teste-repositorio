
#ifndef CSOLICITARSENHA
#define CSOLICITARSENHA

//#include "CSenhaBasePC.h"
//#include "../../SenhaBase/include/CSenhaBasePC.h"
#define ERR_LINHA_EXPIRADA 1

class CSolicitarSenha
{

	public:
		//------------------------------------------------------------------------------		
		 CSolicitarSenha();		// Constructor
        ~CSolicitarSenha();    // Destructor

	
		void setSenhaGeradaAuxiliarCript(char* );
		void setSenhaGeradaCript(char* value);
		char* getSenhaGeradaCript();
		char* getSenhaGeradaAuxiliarCript();

	
		void setSenhaAuxiliar(char* );
		void setSenha(char* value);
		char* getSenha();
		char* getSenhaAuxiliar();

		char* ltrim(char *pStr);

		char* trim(char *pStr);
		char* rtrim(char *pStr);
		bool verificarSenhaGeradaInvalida(int iCdAreaRegistro, int iNrLinha, int iIdTipoRelacionamento, char* cSenha);
		void gerarSenha(int iCdAreaRegistro, int iNrLinha, int iIdTipoRelacionamento);
		void GravaSenhaSemUsuario(int iCdAreaRegistro, int iNrLinha, char* cSenhaGerada);
		int  consultarLinhaExpirada(int iCdAreaRegistro, int iNrLinha);

	
		
private:

	char cSenhaGerada[5];
	char cSenhaGeradaAuxiliar[5];
	char cSenhaCript[33];
	char cSenhaAuxiliarCript[33];


};

#endif