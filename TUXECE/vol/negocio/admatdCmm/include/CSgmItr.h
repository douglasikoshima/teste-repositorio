#ifndef CSegmentacaoItrH
#define CSegmentacaoItrH

#include <stdlib.h>
#include <string.h>


struct STSegmentacaoRegistro
{
	char cidSegmentacao[21+1];
	char csgSegmentacao[255+1];
	char cdsSegmentacao[255+1];
	char cvlPeso[21+1];
};

class CSegmentacaoItr
{
public:
	CSegmentacaoItr();
	~CSegmentacaoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STSegmentacaoRegistro* Registro( void );
	STSegmentacaoRegistro* Registro(int nPosicao);

private:
	STSegmentacaoRegistro* stcrSegmentacao;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidSegmentacao,
			char* csgSegmentacao,
			char* cdsSegmentacao,
			char* cvlPeso );

	void ZeraSegmentacao( void );

};

#endif
