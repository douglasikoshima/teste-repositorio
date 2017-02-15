// Util.h: interface for the Util class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_UTIL_H__0110123F_AB00_47C8_B288_0691CA4DC3FE__INCLUDED_)
#define AFX_UTIL_H__0110123F_AB00_47C8_B288_0691CA4DC3FE__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include<string.h>
#include<stdlib.h>

class Util  
{
public:
	Util();
	virtual ~Util();
	static void strnullcpy(char*str,char*buffer);
	static void strnullncpy(char*str,char*buffer,int length);
	static void strtrimcpy(char*str,char*buffer);
	static void alltrim(char *pszString);
	static void ltrim(char *pszString);
	static void rtrim(char *pszString);
	static int isNull(char*);
	static void formatDate(char*data,char*formateDate);
	static int checkLength(char*str,int length);
};

#endif // !defined(AFX_UTIL_H__0110123F_AB00_47C8_B288_0691CA4DC3FE__INCLUDED_)
