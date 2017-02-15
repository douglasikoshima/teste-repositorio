#ifndef ROUTERCOMMONS
#define ROUTERCOMMONS

#define HASLOG

#define ST_NONE								0x00000000
#define ST_EXECUTE							0x00000001
#define ST_TRANSFORM						0x00000002
#define ST_SELECT							0x00000004
#define ST_IGNORE							0x00000008
#define ST_EXCEPTION						0x00000010
#define ST_SOLVE							0x00000020
#define ST_PRINT							0x00000040
#define ST_CONDSOLVE						0x00000080
#define ST_COMMAND							0x00000100
#define ST_EXTRNCALL						0x00000200
#define ST_FEXTRNCALL						0x00000400
#define ST_RECORDSET						0x00000800
#define ST_RETURN							0x00001000

#define ST_RS_NONE							0x00000000
#define ST_RS_GETVAL						0x00000001
#define ST_RS_MOVENEXT						0x00000002
#define ST_RS_MOVEPREV						0x00000003
#define ST_RS_MOVELAST						0x00000004
#define ST_RS_MOVEFIRS						0x00000005
#define ST_RS_EOF							0x00000006
#define ST_RS_BOF							0x00000007
#define ST_RS_ROWCOUNT						0x00000008
#define ST_RS_GETIND						0x00000009
#define ST_RS_GETERROR						0x0000000A
#define ST_RS_COLCOUNT						0x0000000B
#define ST_RS_NEG							0x01000000

#define AC_NONE								0x00000000
#define AC_XMLGEN							0x00000001
#define AC_LIBFNC							0x00000002
#define AC_UNKNOWN							0x00000003
#define AC_CUSTTYPE							0x00000004
#define AC_DOMNODE							0x00000005
#define AC_STRING							0x00000006
#define AC_INTEGER							0x00000007
#define AC_CURSOR							0x00000008
#define AC_ROUTERSTEP						0x00000009
#define AC_ROUTERCOND						0x0000000A
#define AC_ROUTERTASKCOND					0x0000000B
#define AC_SQLIND							0x0000000C
#define AC_STREAM							0x0000000D
#define AC_XMLGENR							0x0000000E
#define AC_RECORDSET						0x0000000F

#define AC_TYPE_NONE						0x00000000
#define AC_TYPE_DESTROY						0x00000001
#define AC_TYPE_DISCARDABLE 				0x00000002
#define AC_TYPE_AUTOMOUNT					0x00000003
#define AC_TYPE_MOUNT						0x00000004

#define TK_NONE								0x00000000
#define TK_STEP								0x00000001
#define TK_COND								0x00000002
#define TK_LOOP								0x00000003
#define TK_BREAK							0x00000004

#define TC_OPNONE							0x00000000
#define TC_OPEQUALS							0x00000001
#define TC_OPGREAT							0x00000002
#define TC_OPLESS							0x00000003
#define TC_OPDIFF							0x00000004
#define TC_OPEXISTS							0x00000005
#define TC_OPNEXISTS						0x00000006

#define OP_NONE								0x00000000
#define OP_CODE								0x00000001
#define OP_COND								0x00000002
#define OP_LOOP								0x00000003
#define OP_DRCT								0x00000005
#define OP_RETN								0x00000006

#define OP_TR_NONE							0x00000000
#define OP_TR_ADD							0x00000001
#define OP_TR_DFF							0x00000002
#define OP_TR_MLT							0x00000003
#define OP_TR_DIV							0x00000004
#define OP_TR_RPL							0x00000005

#define OP_MT_NONE							0x00000000
#define OP_MT_ADD							0x0000002B
#define OP_MT_DFF							0x0000002D
#define OP_MT_MLT							0x0000002A
#define OP_MT_DIV							0x0000002F

#define OP_CMD_NONE							0x00000000
#define OP_CMD_GETHDRDT						0x00000001
#define OP_CMD_CLEAN						0x00000002
#define OP_CMD_TRIM							0x00000003
#define OP_CMD_RTRIM						0x00000004
#define OP_CMD_LTRIM						0x00000005
#define OP_CMD_OPENRS						0x00000006
#define OP_CMD_PRINTLN						0x00000007
#define OP_CMD_MID							0x00000008
#define OP_CMD_LEN							0x00000009

#define THROW_ADD_ONLY						0x00000000
#define THROW_THIS_ONLY						0x00000001
#define THROW_ALL							0x00000002
#define THROW_INFO							0x00000003

#define REI_NONE							0x00000000
#define REI_WARNING							0x00000001
#define REI_ERROR							0x00000002
#define REI_CRITICAL						0x00000003
#define REI_INFO							0x00000004

#define RXI_NONE							0x00000000
#define RXI_OR								0x0000007C
#define RXI_AND								0x00000026

#define CUSTSIZE							0x00000400
#define MAXADJSIZE							0x0000000C
#define MAXREQUEST							0x00000006

#define RLP_ERR_NONE						0x00000000
#define RLP_ERR_FILENOTFOUND				0x0000000A
#define RLP_ERR_INTERNALFAILED				0x0000000B
#define RLP_ERR_LIBCANNOTBEUNLOADED			0x0000000C
#define RLP_ERR_LIBCANNOTBEOPEN				0x0000000D

#define RLI_NONE							0x00000000
#define RLI_SOLVE							0x00000009
#define RLI_RS								0x0000000A
#define RLI_EXPR							0x0000000B

#define RS_NONE								0x00000000
#define RS_NOTFOUND							0xffffffff
#define RS_HAVEERRORS						0x00000001

#define REI_PARSE							cbREIType[0]
#define REI_RUNTIME							cbREIType[1]
#define REI_RELEASE							cbREIType[2]
#define REI_WARNINGT						cbREIType[3]
#define REI_CODEWR							cbREIType[4]

#define REI_EPARSE(A,B)						prei->AddMessage(REI_PARSE,A,0L,-1,B,THROW_ADD_ONLY)
#define REI_ERUNTIME(A,B)					prei->AddMessage(REI_RUNTIME,A,0L,-1,B,THROW_THIS_ONLY)
#define WARNING(A)							prei->AddMessage(REI_WARNINGT,A,0L,-1,REI_WARNING,THROW_ADD_ONLY)
#define REI_CODEERR(A)						prei->AddMessage(REI_CODEWR,A,__FILE__,__LINE__,REI_ERROR,THROW_ALL)

#define SZADJ								0x00014370
#ifdef __cplusplus
typedef void(*_fncCall)(PARAMS*,ACCUM*,THROWIND*);
#endif
typedef void*HLIB;
typedef void*HFUNC;

#define BUILD_SIGNATURE(a,b,c)char cSignature[]="Router Service ver " #a "." #b " - SSKlunk Interface - Build " #c;\
char cVersion[]=#a "." #b " " #c;int iMinor=##b;int iMajor=##a;int iBuild=##c;

#ifdef WIN32
#define RTINT_SZ sizeof(int)
#define RTINT int
#else
#define RTINT_SZ sizeof(long)
#define RTINT long
#endif
#include<tuxfw.h>

#define MMAllocator(SZ,CHR) malloc(SZ)
#define MMFree(UK) free(UK)

extern char cSO[];
extern char cSignature[];
extern char cVersion[];
extern char cType[];
extern int iMinor;
extern int iMajor;
extern int iBuild;

#endif