#ifndef CMotivoCampanhaItrH
#define CMotivoCampanhaItrH

#include <stdlib.h>
#include <string.h>

struct STMotivoCampanhaRegistro
{
	char cidMotivoCampanha[21+1];
	char cidSubCampanhaHistorico[21+1];
	char cidTipoStatusCampanha[21+1];
	char cidTipoMotivoCampanha[21+1];
	char cidTipoSubMotivoCampanha[21+1];
	char cinAtivo[21+1];
};

class CMotivoCampanhaItr
{
public:
	CMotivoCampanhaItr();
	~CMotivoCampanhaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STMotivoCampanhaRegistro* Registro( void );
	STMotivoCampanhaRegistro* Registro(int nPosicao);

private:
	STMotivoCampanhaRegistro* stcrMotivoCampanha;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidMotivoCampanha, 
		      char* cidSubCampanhaHistorico, 
			  char* cidTipoStatusCampanha, 
			  char* cidTipoMotivoCampanha, 
			  char* cidTipoSubMotivoCampanha );

	void Add( char* cidMotivoCampanha, 
		      char* cidSubCampanhaHistorico, 
			  char* cidTipoStatusCampanha, 
			  char* cidTipoMotivoCampanha, 
			  char* cidTipoSubMotivoCampanha,
			  char* cinAtivo );

	void ZeraMotivoCampanha( void );

};

#endif