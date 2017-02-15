#ifndef CUFFeriadoItrH
#define CUFFeriadoItrH

#include <stdlib.h>
#include <string.h>


struct STUFFeriadoRegistro
{
	char cidUFFeriado[21+1];
	char cidUF[21+1];
	char cidFeriado[21+1];
};

class CUFFeriadoItr
{
public:
	CUFFeriadoItr();
	~CUFFeriadoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STUFFeriadoRegistro* Registro( void );
	STUFFeriadoRegistro* Registro(int nPosicao);

private:
	STUFFeriadoRegistro* stcrUFFeriado;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidUFFeriado,
			char* cidUF,
			char* cidFeriado );

	void ZeraUFFeriado( void );

};

#endif
