//////////////////////////////////////////////////////////////////////
// CRelatorioOperador.h: interface for the CRelatorioOperador class.
//////////////////////////////////////////////////////////////////////

#ifndef RelatorioOperadorHH
#define RelatorioOperadorHH

#include "CRelatorioCampanha.h"
#include "SRelatorioOperador.h"
#include <tuxfw.h>

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CRelatorioOperador : public CRelatorioCampanha
{
public:
	// Destrutor
	~CRelatorioOperador() {};

	// Monta o relatório
	void montaRelatorio(struct SRelatorioCampanha*,XMLGen*);
	stErro CheckRelatorio(struct SRelatorioCampanha*);

private:
	struct SRelatorioOperador* psRelatorio;
};

#endif