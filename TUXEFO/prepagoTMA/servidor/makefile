#
# $Id: makefile,v 1.1 2009/07/31 15:35:18 a5110702 Exp $
#

SUBDIRS= sPREn01 sPREt02

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

