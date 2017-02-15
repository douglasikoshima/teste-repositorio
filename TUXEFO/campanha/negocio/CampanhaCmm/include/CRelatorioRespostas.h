//////////////////////////////////////////////////////////////////////
// CRelatorioRespostas.h: interface for the CRelatorioRespostas class.
//////////////////////////////////////////////////////////////////////

#ifndef RelatorioRespostasHH
#define RelatorioRespostasHH

#include "CRelatorioCampanha.h"
#include "SRelatorioRespostas.h"
#include <tuxfw.h>

#define strlennull( y ) ( y == NULL ? 0 : strlen( y )  )

class CRelatorioRespostas : public CRelatorioCampanha
{
public:
	// Destrutor
	~CRelatorioRespostas() {};

	// Monta o relatório
	void montaRelatorio(struct SRelatorioCampanha*,XMLGen*);
	stErro CheckRelatorio(struct SRelatorioCampanha*);

private:
	struct SRelatorioRespostas* psRelatorio;
};

#endif