// TipoLinha.h: interface for the TipoLinha class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_ENDERECO_H__68254CC8_AD82_463E_8953_48DA5E11071E__INCLUDED_)
#define AFX_ENDERECO_H__68254CC8_AD82_463E_8953_48DA5E11071E__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#define STRCPY_TO_ORA(dest, source){ \
        dest.len = (unsigned short) strlen(source); \
        strcpy((char *) dest.arr, (const char *) source);}

#define STRCPY_FROM_ORA(dest, source)\
        dest[source.len] = 0;\
        strncpy((char *)dest,(const char *)source.arr, source.len)

#define LEN_NUMBER_ORA                  25

class endereco
{
public:
    endereco();
    virtual ~endereco();
    void getEndereco(char *pNrCEP, char *pQtdReg, XMLGen* xml_g, int *piCount);
};

#endif
