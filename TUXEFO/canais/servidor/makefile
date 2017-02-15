#Compile Tuxedo services
#This makefile generate a executable binary files
.SILENT:

#Variable Section
SUBDIRS=TAVLOJA srvCanais sCNAn01 sCNAt01 sCNAt02 sCNAn02 sCNAn03

all:
	echo Making Enviroment canais
	for dir in $(SUBDIRS); do \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make ; \
			cd ..; \
		fi \
	done

build:
	echo Building Enviroment canais
	for dir in $(SUBDIRS); do \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make; \
			make install; \
			cd ..; \
		fi \
	done

install:
	echo Installing Enviroment canais
	for dir in $(SUBDIRS); do \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make install; \
			cd ..; \
		fi \
	done
	
clean:
	echo Limpando todos os Objetos e Binarios do Modulo CANAIS
	for dir in $(SUBDIRS); do \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make clean; \
			cd ..; \
		fi \
	done
