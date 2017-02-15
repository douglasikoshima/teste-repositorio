#ifndef CTipoRetornoContatoItrH
#define CTipoRetornoContatoItrH

#include <stdlib.h>
#include <string.h>


struct STTipoRetornoContatoRegistro
{
	char cidTipoRetornoContato[21+1];
	char cnmTipoRetornoContato[255+1];
};

class CTipoRetornoContatoItr
{
public:
	CTipoRetornoContatoItr();
	~CTipoRetornoContatoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoRetornoContatoRegistro* Registro( void );
	STTipoRetornoContatoRegistro* Registro(int nPosicao);

private:
	STTipoRetornoContatoRegistro* stcrTipoRetornoContato;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidTipoRetornoContato,
			char* cnmTipoRetornoContato );

	void ZeraTipoRetornoContato( void );

};

#endif
