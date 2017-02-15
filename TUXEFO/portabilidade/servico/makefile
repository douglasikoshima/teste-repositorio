#
# $Id: makefile,v 1.1 2009/07/31 15:33:20 a5110702 Exp $
#

SUBDIRS= `ls -l | grep ^d | grep -v CVS | awk '{print $$9}'`

all:
	@for dir in $(SUBDIRS); do \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make all; \
			cd ..; \
		fi \
	done

clean:
	@for dir in $(SUBDIRS); do \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make clean; \
			cd ..; \
		fi \
	done

install:
	@for dir in $(SUBDIRS); do \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make install; \
			cd ..; \
		fi \
	done 

build:
	@for dir in $(SUBDIRS); do \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make; \
			make install; \
			cd ..; \
		fi \
	done
