#ifndef CUFOperadoraItrH
#define CUFOperadoraItrH

#include <stdlib.h>
#include <string.h>
#include<tuxfw.h>

struct STUFOperadoraRegistro
{
	char cidUFOperadora[21+1];
	char cdsUFOperadora[255+1];
	char cidUF[21+1];
	char csgUF[255+1];
	char cnmUF[255+1];
};

class CUFOperadoraItr
{
public:
	CUFOperadoraItr();
	~CUFOperadoraItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STUFOperadoraRegistro* Registro( void );
	STUFOperadoraRegistro* Registro(int nPosicao);

private:
	STUFOperadoraRegistro* stcrUFOperadora;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cidUFOperadora
	         ,char* cdsUFOperadora
	         ,char* cidUF
	         ,char* csgUF 
	         ,char* cnmUF );
	void ZeraUFOperadora( void );

};

#endif