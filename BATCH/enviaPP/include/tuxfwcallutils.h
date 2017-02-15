/**!
 * 
 * @module  
 * @purpose 
 * @version $Revision: 1.1.6.1 $
 * @CVS     $Author: a5110706 $ - $Date: 2012/08/14 22:23:48 $
 * @ID      $Id: tuxfwcallutils.h,v 1.1.6.1 2012/08/14 22:23:48 a5110706 Exp $
 **/

#ifndef PRXTIMESTAMP_H
#define PRXTIMESTAMP_H

/* ************************************************************************ */
/* Module: prxtimestamp.h													*/
/* Purpose: module to manipulate the common data/time format: YYYYMMDDHHMISS*/
/* Source Control: 															*/
/* History:																	*/
/*	2004/05/24 -  Filipe Filipe	- Versão 0									*/
/* ************************************************************************ */

#include <time.h>

#ifdef __cplusplus
extern "C" {
#endif


#ifdef WIN32
#ifdef PRXTIMESTAMP_DLLIMP
#define PRXTIMESTAMPAPI __declspec(dllexport)
#else
#ifdef PRXTIMESTAMP_DLLREF
#define PRXTIMESTAMPAPI __declspec(dllimport)
#else
#define PRXTIMESTAMPAPI
#endif
#endif
#else
#define PRXTIMESTAMPAPI
#endif


/* return time in format YYYYMMDDHHMMSS */
/* user must free the pointer with free() */
PRXTIMESTAMPAPI char *createTimeStamp(void);
PRXTIMESTAMPAPI char *createTimeStampEx(time_t tim);

PRXTIMESTAMPAPI int createTimeStampStatic(char *timeStr);
PRXTIMESTAMPAPI int createTimeStampStaticEx(char *timeStr, time_t tim);
PRXTIMESTAMPAPI char *createTimeStampDetail(int year, int month, int day, int hour, int min, int sec);
PRXTIMESTAMPAPI char *createTimeStampDetail2(char *dest, int year, int month, int day, int hour, int min, int sec);

PRXTIMESTAMPAPI time_t convertTimeStampTotime_t(char *);

PRXTIMESTAMPAPI void setTimeSettings(void);

#ifdef __cplusplus
}
#endif



#endif




#ifndef NTUNIXTIME_H
#define NTUNIXTIME_H

/* ************************************************************************ */
/* Module: ntunixtime.h														*/
/* Purpose: utilities for time based common functions			 			*/
/* Source Control: 															*/
/* History:																	*/
/*	2004/05/24 -  Filipe Filipe	- Versão 0									*/
/* ************************************************************************ */

#ifndef _WIN32

#include <sys/time.h>
#include <math.h>

#define MYSLEEP(time)		{		\
	struct timeval tv;			\
	tv.tv_sec=(int)floor((double)(time));			\
	tv.tv_usec=(int)( (double) (((double)(time)-(double)floor((double)(time)))*1000.0));		\
	select(0, NULL, NULL, NULL, &tv);	\
};

#define MYDECLARE_RESPONSE_TIME_FIELDS \
	float  responseTime; \
    struct timeval startTime, \
                   endTime, \
                   lapsed; \
    struct timezone timeZone;

#define MYGET_CURRENT_TIME(st) \
       gettimeofday(&st, &timeZone);

#define MYCALCULATE_RESPONSE_TIME() \
       if (startTime.tv_usec > endTime.tv_usec) \
       { \
           endTime.tv_usec += 1000000; \
           endTime.tv_sec--; \
       } \
       lapsed.tv_usec = endTime.tv_usec - startTime.tv_usec; \
       lapsed.tv_sec  = endTime.tv_sec  - startTime.tv_sec; \
       responseTime = (float)lapsed.tv_sec + ((float)lapsed.tv_usec / (float)1000000);


#else
#include <windows.h>
#include "mmsystem.h"
#define MYDECLARE_RESPONSE_TIME_FIELDS \
	float	responseTime; \
	DWORD	startTime, \
			endTime;

#define MYGET_CURRENT_TIME(st) \
	st = timeGetTime();

#define MYCALCULATE_RESPONSE_TIME() \
  responseTime=(float)(DWORD)endTime - (DWORD)startTime; \
  responseTime=(float)responseTime/(float)1000;


#include <stdio.h>
#define MYSLEEP(time)		{								\
	DWORD dword_aux;									\
	dword_aux = (DWORD)(((float)(time))*(float)1000.0);	\
	/*printf("SLEEP %ims\n", dword_aux);*/					\
	Sleep(dword_aux);									\
};

#endif /* _WIN32 */




#define MYGET_START_TIME() MYGET_CURRENT_TIME(startTime)
#define MYGET_END_TIME()   MYGET_CURRENT_TIME(endTime)

#define MYSTART_RESPONSE_TIME() \
{ \
  MYDECLARE_RESPONSE_TIME_FIELDS \
  MYGET_START_TIME()


#define MYEND_RESPONSE_TIME(totalField, msg) \
  MYGET_END_TIME() \
  MYCALCULATE_RESPONSE_TIME() \
  (totalField)+=responseTime; \
}


#define MYEND_RESPONSE_TIME0(totalField, msg) \
  MYGET_END_TIME() \
  MYCALCULATE_RESPONSE_TIME() \
  (totalField)=responseTime; \
}

#endif



#ifndef TUXUTIL_H
#define TUXUTIL_H

/* ************************************************************************ */
/* Module: tuxutil.h														*/
/* Purpose: Tuxedo utilities												*/
/* Source Control: 															*/
/* History:																	*/
/*	2004/05/24 -  Filipe Filipe	- Version 0									*/
/* ************************************************************************ */

#ifdef __cplusplus
extern "C" {
#endif


#ifdef WIN32
#ifdef TUXUTIL_DLLIMP
#define TUXUTILAPI __declspec(dllexport)
#else
#ifdef TUXUTIL_DLLREF
#define TUXUTILAPI __declspec(dllimport)
#else
#define TUXUTILAPI
#endif
#endif
#else
#define TUXUTILAPI
#endif

#include <atmi.h>
#include <fml.h>
#include <fml32.h>

#ifndef MACRO_Ttpadvertise
#ifdef WIN32
//##ModelId=41079D2902F1
typedef int (__stdcall *Ttpadvertise)(char *, void (*)(TPSVCINFO *));
#else
typedef int (*Ttpadvertise)(char *, void (*)(TPSVCINFO *));
#endif
#define MACRO_Ttpadvertise
#endif

TUXUTILAPI int tuxutil_tpcall_noanswer(char *service, char *buffer, long ilen, 
							int timeout, int flags,
							int *tuxerr, char **tuxerrtxt);

TUXUTILAPI int tuxutil_tpcall_noanswerassync(char *service, char *buffer, long ilen, 
								  int timeout, int flags,
								  int *tuxerr, char **tuxerrtxt);

TUXUTILAPI int tuxutil_tpenqueue_noreply(char *qspace, char *qspacebck, char *qname, 
							  char *buffer, long ilen, int timeout, int flags,
							  int *tuxerr, char **tuxerrtxt);

TUXUTILAPI int tuxutil_tpenqueue_noreplyEx(char *qspace0, char *qspace1, char *qname, 
								char *buffer, long ilen, int timeout, int flags,
								char *corrid, char *qreply, char *qfail,
								int *tuxerr, char **tuxerrtxt);
								
TUXUTILAPI int tuxutil_tpenqueue_noreplyTimeEx(	char *qspace0, char *qspace1, char *qname, 
								char *buffer, long ilen, 
								int timeout, int flags, 
								char *corrid, char *qreply, char *qfail,
								char *timeabs, char *timerel,
								int *tuxerr, char **tuxerrtxt);								

TUXUTILAPI int tuxutil_tpenqueue_noreplyTimeEx2(	char *qspace0, char *qspace1, char *qname, 
								char *buffer, long ilen, 
								int timeout, int flags, 
								char *corrid, char *qreply, char *qfail,
								char *timeabs, char *timerel,
								char **destqspace, 
								int *tuxerr, char **tuxerrtxt);

TUXUTILAPI int tuxutil_tpenqueue_noreplyTimeEx3(	char *qspace0, char *qspace1, char *qname, 
								char *buffer, long ilen, 
								int timeout, int flags, 
								char *corrid, char *qreply, char *qfail,
								char *timeabs, char *timerel,
								char **destqspace, char *msgid,
								int *tuxerr, char **tuxerrtxt);

TUXUTILAPI int tuxutil_tpenqueue_noreplyTimeEx4(	char *qspace0, char *qspace1, char *qname, 
								char *buffer, long ilen, 
								int timeout, int flags, int priority,
								char *corrid, char *qreply, char *qfail,
								char *timeabs, char *timerel,
								char **destqspace, char *msgid,
								int *tuxerr, char **tuxerrtxt);

TUXUTILAPI int tuxutil_tpdequeue(	char *qspace0, char *qspace1, char *qname, char **buffer, long *olen, int timeout, 
			int flags, char *msgid, char *corrid,
			int *tuxerr, char **tuxerrtxt);

TUXUTILAPI int tuxutil_tpdequeueEx(	char *qspace0, char *qspace1, char *qname, char **buffer, long *olen, 
			int timeout, int flags, char *msgid, char *corrid, int block,
			int *tuxerr, char **tuxerrtxt);


//##ModelId=41079D2902FD
typedef struct Ttuxutil_dequeue {
	char msgid[33];			/* id of the msg dequeued */
	char corrid[33];       /* correlation identifier used to identify the message */
	char replyqueue[17];   /* queue name for reply */
	char failurequeue[17]; /* queue name for failure */
	int priority;
	char *imsgid, *icorrid, *ireplyqueue, *ifailurequeue;
	int *ipriority;
	long appkey;           /* application authentication client key */
	long urcode;           /* user-return code */
	CLIENTID cltid;        /* client identifier for originating client */
} Ttuxutil_dequeue;

TUXUTILAPI int tuxutil_tpdequeueEx2(	char *qspace0, char *qspace1, char *qname, char **buffer, long *olen, 
			int timeout, int flags, char *msgid, char *corrid, int block, struct Ttuxutil_dequeue *msginfo,
			int *tuxerr, char **tuxerrtxt);

TUXUTILAPI int Fchg32Ex(FBFR32 **fbfr, FLDID32 fieldid, FLDOCC32 oc, char *value, FLDLEN32 len, 
									int *f0, int *v0, int fd, int vd);

TUXUTILAPI int F16to32Ex(FBFR32 **out, FBFR *in, int f0, int v0, int fd, int vd);
TUXUTILAPI int F32to16Ex(FBFR **out, FBFR32 *in, int f0, int v0, int fd, int vd);

TUXUTILAPI int Fconcat32Ex(FBFR32 **out, FBFR32 *in);

TUXUTILAPI char *tuxutil_strdiagnostic(int diag);


/* writes in statusCode & statusText Tuxedo errors:
input: tperrno e diagnostic
output: statusCode & statusText written 
		0 for sucess, <0 for error */
TUXUTILAPI int tuxutil_buildtuxedoerror(int tperr, int diag, char *statusCode, char *statusText);


/* 
deletes all messages in a /Q queue. if logPrefix, dumps logs using userlog
caller must alloc char errString[2048]
returns <0 on error */
TUXUTILAPI int tuxutil_queue_delete_msgs(char *logPrefix, 
										 char *gLMID, char *gQMCONFIG, 
										 char *m_queueSpace, char *m_queue, 
										 char *errString);

/* uses MIB to read a status of a message by msgid */
/* ret=0 (msg not found); ret=1 (msg found); ret<0 (error) */
//##ModelId=41079D2902FF
typedef struct TQMsgStat {
	long priority;
	char msgid[256];
	char msgTime[256];
	char corrid[256];
	long curRetries;
	long msgSize;
} TQMsgStat;
TUXUTILAPI int tuxutil_queue_stat_msg(char *m_LMID, char *m_QMCONFIG, char *m_queueSpace, char *m_queue, char *m_msgid, 
						   struct TQMsgStat *msgstat,
						   char *errString);

TUXUTILAPI int muserlog(char *msg, ...);

/* return 'status':
-1 -error				(ret<0)
0  -no msg to move		(ret=0)
1  -moved ok			(ret=0)
2  -rollback is needed	(ret<0)
3  -file written		(ret=1)
4  -msg lost			(ret<0)
5  -msg unable to move	(ret<0) */
/* 6  -unable to update trace table - action codes */
/* 7  -unable to update trace table */
TUXUTILAPI char *tuxutil_mvmsg_status_txt(int status);
TUXUTILAPI char *tuxutil_mvmsg_status(int status);



TUXUTILAPI int tuxutil_moveQMsg_FML32(char *srcQS, char *srcQN, char *destQS, char *destQSBCK, char *destQN, 
						    char *srcCorrid, char *srcTuxmsgid,
							int timeout, int enqueuetimerel, int srcFlags, int destFlags, char **destqspace, char *tuxmsgid,
							int *doRollback, int *status, int *tuxerr, char **tuxerrtxt);

//##ModelId=41079D29030E
typedef struct Ttuxutil_moveMsg {
	struct Ttuxutil_moveMsg *next;
	char *name;
	char *srcQS;
	char *srcQN;
	char *destQS;
	char *destQSBCK;
	char *destQN;
	int timeout;
	int srcFlags;
	int destFlags;
	int maxcount;
	int tracelevel;
	char *msgType;
} Ttuxutil_moveMsg;

TUXUTILAPI int tuxutil_mvmsg_init_fromenv(struct Ttuxutil_moveMsg **mvmsg, int silent);
TUXUTILAPI int tuxutil_mvmsg_init(struct Ttuxutil_moveMsg **mvmsg, char *configFileName);
TUXUTILAPI int tuxutil_mvmsg_done(struct Ttuxutil_moveMsg *mvmsg);
TUXUTILAPI int tuxutil_mvmsg_loop(struct Ttuxutil_moveMsg *mvmsg, int *doRollback, int *tuxerr, char **tuxerrtxt);


//##ModelId=41079D29031C
typedef struct TMIBService {
	struct TMIBService *next;
	char *name;
} TMIBService;

TUXUTILAPI int tuxutil_mib_stat_services(TMIBService **svcList, char *errString);

#ifdef __cplusplus
}
#endif

#endif



