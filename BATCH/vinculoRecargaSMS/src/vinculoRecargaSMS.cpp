
/* Result Sets Interface */
#ifndef SQL_CRSR
#  define SQL_CRSR
  struct sql_cursor
  {
    unsigned int curocn;
    void *ptr1;
    void *ptr2;
    unsigned int magic;
  };
  typedef struct sql_cursor sql_cursor;
  typedef struct sql_cursor SQL_CURSOR;
#endif /* SQL_CRSR */

/* Thread Safety */
typedef void * sql_context;
typedef void * SQL_CONTEXT;

/* Object support */
struct sqltvn
{
  unsigned char *tvnvsn; 
  unsigned short tvnvsnl; 
  unsigned char *tvnnm;
  unsigned short tvnnml; 
  unsigned char *tvnsnm;
  unsigned short tvnsnml;
};
typedef struct sqltvn sqltvn;

struct sqladts
{
  unsigned int adtvsn; 
  unsigned short adtmode; 
  unsigned short adtnum;  
  sqltvn adttvn[1];       
};
typedef struct sqladts sqladts;

static struct sqladts sqladt = {
  1,1,0,
};

/* Binding to PL/SQL Records */
struct sqltdss
{
  unsigned int tdsvsn; 
  unsigned short tdsnum; 
  unsigned char *tdsval[1]; 
};
typedef struct sqltdss sqltdss;
static struct sqltdss sqltds =
{
  1,
  0,
};

/* File name & Package Name */
struct sqlcxp
{
  unsigned short fillen;
           char  filnam[63];
};
static const struct sqlcxp sqlfpn =
{
    62,
    "/home/wsaba/BATCH/vinculoRecargaSMS/src/vinculoRecargaSMS.pcpp"
};


static unsigned int sqlctx = 2102345084;


static struct sqlexd {
   unsigned long  sqlvsn;
   unsigned int   arrsiz;
   unsigned int   iters;
   unsigned int   offset;
   unsigned short selerr;
   unsigned short sqlety;
   unsigned int   occurs;
      const short *cud;
   unsigned char  *sqlest;
      const char  *stmt;
   sqladts *sqladtp;
   sqltdss *sqltdsp;
   unsigned char  **sqphsv;
   unsigned long  *sqphsl;
            int   *sqphss;
            short **sqpind;
            int   *sqpins;
   unsigned long  *sqparm;
   unsigned long  **sqparc;
   unsigned short  *sqpadto;
   unsigned short  *sqptdso;
   unsigned int   sqlcmax;
   unsigned int   sqlcmin;
   unsigned int   sqlcincr;
   unsigned int   sqlctimeout;
   unsigned int   sqlcnowait;
            int   sqfoff;
   unsigned int   sqcmod;
   unsigned int   sqfmod;
   unsigned char  *sqhstv[5];
   unsigned long  sqhstl[5];
            int   sqhsts[5];
            short *sqindv[5];
            int   sqinds[5];
   unsigned long  sqharm[5];
   unsigned long  *sqharc[5];
   unsigned short  sqadto[5];
   unsigned short  sqtdso[5];
} sqlstm = {12,5};

// Prototypes
extern "C" {
  void sqlcxt (void **, unsigned int *,
               struct sqlexd *, const struct sqlcxp *);
  void sqlcx2t(void **, unsigned int *,
               struct sqlexd *, const struct sqlcxp *);
  void sqlbuft(void **, char *);
  void sqlgs2t(void **, char *);
  void sqlorat(void **, unsigned int *, void *);
}

// Forms Interface
static const int IAPSUCC = 0;
static const int IAPFAIL = 1403;
static const int IAPFTL  = 535;
extern "C" { void sqliem(char *, int *); }

 static const char *sq0001 = 
"select IDATENDIMENTOPROTOCOLO ,CDAREAREGISTRO ,NRLINHA ,DSMENSAGEMENVIO ,INE\
NVIOSMS  from (select  /*+  index(FILASMSPROTOCOLO FILASMSPROTOCOLOIE1)  +*/ F\
ILASMSPROTOCOLO.IDATENDIMENTOPROTOCOLO ,FILASMSPROTOCOLO.CDAREAREGISTRO ,FILAS\
MSPROTOCOLO.NRLINHA ,FILASMSPROTOCOLO.DSMENSAGEMENVIO ,FILASMSPROTOCOLO.INENVI\
OSMS  from ATENDIMENTO.FILASMSPROTOCOLO FILASMSPROTOCOLO where FILASMSPROTOCOL\
O.DTENCERRAMENTO<SYSTIMESTAMP order by FILASMSPROTOCOLO.DTULTIMAALTERACAO ) wh\
ere ROWNUM<=:b0           ";

typedef struct { unsigned short len; unsigned char arr[1]; } VARCHAR;
typedef struct { unsigned short len; unsigned char arr[1]; } varchar;

/* cud (compilation unit data) array */
static const short sqlcud0[] =
{12,4130,31,0,0,
5,0,0,1,492,0,9,224,0,0,1,1,0,1,0,1,3,0,0,
24,0,0,1,0,0,13,228,0,0,5,0,0,1,0,2,9,0,0,2,9,0,0,2,9,0,0,2,9,0,0,2,3,0,0,
59,0,0,1,0,0,15,238,0,0,0,0,0,1,0,
74,0,0,2,226,0,3,397,0,0,5,5,0,1,0,1,9,0,0,1,9,0,0,1,9,0,0,1,9,0,0,1,9,0,0,
109,0,0,3,83,0,2,422,0,0,1,1,0,1,0,1,9,0,0,
128,0,0,4,0,0,29,428,0,0,0,0,0,1,0,
143,0,0,5,0,0,31,482,0,0,0,0,0,1,0,
158,0,0,6,0,0,27,754,0,0,4,4,0,1,0,1,97,0,0,1,10,0,0,1,10,0,0,1,10,0,0,
189,0,0,7,0,0,30,767,0,0,0,0,0,1,0,
204,0,0,8,126,0,4,796,0,0,1,0,0,1,0,2,3,0,0,
223,0,0,9,105,0,4,843,0,0,1,0,0,1,0,2,9,0,0,
242,0,0,10,103,0,4,882,0,0,1,0,0,1,0,2,9,0,0,
};


    /**
 * @modulo  Batch
 * @usecase Batch
 * @author
 * @version $Revision: 1.1.4.1 $
 * @CVS     $Author: a5116174 $ - $Date: 2010/09/06 19:00:59 $
 **/

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <iostream.h>
#include <ctype.h>

#include <errno.h>
#include <time.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>

#define MAX_RETRIES     10

#define LOCK            1
#define UNLOCK          0
#define KILL            2

#ifndef WIN32
#include <unistd.h>
#endif

#include <libxml/xpath.h>
#include <libxml/tree.h>
#include <libxml/parser.h>
#include <libxml/debugXML.h>
#include <libxml/xmlmemory.h>
#include <libxml/tree.h>
#include <libxml/parser.h>
#include <libxml/uri.h>
#include <libxml/entities.h>
#include <libxml/valid.h>
#include <libxml/xmlerror.h>
#include <libxml/parserInternals.h>
#include <libxml/globals.h>
#include <libxml/xpathInternals.h>

#include <tibems/tibems.h>

#include "../include/vinculoRecargaSMS.h"

#include "../../commons/Propriedade/include/MFile.h"
#include "../../commons/Log/include/Log.h"
#include "../../commons/SplitLine.h"

#include <string>
using namespace std;

/* EXEC SQL INCLUDE SQLCA;
 */ 
/*
 * $Header: /cvs/BATCH/vinculoRecargaSMS/src/vinculoRecargaSMS.cpp,v 1.1.4.1 2010/09/06 19:00:59 a5116174 Exp $ sqlca.h 
 */

/* Copyright (c) 1985,1986, 1998 by Oracle Corporation. */
 
/*
NAME
  SQLCA : SQL Communications Area.
FUNCTION
  Contains no code. Oracle fills in the SQLCA with status info
  during the execution of a SQL stmt.
NOTES
  **************************************************************
  ***                                                        ***
  *** This file is SOSD.  Porters must change the data types ***
  *** appropriately on their platform.  See notes/pcport.doc ***
  *** for more information.                                  ***
  ***                                                        ***
  **************************************************************

  If the symbol SQLCA_STORAGE_CLASS is defined, then the SQLCA
  will be defined to have this storage class. For example:
 
    #define SQLCA_STORAGE_CLASS extern
 
  will define the SQLCA as an extern.
 
  If the symbol SQLCA_INIT is defined, then the SQLCA will be
  statically initialized. Although this is not necessary in order
  to use the SQLCA, it is a good pgming practice not to have
  unitialized variables. However, some C compilers/OS's don't
  allow automatic variables to be init'd in this manner. Therefore,
  if you are INCLUDE'ing the SQLCA in a place where it would be
  an automatic AND your C compiler/OS doesn't allow this style
  of initialization, then SQLCA_INIT should be left undefined --
  all others can define SQLCA_INIT if they wish.

  If the symbol SQLCA_NONE is defined, then the SQLCA variable will
  not be defined at all.  The symbol SQLCA_NONE should not be defined
  in source modules that have embedded SQL.  However, source modules
  that have no embedded SQL, but need to manipulate a sqlca struct
  passed in as a parameter, can set the SQLCA_NONE symbol to avoid
  creation of an extraneous sqlca variable.
 
MODIFIED
    lvbcheng   07/31/98 -  long to int
    jbasu      12/12/94 -  Bug 217878: note this is an SOSD file
    losborne   08/11/92 -  No sqlca var if SQLCA_NONE macro set 
  Clare      12/06/84 - Ch SQLCA to not be an extern.
  Clare      10/21/85 - Add initialization.
  Bradbury   01/05/86 - Only initialize when SQLCA_INIT set
  Clare      06/12/86 - Add SQLCA_STORAGE_CLASS option.
*/
 
#ifndef SQLCA
#define SQLCA 1
 
struct   sqlca
         {
         /* ub1 */ char    sqlcaid[8];
         /* b4  */ int     sqlabc;
         /* b4  */ int     sqlcode;
         struct
           {
           /* ub2 */ unsigned short sqlerrml;
           /* ub1 */ char           sqlerrmc[70];
           } sqlerrm;
         /* ub1 */ char    sqlerrp[8];
         /* b4  */ int     sqlerrd[6];
         /* ub1 */ char    sqlwarn[8];
         /* ub1 */ char    sqlext[8];
         };

#ifndef SQLCA_NONE 
#ifdef   SQLCA_STORAGE_CLASS
SQLCA_STORAGE_CLASS struct sqlca sqlca
#else
         struct sqlca sqlca
#endif
 
#ifdef  SQLCA_INIT
         = {
         {'S', 'Q', 'L', 'C', 'A', ' ', ' ', ' '},
         sizeof(struct sqlca),
         0,
         { 0, {0}},
         {'N', 'O', 'T', ' ', 'S', 'E', 'T', ' '},
         {0, 0, 0, 0, 0, 0},
         {0, 0, 0, 0, 0, 0, 0, 0},
         {0, 0, 0, 0, 0, 0, 0, 0}
         }
#endif
         ;
#endif
 
#endif
 
/* end SQLCA */


#define CONVIND(O,I) \
{\
    if (I == -1) { \
        ##O.arr[0]=0; \
    } else { \
        ##O.arr[##O.len]=0; \
    } \
}

//==============================================================================
// Globais
char szAux[1024 + 1];
Log oLog;
int iSignalProcessa = 1;

//==============================================================================
// Protótipos
int ObterParamMaxRegistros(int LimMax);
int Process(char *pszFileName, int iFlagLock);
void UnLockSemaphore(int semid);
void LockSemaphore(int semid);
int InitSemaphore(key_t key, int nsems);

//==============================================================================
// Run
int main(int argc, char* argv[])
{
    char szNrProtocoloAnt[38];
    char szNrLinhaAnt[20];
    char szDtAberturaAnt[15];
    char szHrAberturaAnt[15];

    TParamConf tParamConf;

    /* EXEC SQL BEGIN DECLARE SECTION; */ 

        const int MAX_REGS = 20000;

        int maxRegs;
        int linhas_retornadas;
        const char *pOraQuery;

        /* VARCHAR lstDadoOraIdAtendimentoProtocolo[MAX_REGS][39]; */ 
struct { unsigned short len; unsigned char arr[42]; } lstDadoOraIdAtendimentoProtocolo[20000];

        /* VARCHAR lstDadoOraCdAreaRegistro[MAX_REGS][4]; */ 
struct { unsigned short len; unsigned char arr[6]; } lstDadoOraCdAreaRegistro[20000];

        /* VARCHAR lstDadoOraNrLinha[MAX_REGS][11]; */ 
struct { unsigned short len; unsigned char arr[14]; } lstDadoOraNrLinha[20000];

        /* VARCHAR lstDadoOraDsMensagemEnvio[MAX_REGS][1001]; */ 
struct { unsigned short len; unsigned char arr[1002]; } lstDadoOraDsMensagemEnvio[20000];

        /* VARCHAR lstDadoOraDsMotivoNaoEnvio[MAX_REGS][256]; */ 
struct { unsigned short len; unsigned char arr[258]; } lstDadoOraDsMotivoNaoEnvio[20000];

        int lstDadoOraInEnvioSMS[MAX_REGS];

        short lstStatOraIdAtendimentoProtocolo[MAX_REGS];
        short lstStatOraCdAreaRegistro[MAX_REGS];
        short lstStatOraNrLinha[MAX_REGS];
        short lstStatOraDsMensagemEnvio[MAX_REGS];
        short lstStatOraDsMotivoNaoEnvio[MAX_REGS];
        short lstStatOraInEnvioSMS[MAX_REGS];

    /* EXEC SQL END DECLARE SECTION; */ 


    tibemsSSLParams sslParams = NULL;
    //tibemsTextMsg msg = NULL;
    tibemsQueueConnection queueConnection = NULL;
    tibemsQueue queue = NULL;
    tibemsQueueSession queueSession = NULL;
    tibemsQueueSender queueSender = NULL;
    tibems_status status = TIBEMS_OK;

    char szDsMensagemEnvio[601];
    char szDsListaClassificacao[601] = {0};
    char szidClassificacaoSMS[40];

    oLog.setNivel(2);

    oLog.logDebug(">>>vinculoRecargaSMS");

    ArmaSinal(SIGTERM);

    //==========================================================================
    // Parâmetros de configuração
    if(ObtemParamConf(&tParamConf))
    {
        oLog.logError("Erro obtendo parametros de configuracao");
        return -1;
    }

    //==========================================================================
    // Lock
    oLog.logDebug("Vai locar fila de processamento...");
    int iRet = Process(argv[0],LOCK);

    if(iRet)
    {
    	oLog.logInformation("Saida por TIMEOUT");
        return -1;
    }
    oLog.logDebug("Locou fila de processamento");

    //==========================================================================
    // Conecta ao banco
    oLog.logDebug("Conectando na BD...");
    if (DBConnect(tParamConf.szUsr, tParamConf.szPws, tParamConf.szInst))
    {
        oLog.logError("Erro conectando no banco de dados");
        Process(argv[0], UNLOCK);
        oLog.logDebug("unlock executado");
        return -1;
    }
    oLog.logDebug("Conectado na BD com sucesso...");

    //==========================================================================
    // Limite máximo de registros
    sprintf(szAux,"Limite maximo permitido=%d",MAX_REGS);oLog.logDebug(szAux);
    maxRegs = ObterParamMaxRegistros(MAX_REGS);
    sprintf(szAux,"Limite parametrizado=%d",maxRegs);oLog.logDebug(szAux);

    //==========================================================================
    // Trata mensagem?
    ObterParamMsgParse(&tParamConf);
    sprintf(szAux,"Tratamento da Mensagem=%s",tParamConf.szMessageParse);oLog.logDebug(szAux);

    //==========================================================================
    // Logar mensagem ?
    ObterParamMsgLog(&tParamConf);
    sprintf(szAux,"Log da Mensagem=%s",tParamConf.szMessageLog);oLog.logDebug(szAux);

    if ( maxRegs > 0 )
    {
        //==========================================================================
        // Cursor

        /* EXEC SQL WHENEVER SQLERROR GOTO sqlError; */ 

        /* EXEC SQL WHENEVER NOT FOUND CONTINUE; */ 


        /* EXEC SQL DECLARE curvinculoRecargaSMS CURSOR FOR 
            SELECT IDATENDIMENTOPROTOCOLO,CDAREAREGISTRO,NRLINHA,DSMENSAGEMENVIO,INENVIOSMS
            FROM
            ( 
                SELECT /o+ index(FILASMSPROTOCOLO FILASMSPROTOCOLOIE1) o/
                    FILASMSPROTOCOLO.IDATENDIMENTOPROTOCOLO,
                    FILASMSPROTOCOLO.CDAREAREGISTRO,
                    FILASMSPROTOCOLO.NRLINHA,
                    FILASMSPROTOCOLO.DSMENSAGEMENVIO,
                    FILASMSPROTOCOLO.INENVIOSMS
                FROM
                    ATENDIMENTO.FILASMSPROTOCOLO FILASMSPROTOCOLO
                WHERE
                    FILASMSPROTOCOLO.DTENCERRAMENTO < SYSTIMESTAMP
                ORDER BY
                    FILASMSPROTOCOLO.DTULTIMAALTERACAO
            )
            WHERE ROWNUM <= :maxRegs; */ 


        memset(&lstDadoOraIdAtendimentoProtocolo,0,sizeof(lstDadoOraIdAtendimentoProtocolo));
        memset(&lstDadoOraCdAreaRegistro,0,sizeof(lstDadoOraCdAreaRegistro));
        memset(&lstDadoOraNrLinha,0,sizeof(lstDadoOraNrLinha));
        memset(&lstDadoOraDsMensagemEnvio,0,sizeof(lstDadoOraDsMensagemEnvio));
        memset(&lstDadoOraDsMotivoNaoEnvio,0,sizeof(lstDadoOraDsMotivoNaoEnvio));
        memset(&lstDadoOraInEnvioSMS,0,sizeof(lstDadoOraInEnvioSMS));

        memset(&lstStatOraIdAtendimentoProtocolo,-1,sizeof(lstStatOraIdAtendimentoProtocolo));
        memset(&lstStatOraCdAreaRegistro,-1,sizeof(lstStatOraCdAreaRegistro));
        memset(&lstStatOraNrLinha,-1,sizeof(lstStatOraNrLinha));
        memset(&lstStatOraDsMensagemEnvio,-1,sizeof(lstStatOraDsMensagemEnvio));
        memset(&lstStatOraDsMotivoNaoEnvio,-1,sizeof(lstStatOraDsMotivoNaoEnvio));
        memset(&lstStatOraInEnvioSMS,-1,sizeof(lstStatOraInEnvioSMS));

        /* EXEC SQL OPEN curvinculoRecargaSMS; */ 

{
        struct sqlexd sqlstm;
        sqlstm.sqlvsn = 12;
        sqlstm.arrsiz = 1;
        sqlstm.sqladtp = &sqladt;
        sqlstm.sqltdsp = &sqltds;
        sqlstm.stmt = sq0001;
        sqlstm.iters = (unsigned int  )1;
        sqlstm.offset = (unsigned int  )5;
        sqlstm.selerr = (unsigned short)1;
        sqlstm.cud = sqlcud0;
        sqlstm.sqlest = (unsigned char  *)&sqlca;
        sqlstm.sqlety = (unsigned short)256;
        sqlstm.occurs = (unsigned int  )0;
        sqlstm.sqcmod = (unsigned int )0;
        sqlstm.sqhstv[0] = (unsigned char  *)&maxRegs;
        sqlstm.sqhstl[0] = (unsigned long )sizeof(int);
        sqlstm.sqhsts[0] = (         int  )0;
        sqlstm.sqindv[0] = (         short *)0;
        sqlstm.sqinds[0] = (         int  )0;
        sqlstm.sqharm[0] = (unsigned long )0;
        sqlstm.sqadto[0] = (unsigned short )0;
        sqlstm.sqtdso[0] = (unsigned short )0;
        sqlstm.sqphsv = sqlstm.sqhstv;
        sqlstm.sqphsl = sqlstm.sqhstl;
        sqlstm.sqphss = sqlstm.sqhsts;
        sqlstm.sqpind = sqlstm.sqindv;
        sqlstm.sqpins = sqlstm.sqinds;
        sqlstm.sqparm = sqlstm.sqharm;
        sqlstm.sqparc = sqlstm.sqharc;
        sqlstm.sqpadto = sqlstm.sqadto;
        sqlstm.sqptdso = sqlstm.sqtdso;
        sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
        if (sqlca.sqlcode < 0) goto sqlError;
}



        oLog.logDebug("Carregando dados resultantes...");

        /* EXEC SQL FOR :maxRegs FETCH curvinculoRecargaSMS 
        INTO 
            :lstDadoOraIdAtendimentoProtocolo:lstStatOraIdAtendimentoProtocolo,
            :lstDadoOraCdAreaRegistro:lstStatOraCdAreaRegistro,
            :lstDadoOraNrLinha:lstStatOraNrLinha,
            :lstDadoOraDsMensagemEnvio:lstStatOraDsMensagemEnvio,
            :lstDadoOraInEnvioSMS:lstStatOraInEnvioSMS; */ 

{
        struct sqlexd sqlstm;
        sqlstm.sqlvsn = 12;
        sqlstm.arrsiz = 5;
        sqlstm.sqladtp = &sqladt;
        sqlstm.sqltdsp = &sqltds;
        sqlstm.iters = (unsigned int  )maxRegs;
        sqlstm.offset = (unsigned int  )24;
        sqlstm.selerr = (unsigned short)1;
        sqlstm.cud = sqlcud0;
        sqlstm.sqlest = (unsigned char  *)&sqlca;
        sqlstm.sqlety = (unsigned short)256;
        sqlstm.occurs = (unsigned int  )0;
        sqlstm.sqfoff = (         int )0;
        sqlstm.sqfmod = (unsigned int )2;
        sqlstm.sqhstv[0] = (unsigned char  *)lstDadoOraIdAtendimentoProtocolo;
        sqlstm.sqhstl[0] = (unsigned long )41;
        sqlstm.sqhsts[0] = (         int  )44;
        sqlstm.sqindv[0] = (         short *)lstStatOraIdAtendimentoProtocolo;
        sqlstm.sqinds[0] = (         int  )sizeof(short);
        sqlstm.sqharm[0] = (unsigned long )0;
        sqlstm.sqharc[0] = (unsigned long  *)0;
        sqlstm.sqadto[0] = (unsigned short )0;
        sqlstm.sqtdso[0] = (unsigned short )0;
        sqlstm.sqhstv[1] = (unsigned char  *)lstDadoOraCdAreaRegistro;
        sqlstm.sqhstl[1] = (unsigned long )6;
        sqlstm.sqhsts[1] = (         int  )8;
        sqlstm.sqindv[1] = (         short *)lstStatOraCdAreaRegistro;
        sqlstm.sqinds[1] = (         int  )sizeof(short);
        sqlstm.sqharm[1] = (unsigned long )0;
        sqlstm.sqharc[1] = (unsigned long  *)0;
        sqlstm.sqadto[1] = (unsigned short )0;
        sqlstm.sqtdso[1] = (unsigned short )0;
        sqlstm.sqhstv[2] = (unsigned char  *)lstDadoOraNrLinha;
        sqlstm.sqhstl[2] = (unsigned long )13;
        sqlstm.sqhsts[2] = (         int  )16;
        sqlstm.sqindv[2] = (         short *)lstStatOraNrLinha;
        sqlstm.sqinds[2] = (         int  )sizeof(short);
        sqlstm.sqharm[2] = (unsigned long )0;
        sqlstm.sqharc[2] = (unsigned long  *)0;
        sqlstm.sqadto[2] = (unsigned short )0;
        sqlstm.sqtdso[2] = (unsigned short )0;
        sqlstm.sqhstv[3] = (unsigned char  *)lstDadoOraDsMensagemEnvio;
        sqlstm.sqhstl[3] = (unsigned long )1003;
        sqlstm.sqhsts[3] = (         int  )1004;
        sqlstm.sqindv[3] = (         short *)lstStatOraDsMensagemEnvio;
        sqlstm.sqinds[3] = (         int  )sizeof(short);
        sqlstm.sqharm[3] = (unsigned long )0;
        sqlstm.sqharc[3] = (unsigned long  *)0;
        sqlstm.sqadto[3] = (unsigned short )0;
        sqlstm.sqtdso[3] = (unsigned short )0;
        sqlstm.sqhstv[4] = (unsigned char  *)lstDadoOraInEnvioSMS;
        sqlstm.sqhstl[4] = (unsigned long )sizeof(int);
        sqlstm.sqhsts[4] = (         int  )sizeof(int);
        sqlstm.sqindv[4] = (         short *)lstStatOraInEnvioSMS;
        sqlstm.sqinds[4] = (         int  )sizeof(short);
        sqlstm.sqharm[4] = (unsigned long )0;
        sqlstm.sqharc[4] = (unsigned long  *)0;
        sqlstm.sqadto[4] = (unsigned short )0;
        sqlstm.sqtdso[4] = (unsigned short )0;
        sqlstm.sqphsv = sqlstm.sqhstv;
        sqlstm.sqphsl = sqlstm.sqhstl;
        sqlstm.sqphss = sqlstm.sqhsts;
        sqlstm.sqpind = sqlstm.sqindv;
        sqlstm.sqpins = sqlstm.sqinds;
        sqlstm.sqparm = sqlstm.sqharm;
        sqlstm.sqparc = sqlstm.sqharc;
        sqlstm.sqpadto = sqlstm.sqadto;
        sqlstm.sqptdso = sqlstm.sqtdso;
        sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
        if (sqlca.sqlcode < 0) goto sqlError;
}



        linhas_retornadas = sqlca.sqlerrd[2];

        /* EXEC SQL CLOSE curvinculoRecargaSMS; */ 

{
        struct sqlexd sqlstm;
        sqlstm.sqlvsn = 12;
        sqlstm.arrsiz = 5;
        sqlstm.sqladtp = &sqladt;
        sqlstm.sqltdsp = &sqltds;
        sqlstm.iters = (unsigned int  )1;
        sqlstm.offset = (unsigned int  )59;
        sqlstm.cud = sqlcud0;
        sqlstm.sqlest = (unsigned char  *)&sqlca;
        sqlstm.sqlety = (unsigned short)256;
        sqlstm.occurs = (unsigned int  )0;
        sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
        if (sqlca.sqlcode < 0) goto sqlError;
}



        sprintf( szAux,"linhas retornadas=%d",linhas_retornadas );
        oLog.logDebug(szAux);

        //==========================================================================
        // Processa

        if ( linhas_retornadas > 0 )
	    {
            // Conecta com fila Tibco
            oLog.logDebug("Iniciando conexao JMS...");

            const char *queueName = tParamConf.szQueueName;     // VIVO.FO.SMS.SEND
            const char *serverUrl = tParamConf.szServerUrl;     // astorga.telespcelular.com.br
            const char *userName = tParamConf.szUserName;       // admin
            const char *password = tParamConf.szPassword;       // 71b60
            const char *pk_password = tParamConf.szPk_password; // teste

            sprintf(szAux, "  queueName=%s", queueName); oLog.logDebug(szAux);
            sprintf(szAux, "  serverUrl=%s", serverUrl); oLog.logDebug(szAux);
            //sprintf(szAux, "   userName=%s", userName); oLog.logDebug(szAux);
            //sprintf(szAux, "   password=%s", password); oLog.logDebug(szAux);
            //sprintf(szAux, "pk_password=%s", pk_password); oLog.logDebug(szAux);

            // Create the connection, use ssl if specified
            if(sslParams)
            {
                status = tibemsQueueConnection_CreateSSL(&queueConnection, serverUrl, NULL, userName, password, sslParams, pk_password);
            }
            else
            {
                status = tibemsQueueConnection_Create(&queueConnection, serverUrl, NULL, userName, password);
            }

            if (status != TIBEMS_OK)
            {
                oLog.logDebug("Falha de conexão à fila Tibco...");
                oLog.logDebug("Desconectando da BD...");
                DBDisconnect();

                oLog.logDebug("Liberando fila...");
                Process(argv[0], UNLOCK);

                Fail("Error creating tibemsQueueConnection", status);
            }

            // create the queue session
            status = tibemsQueue_Create(&queue, queueName);
            if (status != TIBEMS_OK)
            {
                oLog.logDebug("Falha de conexão à fila Tibco...");
                oLog.logDebug("Desconectando da BD...");
                DBDisconnect();

                oLog.logDebug("Liberando fila...");
                Process(argv[0], UNLOCK);

                Fail("Error tibemsQueue_Create", status);
            }

            // create the queue session
            status = tibemsQueueConnection_CreateQueueSession(queueConnection, &queueSession, TIBEMS_FALSE, TIBEMS_AUTO_ACKNOWLEDGE);
            if (status != TIBEMS_OK)
            {
                oLog.logDebug("Falha de conexão à fila Tibco...");
                oLog.logDebug("Desconectando da BD...");
                DBDisconnect();

                oLog.logDebug("Liberando fila...");
                Process(argv[0], UNLOCK);

                Fail("Error tibemsQueueConnection_CreateQueueSession", status);
            }

            // create the queue sender
            status = tibemsQueueSession_CreateSender(queueSession, &queueSender, queue);
            if (status != TIBEMS_OK)
            {
                oLog.logDebug("Falha de conexão à fila Tibco...");
                oLog.logDebug("Desconectando da BD...");
                DBDisconnect();

                oLog.logDebug("Liberando fila...");
                Process(argv[0], UNLOCK);

                Fail("Error tibemsQueueSession_CreateSender", status);
            }

            oLog.logDebug("Iniciando conexao JMS OK...");

            oLog.logDebug("Enviando SMS's...");

            //======================================================================
            // Envia os SMS's que tiverem de ser enviados...
            int qtEnviadas = 0;
            for( int i = 0; i < linhas_retornadas; i++ )
            {
                if(iSignalProcessa == 0)
                {
                    oLog.logDebug("3.Tratamento de sinal de termino dentro do loop");
                    break;
                }

                CONVIND(lstDadoOraIdAtendimentoProtocolo[i],lstStatOraIdAtendimentoProtocolo[i]);
                CONVIND(lstDadoOraCdAreaRegistro[i],lstStatOraCdAreaRegistro[i]);
                CONVIND(lstDadoOraNrLinha[i],lstStatOraNrLinha[i]);
                CONVIND(lstDadoOraDsMensagemEnvio[i],lstStatOraDsMensagemEnvio[i]);

                // Se existir suspeita de mensagem com lixo, podemos habilitar tratamento
                if ( 'S' == tParamConf.szMessageParse[0] )
                {
                    int lenMensagem = strlen((char*)lstDadoOraDsMensagemEnvio[i].arr);

                    char *p = strstr((char*)lstDadoOraDsMensagemEnvio[i].arr," as ");
                    if ( p==0 ) { p = strstr((char*)lstDadoOraDsMensagemEnvio[i].arr," AS "); }
                    int posIni = p - (char*)lstDadoOraDsMensagemEnvio[i].arr + 12; // 12 = tam de " as hh:mm - ";
                    if ( posIni < 0 || posIni > lenMensagem ) { posIni=0; }

                    for( int j=posIni;j<lenMensagem;j++ )
                    {
                        if ( lstDadoOraDsMensagemEnvio[i].arr[j] < 32 || lstDadoOraDsMensagemEnvio[i].arr[j] > 125 )
                        {
                             lstDadoOraDsMensagemEnvio[i].arr[j] = ' ';
                        }
                    }
                } // if ( 'S' == tParamConf.szMessageParse[0] )

                if ( 1 == lstDadoOraInEnvioSMS[i] )
                {
                    EnviarMensagemTibco((const char*)lstDadoOraIdAtendimentoProtocolo[i].arr
                                       ,(const char*)lstDadoOraCdAreaRegistro[i].arr
                                       ,(const char*)lstDadoOraNrLinha[i].arr
                                       ,(const char*)lstDadoOraDsMensagemEnvio[i].arr
                                       ,tParamConf.szQueueName
                                       ,argv[0]
                                       ,queueSender
                                       ,tParamConf.szMessageLog
                                       );

                    qtEnviadas++;
                }
                else
                {
                    strcpy((char*)lstDadoOraDsMotivoNaoEnvio[i].arr,
                        "SMS não enviado para este protocolo pois todas folhas "
                                            "de contato foram configuradas com INSMS=0");
                    lstDadoOraDsMotivoNaoEnvio[i].len = 
                                        strlen((char*)lstDadoOraDsMotivoNaoEnvio[i].arr);
                    lstStatOraDsMotivoNaoEnvio[i] = 1;
                }
            } // for(;;)

            sprintf(szAux,"%d mensagens foram enviadas",qtEnviadas);
            oLog.logDebug(szAux);

            //======================================================================
            // Copia o bloco para o histórico
            oLog.logDebug("Vai gerar histórico...");
            /* EXEC SQL FOR :linhas_retornadas 
                INSERT INTO ATENDIMENTO.FILASMSPROTOCOLOHIST
                (
                    IDATENDIMENTOPROTOCOLO,
                    CDAREAREGISTRO,
                    NRLINHA,
                    DSMENSAGEMENVIO,
                    DSMOTIVONAOENVIO,
                    DTULTIMAALTERACAO,
                    IDUSUARIOALTERACAO
                )
                VALUES
                (
                    :lstDadoOraIdAtendimentoProtocolo:lstStatOraIdAtendimentoProtocolo,
                    :lstDadoOraCdAreaRegistro:lstStatOraCdAreaRegistro,
                    :lstDadoOraNrLinha:lstStatOraNrLinha,
                    :lstDadoOraDsMensagemEnvio:lstStatOraDsMensagemEnvio,
                    :lstDadoOraDsMotivoNaoEnvio:lstStatOraDsMotivoNaoEnvio,
                    SYSTIMESTAMP,
                    -99
                ); */ 

{
            struct sqlexd sqlstm;
            sqlstm.sqlvsn = 12;
            sqlstm.arrsiz = 5;
            sqlstm.sqladtp = &sqladt;
            sqlstm.sqltdsp = &sqltds;
            sqlstm.stmt = "insert into ATENDIMENTO.FILASMSPROTOCOLOHIST (IDA\
TENDIMENTOPROTOCOLO,CDAREAREGISTRO,NRLINHA,DSMENSAGEMENVIO,DSMOTIVONAOENVIO,DT\
ULTIMAALTERACAO,IDUSUARIOALTERACAO) values (:b1:b2,:b3:b4,:b5:b6,:b7:b8,:b9:b1\
0,SYSTIMESTAMP,(-99))";
            sqlstm.iters = (unsigned int  )linhas_retornadas;
            sqlstm.offset = (unsigned int  )74;
            sqlstm.cud = sqlcud0;
            sqlstm.sqlest = (unsigned char  *)&sqlca;
            sqlstm.sqlety = (unsigned short)256;
            sqlstm.occurs = (unsigned int  )0;
            sqlstm.sqhstv[0] = (unsigned char  *)lstDadoOraIdAtendimentoProtocolo;
            sqlstm.sqhstl[0] = (unsigned long )41;
            sqlstm.sqhsts[0] = (         int  )44;
            sqlstm.sqindv[0] = (         short *)lstStatOraIdAtendimentoProtocolo;
            sqlstm.sqinds[0] = (         int  )sizeof(short);
            sqlstm.sqharm[0] = (unsigned long )0;
            sqlstm.sqharc[0] = (unsigned long  *)0;
            sqlstm.sqadto[0] = (unsigned short )0;
            sqlstm.sqtdso[0] = (unsigned short )0;
            sqlstm.sqhstv[1] = (unsigned char  *)lstDadoOraCdAreaRegistro;
            sqlstm.sqhstl[1] = (unsigned long )6;
            sqlstm.sqhsts[1] = (         int  )8;
            sqlstm.sqindv[1] = (         short *)lstStatOraCdAreaRegistro;
            sqlstm.sqinds[1] = (         int  )sizeof(short);
            sqlstm.sqharm[1] = (unsigned long )0;
            sqlstm.sqharc[1] = (unsigned long  *)0;
            sqlstm.sqadto[1] = (unsigned short )0;
            sqlstm.sqtdso[1] = (unsigned short )0;
            sqlstm.sqhstv[2] = (unsigned char  *)lstDadoOraNrLinha;
            sqlstm.sqhstl[2] = (unsigned long )13;
            sqlstm.sqhsts[2] = (         int  )16;
            sqlstm.sqindv[2] = (         short *)lstStatOraNrLinha;
            sqlstm.sqinds[2] = (         int  )sizeof(short);
            sqlstm.sqharm[2] = (unsigned long )0;
            sqlstm.sqharc[2] = (unsigned long  *)0;
            sqlstm.sqadto[2] = (unsigned short )0;
            sqlstm.sqtdso[2] = (unsigned short )0;
            sqlstm.sqhstv[3] = (unsigned char  *)lstDadoOraDsMensagemEnvio;
            sqlstm.sqhstl[3] = (unsigned long )1003;
            sqlstm.sqhsts[3] = (         int  )1004;
            sqlstm.sqindv[3] = (         short *)lstStatOraDsMensagemEnvio;
            sqlstm.sqinds[3] = (         int  )sizeof(short);
            sqlstm.sqharm[3] = (unsigned long )0;
            sqlstm.sqharc[3] = (unsigned long  *)0;
            sqlstm.sqadto[3] = (unsigned short )0;
            sqlstm.sqtdso[3] = (unsigned short )0;
            sqlstm.sqhstv[4] = (unsigned char  *)lstDadoOraDsMotivoNaoEnvio;
            sqlstm.sqhstl[4] = (unsigned long )258;
            sqlstm.sqhsts[4] = (         int  )260;
            sqlstm.sqindv[4] = (         short *)lstStatOraDsMotivoNaoEnvio;
            sqlstm.sqinds[4] = (         int  )sizeof(short);
            sqlstm.sqharm[4] = (unsigned long )0;
            sqlstm.sqharc[4] = (unsigned long  *)0;
            sqlstm.sqadto[4] = (unsigned short )0;
            sqlstm.sqtdso[4] = (unsigned short )0;
            sqlstm.sqphsv = sqlstm.sqhstv;
            sqlstm.sqphsl = sqlstm.sqhstl;
            sqlstm.sqphss = sqlstm.sqhsts;
            sqlstm.sqpind = sqlstm.sqindv;
            sqlstm.sqpins = sqlstm.sqinds;
            sqlstm.sqparm = sqlstm.sqharm;
            sqlstm.sqparc = sqlstm.sqharc;
            sqlstm.sqpadto = sqlstm.sqadto;
            sqlstm.sqptdso = sqlstm.sqtdso;
            sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
            if (sqlca.sqlcode < 0) goto sqlError;
}



            //======================================================================
            // Apaga o bloco
            oLog.logDebug("Vai apagar bloco processado...");
            /* EXEC SQL FOR :linhas_retornadas 
                DELETE
                    ATENDIMENTO.FILASMSPROTOCOLO
                WHERE
                    IDATENDIMENTOPROTOCOLO IN (:lstDadoOraIdAtendimentoProtocolo:lstStatOraIdAtendimentoProtocolo); */ 

{
            struct sqlexd sqlstm;
            sqlstm.sqlvsn = 12;
            sqlstm.arrsiz = 5;
            sqlstm.sqladtp = &sqladt;
            sqlstm.sqltdsp = &sqltds;
            sqlstm.stmt = "delete  from ATENDIMENTO.FILASMSPROTOCOLO  where \
IDATENDIMENTOPROTOCOLO in (:b1:b2)";
            sqlstm.iters = (unsigned int  )linhas_retornadas;
            sqlstm.offset = (unsigned int  )109;
            sqlstm.cud = sqlcud0;
            sqlstm.sqlest = (unsigned char  *)&sqlca;
            sqlstm.sqlety = (unsigned short)256;
            sqlstm.occurs = (unsigned int  )0;
            sqlstm.sqhstv[0] = (unsigned char  *)lstDadoOraIdAtendimentoProtocolo;
            sqlstm.sqhstl[0] = (unsigned long )41;
            sqlstm.sqhsts[0] = (         int  )44;
            sqlstm.sqindv[0] = (         short *)lstStatOraIdAtendimentoProtocolo;
            sqlstm.sqinds[0] = (         int  )sizeof(short);
            sqlstm.sqharm[0] = (unsigned long )0;
            sqlstm.sqharc[0] = (unsigned long  *)0;
            sqlstm.sqadto[0] = (unsigned short )0;
            sqlstm.sqtdso[0] = (unsigned short )0;
            sqlstm.sqphsv = sqlstm.sqhstv;
            sqlstm.sqphsl = sqlstm.sqhstl;
            sqlstm.sqphss = sqlstm.sqhsts;
            sqlstm.sqpind = sqlstm.sqindv;
            sqlstm.sqpins = sqlstm.sqinds;
            sqlstm.sqparm = sqlstm.sqharm;
            sqlstm.sqparc = sqlstm.sqharc;
            sqlstm.sqpadto = sqlstm.sqadto;
            sqlstm.sqptdso = sqlstm.sqtdso;
            sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
            if (sqlca.sqlcode < 0) goto sqlError;
}



            /* EXEC SQL COMMIT; */ 

{
            struct sqlexd sqlstm;
            sqlstm.sqlvsn = 12;
            sqlstm.arrsiz = 5;
            sqlstm.sqladtp = &sqladt;
            sqlstm.sqltdsp = &sqltds;
            sqlstm.iters = (unsigned int  )1;
            sqlstm.offset = (unsigned int  )128;
            sqlstm.cud = sqlcud0;
            sqlstm.sqlest = (unsigned char  *)&sqlca;
            sqlstm.sqlety = (unsigned short)256;
            sqlstm.occurs = (unsigned int  )0;
            sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
            if (sqlca.sqlcode < 0) goto sqlError;
}



            //======================================================================
            // Desconecta da fila Tibco
            oLog.logDebug("Fechando conexao JMS...");

            // destroy the queue
            status = tibemsQueue_Destroy(queue);
            if (status != TIBEMS_OK)
            {
                sprintf( szAux,"Error tibemsQueue_Destroy=[%d]",status );
                oLog.logDebug(szAux);
            }
            else
            {
                // close the connection
                status = tibemsConnection_Close(queueConnection);
                if (status != TIBEMS_OK)
                {
                    sprintf( szAux,"Error tibemsConnection_Close=[%d]",status );
                    oLog.logDebug(szAux);
                }
                else
                {
                    // destroy the ssl params
                    tibemsSSLParams_Destroy(sslParams);
                    oLog.logDebug("Fechando conexao JMS OK...");
                }
            }

        } // if ( linhas_retornadas > 0 )
    } // if ( maxRegs > 0 )
    else
    {
        oLog.logDebug("NÃO HOUVE PROCESSAMENTO PARA BUFFER COM TAMANHO ZERO");
    }
    oLog.logDebug("Desconectando da BD...");
    DBDisconnect();

    oLog.logDebug("Liberando fila...");
    Process(argv[0], UNLOCK);

    oLog.logDebug("Processamento encerrado");
    oLog.logDebug("<<<vinculoRecargaSMS");

    return 0;

    sqlError:
        oLog.logDebug("Liberando fila por erro...");
        Process(argv[0], UNLOCK);

        sprintf(szAux, "Finalizando processo com erro ORACLE (%d-%.256s)",
                    sqlca.sqlcode,sqlca.sqlerrm.sqlerrmc); oLog.logDebug(szAux);

        /* EXEC SQL ROLLBACK; */ 

{
        struct sqlexd sqlstm;
        sqlstm.sqlvsn = 12;
        sqlstm.arrsiz = 5;
        sqlstm.sqladtp = &sqladt;
        sqlstm.sqltdsp = &sqltds;
        sqlstm.iters = (unsigned int  )1;
        sqlstm.offset = (unsigned int  )143;
        sqlstm.cud = sqlcud0;
        sqlstm.sqlest = (unsigned char  *)&sqlca;
        sqlstm.sqlety = (unsigned short)256;
        sqlstm.occurs = (unsigned int  )0;
        sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
        if (sqlca.sqlcode < 0) goto sqlError;
}



        oLog.logDebug("Vai tentar desconectar da BD...");
        DBDisconnect();

        return -1;
}


/************************************************************************************************************/
void EnviarMensagemTibco(const char *idAtendimentoProtocolo,const char *cdAreaRegistro
                        ,const char *nrLinha,const char *dsMensagemEnvio
                        ,const char *queueName,char *argVZero
                        ,tibemsQueueSender &queueSender
                        ,const char *pSzMessageLog)
{
    tibemsTextMsg msg;
    char szMsg[2001];

    sprintf(szMsg,
    "<?xml version=\"1.0\" encoding=\"ISO-8859-1\"?>"
    "<msg>"
        "<msgHdr>"
            "<service>SMSSend</service>"
            "<user>1</user>"
            "<topic>%s</topic>"
            "<timeout>30</timeout>"
        "</msgHdr>"
        "<msgBody>"
            "<sms>"
                "<codigoInterno>%s</codigoInterno>"
                "<origem>2001</origem>"
                "<destino>%s%s</destino>"
                "<mensagem>%s</mensagem>"
            "</sms>"
        "</msgBody>"
    "</msg>",queueName
            ,idAtendimentoProtocolo
            ,cdAreaRegistro,nrLinha
            ,dsMensagemEnvio);

    // se é parar loggar a mensagem de envio ...
    if ( 'S' == *pSzMessageLog )
    {
        sprintf(szAux,"Vai enfileirar MSG=%s,TAMMSG=%d",szMsg,strlen(szMsg));
        oLog.logDebug(szAux);
    }

	// create the text message
	tibems_status status = tibemsTextMsg_Create(&msg);
	if (status != TIBEMS_OK)
    {
        oLog.logDebug("Falha de envio à fila Tibco...");
        oLog.logDebug("Desconectando da BD...");
        DBDisconnect();

        oLog.logDebug("Liberando fila...");
        Process(argVZero, UNLOCK);

        Fail("Error tibemsTextMsg_Create", status);
    }

	// set the message text
	status = tibemsTextMsg_SetText(msg, szMsg);
	if (status != TIBEMS_OK)
    {
        oLog.logDebug("Falha de envio à fila Tibco...");
        oLog.logDebug("Desconectando da BD...");
        DBDisconnect();

        oLog.logDebug("Liberando fila...");
        Process(argVZero, UNLOCK);

        Fail("Error tibemsTextMsg_SetText", status);
    }

	// send the message
	status = tibemsQueueSender_Send(queueSender, msg);
	if (status != TIBEMS_OK)
    {
        oLog.logDebug("Falha de envio à fila Tibco...");
        oLog.logDebug("Desconectando da BD...");
        DBDisconnect();

        oLog.logDebug("Liberando fila...");
        Process(argVZero, UNLOCK);

        Fail("Error tibemsQueueSender_Send", status);
    }

	// destroy the message
	status = tibemsMsg_Destroy(msg);
	if (status != TIBEMS_OK)
    {
        oLog.logDebug("Falha de envio à fila Tibco...");
        oLog.logDebug("Desconectando da BD...");
        DBDisconnect();

        oLog.logDebug("Liberando fila...");
        Process(argVZero, UNLOCK);

        Fail("Error tibemsMsg_Destroy", status);
    }
}

/************************************************************************************************************/
void ArmaSinal(int iSignal)
{
    sprintf(szAux, "Armando tratamento para Signal(%d)", iSignal); oLog.logInformation(szAux);

    if(signal((iSignal), ProcessaSinal) == SIG_ERR)
    {
        fprintf(stderr, "ERRO ARMANDO SINAL!!!");
        exit(-1);
    }
}

/************************************************************************************************************/
void ProcessaSinal(int iSig)
{
    sprintf(szAux, "iSig(%d)", iSig); oLog.logInformation(szAux);

    // rearma o mesmo sinal lançado
    ArmaSinal(iSig);

    if(iSig == SIGTERM)
    {
        oLog.logInformation("Finalizando processamento via sinal....");
        iSignalProcessa=0;
    }
}

/************************************************************************************************************/
int ObtemParamConf(TParamConf *ptParamConf)
{
    MFile mfConfig;
    SplitLine NewLinha;
    char szLinha[101];
    char szDivArq[256];
    char szDivPre[21];

    oLog.logDebug("Obtendo parametros de configuracao...");

    // Zera variáveis
    memset(ptParamConf, 0, sizeof(TParamConf));
    memset(szLinha, 0, sizeof(szLinha));
    memset(szDivArq, 0, sizeof(szDivArq));
    memset(szDivPre, 0, sizeof(szDivPre));

    // Define o arquivo de configuração
    mfConfig.setPath("vinculoRecargaSMS.cfg");

    // Verificar se conseguiu abrir o arquivo
    if(!mfConfig.abrir())
    {
        oLog.logError("Erro abrindo arquivo de configuração!");
        return -1;
    }

    // Capturando usuario, senha, path e instance de banco do arquivo
    NewLinha.SetDiv('=');
    while(mfConfig.getLine(szLinha) != 0)
    {
        NewLinha.SetLine(szLinha);
        NewLinha.GetBeforeDiv(szDivPre);
        NewLinha.GetAfterDiv(szDivArq);

        // Verifica qual parâmetro foi encontrado.
        if (!strcmp(szDivPre, "pwd_db"))
        {
            strncpy(ptParamConf->szPws, szDivArq, TAM_BD_PASSWD);
        }
        else if (!strcmp(szDivPre, "usr_db"))
        {
            strncpy(ptParamConf->szUsr, szDivArq, TAM_BD_USRNAME);
        }
        else if (!strcmp(szDivPre, "inst_db"))
        {
            strncpy(ptParamConf->szInst, szDivArq, TAM_BD_INST);
        }
        else if (!strcmp(szDivPre, "pwd_tux"))
        {
            strncpy(ptParamConf->szPwsTux, szDivArq, TAM_TX_PASSWD);
        }
        else if (!strcmp(szDivPre, "usr_tux"))
        {
            strncpy(ptParamConf->szUsrTux, szDivArq, TAM_TX_USRNAME);
        }
        else if (!strcmp(szDivPre, "pwd_tux_gen"))
        {
            strncpy(ptParamConf->szPwsTuxGen, szDivArq, TAM_TX_GEN);
        }
        else if (strcmp(szDivPre, "clt_tux")==0)
        {
            strncpy(ptParamConf->szCltTux, szDivArq, TAM_TX_CLT);
        }
        else if (strcmp(szDivPre, "QueueName")==0)
        {
            strncpy(ptParamConf->szQueueName, szDivArq, TAM_SZ_QUEUE_NAME);
        }
        else if (strcmp(szDivPre, "ServerUrl")==0)
        {
            strncpy(ptParamConf->szServerUrl, szDivArq, TAM_SZ_SERVER_URL);
        }
        else if (strcmp(szDivPre, "UserName")==0)
        {
            strncpy(ptParamConf->szUserName, szDivArq, TAM_SZ_USERNAME);
        }
        else if (strcmp(szDivPre, "Password")==0)
        {
            strncpy(ptParamConf->szPassword, szDivArq, TAM_SZ_PASSWORD);
        }
        else if (strcmp(szDivPre, "Pk_password")==0)
        {
            strncpy(ptParamConf->szPk_password, szDivArq, TAM_SZ_PKPASSWD);
        }

        memset(szLinha,  0, sizeof(szLinha));
        memset(szDivPre, 0, sizeof(szDivPre));
        memset(szDivArq, 0, sizeof(szDivArq));
    }

    // Fechando o arquivo de configuracao aberto
    mfConfig.fechar();

    //sprintf(szAux, "ptParamConf->szPws[%s]", ptParamConf->szPws); oLog.logDebug(szAux);
    sprintf(szAux, "ptParamConf->szUsr[%s]", ptParamConf->szUsr); oLog.logDebug(szAux);
    sprintf(szAux, "ptParamConf->szInst[%s]", ptParamConf->szInst); oLog.logDebug(szAux);
    //sprintf(szAux, "ptParamConf->szPwsTux[%s]", ptParamConf->szPwsTux); oLog.logDebug(szAux);
    sprintf(szAux, "ptParamConf->szUsrTux[%s]", ptParamConf->szUsrTux); oLog.logDebug(szAux);
    //sprintf(szAux, "ptParamConf->szPwsTuxGen[%s]", ptParamConf->szPwsTuxGen); oLog.logDebug(szAux);
    sprintf(szAux, "ptParamConf->szCltTux[%s]", ptParamConf->szCltTux); oLog.logDebug(szAux);

    sprintf(szAux, "ptParamConf->szQueueName[%s]", ptParamConf->szQueueName); oLog.logDebug(szAux);
    sprintf(szAux, "ptParamConf->szServerUrl[%s]", ptParamConf->szServerUrl); oLog.logDebug(szAux);
    sprintf(szAux, "ptParamConf->szUserName[%s]", ptParamConf->szUserName); oLog.logDebug(szAux);
    //sprintf(szAux, "ptParamConf->szPassword[%s]", ptParamConf->szPassword); oLog.logDebug(szAux);
    //sprintf(szAux, "ptParamConf->szPk_password[%s]", ptParamConf->szPk_password); oLog.logDebug(szAux);

    // Verifica se todos os dados foram recuperados do arquivo de configuração.
    if (strlen(ptParamConf->szInst) == 0 ||
        strlen(ptParamConf->szPwsTux) == 0 ||
        strlen(ptParamConf->szUsrTux) == 0 ||
        strlen(ptParamConf->szPwsTuxGen) == 0 ||
        strlen(ptParamConf->szCltTux) == 0 ||
        strlen(ptParamConf->szQueueName) == 0 ||
        strlen(ptParamConf->szServerUrl) == 0 ||
        strlen(ptParamConf->szUserName) == 0 ||
        strlen(ptParamConf->szPassword) == 0)
    {
        oLog.logError("Dados incompletos!");
        return -1;
    }

    oLog.logDebug("Parametros de configuracao obtidos com sucesso...");
    return 0;
}

/************************************************************************************************************/
int DBConnect(char *pUsr, char *pPwd, char *pInst)
{
    /* EXEC SQL BEGIN DECLARE SECTION; */ 

        char connString[256];
    /* EXEC SQL END DECLARE SECTION; */ 


    // String de conexao
    sprintf(connString, "%s/%s@%s", pUsr, pPwd, pInst);

    // Marca ponto de erro
    /* EXEC SQL WHENEVER SQLERROR GOTO errConn; */ 


    // Conecta
    /* EXEC SQL CONNECT :connString; */ 

{
    struct sqlexd sqlstm;
    sqlstm.sqlvsn = 12;
    sqlstm.arrsiz = 5;
    sqlstm.sqladtp = &sqladt;
    sqlstm.sqltdsp = &sqltds;
    sqlstm.iters = (unsigned int  )10;
    sqlstm.offset = (unsigned int  )158;
    sqlstm.cud = sqlcud0;
    sqlstm.sqlest = (unsigned char  *)&sqlca;
    sqlstm.sqlety = (unsigned short)256;
    sqlstm.occurs = (unsigned int  )0;
    sqlstm.sqhstv[0] = (unsigned char  *)connString;
    sqlstm.sqhstl[0] = (unsigned long )256;
    sqlstm.sqhsts[0] = (         int  )256;
    sqlstm.sqindv[0] = (         short *)0;
    sqlstm.sqinds[0] = (         int  )0;
    sqlstm.sqharm[0] = (unsigned long )0;
    sqlstm.sqadto[0] = (unsigned short )0;
    sqlstm.sqtdso[0] = (unsigned short )0;
    sqlstm.sqphsv = sqlstm.sqhstv;
    sqlstm.sqphsl = sqlstm.sqhstl;
    sqlstm.sqphss = sqlstm.sqhsts;
    sqlstm.sqpind = sqlstm.sqindv;
    sqlstm.sqpins = sqlstm.sqinds;
    sqlstm.sqparm = sqlstm.sqharm;
    sqlstm.sqparc = sqlstm.sqharc;
    sqlstm.sqpadto = sqlstm.sqadto;
    sqlstm.sqptdso = sqlstm.sqtdso;
    sqlstm.sqlcmax = (unsigned int )100;
    sqlstm.sqlcmin = (unsigned int )2;
    sqlstm.sqlcincr = (unsigned int )1;
    sqlstm.sqlctimeout = (unsigned int )0;
    sqlstm.sqlcnowait = (unsigned int )0;
    sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
    if (sqlca.sqlcode < 0) goto errConn;
}



    return (sqlca.sqlcode);

errConn:
    oLog.logInformation("<<<DBConnect [ERROR]");
    return -1;
}

/************************************************************************************************************/
void DBDisconnect(void)
{
    /* EXEC SQL WHENEVER SQLERROR GOTO Error; */ 

    /* EXEC SQL COMMIT WORK RELEASE; */ 

{
    struct sqlexd sqlstm;
    sqlstm.sqlvsn = 12;
    sqlstm.arrsiz = 5;
    sqlstm.sqladtp = &sqladt;
    sqlstm.sqltdsp = &sqltds;
    sqlstm.iters = (unsigned int  )1;
    sqlstm.offset = (unsigned int  )189;
    sqlstm.cud = sqlcud0;
    sqlstm.sqlest = (unsigned char  *)&sqlca;
    sqlstm.sqlety = (unsigned short)256;
    sqlstm.occurs = (unsigned int  )0;
    sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
    if (sqlca.sqlcode < 0) goto Error;
}


    return;

Error:
    sprintf(szAux, "ERRO ORACLE -> sqlcode=%d,sqlerrmc=%.70s",sqlca.sqlcode,sqlca.sqlerrm.sqlerrmc); oLog.logDebug(szAux);
    return;
}

/************************************************************************************************************/
void Fail(const char* message, tibems_status s)
{
    const char* msg = tibemsStatus_GetText(s);
    printf("ERROR: %s\n",message);
    printf("\tSTATUS: %d %s\n",s,msg?msg:"(Undefined Error)");
    exit(1);
}

/************************************************************************************************************/
int ObterParamMaxRegistros(int LimMax)
{
    /* EXEC SQL BEGIN DECLARE SECTION; */ 

        int MaxRegs=0;
    /* EXEC SQL END DECLARE SECTION; */ 


    struct sqlca sqlca;

    /* EXEC SQL WHENEVER SQLERROR goto erro; */ 

    /* EXEC SQL WHENEVER NOT FOUND goto naoexiste; */ 


    /* EXEC SQL
        SELECT
            TO_NUMBER(NVL(DSVALORPARAMETRO,2000))
        INTO
            :MaxRegs
        FROM
            APOIO.PARAMETRO
        WHERE
            CDPARAMETRO = 'SMS_VINCULORECARGA_NRO_MAXREGS'; */ 

{
    struct sqlexd sqlstm;
    sqlstm.sqlvsn = 12;
    sqlstm.arrsiz = 5;
    sqlstm.sqladtp = &sqladt;
    sqlstm.sqltdsp = &sqltds;
    sqlstm.stmt = "select TO_NUMBER(NVL(DSVALORPARAMETRO,2000)) into :b0  fr\
om APOIO.PARAMETRO where CDPARAMETRO='SMS_VINCULORECARGA_NRO_MAXREGS'";
    sqlstm.iters = (unsigned int  )1;
    sqlstm.offset = (unsigned int  )204;
    sqlstm.selerr = (unsigned short)1;
    sqlstm.cud = sqlcud0;
    sqlstm.sqlest = (unsigned char  *)&sqlca;
    sqlstm.sqlety = (unsigned short)256;
    sqlstm.occurs = (unsigned int  )0;
    sqlstm.sqhstv[0] = (unsigned char  *)&MaxRegs;
    sqlstm.sqhstl[0] = (unsigned long )sizeof(int);
    sqlstm.sqhsts[0] = (         int  )0;
    sqlstm.sqindv[0] = (         short *)0;
    sqlstm.sqinds[0] = (         int  )0;
    sqlstm.sqharm[0] = (unsigned long )0;
    sqlstm.sqadto[0] = (unsigned short )0;
    sqlstm.sqtdso[0] = (unsigned short )0;
    sqlstm.sqphsv = sqlstm.sqhstv;
    sqlstm.sqphsl = sqlstm.sqhstl;
    sqlstm.sqphss = sqlstm.sqhsts;
    sqlstm.sqpind = sqlstm.sqindv;
    sqlstm.sqpins = sqlstm.sqinds;
    sqlstm.sqparm = sqlstm.sqharm;
    sqlstm.sqparc = sqlstm.sqharc;
    sqlstm.sqpadto = sqlstm.sqadto;
    sqlstm.sqptdso = sqlstm.sqtdso;
    sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
    if (sqlca.sqlcode == 1403) goto naoexiste;
    if (sqlca.sqlcode < 0) goto erro;
}


	 
    if ( MaxRegs > LimMax )
    {
        sprintf(szAux,"Valor solicitado de %d é maior que o limite máximo. Assumindo limite máximo.",MaxRegs);
        oLog.logDebug(szAux);
        MaxRegs = LimMax;
    }

    if ( MaxRegs < 0 )
    {
        oLog.logDebug("Parametro 'SMS_VINCULORECARGA_NRO_MAXREGS' com valor inválido, assumindo default.");
        MaxRegs = 2000;
    }

    return MaxRegs;

erro:
    sprintf(szAux,"maxRegsPrm default=%d,errcode=%d",2000,sqlca.sqlcode); oLog.logDebug(szAux);
    return 2000;

naoexiste:
    sprintf(szAux,"Parametro 'SMS_VINCULORECARGA_NRO_MAXREGS' nao encontrado. Assumindo %d",2000); oLog.logDebug(szAux);
    return 2000;
}

/************************************************************************************************************/
void ObterParamMsgParse(TParamConf *tParamConf)
{
    /* EXEC SQL BEGIN DECLARE SECTION; */ 

        /* VARCHAR varOraDsValorParametro[256]; */ 
struct { unsigned short len; unsigned char arr[256]; } varOraDsValorParametro;

        short statOraDsValorParametro = -1;
    /* EXEC SQL END DECLARE SECTION; */ 


    struct sqlca sqlca;

    /* EXEC SQL WHENEVER SQLERROR goto erroParse; */ 

    /* EXEC SQL WHENEVER NOT FOUND goto naoexisteParse; */ 


    /* EXEC SQL
        SELECT
            NVL(DSVALORPARAMETRO,'S')
        INTO
            :varOraDsValorParametro:statOraDsValorParametro
        FROM
            APOIO.PARAMETRO
        WHERE
            CDPARAMETRO = 'SMS_PROTO_MSGPARSE'; */ 

{
    struct sqlexd sqlstm;
    sqlstm.sqlvsn = 12;
    sqlstm.arrsiz = 5;
    sqlstm.sqladtp = &sqladt;
    sqlstm.sqltdsp = &sqltds;
    sqlstm.stmt = "select NVL(DSVALORPARAMETRO,'S') into :b0:b1  from APOIO.\
PARAMETRO where CDPARAMETRO='SMS_PROTO_MSGPARSE'";
    sqlstm.iters = (unsigned int  )1;
    sqlstm.offset = (unsigned int  )223;
    sqlstm.selerr = (unsigned short)1;
    sqlstm.cud = sqlcud0;
    sqlstm.sqlest = (unsigned char  *)&sqlca;
    sqlstm.sqlety = (unsigned short)256;
    sqlstm.occurs = (unsigned int  )0;
    sqlstm.sqhstv[0] = (unsigned char  *)&varOraDsValorParametro;
    sqlstm.sqhstl[0] = (unsigned long )258;
    sqlstm.sqhsts[0] = (         int  )0;
    sqlstm.sqindv[0] = (         short *)&statOraDsValorParametro;
    sqlstm.sqinds[0] = (         int  )0;
    sqlstm.sqharm[0] = (unsigned long )0;
    sqlstm.sqadto[0] = (unsigned short )0;
    sqlstm.sqtdso[0] = (unsigned short )0;
    sqlstm.sqphsv = sqlstm.sqhstv;
    sqlstm.sqphsl = sqlstm.sqhstl;
    sqlstm.sqphss = sqlstm.sqhsts;
    sqlstm.sqpind = sqlstm.sqindv;
    sqlstm.sqpins = sqlstm.sqinds;
    sqlstm.sqparm = sqlstm.sqharm;
    sqlstm.sqparc = sqlstm.sqharc;
    sqlstm.sqpadto = sqlstm.sqadto;
    sqlstm.sqptdso = sqlstm.sqtdso;
    sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
    if (sqlca.sqlcode == 1403) goto naoexisteParse;
    if (sqlca.sqlcode < 0) goto erroParse;
}



    CONVIND(varOraDsValorParametro,statOraDsValorParametro);
    SAFE_STRNCPY(tParamConf->szMessageParse,(char*)varOraDsValorParametro.arr);

    return;

erroParse:
    sprintf(szAux,"Erro %d na busca do parametro 'SMS_PROTO_MSGPARSE'. Assumindo 'S'.",sqlca.sqlcode);
    oLog.logDebug(szAux);
    SAFE_STRNCPY(tParamConf->szMessageParse,"S");

naoexisteParse:
    sprintf(szAux,"Parametro 'SMS_PROTO_MSGPARSE' nao encontrado. Assumindo 'S'.");
    oLog.logDebug(szAux);
    SAFE_STRNCPY(tParamConf->szMessageParse,"S");
}

/************************************************************************************************************/
void ObterParamMsgLog(TParamConf *tParamConf)
{
    /* EXEC SQL BEGIN DECLARE SECTION; */ 

        /* VARCHAR varOraDsValorParametro[256]; */ 
struct { unsigned short len; unsigned char arr[256]; } varOraDsValorParametro;

        short statOraDsValorParametro = -1;
    /* EXEC SQL END DECLARE SECTION; */ 


    struct sqlca sqlca;

    /* EXEC SQL WHENEVER SQLERROR goto erroLog; */ 

    /* EXEC SQL WHENEVER NOT FOUND goto naoexisteLog; */ 


    /* EXEC SQL
        SELECT
            NVL(DSVALORPARAMETRO,'N')
        INTO
            :varOraDsValorParametro:statOraDsValorParametro
        FROM
            APOIO.PARAMETRO
        WHERE
            CDPARAMETRO = 'SMS_PROTO_MSGLOG'; */ 

{
    struct sqlexd sqlstm;
    sqlstm.sqlvsn = 12;
    sqlstm.arrsiz = 5;
    sqlstm.sqladtp = &sqladt;
    sqlstm.sqltdsp = &sqltds;
    sqlstm.stmt = "select NVL(DSVALORPARAMETRO,'N') into :b0:b1  from APOIO.\
PARAMETRO where CDPARAMETRO='SMS_PROTO_MSGLOG'";
    sqlstm.iters = (unsigned int  )1;
    sqlstm.offset = (unsigned int  )242;
    sqlstm.selerr = (unsigned short)1;
    sqlstm.cud = sqlcud0;
    sqlstm.sqlest = (unsigned char  *)&sqlca;
    sqlstm.sqlety = (unsigned short)256;
    sqlstm.occurs = (unsigned int  )0;
    sqlstm.sqhstv[0] = (unsigned char  *)&varOraDsValorParametro;
    sqlstm.sqhstl[0] = (unsigned long )258;
    sqlstm.sqhsts[0] = (         int  )0;
    sqlstm.sqindv[0] = (         short *)&statOraDsValorParametro;
    sqlstm.sqinds[0] = (         int  )0;
    sqlstm.sqharm[0] = (unsigned long )0;
    sqlstm.sqadto[0] = (unsigned short )0;
    sqlstm.sqtdso[0] = (unsigned short )0;
    sqlstm.sqphsv = sqlstm.sqhstv;
    sqlstm.sqphsl = sqlstm.sqhstl;
    sqlstm.sqphss = sqlstm.sqhsts;
    sqlstm.sqpind = sqlstm.sqindv;
    sqlstm.sqpins = sqlstm.sqinds;
    sqlstm.sqparm = sqlstm.sqharm;
    sqlstm.sqparc = sqlstm.sqharc;
    sqlstm.sqpadto = sqlstm.sqadto;
    sqlstm.sqptdso = sqlstm.sqtdso;
    sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
    if (sqlca.sqlcode == 1403) goto naoexisteLog;
    if (sqlca.sqlcode < 0) goto erroLog;
}



    CONVIND(varOraDsValorParametro,statOraDsValorParametro);
    SAFE_STRNCPY(tParamConf->szMessageLog,(char*)varOraDsValorParametro.arr);

    return;

erroLog:
    sprintf(szAux,"Erro %d na busca do parametro 'SMS_PROTO_MSGLOG'. Assumindo 'N'.",sqlca.sqlcode);
    oLog.logDebug(szAux);
    SAFE_STRNCPY(tParamConf->szMessageLog,"N");

naoexisteLog:
    sprintf(szAux,"Parametro 'SMS_PROTO_MSGLOG' nao encontrado. Assumindo 'N'.");
    oLog.logDebug(szAux);
    SAFE_STRNCPY(tParamConf->szMessageLog,"N");
}

/********************************************************************************************/
int Process(char *pszFileName, int iFlagLock)
{
    static int semid;
    time_t tStart, tEnd;
    double tDiff;
    key_t key;

    if(iFlagLock == UNLOCK)
    {
        UnLockSemaphore(semid);
    }
    else if(iFlagLock == LOCK)
    {

        if((key = ftok(pszFileName, 'a')) == (key_t) -1)
        {
            perror("IPC error: ftok");
            exit(-1);
        }

        if((semid = InitSemaphore(key, 1)) == -1)
        {
            perror("InitSemaphore");
            exit(-1);
        }

        time(&tStart);
        LockSemaphore(semid);
        time(&tEnd);

        tDiff = difftime(tEnd, tStart);

        // tempo limite para espera de processamento
        if(tDiff > (double)0)
            return -1;
    }
    else if(iFlagLock == KILL)
    {
        semctl(semid, 0, IPC_RMID); // deleta o semaforo
    }
    else
    {
        exit(-1);
    }

    return 0;
}

/********************************************************************************************/
void UnLockSemaphore(int semid)
{
    struct sembuf sb;

    sb.sem_num = 0;
    sb.sem_op = 1; // free resource
    sb.sem_flg = SEM_UNDO;

    if (semop(semid, &sb, 1) == -1)
    {
        perror("semop");
        exit(1);
    }
}

/********************************************************************************************/
void LockSemaphore(int semid)
{
    struct sembuf sb;

    sb.sem_num = 0;
    sb.sem_op = -1;  // set to allocate resource
    sb.sem_flg = SEM_UNDO;

    if (semop(semid, &sb, 1) == -1)
    {
        perror("semop");
        exit(1);
    }
}

/********************************************************************************************/
/*
** InitSemaphore() -- more-than-inspired by W. Richard Stevens' UNIX Network
** Programming 2nd edition, volume 2, lockvsem.c, page 295.
*/
int InitSemaphore(key_t key, int nsems)
{
    union semun
    {
        int val;
        struct semid_ds *buf;
        ushort *array;
    } arg;

    struct semid_ds buf;
    struct sembuf sb;
    int semid;

    semid = semget(key, nsems, IPC_CREAT | IPC_EXCL | 0666);
    if (semid >= 0)
    { // we got it first
        sb.sem_op = 1;
        sb.sem_flg = 0;
        arg.val = 1;

        for(sb.sem_num = 0; sb.sem_num < nsems; sb.sem_num++)
        {
            // do a semop() to "free" the semaphores.
            // this sets the sem_otime field, as needed below.
            if (semop(semid, &sb, 1) == -1)
            {
                int e = errno;
                semctl(semid, 0, IPC_RMID); // clean up
                errno = e;
                return -1; // error, check errno
            }
        }
    }
    else if (errno == EEXIST)
    {
        int ready = 0;

        semid = semget(key, nsems, 0); // get the id
        if (semid < 0)
            return semid; // error, check errno

        // wait for other process to initialize the semaphore:
        arg.buf = &buf;

        for(int i = 0; i < MAX_RETRIES && !ready; i++)
        {
            semctl(semid, nsems-1, IPC_STAT, arg);

            if (arg.buf->sem_otime != 0)
            {
                ready = 1;
            }
            else
            {
                sleep(1);
            }
        }
        if(!ready)
        {
            errno = ETIME;
            return -1;
        }
    }
    else
    {
        return semid; // error, check errno
    }

    return semid;
}
