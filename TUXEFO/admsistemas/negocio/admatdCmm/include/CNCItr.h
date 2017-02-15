#ifndef CNivelCargoItrH
#define CNivelCargoItrH

#include <stdlib.h>
#include <string.h>

struct STNivelCargoRegistro
{
	char cidNivelCargo[21+1];
	char cidNivel[21+1];
	char cidCargo[21+1];
};

class CNivelCargoItr
{
public:
	CNivelCargoItr();
	~CNivelCargoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STNivelCargoRegistro* Registro( void );
	STNivelCargoRegistro* Registro(int nPosicao);

private:
	STNivelCargoRegistro* stcrNivelCargo;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidNivelCargo,
			char* cidNivel,
			char* cidCargo );

	void ZeraNivelCargo( void );

};

#endif
