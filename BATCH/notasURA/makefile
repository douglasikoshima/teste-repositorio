.SILENT:

APPNAME=notasUra

BATCH_DIR=$(HOME)/BATCH

COMPILE_DIR=$(BATCH_DIR)/notasURA
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_BIN=$(COMPILE_DIR)/bin
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_INC=$(COMPILE_DIR)/include

COMPILE_COMMONS_COLE=$(HOME)/BATCH/commons/Collection/src
COMPILE_COMMONS_PROP=$(HOME)/BATCH/commons/Propriedade/src
COMPILE_COMMONS_LOG=$(HOME)/BATCH/commons/Log/src

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos

EXEC_DIR=$(PROCESSOS_DIR)/notasURA
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_CFG=$(EXEC_DIR)/bin
EXEC_LOG=$(EXEC_DIR)/log
EXEC_DTA=$(EXEC_DIR)/data


CFLAGS= -L$(ORALIBDIR) -lclntsh -lld -lm -lm -lc_r -lpthreads
export CFLAGS

all:  build

build:$(APPNAME)

clean:
	rm -f $(COMPILE_SRC)/*.o $(COMPILE_SRC)/*.lis $(COMPILE_SRC)/notasUra.cpp $(COMPILE_BIN)/notasUra
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
	if [ ! -d $(EXEC_DTA) ] ; then mkdir $(EXEC_DTA); fi
	# copia executa.sh
	cp -p $(COMPILE_DIR)/notasUra.sh $(EXEC_DIR)
	chmod 0754 $(EXEC_DIR)/notasUra.sh
	# copia executavel
	cp -p $(COMPILE_BIN)/notasUra $(EXEC_BIN)
	# copia arquivos de configuracao comuns
	cp -p $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg
	# copia arquivo de configuracao da aplicacao
	cp -p $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg

uninstall:
	rm -r -f $(EXEC_DIR)


notasUra: notasUra.cpp notasUra.o Log.o MFile.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	export CC=xlC;	\
	buildclient -w -f " -g " -f $(COMPILE_SRC)/notasUra.o -f $(COMPILE_SRC)/MFile.o -f $(COMPILE_SRC)/Log.o -f "$(XERCESCPATH)/lib/libxerces-c25.0.a" -o $(COMPILE_BIN)/notasUra


Log.o: $(COMPILE_COMMONS_LOG)/Log.cpp makefile
	xlC -c $(PINCLUDE) $(COMPILE_COMMONS_LOG)/Log.cpp -o $(COMPILE_SRC)/Log.o

MFile.o: $(COMPILE_COMMONS_PROP)/MFile.cpp makefile
	xlC -c $(PINCLUDE) $(COMPILE_COMMONS_PROP)/MFile.cpp -o $(COMPILE_SRC)/MFile.o

notasUra.o: $(COMPILE_SRC)/notasUra.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/notasUra.cpp -o $(COMPILE_SRC)/notasUra.o

notasUra.cpp: $(COMPILE_SRC)/notasUra.pcpp makefile
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/notasUra.pcpp
