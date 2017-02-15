.SILENT:

APPNAME=cargaBancosPR

BATCH_DIR=$(HOME)/BATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_BIN=$(COMPILE_DIR)/bin
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_INC=$(COMPILE_DIR)/include

COMPILE_COMMONS_COLE=$(HOME)/BATCH/commons/Collection/src
COMPILE_COMMONS_PROP=$(HOME)/BATCH/commons/Propriedade/src
COMPILE_COMMONS_LOG=$(HOME)/BATCH/commons/Log/src

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_CFG=$(EXEC_DIR)/bin
EXEC_LOG=$(EXEC_DIR)/log
EXEC_DATA=$(EXEC_DIR)/data
EXEC_ERROR=$(EXEC_DIR)/error
EXEC_TEMP=$(EXEC_DIR)/temp
EXEC_ENV=$(EXEC_DIR)/enviados

CFLAGS= -L$(ORALIBDIR) -lclntsh -lld -lm -lm -lc_r -lpthreads
export CFLAGS

all:  build

build:$(APPNAME)

clean:
	rm -f $(COMPILE_SRC)/*.o $(COMPILE_SRC)/*.lis $(COMPILE_SRC)/cargaBancosPR.cpp $(COMPILE_BIN)/cargaBancosPR
	rm -r -f $(COMPILE_BIN)


install:
	umask 077
	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi
	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_BIN) ] ; then mkdir $(EXEC_BIN); fi
	if [ ! -d $(EXEC_CFG) ] ; then mkdir $(EXEC_CFG); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(EXEC_DATA) ] ; then mkdir $(EXEC_DATA); fi
	if [ ! -d $(EXEC_ERROR) ] ; then mkdir $(EXEC_ERROR); fi
	if [ ! -d $(EXEC_TEMP) ] ; then mkdir $(EXEC_TEMP); fi
	if [ ! -d $(EXEC_ENV) ] ; then mkdir $(EXEC_ENV); fi
	cp -pf $(COMPILE_DIR)/cargaBancosPR.sh $(EXEC_DIR)
	# copia executavel
	cp -pf $(COMPILE_BIN)/$(APPNAME) $(EXEC_BIN)
	# copia arquivos de configuracao comuns
	cp -pf $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg
	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg
	# copia arquivo de controle da aplicação
	cp -pf $(COMPILE_CFG)/control.ctl $(EXEC_BIN)/control.ctl
	
	chmod 0754 $(EXEC_DIR)/cargaBancosPR.sh

uninstall:
	rm -r -f $(EXEC_DIR)

cargaBancosPR: cargaBancosPR.cpp cargaBancosPR.o Log.o MFile.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	export CC=xlC;	\
	buildclient -w -f " -g " -f $(COMPILE_SRC)/cargaBancosPR.o -f $(COMPILE_SRC)/MFile.o -f $(COMPILE_SRC)/Log.o -f "$(XERCESCPATH)/lib/libxerces-c25.0.a $(PLIBPATH) -lxml2 -ltibems64 -lssl -lcrypto" -o $(COMPILE_BIN)/$(APPNAME)

Log.o: $(COMPILE_COMMONS_LOG)/Log.cpp makefile
	xlC -c $(PINCLUDE) $(COMPILE_COMMONS_LOG)/Log.cpp -o $(COMPILE_SRC)/Log.o

MFile.o: $(COMPILE_COMMONS_PROP)/MFile.cpp makefile
	xlC -c $(PINCLUDE) $(COMPILE_COMMONS_PROP)/MFile.cpp -o $(COMPILE_SRC)/MFile.o

cargaBancosPR.o: $(COMPILE_SRC)/cargaBancosPR.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/cargaBancosPR.cpp -o $(COMPILE_SRC)/cargaBancosPR.o

cargaBancosPR.cpp: $(COMPILE_SRC)/cargaBancosPR.pcpp makefile
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/cargaBancosPR.pcpp