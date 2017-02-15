#ifndef CPosItrH
#define CPosItrH

#include <stdlib.h>
#include <string.h>

struct STPosicaoRegistro
{
	char cidPosicao[21+1];
	char cdsPosicao[255+1];
};

class CPosItr
{
public:
	CPosItr();
	~CPosItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STPosicaoRegistro* Registro( void );
	STPosicaoRegistro* Registro(int nPosicao);

private:
	STPosicaoRegistro* stcrPosicao;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( char* cid, char* cnome );
	void ZeraPosicao( void );

};

#endif