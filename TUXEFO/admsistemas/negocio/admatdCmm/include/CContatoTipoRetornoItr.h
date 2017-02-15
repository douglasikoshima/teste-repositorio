#ifndef CContatoTipoRetornoItrH
#define CContatoTipoRetornoItrH

#include <stdlib.h>
#include <string.h>


struct STContatoTipoRetornoRegistro
{
	char cidContatoTipoRetorno[21+1];
	char cidContato[21+1];
	char cidTipoRetornoContato[21+1];
};

class CContatoTipoRetornoItr
{
public:
	CContatoTipoRetornoItr();
	~CContatoTipoRetornoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoTipoRetornoRegistro* Registro( void );
	STContatoTipoRetornoRegistro* Registro(int nPosicao);

private:
	STContatoTipoRetornoRegistro* stcrContatoTipoRetorno;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContatoTipoRetorno,
			char* cidContato,
			char* cidTipoRetornoContato );

	void ZeraContatoTipoRetorno( void );

};

#endif
