#ifndef CNvlHieItrH
#define CNvlHieItrH

#include <stdlib.h>
#include <string.h>

struct STOperadoraDeptoRegistro
{
	char cidOperadoraDepto[21+1];
	char cidArea[21+1];
	char cidPessoaDeParaOperadora[21+1];
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
	STOperadoraDeptoRegistro* Registro( void );
	STOperadoraDeptoRegistro* Registro(int nOperadoraDepto);

private:
	STOperadoraDeptoRegistro* stcrOperadoraDepto;
	int _iQuantidade;
	int _iOperadoraDepto;

protected:
	void Add( char* cidOperadoraDepto,
              char* cidArea, 
              char* cidPessoaDeParaOperadora );
	void ZeraOperadoraDepto( void );

};

#endif