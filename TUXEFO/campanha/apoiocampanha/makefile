#
# $Id: makefile,v 1.1 2009/07/31 15:34:22 a5110702 Exp $
#

SUBDIRS=`ls -l | grep ^d | awk '{print $$9}'`

all: libs bins

bins:
	@for srv in *.mk; do \
		make -f $$srv; \
	done

libs: 
	@for dir in $(SUBDIRS); do \
		cd $$dir; \
		make; \
		cd ..; \
	done



clean: cl_libs cl_bins

cl_bins:
	@for srv in *.mk; do \
		make -f $$srv clean; \
	done

cl_libs:
	@for dir in $(SUBDIRS); do \
		cd $$dir; \
		make clean; \
		cd ..; \
	done



install: ins_libs ins_bins

ins_libs:
	@for dir in $(SUBDIRS); do \
		cd $$dir; \
		make install; \
		cd ..; \
	done

ins_bins:
	@for srv in *.mk; do \
		make -f $$srv install; \
	done


