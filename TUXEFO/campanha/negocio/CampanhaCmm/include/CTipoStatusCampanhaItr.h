#ifndef CTipoStatusCampanhaItrH
#define CTipoStatusCampanhaItrH

#include <stdlib.h>
#include <string.h>

struct STTipoStatusCampanhaRegistro
{
	char cidTipoStatusCampanha[21+1];
	char csgTipoStatusCampanha[256+1];
	char cdsTipoStatusCampanha[256+1];
};

class CTipoStatusCampanhaItr
{
public:
	CTipoStatusCampanhaItr();
	~CTipoStatusCampanhaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STTipoStatusCampanhaRegistro* Registro( void );
	STTipoStatusCampanhaRegistro* Registro(int nPosicao);

private:
	STTipoStatusCampanhaRegistro* stcrTipoStatusCampanha;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidTipoStatusCampanha, 
		      char* csgTipoStatusCampanha, 
			  char* cdsTipoStatusCampanha );

	void ZeraTipoStatusCampanha( void );

};

#endif