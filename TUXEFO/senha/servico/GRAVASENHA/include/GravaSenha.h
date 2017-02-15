//------------------------------------------------------------------------------		
#ifndef CGRAVASENHA
#define CGRAVASENHA

//#include "CSenhaBasePC.h"
#include "../../SenhaBase/include/CSenhaBasePC.h"


//------------------------------------------------------------------------------		
// CListaIDPos 
//------------------------------------------------------------------------------		

class CGravaSenha : public CSenhaBase
{
	public:
		//------------------------------------------------------------------------------		
		 CGravaSenha(DOMNode*, XMLGen* , char *);		// Constructor
		 CGravaSenha(char *cSenha);        //Construtor 
        ~CGravaSenha(){};								// Destructor
		void Executar(); 
		bool VerificarSenhaFraca();
		bool RegistrarSenha();
		bool EnviaSMS();
		void setSenha(char *cSenha);
		bool EnviaSMSSenhaGerada(char* cSenha, int iIdCanal, int iCdAreaRegistro, int iNrLinha);

		
		//------------------------------------------------------------------------------		


	protected:
		TString cdMsg;
	private:

};

#endif
