#ifndef CTipoLinhaItrH
#define CTipoLinhaItrH

#include <stdlib.h>
#include <string.h>


struct STTipoLinhaRegistro
{
	char cidTipoLinha[21+1];
	char csgTipoLinha[255+1];
	char cdsTipoLinha[255+1];
	char cvlPeso[21+1];
};

class CTipoLinhaItr
{
public:
	CTipoLinhaItr();
	~CTipoLinhaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoLinhaRegistro* Registro( void );
	STTipoLinhaRegistro* Registro(int nPosicao);

private:
	STTipoLinhaRegistro* stcrTipoLinha;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidTipoLinha,
			char* csgTipoLinha,
			char* cdsTipoLinha,
			char* cvlPeso );

	void ZeraTipoLinha( void );

};

#endif
