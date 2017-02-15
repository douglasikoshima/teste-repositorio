#ifndef CPgnItrH
#define CPgnItrH

#include <stdlib.h>
#include <string.h>
#include<tuxfw.h>

struct STPaginaRegistro
{
	char cidPagina[21+1];
	char cnmPagina[255+1];
	char cnmUrl[255+1];
	char cIndisponibilidade[21+1];
	char cidSubSistema[21+1];
	char cnmSubSistema[255+1];
};

class CPgnItr
{
public:
	CPgnItr();
	~CPgnItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STPaginaRegistro* Registro( void );
	STPaginaRegistro* Registro(int nPosicao);

private:
	STPaginaRegistro* stcrPagina;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cid, char* cnome, char* curl, char* cindisponibilidade, char* cidsubsistema, char* cnmsubsistema );
	void ZeraPagina( void );

};

#endif