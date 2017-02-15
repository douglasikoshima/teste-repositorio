SUBDIRS= sCMPt01 \
		sCMPt02 \
		sCMPt03 \
		sCMPt04 \
		sCMPt05 \
		sCMPt06 \
		sCMPt07 \
		sCMPn08 \
		sCMPn09 \
		sCMPn10 \
		sCMPn11 \
		sCMPn12 

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
