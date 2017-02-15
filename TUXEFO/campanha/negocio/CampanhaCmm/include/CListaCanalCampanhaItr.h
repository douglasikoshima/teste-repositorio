#ifndef CListaCanalCampanhaItrH
#define CListaCanalCampanhaItrH

#include <stdlib.h>
#include <string.h>

struct STListaCanalCampanhaRegistro
{
	char cidListaCanalCampanha[21+1];
	char cidLista[21+1];
	char cidCanalCampanha[21+1];
	char cinAtivo[21+1];
};

class CListaCanalCampanhaItr
{
public:
	CListaCanalCampanhaItr();
	~CListaCanalCampanhaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STListaCanalCampanhaRegistro* Registro( void );
	STListaCanalCampanhaRegistro* Registro(int nPosicao);

private:
	STListaCanalCampanhaRegistro* stcrListaCanalCampanha;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidListaCanalCampanha,
	          char* cidLista,
	          char* cidCanalCampanha,
	          char* cinAtivo );

	void ZeraListaCanalCampanha( void );

};

#endif