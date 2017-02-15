#ifndef CTipoFeriadoItrH
#define CTipoFeriadoItrH

#include <stdlib.h>
#include <string.h>


struct STTipoFeriadoRegistro
{
	char cidTipoFeriado[21+1];
	char cdsTipoFeriado[255+1];
};

class CTipoFeriadoItr
{
public:
	CTipoFeriadoItr();
	~CTipoFeriadoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoFeriadoRegistro* Registro( void );
	STTipoFeriadoRegistro* Registro(int nPosicao);

private:
	STTipoFeriadoRegistro* stcrTipoFeriado;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidTipoFeriado,
			char* cdsTipoFeriado );

	void ZeraTipoFeriado( void );

};

#endif
