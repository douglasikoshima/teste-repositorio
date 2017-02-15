#
# $Id: makefile,v 1.1 2009/07/31 15:35:22 a5110702 Exp $
#

#.SILENT:

SUBDIRS= sCLIn02 \
		 sCLIn04 \
		 sCLIn06 \
		 sCLIn07 \
		 sCLIn09 \
		 sCLIn11 \
		 sCLIn13 \
		 sCLIn14 \
		 sCLIn17 \
		 sCLIn19 \
		 sCLIn20 \
		 sCLIn22 \
		 sCLIn24 \
		 sCLIn26 \
		 sCLIn28 \
		 sCLIn30 \
		 sCLIn32 \
		 sCLIn34 \
		 sCLIn35 \
		 sCLIn36 \
		 sCLIn37 \
		 sCLIn38 \
		 sCLIn39 \
		 sCLIn41 \
		 sCLIn42 \
		 sCLIn44 \
		 sCLIt01 \
		 sCLIt03 \
		 sCLIt05 \
		 sCLIt08 \
		 sCLIt10 \
		 sCLIt12 \
		 sCLIt15 \
		 sCLIt16 \
		 sCLIt18 \
		 sCLIt21 \
		 sCLIt23 \
		 sCLIt25 \
		 sCLIt27 \
		 sCLIt29 \
		 sCLIt31 \
		 sCLIt33 \
		 sCLIt40 \
		 sCLIt43

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

insbin:
	@for dir in $(SUBDIRS); do \
	  cd $$dir; \
	  make insbin; \
	  cd ..; \
	done

build:
	@for dir in $(SUBDIRS); do \
	  cd $$dir; \
	  make; \
	  make install; \
	  cd ..; \
	done
