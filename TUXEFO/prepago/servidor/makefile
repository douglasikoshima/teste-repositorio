#
# $Id: makefile,v 1.1.2.1.102.1.18.1 2015/09/21 20:14:54 a5110705 Exp $
#

SUBDIRS= srvprepago srvPPINSPES srvproccad sPPt01 sPPt02

all:
	@for dir in $(SUBDIRS); do \
	  cd $$dir; \
	  make all 2>make.err; \
	  cd ..; \
	done

clean:
	@for dir in $(SUBDIRS); do \
	  cd $$dir; \
	  make clean; \
	  cd ..; \
	done

install:
	@for dir in $(SUBDIRS); do \
	  cd $$dir; \
	  make install; \
	  cd ..; \
	done

build:
	@for dir in $(SUBDIRS); do \
	  cd $$dir; \
	  make; \
	  make install; \
	  cd ..; \
	done
