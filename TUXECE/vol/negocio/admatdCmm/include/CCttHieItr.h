#ifndef CContatoHierarquiaItrH
#define CContatoHierarquiaItrH

#include <stdlib.h>
#include <string.h>


struct STContatoHierarquiaRegistro
{
	char cidContato[21+1];
	char cidContatoPai[21+1];
};

class CContatoHierarquiaItr
{
public:
	CContatoHierarquiaItr();
	~CContatoHierarquiaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoHierarquiaRegistro* Registro( void );
	STContatoHierarquiaRegistro* Registro(int nPosicao);

private:
	STContatoHierarquiaRegistro* stcrContatoHierarquia;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidContato,
			char* cidContatoPai );

	void ZeraContatoHierarquia( void );

};

#endif
