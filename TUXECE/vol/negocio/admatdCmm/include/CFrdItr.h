#ifndef CFeriadoItrH
#define CFeriadoItrH

#include <stdlib.h>
#include <string.h>


struct STFeriadoRegistro
{
	char cidFeriado[21+1];
	char cidTipoFeriado[21+1];
	char cdsTipoFeriado[255+1];
	char cidNomeFeriado[21+1];
	char cdsFeriado[255+1];
	char cinFeriadoMovel[21+1];
	char cdtDia[12+1];
	char cidUF[21+1];
	char csgUF[255+1];
	char cnmUF[255+1];
	char cidMunicipio[21+1];
	char cnmMunicipio[255+1];
	char cidHorarioVerao[21+1];
	char cnrHorarioVerao[21+1];
	char cdtInicio[12+1];
	char cdtFim[12+1];
    char cinRelatorio[21+1];
};

class CFeriadoItr
{
public:
	CFeriadoItr();
	~CFeriadoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STFeriadoRegistro* Registro( void );
	STFeriadoRegistro* Registro(int nPosicao);

private:
	STFeriadoRegistro* stcrFeriado;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidFeriado,
			  char* cidTipoFeriado,
			  char* cdsTipoFeriado,
			  char* cidNomeFeriado,
		 	  char* cdsFeriado,
			  char* cinFeriadoMovel,
			  char* cdtDia,
			  char* cidUF,
			  char* csgUF,
			  char* cnmUF,
			  char* cidMunicipio,
			  char* cnmMunicipio,
			  char* cidHorarioVerao,
			  char* cnrHorarioVerao,
			  char* cdtInicio,
			  char* cdtFim,
			  char* cinRelatorio
			  );

	void ZeraFeriado( void );

};

#endif
