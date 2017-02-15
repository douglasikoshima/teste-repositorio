/*	Copyright (c) 1997 BEA Systems, Inc.
  	All rights reserved

  	THIS IS UNPUBLISHED PROPRIETARY
  	SOURCE CODE OF BEA Systems, Inc.
  	The copyright notice above does not
  	evidence any actual or intended
  	publication of such source code.
*/

/*	Copyright (c) 1993 USL
	All rights reserved

	THIS IS UNPUBLISHED PROPRIETARY
	SOURCE CODE OF USL
	The copyright notice above does not
	evidence any actual or intended
	publication of such source code.
*/
#ifndef TMENV_H
#define TMENV_H 1

/* #ident	"@(#) gp/libgp/mach/rs6000_ev.h	$Revision: 1.2.2.1 $" */

#ifndef _TMPROTOTYPES
#define _TMPROTOTYPES 1
#endif

#if defined(_TMPROTOTYPES)
#if !defined(_TMCONST)
#define _TMCONST	const
#endif
#else
#define _TMCONST
#endif

#ifndef _IBMR2
#define _IBMR2
#endif

#ifdef _TMPROTOTYPES
#define _(a) a
#else
#define _(a) ()
#endif

#ifndef NOWHAT
static	char	h_tmenv[] = "@(#) gp/libgp/mach/rs6000_ev.h	$Revision: 1.2.2.1 $";
#endif

#define O_BINARY	0

/* This is only used in Windows NT for thread instance data */
#define	_TM_THREADVAR

#define	_TM_FAR
#define	_TM_NEAR
#define _TMDLLENTRY
#define _TM_CDECL

#include <sys/types.h>
#if !defined(_POSIX_SOURCE)
typedef unsigned long	uid_t;
typedef unsigned long	gid_t;
typedef	unsigned long mode_t;
typedef char *caddr_t;
typedef	int pid_t;
typedef	long off_t;
#endif

typedef size_t		isize_t;
typedef uid_t           iuid_t;
typedef gid_t           igid_t;

#define _TMNOCRYPTHDR 1

#define _TMPAGESIZE 	4096

typedef	int		_TMXDRINT;
typedef	unsigned int	_TMXDRUINT;
#ifdef __64BIT__
#include <stdlib.h>
#define _TMLONG64
typedef int    TM32I;
typedef unsigned int TM32U;
#else
typedef	long		TM32I;
typedef	unsigned long	TM32U;
#endif

#define _TMIGW
#define _TMIGWT
#define _TMIBUFT
#define _TMIDNW
#define _TMIFML
#define _TMIFML32
#define _TMIFS
#define _TMIGP
#define _TMINWI
#define _TMINWS
#define _TMIQM
#define _TMIRMS
#define _TMISQL
#define _TMITMIB
#define _TMITUX
#define _TMITUX2
#define _TMITUX2WSC
#define _TMITUXWSC
#define _TMIUSORT
#define _TMIWSC
#define _TMITRPC
#define _TMISERVER

#endif
