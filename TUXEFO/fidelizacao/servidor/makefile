#
# $Id: makefile,v 1.1.118.1.12.1.18.1 2015/09/21 20:14:54 a5110705 Exp $
#

SUBDIRS= sRETn01 sRETn02 sRETtSAP03 sRETtATL04 sRETn05 sRETt06 sRETt07 sRETn08 sRETn09 sRETt10 sRETt11 sRETt12 sRETt13

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
