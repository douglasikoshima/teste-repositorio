#ifndef CContatoCombosItrH
#define CContatoCombosItrH

#include <stdlib.h>
#include <string.h>


struct STContatoCombosRegistro
{
	char cidCombo[21+1];
	char csgCombo[255+1];
	char cdsCombo[255+1];
};

class CContatoCombosItr
{
public:
	CContatoCombosItr();
	~CContatoCombosItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STContatoCombosRegistro* Registro( void );
	STContatoCombosRegistro* Registro(int nPosicao);

private:
	STContatoCombosRegistro* stcrContatoCombos;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( char* cidCombo
			 ,char* csgCombo
			 ,char* cdsCombo );

	void ZeraContatoCombos( void );

};

#endif
