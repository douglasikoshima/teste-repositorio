#Compile Tuxedo services
#This makefile generate a executable binary files
.SILENT:
#
# A ordem de compilacao tem que partir do tuxfw, pois os outros aplicativos 
# necessitam deles.
#
SUBDIRS=Collection emailUtil routerLib smsUtil
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
	