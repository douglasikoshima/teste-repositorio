/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1 $
 * @CVS     $Author: rrusso $ - $Date: 2006/06/07 22:20:22 $
 * @ID      $Id: tuxfwlogfile.h,v 1.1 2006/06/07 22:20:22 rrusso Exp $
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

