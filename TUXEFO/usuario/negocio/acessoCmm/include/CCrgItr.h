#ifndef CCrgItrH
#define CCrgItrH

#include <stdlib.h>
#include <string.h>
#include <tuxfw.h>

struct STCargoRegistro
{
	char cidCargo[21+1];
	char cnmCargo[255+1];
};

class CCrgItr
{
public:
	CCrgItr();
	~CCrgItr();
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
	void Add( char* cid, char* cnome );
	void ZeraCargo( void );

};

#endif