// ControleConsumo.h: interface for the ControleConsumo class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_CONTROLECONSUMO_H__4AAD631A_1D1D_42DC_971F_EAD3062106A4__INCLUDED_)
#define AFX_CONTROLECONSUMO_H__4AAD631A_1D1D_42DC_971F_EAD3062106A4__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "ServiceAtlys.h"
#include "Servico.h"
#include "RegistroContato.h"

class ControleConsumo : public ServiceAtlys  
{
public:
	ControleConsumo();
	virtual ~ControleConsumo();
	int getControleConsumo(XMLGen *xmlgen,char*accessNbr);
	int setControleConsumo(XMLGen *xmlgen,char*accessNbr,char*servico,char*operacao,char*dia);
	int avisoSobreDemanda(char*accessNbr);
	int registrarContato(char*ddd,char*linha,char*cdContato,char*idUser);
private:
	TuxHelper tuxhp;
	ListServicos m_servicos;
	char *m_sbscrpId;
	char *m_svcId;
	bool m_habilidaPercentual;

};

#endif // !defined(AFX_CONTROLECONSUMO_H__4AAD631A_1D1D_42DC_971F_EAD3062106A4__INCLUDED_)
