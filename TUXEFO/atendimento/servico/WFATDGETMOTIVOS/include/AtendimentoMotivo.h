// AtendimentoMotivo.h: interface for the AtendimentoMotivo class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ATENDIMENTOMOTIVO_H__31D07B9E_9889_4D5F_885C_339B153E08C3__INCLUDED_)
#define AFX_ATENDIMENTOMOTIVO_H__31D07B9E_9889_4D5F_885C_339B153E08C3__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>

#include<tuxfw.h>

class AtendimentoMotivo  
{
public:
	AtendimentoMotivo();
	virtual ~AtendimentoMotivo();
	int listMotivos(char*idTabelaMotivo,XMLGen*xml);
// int listMotivos(char *idAtividade,XMLGen *xml);
	int listAllMotivos(XMLGen*xml);
    void sql_error_ATDGETMOTIVOS( sqlca * sqlca );
};

#endif // !defined(AFX_ATENDIMENTOMOTIVO_H__31D07B9E_9889_4D5F_885C_339B153E08C3__INCLUDED_)
