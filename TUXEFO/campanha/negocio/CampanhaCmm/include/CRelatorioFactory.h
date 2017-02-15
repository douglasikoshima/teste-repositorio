//////////////////////////////////////////////////////////////////////
// CRelatorioFactory.h: interface for the CRelatorioFactory class.
//////////////////////////////////////////////////////////////////////

#ifndef RelatorioFactoryHH
#define RelatorioFactoryHH

#include "CRelatorioCampanha.h"
#include "CRelatorioGerenciamento.h"
#include "CRelatorioEfetividade.h"
#include "CRelatorioOperador.h"
#include "CRelatorioMotivos.h"
#include "CRelatorioRespostas.h"
#include "CRelArqResp.h"
#include "CRelArqResult.h"

class CRelatorioFactory : public  CRelatorioGerenciamento, public CRelatorioEfetividade, public CRelatorioOperador, public CRelatorioMotivos, public CRelatorioRespostas, public CRelArqResp, public CRelArqResult
{
public:
	// Construtor/Destrutor
	CRelatorioFactory() {};
	~CRelatorioFactory() {};
	// Recupera instância de relatório
	static CRelatorioCampanha* getRelatorio(DOMNode*,XMLGen*, int user);
	// Realiza a checagem dos parametros de entrada..
	static stErro VerificaRelatorio(DOMNode*);
	static SRelatorioCampanha* setData(DOMNode* dnode); 
	static CRelatorioCampanha* getInstance(DOMNode* dnode);
};

#endif

