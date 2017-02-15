
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
           char  filnam[67];
};
static const struct sqlcxp sqlfpn =
{
    66,
    "/home/wsaba/BATCH/ManterPerfilUsuario/src/ManterPerfilUsuario.pcpp"
};


static unsigned int sqlctx = 278754436;


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
   unsigned char  *sqhstv[4];
   unsigned long  sqhstl[4];
            int   sqhsts[4];
            short *sqindv[4];
            int   sqinds[4];
   unsigned long  sqharm[4];
   unsigned long  *sqharc[4];
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
{12,4130,31,0,0,
5,0,0,1,388,0,4,345,0,0,4,3,0,1,0,2,3,0,0,1,97,0,0,1,97,0,0,1,97,0,0,
36,0,0,2,104,0,4,378,0,0,2,1,0,1,0,2,3,0,0,1,97,0,0,
59,0,0,3,87,0,4,381,0,0,2,1,0,1,0,2,3,0,0,1,97,0,0,
82,0,0,4,92,0,2,384,0,0,2,2,0,1,0,1,3,0,0,1,3,0,0,
105,0,0,5,0,0,29,387,0,0,0,0,0,1,0,
120,0,0,6,104,0,4,401,0,0,2,1,0,1,0,2,3,0,0,1,97,0,0,
143,0,0,7,87,0,4,404,0,0,2,1,0,1,0,2,3,0,0,1,97,0,0,
166,0,0,8,167,0,3,407,0,0,2,2,0,1,0,1,3,0,0,1,3,0,0,
189,0,0,9,0,0,29,411,0,0,0,0,0,1,0,
204,0,0,10,64,0,4,519,0,0,2,1,0,1,0,1,3,0,0,2,9,0,0,
227,0,0,11,58,0,4,549,0,0,1,0,0,1,0,2,9,0,0,
246,0,0,12,0,0,27,759,0,0,4,4,0,1,0,1,97,0,0,1,10,0,0,1,10,0,0,1,10,0,0,
277,0,0,13,0,0,30,776,0,0,0,0,0,1,0,
292,0,0,14,87,0,4,795,0,0,1,0,0,1,0,2,9,0,0,
};


#include <unistd.h>
#include <dirent.h>

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <stdio.h>
#include <iostream.h>
#ifndef WIN32
#include <unistd.h>
#endif
#include <ctype.h>

#include "../include/ManterPerfilUsuario.h"

#include "../../commons/Propriedade/include/MFile.h"
#include "../../commons/Log/include/Log.h"
#include "../../commons/SplitLine.h"
#include "../../commons/CLeitorArquivo/CLeitorArquivo.h"

//#define LOG_DE_ERRO 1

/* EXEC SQL INCLUDE SQLCA;
 */ 
/*
 * $Header: /cvs/BATCH/ManterPerfilUsuario/src/ManterPerfilUsuario.cpp,v 1.1.2.1 2010/11/12 11:47:47 a5116174 Exp $ sqlca.h 
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


char szAux[1024];
char szTemp[1024];
char szTempTrace[2048];
Log oLog;
int iSignalProcessa=1;

#define TAM_BUFFER 2048

#define CONVIND(O,I) \
{\
    if (I == -1) { \
        ##O.arr[0]=0; \
    } else { \
        ##O.arr[##O.len]=0; \
    } \
}

#ifdef WIN32
#define MKDIR _mkdir
#define ACCESS _access
#else
#define MKDIR mkdir
#define ACCESS access
#endif

void NomeRelatorio( char * sNmRelatorio );
void ObterHora(char *pszData);
int ManterRegistros(CLeitorArquivo &leitorArq, int iIndex, string &sStatusRegistro);
void moverArquivo(const char *pszNomeArquivoOrigem,const char *pszNomeArquivoDestino);
int OpenDir(char *pszDiretorio,char*pfile);

//void sqlTratamentoErro(sqlca*sqlca);

int main(void)
{
    TParamConf tParamConf;
    int retorno = 0;
    char sNmRelatorio[256];
	char szNome_Arquivo[1024];
	

    /* EXEC SQL BEGIN DECLARE SECTION; */ 

        /* VARCHAR oszLinha[512]; */ 
struct { unsigned short len; unsigned char arr[512]; } oszLinha;

        /* VARCHAR strOraDataProcessamento[12]; */ 
struct { unsigned short len; unsigned char arr[12]; } strOraDataProcessamento;

        /* VARCHAR strOraHoraProcessamento[12]; */ 
struct { unsigned short len; unsigned char arr[12]; } strOraHoraProcessamento;


        short ostLinha;
        short ostrOraDataProcessamento = -1;
        short ostrOraHoraProcessamento = -1;
    /* EXEC SQL END DECLARE SECTION; */ 


    oLog.setNivel(2);
    oLog.logDebug(">>>ManterPerfilUsuario");

#ifndef WIN32
    ArmaSinal(SIGTERM);
#endif

    // parametros de configuração
    if(ObtemParamConf(&tParamConf))
    {
        oLog.logError("Erro obtendo parametros de configuracao");
        oLog.logDebug("<<<ManterPerfilUsuario");
        return -1;
    }

    oLog.logDebug("Conectando na BD...");
    if (DBConnect(tParamConf.szUsr, tParamConf.szPws, tParamConf.szInst))
    {
        oLog.logError("Erro conectando no banco de dados");
        oLog.logDebug("<<<ManterPerfilUsuario");
        return -1;
    }
    oLog.logDebug("Conectado na BD com sucesso...");

	char szData[256];	memset(szData,'\0',sizeof(szData));
	char szHora[256];	memset(szHora,'\0',sizeof(szHora));
	ObterData(0,szData);
	ObterHora(szHora);
	
#ifdef LOG_DE_ERRO	
	FILE *pFileRegErros=0;
	string sArquivoRegErros;
	sArquivoRegErros = tParamConf.szPathLogErros;
	sArquivoRegErros += "/";	
	sArquivoRegErros += szData;
	sArquivoRegErros += "-";
	sArquivoRegErros += szHora;
	sArquivoRegErros += "-ManterPerfilUsuario_reg_erros.txt";
	
    if( !(pFileRegErros = fopen(sArquivoRegErros.data(), "w")))
    {
        sprintf(szTempTrace, "Erro criando arquivo[%s]", sArquivoRegErros.data()); oLog.logDebug(szTempTrace);
        return -1;
    }
#endif

	sprintf(szTempTrace, "Inicio do processo de ManterPerfilUsuario..."); oLog.logDebug(szTempTrace);
	
	// 1 - CRIA UM VETOR COM O NOME DAS COLUNAS:
	VEC_STRING vecString;
	vecString.push_back(string("LOGIN"));
	vecString.push_back(string("NOME_GRUPO"));
	vecString.push_back(string("NOME_PERFIL"));
	vecString.push_back(string("ACAO"));
	
	// 2 - DEFINE O CAMINHO EM QUE ESTA O ARQUIVO A SER LIDO
	string sCaminho;
	sCaminho = tParamConf.szPath;
					   
	// 3 - DEFINE O NOME DO ARQUIVO A SER LIDO
	int iCountLine=0;
	int iInseridosSucesso = 0;
	int iErrosOcorridos = 0;
	memset(&szNome_Arquivo,0,sizeof(szNome_Arquivo));
	while(OpenDir(tParamConf.szPath,szNome_Arquivo) != 0)
	{
		sprintf(szTempTrace, "Identificado um arquivo [%s] para realizar a carga...\n",szNome_Arquivo ); oLog.logDebug(szTempTrace);
		
		string sArquivo;
		sArquivo = "/";
		sArquivo += szNome_Arquivo;
		
		// 4 - DEFINE QUAL O TIPO DE SEPARADOR DE DADOS ESPERADO
		string sSeparador = "|";

		
		sprintf(szTempTrace, "Caminho[%s] e nome de arquivo[%s] utilizados para o ler as informaçoes de carga.",sCaminho.data() ,sArquivo.data()  ); oLog.logDebug(szTempTrace);
		// 5 - INSTANCIA O OBJETO CLEITORARQUIVO
		CLeitorArquivo leitorArq(vecString,sCaminho,sArquivo,sSeparador);
		
		int iNumeroLinhasLido = 0;
		// 6 - DEFINE UM TAMANHO DO BLOCO DE LINHAS QUE DEVE SER LIDO POR VEZ.
		int iTamanhoDoBloco = 500;


		

		string sStatusRegistro;
		// 6 - DEFINE UM LOOP DE TAMANHO J, ONDE J REPRESENTA QUANTAS VEZES SERA LIDO UM BLOCO DE TAMANHO 'iTAMANHODOBLOCO'
		for( int j=0;; ++j)
		{
			ErrosLeitorArquivo eErrorLeitor;
			eErrorLeitor = leitorArq.LerArquivo(iNumeroLinhasLido, iTamanhoDoBloco);
			switch(eErrorLeitor)
			{/*
				case ERRO_ABRIR_ARQUIVO:
				{
					oLog.logDebug("Retorno[ERRO_ABRIR_ARQUIVO] ao ler o arquivo.");					
					break;
				}
				case ERRO_QTD_DE_COLUNAS_INCOMPATIVEL:
				{
					oLog.logDebug("Retorno[ERRO_QTD_DE_COLUNAS_INCOMPATIVEL] ao ler o arquivo.");					
					break;
				}
				case OK_SUCESSO:
				{
					oLog.logDebug("Retorno[OK_SUCESSO] ao ler o arquivo.");					
					break;
				}
				default:
					break;*/
			}
			if( iNumeroLinhasLido > 0)
			{
				sprintf(szTempTrace, "Quantidade de registros lidos[%d] do arquivo[%s].",iNumeroLinhasLido,sArquivo.data()  ); oLog.logDebug(szTempTrace);
				// 7 - EXIBE O CONTEUDO DE CADA LINHA LIDO NO BLOCO CORRENTE
				for( int iIndex=0; iIndex< iNumeroLinhasLido ; ++iIndex)
				{
					int iReturn = 0;
					if ( iReturn = ManterRegistros(leitorArq, iIndex, sStatusRegistro) == 0 )
					{
						iInseridosSucesso++;
						// Registro inserido com sucesso.
						
					}
					else
					{
						iErrosOcorridos++;
						// Ocorreu algum problema e o Registro nao foi inserido. Gravar no arquivo de log de erros.
						#ifdef LOG_DE_ERRO
							string sRegistroError;
							sRegistroError = leitorArq.ObterLinha(iIndex) + ";" + sStatusRegistro;
							gravarRegistro( pFileRegErros, sRegistroError.data());
						#endif
					}
					
					/*cout<<leitorArq["Telefone"][i]<<"|";
					cout<<leitorArq["CodigoA"][i]<<"|";
					cout<<leitorArq["Data"][i]<<"|";
					cout<<leitorArq["CodigoB"][i];*/
				}
			}
			else
			{
				if( iCountLine == 0 )
				{
					sprintf(szTempTrace, "Nenhum registro lido do arquivo[%s].",sArquivo.data()  ); oLog.logDebug(szTempTrace);					
				}
				break;
			}
			iCountLine +=iNumeroLinhasLido;
		}
		//cout<<"\n Numero de Linhas Lidas = "<<iCountLine<<"\n";
		//system("pause");
		
		string strArquivoOrigem;
		string strArquivoDestino;
		
		strArquivoOrigem = szNome_Arquivo;
		strArquivoDestino = szNome_Arquivo;
		strArquivoDestino += ".out";
		moverArquivo(strArquivoOrigem.c_str(), strArquivoDestino.c_str());

	#ifdef LOG_DE_ERRO
		fclose(pFileRegErros);
	#endif
	}
	
	// 8 - QUANDO TERMINAR O ESCOPO O OBJETO 'CLEITORARQUIVO' CHAMA O DESTRUTOR QUE FECHA O ARQUIVO LIDO.

	sprintf(szTempTrace, "Foram inseridos [%d] registros com sucesso.", iInseridosSucesso ); oLog.logDebug(szTempTrace);
	sprintf(szTempTrace, "Foram identificados [%d] registros com erro.", iErrosOcorridos ); oLog.logDebug(szTempTrace);
	sprintf(szTempTrace, "Fim do processo de ManterPerfilUsuario..."); oLog.logDebug(szTempTrace);

    DBDisconnect();

    if ( 0 == retorno )
    {
        oLog.logDebug("Processamento encerrado com sucesso...");
    }
    else
    {
        oLog.logDebug("Processamento encerrado com ERRO!");
    }

    oLog.logDebug("<<<ManterPerfilUsuario");

    return retorno;

    sqlError:
        sprintf(szTempTrace, "Finalizando processo com erro ORACLE (%d)", sqlca.sqlcode); oLog.logDebug(szTempTrace);
        return -1;
}

void moverArquivo(const char *pszNomeArquivoOrigem,const char *pszNomeArquivoDestino)
{
    char sComando[_MAX_PATH*2+16];
    char szAux[1024];

    sprintf(sComando,"/usr/bin/mv -f %s %s", pszNomeArquivoOrigem, pszNomeArquivoDestino);

    oLog.logDebug(" Vai mover o arquivo");
    sprintf(szAux," Arquivo Origem [%s]", pszNomeArquivoOrigem); oLog.logDebug(szAux);
    sprintf(szAux,"Arquivo Destino [%s]", pszNomeArquivoDestino); oLog.logDebug(szAux);
    sprintf(szAux,"        Comando [%s]", sComando); oLog.logDebug(szAux);

    system(sComando);
}

/************************************************************************************************************/
int ManterRegistros(CLeitorArquivo &leitorArq, int iIndex, string &sStatusRegistro)
{
    int iQtdRegistros=0;
	int retorno = 0;
	char szNomeArquivo[_MAX_PATH+9];
	char szNRDOCUMENTO[TAM_BUFFER+1];
	
	/* EXEC SQL BEGIN DECLARE SECTION; */ 

		const char* ccOraLogin = leitorArq["LOGIN"][iIndex].data();
		const char* ccOraNomeGrupo = leitorArq["NOME_GRUPO"][iIndex].data();
		const char* ccOraNomePerfil = leitorArq["NOME_PERFIL"][iIndex].data();
		const char* ccOraAcao = leitorArq["ACAO"][iIndex].data();
		/* VARCHAR szCONTA[20]; */ 
struct { unsigned short len; unsigned char arr[20]; } szCONTA;

		short iszCONTA=-1;
        /* VARCHAR ostrIDPessoa[21+1]; */ 
struct { unsigned short len; unsigned char arr[22]; } ostrIDPessoa;

		
		int iOraExisteRegistro;
		int iOraIdPessoaUsr;
		int iOraIdGrupo;
		
		int iOraExisteGrupo;
		int iOraExistePerfil;
		
	/* EXEC SQL END DECLARE SECTION; */ 

	struct sqlca sqlca;
	
    /* EXEC SQL WHENEVER NOT FOUND CONTINUE; */ 

	/* EXEC SQL WHENEVER SQLERROR GOTO sqlError; */ 

	//EXEC SQL WHENEVER SQLERROR  DO sqlTratamentoErro(&sqlca);	
	
	string sTemp;
	sTemp = "Parametro de entrada LOGIN [";
	sTemp += leitorArq["LOGIN"][iIndex].data();
	sTemp += "], NOME_GRUPO [";
	sTemp += leitorArq["NOME_GRUPO"][iIndex].data();
	sTemp += "], NOME_PERFIL [";
	sTemp += leitorArq["NOME_PERFIL"][iIndex].data();
	sTemp += "], ACAO [";
	sTemp += leitorArq["ACAO"][iIndex].data();
	
	oLog.logDebug(sTemp.data());	
	
/*	EXEC SQL
	SELECT 
		USR.NMLOGINUSUARIO, GRP.NMGRUPO, PERF.NMPERFIL 
	FROM 
		ACESSO.USUARIO USR,
		ACESSO.USUARIOGRUPO USRG,
		ACESSO.GRUPO GRP,
		CONTATOADM.PERFIL PERF
	WHERE
		USR.IDPESSOAUSUARIO = USRG.IDPESSOAUSUARIO AND
		USRG.IDGRUPO = GRP.IDGRUPO AND
		GRP.NMGRUPO = 'ADMINISTRADOR' AND
		PERF.NMPERFIL = 'TODAS VARIAVEIS' AND
		USR.NMLOGINUSUARIO = 'A_AMARQUES' AND
		(SELECT COUNT(1) FROM CONTATOADM.GRUPOPERFIL GRPPERF WHERE GRP.IDGRUPO = GRPPERF.IDGRUPO AND GRPPERF.IDPERFIL = PERF.IDPERFIL) >= 1;
*/
	/* EXEC SQL
	SELECT 
		COUNT(1) 
	INTO
		:iOraExisteRegistro
	FROM 
		ACESSO.USUARIO USR,
		ACESSO.USUARIOGRUPO USRG,
		ACESSO.GRUPO GRP,
		CONTATOADM.PERFIL PERF,
		CONTATOADM.GRUPOPERFIL GRPPERF
	WHERE
		USR.IDPESSOAUSUARIO = USRG.IDPESSOAUSUARIO AND
		USRG.IDGRUPO = GRP.IDGRUPO AND
		GRP.NMGRUPO = :ccOraNomeGrupo AND
		PERF.NMPERFIL = :ccOraNomePerfil AND
		USR.NMLOGINUSUARIO = :ccOraLogin AND
		GRP.IDGRUPO = GRPPERF.IDGRUPO AND
		GRPPERF.IDPERFIL = PERF.IDPERFIL AND
		ROWNUM < 2; */ 

{
 struct sqlexd sqlstm;
 sqlstm.sqlvsn = 12;
 sqlstm.arrsiz = 4;
 sqlstm.sqladtp = &sqladt;
 sqlstm.sqltdsp = &sqltds;
 sqlstm.stmt = "select count(1) into :b0  from ACESSO.USUARIO USR ,ACESSO.US\
UARIOGRUPO USRG ,ACESSO.GRUPO GRP ,CONTATOADM.PERFIL PERF ,CONTATOADM.GRUPOPER\
FIL GRPPERF where (((((((USR.IDPESSOAUSUARIO=USRG.IDPESSOAUSUARIO and USRG.IDG\
RUPO=GRP.IDGRUPO) and GRP.NMGRUPO=:b1) and PERF.NMPERFIL=:b2) and USR.NMLOGINU\
SUARIO=:b3) and GRP.IDGRUPO=GRPPERF.IDGRUPO) and GRPPERF.IDPERFIL=PERF.IDPERFI\
L) and ROWNUM<2)";
 sqlstm.iters = (unsigned int  )1;
 sqlstm.offset = (unsigned int  )5;
 sqlstm.selerr = (unsigned short)1;
 sqlstm.cud = sqlcud0;
 sqlstm.sqlest = (unsigned char  *)&sqlca;
 sqlstm.sqlety = (unsigned short)256;
 sqlstm.occurs = (unsigned int  )0;
 sqlstm.sqhstv[0] = (unsigned char  *)&iOraExisteRegistro;
 sqlstm.sqhstl[0] = (unsigned long )sizeof(int);
 sqlstm.sqhsts[0] = (         int  )0;
 sqlstm.sqindv[0] = (         short *)0;
 sqlstm.sqinds[0] = (         int  )0;
 sqlstm.sqharm[0] = (unsigned long )0;
 sqlstm.sqadto[0] = (unsigned short )0;
 sqlstm.sqtdso[0] = (unsigned short )0;
 sqlstm.sqhstv[1] = (unsigned char  *)ccOraNomeGrupo;
 sqlstm.sqhstl[1] = (unsigned long )0;
 sqlstm.sqhsts[1] = (         int  )0;
 sqlstm.sqindv[1] = (         short *)0;
 sqlstm.sqinds[1] = (         int  )0;
 sqlstm.sqharm[1] = (unsigned long )0;
 sqlstm.sqadto[1] = (unsigned short )0;
 sqlstm.sqtdso[1] = (unsigned short )0;
 sqlstm.sqhstv[2] = (unsigned char  *)ccOraNomePerfil;
 sqlstm.sqhstl[2] = (unsigned long )0;
 sqlstm.sqhsts[2] = (         int  )0;
 sqlstm.sqindv[2] = (         short *)0;
 sqlstm.sqinds[2] = (         int  )0;
 sqlstm.sqharm[2] = (unsigned long )0;
 sqlstm.sqadto[2] = (unsigned short )0;
 sqlstm.sqtdso[2] = (unsigned short )0;
 sqlstm.sqhstv[3] = (unsigned char  *)ccOraLogin;
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
 if (sqlca.sqlcode < 0) goto sqlError;
}



		
		
	if(leitorArq["ACAO"][iIndex].compare("1 – EXCLUIR") == 0)
	{
		sprintf(szTempTrace, "Identificado a Ação EXCLUIR..."); oLog.logDebug(szTempTrace);	
		if( iOraExisteRegistro <= 0 )
		{
			sprintf(szTempTrace, "Registro nao econtrado. Ação de excluir nao realizada..."); oLog.logDebug(szTempTrace);	
			return -1;
		}
		else
		{
			/* EXEC SQL
				SELECT USR.IDPESSOAUSUARIO INTO :iOraIdPessoaUsr FROM ACESSO.USUARIO USR WHERE USR.NMLOGINUSUARIO = :ccOraLogin AND ROWNUM < 2; */ 

{
   struct sqlexd sqlstm;
   sqlstm.sqlvsn = 12;
   sqlstm.arrsiz = 4;
   sqlstm.sqladtp = &sqladt;
   sqlstm.sqltdsp = &sqltds;
   sqlstm.stmt = "select USR.IDPESSOAUSUARIO into :b0  from ACESSO.USUARIO U\
SR where (USR.NMLOGINUSUARIO=:b1 and ROWNUM<2)";
   sqlstm.iters = (unsigned int  )1;
   sqlstm.offset = (unsigned int  )36;
   sqlstm.selerr = (unsigned short)1;
   sqlstm.cud = sqlcud0;
   sqlstm.sqlest = (unsigned char  *)&sqlca;
   sqlstm.sqlety = (unsigned short)256;
   sqlstm.occurs = (unsigned int  )0;
   sqlstm.sqhstv[0] = (unsigned char  *)&iOraIdPessoaUsr;
   sqlstm.sqhstl[0] = (unsigned long )sizeof(int);
   sqlstm.sqhsts[0] = (         int  )0;
   sqlstm.sqindv[0] = (         short *)0;
   sqlstm.sqinds[0] = (         int  )0;
   sqlstm.sqharm[0] = (unsigned long )0;
   sqlstm.sqadto[0] = (unsigned short )0;
   sqlstm.sqtdso[0] = (unsigned short )0;
   sqlstm.sqhstv[1] = (unsigned char  *)ccOraLogin;
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
   if (sqlca.sqlcode < 0) goto sqlError;
}


			
			/* EXEC SQL
				SELECT GRP.IDGRUPO INTO :iOraIdGrupo FROM ACESSO.GRUPO GRP WHERE GRP.NMGRUPO = :ccOraNomeGrupo AND ROWNUM < 2; */ 

{
   struct sqlexd sqlstm;
   sqlstm.sqlvsn = 12;
   sqlstm.arrsiz = 4;
   sqlstm.sqladtp = &sqladt;
   sqlstm.sqltdsp = &sqltds;
   sqlstm.stmt = "select GRP.IDGRUPO into :b0  from ACESSO.GRUPO GRP where (\
GRP.NMGRUPO=:b1 and ROWNUM<2)";
   sqlstm.iters = (unsigned int  )1;
   sqlstm.offset = (unsigned int  )59;
   sqlstm.selerr = (unsigned short)1;
   sqlstm.cud = sqlcud0;
   sqlstm.sqlest = (unsigned char  *)&sqlca;
   sqlstm.sqlety = (unsigned short)256;
   sqlstm.occurs = (unsigned int  )0;
   sqlstm.sqhstv[0] = (unsigned char  *)&iOraIdGrupo;
   sqlstm.sqhstl[0] = (unsigned long )sizeof(int);
   sqlstm.sqhsts[0] = (         int  )0;
   sqlstm.sqindv[0] = (         short *)0;
   sqlstm.sqinds[0] = (         int  )0;
   sqlstm.sqharm[0] = (unsigned long )0;
   sqlstm.sqadto[0] = (unsigned short )0;
   sqlstm.sqtdso[0] = (unsigned short )0;
   sqlstm.sqhstv[1] = (unsigned char  *)ccOraNomeGrupo;
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
   if (sqlca.sqlcode < 0) goto sqlError;
}



			/* EXEC SQL
				DELETE FROM ACESSO.USUARIOGRUPO USRG WHERE USRG.IDGRUPO = :iOraIdGrupo AND USRG.IDPESSOAUSUARIO = :iOraIdPessoaUsr; */ 

{
   struct sqlexd sqlstm;
   sqlstm.sqlvsn = 12;
   sqlstm.arrsiz = 4;
   sqlstm.sqladtp = &sqladt;
   sqlstm.sqltdsp = &sqltds;
   sqlstm.stmt = "delete  from ACESSO.USUARIOGRUPO USRG  where (USRG.IDGRUPO\
=:b0 and USRG.IDPESSOAUSUARIO=:b1)";
   sqlstm.iters = (unsigned int  )1;
   sqlstm.offset = (unsigned int  )82;
   sqlstm.cud = sqlcud0;
   sqlstm.sqlest = (unsigned char  *)&sqlca;
   sqlstm.sqlety = (unsigned short)256;
   sqlstm.occurs = (unsigned int  )0;
   sqlstm.sqhstv[0] = (unsigned char  *)&iOraIdGrupo;
   sqlstm.sqhstl[0] = (unsigned long )sizeof(int);
   sqlstm.sqhsts[0] = (         int  )0;
   sqlstm.sqindv[0] = (         short *)0;
   sqlstm.sqinds[0] = (         int  )0;
   sqlstm.sqharm[0] = (unsigned long )0;
   sqlstm.sqadto[0] = (unsigned short )0;
   sqlstm.sqtdso[0] = (unsigned short )0;
   sqlstm.sqhstv[1] = (unsigned char  *)&iOraIdPessoaUsr;
   sqlstm.sqhstl[1] = (unsigned long )sizeof(int);
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
   if (sqlca.sqlcode < 0) goto sqlError;
}


				
			/* EXEC SQL COMMIT; */ 

{
   struct sqlexd sqlstm;
   sqlstm.sqlvsn = 12;
   sqlstm.arrsiz = 4;
   sqlstm.sqladtp = &sqladt;
   sqlstm.sqltdsp = &sqltds;
   sqlstm.iters = (unsigned int  )1;
   sqlstm.offset = (unsigned int  )105;
   sqlstm.cud = sqlcud0;
   sqlstm.sqlest = (unsigned char  *)&sqlca;
   sqlstm.sqlety = (unsigned short)256;
   sqlstm.occurs = (unsigned int  )0;
   sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
   if (sqlca.sqlcode < 0) goto sqlError;
}


			sprintf(szTempTrace, "Registro Excluido com sucesso..."); oLog.logDebug(szTempTrace);
		}
	}
	else if(leitorArq["ACAO"][iIndex].compare("2 – INCLUIR") == 0)
	{
		sprintf(szTempTrace, "Identificado a Ação INCLUIR..."); oLog.logDebug(szTempTrace);	
		if( iOraExisteRegistro >= 1 )
		{
			sprintf(szTempTrace, "Registro já existe no banco de dados. Ação de incluir nao realizada..."); oLog.logDebug(szTempTrace);
			return -1;
		}
		else
		{
			/* EXEC SQL
				SELECT USR.IDPESSOAUSUARIO INTO :iOraIdPessoaUsr FROM ACESSO.USUARIO USR WHERE USR.NMLOGINUSUARIO = :ccOraLogin AND ROWNUM < 2; */ 

{
   struct sqlexd sqlstm;
   sqlstm.sqlvsn = 12;
   sqlstm.arrsiz = 4;
   sqlstm.sqladtp = &sqladt;
   sqlstm.sqltdsp = &sqltds;
   sqlstm.stmt = "select USR.IDPESSOAUSUARIO into :b0  from ACESSO.USUARIO U\
SR where (USR.NMLOGINUSUARIO=:b1 and ROWNUM<2)";
   sqlstm.iters = (unsigned int  )1;
   sqlstm.offset = (unsigned int  )120;
   sqlstm.selerr = (unsigned short)1;
   sqlstm.cud = sqlcud0;
   sqlstm.sqlest = (unsigned char  *)&sqlca;
   sqlstm.sqlety = (unsigned short)256;
   sqlstm.occurs = (unsigned int  )0;
   sqlstm.sqhstv[0] = (unsigned char  *)&iOraIdPessoaUsr;
   sqlstm.sqhstl[0] = (unsigned long )sizeof(int);
   sqlstm.sqhsts[0] = (         int  )0;
   sqlstm.sqindv[0] = (         short *)0;
   sqlstm.sqinds[0] = (         int  )0;
   sqlstm.sqharm[0] = (unsigned long )0;
   sqlstm.sqadto[0] = (unsigned short )0;
   sqlstm.sqtdso[0] = (unsigned short )0;
   sqlstm.sqhstv[1] = (unsigned char  *)ccOraLogin;
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
   if (sqlca.sqlcode < 0) goto sqlError;
}


			
			/* EXEC SQL
				SELECT GRP.IDGRUPO INTO :iOraIdGrupo FROM ACESSO.GRUPO GRP WHERE GRP.NMGRUPO = :ccOraNomeGrupo AND ROWNUM < 2; */ 

{
   struct sqlexd sqlstm;
   sqlstm.sqlvsn = 12;
   sqlstm.arrsiz = 4;
   sqlstm.sqladtp = &sqladt;
   sqlstm.sqltdsp = &sqltds;
   sqlstm.stmt = "select GRP.IDGRUPO into :b0  from ACESSO.GRUPO GRP where (\
GRP.NMGRUPO=:b1 and ROWNUM<2)";
   sqlstm.iters = (unsigned int  )1;
   sqlstm.offset = (unsigned int  )143;
   sqlstm.selerr = (unsigned short)1;
   sqlstm.cud = sqlcud0;
   sqlstm.sqlest = (unsigned char  *)&sqlca;
   sqlstm.sqlety = (unsigned short)256;
   sqlstm.occurs = (unsigned int  )0;
   sqlstm.sqhstv[0] = (unsigned char  *)&iOraIdGrupo;
   sqlstm.sqhstl[0] = (unsigned long )sizeof(int);
   sqlstm.sqhsts[0] = (         int  )0;
   sqlstm.sqindv[0] = (         short *)0;
   sqlstm.sqinds[0] = (         int  )0;
   sqlstm.sqharm[0] = (unsigned long )0;
   sqlstm.sqadto[0] = (unsigned short )0;
   sqlstm.sqtdso[0] = (unsigned short )0;
   sqlstm.sqhstv[1] = (unsigned char  *)ccOraNomeGrupo;
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
   if (sqlca.sqlcode < 0) goto sqlError;
}


			
			/* EXEC SQL
			  INSERT INTO ACESSO.USUARIOGRUPO (IDUSUARIOGRUPO, IDPESSOAUSUARIO, IDGRUPO, IDUSUARIOALTERACAO, DTULTIMAALTERACAO)
				VALUES(ACESSO.USUARIOGRUPOSQ.NEXTVAL, :iOraIdPessoaUsr, :iOraIdGrupo, 1, SYSDATE); */ 

{
   struct sqlexd sqlstm;
   sqlstm.sqlvsn = 12;
   sqlstm.arrsiz = 4;
   sqlstm.sqladtp = &sqladt;
   sqlstm.sqltdsp = &sqltds;
   sqlstm.stmt = "insert into ACESSO.USUARIOGRUPO (IDUSUARIOGRUPO,IDPESSOAUS\
UARIO,IDGRUPO,IDUSUARIOALTERACAO,DTULTIMAALTERACAO) values (ACESSO.USUARIOGRUP\
OSQ.nextval ,:b0,:b1,1,SYSDATE)";
   sqlstm.iters = (unsigned int  )1;
   sqlstm.offset = (unsigned int  )166;
   sqlstm.cud = sqlcud0;
   sqlstm.sqlest = (unsigned char  *)&sqlca;
   sqlstm.sqlety = (unsigned short)256;
   sqlstm.occurs = (unsigned int  )0;
   sqlstm.sqhstv[0] = (unsigned char  *)&iOraIdPessoaUsr;
   sqlstm.sqhstl[0] = (unsigned long )sizeof(int);
   sqlstm.sqhsts[0] = (         int  )0;
   sqlstm.sqindv[0] = (         short *)0;
   sqlstm.sqinds[0] = (         int  )0;
   sqlstm.sqharm[0] = (unsigned long )0;
   sqlstm.sqadto[0] = (unsigned short )0;
   sqlstm.sqtdso[0] = (unsigned short )0;
   sqlstm.sqhstv[1] = (unsigned char  *)&iOraIdGrupo;
   sqlstm.sqhstl[1] = (unsigned long )sizeof(int);
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
   if (sqlca.sqlcode < 0) goto sqlError;
}


				
			/* EXEC SQL COMMIT; */ 

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
   if (sqlca.sqlcode < 0) goto sqlError;
}


			sprintf(szTempTrace, "Registro Incluido com sucesso..."); oLog.logDebug(szTempTrace);
		}
	}
	else
	{
		sprintf(szTempTrace, "Registro nao atualizado. Ação desconhecida..."); oLog.logDebug(szTempTrace);	
	}
	
	return 0;

sqlError:
		string sRegistroError;
		sRegistroError = "Erro no processamento do registro: " + leitorArq.ObterLinha(iIndex) + ";" + sStatusRegistro;
		oLog.logDebug((char *)sRegistroError.data());
		
	    sprintf(szTempTrace, "ERRO ORACLE -> sqlcode=%d,sqlerrmc=%.70s\n",sqlca.sqlcode,sqlca.sqlerrm.sqlerrmc); oLog.logDebug(szTempTrace);		
		sStatusRegistro = szTempTrace;
        return -2;	

/*sqlInsertPG:
		sStatusRegistro = sqlca.sqlerrm.sqlerrmc;
        sprintf(szTempTrace, "Ocorreu um erro ao tentar inserir um registro em CUSTOMER.PESSOAGESTOR. Erro ORACLE (%d)", sqlca.sqlcode); oLog.logDebug(szTempTrace);
        return -2;	
		
sqlInsertPGC:
		sStatusRegistro = sqlca.sqlerrm.sqlerrmc;
        sprintf(szTempTrace, "Ocorreu um erro ao tentar inserir um registro em CUSTOMER.PESSOAGESTORCONTA. Erro ORACLE (%d)", sqlca.sqlcode); oLog.logDebug(szTempTrace);
        return -3;	
		
sqlInsertPGM:
        sStatusRegistro = sqlca.sqlerrm.sqlerrmc;
		sprintf(szTempTrace, "Ocorreu um erro ao tentar inserir um registro em CUSTOMER.PESSOAGESTORMASTER. Erro ORACLE (%d)", sqlca.sqlcode); oLog.logDebug(szTempTrace);
        return -4;	
		*/
}


int OpenDir(char *pszDiretorio,char*pfile)
{
    static DIR *pDir;
    static struct dirent *pDirent;
    static char szArq[512 + 1];
    static int iFlag=1;

	char szAux[1024];

    oLog.logDebug(">>> OpenDir");
    sprintf(szAux, "pszDiretorio[%s]", pszDiretorio); oLog.logDebug(szAux);

    if(iFlag == 1)
    {
        /* Abre diretorio e verifica sucesso */
        if( (pDir = opendir(pszDiretorio)) == NULL)
        {
            sprintf(szAux, "Erro abrindo diretorio [%s]. errno(%d) strerror[%s]", pszDiretorio, errno, strerror(errno)); oLog.logError(szAux);
            exit(-1);
        }

        iFlag=0;
    }
	
	try
	{
		/* Efetua a leitura dos nomes dos arquivos do diretorio */
		while((pDirent = readdir(pDir)) != NULL )
		{
			sprintf(szAux, "Arquivo obtido[%s]", pDirent->d_name); oLog.logDebug(szAux);

			/* valida arquivo */
			if((!memcmp(pDirent->d_name+strlen(pDirent->d_name)-4, ".txt", 4)))
			{
				/* Monta path + nome do arquivo a ser processado */
				strcpy(szArq, pDirent->d_name);
				sprintf(szAux, "Arquivo validado [%s]", szArq); oLog.logDebug(szAux);

				oLog.logDebug("<<< OpenDir <NOT NULL>");
				strcpy(pfile,szArq);
				return 1;
			}
		}
	}
	catch(...)
	{
		oLog.logDebug("<<< OpenDir (exception).");
	}

    closedir(pDir);
    iFlag=1;
    oLog.logDebug("<<< OpenDir <NULL>");
    return 0;
}




/************************************************************************************************************/
void ObterData(int iDiasAnteriores, char *pszData)
{
    /* EXEC SQL BEGIN DECLARE SECTION; */ 

        /* VARCHAR strOraDataProcessamento[12]; */ 
struct { unsigned short len; unsigned char arr[12]; } strOraDataProcessamento;

        short ostrOraDataProcessamento = -1;
		short ostrDiasAnteriores = iDiasAnteriores;
    /* EXEC SQL END DECLARE SECTION; */ 

	struct sqlca sqlca;
	
    /* EXEC SQL WHENEVER NOT FOUND CONTINUE; */ 

	/* EXEC SQL WHENEVER SQLERROR GOTO sqlError; */ 

    /* EXEC SQL 
        SELECT
           TO_CHAR(SYSDATE -:ostrDiasAnteriores,'DDMMYYYY')
        INTO
            :strOraDataProcessamento:ostrOraDataProcessamento
        FROM
            DUAL; */ 

{
    struct sqlexd sqlstm;
    sqlstm.sqlvsn = 12;
    sqlstm.arrsiz = 4;
    sqlstm.sqladtp = &sqladt;
    sqlstm.sqltdsp = &sqltds;
    sqlstm.stmt = "select TO_CHAR((SYSDATE-:b0),'DDMMYYYY') into :b1:b2  fro\
m DUAL ";
    sqlstm.iters = (unsigned int  )1;
    sqlstm.offset = (unsigned int  )204;
    sqlstm.selerr = (unsigned short)1;
    sqlstm.cud = sqlcud0;
    sqlstm.sqlest = (unsigned char  *)&sqlca;
    sqlstm.sqlety = (unsigned short)256;
    sqlstm.occurs = (unsigned int  )0;
    sqlstm.sqhstv[0] = (unsigned char  *)&ostrDiasAnteriores;
    sqlstm.sqhstl[0] = (unsigned long )sizeof(short);
    sqlstm.sqhsts[0] = (         int  )0;
    sqlstm.sqindv[0] = (         short *)0;
    sqlstm.sqinds[0] = (         int  )0;
    sqlstm.sqharm[0] = (unsigned long )0;
    sqlstm.sqadto[0] = (unsigned short )0;
    sqlstm.sqtdso[0] = (unsigned short )0;
    sqlstm.sqhstv[1] = (unsigned char  *)&strOraDataProcessamento;
    sqlstm.sqhstl[1] = (unsigned long )14;
    sqlstm.sqhsts[1] = (         int  )0;
    sqlstm.sqindv[1] = (         short *)&ostrOraDataProcessamento;
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
    if (sqlca.sqlcode < 0) goto sqlError;
}



    CONVIND( strOraDataProcessamento,ostrOraDataProcessamento );

	sprintf( pszData,"%s", (char*)strOraDataProcessamento.arr );
	pszData[8] = '\0';
	
    return;
	
sqlError:
        return;		
}

void ObterHora(char *pszData)
{
    /* EXEC SQL BEGIN DECLARE SECTION; */ 

        /* VARCHAR strOraDataProcessamento[12]; */ 
struct { unsigned short len; unsigned char arr[12]; } strOraDataProcessamento;

        short ostrOraDataProcessamento = -1;
    /* EXEC SQL END DECLARE SECTION; */ 

	struct sqlca sqlca;
	
    /* EXEC SQL WHENEVER NOT FOUND CONTINUE; */ 

	/* EXEC SQL WHENEVER SQLERROR GOTO sqlError; */ 

	
    /* EXEC SQL 
        SELECT
           TO_CHAR(SYSDATE,'HH24MISS')
        INTO
            :strOraDataProcessamento:ostrOraDataProcessamento
        FROM
            DUAL; */ 

{
    struct sqlexd sqlstm;
    sqlstm.sqlvsn = 12;
    sqlstm.arrsiz = 4;
    sqlstm.sqladtp = &sqladt;
    sqlstm.sqltdsp = &sqltds;
    sqlstm.stmt = "select TO_CHAR(SYSDATE,'HH24MISS') into :b0:b1  from DUAL\
 ";
    sqlstm.iters = (unsigned int  )1;
    sqlstm.offset = (unsigned int  )227;
    sqlstm.selerr = (unsigned short)1;
    sqlstm.cud = sqlcud0;
    sqlstm.sqlest = (unsigned char  *)&sqlca;
    sqlstm.sqlety = (unsigned short)256;
    sqlstm.occurs = (unsigned int  )0;
    sqlstm.sqhstv[0] = (unsigned char  *)&strOraDataProcessamento;
    sqlstm.sqhstl[0] = (unsigned long )14;
    sqlstm.sqhsts[0] = (         int  )0;
    sqlstm.sqindv[0] = (         short *)&ostrOraDataProcessamento;
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



    CONVIND( strOraDataProcessamento,ostrOraDataProcessamento );

	sprintf( pszData,"%s", (char*)strOraDataProcessamento.arr );
	pszData[6] = '\0';
    return;
sqlError:
        return;		
	
}

/************************************************************************************************************/
int gravarRegistro(FILE *pFile, const char *spzRegistro)
{
    char szPog[1000];
 
    if( spzRegistro )
    {
        if(fputs(spzRegistro, pFile) <= 0)
        {
            return -1;
        }
    }

    return 0;
}

/************************************************************************************************************/

/*void sqlTratamentoErro(sqlca*sqlca)
{

    EXEC SQL WHENEVER NOT FOUND CONTINUE;

    ULOGE("sql_error_WFAtendimento:sqlcode=%d,sqlerrmc=%.70s"
                            ,sqlca->sqlcode
                            ,sqlca->sqlerrm.sqlerrmc);

    throw new TuxBasicOraException(sqlca->sqlcode
                                  ,sqlca->sqlerrm.sqlerrmc
                                  ,sqlca->sqlerrm.sqlerrml);
	return 0;
}
*/

/************************************************************************************************************/
#ifndef WIN32
void ArmaSinal(int iSignal)
{
    sprintf(szTempTrace, "Armando tratamento para Signal(%d)", iSignal); oLog.logInformation(szTempTrace);

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
    sprintf(szTempTrace, "iSig(%d)", iSig); oLog.logInformation(szTempTrace);

    /* rearma o mesmo sinal lancado */
    ArmaSinal(iSig);

    if(iSig == SIGTERM)
	{
        oLog.logInformation("Finalizando processamento via sinal....");
        iSignalProcessa=0;
    }

    oLog.logInformation(">>>ProcessaSignal");
}
#endif

/************************************************************************************************************/
int ObtemParamConf(TParamConf *ptParamConf)
{
    MFile mfConfig;
    SplitLine NewLinha;
    char szLinha[100 + 1];
    char szDivArq[256];
    char szDivPre[21];

    oLog.logDebug("Obtendo parametros de configuracao...");

    /* Zera variáveis */
    memset(ptParamConf, 0x00, sizeof(TParamConf));
    memset(szLinha, 0x00, sizeof(szLinha));
    memset(szDivArq, 0x00, sizeof(szDivArq));
    memset(szDivPre, 0x00, sizeof(szDivPre));


    /* Define o arquivo de configuração */
    mfConfig.setPath("ManterPerfilUsuario.cfg");

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
        else if (!strcmp(szDivPre, "clt_tux"))
        {
            strncpy(ptParamConf->szCltTux, szDivArq, TAM_TX_CLT);
        }
        else if (!strcmp(szDivPre, "path"))
        {
            strncpy(ptParamConf->szPath, szDivArq, _MAX_PATH);
        }
        else if (!strcmp(szDivPre, "path_log_erros"))
        {
            strncpy(ptParamConf->szPathLogErros, szDivArq, _MAX_PATH);
        }
		
        memset(szLinha,  0x00, sizeof(szLinha));
        memset(szDivPre, 0x00, sizeof(szDivPre));
        memset(szDivArq, 0x00, sizeof(szDivArq));
    }

    /* Fechando o arquivo de configuracao aberto */
    mfConfig.fechar();

    sprintf(szTempTrace, "ptParamConf->szPws[%s]", ptParamConf->szPws); oLog.logDebug(szTempTrace);
    sprintf(szTempTrace, "ptParamConf->szUsr[%s]", ptParamConf->szUsr); oLog.logDebug(szTempTrace);
    sprintf(szTempTrace, "ptParamConf->szInst[%s]", ptParamConf->szInst); oLog.logDebug(szTempTrace);
    sprintf(szTempTrace, "ptParamConf->szPwsTux[%s]", ptParamConf->szPwsTux); oLog.logDebug(szTempTrace);
    sprintf(szTempTrace, "ptParamConf->szUsrTux[%s]", ptParamConf->szUsrTux); oLog.logDebug(szTempTrace);
    sprintf(szTempTrace, "ptParamConf->szPwsTuxGen[%s]", ptParamConf->szPwsTuxGen); oLog.logDebug(szTempTrace);
    sprintf(szTempTrace, "ptParamConf->szCltTux[%s]", ptParamConf->szCltTux); oLog.logDebug(szTempTrace);
    sprintf(szTempTrace, "ptParamConf->szPath[%s]", ptParamConf->szPath); oLog.logDebug(szTempTrace);
    sprintf(szTempTrace, "ptParamConf->szPathLogErros[%s]", ptParamConf->szPathLogErros); oLog.logDebug(szTempTrace);

    /* Verifica se todos os dados foram recuperados do arquivo de configuração. */
    if (strlen(ptParamConf->szInst) == 0 ||
        strlen(ptParamConf->szPwsTux) == 0 ||
        strlen(ptParamConf->szUsrTux) == 0 ||
        strlen(ptParamConf->szPwsTuxGen) == 0 ||
        strlen(ptParamConf->szCltTux) == 0 ||
        strlen(ptParamConf->szPath) == 0 ||
        strlen(ptParamConf->szPathLogErros) == 0 )
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


    oLog.logInformation(">>>DBConnect");

    /* String de conexao */
    sprintf(connString, "%s/%s@%s", pUsr, pPwd, pInst);

    /* Marca ponto de erro */
    /* EXEC SQL WHENEVER SQLERROR GOTO errConn; */ 


    /* Conecta */
    /* EXEC SQL CONNECT :connString; */ 

{
    struct sqlexd sqlstm;
    sqlstm.sqlvsn = 12;
    sqlstm.arrsiz = 4;
    sqlstm.sqladtp = &sqladt;
    sqlstm.sqltdsp = &sqltds;
    sqlstm.iters = (unsigned int  )10;
    sqlstm.offset = (unsigned int  )246;
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



    oLog.logInformation("<<<DBConnect");

    return (sqlca.sqlcode);

errConn:
    oLog.logInformation("<<<DBConnect [ERROR]");
    return -1;
}

/************************************************************************************************************/
void DBDisconnect(void)
{
    oLog.logInformation(">>>DBDisconnect");

    /* EXEC SQL WHENEVER SQLERROR GOTO DBDisconnectError; */ 

    /* EXEC SQL COMMIT WORK RELEASE; */ 

{
    struct sqlexd sqlstm;
    sqlstm.sqlvsn = 12;
    sqlstm.arrsiz = 4;
    sqlstm.sqladtp = &sqladt;
    sqlstm.sqltdsp = &sqltds;
    sqlstm.iters = (unsigned int  )1;
    sqlstm.offset = (unsigned int  )277;
    sqlstm.cud = sqlcud0;
    sqlstm.sqlest = (unsigned char  *)&sqlca;
    sqlstm.sqlety = (unsigned short)256;
    sqlstm.occurs = (unsigned int  )0;
    sqlcxt((void **)0, &sqlctx, &sqlstm, &sqlfpn);
    if (sqlca.sqlcode < 0) goto DBDisconnectError;
}



    oLog.logInformation("<<<DBDisconnect");
    return;

DBDisconnectError:
    sprintf(szTempTrace, "ERRO ORACLE -> sqlcode=%d,sqlerrmc=%.70s",sqlca.sqlcode,sqlca.sqlerrm.sqlerrmc); oLog.logDebug(szTempTrace);
}

void NomeRelatorio( char * sNmRelatorio )
{
    /* EXEC SQL BEGIN DECLARE SECTION; */ 

        /* VARCHAR oraNmRelatorio[256]; */ 
struct { unsigned short len; unsigned char arr[256]; } oraNmRelatorio;

        short   i_oraNmRelatorio = -1;
    /* EXEC SQL END DECLARE SECTION; */ 

    
    /* EXEC SQL WHENEVER SQLERROR GOTO sqlErrorNomeRelatorio; */ 

    /* EXEC SQL WHENEVER NOT FOUND CONTINUE; */ 


    /* EXEC SQL
    SELECT
       DSVALORPARAMETRO
    INTO
       :oraNmRelatorio:i_oraNmRelatorio
    FROM
       APOIO.PARAMETRO
    WHERE
       CDPARAMETRO = 'RELATDVOL'; */ 

{
    struct sqlexd sqlstm;
    sqlstm.sqlvsn = 12;
    sqlstm.arrsiz = 4;
    sqlstm.sqladtp = &sqladt;
    sqlstm.sqltdsp = &sqltds;
    sqlstm.stmt = "select DSVALORPARAMETRO into :b0:b1  from APOIO.PARAMETRO\
 where CDPARAMETRO='RELATDVOL'";
    sqlstm.iters = (unsigned int  )1;
    sqlstm.offset = (unsigned int  )292;
    sqlstm.selerr = (unsigned short)1;
    sqlstm.cud = sqlcud0;
    sqlstm.sqlest = (unsigned char  *)&sqlca;
    sqlstm.sqlety = (unsigned short)256;
    sqlstm.occurs = (unsigned int  )0;
    sqlstm.sqhstv[0] = (unsigned char  *)&oraNmRelatorio;
    sqlstm.sqhstl[0] = (unsigned long )258;
    sqlstm.sqhsts[0] = (         int  )0;
    sqlstm.sqindv[0] = (         short *)&i_oraNmRelatorio;
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
    if (sqlca.sqlcode < 0) goto sqlErrorNomeRelatorio;
}

   // Constante para busca no banco do nome do relatorio a ser gerado
    
    CONVIND( oraNmRelatorio, i_oraNmRelatorio );
    
    if ( i_oraNmRelatorio != -1 )
    {
        sprintf( sNmRelatorio,"%.*s", oraNmRelatorio.len,(char*)oraNmRelatorio.arr );
    }
    
    return;
    
sqlErrorNomeRelatorio:
    sNmRelatorio[0] = 0x0;
}
