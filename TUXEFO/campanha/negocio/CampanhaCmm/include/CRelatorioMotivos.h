//////////////////////////////////////////////////////////////////////
// CRelatorioMotivos.h: interface for the CRelatorioMotivos class.
//////////////////////////////////////////////////////////////////////

#ifndef RelatorioMotivosHH
#define RelatorioMotivosHH

#include "CRelatorioCampanha.h"
#include "SRelatorioMotivos.h"
#include <tuxfw.h>

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CRelatorioMotivos : public CRelatorioCampanha
{
public:
	// Destrutor
	~CRelatorioMotivos() {};

	// Monta o relatório
	void montaRelatorio(struct SRelatorioCampanha*,XMLGen*);
	stErro CheckRelatorio(struct SRelatorioCampanha*);

private:
	struct SRelatorioMotivos* psRelatorio;
};

#endif