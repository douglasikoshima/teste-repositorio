#ifndef CCargoAtribuicaoItrH
#define CCargoAtribuicaoItrH

#include <stdlib.h>
#include <string.h>

struct STCargoAtribuicaoRegistro
{
	char cidCargoAtribuicao[21+1];
	char cidCargo[21+1];
	char cidAtribuicao[21+1];
};

class CCargoAtribuicaoItr
{
public:
	CCargoAtribuicaoItr();
	~CCargoAtribuicaoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STCargoAtribuicaoRegistro* Registro( void );
	STCargoAtribuicaoRegistro* Registro(int nPosicao);

private:
	STCargoAtribuicaoRegistro* stcrCargoAtribuicao;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidCargoAtribuicao,
			char* cidCargo,
			char* cidAtribuicao );

	void ZeraCargoAtribuicao( void );

};

#endif
