#Compile Tuxedo services
#This makefile generate a executable binary files
.SILENT:

#Variable Section
SUBDIRS=srvUra TuxProxy InfoConta UraPrePago TuxConsumo TuxLog srvSMS TuxATLYS TuxInter TuxNGIN TuxPREPAGO srvURIN TuxATLYSURA srvBanner UraPreCred sURAn01 sRSVt01 sINTn01 sINTn03

all:
	echo Making Enviroment interface
	for dir in $(SUBDIRS); do \
		if test -d $$dir; then \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make ; \
			cd ..; \
		fi \
		fi \
	done

build:
	echo Building Enviroment interface
	for dir in $(SUBDIRS); do \
		if test -d $$dir; then \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make; \
			make install; \
			cd ..; \
		fi \
		fi \
	done

install:
	echo Installing Enviroment interface
	for dir in $(SUBDIRS); do \
		if test -d $$dir; then \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make install; \
			cd ..; \
		fi \
		fi \
	done
	
clean:
	echo Limpando todos os Objetos e Binarios do Modulo INTERFACE
	for dir in $(SUBDIRS); do \
		if test -d $$dir; then \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make clean; \
			cd ..; \
			fi \
		fi \
	done
