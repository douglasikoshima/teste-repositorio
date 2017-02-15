#ifndef CSubItrH
#define CSubItrH

#include <stdlib.h>
#include <string.h>
#include<tuxfw.h>

struct STSubRegistro
{
	char cidSubSistema[21+1];
	char cnmSubSistema[255+1];
	char cidSistema[21+1];
};

class CSubItr
{
public:
	CSubItr();
	~CSubItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STSubRegistro* Registro( void );
	STSubRegistro* Registro(int nPosicao);

private:
	STSubRegistro* stcrSub;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cidSubSistema, 
	          char* cnmSubSistema, 
	          char* cidSistema );

	void ZeraSub( void );

};

#endif