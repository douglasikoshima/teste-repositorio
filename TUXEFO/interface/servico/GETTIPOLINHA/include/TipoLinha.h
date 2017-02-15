// TipoLinha.h: interface for the TipoLinha class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_TIPOLINHA_H__68254CC8_AD82_463E_8953_48DA5E11071E__INCLUDED_)
#define AFX_TIPOLINHA_H__68254CC8_AD82_463E_8953_48DA5E11071E__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

class TipoLinha  
{
public:
	TipoLinha();
	virtual ~TipoLinha();
	int getTipoLinha(char*cdDDD,char*cdNumTelefone,char*tipoLinha,char*idLinhaTelefonica);

};

#endif // !defined(AFX_TIPOLINHA_H__68254CC8_AD82_463E_8953_48DA5E11071E__INCLUDED_)
