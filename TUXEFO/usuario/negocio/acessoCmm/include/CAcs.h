#ifndef CAcsH
#define CAcsH

//#include<XMLImpl.h>
#include <tuxfw.h>

#include "CAcsItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )


class CAcs : public CAcsItr
{
	public:
		CAcs();
		~CAcs();
		int UsuarioAtivo( char* cidPessoaUsuario );
		int ListId( char* cnmurlpagina, char* cidusuario );
		int ListId( char* cidusuario, XMLGen*xml );
		int ListAll( void );
		void GetXml( char* cNomeTag, XMLGen*xml );
		int GetSessaoUsuario(char* cidusuario,char *dsSessaoUsuario);
		
		int UsuarioLogado( char* cidPessoaUsuario );
		int login( char* cidPessoaUsuario, char* csessionId );
		int logout( char* cidPessoaUsuario );
		int logout( char* cidPessoaUsuario, char* csessionId );
		int VerificaAtualizalogin( char* cidPessoaUsuario, char* csessionId );
		int BuscaTempoDeExpiracao( void );
		
		char* getMensagem( void ) { return cMensagem; };
	
	private:
		char cMensagem[255+1];
};

#endif