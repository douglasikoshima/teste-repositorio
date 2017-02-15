// Util.h: interface for the Util class.
//
//////////////////////////////////////////////////////////////////////

#if !defined(AFX_UTIL_H__9669157D_1CF5_4CDF_9501_BCE2E170DB6A__INCLUDED_)
#define AFX_UTIL_H__9669157D_1CF5_4CDF_9501_BCE2E170DB6A__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000
#include<string.h>

class UtilWorkflow  
{
public:
	UtilWorkflow();
	virtual ~UtilWorkflow();
	static char* format(char*,char*);
	static char* strnullcpy(char*str,const char*str2);
};

#endif // !defined(AFX_UTIL_H__9669157D_1CF5_4CDF_9501_BCE2E170DB6A__INCLUDED_)
