/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.2.1 $
 * @CVS     $Author: rrusso $ - $Date: 2008/01/04 14:48:51 $
 * @ID      $Id: tuxfwlog.h,v 1.1.2.1 2008/01/04 14:48:51 rrusso Exp $
 **/

#if !defined(AFX_LOG_H__770DDC59_202A_429A_9C00_42E79E0E893D__INCLUDED_)
#define AFX_LOG_H__770DDC59_202A_429A_9C00_42E79E0E893D__INCLUDED_

#if _MSC_VER > 1000
#pragma once
#endif // _MSC_VER > 1000

#include "tuxfwlogexception.h"

//namespace tuxfw
//{

// Constantes para Log
//
#define TUXFW_LOG_LEN_EOS	  1
#define TUXFW_LOG_PREFIX    "LOG_"

//
// Atenção - importante !!!
// Rever utilização de buffers de log quanto a consistencia de buffer overflow
// possivelmente mudando as estruturas para snprintf
//
#define TUXFW_LOG_LEN_AUX			 32768
#define TUXFW_LOG_TIPOLOG_LEN_AUX		10
#define TUXFW_LOG_PATHFILE_LEN_AUX     255

//
// Macros para facilitar a utilização dos logs do framework
//
// Definicoes de log DEBUG
// tuxfw_getlogger : Para chamar log em funcoes fora do objeto.
#define ULOG			 tuxfw_getlogger()->debug
#define ULOG_INT(vrint)  ULOG(#vrint"  : [%d]",vrint)
#define ULOG_VAR(vrora)  ULOG(#vrora"  : [%.*s]",vrora.len,vrora.arr)
#define ULOG_STR(vrstr)  ULOG(#vrstr"  : [%s]",vrstr)

//Nível de Log information
#define ULOGI            tuxfw_getlogger()->information
#define ULOG_START(nome) ULOGI("start: %s ",nome)
#define ULOG_END(nome)   ULOGI("end: %s ",nome)

//Nivel de Log warning
#define ULOGW            tuxfw_getlogger()->warning

//Nivel de Log error
#define ULOGE            tuxfw_getlogger()->error

class TuxLog
{
public:
	TuxLog();
	virtual ~TuxLog();
public:
	virtual int open(void);
	void write(LogType iTipoLog, char *pszFormat, ...);
	virtual int close(void);
	void debug(char *pszFormat, ...);
	void information(char *pszFormat, ...);
	void warning(char *pszFormat, ...);
	void error(char *pszFormat, ...);
	void critical(char *pszFormat, ...);
	void setLevel(LogType level);
	LogType getLevel();

protected:
	virtual void internalWrite(LogType iTipoLog, char * szValue);
	virtual void internalWrite(char * szValue);
	virtual void internalFlush();
	void refresh();					// realiza controle de reciclagem e flush meios de armazenamento de log
	LogType iMasterLevel;
	char lineBuffer[TUXFW_LOG_LEN_AUX + TUXFW_LOG_LEN_EOS];
	bool alwaysFlush;   // flag para flush forçado em modo debug;
	//
	int isOpen;
	int refRecicleLog; // Dia do ano como referencia para reciclagem de log
	int refFlush;       // Marca de referencia para flush forçado temporizado
};

//}
#endif // !defined(AFX_LOG_H__770DDC59_202A_429A_9C00_42E79E0E893D__INCLUDED_)


