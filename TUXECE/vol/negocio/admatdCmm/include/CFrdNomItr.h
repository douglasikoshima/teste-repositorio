#ifndef CNomeFeriadoItrH
#define CNomeFeriadoItrH

#include <stdlib.h>
#include <string.h>


struct STNomeFeriadoRegistro
{
	char cidNomeFeriado[21+1];
	char cdsFeriado[255+1];
};

class CNomeFeriadoItr
{
public:
	CNomeFeriadoItr();
	~CNomeFeriadoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STNomeFeriadoRegistro* Registro( void );
	STNomeFeriadoRegistro* Registro(int nPosicao);

private:
	STNomeFeriadoRegistro* stcrNomeFeriado;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidNomeFeriado,
			char* cdsFeriado );

	void ZeraNomeFeriado( void );

};

#endif
