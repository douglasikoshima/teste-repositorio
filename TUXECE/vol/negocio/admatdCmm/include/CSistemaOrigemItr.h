#ifndef CSistemaOrigemItrH
#define CSistemaOrigemItrH

#include <stdlib.h>
#include <string.h>

struct STSistemaOrigemRegistro
{
	char cidSistemaOrigem[21+1];
	char cnmSistemaOrigem[255+1];
};

class CSistemaOrigemItr
{
public:
	CSistemaOrigemItr();
	~CSistemaOrigemItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STSistemaOrigemRegistro* Registro( void );
	STSistemaOrigemRegistro* Registro(int nPosicao);

private:
	STSistemaOrigemRegistro* stcrSistemaOrigem;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidSistemaOrigem,
			char* cnmSistemaOrigem );

	void ZeraSistemaOrigem( void );

};

#endif
