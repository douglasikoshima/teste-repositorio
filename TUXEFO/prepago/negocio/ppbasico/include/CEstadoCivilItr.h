#ifndef CEstadoCivilItrH
#define CEstadoCivilItrH

#include <stdlib.h>
#include <string.h>


struct STEstadoCivilRegistro
{
	char cidEstadoCivil[21+1];
	char csgEstadoCivil[255+1];
	char cdsEstadoCivil[255+1];
};

class CEstadoCivilItr
{
public:
	CEstadoCivilItr();
	~CEstadoCivilItr();
	int Primeiro( void );
	int Ultimo( void );
	int Quantidade( void );
	STEstadoCivilRegistro* Registro( void );
	STEstadoCivilRegistro* Registro(int nPosicao);

private:
	STEstadoCivilRegistro* stcrEstadoCivil;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidEstadoCivil,
			char* csgEstadoCivil,
			char* cdsEstadoCivil );

	void ZeraEstadoCivil( void );

};

#endif
