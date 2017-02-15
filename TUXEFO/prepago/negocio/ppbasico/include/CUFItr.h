#ifndef CUFItrH
#define CUFItrH

#include <stdlib.h>
#include <string.h>


struct STUFRegistro
{
	char cidUF[21+1];
	char csgUF[255+1];
	char cnmUF[255+1];
	char cnrFusoHorario[21+1];
};

class CUFItr
{
public:
	CUFItr();
	~CUFItr();
	int Primeiro( void );
	int Ultimo( void );
	int Quantidade( void );
	STUFRegistro* Registro( void );
	STUFRegistro* Registro(int nPosicao);

private:
	STUFRegistro* stcrUF;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidUF,
			char* csgUF,
			char* cnmUF,
			char* cnrFusoHorario );

	void ZeraUF( void );

};

#endif
