SUBDIRS=TotemSenha

all:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make all; \
		cd ..; \
	done

clean:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make clean; \
		cd ..; \
	done

install:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make install; \
		cd ..; \
	done 

build:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make; \
		make install; \
		cd ..; \
	done
