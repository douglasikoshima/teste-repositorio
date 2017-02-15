// AtendimentoTipoNota.h: interface for the AtendimentoTipoNota class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ATENDIMENTOTIPONOTA_H__55223897_4F1C_46D8_A514_594F005B0954__INCLUDED_)
#define AFX_ATENDIMENTOTIPONOTA_H__55223897_4F1C_46D8_A514_594F005B0954__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include<tuxfw.h>

class AtendimentoTipoNota  
{
public:
	AtendimentoTipoNota();
	virtual ~AtendimentoTipoNota();
	int listNotas(XMLGen*xml);

};

#endif // !defined(AFX_ATENDIMENTOTIPONOTA_H__55223897_4F1C_46D8_A514_594F005B0954__INCLUDED_)
