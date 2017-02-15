#ifndef CNivelCargoH
#define CNivelCargoH

#include <tuxfw.h>
#include "../include/CNCItr.h"
#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CNivelCargo : public CNivelCargoItr
{
    private:
		int InserirRaiz();
		int InserirNode();

	public:
		CNivelCargo();
		~CNivelCargo();

		//Monta o xml padrao desta tabela
		void GetXml( char* cNomeTag, XMLGen*xml );
		//Monta o xml array padrao desta tabela
		void GetXml( char* cNomeTagArray, char* cNomeTag, XMLGen*xml );
		//Recupera registros da pesquisa
		int List( 
			char* cidNivelCargo,
			char* cidNivel,
			char* cidCargo,
			char* cidUsuarioAlteracao );
		//Recupera um registro
		int ListId( char* cidNivelCargo );
		//Recupera todos os registros
		int ListAll( void );
		//Insere um registro
		int Insert( 
			char* cidNivel,
			char* cidCargo,
			char* cidUsuarioAlteracao );
		//Apaga um ou mais registros
		int Delete( char* cidNivelCargo );
		//Relaciona e Lista relacionados e existentes
		int RelacionaNvl( char*cidNivel, char*cNomeIdTag, char*cidUsuarioAlteracao, DOMNode*dnode );
		//Apaga um ou mais registros
		int EraseNvl( char* cidNivel );
		//Relaciona e Lista relacionados e existentes
		int RelacionaCrg( char*cidCargo, char*cNomeIdTag, char*cidUsuarioAlteracao, DOMNode*dnode );
		//Apaga um ou mais registros
		int EraseCrg( char* cidCargo );
};

#endif

