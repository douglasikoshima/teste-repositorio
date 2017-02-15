#Compile Tuxedo services
#This makefile generate a executable binary files
.SILENT:
#Variable Section
SUBDIRS=negocio servico servidor

all:
	echo Making Enviroment interface
	for dir in $(SUBDIRS); do \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make ; \
			cd ..; \
		fi \
	done

build:
	echo Building Enviroment interface
	for dir in $(SUBDIRS); do \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make build; \
			cd ..; \
		fi \
	done

install:
	echo Installing Enviroment interface
    @-cp $(LIBDIR)/lib$(SUBDIR).so $(LIBCLI) \
	for dir in $(SUBDIRS); do \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make install; \
			cd ..; \
		fi \
	done
	
clean:
	echo Limpando todos os Objetos e Binarios do Modulo INTERFACE
	for dir in $(SUBDIRS); do \
		if ! [ $$dir = "CVS" ]; then \
			cd $$dir; \
			make clean; \
			cd ..; \
		fi \
	done
