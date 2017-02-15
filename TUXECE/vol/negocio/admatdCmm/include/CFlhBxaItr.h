#ifndef CContatoFolhaBaixaItrH
#define CContatoFolhaBaixaItrH

#include <stdlib.h>
#include <string.h>


struct STContatoFolhaBaixaRegistro
{
	char cidContatoFolhaBaixa[21+1];
	char cidContato[21+1];
	char cidBaixa[21+1];
};

class CContatoFolhaBaixaItr
{
public:
	CContatoFolhaBaixaItr();
	~CContatoFolhaBaixaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoFolhaBaixaRegistro* Registro( void );
	STContatoFolhaBaixaRegistro* Registro(int nPosicao);

private:
	STContatoFolhaBaixaRegistro* stcrContatoFolhaBaixa;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContatoFolhaBaixa,
			char* cidContato,
			char* cidBaixa );

	void ZeraContatoFolhaBaixa( void );

};

#endif
