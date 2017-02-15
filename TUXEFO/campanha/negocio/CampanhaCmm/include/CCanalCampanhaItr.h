#ifndef CCanalCampanhaItrH
#define CCanalCampanhaItrH

#include <stdlib.h>
#include <string.h>

struct STCanalCampanhaRegistro
{
	char cidCanalCampanha[21+1];
	char cidSubCampanhaHistorico[21+1];
	char cidCanalUFOperadora[21+1];
	char csqApresentacao[21+1];
	char cinDisponibilidade[21+1];
	char cinAtivo[21+1];
};

class CCanalCampanhaItr
{
public:
	CCanalCampanhaItr();
	~CCanalCampanhaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STCanalCampanhaRegistro* Registro( void );
	STCanalCampanhaRegistro* Registro(int nPosicao);

private:
	STCanalCampanhaRegistro* stcrCanalCampanha;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidCanalCampanha,
	          char* cidSubCampanhaHistorico,
	          char* cidCanalUFOperadora,
	          char* csqApresentacao,
	          char* cinDisponibilidade,
	          char* cinAtivo );

	void ZeraCanalCampanha( void );

};

#endif