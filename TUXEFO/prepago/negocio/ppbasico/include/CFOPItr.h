#ifndef CCFOPItrH
#define CCFOPItrH

#include <stdlib.h>
#include <string.h>

struct STCFOPRegistro
{
	char cidCFOP[21+1];
	char cdsCFOP[255+1];
};

class CCFOPItr
{
public:
	CCFOPItr();
	~CCFOPItr();
	int Primeiro( void );
	int Ultimo( void );
	int Quantidade( void );
	STCFOPRegistro* Registro( void );
	STCFOPRegistro* Registro(int nPosicao);

private:
	STCFOPRegistro* stcrCFOP;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cidCFOP, char* cdsCFOP);
	void ZeraCFOP( void );

};

#endif