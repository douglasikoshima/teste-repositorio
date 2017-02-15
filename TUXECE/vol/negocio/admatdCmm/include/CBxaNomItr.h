#ifndef CNomeBaixaItrH
#define CNomeBaixaItrH

#include <stdlib.h>
#include <string.h>


struct STNomeBaixaRegistro
{
	char cidNomeBaixa[21+1];
	char cnmBaixa[255+1];
};

class CNomeBaixaItr
{
public:
	CNomeBaixaItr();
	~CNomeBaixaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STNomeBaixaRegistro* Registro( void );
	STNomeBaixaRegistro* Registro(int nPosicao);

private:
	STNomeBaixaRegistro* stcrNomeBaixa;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidNomeBaixa,
			char* cnmBaixa );

	void ZeraNomeBaixa( void );

};

#endif
