#Compile Tuxedo services
#This makefile generate a executable binary files
.SILENT:
SUBDIRS=`ls -l | grep ^d | grep -v CVS | awk '{print $$9}'`
all:
	cd SenhaBase; \
	make; \
	make install; \
	cd ..; \
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make all; \
		cd ..; \
	done

clean:
	cd SenhaBase; \
	make clean; \
	cd ..; \
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make clean; \
		cd ..; \
	done

install:
	cd SenhaBase; \
	make install; \
	cd ..; \
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make install; \
		cd ..; \
	done 

build:
	cd SenhaBase; \
	make ; \
	make install ; \
	cd ..; \
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make; \
		make install; \
		cd ..; \
	done

package:
	for dir in $(SUBDIRS); do \
		cd $$dir; \
		make package; \
		cd ..; \
	done 

