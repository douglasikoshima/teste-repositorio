#ifndef CSmsH
#define CSmsH

#include <tuxfw.h>
#define STRLENNULL( y ) ( y == NULL ? 0 : strlen( y )  )


class CSms
{
    private:
		char cIdSms[21+1];
		char cOrigem[255+1];

	public:
		CSms();
		~CSms();

		void Insert( 
		    char* pzcdsSms
		   ,char* pzcnrOrigem
		   ,char* pzcnrDestino
		   ,char* pzcidPessoaUsuario
		);
		void AtiualizaStatus( 
		    char* pzidsSms
		   ,char* pzcidStatus
		   ,char* pzcdtEntrega
		   ,char* pzcidPessoaUsuario
		);
		char* RecuperaOrigem( void );


		char* getIdSms( void ) { return cIdSms; }
		char* getOrigem( void ) { return cOrigem; }

};

#endif

