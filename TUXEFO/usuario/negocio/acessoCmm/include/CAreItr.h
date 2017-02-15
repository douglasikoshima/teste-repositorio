#ifndef CAreItrH
#define CAreItrH

#include <stdlib.h>
#include <string.h>

struct STAreaRegistro
{
	char cidArea[21+1];
	char cnmDepartamento[255+1];
};

class CAreItr
{
public:
	CAreItr();
	~CAreItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STAreaRegistro* Registro( void );
	STAreaRegistro* Registro(int nPosicao);

private:
	STAreaRegistro* stcrArea;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cid, char* cnome );
	void ZeraArea( void );

};

#endif