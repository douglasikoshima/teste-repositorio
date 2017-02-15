#include <stdio.h>
#include <errno.h>
#include <signal.h>
#include <dirent.h>
#include <stdlib.h>
#include <string.h>
#include <iostream.h>
#ifndef WIN32
#include <unistd.h>
#endif
#include <ctype.h>
#include <time.h>
#include <sys/time.h>
#include <sys/types.h>
#include <sys/times.h>
#include <sys/param.h>

#define  TICKS   HZ

#ifndef LINHA_OFERTA_H
#define LINHA_OFERTA_H

#ifndef _MAX_PATH
#   define _MAX_PATH 1024
#endif

#define TAM_BD_PASSWD 32
#define TAM_BD_USRNAME 32
#define TAM_BD_INST 32
#define TAM_TX_PASSWD TAM_BD_PASSWD
#define TAM_TX_USRNAME TAM_BD_USRNAME
#define TAM_TX_CLT 32
#define TAM_TX_GEN 32

#define STATUS_ERRO     0
#define STATUS_SUCESSO  1
#define STATUS_VAZIO    2

static long     time_start, time_stop;
static struct tms   tms_start, tms_stop;
long   times();
static double   start, stop, seconds;

typedef struct
{
    char szPws[TAM_BD_PASSWD + 1];     // pass BD          pwd_db
    char szUsr[TAM_BD_USRNAME + 1];    // user DB          usr_db
    char szInst[TAM_BD_INST + 1];      // instancia BD     inst_db
    char szPwsTux[TAM_TX_PASSWD + 1];  // pass TUXEDO      pwd_tux
    char szUsrTux[TAM_TX_USRNAME + 1]; // user TUXEDO      usr_tux
    char szCltTux[TAM_TX_CLT + 1];     // client TUX       clt_tux
    char szPwsTuxGen[TAM_TX_GEN + 1];  // pass TUX GEN     pwd_tux_gen
    char szPath[_MAX_PATH + 1];
    char szPathToGo[_MAX_PATH + 1];
} TParamConf;

/* prototipos */
int    DBConnect(char *pUsr, char *pPwd, char *pInst);
void   DBDisconnect(void);
int    ObtemParamConf(TParamConf *ptParamConf);
void   moverArquivo( const char *pszNomeArquivoOrigem, const char *pszNomeArquivoDestino );
void   BuscaArquivoProcessar( char * nmArquivoProc );
void   ToStr( char * bf );
void   t_start( void );
void   t_stop( void );
double t_getrtime( void );
void   termination_handler( int signum );
void   LimpaHistorico( void );


/*******************************************************************************
 * Macros para manipulacao de dados ProC/C++
 *******************************************************************************/
#define endOraStr(varstr)      varstr.arr[varstr.len]= '\0'
#define oraToStr(bstr,vchar)   if(!bstr) strncpy(bstr,vchar.arr,vchar.len)
#define strToOra(vchar,bstr)   vchar.len = strlen(bstr);strncpy((char *)vchar.arr,bstr,vchar.len);vchar.arr[vchar.len] = 0
#define strconv(buffer,fonte)  sprintf(buffer,"%d",fonte)


#endif
