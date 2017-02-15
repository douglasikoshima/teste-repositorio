#ifndef CContatoSegmentacaoItrH
#define CContatoSegmentacaoItrH

#include <stdlib.h>
#include <string.h>


struct STContatoSegmentacaoRegistro
{
	char cidContatoSegmentacao[21+1];
	char cidContato[21+1];
	char cidSegmentacao[21+1];
};

class CContatoSegmentacaoItr
{
public:
	CContatoSegmentacaoItr();
	~CContatoSegmentacaoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoSegmentacaoRegistro* Registro( void );
	STContatoSegmentacaoRegistro* Registro(int nPosicao);

private:
	STContatoSegmentacaoRegistro* stcrContatoSegmentacao;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContatoSegmentacao,
			char* cidContato,
			char* cidSegmentacao );

	void ZeraContatoSegmentacao( void );

};

#endif
