#Compile Tuxedo services
#This makefile generate a executable binary files
.SILENT:

#Variable Section
SUBDIRS=BUSCAPORCEP SETIMAGEM PESQSATISFACAO SONDA TROCASENHA TuxProxyBE URSPPONTOS VALSENHAID VALSENHAFXID VALSENHASEMID UREXPONTOS BLOQSENHA IMGCONTA GRAVAPREPAGO GETSTATUSLN CONSUMO TUXLOGBE SMSENVIA SMSSTATUS TUXATLYSBE TUXNGINBE TUXPREPAGOBE TUXINTERBE GETTIPOLINHA TUXATLYSURA GETBANNER GETPARBANNER PESQBANNER SETBANNER CADPREPAGO GETEMAIL GRVNOVOPRE RSVAPARELHO BuscaGrupoAtendimento

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
