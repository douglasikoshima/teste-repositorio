#ifndef CNomeContatoItrH
#define CNomeContatoItrH

#include <stdlib.h>
#include <string.h>


struct STNomeContatoRegistro
{
	char cidNomeContato[21+1];
	char cnmContato[255+1];
};

class CNomeContatoItr
{
public:
	CNomeContatoItr();
	~CNomeContatoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STNomeContatoRegistro* Registro( void );
	STNomeContatoRegistro* Registro(int nPosicao);

private:
	STNomeContatoRegistro* stcrNomeContato;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidNomeContato,
			char* cnmContato );

	void ZeraNomeContato( void );

};

#endif
