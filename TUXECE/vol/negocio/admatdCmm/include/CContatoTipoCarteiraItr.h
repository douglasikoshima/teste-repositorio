#ifndef CContatoTipoCarteiraItrH
#define CContatoTipoCarteiraItrH

#include <stdlib.h>
#include <string.h>


struct STContatoTipoCarteiraRegistro
{
	char cidContatoTipoCarteira[21+1];
	char cidContato[21+1];
	char cidTipoCarteira[21+1];
};

class CContatoTipoCarteiraItr
{
public:
	CContatoTipoCarteiraItr();
	~CContatoTipoCarteiraItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoTipoCarteiraRegistro* Registro( void );
	STContatoTipoCarteiraRegistro* Registro(int nPosicao);

private:
	STContatoTipoCarteiraRegistro* stcrContatoTipoCarteira;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContatoTipoCarteira,
			char* cidContato,
			char* cidTipoCarteira );

	void ZeraContatoTipoCarteira( void );

};

#endif
