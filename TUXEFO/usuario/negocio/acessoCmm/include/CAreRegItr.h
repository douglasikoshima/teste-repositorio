#ifndef CAreRegItrH
#define CAreRegItrH

#include <stdlib.h>
#include <string.h>
#include <tuxfw.h>

struct STAreaRegistroRegistro
{
	char cidAreaRegistro[21+1];
	char ccdAreaRegistro[255+1];
	char cnmAreaRegistro[255+1];
	char cidUFOperadora[255+1];
};

class CAreRegItr
{
public:
	CAreRegItr();
	~CAreRegItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STAreaRegistroRegistro* Registro( void );
	STAreaRegistroRegistro* Registro(int nPosicao);

private:
	STAreaRegistroRegistro* stcrAreaRegistro;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cidAreaRegistro
	         ,char* ccdAreaRegistro
	         ,char* cnmAreaRegistro
	         ,char* cidUFOperadora );
	void ZeraAreaRegistro( void );

};

#endif