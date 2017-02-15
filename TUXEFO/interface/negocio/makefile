#Compile Tuxedo services
#This makefile generate a executable binary files
.SILENT:

#Variable Section
SUBDIRS=VolNegocio commons PlugInBE PlugInATLYS PlugInPPS PlugInNGIN CreatorPlugInBE InfoConta UraPrePago ControleConsumo TuxLog SMS AtualizaCadastro RegistraContato AtendimentoPesquisaURA

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
			make; \
			make install; \
			cd ..; \
		fi \
	done

install:
	echo Installing Enviroment interface
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
