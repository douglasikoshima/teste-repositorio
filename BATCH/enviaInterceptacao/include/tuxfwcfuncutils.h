/**!
 * 
 * @module  
 * @purpose General C utility functions
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5110702 $ - $Date: 2009/08/26 19:24:36 $
 * @ID      $Id: tuxfwcfuncutils.h,v 1.1.4.1 2009/08/26 19:24:36 a5110702 Exp $
 **/

//namespace tuxfw
//{

//#include <stddef.h>
#include <stdarg.h>
#include <time.h>
//#include <stdio.h>
//#include <stdlib.h>
//#include <ctype.h>
//#include <string.h>
//#include <userlog.h>

// Clona uma string em nova posicao de memória
char* derivStr(char*_x);
int createTimeStampStaticEx(char *timeStr, time_t tim);
void dumpInFile(const char *filePathName, const char *dumpText);
//
//
struct tuxfw_StrcCPtrNode;
typedef struct tuxfw_StrcCPtrNode tuxfw_CPtrNode,*tuxfw_PCPtrNode;
extern tuxfw_PCPtrNode tuxfw_gPCPtrNode;
void tuxfw_pushTraceCPtr( char* tracePoint, char* charPtr );
void tuxfw_popTraceCPtr( char* tracePoint, char* charPtr );
void tuxfw_dumpTraceCPtr();
void tuxfw_cleanTraceCPtr();

#ifndef _PORTABLE_SNPRINTF_H_
#define _PORTABLE_SNPRINTF_H_

#define PORTABLE_SNPRINTF_VERSION_MAJOR 2
#define PORTABLE_SNPRINTF_VERSION_MINOR 2

#ifdef HAVE_SNPRINTF
#include <stdio.h>
#else
extern int snprintf(char *, size_t, const char *, /*args*/ ...);
extern int vsnprintf(char *, size_t, const char *, va_list);
#endif

#if defined(HAVE_SNPRINTF) && defined(PREFER_PORTABLE_SNPRINTF)
extern int portable_snprintf(char *str, size_t str_m, const char *fmt, /*args*/ ...);
extern int portable_vsnprintf(char *str, size_t str_m, const char *fmt, va_list ap);
#define snprintf  portable_snprintf
#define vsnprintf portable_vsnprintf
#endif

extern int asprintf  (char **ptr, const char *fmt, /*args*/ ...);
extern int vasprintf (char **ptr, const char *fmt, va_list ap);
extern int asnprintf (char **ptr, size_t str_m, const char *fmt, /*args*/ ...);
extern int vasnprintf(char **ptr, size_t str_m, const char *fmt, va_list ap);


#endif
