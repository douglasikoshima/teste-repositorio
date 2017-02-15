#ifndef CCargoItrH
#define CCargoItrH

#include <stdlib.h>
#include <string.h>

struct STCargoRegistro
{
	char cidCargo[21+1];
	char cnmCargo[255+1];
};

class CCargoItr
{
public:
	CCargoItr();
	~CCargoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STCargoRegistro* Registro( void );
	STCargoRegistro* Registro(int nPosicao);

private:
	STCargoRegistro* stcrCargo;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidCargo,
			char* cnmCargo );

	void ZeraCargo( void );

};

#endif
