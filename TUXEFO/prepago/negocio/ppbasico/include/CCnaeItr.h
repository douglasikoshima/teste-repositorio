#ifndef CCNAEItrH
#define CCNAEItrH

#include <stdlib.h>
#include <string.h>

#include "Global.h"

struct STCnaeRegistro
{
    char szIdCnae[LEN_IDCNAE + LEN_EOS];
    char szCdCnae[LEN_CDCNAE + LEN_EOS];
    char szDsCnae[LEN_DSCNAE + LEN_EOS];
    char szCdCfop[LEN_CDCFOP + LEN_EOS];
};

class CCnaeItr
{
public:
	CCnaeItr();
	~CCnaeItr();
	int Primeiro( void );
	int Ultimo( void );
	int Quantidade( void );
    bool buscaCnae( void );
	STCnaeRegistro* Registro(int nPosicao);

private:
	STCnaeRegistro* stcrCnae;
	int _iQuantidade;
	int _iPosicao;

protected:
	void Add( 
				char* szIdCnae,
				char* szCdCnae,
				char* szDsCnae,
				char* szCdCfop
			);
	void ZeraCnae( void );

};

#endif
