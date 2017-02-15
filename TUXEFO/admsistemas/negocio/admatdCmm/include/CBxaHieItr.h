#ifndef CBaixaHierarquiaItrH
#define CBaixaHierarquiaItrH

#include <stdlib.h>
#include <string.h>


struct STBaixaHierarquiaRegistro
{
	char cidBaixa[21+1];
	char cidBaixaHierarquia[21+1];
};

class CBaixaHierarquiaItr
{
public:
	CBaixaHierarquiaItr();
	~CBaixaHierarquiaItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STBaixaHierarquiaRegistro* Registro( void );
	STBaixaHierarquiaRegistro* Registro(int nPosicao);

private:
	STBaixaHierarquiaRegistro* stcrBaixaHierarquia;
	int _iQuantidade;
	int _iPosicao;

protected:

	void Add( 
			char* cidBaixa,
			char* cidBaixaHierarquia );

	void ZeraBaixaHierarquia( void );

};

#endif
