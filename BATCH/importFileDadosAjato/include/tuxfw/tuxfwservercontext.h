/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.6.1 $
 * @CVS     $Author: jones $ - $Date: 2013/07/10 18:04:38 $
 * @ID      $Id: tuxfwservercontext.h,v 1.1.6.1 2013/07/10 18:04:38 jones Exp $
 **/

#if !defined(AFX_TUXFWSERVERCONTEXT_H__816AA6ED_2E2D_4417_9105_80C4BC685229__INCLUDED_)
#define AFX_TUXFWSERVERCONTEXT_H__816AA6ED_2E2D_4417_9105_80C4BC685229__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "tuxfwlog.h"

//namespace tuxfw
//{

//##ModelId=41079D28032C
class TuxServerContext
{
public:
	//##ModelId=41079D28033C
	TuxServerContext( char* execName);
//  TuxServerContext( DOMNode* XMLConfig );

	//##ModelId=41079D28033D
	virtual ~TuxServerContext();
	//
	// setters
	//##ModelId=41079D28033F
	void setLogWritter( TuxLog* plogwritter );
	// getters
	//##ModelId=41079D280341
	TuxLog* getLogWritter( void );
	//##ModelId=41079D280343
	DOMNode* getServiceParameters( char* svcName );
private:
	//##ModelId=41079D28034C
	int loadConfigFromFile();
	//##ModelId=41079D28034D
	char* serverName;
	//##ModelId=41079D28034F
	TuxLog*	logwritter;
	//##ModelId=41079D28035C
	DOMNode* xmlConfig;
	//
	//##ModelId=41079D280361
	XercesDOMParser* xml_p;
	//##ModelId=41079D28036C
 	MemBufInputSource* mbis;
};

extern TuxLog* tuxfw_getlogger(void);
extern int tuxfw_initserverContext(char* execName);
//extern "C" void tuxfw_terminate(void );
//extern int tuxfw_serverinit();
int tuxfw_serverinit(int argc, char *argv[]);
extern int tuxfw_serverend();
extern "C" void tuxfw_singnalHandler(int);

//}
#endif // !defined(AFX_TUXFWSERVERCONTEXT_H__816AA6ED_2E2D_4417_9105_80C4BC685229__INCLUDED_)
