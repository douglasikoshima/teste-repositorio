// Linha.h: interface for the Linha class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_LINHA_H__A7730959_93F1_4843_8462_08F2B9310DD1__INCLUDED_)
#define AFX_LINHA_H__A7730959_93F1_4843_8462_08F2B9310DD1__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include"Util.h"

class Linha  
{
public:
	Linha();
	virtual ~Linha();
	void setIdLinha(char*);
	void setNrLinha(char*);
	char*getIdLinha();
	char*getNrLinha();
private:
	char idLinha[21];
	char nrLinha[256];
};

#endif // !defined(AFX_LINHA_H__A7730959_93F1_4843_8462_08F2B9310DD1__INCLUDED_)
