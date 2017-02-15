#ifndef CHorarioVeraoItrH
#define CHorarioVeraoItrH

#include <stdlib.h>
#include <string.h>


struct STHorarioVeraoRegistro
{
	char cidHorarioVerao[21+1];
	char cidUF[21+1];
	char cnrHorarioVerao[21+1];
	char cdtInicio[12+1];
	char cdtFim[12+1];
};

class CHorarioVeraoItr
{
public:
	CHorarioVeraoItr();
	~CHorarioVeraoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STHorarioVeraoRegistro* Registro( void );
	STHorarioVeraoRegistro* Registro(int nPosicao);

private:
	STHorarioVeraoRegistro* stcrHorarioVerao;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidHorarioVerao,
			char* cidUF,
			char* cnrHorarioVerao,
			char* cdtInicio,
			char* cdtFim );

	void ZeraHorarioVerao( void );

};

#endif
