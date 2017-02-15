#ifndef CNvlHieItrH
#define CNvlHieItrH

#include <stdlib.h>
#include <string.h>

struct STNivelHierarquiaRegistro
{
	char cidNivelHierarquia[21+1];
	char cidNivelPai[21+1];
	char cidNivel[21+1];
	char cidCargo[21+1];
};

class CNvlHieItr
{
public:
	CNvlHieItr();
	~CNvlHieItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STNivelHierarquiaRegistro* Registro( void );
	STNivelHierarquiaRegistro* Registro(int nNivelHierarquia);

private:
	STNivelHierarquiaRegistro* stcrNivelHierarquia;
	int _iQuantidade;
	int _iNivelHierarquia;

protected:
	void Add( char* cidNivelHierarquia,
              char* cidNivelPai, 
              char* cidNivel, 
              char* cidCargo );
	void ZeraNivelHierarquia( void );

};

#endif