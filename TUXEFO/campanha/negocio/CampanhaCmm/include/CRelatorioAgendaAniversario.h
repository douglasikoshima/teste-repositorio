//////////////////////////////////////////////////////////////////////
// CRelatorioEfetividade.h: interface for the CRelatorioEfetividade class.
//////////////////////////////////////////////////////////////////////

#ifndef RelatorioAGENDAANIVERSARIOHH
#define RelatorioAGENDAANIVERSARIOHH

#include "CRelatorioCampanha.h"
#include "SRelatorioAgendaAniversario.h"
#include <tuxfw.h>

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CRelatorioAgAni : public CRelatorioCampanha
{
public:
	// Destrutor
	~CRelatorioAgAni() {};

	// Monta o relatório
	void montaRelatorio(struct SRelatorioCampanha*,XMLGen*);
	stErro CheckRelatorio(struct SRelatorioCampanha*);

private:
	struct SRelatorioAgendaAniversario* psRelatorio;
};

#endif