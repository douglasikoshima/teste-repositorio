
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
           char  filnam[82];
};
static const struct sqlcxp sqlfpn =
{
    81,
    "/home/wfonseca/TUXEFO/campanha/negocio/CampanhaCmm/src/CCampanhaQuestionario.pcpp"
};


static unsigned int sqlctx = 1551692420;


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

 static const char *sq0005 = 
"select IDCAMPANHAQUESTIONARIO ,IDPERGUNTA ,IDCANALCAMPANHA ,IDUSUARIOALTERAC\
AO ,DTULTIMAALTERACAO  from CAMPANHA.CAMPANHAQUESTIONARIO where IDCAMPANHAQ\
UESTIONARIO=:b0           ";

 static const char *sq0006 = 
"select IDCAMPANHAQUESTIONARIO ,IDPERGUNTA ,IDCANALCAMPANHA ,IDUSUARIOALTERAC\
AO ,DTULTIMAALTERACAO  from CAMPANHA.CAMPANHAQUESTIONARIO            ";

typedef struct { unsigned short len; unsigned char arr[1]; } VARCHAR;
typedef struct { unsigned short len; unsigned char arr[1]; } varchar;

/* cud (compilation unit data) array */
static const short sqlcud0[] =
{12,4130,31,0,0,
5,0,0,1,68,0,4,34,0,0,1,0,0,1,0,2,9,0,0,
24,0,0,2,166,0,3,39,0,0,4,4,0,1,0,1,9,0,0,1,97,0,0,1,97,0,0,1,97,0,0,
55,0,0,3,161,0,5,82,0,0,4,4,0,1,0,1,97,0,0,1,97,0,0,1,97,0,0,1,97,0,0,
86,0,0,4,126,0,5,116,0,0,2,2,0,1,0,1,97,0,0,1,97,0,0,
109,0,0,5,180,0,9,172,0,0,1,1,0,1,0,1,97,0,0,
128,0,0,5,0,0,13,181,0,0,5,0,0,1,0,2,9,0,0,2,9,0,0,2,9,0,0,2,9,0,0,2,9,0,0,
163,0,0,5,0,0,15,192,0,0,0,0,0,1,0,
178,0,0,6,148,0,9,239,0,0,0,0,0,1,0,
193,0,0,6,0,0,13,248,0,0,5,0,0,1,0,2,9,0,0,2,9,0,0,2,9,0,0,2,9,0,0,2,9,0,0,
228,0,0,6,0,0,15,259,0,0,0,0,0,1,0,
};


#undef SQLCA
#define SQLCA_NONE
#include<sqlca.h>
#include<sqlda.h>

#include <CCampanhaQuestionario.h>

CCampanhaQuestionario::CCampanhaQuestionario()
{
}

CCampanhaQuestionario::~CCampanhaQuestionario()
{
}

int CCampanhaQuestionario::Insert( char* cidCanalCampanha, 
							       char* cidPergunta,
							       char* cidUser )
{
	struct sqlca sqlca;
	/* EXEC SQL BEGIN DECLARE SECTION; */ 

		/* VARCHAR cAuxidCampanhaQuestionario[21+1]; */ 
struct { unsigned short len; unsigned char arr[22]; } cAuxidCampanhaQuestionario;

		char* cAuxidCanalCampanha = cidCanalCampanha;
		char* cAuxidPergunta = cidPergunta;
		char* cAuxidUser = cidUser;
	/* EXEC SQL END DECLARE SECTION; */ 

	
	ZeraCampanhaQuestionario();
	
	/* EXEC SQL WHENEVER NOT FOUND CONTINUE; */ 

	/* EXEC SQL WHENEVER SQLERROR GOTO GotoInsert; */ 

	sqlca.sqlcode=0;
	
	/* EXEC SQL
		SELECT CAMPANHA.CAMPANHAQUESTIONARIOSQ.NEXTVAL
		  INTO :cAuxidCampanhaQuestionario
		  FROM DUAL; */ 

{
 struct sqlexd sqlstm;
 sqlstm.sqlvsn = 12;
 sqlstm.arrsiz = 1;
 sqlstm.sqladtp = &sqladt;
 sqlstm.sqltdsp = &sqltds;
 sqlstm.stmt = "select CAMPANHA.CAMPANHAQUESTIONARIOSQ.nextval  into :b0  fr\
om DUAL ";
 sqlstm.iters = (unsigned int  )1;
 sqlstm.offset = (unsigned int  )5;
 sqlstm.selerr = (unsigned short)1;
 sqlstm.cud = sqlcud0;
 sqlstm.sqlest = (unsigned char  *)&sqlca;
 sqlstm.sqlety = (unsigned short)256;
 sqlstm.occurs = (unsigned int  )0;
 sqlstm.sqhstv[0] = (unsigned char  *)&cAuxidCampanhaQuestionario;
 sqlstm.sqhstl[0] = (unsigned long )24;
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
 if (sqlca.sqlcode < 0) goto GotoInsert;
}


	       
	/* EXEC SQL 
		INSERT INTO CAMPANHA.CAMPANHAQUESTIONARIO
		  (IDCAMPANHAQUESTIONARIO, 
		   IDCANALCAMPANHA, 
		   IDPERGUNTA,
		   IDUSUARIOALTERACAO,
		   DTULTIMAALTERACAO )
		VALUES
		  (:cAuxidCampanhaQuestionario, 
		   :cAuxidCanalCampanha, 
		   :cAuxidPergunta,
		   :cAuxidUser,
		   SYSDATE ); */ 

{
 struct sqlexd sqlstm;
 sqlstm.sqlvsn = 12;
 sqlstm.arrsiz = 4;
 sqlstm.sqladtp = &sqladt;
 sqlstm.sqltdsp = &sqltds;
 sqlstm.stmt = "insert into CAMPANHA.CAMPANHAQUESTIONARIO (IDCAMPANHAQUES\
TIONARIO,IDCANALCAMPANHA,IDPERGUNTA,IDUSUARIOALTERACAO,DTULTIMAALTERACAO) valu\
es (:b0,:b1,:b2,:b3,SYSDATE)";
 sqlstm.iters = (unsigned int  )1;
 sqlstm.offset = (unsigned int  )24;
 sqlstm.cud = sqlcud0;
 sqlstm.sqlest = (unsigned char  *)&sqlca;
 sqlstm.sqlety = (unsigned short)256;
 sqlstm.occurs = (unsigned int  )0;
 sqlstm.sqhstv[0] = (unsigned char  *)&cAuxidCampanhaQuestionario;
 sqlstm.sqhstl[0] = (unsigned long )24;
 sqlstm.sqhsts[0] = (         int  )0;
 sqlstm.sqindv[0] = (         short *)0;
 sqlstm.sqinds[0] = (         int  )0;
 sqlstm.sqharm[0] = (unsigned long )0;
 sqlstm.sqadto[0] = (unsigned short )0;
 sqlstm.sqtdso[0] = (unsigned short )0;
 sqlstm.sqhstv[1] = (unsigned char  *)cAuxidCanalCampanha;
 sqlstm.sqhstl[1] = (unsigned long )0;
 sqlstm.sqhsts[1] = (         int  )0;
 sqlstm.sqindv[1] = (         short *)0;
 sqlstm.sqinds[1] = (         int  )0;
 sqlstm.sqharm[1] = (unsigned long )0;
 sqlstm.sqadto[1] = (unsigned short )0;
 sqlstm.sqtdso[1] = (unsigned short )0;
 sqlstm.sqhstv[2] = (unsigned char  *)cAuxidPergunta;
 sqlstm.sqhstl[2] = (unsigned long )0;
 sqlstm.sqhsts[2] = (         int  )0;
 sqlstm.sqindv[2] = (         short *)0;
 sqlstm.sqinds[2] = (         int  )0;
 sqlstm.sqharm[2] = (unsigned long )0;
 sqlstm.sqadto[2] = (unsigned short )0;
 sqlstm.sqtdso[2] = (unsigned short )0;
 sqlstm.sqhstv[3] = (unsigned char  *)cAuxidUser;
 sqlstm.sqhstl[3] = (unsigned long )0;
 sqlstm.sqhsts[3] = (         int  )0;
 sqlstm.sqindv[3] = (         short *)0;
 sqlstm.sqinds[3] = (         int  )0;
 sqlstm.sqharm[3] = (unsigned long )0;
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
 if (sqlca.sqlcode < 0) goto GotoInsert;
}

  
	
	if( sqlca.sqlcode == 0 )
		return 1;
	else
		return 0;

GotoInsert:
	throw TuxBasicOraException(sqlca.sqlcode);
	
}

int CCampanhaQuestionario::Update( char* cidCampanhaQuestionario, 
							       char* cidCanalCampanha, 
							       char* cidPergunta,
							       char* cidUser )
{
	struct sqlca sqlca;
	/* EXEC SQL BEGIN DECLARE SECTION; */ 

		char* cAuxidCampanhaQuestionario = cidCampanhaQuestionario;
		char* cAuxidCanalCampanha = cidCanalCampanha;
		char* cAuxidPergunta = cidPergunta;
		char* cAuxidUser = cidUser;
	/* EXEC SQL END DECLARE SECTION; */ 


	ZeraCampanhaQuestionario();
	
	/* EXEC SQL WHENEVER NOT FOUND CONTINUE; */ 

	/* EXEC SQL WHENEVER SQLERROR GOTO GotoUpdate; */ 

	sqlca.sqlcode=0;
	
	/* EXEC SQL 
		UPDATE CAMPANHA.CAMPANHAQUESTIONARIO
		   SET IDCANALCAMPANHA = :cAuxidCanalCampanha,
		       IDPERGUNTA = :cAuxidPergunta,
		       IDUSUARIOALTERACAO = :cAuxidUser,
		       DTULTIMAALTERACAO = SYSDATE
		 WHERE IDCAMPANHAQUESTIONARIO = :cAuxidCampanhaQuestionario; */ 

{
 struct sqlexd sqlstm;
 sqlstm.sqlvsn = 12;
 sqlstm.arrsiz = 4;
 sqlstm.sqladtp = &sqladt;
 sqlstm.sqltdsp = &sqltds;
 sqlstm.stmt = "update CAMPANHA.CAMPANHAQUESTIONARIO  set IDCANALCAMPANHA\
=:b0,IDPERGUNTA=:b1,IDUSUARIOALTERACAO=:b2,DTULTIMAALTERACAO=SYSDATE where IDC\
AMPANHAQUESTIONARIO=:b3";
 sqlstm.iters = (unsigned int  )1;
 sqlstm.offset = (unsigned int  )55;
 sqlstm.cud = sqlcud0;
 sqlstm.sqlest = (unsigned char  *)&sqlca;
 sqlstm.sqlety = (unsigned short)256;
 sqlstm.occurs = (unsigned int  )0;
 sqlstm.sqhstv[0] = (unsigned char  *)cAuxidCanalCampanha;
 sqlstm.sqhstl[0] = (unsigned long )0;
 sqlstm.sqhsts[0] = (         int  )0;
 sqlstm.sqindv[0] = (         short *)0;
 sqlstm.sqinds[0] = (         int  )0;
 sqlstm.sqharm[0] = (unsigned long )0;
 sqlstm.sqadto[0] = (unsigned short )0;
 sqlstm.sqtdso[0] = (unsigned short )0;
 sqlstm.sqhstv[1] = (unsigned char  *)cAuxidPergunta;
 sqlstm.sqhstl[1] = (unsigned long )0;
 sqlstm.sqhsts[1] = (         int  )0;
 sqlstm.sqindv[1] = (         short *)0;
 sqlstm.sqinds[1] = (         int  )0;
 sqlstm.sqharm[1] = (unsigned long )0;
 sqlstm.sqadto[1] = (unsigned short )0;
 sqlstm.sqtdso[1] = (unsigned short )0;
 sqlstm.sqhstv[2] = (unsigned char  *)cAuxidUser;
 sqlstm.sqhstl[2] = (unsigned long )0;
 sqlstm.sqhsts[2] = (         int  )0;
 sqlstm.sqindv[2] = (         short *)0;
 sqlstm.sqinds[2] = (         int  )0;
 sqlstm.sqharm[2] = (unsigned long )0;
 sqlstm.sqadto[2] = (unsigned short )0;
 sqlstm.sqtdso[2] = (unsigned short )0;
 sqlstm.sqhstv[3] = (unsigned char  *)cAuxidCampanhaQuestionario;
 sqlstm.sqhstl[3] = (unsigned long )0;
 sqlstm.sqhsts[3] = (         int  )0;
 sqlstm.sqindv[3] = (         short *)0;
 sqlstm.sqinds[3] = (         int  )0;
 sqlstm.sqharm[3] = (unsigned long )0;
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
 if (sqlca.sqlcode < 0) goto GotoUpdate;
}

   
	
	if( sqlca.sqlcode == 0 )
		return 1;
	else
		return 0;

GotoUpdate:
	throw TuxBasicOraException(sqlca.sqlcode);
	
}

int CCampanhaQuestionario::Remove( char* cidCampanhaQuestionario,
							       char* cidUser )
{
	struct sqlca sqlca;
	/* EXEC SQL BEGIN DECLARE SECTION; */ 

		char* cAuxidCampanhaQuestionario = cidCampanhaQuestionario;
		char* cAuxidUser = cidUser;
	/* EXEC SQL END DECLARE SECTION; */ 


	ZeraCampanhaQuestionario();
	
	/* EXEC SQL WHENEVER NOT FOUND CONTINUE; */ 

	/* EXEC SQL WHENEVER SQLERROR GOTO GotoRemove; */ 

	sqlca.sqlcode=0;
	
//Coloque seu codigo de delecao aqui abaixo
	/* EXEC SQL 
		UPDATE CAMPANHA.CAMPANHAQUESTIONARIO
		   SET IDUSUARIOALTERACAO = :cAuxidUser,
		       DTULTIMAALTERACAO = SYSDATE
		 WHERE IDCAMPANHAQUESTIONARIO = :cAuxidCampanhaQuestionario; */ 

{
 struct sqlexd sqlstm;
 sqlstm.sqlvsn = 12;
 sqlstm.arrsiz = 4;
 sqlstm.sqladtp = &sqladt;
 sqlstm.sqltdsp = &sqltds;
 sqlstm.stmt = "update CAMPANHA.CAMPANHAQUESTIONARIO  set IDUSUARIOALTERA\
CAO=:b0,DTULTIMAALTERACAO=SYSDATE where IDCAMPANHAQUESTIONARIO=:b1";
 sqlstm.iters = (unsigned int  )1;
 sqlstm.offset = (unsigned int  )86;
 sqlstm.cud = sqlcud0;
 sqlstm.sqlest = (unsigned char  *)&sqlca;
 sqlstm.sqlety = (unsigned short)256;
 sqlstm.occurs = (unsigned int  )0;
 sqlstm.sqhstv[0] = (unsigned char  *)cAuxidUser;
 sqlstm.sqhstl[0] = (unsigned long )0;
 sqlstm.sqhsts[0] = (         int  )0;
 sqlstm.sqindv[0] = (         short *)0;
 sqlstm.sqinds[0] = (         int  )0;
 sqlstm.sqharm[0] = (unsigned long )0;
 sqlstm.sqadto[0] = (unsigned short )0;
 sqlstm.sqtdso[0] = (unsigned short )0;
 sqlstm.sqhstv[1] = (unsigned char  *)cAuxidCampanhaQuestionario;
 sqlstm.sqhstl[1] = (unsigned long )0;
 sqlstm.sqhsts[1] = (         int  )0;
 sqlstm.sqindv[1] = (         short *)0;
 sqlstm.sqinds[1] = (         int  )0;
 sqlstm.sqharm[1] = (unsigned long )0;
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
 if (sqlca.sqlcode < 0) goto GotoRemove;
}


//Coloque seu codigo de delecao aqui acima
	
	if( sqlca.sqlcode == 0 )
		return 1;
	else
		return 0;

GotoRemove:
	throw TuxBasicOraException(sqlca.sqlcode);
	
}

int CCampanhaQuestionario::ListId( char* cid )
{
	int    iCont = 0;
	struct sqlca sqlca;
	/* EXEC SQL BEGIN DECLARE SECTION; */ 

		char* cidOra = cid;
		struct
		{
			/* VARCHAR stidCampanhaQuestionario[21+1]; */ 
struct { unsigned short len; unsigned char arr[22]; } stidCampanhaQuestionario;

			/* VARCHAR stidCanalCampanha[21+1]; */ 
struct { unsigned short len; unsigned char arr[22]; } stidCanalCampanha;

			/* VARCHAR stidPergunta[21+1]; */ 
struct { unsigned short len; unsigned char arr[22]; } stidPergunta;

			/* VARCHAR stidUser[21+1]; */ 
struct { unsigned short len; unsigned char arr[22]; } stidUser;

			/* VARCHAR stdtUltimaAlteracao[21+1]; */ 
struct { unsigned short len; unsigned char arr[22]; } stdtUltimaAlteracao;

		} stCampanhaQuestionarioRegistro;
		struct
		{
			short iidCampanhaQuestionario;
			short iidCanalCampanha;
			short iidPergunta;
			short iidUser;
			short idtUltimaAlteracao;
		} stCampanhaQuestionarioIndicator;
	/* EXEC SQL END DECLARE SECTION; */ 


	ZeraCampanhaQuestionario();

	/* EXEC SQL WHENEVER NOT FOUND DO break; */ 

	/* EXEC SQL WHENEVER SQLERROR GOTO GotoListId; */ 

	sqlca.sqlcode=0;

   	/* EXEC SQL DECLARE CursorCampanhaQuestionarioId CURSOR FOR
		SELECT IDCAMPANHAQUESTIONARIO, 
		       IDPERGUNTA, 
		       IDCANALCAMPANHA,
		       IDUSUARIOALTERACAO,
		       DTULTIMAALTERACAO
		  FROM CAMPANHA.CAMPANHAQUESTIONARIO
		 WHERE IDCAMPANHAQUESTIONARIO = :cidOra; */ 


	/* EXEC SQL OPEN CursorCampanhaQuestionarioId; */ 

{
 struct sqlexd sqlstm;
 sqlstm.sqlvsn = 12;
 sqlstm.arrsiz = 4;
 sqlstm.sqladtp = &sqladt;
 sqlstm.sqltdsp = &sqltds;
 sqlstm.stmt = sq0005;
 sqlstm.iters = (unsigned int  )1;
 sqlstm.offset = (unsigned int  )109;
 sqlstm.selerr = (unsigned short)1;
 sqlstm.cud = sqlcud0;
 sqlstm.sqlest = (unsigned char  *)&sqlca;
 sqlstm.sqlety = (unsigned short)256;
 sqlstm.occurs = (unsigned int  )0;
 sqlstm.sqcmod = (unsigned int )0;
 sqlstm.sqhstv[0] = (unsigned char  *)cidOra;
 sqlstm.sqhstl[0] = (unsigned long )0;
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
 if (sqlca.sqlcode < 0) goto GotoListId;
}



	if(sqlca.sqlcode)
		return 0;
	else
	{
		for(;;)
		{
			memset( &stCampanhaQuestionarioRegistro, 0, sizeof(stCampanhaQuestionarioRegistro) );
			/* EXEC SQL FETCH CursorCampanhaQuestionarioId INTO :stCampanhaQuestionarioRegistro:stCampanhaQuestionarioIndicator; */ 

{
   struct sqlexd sqlstm;
   sqlstm.sqlvsn = 12;
   sqlstm.arrsiz = 5;
   sqlstm.sqladtp = &sqladt;
   sqlstm.sqltdsp = &sqltds;
   sqlstm.iters = (unsigned int  )1;
   sqlstm.offset = (unsigned int  )128;
   sqlstm.selerr = (unsigned short)1;
   sqlstm.cud = sqlcud0;
   sqlstm.sqlest = (unsigned char  *)&sqlca;
   sqlstm.sqlety = (unsigned short)256;
   sqlstm.occurs = (unsigned int  )0;
   sqlstm.sqfoff = (         int )0;
   sqlstm.sqfmod = (unsigned int )2;
   sqlstm.sqhstv[0] = (unsigned char  *)&stCampanhaQuestionarioRegistro.stidCampanhaQuestionario;
   sqlstm.sqhstl[0] = (unsigned long )24;
   sqlstm.sqhsts[0] = (         int  )0;
   sqlstm.sqindv[0] = (         short *)&stCampanhaQuestionarioIndicator.iidCampanhaQuestionario;
   sqlstm.sqinds[0] = (         int  )0;
   sqlstm.sqharm[0] = (unsigned long )0;
   sqlstm.sqadto[0] = (unsigned short )0;
   sqlstm.sqtdso[0] = (unsigned short )0;
   sqlstm.sqhstv[1] = (unsigned char  *)&stCampanhaQuestionarioRegistro.stidCanalCampanha;
   sqlstm.sqhstl[1] = (unsigned long )24;
   sqlstm.sqhsts[1] = (         int  )0;
   sqlstm.sqindv[1] = (         short *)&stCampanhaQuestionarioIndicator.iidCanalCampanha;
   sqlstm.sqinds[1] = (         int  )0;
   sqlstm.sqharm[1] = (unsigned long )0;
   sqlstm.sqadto[1] = (unsigned short )0;
   sqlstm.sqtdso[1] = (unsigned short )0;
   sqlstm.sqhstv[2] = (unsigned char  *)&stCampanhaQuestionarioRegistro.stidPergunta;
   sqlstm.sqhstl[2] = (unsigned long )24;
   sqlstm.sqhsts[2] = (         int  )0;
   sqlstm.sqindv[2] = (         short *)&stCampanhaQuestionarioIndicator.iidPergunta;
   sqlstm.sqinds[2] = (         int  )0;
   sqlstm.sqharm[2] = (unsigned long )0;
   sqlstm.sqadto[2] = (unsigned short )0;
   sqlstm.sqtdso[2] = (unsigned short )0;
   sqlstm.sqhstv[3] = (unsigned char  *)&stCampanhaQuestionarioRegistro.stidUser;
   sqlstm.sqhstl[3] = (unsigned long )24;
   sqlstm.sqhsts[3] = (         int  )0;
   sqlstm.sqindv[3] = (         short *)&stCampanhaQuestionarioIndicator.iidUser;
   sqlstm.sqinds[3] = (         int  )0;
   sqlstm.sqharm[3] = (unsigned long )0;
   sqlstm.sqadto[3] = (unsigned short )0;
   sqlstm.sqtdso[3] = (unsigned short )0;
   sqlstm.sqhstv[4] = (unsigned char  *)&stCampanhaQuestionarioRegistro.stdtUltimaAlteracao;
   sqlstm.sqhstl[4] = (unsigned long )24;
   sqlstm.sqhsts[4] = (         int  )0;
   sqlstm.sqindv[4] = (         short *)&stCampanhaQuestionarioIndicator.idtUltimaAlteracao;
   sqlstm.sqinds[4] = (         int  )0;
   sqlstm.sqharm[4] = (unsigned long )0;
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
   if (sqlca.sqlcode == 1403) break;
   if (sqlca.sqlcode < 0) goto GotoListId;
}



			Add( (char*)stCampanhaQuestionarioRegistro.stidCampanhaQuestionario.arr,
			     (char*)stCampanhaQuestionarioRegistro.stidCanalCampanha.arr,
			     (char*)stCampanhaQuestionarioRegistro.stidPergunta.arr,
			     (char*)stCampanhaQuestionarioRegistro.stidUser.arr,
			     (char*)stCampanhaQuestionarioRegistro.stdtUltimaAlteracao.arr );

			iCont++;

		}
		/* EXEC SQL CLOSE CursorCampanhaQuestionarioId; */ 

{
  struct sqlexd sqlstm;
  sqlstm.sqlvsn = 12;
  sqlstm.arrsiz = 5;
  sqlstm.sqladtp = &sqladt;
  sqlstm.sqltdsp = &sqltds;
  sqlstm.iters = (unsigned int  )1;
  sqlstm.offset = (unsigned int  )163;
  sqlstm.cud = sqlcud0;
  sqlstm.sqlest = (unsigned char  *)&sqlca;
  sqlstm.sqlety = (unsigned short)256;
  sqlstm.occurs = (unsigned int  )0;
  sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
  if (sqlca.sqlcode < 0) goto GotoListId;
}


	}

	return iCont;

GotoListId:
	throw TuxBasicOraException(sqlca.sqlcode);

}

int CCampanhaQuestionario::ListAll( void )
{
	int    iCont = 0;
	struct sqlca sqlca;
	/* EXEC SQL BEGIN DECLARE SECTION; */ 

		struct
		{
			/* VARCHAR stidCampanhaQuestionario[21+1]; */ 
struct { unsigned short len; unsigned char arr[22]; } stidCampanhaQuestionario;

			/* VARCHAR stidCanalCampanha[21+1]; */ 
struct { unsigned short len; unsigned char arr[22]; } stidCanalCampanha;

			/* VARCHAR stidPergunta[21+1]; */ 
struct { unsigned short len; unsigned char arr[22]; } stidPergunta;

			/* VARCHAR stidUser[21+1]; */ 
struct { unsigned short len; unsigned char arr[22]; } stidUser;

			/* VARCHAR stdtUltimaAlteracao[21+1]; */ 
struct { unsigned short len; unsigned char arr[22]; } stdtUltimaAlteracao;

		} stCampanhaQuestionarioRegistro;
		struct
		{
			short iidCampanhaQuestionario;
			short iidCanalCampanha;
			short iidPergunta;
			short iidUser;
			short idtUltimaAlteracao;
		} stCampanhaQuestionarioIndicator;
	/* EXEC SQL END DECLARE SECTION; */ 


	ZeraCampanhaQuestionario();

	/* EXEC SQL WHENEVER NOT FOUND DO break; */ 

	/* EXEC SQL WHENEVER SQLERROR GOTO GotoListAll; */ 

	sqlca.sqlcode=0;

   	/* EXEC SQL DECLARE CursorCampanhaQuestionarioAll CURSOR FOR
		SELECT IDCAMPANHAQUESTIONARIO, 
		       IDPERGUNTA, 
		       IDCANALCAMPANHA,
		       IDUSUARIOALTERACAO,
		       DTULTIMAALTERACAO
		  FROM CAMPANHA.CAMPANHAQUESTIONARIO; */ 


	/* EXEC SQL OPEN CursorCampanhaQuestionarioAll; */ 

{
 struct sqlexd sqlstm;
 sqlstm.sqlvsn = 12;
 sqlstm.arrsiz = 5;
 sqlstm.sqladtp = &sqladt;
 sqlstm.sqltdsp = &sqltds;
 sqlstm.stmt = sq0006;
 sqlstm.iters = (unsigned int  )1;
 sqlstm.offset = (unsigned int  )178;
 sqlstm.selerr = (unsigned short)1;
 sqlstm.cud = sqlcud0;
 sqlstm.sqlest = (unsigned char  *)&sqlca;
 sqlstm.sqlety = (unsigned short)256;
 sqlstm.occurs = (unsigned int  )0;
 sqlstm.sqcmod = (unsigned int )0;
 sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
 if (sqlca.sqlcode < 0) goto GotoListAll;
}



	if(sqlca.sqlcode)
		return 0;
	else
	{
		for(;;)
		{
			memset( &stCampanhaQuestionarioRegistro, 0, sizeof(stCampanhaQuestionarioRegistro) );
			/* EXEC SQL FETCH CursorCampanhaQuestionarioAll INTO :stCampanhaQuestionarioRegistro:stCampanhaQuestionarioIndicator; */ 

{
   struct sqlexd sqlstm;
   sqlstm.sqlvsn = 12;
   sqlstm.arrsiz = 5;
   sqlstm.sqladtp = &sqladt;
   sqlstm.sqltdsp = &sqltds;
   sqlstm.iters = (unsigned int  )1;
   sqlstm.offset = (unsigned int  )193;
   sqlstm.selerr = (unsigned short)1;
   sqlstm.cud = sqlcud0;
   sqlstm.sqlest = (unsigned char  *)&sqlca;
   sqlstm.sqlety = (unsigned short)256;
   sqlstm.occurs = (unsigned int  )0;
   sqlstm.sqfoff = (         int )0;
   sqlstm.sqfmod = (unsigned int )2;
   sqlstm.sqhstv[0] = (unsigned char  *)&stCampanhaQuestionarioRegistro.stidCampanhaQuestionario;
   sqlstm.sqhstl[0] = (unsigned long )24;
   sqlstm.sqhsts[0] = (         int  )0;
   sqlstm.sqindv[0] = (         short *)&stCampanhaQuestionarioIndicator.iidCampanhaQuestionario;
   sqlstm.sqinds[0] = (         int  )0;
   sqlstm.sqharm[0] = (unsigned long )0;
   sqlstm.sqadto[0] = (unsigned short )0;
   sqlstm.sqtdso[0] = (unsigned short )0;
   sqlstm.sqhstv[1] = (unsigned char  *)&stCampanhaQuestionarioRegistro.stidCanalCampanha;
   sqlstm.sqhstl[1] = (unsigned long )24;
   sqlstm.sqhsts[1] = (         int  )0;
   sqlstm.sqindv[1] = (         short *)&stCampanhaQuestionarioIndicator.iidCanalCampanha;
   sqlstm.sqinds[1] = (         int  )0;
   sqlstm.sqharm[1] = (unsigned long )0;
   sqlstm.sqadto[1] = (unsigned short )0;
   sqlstm.sqtdso[1] = (unsigned short )0;
   sqlstm.sqhstv[2] = (unsigned char  *)&stCampanhaQuestionarioRegistro.stidPergunta;
   sqlstm.sqhstl[2] = (unsigned long )24;
   sqlstm.sqhsts[2] = (         int  )0;
   sqlstm.sqindv[2] = (         short *)&stCampanhaQuestionarioIndicator.iidPergunta;
   sqlstm.sqinds[2] = (         int  )0;
   sqlstm.sqharm[2] = (unsigned long )0;
   sqlstm.sqadto[2] = (unsigned short )0;
   sqlstm.sqtdso[2] = (unsigned short )0;
   sqlstm.sqhstv[3] = (unsigned char  *)&stCampanhaQuestionarioRegistro.stidUser;
   sqlstm.sqhstl[3] = (unsigned long )24;
   sqlstm.sqhsts[3] = (         int  )0;
   sqlstm.sqindv[3] = (         short *)&stCampanhaQuestionarioIndicator.iidUser;
   sqlstm.sqinds[3] = (         int  )0;
   sqlstm.sqharm[3] = (unsigned long )0;
   sqlstm.sqadto[3] = (unsigned short )0;
   sqlstm.sqtdso[3] = (unsigned short )0;
   sqlstm.sqhstv[4] = (unsigned char  *)&stCampanhaQuestionarioRegistro.stdtUltimaAlteracao;
   sqlstm.sqhstl[4] = (unsigned long )24;
   sqlstm.sqhsts[4] = (         int  )0;
   sqlstm.sqindv[4] = (         short *)&stCampanhaQuestionarioIndicator.idtUltimaAlteracao;
   sqlstm.sqinds[4] = (         int  )0;
   sqlstm.sqharm[4] = (unsigned long )0;
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
   if (sqlca.sqlcode == 1403) break;
   if (sqlca.sqlcode < 0) goto GotoListAll;
}



			Add( (char*)stCampanhaQuestionarioRegistro.stidCampanhaQuestionario.arr,
			     (char*)stCampanhaQuestionarioRegistro.stidCanalCampanha.arr,
			     (char*)stCampanhaQuestionarioRegistro.stidPergunta.arr,
			     (char*)stCampanhaQuestionarioRegistro.stidUser.arr,
			     (char*)stCampanhaQuestionarioRegistro.stdtUltimaAlteracao.arr );

			iCont++;

		}
		/* EXEC SQL CLOSE CursorCampanhaQuestionarioAll; */ 

{
  struct sqlexd sqlstm;
  sqlstm.sqlvsn = 12;
  sqlstm.arrsiz = 5;
  sqlstm.sqladtp = &sqladt;
  sqlstm.sqltdsp = &sqltds;
  sqlstm.iters = (unsigned int  )1;
  sqlstm.offset = (unsigned int  )228;
  sqlstm.cud = sqlcud0;
  sqlstm.sqlest = (unsigned char  *)&sqlca;
  sqlstm.sqlety = (unsigned short)256;
  sqlstm.occurs = (unsigned int  )0;
  sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
  if (sqlca.sqlcode < 0) goto GotoListAll;
}


	}

	return iCont;

GotoListAll:
	throw TuxBasicOraException(sqlca.sqlcode);

}

void CCampanhaQuestionario::GetXml( char* cNomeTag, XMLGen*xml )
{
	if( Quantidade() > 0 )
	{
		for( int x = 0; x < Quantidade(); x++ )
		{
			xml->createTag(cNomeTag);
			xml->addProp(	"xmlns", "camapanha.fo.vivo.com.br/vo" );
			if( Registro( x ) != NULL )
			{
				xml->addItem("idCampanhaQuestionario", Registro(x)->cidCampanhaQuestionario  );
				xml->addItem("idCanalCampanha", Registro(x)->cidCanalCampanha    );
				xml->addItem("idPergunta", Registro(x)->cidPergunta    );
			}
			xml->closeTag();
		}
	}
}
