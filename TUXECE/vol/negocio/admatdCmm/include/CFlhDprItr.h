#ifndef CContatoFolhaEnvioDPRItrH
#define CContatoFolhaEnvioDPRItrH

#include <stdlib.h>
#include <string.h>


struct STContatoFolhaEnvioDPRRegistro
{
	char cidContato[21+1];
};

class CContatoFolhaEnvioDPRItr
{
public:
	CContatoFolhaEnvioDPRItr();
	~CContatoFolhaEnvioDPRItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoFolhaEnvioDPRRegistro* Registro( void );
	STContatoFolhaEnvioDPRRegistro* Registro(int nPosicao);

private:
	STContatoFolhaEnvioDPRRegistro* stcrContatoFolhaEnvioDPR;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContato );

	void ZeraContatoFolhaEnvioDPR( void );

};

#endif
