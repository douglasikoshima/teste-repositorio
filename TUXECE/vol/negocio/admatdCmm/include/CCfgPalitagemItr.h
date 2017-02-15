#ifndef CCfgPalitagemItrH
#define CCfgPalitagemItrH

#include <stdlib.h>
#include <string.h>


struct STCfgPalitagemRegistro
{
    char cidSistemaOrigem[39+1];
    char csgServico[100+1];
    char cdsServico[60+1];
    char cidProcedencia[39+1];
    char cdtUltimaAlteracao[64+1];
};

class CCfgPalitagemItr
{
public:
	CCfgPalitagemItr();
	~CCfgPalitagemItr();
	int Primeiro( void );
	int Proximo( void );
	int Anterior( void );
	int Ultimo( void );
	int Quantidade( void );
	STCfgPalitagemRegistro* Registro( void );
	STCfgPalitagemRegistro* Registro(int nPosicao);

private:
	STCfgPalitagemRegistro* stcrCfgPalitagem;
	int _iQuantidade;
	int _iPosicao;

protected:

        void Add(
			char* cidSistemaOrigem,
			char* csgSistemaOrigem,
			char* cdsServico,
			char* cidProcedencia,
            char* idUser
            );

	void ZeraCfgPalitagem( void );

};

#endif
