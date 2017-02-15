#ifndef ROUTERCLASS
#define ROUTERCLASS

#define ROUTERACCUMIMPL			RouterAccumImpl
#define ROUTERTRACEIMPL			RouterTraceImpl
#define ROUTERLIBMANIMPL		RouterLibManImpl
#define ROUTERLIBIMPL			RouterLibImpl
#define AUXDOMIMPL				AuxDOMImpl
#define ROUTERSVCMAN			RouterSvcMan
#define ROUTERSVC				RouterSvc
#define ROUTERIMPL				RouterImpl
#define ROUTERCODEIMPL			RouterCodeImpl
#define ROUTERTASKIMPL			RouterTaskImpl
#define ROUTERSTEPIMPL			RouterStepImpl
#define ROUTERSTEPSTOCKIMPL		RouterStepStockImpl
#define ROUTERFUNCTIONCALLIMPL	RouterFunctionCallImpl
#define ROUTERTRANSFORMIMPL		RouterTransformImpl
#define ROUTERVARNAMEIMPL		RouterVarNameImpl
#define ROUTERLOOPIMPL			RouterLoopImpl
#define ROUTERCURSORIMPL		RouterCursorImpl
#define ROUTERSTRINGIMPL		RouterStringImpl
#define ROUTERXMLIMPL			RouterXMLImpl
#define ROUTERSTREAMIMPL		RouterStreamImpl
#define ROUTERAUX				RouterAux
#define ROUTERCONTROL			RouterControl
#define ROUTEREXTERNCALLIMPL	RouterExternCallImpl
#define ROUTERCOMMANDIMPL		RouterCommandImpl
#define ROUTERERRORIMPL			RouterErrorImpl
#define ROUTERRECORDSETIMPL		RouterRecordsetImpl
#define ROUTERASSEMBLEIMPL		RouterAssembleImpl
#define ROUTERBASELOOPIMPL		RouterBaseLoopImpl
#define ROUTERWHILEIMPL			RouterWhileImpl
#define ROUTEREXPRESSIONIMPL	RouterExpressionImpl
#define ROUTERPATROLIMPL		RouterPatrolImpl
#define ROUTERDOMIMPL			RouterDOMImpl
#define ROUTERMATHIMPL			RouterMathImpl
#define ROUTERLOGIMPL			RouterLogImpl

struct ACCUM;
struct THROWIND;
struct PARAMS;
struct COLUMN;
struct LIBACCUM;
struct ACCUMSTOCK;
struct ERRORQUEUE;
struct CELL;
struct ROW;
struct COL;
struct RS;
struct EXPRESSIONQ;
struct EXPRESSIONQL;
struct ITEM;
class AuxDOMImpl;
class RouterAccumImpl;
class RouterLibImpl;
class RouterLibManImpl;
class RouterSvcMan;
class Router;
class RouterImpl;
class RouterCodeImpl;
class CodeUnit;
class CodeUnitCond;
class RouterTaskImpl;
class RouterTaskCondImpl;
class RouterSentenceImpl;
class RouterStepStockImpl;
class RouterStepImpl;
class RouterTraceImpl;
class RouterTransformImpl;
class RouterVarNameImpl;
class RouterHelper;
class RouterFunctionCallImpl;
class RouterLoopImpl;
class RouterCursorImpl;
class RouterStringImpl;
class RouterXMLImpl;
class RouterStreamImpl;
class RouterControl;
class RouterExternCallImpl;
class RouterCommandImpl;
class RouterErrorImpl;
class RouterRecordsetImpl;
class RouterAssembleImpl;
class RouterBaseLoopImpl;
class RouterWhileImpl;
class RouterExpressionImpl;
class RouterPatrolImpl;
class RouterDOMImpl;
class RouterMathImpl;
class RouterLogImpl;

#include"RouterCommons.h"
#include"Token.h"

/*******************
*      Struct      *
*******************/
struct THROWIND
{
	char cid[28];
	char cmsg[1024];
	char id;
};

struct PARAMS
{
	PARAMS():pcount(0),parmlist(0L){};
	int pcount;
	ACCUM**parmlist;
};

struct ACCUM
{
	ACCUM()
	{var.othr=0L;vartype=AC_NONE;};
	ACCUM&operator=(ACCUM*accum)
	{var.othr=accum->var.othr;vartype=accum->vartype;varsize=accum->varsize;return*this;}
	ACCUM&operator=(ACCUM accum)
	{var.othr=accum.var.othr;vartype=accum.vartype;varsize=accum.varsize;return*this;}
	union
	{
		XMLGen*xml;
		AuxDOMImpl*dom;
		_fncCall fnc;
		char*pstr;
		RTINT i32;
		void*othr;
	}var;
	int vartype;
	int varsize;
};

struct COLUMN
{
	COLUMN();
	~COLUMN();
	COLUMN*AddNewColumn();
	char*cname;
	RouterAccumImpl*paccum;
	char*pbf;
	int clrType;
	COLUMN*nextColumn;
};

struct PARMLST
{
	int vartype;
	ACCUM*accum;
};

struct LIBACCUM
{
	LIBACCUM(){pfncName=0L;fnc=0L;};
	char*pfncName;
	_fncCall fnc;
};

struct ACCUMSTOCK
{
	ACCUMSTOCK();
	ACCUMSTOCK(RouterAccumImpl*);
	~ACCUMSTOCK();
	ACCUMSTOCK*AddNewACCUMSTOCK();
	ACCUMSTOCK*AddNewACCUMSTOCK(RouterAccumImpl*);
	RouterAccumImpl*accum;
	ACCUMSTOCK*next;
};

struct ERRORQUEUE
{
	ERRORQUEUE();
	~ERRORQUEUE();
	ERRORQUEUE*AddNewERRORQUEUE();
	char*pmsg;
	char ind;
	ERRORQUEUE*next;
};

struct CELL
{
	CELL();
	~CELL();
	union
	{
		char*pstr;
		RTINT i32;
		void*othr;
	}var;
	char vInd;
};

struct ROW
{
	ROW();
	~ROW();
	ROW*AddNewROW();
	CELL*cel;
	ROW*next;
	ROW*prev;
};

struct COL
{
	COL();
	~COL();
	char*pstr;
	char ind;
};

struct RS
{
	RS();
	~RS();
	char*pstr;
	int cind;
	ROW*row;
	COL*col;
};

struct EXPRESSIONQ
{
	char*pv;
	int ind;
	EXPRESSIONQ*next;
};

struct EXPRESSIONQL
{
	EXPRESSIONQL();
	~EXPRESSIONQL();
	RouterSentenceImpl*prsi;
	int ind;
};

struct ITEM
{
	ITEM();
	~ITEM();
	char*pnm;
	ITEM*next;
};

/*******************
*       Class      *
*******************/
class RouterHelper
{
public:
	RouterHelper(RouterAccumImpl*,RouterTraceImpl*);
	int DeclareVar(char*);
	DOMNode*RetrieveNode(DOMNode*,char*,int*,int);
	char*RetrieveNodeText(DOMNode*,char*);
	int IsRecordset(char*);
	static int IsSolve(char*);
	static int IsCommand(char*);
protected:
	int MakeParam(char*,RouterAccumImpl**);
	int ParseSolveW(char*,RouterAccumImpl***,RouterTraceImpl*);
private:
	int IsDistinct(char*);
	RouterAccumImpl*rootAccum;
	RouterTraceImpl*ptrace;
};

class RouterLibImpl
{
public:
	RouterLibImpl(char*,RouterTraceImpl*);
	~RouterLibImpl();
	void*GetInstance(char*);
	int LoadFunction(char*);
	RouterLibImpl*nextLib;
	RouterLibImpl*RetrieveLibInstance(char*);
	RouterLibImpl*AddNewLib(char*);
private:
	HLIB hLib;
	char*pLib;
	RouterAccumImpl*paccum;
	RouterTraceImpl*ptrace;
};

class RouterLibManImpl
{
public:
	RouterLibManImpl(RouterTraceImpl*);
	~RouterLibManImpl();
	int GetInstance(char*,RouterAccumImpl*);
	int MakeInstance(char*);
private:
	RouterLibImpl*plib;
	RouterTraceImpl*ptrace;
};

class AuxDOMImpl: public TuxHelper
{
public:
	AuxDOMImpl(char*);
	AuxDOMImpl(DOMNode*);
	~AuxDOMImpl();
	int Bad();
	DOMNode*RetrieveDOM();
	int AttachDOM(DOMNode*);
	int SetRoot(char*);
	int SetText(char*);
private:
	int CleanAndDestroy();
	XercesDOMParser*xml_p;
	MemBufInputSource*mbis;
	DOMNode*pnode;
};

class CodeUnitCond
{
public:
	CodeUnitCond();
	~CodeUnitCond();
	char*clause;
	CodeUnitCond*pucondT;
	CodeUnitCond*pucondF;
	CodeUnit*pcunit;
};

class CodeUnit
{
public:
	CodeUnit();
	~CodeUnit();
	union
	{
		char*pcmd;
		CodeUnitCond*code;
	}cmd;
	int optype;
	CodeUnit*nextUnit;
	CodeUnit*AddNewUnit();
};

class RouterCodeImpl
{
public:
	RouterCodeImpl();
	~RouterCodeImpl();
	CodeUnit*pcunit;
	int Assemble(char*);
private:
	int Assemble();
	int ProcessIFDirective(CodeUnit*);
	int ProcessWHILEDirective(CodeUnit*);
	char*GetBlockLimit(char*);
	int GetBlockLimitAdj(char*);
	char*GetLocalBlockLimitAdj(char*,char*);
	char*GetCommentLimitAdj(char*);				//TR21
	char*psrc;
};

class RouterFunctionCallImpl
{
public:
	RouterFunctionCallImpl(RouterTraceImpl*);
	~RouterFunctionCallImpl();
	int SetFunction(RouterAccumImpl*);
	int AddParam(RouterAccumImpl*);
	int Execute();
	int SetReturn(RouterAccumImpl*);
private:
	ACCUMSTOCK*paccum;
	RouterAccumImpl*raccum;
	RouterAccumImpl*fncCall;
	RouterTraceImpl*ptrace;
	PARAMS*params;
};

class RouterImpl: public RouterHelper
{
public:
	RouterImpl(void*);
	~RouterImpl();
	int SetupSession(DOMNode*,XMLGen*);
	int Assemble(char*);
	int Execute();
private:
	int XChrBootleg();
	RouterTaskImpl*ptask;
	RouterLibManImpl*plib;
	RouterAccumImpl*paccum;
	RouterTraceImpl*ptrace;
	RouterCodeImpl*pcode;
	char tron;
	char owner;
	void*pv;
};

class RouterStepStockImpl
{
public:
	RouterStepStockImpl(RouterLibManImpl*,RouterAccumImpl*,RouterTraceImpl*);
	~RouterStepStockImpl();
	int Assemble(CodeUnit*);
	int Assemble(char*);
	RouterStepStockImpl*Execute();
	RouterStepStockImpl*AddNewStepStock();
private:
	int HasMacro(char*);
	RouterStepImpl*pstep;
	RouterLoopImpl*ploop;
	RouterTaskCondImpl*ptaskc;
	RouterStepStockImpl*nextStockStep;
	RouterAccumImpl*rootAccum;
	RouterLibManImpl*plib;
	RouterTraceImpl*ptrace;
	char type;
};

class RouterStepImpl: public RouterHelper
{
public:
	RouterStepImpl(RouterLibManImpl*,RouterAccumImpl*,RouterTraceImpl*);
	~RouterStepImpl();
	int Assemble(char*);
	RouterStepImpl*Execute();
	RouterStepImpl*AddNewStep();
private:
	int ParsePrintStep(char*);
	int ParseExecuteStep(char*);
	int ParseTransformStep(char*);
	int ParseSQLStep(char*);
	int ParseExceptionStep(char*);
	int ParseSolveStep(char*);
	int ParseConditionalSolveStep(char*);
	int ParseExternCallStep(char*);
	int ParseFlowStep(char*,char);
	int ParseCommandStep(char*);
	int ParseIncDecStep(char*);
	int ParseRecordsetStep(char*);
	char*QueryExternCode(char*,int);
	int QueryScriptFlowByID(int);
	int QueryScriptIDByName(char*);
	int stype,clrType,sstype;
	RouterStepImpl*nextStep;
	RouterAccumImpl**paccum;
	RouterCursorImpl*pcur;
	RouterFunctionCallImpl*pfnc;
	RouterAccumImpl*rootAccum;
	RouterLibManImpl*plib;
	RouterTraceImpl*ptrace;
	RouterExternCallImpl*pextn;
	RouterCommandImpl*prci;
	int rac_ind;
	char tranftype;
	char retind;
};

class Router
{
public:
	Router(DOMNode*,XMLGen*,void*);
	~Router();
	int Execute();
	int Assemble(char*);
private:
	RouterImpl*rimpl;
	void*pv;
};

class RouterSvcMan: public TuxHelper
{
public:
	RouterSvcMan();
	~RouterSvcMan();
	char*RetrieveSource();
	int Parse(DOMNode*);
private:
	int QueryDB(char**,int);
	char*src;
};

class RouterTuxFWReader: public TuxBasicSvc
{
public:
	char*readHdrAttr(void*,char*);
};

class RouterSentenceImpl: public RouterHelper
{
public:
	RouterSentenceImpl(RouterLibManImpl*,RouterAccumImpl*,RouterTraceImpl*);
	~RouterSentenceImpl();
	int Execute();
	int Assemble(char*);
private:
	int op;
	RouterStepStockImpl*psstep;
	RouterAccumImpl**paccum;
	RouterAccumImpl*rootAccum;
	RouterTraceImpl*ptrace;
	RouterLibManImpl*plib;
};

class RouterTaskCondImpl
{
public:
	RouterTaskCondImpl(RouterLibManImpl*,RouterAccumImpl*,RouterTraceImpl*);
	~RouterTaskCondImpl();
	int Execute();
	int Assemble(CodeUnitCond*);
private:
	int RunNode();
	RouterLibManImpl*plib;
	RouterAccumImpl*rootAccum;
	RouterStepStockImpl*pstepT;
	RouterStepStockImpl*pstepF;
	RouterTaskCondImpl*ptci;
	RouterExpressionImpl*pexp;
	RouterTraceImpl*ptrace;
	char ind;
};

class RouterTaskImpl
{
public:
	RouterTaskImpl(RouterLibManImpl*,RouterAccumImpl*,RouterTraceImpl*);
	virtual ~RouterTaskImpl();
	int Assemble(char*);
	int Assemble(CodeUnitCond*);
	RouterTaskImpl*Execute();
	RouterTaskImpl*AddNewTask();
private:
	int ttype;
	RouterStepStockImpl*pstep;
	RouterTaskCondImpl*pcond;
	RouterLoopImpl*pdnli;
	RouterLibManImpl*plib;
	RouterAccumImpl*rootAccum;
	RouterTaskImpl*nextTask;
	RouterTraceImpl*ptrace;
	void*pv;
};

class RouterTraceImpl
{
public:
	RouterTraceImpl();
	int DumpAccum(RouterAccumImpl*);
	int DumpText(char*);
	int DumpVText(char*,...);
	int PrintAccum(RouterAccumImpl*);
	int PrintText(char*);
	int SetDebug(){return dbg=1;}
	int SetTrace(){return ind=1;};
	int UnsetCheck(){return chk=0;};
	int HasCheck(){return chk;};
	int HasTrace(){return ind;};
	int HasDebug(){return dbg;};
protected:
	int PrintHeader();
private:
	int AdjSize(char*);
	char ind,chk,dbg;
};

class RouterTransformImpl: public RouterHelper
{
public:
	RouterTransformImpl(RouterAccumImpl*);
	int CastToString(RouterAccumImpl*,char);
	int CastToInteger(RouterAccumImpl*,char);
	int CastFromXMLGenToDOMNode(RouterAccumImpl*);
	int CastFromDOMNodeToXMLGen(RouterAccumImpl*,char);
	int CopyFromXMLToXML(RouterAccumImpl*,char);
	int CastFromIntToXML(RouterAccumImpl*);
	int CastFromCharToXML(RouterAccumImpl*,char);
	int CastFromDOMNODEToStringOrInteger(RouterAccumImpl*,char*,char);
	int CastToStream(RouterAccumImpl*,char);
	int CastFromStreamToXML(RouterAccumImpl*,char);
	int CastFromStreamToDOMNode(RouterAccumImpl*);
	int CastFromStringToDOMNode(RouterAccumImpl*);
	int ReplaceEscapeSequence();
	static int HasEscapeSequence(RouterAccumImpl*);
private:
	RouterAccumImpl*paccum;
};

class RouterVarNameImpl
{
public:
	RouterVarNameImpl(RouterTraceImpl*);
	~RouterVarNameImpl();
	char*VarName(int);
private:
	char pvname[15];
	RouterTraceImpl*ptrace;
};

class RouterAccumImpl
{
public:
	RouterAccumImpl();
	RouterAccumImpl(RouterAccumImpl*);
	~RouterAccumImpl();
	RouterAccumImpl&operator=(RouterAccumImpl*);
	RouterAccumImpl&operator=(RouterAccumImpl&);
	int OperatorIsEquals(RouterAccumImpl*);
	int OperatorIsGreat(RouterAccumImpl*);
	int OperatorIsLess(RouterAccumImpl*);
	int OperatorIsNotEquals(RouterAccumImpl*);
	int Attach(RouterAccumImpl*);
	ACCUM descriptor;
	int flags,sz;
	char varNm[15];
	RouterAccumImpl*AddNewAccum();
	RouterAccumImpl*AddNewAccum(RouterAccumImpl*);
	RouterAccumImpl*RetrieveNextAccum();
	RouterAccumImpl*RetrieveVar(char*);
	int MakeVar(char*,int);
	int MakeVar(char*,char*);
	int CleanAndDestroy();
	int AddRef(){return ref++;};
	int ReleaseRef(){return ref--;};
	int GetRef(){return ref;};
private:
	RouterAccumImpl*nextAccum;
	RouterTraceImpl*ptrace;
	int ref;
};

class RouterCursorImpl:public RouterHelper
{
public:
	RouterCursorImpl(RouterAccumImpl*,RouterTraceImpl*);
	~RouterCursorImpl();
	int Assemble(char*);
	int Assemble(char*,int);
	int Execute();
	int Execute(RouterRecordsetImpl*);
private:
	int ORAExecute(void*,void*);
	int ORAExecuteAdj(void*,void*,RouterRecordsetImpl*);
	int AssembleCols(void*,void**,short**);
	int AssembleColsAdj(void*,void**,short**,RouterRecordsetImpl*);
	int AssembleWhere(void**,short**);
	int ReadSelectListAdj(char*);
	int CountWhere();
	int CountSelList();
	int AssembleVars();
	int AssembleRecordsetVar(RouterRecordsetImpl*);
	int Prepare();
	char*SeekFROMAdj(char*);
	char*SeekWHEREAdj(char*);
	char*SeekCommaAdj(char*);
	int IsSQL(char*);
	int AdjustWHEREAdj();
	int IsSQLSep(char*);
	int SetWHEREAdj(char*);
	int AddColumnItem(char*);
	int AdjustSize(int);
	int SQLDAAllocator(void**,int);
	int SQLDAFree(void*);
	int NVL(char*);
	int MakeInd();
	char*sqlclause;
	int CommitSQL(char*,char*,char**,char**,char,int*);
	RouterAccumImpl*rootAccum;
	RouterAccumImpl*psqlInd;
	RouterTraceImpl*ptrace;
	COLUMN*pcol;
	int cc,adind;
	int sqlcd;
	COLUMN*wclause;
};

class RouterStringImpl
{
public:
	char*RTrim(char*);
	int MakeString(RouterAccumImpl*,char*);
	int MakeString(RouterAccumImpl*,int);
	int AppendString(RouterAccumImpl*,char*);
	int AdjustSize(RouterAccumImpl*);
	static char*_RTrim(char*);
	static char*_LTrim(char*);
	static char*_GetStringLimitAdj(char*,char);		//TR8
	static char*_GetStringLimitAdj(char*);			//TR36
	static char*_FindFromLimitAdj(char*);			//TR9
	static char*_FindFromCode(char*,char*);			//TR31
	static char*_SeekRelativesLmtAdj(char*);		//TR35
	static char*_TRIfBlockAdj(char*);
	static int _IsNumeric(char*);
	static int _HasIncDec(char*);
	static int _IsTransform(char*);
	static int _IsExternCall(char*);
	static int _Encode(XMLGen*);					//TR36
};

class RouterXMLImpl:public XMLGen
{
public:
	int AddSpace();
	int AttachText(char*);
};

class RouterStreamImpl: public RouterStringImpl
{
public:
	RouterStreamImpl(RouterTraceImpl*);
	~RouterStreamImpl();
	int SetText(char*);
	int AppendText(char*);
	char*GetText();
	int GetLength();
	int SetLength(int);
	int SetEOT();
	int MoveCursor(int);
	int Truncate();
	int RTrim();
	int LTrim();
private:
	int GetNewSizeAdj(int);
	int Allocator();
	int Allocator(int);
	int Allocator(int,int,int);
	int AttachText(char*,int,int);
	int CheckSpace(int,int);
	RouterTraceImpl*ptrace;
	char*pstr;
	int varsz,varusz,rsz;
};

class RouterControl
{
public:
	RouterControl(void*,char*);
	~RouterControl();
	int AttachParams(DOMNode*,XMLGen*);
	int Dispatch();
private:
	int CleanAndDestroy();
	int EnterContext();
	int ExitContext();
	RouterSvcMan*prsm;
	Router*pr;
	void*pv;
	char*pc;
};

class RouterExternCallImpl: public RouterHelper
{
public:
	RouterExternCallImpl(RouterAccumImpl*,RouterTraceImpl*);
	~RouterExternCallImpl();
	int Assemble(char*);
	int SetVars(char*,char*);
	int SetInd();
	int Execute();
private:
	RouterAccumImpl*rootAccum;
	RouterTraceImpl*ptrace;
	RouterAccumImpl**paccum;
	AuxDOMImpl*padi;
	XMLGen*pxml;
	Router*pr;
	char*pcode;
	char ind;
	char op;
};

class RouterCommandImpl: public RouterHelper
{
public:
	RouterCommandImpl(RouterAccumImpl*,RouterTraceImpl*);
	~RouterCommandImpl();
	int Assemble(char*);
	int Execute();
private:
	int BeforeExecute();
	int AfterExecute();
	int ParseGetHdrData(char*);
	int ParseClean(char*);
	int ParseTrim(char*);
	int ParseOpenRS(char*);
	int ParsePrintln(char*);
	int ParseMid(char*);
	int ParseLen(char*);
	int Validate(char*);
	int cmdind;
	int varInd;
	char op;
	RouterAccumImpl*rootAccum;
	RouterTraceImpl*ptrace;
	RouterAccumImpl**paccum;
	RouterTaskImpl*pbtask;
	RouterTaskImpl*patask;
};

class RouterErrorImpl
{
public:
	RouterErrorImpl(RouterTraceImpl*);
	~RouterErrorImpl();
	int HasErrors();
	int HasWarnings();
	int HasCriticals();
	int SetScriptName(char*);
	int Throw();
	int AddMessage(char*,char*,char*,int,char,char);
private:
	RouterTraceImpl*ptrace;
	ERRORQUEUE*peq;
	char*pbf;
	char*scpt;
	int iec;
	int iwc;
	int icc;
	int aclen;
};

class RouterRecordsetImpl
{
public:
	RouterRecordsetImpl(RouterTraceImpl*);
	~RouterRecordsetImpl();
	int MakeColumns(int);
	int SetColumnName(char*,int);
	int FindFromColumnID(char*);
	char GetValue(int,void**);
	char GetValue(char*,void**);
	RouterAccumImpl*GetValue(char*);
	RouterAccumImpl*GetValue(int);
	int EOR();
	int BOR();
	int MoveNext();
	int MovePrevious();
	int MoveLast();
	int MoveFirst();
	int AddNewRow();
	int SetValues();
	char*GetColBuffInd(int,int,char);
	int GetColCount();
	int GetRowCount();
	int SetInd(RouterAccumImpl*);
	int GetInd();
private:
	RouterAccumImpl*iaccum;
	RouterAccumImpl*caccum;
	RouterAccumImpl*sqlind;
	RouterTraceImpl*ptrace;
	RS*rs;
	COL*bf;
	ROW*rw;
	ROW*ri;
	int ic;
	int ir;
	int ieof;
	int ibof;
};

class RouterAssembleImpl
{
public:
	RouterAssembleImpl(RouterAccumImpl*,RouterTraceImpl*);
	int _Assemble(CodeUnit*,RouterLibManImpl*,RouterTaskImpl**);
private:
	RouterAccumImpl*paccum;
	RouterTraceImpl*ptrace;
};

class RouterBaseLoopImpl
{
public:
	int _Execute();
protected:
	virtual int _rbliExecute()=0;
	virtual int _rbliGetInd()=0;
	virtual int _rbliBefore();
	virtual int _rbliAfter();
};

class RouterWhileImpl:public RouterBaseLoopImpl
{
public:
	RouterWhileImpl(RouterAccumImpl*,RouterTraceImpl*,RouterLibManImpl*);
	~RouterWhileImpl();
	int _Assemble(CodeUnitCond*);
private:
	int _rbliExecute();
	RouterAccumImpl*rootAccum;
	RouterTraceImpl*ptrace;
	RouterLibManImpl*plib;
	RouterTaskImpl*ptask;
};

class RouterLoopImpl: public RouterHelper, public RouterWhileImpl
{
public:
	RouterLoopImpl(RouterLibManImpl*,RouterAccumImpl*,RouterTraceImpl*);
	~RouterLoopImpl();
	int Bad();
	int Execute();
	DOMNode*GetNext();
	DOMNode*GetFirst();
	int Assemble(CodeUnitCond*);
private:
	int _rbliGetInd();
	int _rbliBefore();
	RouterAccumImpl*rootAccum;
	RouterLibManImpl*plib;
	RouterTraceImpl*ptrace;
	RouterAccumImpl**paccum;
	RouterExpressionImpl*prexi;
	DOMNode*pdnd;
	AuxDOMImpl*adi;
	char ind;
	int actItem;
	int sstype;
};

class RouterExpressionImpl:public RouterHelper
{
public:
	RouterExpressionImpl(RouterLibManImpl*,RouterAccumImpl*,RouterTraceImpl*);
	~RouterExpressionImpl();
	int Assemble(char*);
	int Execute();
private:
	int AddItem(char*,int);
	int GetItem(char**);
	int FindOperator(char*,char**);
	int HasOperator(char*);
	RouterAccumImpl*rootAccum;
	RouterTraceImpl*ptrace;
	RouterLibManImpl*plib;
	EXPRESSIONQL**psent;
	int noSent;
	EXPRESSIONQ*rxi,*lrxi;
};

class RouterPatrolImpl
{
public:
	RouterPatrolImpl();
	~RouterPatrolImpl();
	int Push(char*);
	int Pop();
	int RetrieveCallStack(char*,int);
private:
	ITEM*pitem;
};

class RouterDOMImpl
{
public:
	RouterDOMImpl(AuxDOMImpl*);
	~RouterDOMImpl();
	char*GetText();
private:
	int Execute(AuxDOMImpl*);
	RouterStreamImpl rsi;
};

class RouterLogImpl:public RouterTraceImpl
{
public:
	RouterLogImpl();
	~RouterLogImpl();
	int WriteLog(char*);
};

/*******************
*     Externals    *
*******************/
#ifdef WIN32
extern char*_MMDerivStr(char*,char*);
#define derivStr(A) _MMDerivStr(A,#A)
#else
extern char*derivStr(char*);
#endif
extern RouterVarNameImpl*prvni;
extern RouterErrorImpl*prei;
extern RouterPatrolImpl*prpi;
extern int sqlInd;
extern char*cbREIType[];

#ifdef HASLOG
extern RouterLogImpl __rli;
#endif

#endif

