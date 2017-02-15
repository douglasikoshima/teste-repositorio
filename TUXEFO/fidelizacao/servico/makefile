#
# $Id: makefile,v 1.1 2009/07/31 15:34:39 a5110702 Exp $
#

SUBDIRS= `ls -l | grep ^d | grep -v CVS | awk '{print $$9}'`

all: config
	@for dir in $(SUBDIRS); do \
	  cd $$dir; \
	  make all; \
	  cd ..; \
	done

clean:
	@for dir in $(SUBDIRS); do \
	  cd $$dir; \
	  make clean; \
	  cd ..; \
	done

inst: install insbin

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

build: config
	@for dir in $(SUBDIRS); do \
	  cd $$dir; \
	  make; \
	  make install; \
	  cd ..; \
	done

config:
	@for dir in $(SUBDIRS); do \
          cd $$dir; \
          if [ ! -a lib ]; then \
            mkdir lib; \
          fi; \
          cd ..; \
        done

	  

