#ifndef CUFItrH
#define CUFItrH

#include <stdlib.h>
#include <string.h>
#include<tuxfw.h>

struct STUFRegistro
{
	char cidUF[21+1];
	char csgUF[255+1];
	char cnmUF[255+1];
};

class CUFItr
{
public:
	CUFItr();
	~CUFItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STUFRegistro* Registro( void );
	STUFRegistro* Registro(int nPosicao);

private:
	STUFRegistro* stcrUF;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cid, char* csigla, char* cnome );
	void ZeraUF( void );

};

#endif