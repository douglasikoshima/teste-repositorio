#ifndef CNivelItrH
#define CNivelItrH

#include <stdlib.h>
#include <string.h>

struct STNivelRegistro
{
	char cidNivel[21+1];
	char cidNivelPai[21+1];
	char cdsNivel[255+1];
	char cdsPath[1024+1];
	int  iLevel;
};

class CNivelItr
{
public:
	CNivelItr();
	~CNivelItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STNivelRegistro* Registro( void );
	STNivelRegistro* Registro(int nPosicao);
	char* GetErro() { return cErroGeral; }

private:
	STNivelRegistro* stcrNivel;
	int _iQuantidade;
	int _iPosicao;
	char cErroGeral[255+1];

protected:

	void Add( 
			char* cidNivel,
			char* cdsNivel);
			

	void Add( 
			char* cidNivel,
			char* cidNivelPai,
			char* cdsNivel,
			char* cdsPath,
			int   iLevel );

	void ZeraNivel( void );
	void  SetErro( char* cErro );
};

#endif
