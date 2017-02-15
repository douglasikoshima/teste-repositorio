#ifndef CNvlItrH
#define CNvlItrH

#include <stdlib.h>
#include <string.h>

struct STNivelRegistro
{
	char cidNivel[21+1];
	char cdsNivel[255+1];
};

class CNvlItr
{
public:
	CNvlItr();
	~CNvlItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STNivelRegistro* Registro( void );
	STNivelRegistro* Registro(int nNivel);

private:
	STNivelRegistro* stcrNivel;
	int _iQuantidade;
	int _iNivel;

protected:
	void Add( char* cid, char* cnome );
	void ZeraNivel( void );

};

#endif