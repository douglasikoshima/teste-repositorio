#ifndef CCrgH
#define CCrgH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CCrgItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CCrg : public CCrgItr
{
	public:
		CCrg();
		~CCrg();
		char* RTrim(char *pszString);
		int Insert( char* cnmCargo,
				    char* cLogUser );
		int Update( char* cidCargo, 
					char* cnmCargo,
				    char* cLogUser );
		int ListId( char* cid );
		int ListAll( void );
		int InsertUpdateCargoUsuario( char* cidPessoa
                                     ,char* cidCargo
                                     ,char* cidUser );
		void GetXml( char* cNomeTag, XMLGen*xml );
		char* GetErro( void ) { return _cErro; }

	private:
		char _cErro[255+1];
		void SetErro( char* cErro );

};

#endif