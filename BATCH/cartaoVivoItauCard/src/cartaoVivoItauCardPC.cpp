
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
           char  filnam[32];
};
static const struct sqlcxp sqlfpn =
{
    31,
    ".\\src\\cartaoVivoItauCardPC.pcpp"
};


static unsigned int sqlctx = 261430716;


static struct sqlexd {
   unsigned int   sqlvsn;
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
            void  **sqphsv;
   unsigned int   *sqphsl;
            int   *sqphss;
            void  **sqpind;
            int   *sqpins;
   unsigned int   *sqparm;
   unsigned int   **sqparc;
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
            void  *sqhstv[4];
   unsigned int   sqhstl[4];
            int   sqhsts[4];
            void  *sqindv[4];
            int   sqinds[4];
   unsigned int   sqharm[4];
   unsigned int   *sqharc[4];
   unsigned short  sqadto[4];
   unsigned short  sqtdso[4];
} sqlstm = {12,4};

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

typedef struct { unsigned short len; unsigned char arr[1]; } VARCHAR;
typedef struct { unsigned short len; unsigned char arr[1]; } varchar;

/* cud (compilation unit data) array */
static const short sqlcud0[] =
{12,4130,178,0,0,
5,0,0,1,66,0,4,223,0,0,1,0,0,1,0,2,9,0,0,
24,0,0,2,94,0,4,329,0,0,2,1,0,1,0,2,3,0,0,1,97,0,0,
47,0,0,3,91,0,4,354,0,0,2,1,0,1,0,2,9,0,0,1,97,0,0,
70,0,0,4,93,0,4,391,0,0,3,2,0,1,0,2,3,0,0,1,9,0,0,1,9,0,0,
97,0,0,5,175,0,3,404,0,0,4,4,0,1,0,1,9,0,0,1,9,0,0,1,9,0,0,1,9,0,0,
128,0,0,6,0,0,31,417,0,0,0,0,0,1,0,
143,0,0,7,0,0,29,427,0,0,0,0,0,1,0,
158,0,0,8,164,0,5,432,0,0,4,4,0,1,0,1,9,0,0,1,9,0,0,1,9,0,0,1,9,0,0,
189,0,0,9,0,0,31,446,0,0,0,0,0,1,0,
204,0,0,10,0,0,29,456,0,0,0,0,0,1,0,
219,0,0,11,76,0,2,463,0,0,2,2,0,1,0,1,9,0,0,1,9,0,0,
242,0,0,12,0,0,29,470,0,0,0,0,0,1,0,
257,0,0,13,0,0,27,667,0,0,4,4,0,1,0,1,97,0,0,1,10,0,0,1,10,0,0,1,10,0,0,
288,0,0,14,0,0,30,682,0,0,0,0,0,1,0,
};


/**
 * 
 * @modulo  Batch
 * @usecase Batch
 * @author  Cassio
 * @version $Revision: 1.1.2.1.82.1 $
 * @CVS     $Author: a5114878 $ - $Date: 2012/07/12 15:37:57 $
 **/

#ifdef WIN32
#pragma warning(disable:4786)
#endif

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#ifdef WIN32
#include <io.h>
#include <direct.h>
#endif
#ifndef WIN32
#include <dirent.h>
#include <unistd.h>
#include <sys/types.h>
#include <sys/ipc.h>
#include <sys/sem.h>
#endif
#include <ctype.h>
#include <errno.h>
#include <time.h>

#include <string>
#include <iostream>
#include <fstream>

#include "../include/cartaoVivoItauCardPC.h"
#include "../../commons/Propriedade/include/MFile.h"
#include "../../commons/Log/include/Log.h"
#include "../../commons/SplitLine.h"

using namespace std;

/* EXEC SQL INCLUDE SQLCA;
 */ 
/*
 * $Header: /cvs/BATCH/cartaoVivoItauCard/src/cartaoVivoItauCardPC.cpp,v 1.1.2.1.82.1 2012/07/12 15:37:57 a5114878 Exp $ sqlca.h 
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


/************************************************************************************************************/
char szAux[512];
Log oLog;
int iSignalProcessa=1;

#define MAX_RETRIES     10

#define LOCK            1
#define UNLOCK          0
#define KILL            2

#define SAFE_STRNCPY(dst,src) strncpy(dst,src?src:"",sizeof(dst)-1);dst[sizeof(dst)-1]=0;
#define CONVIND(O,I) \
{\
    if (I == -1) { \
        ##O.arr[0]=0; \
    } else { \
        ##O.arr[##O.len]=0; \
    } \
}

/* EXEC SQL BEGIN DECLARE SECTION; */ 

#define TAM_CPO_CPF             11
#define POS_COP_CPF             0
#define TAM_CPO_CDAREAREGISTRO  3
#define POS_COP_CDAREAREGISTRO  POS_COP_CPF + TAM_CPO_CPF
#define TAM_CPO_NRLINHA         9
#define POS_COP_NRLINHA         POS_COP_CDAREAREGISTRO + TAM_CPO_CDAREAREGISTRO
#define TAM_CPO_CODPRODUTO      4
#define POS_COP_CODPRODUTO      POS_COP_NRLINHA + TAM_CPO_NRLINHA
#define TAM_CPO_CODOPERACAO     3
#define POS_COP_CODOPERACAO     POS_COP_CODPRODUTO + TAM_CPO_CODPRODUTO
#define TAM_LINHA               TAM_CPO_CPF+TAM_CPO_CDAREAREGISTRO+\
                                TAM_CPO_NRLINHA+TAM_CPO_CODPRODUTO+\
                                TAM_CPO_CODOPERACAO
/* EXEC SQL END DECLARE SECTION; */ 


/************************************************************************************************************/
// Protótipos
#ifndef WIN32
int Process(char *pszFileName, int iFlagLock);
void UnLockSemaphore(int semid);
void LockSemaphore(int semid);
int InitSemaphore(key_t key, int nsems);
void ArmaSinal(int iSignal);
#endif

/************************************************************************************************************/
int main(int argc, char* argv[])
{
    TParamConf tParamConf;
    char streamBuf[1025];

    /* EXEC SQL BEGIN DECLARE SECTION; */ 


        /* VARCHAR varOraDataAtual[32]; */ 
struct { unsigned short len; unsigned char arr[32]; } varOraDataAtual;

        short statOraDataAtual = -1;

        char varOraCdAreaRegistro[TAM_CPO_CDAREAREGISTRO+1];
        char varOraCodProduto[TAM_CPO_CODPRODUTO+1];

        struct AddLinhaItauCard
        {
            /* VARCHAR idAreaRegistro[3]; */ 
struct { unsigned short len; unsigned char arr[3]; } idAreaRegistro;

            /* VARCHAR nrLinha[TAM_CPO_NRLINHA+1]; */ 
struct { unsigned short len; unsigned char arr[9]; } nrLinha;

            /* VARCHAR idTipoCartaoVivoItauCard[TAM_CPO_CODPRODUTO+1]; */ 
struct { unsigned short len; unsigned char arr[5]; } idTipoCartaoVivoItauCard;

            /* VARCHAR nrCpf[TAM_CPO_CPF+1]; */ 
struct { unsigned short len; unsigned char arr[12]; } nrCpf;

        } varOraLinhaItauCardAdd;

        struct AddLinhaItauCardStat
        {
            short idAreaRegistro;
            short nrLinha;
            short idTipoCartaoVivoItauCard;
            short nrCpf;
        } statOraLinhaItauCardAdd;

        struct RemLinhaItauCard
        {
            /* VARCHAR idAreaRegistro[3]; */ 
struct { unsigned short len; unsigned char arr[3]; } idAreaRegistro;

            /* VARCHAR nrLinha[TAM_CPO_NRLINHA+1]; */ 
struct { unsigned short len; unsigned char arr[9]; } nrLinha;

        } varOraLinhaItauCardRem;

        int varOraCount;

    /* EXEC SQL END DECLARE SECTION; */ 


    oLog.setNivel(2);

#ifndef WIN32
    ArmaSinal(SIGTERM);

    //==========================================================================
    // Lock"a"
    oLog.logDebug("Vai lockar fila de processamento...\n");
    int iRet = Process(argv[0],LOCK);

    if(iRet)
    {
        oLog.logInformation("Saida por TIMEOUT\n");
        return -1;
    }
    oLog.logDebug("Locou fila de processamento\n");
#endif // #ifndef WIN32

    //==========================================================================
    // parametros de configuração
    if(ObtemParamConf(&tParamConf))
    {
        return -1;
    }

    //==========================================================================
    // Verifica existencia de diretórios de processamento
#ifndef WIN32    
    // char *curDir = getcwd(0,2048);
    // sprintf(szAux,"curDir=%s",curDir); oLog.logError(szAux);

    // if ( chdir(tParamConf.szPathIn) != 0 )
    // {
    //     sprintf(szAux,"Diretório %s não existe",tParamConf.szPathIn); oLog.logError(szAux);
// #ifndef WIN32
    //     Process(argv[0], UNLOCK);
    //     oLog.logDebug("unlock executado\n");
// #endif // #ifndef WIN32
    //     return -1;
    // }

    if ( chdir(tParamConf.szPathErr) != 0 )
    {
        sprintf(szAux,"Diretório %s não existe",tParamConf.szPathErr); oLog.logError(szAux);
#ifndef WIN32
        Process(argv[0], UNLOCK);
        oLog.logDebug("unlock executado\n");
#endif // #ifndef WIN32
        return -1;
    }

    if ( chdir(tParamConf.szPathProcessados) != 0 )
    {
        sprintf(szAux,"Diretório %s não existe",tParamConf.szPathProcessados); oLog.logError(szAux);
#ifndef WIN32
        Process(argv[0], UNLOCK);
        oLog.logDebug("unlock executado\n");
#endif // #ifndef WIN32
        return -1;
    }

    // if ( chdir(curDir) != 0 )
    // {
    //     free(curDir);
    //     sprintf(szAux,"Diretório %s não existe",curDir); oLog.logError(szAux);
// #ifndef WIN32
    //     Process(argv[0], UNLOCK);
    //     oLog.logDebug("unlock executado\n");
// #endif // #ifndef WIN32
    //     return -1;
    // }
    // free(curDir);
#endif

    //==========================================================================
    // Conecta
    if (DBConnect(tParamConf.szUsr, tParamConf.szPws, tParamConf.szInst))
    {
        oLog.logError("Erro conectando no banco de dados");
#ifndef WIN32
        Process(argv[0], UNLOCK);
        oLog.logDebug("unlock executado\n");
#endif // #ifndef WIN32
        return -1;
    }

    //==========================================================================
    // Arquivo a ser processado
    // EXEC SQL WHENEVER SQLERROR GOTO sqlError;

    /* EXEC SQL SELECT TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS') INTO :varOraDataAtual:statOraDataAtual FROM DUAL; */ 

{
    struct sqlexd sqlstm;
    sqlstm.sqlvsn = 12;
    sqlstm.arrsiz = 1;
    sqlstm.sqladtp = &sqladt;
    sqlstm.sqltdsp = &sqltds;
    sqlstm.stmt = "select TO_CHAR(SYSDATE,'YYYYMMDDHH24MISS') into :b0:b1  f\
rom DUAL ";
    sqlstm.iters = (unsigned int  )1;
    sqlstm.offset = (unsigned int  )5;
    sqlstm.selerr = (unsigned short)1;
    sqlstm.cud = sqlcud0;
    sqlstm.sqlest = (unsigned char  *)&sqlca;
    sqlstm.sqlety = (unsigned short)256;
    sqlstm.occurs = (unsigned int  )0;
    sqlstm.sqhstv[0] = (         void  *)&varOraDataAtual;
    sqlstm.sqhstl[0] = (unsigned int  )34;
    sqlstm.sqhsts[0] = (         int  )0;
    sqlstm.sqindv[0] = (         void  *)&statOraDataAtual;
    sqlstm.sqinds[0] = (         int  )0;
    sqlstm.sqharm[0] = (unsigned int  )0;
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
}



    if ( sqlca.sqlcode )
    {
        //@cassio - buscar data do sistema neste caso!
        strcpy((char*)varOraDataAtual.arr,"001"); // @cassio
        string outputFileNameErr = (string)tParamConf.szPathErr + "/" + (string)tParamConf.szFileNameErr +
                           "_ERRO_" + (char*)varOraDataAtual.arr + ".txt";
        string errMsg = (string)(sqlca.sqlerrm.sqlerrmc) + "\n";
        ofstream outputFileErr(outputFileNameErr.c_str(), ios::out|ios::binary);
        outputFileErr.write(errMsg.c_str(),errMsg.length());
        outputFileErr.close();

        sprintf(szAux,"erro oracle %s",sqlca.sqlerrm.sqlerrmc); oLog.logError(szAux);

#ifndef WIN32
        Process(argv[0], UNLOCK);
        oLog.logDebug("unlock executado\n");
#endif // #ifndef WIN32
        return -1;
    }

    CONVIND(varOraDataAtual,statOraDataAtual);


    // Define os nomes dos arquivos
    char fname[_MAX_FNAME+1];
    char ext[_MAX_EXT+1];

    string inputFileName = (string)tParamConf.szPathIn + "/" + (string)tParamConf.szFileNameIn;

    _splitpath(inputFileName.c_str(),0,0,fname,ext);

    string inputFileNameProcessado = (string)tParamConf.szPathProcessados + "/" + (string)tParamConf.szFileNameErr
                           + (char*)varOraDataAtual.arr + ".txt";
    string outputFileNameErr = (string)tParamConf.szPathErr + "/" + (string)tParamConf.szFileNameErr +
                           "_ERRO_" + (char*)varOraDataAtual.arr + ".txt";

    ifstream inputFile(inputFileName.c_str(), ios::in);

    if( !inputFile.is_open() )
    {
        string errMsg = "arquivo "+inputFileName+" nao encontrado para ser processado.\n";
        ofstream outputFileErr(outputFileNameErr.c_str(), ios::out|ios::binary);
        outputFileErr.write(errMsg.c_str(),errMsg.length());
        outputFileErr.close();

        sprintf(szAux,"arquivo %s ser processado não foi encontrado!",inputFileName.c_str()); oLog.logDebug(szAux);

#ifndef WIN32
        Process(argv[0], UNLOCK);
        oLog.logDebug("unlock executado\n");
#endif // #ifndef WIN32
        return -1;
    }

    //==========================================================================
    // Processa
    sprintf(szAux," IN '%s'",inputFileName.c_str()); oLog.logDebug(szAux);
    sprintf(szAux,"ERR '%s'",outputFileNameErr.c_str()); oLog.logDebug(szAux);

    oLog.logDebug("Processando...");

    //EXEC SQL WHENEVER SQLERROR GOTO sqlError;

    /* EXEC SQL WHENEVER NOT FOUND CONTINUE; */ 


    //==========================================================================
    // Inicializa
    varOraCdAreaRegistro[sizeof(varOraCdAreaRegistro)-1] = 0;
    varOraCodProduto[sizeof(varOraCodProduto)-1] = 0;

    memset(&varOraLinhaItauCardAdd,0,sizeof(varOraLinhaItauCardAdd));
    memset(&statOraLinhaItauCardAdd,0,sizeof(statOraLinhaItauCardAdd));
    memset(&varOraLinhaItauCardRem,0,sizeof(varOraLinhaItauCardRem));

    int itRegAdd = 0;
    int itRegUpd = 0;
    int itRegRem = 0;
    int itRegErr = 0;

    //==========================================================================
    // Processa arquivo texto
    while ( inputFile.getline(streamBuf,sizeof(streamBuf)) )
    {
        // tratamento para interromper o processamento via sinal
        if(iSignalProcessa == 0)
        {
            oLog.logDebug("Tratamento de sinal de termino dentro do loop recebeu interrupção");
            break;
        }

        //======================================================================
        // Validação do registro de detalhe
        if ( TAM_LINHA != (inputFile.gcount() - 1) )
        {
            strcat(streamBuf,"Registro de detalhe inválido\n");
            ofstream outputFileErr(outputFileNameErr.c_str(), ios::out|ios::binary|ios::app);
            outputFileErr.write(streamBuf,strlen(streamBuf));
            outputFileErr.close();
            itRegErr++;
            continue;
        }

        memcpy(varOraCodProduto,streamBuf+POS_COP_CODPRODUTO,TAM_CPO_CODPRODUTO);

        /* EXEC SQL
            SELECT
                COUNT(1)
            INTO
                :varOraCount
            FROM
                APOIO.TIPOCARTAOVIVOITAUCARD
            WHERE
                IDTIPOCARTAOVIVOITAUCARD = :varOraCodProduto; */ 

{
        struct sqlexd sqlstm;
        sqlstm.sqlvsn = 12;
        sqlstm.arrsiz = 2;
        sqlstm.sqladtp = &sqladt;
        sqlstm.sqltdsp = &sqltds;
        sqlstm.stmt = "select count(1) into :b0  from APOIO.TIPOCARTAOVIVOIT\
AUCARD where IDTIPOCARTAOVIVOITAUCARD=:b1";
        sqlstm.iters = (unsigned int  )1;
        sqlstm.offset = (unsigned int  )24;
        sqlstm.selerr = (unsigned short)1;
        sqlstm.cud = sqlcud0;
        sqlstm.sqlest = (unsigned char  *)&sqlca;
        sqlstm.sqlety = (unsigned short)256;
        sqlstm.occurs = (unsigned int  )0;
        sqlstm.sqhstv[0] = (         void  *)&varOraCount;
        sqlstm.sqhstl[0] = (unsigned int  )sizeof(int);
        sqlstm.sqhsts[0] = (         int  )0;
        sqlstm.sqindv[0] = (         void  *)0;
        sqlstm.sqinds[0] = (         int  )0;
        sqlstm.sqharm[0] = (unsigned int  )0;
        sqlstm.sqadto[0] = (unsigned short )0;
        sqlstm.sqtdso[0] = (unsigned short )0;
        sqlstm.sqhstv[1] = (         void  *)varOraCodProduto;
        sqlstm.sqhstl[1] = (unsigned int  )5;
        sqlstm.sqhsts[1] = (         int  )0;
        sqlstm.sqindv[1] = (         void  *)0;
        sqlstm.sqinds[1] = (         int  )0;
        sqlstm.sqharm[1] = (unsigned int  )0;
        sqlstm.sqadto[1] = (unsigned short )0;
        sqlstm.sqtdso[1] = (unsigned short )0;
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
}



        if ( sqlca.sqlcode )
        {
            strcat(streamBuf,"Código do Produto da Conta não cadastrado\n");
            ofstream outputFileErr(outputFileNameErr.c_str(), ios::out|ios::binary|ios::app);
            outputFileErr.write(streamBuf,strlen(streamBuf));
            outputFileErr.close();
            itRegErr++;
            continue;
        }

        memcpy(varOraCdAreaRegistro,streamBuf+POS_COP_CDAREAREGISTRO,TAM_CPO_CDAREAREGISTRO);
        if ( varOraCdAreaRegistro[0] == ' ' ) varOraCdAreaRegistro[0] = '0';

        char *varOraCodOperacao = streamBuf+POS_COP_CODOPERACAO;

        /* EXEC SQL
            SELECT
                IDAREAREGISTRO
            INTO
                :varOraLinhaItauCardAdd.idAreaRegistro
            FROM
                APOIO.AREAREGISTRO
            WHERE
                CDAREAREGISTRO = TO_NUMBER(:varOraCdAreaRegistro); */ 

{
        struct sqlexd sqlstm;
        sqlstm.sqlvsn = 12;
        sqlstm.arrsiz = 2;
        sqlstm.sqladtp = &sqladt;
        sqlstm.sqltdsp = &sqltds;
        sqlstm.stmt = "select IDAREAREGISTRO into :b0  from APOIO.AREAREGIST\
RO where CDAREAREGISTRO=TO_NUMBER(:b1)";
        sqlstm.iters = (unsigned int  )1;
        sqlstm.offset = (unsigned int  )47;
        sqlstm.selerr = (unsigned short)1;
        sqlstm.cud = sqlcud0;
        sqlstm.sqlest = (unsigned char  *)&sqlca;
        sqlstm.sqlety = (unsigned short)256;
        sqlstm.occurs = (unsigned int  )0;
        sqlstm.sqhstv[0] = (         void  *)&(varOraLinhaItauCardAdd.idAreaRegistro);
        sqlstm.sqhstl[0] = (unsigned int  )5;
        sqlstm.sqhsts[0] = (         int  )0;
        sqlstm.sqindv[0] = (         void  *)0;
        sqlstm.sqinds[0] = (         int  )0;
        sqlstm.sqharm[0] = (unsigned int  )0;
        sqlstm.sqadto[0] = (unsigned short )0;
        sqlstm.sqtdso[0] = (unsigned short )0;
        sqlstm.sqhstv[1] = (         void  *)varOraCdAreaRegistro;
        sqlstm.sqhstl[1] = (unsigned int  )4;
        sqlstm.sqhsts[1] = (         int  )0;
        sqlstm.sqindv[1] = (         void  *)0;
        sqlstm.sqinds[1] = (         int  )0;
        sqlstm.sqharm[1] = (unsigned int  )0;
        sqlstm.sqadto[1] = (unsigned short )0;
        sqlstm.sqtdso[1] = (unsigned short )0;
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
}



        if ( sqlca.sqlcode )
        {
            sprintf(szAux,"ERRO ORACLE [%s]",sqlca.sqlerrm.sqlerrmc);oLog.logDebug(szAux);
            strcat(streamBuf,"Código de área não existe na base Vivo\n");
            ofstream outputFileErr(outputFileNameErr.c_str(), ios::out|ios::binary|ios::app);
            outputFileErr.write(streamBuf,strlen(streamBuf));
            outputFileErr.close();
            itRegErr++;
            continue;
        }

        //======================================================================
        // Persistência
        memcpy(varOraLinhaItauCardAdd.nrLinha.arr,streamBuf+POS_COP_NRLINHA,TAM_CPO_NRLINHA);
        varOraLinhaItauCardAdd.nrLinha.arr[TAM_CPO_NRLINHA] = 0;
        varOraLinhaItauCardAdd.nrLinha.len = TAM_CPO_NRLINHA;

        if (memcmp(varOraCodOperacao,"ADD",3) == 0 )
        {
            memcpy(varOraLinhaItauCardAdd.idTipoCartaoVivoItauCard.arr,varOraCodProduto,TAM_CPO_CODPRODUTO);
            varOraLinhaItauCardAdd.idTipoCartaoVivoItauCard.arr[TAM_CPO_CODPRODUTO] = 0;
            varOraLinhaItauCardAdd.idTipoCartaoVivoItauCard.len = TAM_CPO_CODPRODUTO;

            memcpy(varOraLinhaItauCardAdd.nrCpf.arr,streamBuf+POS_COP_CPF,TAM_CPO_CPF);
            varOraLinhaItauCardAdd.nrCpf.arr[TAM_CPO_CPF] = 0;
            varOraLinhaItauCardAdd.nrCpf.len = TAM_CPO_CPF;

            /* EXEC SQL
                SELECT
                    COUNT(1)
                INTO
                    :varOraCount
                FROM
                    LINHA.LINHAITAUCARD
                WHERE
                    IDAREAREGISTRO = :varOraLinhaItauCardAdd.idAreaRegistro
                AND NRLINHA = :varOraLinhaItauCardAdd.nrLinha; */ 

{
            struct sqlexd sqlstm;
            sqlstm.sqlvsn = 12;
            sqlstm.arrsiz = 3;
            sqlstm.sqladtp = &sqladt;
            sqlstm.sqltdsp = &sqltds;
            sqlstm.stmt = "select count(1) into :b0  from LINHA.LINHAITAUCAR\
D where (IDAREAREGISTRO=:b1 and NRLINHA=:b2)";
            sqlstm.iters = (unsigned int  )1;
            sqlstm.offset = (unsigned int  )70;
            sqlstm.selerr = (unsigned short)1;
            sqlstm.cud = sqlcud0;
            sqlstm.sqlest = (unsigned char  *)&sqlca;
            sqlstm.sqlety = (unsigned short)256;
            sqlstm.occurs = (unsigned int  )0;
            sqlstm.sqhstv[0] = (         void  *)&varOraCount;
            sqlstm.sqhstl[0] = (unsigned int  )sizeof(int);
            sqlstm.sqhsts[0] = (         int  )0;
            sqlstm.sqindv[0] = (         void  *)0;
            sqlstm.sqinds[0] = (         int  )0;
            sqlstm.sqharm[0] = (unsigned int  )0;
            sqlstm.sqadto[0] = (unsigned short )0;
            sqlstm.sqtdso[0] = (unsigned short )0;
            sqlstm.sqhstv[1] = (         void  *)&(varOraLinhaItauCardAdd.idAreaRegistro);
            sqlstm.sqhstl[1] = (unsigned int  )5;
            sqlstm.sqhsts[1] = (         int  )0;
            sqlstm.sqindv[1] = (         void  *)0;
            sqlstm.sqinds[1] = (         int  )0;
            sqlstm.sqharm[1] = (unsigned int  )0;
            sqlstm.sqadto[1] = (unsigned short )0;
            sqlstm.sqtdso[1] = (unsigned short )0;
            sqlstm.sqhstv[2] = (         void  *)&(varOraLinhaItauCardAdd.nrLinha);
            sqlstm.sqhstl[2] = (unsigned int  )11;
            sqlstm.sqhsts[2] = (         int  )0;
            sqlstm.sqindv[2] = (         void  *)0;
            sqlstm.sqinds[2] = (         int  )0;
            sqlstm.sqharm[2] = (unsigned int  )0;
            sqlstm.sqadto[2] = (unsigned short )0;
            sqlstm.sqtdso[2] = (unsigned short )0;
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
}



            if ( varOraCount == 0 )
            {
                /* EXEC SQL
                    INSERT INTO LINHA.LINHAITAUCARD(IDAREAREGISTRO,NRLINHA,IDTIPOCARTAOVIVOITAUCARD
                                                   ,NRCPF,IDUSUARIOALTERACAO,DTULTIMAALTERACAO)
                    VALUES(:varOraLinhaItauCardAdd.idAreaRegistro:statOraLinhaItauCardAdd.idAreaRegistro,
                           :varOraLinhaItauCardAdd.nrLinha:statOraLinhaItauCardAdd.nrLinha,
                           :varOraLinhaItauCardAdd.idTipoCartaoVivoItauCard:statOraLinhaItauCardAdd.idTipoCartaoVivoItauCard,
                           :varOraLinhaItauCardAdd.nrCpf:statOraLinhaItauCardAdd.nrCpf,
                           -99,
                           SYSDATE); */ 

{
                struct sqlexd sqlstm;
                sqlstm.sqlvsn = 12;
                sqlstm.arrsiz = 4;
                sqlstm.sqladtp = &sqladt;
                sqlstm.sqltdsp = &sqltds;
                sqlstm.stmt = "insert into LINHA.LINHAITAUCARD (IDAREAREGIST\
RO,NRLINHA,IDTIPOCARTAOVIVOITAUCARD,NRCPF,IDUSUARIOALTERACAO,DTULTIMAALTERACAO\
) values (:b0:b1,:b2:b3,:b4:b5,:b6:b7,(-99),SYSDATE)";
                sqlstm.iters = (unsigned int  )1;
                sqlstm.offset = (unsigned int  )97;
                sqlstm.cud = sqlcud0;
                sqlstm.sqlest = (unsigned char  *)&sqlca;
                sqlstm.sqlety = (unsigned short)256;
                sqlstm.occurs = (unsigned int  )0;
                sqlstm.sqhstv[0] = (         void  *)&(varOraLinhaItauCardAdd.idAreaRegistro);
                sqlstm.sqhstl[0] = (unsigned int  )5;
                sqlstm.sqhsts[0] = (         int  )0;
                sqlstm.sqindv[0] = (         void  *)&(statOraLinhaItauCardAdd.idAreaRegistro);
                sqlstm.sqinds[0] = (         int  )0;
                sqlstm.sqharm[0] = (unsigned int  )0;
                sqlstm.sqadto[0] = (unsigned short )0;
                sqlstm.sqtdso[0] = (unsigned short )0;
                sqlstm.sqhstv[1] = (         void  *)&(varOraLinhaItauCardAdd.nrLinha);
                sqlstm.sqhstl[1] = (unsigned int  )11;
                sqlstm.sqhsts[1] = (         int  )0;
                sqlstm.sqindv[1] = (         void  *)&(statOraLinhaItauCardAdd.nrLinha);
                sqlstm.sqinds[1] = (         int  )0;
                sqlstm.sqharm[1] = (unsigned int  )0;
                sqlstm.sqadto[1] = (unsigned short )0;
                sqlstm.sqtdso[1] = (unsigned short )0;
                sqlstm.sqhstv[2] = (         void  *)&(varOraLinhaItauCardAdd.idTipoCartaoVivoItauCard);
                sqlstm.sqhstl[2] = (unsigned int  )7;
                sqlstm.sqhsts[2] = (         int  )0;
                sqlstm.sqindv[2] = (         void  *)&(statOraLinhaItauCardAdd.idTipoCartaoVivoItauCard);
                sqlstm.sqinds[2] = (         int  )0;
                sqlstm.sqharm[2] = (unsigned int  )0;
                sqlstm.sqadto[2] = (unsigned short )0;
                sqlstm.sqtdso[2] = (unsigned short )0;
                sqlstm.sqhstv[3] = (         void  *)&(varOraLinhaItauCardAdd.nrCpf);
                sqlstm.sqhstl[3] = (unsigned int  )14;
                sqlstm.sqhsts[3] = (         int  )0;
                sqlstm.sqindv[3] = (         void  *)&(statOraLinhaItauCardAdd.nrCpf);
                sqlstm.sqinds[3] = (         int  )0;
                sqlstm.sqharm[3] = (unsigned int  )0;
                sqlstm.sqadto[3] = (unsigned short )0;
                sqlstm.sqtdso[3] = (unsigned short )0;
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
}



                if ( sqlca.sqlcode )
                {
                    sprintf(szAux,"ERRO ORACLE [%s]",sqlca.sqlerrm.sqlerrmc);oLog.logDebug(szAux);
                    /* EXEC SQL ROLLBACK; */ 

{
                    struct sqlexd sqlstm;
                    sqlstm.sqlvsn = 12;
                    sqlstm.arrsiz = 4;
                    sqlstm.sqladtp = &sqladt;
                    sqlstm.sqltdsp = &sqltds;
                    sqlstm.iters = (unsigned int  )1;
                    sqlstm.offset = (unsigned int  )128;
                    sqlstm.cud = sqlcud0;
                    sqlstm.sqlest = (unsigned char  *)&sqlca;
                    sqlstm.sqlety = (unsigned short)256;
                    sqlstm.occurs = (unsigned int  )0;
                    sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
}


                    strcat(streamBuf,sqlca.sqlerrm.sqlerrmc);
                    strcat(streamBuf," (erro na inserção)\n");
                    ofstream outputFileErr(outputFileNameErr.c_str(), ios::out|ios::binary|ios::app);
                    outputFileErr.write(streamBuf,strlen(streamBuf));
                    outputFileErr.close();
                    itRegErr++;
                    continue;
                }

                /* EXEC SQL COMMIT; */ 

{
                struct sqlexd sqlstm;
                sqlstm.sqlvsn = 12;
                sqlstm.arrsiz = 4;
                sqlstm.sqladtp = &sqladt;
                sqlstm.sqltdsp = &sqltds;
                sqlstm.iters = (unsigned int  )1;
                sqlstm.offset = (unsigned int  )143;
                sqlstm.cud = sqlcud0;
                sqlstm.sqlest = (unsigned char  *)&sqlca;
                sqlstm.sqlety = (unsigned short)256;
                sqlstm.occurs = (unsigned int  )0;
                sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
}


                itRegAdd++;
            }
            else
            {
                /* EXEC SQL
                    UPDATE
                        LINHA.LINHAITAUCARD
                    SET
                        IDTIPOCARTAOVIVOITAUCARD = :varOraLinhaItauCardAdd.idTipoCartaoVivoItauCard,
                        NRCPF = :varOraLinhaItauCardAdd.nrCpf,
                        IDUSUARIOALTERACAO = -98,
                        DTULTIMAALTERACAO = SYSDATE
                    WHERE
                        IDAREAREGISTRO = :varOraLinhaItauCardAdd.idAreaRegistro
                    AND NRLINHA = :varOraLinhaItauCardAdd.nrLinha; */ 

{
                struct sqlexd sqlstm;
                sqlstm.sqlvsn = 12;
                sqlstm.arrsiz = 4;
                sqlstm.sqladtp = &sqladt;
                sqlstm.sqltdsp = &sqltds;
                sqlstm.stmt = "update LINHA.LINHAITAUCARD  set IDTIPOCARTAOV\
IVOITAUCARD=:b0,NRCPF=:b1,IDUSUARIOALTERACAO=(-98),DTULTIMAALTERACAO=SYSDATE w\
here (IDAREAREGISTRO=:b2 and NRLINHA=:b3)";
                sqlstm.iters = (unsigned int  )1;
                sqlstm.offset = (unsigned int  )158;
                sqlstm.cud = sqlcud0;
                sqlstm.sqlest = (unsigned char  *)&sqlca;
                sqlstm.sqlety = (unsigned short)256;
                sqlstm.occurs = (unsigned int  )0;
                sqlstm.sqhstv[0] = (         void  *)&(varOraLinhaItauCardAdd.idTipoCartaoVivoItauCard);
                sqlstm.sqhstl[0] = (unsigned int  )7;
                sqlstm.sqhsts[0] = (         int  )0;
                sqlstm.sqindv[0] = (         void  *)0;
                sqlstm.sqinds[0] = (         int  )0;
                sqlstm.sqharm[0] = (unsigned int  )0;
                sqlstm.sqadto[0] = (unsigned short )0;
                sqlstm.sqtdso[0] = (unsigned short )0;
                sqlstm.sqhstv[1] = (         void  *)&(varOraLinhaItauCardAdd.nrCpf);
                sqlstm.sqhstl[1] = (unsigned int  )14;
                sqlstm.sqhsts[1] = (         int  )0;
                sqlstm.sqindv[1] = (         void  *)0;
                sqlstm.sqinds[1] = (         int  )0;
                sqlstm.sqharm[1] = (unsigned int  )0;
                sqlstm.sqadto[1] = (unsigned short )0;
                sqlstm.sqtdso[1] = (unsigned short )0;
                sqlstm.sqhstv[2] = (         void  *)&(varOraLinhaItauCardAdd.idAreaRegistro);
                sqlstm.sqhstl[2] = (unsigned int  )5;
                sqlstm.sqhsts[2] = (         int  )0;
                sqlstm.sqindv[2] = (         void  *)0;
                sqlstm.sqinds[2] = (         int  )0;
                sqlstm.sqharm[2] = (unsigned int  )0;
                sqlstm.sqadto[2] = (unsigned short )0;
                sqlstm.sqtdso[2] = (unsigned short )0;
                sqlstm.sqhstv[3] = (         void  *)&(varOraLinhaItauCardAdd.nrLinha);
                sqlstm.sqhstl[3] = (unsigned int  )11;
                sqlstm.sqhsts[3] = (         int  )0;
                sqlstm.sqindv[3] = (         void  *)0;
                sqlstm.sqinds[3] = (         int  )0;
                sqlstm.sqharm[3] = (unsigned int  )0;
                sqlstm.sqadto[3] = (unsigned short )0;
                sqlstm.sqtdso[3] = (unsigned short )0;
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
}



                if ( sqlca.sqlcode )
                {
                    /* EXEC SQL ROLLBACK; */ 

{
                    struct sqlexd sqlstm;
                    sqlstm.sqlvsn = 12;
                    sqlstm.arrsiz = 4;
                    sqlstm.sqladtp = &sqladt;
                    sqlstm.sqltdsp = &sqltds;
                    sqlstm.iters = (unsigned int  )1;
                    sqlstm.offset = (unsigned int  )189;
                    sqlstm.cud = sqlcud0;
                    sqlstm.sqlest = (unsigned char  *)&sqlca;
                    sqlstm.sqlety = (unsigned short)256;
                    sqlstm.occurs = (unsigned int  )0;
                    sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
}


                    strcat(streamBuf,sqlca.sqlerrm.sqlerrmc);
                    strcat(streamBuf," (erro na atualização)\n");
                    ofstream outputFileErr(outputFileNameErr.c_str(), ios::out|ios::binary|ios::app);
                    outputFileErr.write(streamBuf,strlen(streamBuf));
                    outputFileErr.close();
                    itRegErr++;
                    continue;
                }

                /* EXEC SQL COMMIT; */ 

{
                struct sqlexd sqlstm;
                sqlstm.sqlvsn = 12;
                sqlstm.arrsiz = 4;
                sqlstm.sqladtp = &sqladt;
                sqlstm.sqltdsp = &sqltds;
                sqlstm.iters = (unsigned int  )1;
                sqlstm.offset = (unsigned int  )204;
                sqlstm.cud = sqlcud0;
                sqlstm.sqlest = (unsigned char  *)&sqlca;
                sqlstm.sqlety = (unsigned short)256;
                sqlstm.occurs = (unsigned int  )0;
                sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
}


                itRegUpd++;
            }

        }
        else if (memcmp(varOraCodOperacao,"REM",3) == 0 )
        {
            /* EXEC SQL
                DELETE FROM LINHA.LINHAITAUCARD
                WHERE IDAREAREGISTRO = :varOraLinhaItauCardAdd.idAreaRegistro
                AND NRLINHA = :varOraLinhaItauCardAdd.nrLinha; */ 

{
            struct sqlexd sqlstm;
            sqlstm.sqlvsn = 12;
            sqlstm.arrsiz = 4;
            sqlstm.sqladtp = &sqladt;
            sqlstm.sqltdsp = &sqltds;
            sqlstm.stmt = "delete  from LINHA.LINHAITAUCARD  where (IDAREARE\
GISTRO=:b0 and NRLINHA=:b1)";
            sqlstm.iters = (unsigned int  )1;
            sqlstm.offset = (unsigned int  )219;
            sqlstm.cud = sqlcud0;
            sqlstm.sqlest = (unsigned char  *)&sqlca;
            sqlstm.sqlety = (unsigned short)256;
            sqlstm.occurs = (unsigned int  )0;
            sqlstm.sqhstv[0] = (         void  *)&(varOraLinhaItauCardAdd.idAreaRegistro);
            sqlstm.sqhstl[0] = (unsigned int  )5;
            sqlstm.sqhsts[0] = (         int  )0;
            sqlstm.sqindv[0] = (         void  *)0;
            sqlstm.sqinds[0] = (         int  )0;
            sqlstm.sqharm[0] = (unsigned int  )0;
            sqlstm.sqadto[0] = (unsigned short )0;
            sqlstm.sqtdso[0] = (unsigned short )0;
            sqlstm.sqhstv[1] = (         void  *)&(varOraLinhaItauCardAdd.nrLinha);
            sqlstm.sqhstl[1] = (unsigned int  )11;
            sqlstm.sqhsts[1] = (         int  )0;
            sqlstm.sqindv[1] = (         void  *)0;
            sqlstm.sqinds[1] = (         int  )0;
            sqlstm.sqharm[1] = (unsigned int  )0;
            sqlstm.sqadto[1] = (unsigned short )0;
            sqlstm.sqtdso[1] = (unsigned short )0;
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
}



            if ( sqlca.sqlcode == 0 )
            {
                /* EXEC SQL COMMIT; */ 

{
                struct sqlexd sqlstm;
                sqlstm.sqlvsn = 12;
                sqlstm.arrsiz = 4;
                sqlstm.sqladtp = &sqladt;
                sqlstm.sqltdsp = &sqltds;
                sqlstm.iters = (unsigned int  )1;
                sqlstm.offset = (unsigned int  )242;
                sqlstm.cud = sqlcud0;
                sqlstm.sqlest = (unsigned char  *)&sqlca;
                sqlstm.sqlety = (unsigned short)256;
                sqlstm.occurs = (unsigned int  )0;
                sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
}



                itRegRem++;
            }
        }
        else
        {
            strcat(streamBuf,"Operação inválida\n");
            ofstream outputFileErr(outputFileNameErr.c_str(), ios::out|ios::binary|ios::app);
            outputFileErr.write(streamBuf,strlen(streamBuf));
            outputFileErr.close();
            itRegErr++;
            continue;
        }

    } // while ( inputFile.getline(streamBuf,sizeof(streamBuf)) )

    inputFile.close();

#ifndef WIN32
    Process(argv[0], UNLOCK);
#endif // #ifndef WIN32

    DBDisconnect();

    sprintf(szAux,"  Registros inseridos: %d",itRegAdd);oLog.logDebug(szAux);
    sprintf(szAux,"Registros atualizados: %d",itRegUpd);oLog.logDebug(szAux);
    sprintf(szAux,"  Registros removidos: %d",itRegRem);oLog.logDebug(szAux);
    sprintf(szAux,"   Registros com erro: %d",itRegErr);oLog.logDebug(szAux);

    oLog.logDebug("Processamento encerrado com sucesso...");

    return 0; // SUCESSO

    //sqlError:
    //    sprintf(szAux,"ERRO ORACLE [%s]",sqlca.sqlerrm.sqlerrmc);oLog.logDebug(szAux);
    //    EXEC SQL ROLLBACK;
    //    DBDisconnect();
    //    Process(argv[0], UNLOCK);
    //    return -1;
}

/************************************************************************************************************/
void moverArquivo(const char *pszNomeArquivoOrigem,const char *pszNomeArquivoDestino)
{
    char sComando[_MAX_PATH*2+16];

    sprintf(sComando,"/usr/bin/mv -f %s %s", pszNomeArquivoOrigem, pszNomeArquivoDestino);

    oLog.logDebug(" Vai mover o arquivo");
    sprintf(szAux," Arquivo Origem [%s]", pszNomeArquivoOrigem); oLog.logDebug(szAux);
    sprintf(szAux,"Arquivo Destino [%s]", pszNomeArquivoDestino); oLog.logDebug(szAux);
    sprintf(szAux,"        Comando [%s]", sComando); oLog.logDebug(szAux);

    system(sComando);
}

/************************************************************************************************************/
int ObtemParamConf(TParamConf *ptParamConf)
{
    MFile mfConfig;
    SplitLine NewLinha;
    char szLinha[1024 + 1];
    char szDivArq[256];
    char szDivPre[21];

    oLog.logDebug("Obtendo parametros de configuracao...");

    /* Zera variáveis */
    memset(ptParamConf,0, sizeof(TParamConf));
    memset(szLinha,0, sizeof(szLinha));
    memset(szDivArq,0, sizeof(szDivArq));
    memset(szDivPre,0, sizeof(szDivPre));


    /* Define o arquivo de configuração */
    mfConfig.setPath("cartaoVivoItauCard.cfg");

    /* Verificar se conseguiu abrir o arquivo */
    if(!mfConfig.abrir())
    {
        oLog.logError("Erro abrindo arquivo de configuração!");
        return -1;
    }

    /* Capturando usuario, senha, path e instance de banco do arquivo */
    NewLinha.SetDiv('=');
    while(mfConfig.getLine(szLinha) != 0)
    {
        NewLinha.SetLine(szLinha);
        NewLinha.GetBeforeDiv(szDivPre);
        NewLinha.GetAfterDiv(szDivArq);

        /* Verifica qual parâmetro foi encontrado. */
        if (!strcmp(szDivPre, "pwd_db"))
        {
            SAFE_STRNCPY(ptParamConf->szPws, szDivArq);
        }
        else if (!strcmp(szDivPre, "usr_db"))
        {
            SAFE_STRNCPY(ptParamConf->szUsr, szDivArq);
        }
        else if (!strcmp(szDivPre, "inst_db"))
        {
            SAFE_STRNCPY(ptParamConf->szInst, szDivArq);
        }
        else if (!strcmp(szDivPre, "pwd_tux"))
        {
            SAFE_STRNCPY(ptParamConf->szPwsTux, szDivArq);
        }
        else if (!strcmp(szDivPre, "usr_tux"))
        {
            SAFE_STRNCPY(ptParamConf->szUsrTux, szDivArq);
        }
        else if (!strcmp(szDivPre, "pwd_tux_gen"))
        {
            SAFE_STRNCPY(ptParamConf->szPwsTuxGen, szDivArq);
        }
        else if (!strcmp(szDivPre, "clt_tux"))
        {
            SAFE_STRNCPY(ptParamConf->szCltTux, szDivArq);
        }
        else if (!strcmp(szDivPre, "path"))
        {
            SAFE_STRNCPY(ptParamConf->szPathIn, szDivArq);
            
            if ( *(ptParamConf->szPathIn+strlen(ptParamConf->szPathIn)-1) == '/' )
            {
                *(ptParamConf->szPathIn+strlen(ptParamConf->szPathIn)-1) = 0;
            }

            SAFE_STRNCPY(ptParamConf->szPathErr,ptParamConf->szPathIn);
            char *pathErros = "/processados/erros";
            int tamPathErros = strlen(pathErros);

            if ( *(ptParamConf->szPathErr+sizeof(ptParamConf->szPathErr)-tamPathErros-1) == 0 )
            { // cabe?
                strcat(ptParamConf->szPathErr,pathErros);
            }
            else
            {
                fprintf(stderr, "TAMANHO DO PATH MUITO GRANDE (1) !!!\n");
                exit(-2);
            }

            SAFE_STRNCPY(ptParamConf->szPathProcessados,ptParamConf->szPathIn);
            char *pathProcessados = "/processados";
            int tamPathProcessados = strlen(pathProcessados);

            if ( *(ptParamConf->szPathProcessados+sizeof(ptParamConf->szPathProcessados)-tamPathProcessados-1) == 0 )
            { // cabe?
                strcat(ptParamConf->szPathProcessados,pathProcessados);
            }
            else
            {
                fprintf(stderr, "TAMANHO DO PATH MUITO GRANDE (2)!!!\n");
                exit(-2);
            }
        }
        else if (!strcmp(szDivPre, "filename"))
        {
            SAFE_STRNCPY(ptParamConf->szFileNameIn, szDivArq);
        }

        memset(szLinha, 0, sizeof(szLinha));
        memset(szDivPre,0, sizeof(szDivPre));
        memset(szDivArq,0, sizeof(szDivArq));
    }

    mfConfig.fechar();

    sprintf(szAux, "            szUsr='%s'", ptParamConf->szUsr); oLog.logDebug(szAux);
    sprintf(szAux, "           szInst='%s'", ptParamConf->szInst); oLog.logDebug(szAux);
    sprintf(szAux, "         szUsrTux='%s'", ptParamConf->szUsrTux); oLog.logDebug(szAux);
    sprintf(szAux, "         szCltTux='%s'", ptParamConf->szCltTux); oLog.logDebug(szAux);
    sprintf(szAux, "         szPathIn='%s'", ptParamConf->szPathIn); oLog.logDebug(szAux);
    sprintf(szAux, "szPathProcessados='%s'", ptParamConf->szPathProcessados); oLog.logDebug(szAux);
    sprintf(szAux, "        szPathErr='%s'", ptParamConf->szPathErr); oLog.logDebug(szAux);
    sprintf(szAux, "     szFileNameIn='%s'", ptParamConf->szFileNameIn); oLog.logDebug(szAux);

    oLog.logDebug("Parametros de configuracao obtidos com sucesso...");
    return 0;
}

/************************************************************************************************************/
int DBConnect(char *pUsr, char *pPwd, char *pInst)
{
    /* EXEC SQL BEGIN DECLARE SECTION; */ 

        char connString[256];
    /* EXEC SQL END DECLARE SECTION; */ 


    sprintf(connString, "%s/%s@%s", pUsr, pPwd, pInst);

    /* EXEC SQL WHENEVER SQLERROR GOTO errConn; */ 


    oLog.logDebug("Conectando na BD...");

    /* EXEC SQL CONNECT :connString; */ 

{
    struct sqlexd sqlstm;
    sqlstm.sqlvsn = 12;
    sqlstm.arrsiz = 4;
    sqlstm.sqladtp = &sqladt;
    sqlstm.sqltdsp = &sqltds;
    sqlstm.iters = (unsigned int  )10;
    sqlstm.offset = (unsigned int  )257;
    sqlstm.cud = sqlcud0;
    sqlstm.sqlest = (unsigned char  *)&sqlca;
    sqlstm.sqlety = (unsigned short)256;
    sqlstm.occurs = (unsigned int  )0;
    sqlstm.sqhstv[0] = (         void  *)connString;
    sqlstm.sqhstl[0] = (unsigned int  )256;
    sqlstm.sqhsts[0] = (         int  )256;
    sqlstm.sqindv[0] = (         void  *)0;
    sqlstm.sqinds[0] = (         int  )0;
    sqlstm.sqharm[0] = (unsigned int  )0;
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



    oLog.logDebug("Conectou na BD");

    return (sqlca.sqlcode);

errConn:
    oLog.logInformation("<<<DBConnect [ERROR]");
    return -1;
}

/************************************************************************************************************/
void DBDisconnect(void)
{
    /* EXEC SQL WHENEVER SQLERROR GOTO DBDisconnectError; */ 

    /* EXEC SQL COMMIT WORK RELEASE; */ 

{
    struct sqlexd sqlstm;
    sqlstm.sqlvsn = 12;
    sqlstm.arrsiz = 4;
    sqlstm.sqladtp = &sqladt;
    sqlstm.sqltdsp = &sqltds;
    sqlstm.iters = (unsigned int  )1;
    sqlstm.offset = (unsigned int  )288;
    sqlstm.cud = sqlcud0;
    sqlstm.sqlest = (unsigned char  *)&sqlca;
    sqlstm.sqlety = (unsigned short)256;
    sqlstm.occurs = (unsigned int  )0;
    sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
    if (sqlca.sqlcode < 0) goto DBDisconnectError;
}


    oLog.logDebug("Desconectou da BD com sucesso");
    return;

DBDisconnectError:
    sprintf(szAux, "ERRO ORACLE -> sqlcode=%d,sqlerrmc=%.70s",sqlca.sqlcode,sqlca.sqlerrm.sqlerrmc); oLog.logDebug(szAux);
}


/************************************************************************************************************/
#ifndef WIN32
void ArmaSinal(int iSignal)
{
    sprintf(szAux, "Armando tratamento para Signal(%d)", iSignal); oLog.logInformation(szAux);

    if(signal((iSignal), ProcessaSignal) == SIG_ERR)
    {
        fprintf(stderr, "ERRO ARMANDO SINAL!!!\n");
        exit(-1);
    }
}
/************************************************************************************************************/
void ProcessaSignal(int iSig)
{
    oLog.logInformation(">>>ProcessaSignal");
    sprintf(szAux, "iSig(%d)", iSig); oLog.logInformation(szAux);

    /* rearma o mesmo sinal lancado */
    ArmaSinal(iSig);

    if(iSig == SIGTERM)
    {
        oLog.logInformation("Finalizando processamento via sinal....");
        iSignalProcessa=0;
    }

    oLog.logInformation(">>>ProcessaSignal");
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

        /* tempo limite para espera de processamento */
        if(tDiff > (double)0)
            return -1;
    }
    else if(iFlagLock == KILL)
    {
        semctl(semid, 0, IPC_RMID); /* deleta o semaforo */
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
    sb.sem_op = 1; /* free resource */
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
    sb.sem_op = -1;  /* set to allocate resource */
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
    { /* we got it first */
        sb.sem_op = 1;
        sb.sem_flg = 0;
        arg.val = 1;

        for(sb.sem_num = 0; sb.sem_num < nsems; sb.sem_num++)
        {
            /* do a semop() to "free" the semaphores. */
            /* this sets the sem_otime field, as needed below. */
            if (semop(semid, &sb, 1) == -1)
            {
                int e = errno;
                semctl(semid, 0, IPC_RMID); /* clean up */
                errno = e;
                return -1; /* error, check errno */
            }
        }
    }
    else if (errno == EEXIST)
    {
        int ready = 0;

        semid = semget(key, nsems, 0); /* get the id */
        if (semid < 0)
            return semid; /* error, check errno */

        /* wait for other process to initialize the semaphore: */
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
        return semid; /* error, check errno */
    }

    return semid;
}
#endif // #ifndef WIN32
