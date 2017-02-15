/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2014/08/14 15:45:46 $
 * @ID      $Id: tuxfwlogfile.h,v 1.1.6.2 2014/08/14 15:45:46 a5114878 Exp $
 **/

#if !defined(AFX_LOGFILE_H__770DDC59_202A_429A_9C00_42E79E0E893D__INCLUDED_)
#define AFX_LOGFILE_H__770DDC59_202A_429A_9C00_42E79E0E893D__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "tuxfwlog.h"

class TuxLogFile:public TuxLog
{
public:
	TuxLogFile(char* pPathLog, char* pPrefix);
	TuxLogFile(char *pPathLog);
	virtual ~TuxLogFile();
	//
	int open(void);
	int close(void);
	void setFilePrefix( char* prefix );
private:
	void internalWrite(char * pszValue);
	void internalFlush();

	FILE *pFileLog;
	char* prepareLogName();
	char* logPath;
	char* logPrefix;
};

//}
#endif // !defined(AFX_LOGFILE_H__770DDC59_202A_429A_9C00_42E79E0E893D__INCLUDED_)


