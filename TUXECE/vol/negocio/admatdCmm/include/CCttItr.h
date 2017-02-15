#ifndef CContatoItrH
#define CContatoItrH

#include <stdlib.h>
#include <string.h>


struct STContatoRegistro
{
	char cidContato[21+1];
	char cidContatoPai[21+1];
	char cidNomeContato[21+1];
	char cnmContato[255+1];
	char cinDisponibilidade[21+1];
	char cdsPath[2024+1];
	int  iLevel;
	int  iFolha;
	char cdsTipoProcesso[255+1];
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
	int BytesAlocados( void );
	STContatoRegistro* Registro( void );
	STContatoRegistro* Registro(int nPosicao);
	char* GetErro() { return cErroGeral; }

private:
	STContatoRegistro* stcrContato;
	int _iQuantidade;
	int _iPosicao;
    const int tamBlocoCCItr;

	char cErroGeral[255+1];

protected:

	void Add( char* cidContato,
			  char* cidContatoPai,
			  char* cidNomeContato,
			  char* cnmContato,
			  char* cinDisponibilidade,
			  char* cdsPath,
			  int iLevel,
			  int  iFolha,
              char* cdsTipoProcesso);

	void ZeraContato( void );
	void  SetErro( char* cErro );

};

#endif
