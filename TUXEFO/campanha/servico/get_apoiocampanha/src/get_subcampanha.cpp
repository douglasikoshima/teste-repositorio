
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
           char  filnam[89];
};
static const struct sqlcxp sqlfpn =
{
    88,
    "/home/druida/wfonseca/TUXEFO/campanha/servico/get_apoiocampanha/src/get_subcampanha.pcpp"
};


static unsigned int sqlctx = 1592956284;


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
   unsigned char  *sqhstv[20];
   unsigned long  sqhstl[20];
            int   sqhsts[20];
            short *sqindv[20];
            int   sqinds[20];
   unsigned long  sqharm[20];
   unsigned long  *sqharc[20];
   unsigned short  sqadto[20];
   unsigned short  sqtdso[20];
} sqlstm = {12,20};

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
"select SUBCAMPANHAH.idsubcampanhahistorico ,SUBCAMPANHAH.dsscriptsubcampanha\
 ,SUBCAMPANHAH.inclientetelefonica ,SUBCAMPANHAH.qtmaximaagenda ,SUBCAMPANHAH.\
dtinicio ,SUBCAMPANHAH.dtinicioout ,SUBCAMPANHAH.dttermino ,SUBCAMPANHAH.dtter\
minoout ,SUBCAMPANHAH.sqversao ,SUBCAMPANHAH.inreincidente ,SUBCAMPANHAH.idtip\
ocampanha ,TIPOCAMPANHA.sgtipocampanha ,TIPOCAMPANHA.nmtipocampanha ,TIPOCAMPA\
NHA.inativo tpcmpinativo ,SUBCAMPANHAH.idsubcampanhafixa ,SUBCAMPANHAF.inativa\
 sbcmpinativo ,USUBCAMPANHAF.indisponibilidade ,CAMPANHA.sgcampanha ,CAMPANHA.\
dscampanha ,CAMPANHA.inativo cmpinativo  from campanha.subcampanhahistorico\
 SUBCAMPANHAH ,campanha.subcampanhafixa SUBCAMPANHAF ,apoio.tipocampanha\
 TIPOCAMPANHA ,apoio.campanha CAMPANHA where (((SUBCAMPANHAF.inativo=:b0 an\
d SUBCAMPANHAF.idsubcampanhafixa=SUBCAMPANHAH.idsubcampanhafixa) and TIPOCAMPA\
NHA.idtipocampanha=SUBCAMPANHAH.idtipocampanha) and CAMPANHA.idcampanha=SUBCAM\
PANHAF.idcampanha) order by SUBCAMPANHAH.DSSCRIPTSUBCAMPANHA            ";

typedef struct { unsigned short len; unsigned char arr[1]; } VARCHAR;
typedef struct { unsigned short len; unsigned char arr[1]; } varchar;

/* cud (compilation unit data) array */
static const short sqlcud0[] =
{12,4130,31,0,0,
5,0,0,1,1006,0,9,78,0,0,1,1,0,1,0,1,3,0,0,
24,0,0,1,0,0,13,83,0,0,20,0,0,1,0,2,3,0,0,2,9,0,0,2,3,0,0,2,3,0,0,2,9,0,0,2,9,
0,0,2,9,0,0,2,9,0,0,2,3,0,0,2,3,0,0,2,3,0,0,2,9,0,0,2,9,0,0,2,3,0,0,2,3,0,0,2,
3,0,0,2,3,0,0,2,9,0,0,2,9,0,0,2,3,0,0,
119,0,0,1,0,0,15,117,0,0,0,0,0,1,0,
};


//
// $Id: get_subcampanha.cpp,v 1.1.2.1 2010/01/13 22:57:17 a5110702 Exp $
//

#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>

#include "orautil.h"
#include "TuxFW10.h"
#include "XMLImpl.h"

extern struct sqlca sqlca;

int get_campanha(DOMNode*dnode,XMLGen*xml)
{
  
  /* EXEC SQL BEGIN DECLARE SECTION; */ 

    int idSubCampanhaHistorico;
    /* VARCHAR dsScriptSubCampanha[255]; */ 
struct { unsigned short len; unsigned char arr[255]; } dsScriptSubCampanha;

    int     inClienteTelefonica;
    int     qtMaximaAgenda;
    /* VARCHAR dtInicio[255]; */ 
struct { unsigned short len; unsigned char arr[255]; } dtInicio;

    /* VARCHAR dtInicioOut[255]; */ 
struct { unsigned short len; unsigned char arr[255]; } dtInicioOut;

    /* VARCHAR dtTermino[255]; */ 
struct { unsigned short len; unsigned char arr[255]; } dtTermino;

    /* VARCHAR dtTerminoOut[255]; */ 
struct { unsigned short len; unsigned char arr[255]; } dtTerminoOut;

    int     sqVersao;
    int     inReincidente;
    int     idTipoCampanha;
    /* VARCHAR sgTipoCampanha[255]; */ 
struct { unsigned short len; unsigned char arr[255]; } sgTipoCampanha;

    /* VARCHAR nmTipoCampanha[255]; */ 
struct { unsigned short len; unsigned char arr[255]; } nmTipoCampanha;

    int     TpCmpinAtivo;
    int     idSubCampanhaFixa;
    int     SbCmpinAtivo;
    int     inDisponibilidade;
    /* VARCHAR sgCampanha[255]; */ 
struct { unsigned short len; unsigned char arr[255]; } sgCampanha;

    /* VARCHAR dsCampanha[255]; */ 
struct { unsigned short len; unsigned char arr[255]; } dsCampanha;

    int     CmpinAtivo;
    int     inAtivo;
  /* EXEC SQL END DECLARE SECTION; */ 

  /* EXEC SQL WHENEVER SQLERROR DO sql_error(NULL); */ 

  /* EXEC SQL WHENEVER NOT FOUND DO break; */ 


  inAtivo = 1;

  /* EXEC SQL DECLARE crsSubCampanha CURSOR FOR
          SELECT      SUBCAMPANHAH.idsubcampanhahistorico,
 		      SUBCAMPANHAH.dsscriptsubcampanha,
		      SUBCAMPANHAH.inclientetelefonica,
		      SUBCAMPANHAH.qtmaximaagenda,
		      SUBCAMPANHAH.dtinicio,
		      SUBCAMPANHAH.dtinicioout,
		      SUBCAMPANHAH.dttermino,
		      SUBCAMPANHAH.dtterminoout,
		      SUBCAMPANHAH.sqversao,
		      SUBCAMPANHAH.inreincidente,
		      SUBCAMPANHAH.idtipocampanha,
		      TIPOCAMPANHA.sgtipocampanha,
		      TIPOCAMPANHA.nmtipocampanha,
		      TIPOCAMPANHA.inativo as tpcmpinativo,
		      SUBCAMPANHAH.idsubcampanhafixa,
		      SUBCAMPANHAF.inativa as sbcmpinativo,
		      USUBCAMPANHAF.indisponibilidade,
		      CAMPANHA.sgcampanha,
		      CAMPANHA.dscampanha,
		      CAMPANHA.inativo as cmpinativo
          FROM        campanha.subcampanhahistorico SUBCAMPANHAH,
	              campanha.subcampanhafixa SUBCAMPANHAF,
		      apoio.tipocampanha TIPOCAMPANHA,
		      apoio.campanha CAMPANHA
          WHERE       SUBCAMPANHAF.inativo = :inAtivo
          AND         SUBCAMPANHAF.idsubcampanhafixa = SUBCAMPANHAH.idsubcampanhafixa
          AND         TIPOCAMPANHA.idtipocampanha = SUBCAMPANHAH.idtipocampanha
          AND         CAMPANHA.idcampanha = SUBCAMPANHAF.idcampanha
          order by    SUBCAMPANHAH.DSSCRIPTSUBCAMPANHA; */ 

  
  /* EXEC SQL OPEN crsSubCampanha; */ 

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
  sqlstm.sqhstv[0] = (unsigned char  *)&inAtivo;
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
  if (sqlca.sqlcode < 0) sql_error(NULL);
}



  xml->addItem("nome","SUBCAMPANHA");
  for(;;) 
  {
    /* EXEC SQL FETCH crsSubCampanha INTO :idSubCampanhaHistorico, :dsScriptSubCampanha, :inClienteTelefonica, :qtMaximaAgenda, :dtInicio, :dtInicioOut, :dtTermino, :dtTerminoOut, :sqVersao, :inReincidente, :idTipoCampanha, :sgTipoCampanha, :nmTipoCampanha, :TpCmpinAtivo, :idSubCampanhaFixa, :SbCmpinAtivo, :inDisponibilidade, :sgCampanha, :dsCampanha, :CmpinAtivo; */ 

{
    struct sqlexd sqlstm;
    sqlstm.sqlvsn = 12;
    sqlstm.arrsiz = 20;
    sqlstm.sqladtp = &sqladt;
    sqlstm.sqltdsp = &sqltds;
    sqlstm.iters = (unsigned int  )1;
    sqlstm.offset = (unsigned int  )24;
    sqlstm.selerr = (unsigned short)1;
    sqlstm.cud = sqlcud0;
    sqlstm.sqlest = (unsigned char  *)&sqlca;
    sqlstm.sqlety = (unsigned short)256;
    sqlstm.occurs = (unsigned int  )0;
    sqlstm.sqfoff = (         int )0;
    sqlstm.sqfmod = (unsigned int )2;
    sqlstm.sqhstv[0] = (unsigned char  *)&idSubCampanhaHistorico;
    sqlstm.sqhstl[0] = (unsigned long )sizeof(int);
    sqlstm.sqhsts[0] = (         int  )0;
    sqlstm.sqindv[0] = (         short *)0;
    sqlstm.sqinds[0] = (         int  )0;
    sqlstm.sqharm[0] = (unsigned long )0;
    sqlstm.sqadto[0] = (unsigned short )0;
    sqlstm.sqtdso[0] = (unsigned short )0;
    sqlstm.sqhstv[1] = (unsigned char  *)&dsScriptSubCampanha;
    sqlstm.sqhstl[1] = (unsigned long )257;
    sqlstm.sqhsts[1] = (         int  )0;
    sqlstm.sqindv[1] = (         short *)0;
    sqlstm.sqinds[1] = (         int  )0;
    sqlstm.sqharm[1] = (unsigned long )0;
    sqlstm.sqadto[1] = (unsigned short )0;
    sqlstm.sqtdso[1] = (unsigned short )0;
    sqlstm.sqhstv[2] = (unsigned char  *)&inClienteTelefonica;
    sqlstm.sqhstl[2] = (unsigned long )sizeof(int);
    sqlstm.sqhsts[2] = (         int  )0;
    sqlstm.sqindv[2] = (         short *)0;
    sqlstm.sqinds[2] = (         int  )0;
    sqlstm.sqharm[2] = (unsigned long )0;
    sqlstm.sqadto[2] = (unsigned short )0;
    sqlstm.sqtdso[2] = (unsigned short )0;
    sqlstm.sqhstv[3] = (unsigned char  *)&qtMaximaAgenda;
    sqlstm.sqhstl[3] = (unsigned long )sizeof(int);
    sqlstm.sqhsts[3] = (         int  )0;
    sqlstm.sqindv[3] = (         short *)0;
    sqlstm.sqinds[3] = (         int  )0;
    sqlstm.sqharm[3] = (unsigned long )0;
    sqlstm.sqadto[3] = (unsigned short )0;
    sqlstm.sqtdso[3] = (unsigned short )0;
    sqlstm.sqhstv[4] = (unsigned char  *)&dtInicio;
    sqlstm.sqhstl[4] = (unsigned long )257;
    sqlstm.sqhsts[4] = (         int  )0;
    sqlstm.sqindv[4] = (         short *)0;
    sqlstm.sqinds[4] = (         int  )0;
    sqlstm.sqharm[4] = (unsigned long )0;
    sqlstm.sqadto[4] = (unsigned short )0;
    sqlstm.sqtdso[4] = (unsigned short )0;
    sqlstm.sqhstv[5] = (unsigned char  *)&dtInicioOut;
    sqlstm.sqhstl[5] = (unsigned long )257;
    sqlstm.sqhsts[5] = (         int  )0;
    sqlstm.sqindv[5] = (         short *)0;
    sqlstm.sqinds[5] = (         int  )0;
    sqlstm.sqharm[5] = (unsigned long )0;
    sqlstm.sqadto[5] = (unsigned short )0;
    sqlstm.sqtdso[5] = (unsigned short )0;
    sqlstm.sqhstv[6] = (unsigned char  *)&dtTermino;
    sqlstm.sqhstl[6] = (unsigned long )257;
    sqlstm.sqhsts[6] = (         int  )0;
    sqlstm.sqindv[6] = (         short *)0;
    sqlstm.sqinds[6] = (         int  )0;
    sqlstm.sqharm[6] = (unsigned long )0;
    sqlstm.sqadto[6] = (unsigned short )0;
    sqlstm.sqtdso[6] = (unsigned short )0;
    sqlstm.sqhstv[7] = (unsigned char  *)&dtTerminoOut;
    sqlstm.sqhstl[7] = (unsigned long )257;
    sqlstm.sqhsts[7] = (         int  )0;
    sqlstm.sqindv[7] = (         short *)0;
    sqlstm.sqinds[7] = (         int  )0;
    sqlstm.sqharm[7] = (unsigned long )0;
    sqlstm.sqadto[7] = (unsigned short )0;
    sqlstm.sqtdso[7] = (unsigned short )0;
    sqlstm.sqhstv[8] = (unsigned char  *)&sqVersao;
    sqlstm.sqhstl[8] = (unsigned long )sizeof(int);
    sqlstm.sqhsts[8] = (         int  )0;
    sqlstm.sqindv[8] = (         short *)0;
    sqlstm.sqinds[8] = (         int  )0;
    sqlstm.sqharm[8] = (unsigned long )0;
    sqlstm.sqadto[8] = (unsigned short )0;
    sqlstm.sqtdso[8] = (unsigned short )0;
    sqlstm.sqhstv[9] = (unsigned char  *)&inReincidente;
    sqlstm.sqhstl[9] = (unsigned long )sizeof(int);
    sqlstm.sqhsts[9] = (         int  )0;
    sqlstm.sqindv[9] = (         short *)0;
    sqlstm.sqinds[9] = (         int  )0;
    sqlstm.sqharm[9] = (unsigned long )0;
    sqlstm.sqadto[9] = (unsigned short )0;
    sqlstm.sqtdso[9] = (unsigned short )0;
    sqlstm.sqhstv[10] = (unsigned char  *)&idTipoCampanha;
    sqlstm.sqhstl[10] = (unsigned long )sizeof(int);
    sqlstm.sqhsts[10] = (         int  )0;
    sqlstm.sqindv[10] = (         short *)0;
    sqlstm.sqinds[10] = (         int  )0;
    sqlstm.sqharm[10] = (unsigned long )0;
    sqlstm.sqadto[10] = (unsigned short )0;
    sqlstm.sqtdso[10] = (unsigned short )0;
    sqlstm.sqhstv[11] = (unsigned char  *)&sgTipoCampanha;
    sqlstm.sqhstl[11] = (unsigned long )257;
    sqlstm.sqhsts[11] = (         int  )0;
    sqlstm.sqindv[11] = (         short *)0;
    sqlstm.sqinds[11] = (         int  )0;
    sqlstm.sqharm[11] = (unsigned long )0;
    sqlstm.sqadto[11] = (unsigned short )0;
    sqlstm.sqtdso[11] = (unsigned short )0;
    sqlstm.sqhstv[12] = (unsigned char  *)&nmTipoCampanha;
    sqlstm.sqhstl[12] = (unsigned long )257;
    sqlstm.sqhsts[12] = (         int  )0;
    sqlstm.sqindv[12] = (         short *)0;
    sqlstm.sqinds[12] = (         int  )0;
    sqlstm.sqharm[12] = (unsigned long )0;
    sqlstm.sqadto[12] = (unsigned short )0;
    sqlstm.sqtdso[12] = (unsigned short )0;
    sqlstm.sqhstv[13] = (unsigned char  *)&TpCmpinAtivo;
    sqlstm.sqhstl[13] = (unsigned long )sizeof(int);
    sqlstm.sqhsts[13] = (         int  )0;
    sqlstm.sqindv[13] = (         short *)0;
    sqlstm.sqinds[13] = (         int  )0;
    sqlstm.sqharm[13] = (unsigned long )0;
    sqlstm.sqadto[13] = (unsigned short )0;
    sqlstm.sqtdso[13] = (unsigned short )0;
    sqlstm.sqhstv[14] = (unsigned char  *)&idSubCampanhaFixa;
    sqlstm.sqhstl[14] = (unsigned long )sizeof(int);
    sqlstm.sqhsts[14] = (         int  )0;
    sqlstm.sqindv[14] = (         short *)0;
    sqlstm.sqinds[14] = (         int  )0;
    sqlstm.sqharm[14] = (unsigned long )0;
    sqlstm.sqadto[14] = (unsigned short )0;
    sqlstm.sqtdso[14] = (unsigned short )0;
    sqlstm.sqhstv[15] = (unsigned char  *)&SbCmpinAtivo;
    sqlstm.sqhstl[15] = (unsigned long )sizeof(int);
    sqlstm.sqhsts[15] = (         int  )0;
    sqlstm.sqindv[15] = (         short *)0;
    sqlstm.sqinds[15] = (         int  )0;
    sqlstm.sqharm[15] = (unsigned long )0;
    sqlstm.sqadto[15] = (unsigned short )0;
    sqlstm.sqtdso[15] = (unsigned short )0;
    sqlstm.sqhstv[16] = (unsigned char  *)&inDisponibilidade;
    sqlstm.sqhstl[16] = (unsigned long )sizeof(int);
    sqlstm.sqhsts[16] = (         int  )0;
    sqlstm.sqindv[16] = (         short *)0;
    sqlstm.sqinds[16] = (         int  )0;
    sqlstm.sqharm[16] = (unsigned long )0;
    sqlstm.sqadto[16] = (unsigned short )0;
    sqlstm.sqtdso[16] = (unsigned short )0;
    sqlstm.sqhstv[17] = (unsigned char  *)&sgCampanha;
    sqlstm.sqhstl[17] = (unsigned long )257;
    sqlstm.sqhsts[17] = (         int  )0;
    sqlstm.sqindv[17] = (         short *)0;
    sqlstm.sqinds[17] = (         int  )0;
    sqlstm.sqharm[17] = (unsigned long )0;
    sqlstm.sqadto[17] = (unsigned short )0;
    sqlstm.sqtdso[17] = (unsigned short )0;
    sqlstm.sqhstv[18] = (unsigned char  *)&dsCampanha;
    sqlstm.sqhstl[18] = (unsigned long )257;
    sqlstm.sqhsts[18] = (         int  )0;
    sqlstm.sqindv[18] = (         short *)0;
    sqlstm.sqinds[18] = (         int  )0;
    sqlstm.sqharm[18] = (unsigned long )0;
    sqlstm.sqadto[18] = (unsigned short )0;
    sqlstm.sqtdso[18] = (unsigned short )0;
    sqlstm.sqhstv[19] = (unsigned char  *)&CmpinAtivo;
    sqlstm.sqhstl[19] = (unsigned long )sizeof(int);
    sqlstm.sqhsts[19] = (         int  )0;
    sqlstm.sqindv[19] = (         short *)0;
    sqlstm.sqinds[19] = (         int  )0;
    sqlstm.sqharm[19] = (unsigned long )0;
    sqlstm.sqadto[19] = (unsigned short )0;
    sqlstm.sqtdso[19] = (unsigned short )0;
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
    if (sqlca.sqlcode == 1403) break;
    if (sqlca.sqlcode < 0) sql_error(NULL);
}



    endOraStr(sgCampanha);
    endOraStr(dsCampanha);    
    endOraStr(sgTipoCampanha);
    endOraStr(nmTipoCampanha);    
    endOraStr(dtInicio);
    endOraStr(dtInicioOut);
    endOraStr(dtTermino);
    endOraStr(dtTerminoOut);    
    endOraStr(dsScriptSubCampanha);  
    xml->createTag("tns:ApoioVO");
    xml->addItem("codigo",idSubCampanhaHistorico);
    xml->addItem("descricao",(char *)dsScriptSubCampanha.arr);
    xml->addItem("inclientetelefonica",inClienteTelefonica);
    xml->addItem("qtmaximaagenda",qtMaximaAgenda);
    xml->addItem("sqversao",sqVersao);
    xml->addItem("inreincidente",inReincidente);
    xml->addItem("idtipocampanha",idTipoCampanha);
    xml->addItem("sgtipocampanha",(char *)sgTipoCampanha.arr);
    xml->addItem("nmtipocampanha",(char *)nmTipoCampanha.arr);
    xml->addItem("tpcmpinativo",TpCmpinAtivo);
    xml->addItem("idsubcampanhafixa",idSubCampanhaFixa);
    xml->addItem("sbcmpinativo",SbCmpinAtivo);
    xml->addItem("indisponibilidade",inDisponibilidade);
    xml->addItem("sgcampanha",(char *)sgCampanha.arr);
    xml->addItem("dscampanha",(char *)dsCampanha.arr);
    xml->addItem("cmpinativo",CmpinAtivo);
    xml->addItem("dtinicio",(char *)dtInicio.arr);
    xml->addItem("dtinicioout",(char *)dtInicioOut.arr);
    xml->addItem("dttermino",(char *)dtTermino.arr);
    xml->addItem("dtterminoout",(char *)dtTerminoOut.arr);
    xml->closeTag();
  }  
  /* EXEC SQL CLOSE crsSubCampanha; */ 

{
  struct sqlexd sqlstm;
  sqlstm.sqlvsn = 12;
  sqlstm.arrsiz = 20;
  sqlstm.sqladtp = &sqladt;
  sqlstm.sqltdsp = &sqltds;
  sqlstm.iters = (unsigned int  )1;
  sqlstm.offset = (unsigned int  )119;
  sqlstm.cud = sqlcud0;
  sqlstm.sqlest = (unsigned char  *)&sqlca;
  sqlstm.sqlety = (unsigned short)256;
  sqlstm.occurs = (unsigned int  )0;
  sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
  if (sqlca.sqlcode < 0) sql_error(NULL);
}


  
  return 1;
}
