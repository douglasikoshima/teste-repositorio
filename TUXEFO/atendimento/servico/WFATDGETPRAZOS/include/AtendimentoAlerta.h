// AtendimentoAlerta.h: interface for the AtendimentoAlerta class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ATENDIMENTOALERTA_H__D21945FF_6797_4BD4_928A_FECCA685C00A__INCLUDED_)
#define AFX_ATENDIMENTOALERTA_H__D21945FF_6797_4BD4_928A_FECCA685C00A__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include "tuxfw.h"

class AtendimentoAlerta  
{
public:
	AtendimentoAlerta();
	virtual ~AtendimentoAlerta();
	int getWorkflowAlerta(XMLGen*xml_g);
	int getWorkflowAlertaCRI(XMLGen*xml_g,unsigned long _idPessoaUsuario);
};

#endif // !defined(AFX_ATENDIMENTOALERTA_H__D21945FF_6797_4BD4_928A_FECCA685C00A__INCLUDED_)
