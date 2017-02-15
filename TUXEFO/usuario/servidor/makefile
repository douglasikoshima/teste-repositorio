
SUBDIRS= sUSRt01 \
		sUSRt02 \
		sUSRt03 \
		sUSRt04 \
		sUSRt05 \
		sUSRt06 \
		sUSRt07 \
		sUSRn08 \
		sUSRn09 \
		sUSRn10 \
		sUSRn11 \
		sUSRn12 \
		sUSRn13 \
		sUSRn14 \
		sUSRn15 \
		sUSRt16 \
		sUSRt17 \
		sUSRt18

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
