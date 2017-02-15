#ifndef CContatoItrH
#define CContatoItrH

#include <stdlib.h>
#include <string.h>

const tamBlocoCCItr = 200;

struct STContatoRegistro
{
	char cidContato[255+1];
	char cidContatoPai[255+1];
	char cidNomeContato[255+1];
	char cnmContato[255+1];
	char cinDisponibilidade[255+1];
	char cdsPath[2048+1];
	int  iLevel;
	int  iFolha;
};

class CContatoItr
{
public:
	CContatoItr();
	~CContatoItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoRegistro* Registro( void );
	STContatoRegistro* Registro(int nPosicao);
	char* GetErro() { return cErroGeral; }

private:
	STContatoRegistro* stcrContato;
	int _iQuantidade;
	int _iPosicao;
	char cErroGeral[255+1];

protected:

	void Add( char* cidContato,
			  char* cidContatoPai,
			  char* cidNomeContato,
			  char* cnmContato,
			  char* cinDisponibilidade,
			  char* cdsPath,
			  int iLevel,
			  int  iFolha);

	void ZeraContato( void );
	void  SetErro( char* cErro );

};

#endif
