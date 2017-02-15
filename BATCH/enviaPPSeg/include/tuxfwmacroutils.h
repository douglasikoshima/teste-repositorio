/**!
 * 
 * @module  tuxfwmacroutils.h
 * @purpose Several utilities to handle data conversion, copying and presentation
 * @version $Revision: 1.1.6.2 $
 * @CVS     $Author: a5114878 $ - $Date: 2014/08/14 15:45:46 $
 * @ID      $Id: tuxfwmacroutils.h,v 1.1.6.2 2014/08/14 15:45:46 a5114878 Exp $
 **/

//
// Log de BOOT. É o userlog no unix ou console no Windows
#ifdef W32TUXDBG
  #define TUXFW_BOOTSTRAPLOG userlog
#else
  #ifndef _LIB
    #define TUXFW_BOOTSTRAPLOG userlog
  #else
    #define TUXFW_BOOTSTRAPLOG printf("\nbootstrap: ");printf
  #endif
#endif

// Chamada generica para colocação de dados no log do servidor aplicacional
#define TUXFW_ULOG  tuxfw_getlogger()->debug

//
// Macros importadas do FSC
#define stra2strz(strz, stra, stralen) { char *ptr;memcpy(strz, stra, stralen);strz[stralen]=0;ptr=strz+stralen-1;while ((ptr>=strz)&&(isspace(*ptr))){if (isspace(*ptr)) *ptr=0;ptr--;};}
#define stra2int(dest, src, srclen) {char tmp[64+1];memcpy(tmp, src, ((srclen<64)? (srclen):(64)));tmp[((srclen<64)?(srclen):(64))]=0;dest=atoi(tmp);}
#define stra2float(dest, src, srclen) {char tmp[64+1];memcpy(tmp, src, ((srclen<64)? (srclen):(64)));tmp[((srclen<64)?(srclen):(64))]=0;dest=(float)(atof(tmp));}
#define stra2double(dest, src, srclen) {char tmp[64+1];memcpy(tmp, src, ((srclen<64)? (srclen):(64)));tmp[((srclen<64)?(srclen):(64))]=0;dest=atof(tmp);}
#define stra2long(dest, src, srclen) {char tmp[64+1];memcpy(tmp, src, ((srclen<64)? (srclen):(64)));tmp[((srclen<64)?(srclen):(64))]=0;dest=atol(tmp);}
#define mstrcpy(dest, source) {if (source) {dest=(char*)malloc(strlen(source)+1);if (dest) strcpy(dest, source);} else dest=NULL;};
#define mstrcpy0(dest, source) {if (dest) free(dest); if (source) {dest=(char*)malloc(strlen(source)+1); if (dest) strcpy(dest, source);} else dest=NULL;};
#define mastrcpy(dest, src) {if (src) {strncpy(dest, src, sizeof(dest));(dest)[sizeof(dest)-1]=0;} else strcpy(dest,"");}
#define toheapstr(str) { if (str) {char *a; a=(char*)malloc(strlen(str)+1); if (a) {strcpy(a, str); (str)=a;} else (str)=NULL;};}
#define mstrfree(str) { if (str) {free(str); (str)=NULL;};}
#define LVL_LONG(val,defval) (((val)==-1)?(defval):(val))
#define mstrlen(str) ( (str) ? (strlen(str)) : 0 )
#define ptraddstr(ptr,str) {if ((ptr)&&(str)) {int l=strlen(str); memcpy(ptr,str,l); ptr+=l;};}
#define ptrtermstr(ptr) {if(ptr) {(*(ptr))=0; (ptr)++;};}
#define mnvl(s) ( (s)?(s):"" )
#define cstrcpy(dest,src) {if (dest) {if (src) strcpy(dest,src); else strcpy(dest,"");};}

//
// helper macro functions
#define CleanArray(ptr)	{if(ptr) {free(ptr);}}
#define CL_FETCH(ifh)		{ifh.arr[ifh.len]=0;}
#define TO_VARCHAR(VAR,VAL)	{strcpy((char*)VAR.arr,VAL);VAR.len=strlen(VAL);}	
#define INITIALIZE_SQL		struct sqlca sqlca;sqlca.sqlcode=0
//
//
// Stubs de ativação do framework
#if defined(WIN32)

#define TUXSVC "WIN32"
#else
#define TUXSVC ""
#endif

#define DECLARE_TUXEDO_SERVICE(TUXSVCNM)	\
	class impl##TUXSVCNM: public TuxBasicSvc	\
	{	\
	public:	\
		impl##TUXSVCNM(char*TuxSvcNm):TuxBasicSvc(TuxSvcNm){};	\
		void Execute(DOMNode*XMLIn,XMLGen*XMLOut);	\
	};	\
	IMPL_TUX_SVC(TUXSVCNM)

/*!
 * Cria uma chamada main, apenas na plataforma Windows, 
 * usada para auxiliar o desenvolvimento de servicos
 * permitindo acesso local ao Banco de dados e logs.
 */
//#if defined(WIN32)
#if (defined(WIN32))&&(!defined(W32TUXDBG))
#include<conio.h>
#define IMPL_TUX_SVC(TUXSVCNM)	\
	int main(int ac,char**av)	\
	{	\
		char*pret;int len,stmt;	\
		FILE*fo;	\
		if(ac!=2)	\
		{	\
			printf("Usage: %s [XML File]\n",av[0]);	\
			getch();return 0;	\
		}	\
		fo=fopen(av[1],"rb");	\
		if(!fo)	\
		{	\
			printf("FAILED: Cannot open %s file.\n",av[1]);	\
			getch();return 0;	\
		}	\
		if (tpsvrinit(ac, av)==1) { \
			try { \
				impl##TUXSVCNM _svctux##TUXSVCNM(TUXSVC "TuxSvc" #TUXSVCNM);	\
				{	\
					TPSVCINFO tpi;	\
					char cbf[20*TUXFW_MAX_MSG_LEN];	\
					fseek (fo , 0 , SEEK_END); \
					long fileLen = ftell (fo); \
					rewind (fo); \
					tpi.len=fread(cbf,1,fileLen,fo);	\
					tpi.data=cbf;	\
					tpi.data[tpi.len]=0;	\
					fclose(fo);	\
  					/*printf("\nXMLIn: %s\nTamanho msg entrada %d",tpi.data, tpi.len);*/	\
					stmt=_svctux##TUXSVCNM.callService(&tpi,&pret,&len,#TUXSVCNM);	\
					/*printf("\nXMLOut:\n%s\nLength: %d\nReturnCode: %d\n",pret,len,stmt);*/	\
					free(pret);	\
				}	\
			} catch(...) { \
				printf("CRITICAL:service activation failed in service %s", #TUXSVCNM); \
			} \
			tpsvrdone(); \
		} \
		getch();return 0;\
	}
#else //W32TUXDBG 
#define IMPL_TUX_SVC(TUXSVCNM)	\
	extern"C"void TUXSVCNM(TPSVCINFO*tpsvc)	\
	{ \
			char *pret; \
			int len = 0; \
			int stmt = 0;	\
		try { \
			impl##TUXSVCNM _svctux##TUXSVCNM(TUXSVC "TuxSvc" #TUXSVCNM);	\
			stmt=_svctux##TUXSVCNM.callService(tpsvc,&pret,&len,#TUXSVCNM);	\
			tpreturn(stmt,0L,pret,len,0);	\
			tpfree(pret); \
			userlog("DEBUG|fim de servico|%s|%d|%d", #TUXSVCNM, stmt,len ); \
		} catch(...) { \
			/* Tratamento forçar falha de chamada Tuxedo e derrubar o server */ \
			userlog("CRITICAL:service activation failed in service %s", #TUXSVCNM ); \
		    char msg[] = "<msg><msgHdr><statusCode>99E9999</statusCode><statusText>Exceção não tratada atingiu chamada principal.</statusText><msgHdr><msgBody/></msg>"; \
			len = strlen(msg)+1; \
			pret = tpalloc("STRING",0L,len+1); \
			if ( pret != NULL )  \
			{ \
				memcpy(pret,msg,len); \
				(pret)[len]=0; \
			} \
			tpreturn(TPEXIT,0L,pret,len,0);	\
			tpfree(pret); \
		} \
	}
#endif