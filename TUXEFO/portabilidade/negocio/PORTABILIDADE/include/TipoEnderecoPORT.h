#ifndef TIPOENDERECOHPORT
#define TIPOENDERECOHPORT

#include "tuxfw.h"
#include "PPGlobalPORT.h"
#include "TipoEnderecopcPORT.h"

class CTipoEndereco
{

public:
	TTipoEndereco tTipoEndereco;
	CTipoEnderecopc clTipoEnderecopc;

    CTipoEndereco(void);
    bool buscaTipoEndereco(void);

    void setSgTipoEndereco(char *pszSgTipoEndereco);

    char *getIdTipoEndereco(void);
    char *getSgTipoEndereco(void);
};

#endif
