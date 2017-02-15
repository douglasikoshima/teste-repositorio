// Linha.h: interface for the Linha class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_LINHA_H__96FEBB70_8A33_4611_8EAC_DA1FEF8E4FA9__INCLUDED_)
#define AFX_LINHA_H__96FEBB70_8A33_4611_8EAC_DA1FEF8E4FA9__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include<tuxfw.h>

class Linha  
{
public:
	Linha();
	virtual ~Linha();
	int getStatusLinha(char*nrLinha,char*cdDDD,char *estadoLinha);

};

#endif // !defined(AFX_LINHA_H__96FEBB70_8A33_4611_8EAC_DA1FEF8E4FA9__INCLUDED_)
