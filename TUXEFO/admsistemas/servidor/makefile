#
# $Id: makefile,v 1.1.2.1 2010/12/21 21:16:28 a5114878 Exp $
#

SUBDIRS= sADMt01 \
		sADMt02 \
		sADMt03 \
		sADMt04 \
		sADMt05 \
		sADMt06 \
		sADMt07 \
		sADMt08 \
		sADMt09 \
		sADMn10 \
		sADMn11 \
		sADMn12 \
		sADMt13 \
		sADMt14 \
		sADMt15 \
		sADMt16 \
		sADMt17 \
		sADMn18 \
		sADMn19 \
		sADMn20 \
		sADMn21 \
		sADMn22 \
		sADMt23

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
