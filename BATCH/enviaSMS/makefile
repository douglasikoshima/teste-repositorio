.SILENT:

APPNAME=enviaSMS

BATCH_DIR=$(HOME)/BATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_BIN=$(COMPILE_DIR)/bin
COMPILE_CFG=$(COMPILE_DIR)/cfg

COMPILE_COMMONS_PROP=$(HOME)/BATCH/commons/Propriedade/src
COMPILE_COMMONS_LOG=$(HOME)/BATCH/commons/Log/src

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_CFG=$(EXEC_DIR)/bin
EXEC_LOG=$(EXEC_DIR)/log


CFLAGS= -L$(ORALIBDIR) -lclntsh -lld -lm -lm -lc_r -lpthreads
export CFLAGS

all:  build

clean:
	rm -f $(COMPILE_SRC)/*.o $(COMPILE_SRC)/*.lis $(COMPILE_SRC)/enviaSMS.cpp $(COMPILE_BIN)/enviaSMS
	rm -r -f $(COMPILE_BIN)

build:$(APPNAME)

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
	# copia executa.sh
	cp -p $(COMPILE_DIR)/executa.sh $(EXEC_DIR)
	cp -p $(COMPILE_DIR)/enviaSMS.sh $(EXEC_DIR)
	# copia executavel
	cp -p $(COMPILE_BIN)/$(APPNAME) $(EXEC_BIN)
	# copia arquivos de configuracao comuns
	cp -p $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg
	# copia arquivo de configuracao da aplicacao
	cp -p $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg
	# QUEBRA GALHO : copia para arquivo de configuracao com nome fora do padrao
	cp -p $(EXEC_CFG)/$(APPNAME).cfg $(EXEC_CFG)/sms.cfg


uninstall:
	rm -r -f $(EXEC_DIR)


enviaSMS: enviaSMS.o MFile.o Log.o
	if [ ! -d $(COMPILE_BIN) ] ; then mkdir $(COMPILE_BIN); fi
	buildclient -w -f " -g " -f $(COMPILE_SRC)/enviaSMS.o -f $(COMPILE_SRC)/MFile.o -f $(COMPILE_SRC)/Log.o -o $(COMPILE_BIN)/enviaSMS 

Log.o:
	xlc -g -I$(TUXDIR)/include -I$(ORACLE_HOME)/precomp/public -c $(COMPILE_COMMONS_LOG)/Log.cpp -o $(COMPILE_SRC)/Log.o

MFile.o:
	xlc -g -I$(TUXDIR)/include -I$(ORACLE_HOME)/precomp/public -c $(COMPILE_COMMONS_PROP)/MFile.cpp -o $(COMPILE_SRC)/MFile.o

enviaSMS.o: enviaSMS.cpp 
	xlc -g -I$(TUXDIR)/include -I$(ORACLE_HOME)/precomp/public -c $(COMPILE_SRC)/enviaSMS.cpp -o $(COMPILE_SRC)/enviaSMS.o


enviaSMS.cpp:
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/enviaSMS.pcpp

