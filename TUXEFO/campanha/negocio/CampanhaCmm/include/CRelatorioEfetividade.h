//////////////////////////////////////////////////////////////////////
// CRelatorioEfetividade.h: interface for the CRelatorioEfetividade class.
//////////////////////////////////////////////////////////////////////

#ifndef RelatorioEfetividadeHH
#define RelatorioEfetividadeHH

#include "CRelatorioCampanha.h"
#include "SRelatorioEfetividade.h"
#include <tuxfw.h>

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CRelatorioEfetividade : public CRelatorioCampanha
{
public:
	// Destrutor
	~CRelatorioEfetividade() {};

	// Monta o relatório
	void montaRelatorio(struct SRelatorioCampanha*,XMLGen*);
	stErro CheckRelatorio(struct SRelatorioCampanha*);

private:
	struct SRelatorioEfetividade* psRelatorio;
};

#endif