#ifndef CAtribuicaoItrH
#define CAtribuicaoItrH

#include <stdlib.h>
#include <string.h>

struct STAtribuicaoRegistro
{
	char cidAtribuicao[21+1];
	char cnmAtribuicao[255+1];
};

class CAtribuicaoItr
{
public:
	CAtribuicaoItr();
	~CAtribuicaoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STAtribuicaoRegistro* Registro( void );
	STAtribuicaoRegistro* Registro(int nPosicao);

private:
	STAtribuicaoRegistro* stcrAtribuicao;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidAtribuicao,
			char* cnmAtribuicao );

	void ZeraAtribuicao( void );

};

#endif
