#ifndef CNvlHieH
#define CNvlHieH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CNvlHieItr.h"


class CNvlHie : public CNvlHieItr
{
	public:
		CNvlHie();
		~CNvlHie();
		char* RTrim(char *pszString);
		int Insert( char* cidArea,
		            char* idPessoaDeParaOperadora, char* cLogUser );
		int Update( char* cidOperadoraDepto, 
					char* cidArea,
		            char* cidPessoaDeParaOperadora, char* cLogUser );
		int ListId( char* cid );
		int ListAll( void );
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif