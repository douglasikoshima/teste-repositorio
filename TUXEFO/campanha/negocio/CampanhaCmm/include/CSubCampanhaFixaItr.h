#ifndef CSubCampanhaFixaItrH
#define CSubCampanhaFixaItrH

#include <stdlib.h>
#include <string.h>

struct STSubCampanhaFixaRegistro
{
	char cidSubCampanhaFixa[21+1];
	char cnmSubCampanhaFixa[255+1];
	char cidCampanha[21+1];
	char cinAtiva[21+1];
	char cinDisponibilidade[21+1];
};

class CSubCampanhaFixaItr
{
public:
	CSubCampanhaFixaItr();
	~CSubCampanhaFixaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STSubCampanhaFixaRegistro* Registro( void );
	STSubCampanhaFixaRegistro* Registro(int nPosicao);

private:
	STSubCampanhaFixaRegistro* stcrSubCampanhaFixa;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidSubCampanhaFixa, 
		      char* cidCampanha, 
			  char* cinAtiva, 
			  char* cinDisponibilidade );

	void Add( char* cidSubCampanhaFixa, 
		      char* cnmSubCampanhaFixa, 
		      char* cidCampanha, 
			  char* cinAtiva, 
			  char* cinDisponibilidade );

	void ZeraSubCampanhaFixa( void );

};

#endif