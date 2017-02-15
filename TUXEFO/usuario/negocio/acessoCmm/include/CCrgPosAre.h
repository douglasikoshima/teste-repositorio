#ifndef CCrgPosAreH
#define CCrgPosAreH

//#include<XMLImpl.h>
#include<tuxfw.h>

#include "CCrgPosAreItr.h"


class CCrgPosAre : public CCrgPosAreItr
{
	public:
		CCrgPosAre();
		~CCrgPosAre();
		char* RTrim(char *pszString);
		int Insert( char* cidCargo,
		            char* idPosicao,
		            char* idDepartamento,
                    char* cidPessoaUsuario,
                    char* cidSessao );
		int Update( char* cidCargoPosicaoArea, 
					char* cidCargo,
		            char* cidPosicao,
		            char* cidDepartamento,
                    char* cidPessoaUsuario,
                    char* cidSessao );
		int ListId( char* cid );
		int ListAll( void );
		void GetXml( char* cNomeTag, XMLGen*xml );

};

#endif