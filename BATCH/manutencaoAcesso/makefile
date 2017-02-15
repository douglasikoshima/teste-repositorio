.SILENT:

APPNAME=manutencaoAcesso

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

CFLAGS= -L$(ORALIBDIR) -lhu -lclntsh -lld -lm -lm -lc_r -lpthreads
export CFLAGS

all:  build

build:$(APPNAME)

clean:
	rm -f $(COMPILE_SRC)/*.o $(COMPILE_SRC)/*.lis $(COMPILE_SRC)/manutencaoAcesso.cpp $(COMPILE_BIN)/manutencaoAcesso
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
	# copia executa.sh
	cp -pf $(COMPILE_DIR)/executa.sh $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/manutencaoAcesso.sh $(EXEC_DIR)
	# copia executavel
	cp -pf $(COMPILE_BIN)/$(APPNAME) $(EXEC_BIN)
	# copia arquivos de configuracao comuns
	cp -pf $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg
	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg
	# copia arquivo de controle da aplicação
	cp -pf $(COMPILE_CFG)/control.ctl $(EXEC_BIN)/control.ctl
	
	chmod 0755 $(EXEC_DIR)
	chmod 0755 $(EXEC_BIN)
	chmod 0755 $(EXEC_CFG) 
	chmod 0777 $(EXEC_DATA)
	chmod 0777 $(EXEC_ERROR)
	chmod 0755 $(EXEC_LOG)
	chmod 0754 $(EXEC_DIR)/executa.sh
	chmod 0754 $(EXEC_DIR)/manutencaoAcesso.sh
	chmod 0664 $(EXEC_CFG)/$(APPNAME).cfg

uninstall:
	rm -r -f $(EXEC_DIR)

manutencaoAcesso: manutencaoAcesso.cpp manutencaoAcesso.o Log.o MFile.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	export CC=xlC;	\
	buildclient -w -f " -g " -f $(COMPILE_SRC)/manutencaoAcesso.o -f $(COMPILE_SRC)/MFile.o -f $(COMPILE_SRC)/Log.o -f "$(XERCESCPATH)/lib/libxerces-c25.0.a $(PLIBPATH) -lxml2 -ltibems64 -lssl -lcrypto" -o $(COMPILE_BIN)/$(APPNAME)

Log.o: $(COMPILE_COMMONS_LOG)/Log.cpp makefile
	xlC -c $(PINCLUDE) $(COMPILE_COMMONS_LOG)/Log.cpp -o $(COMPILE_SRC)/Log.o

MFile.o: $(COMPILE_COMMONS_PROP)/MFile.cpp makefile
	xlC -c $(PINCLUDE) $(COMPILE_COMMONS_PROP)/MFile.cpp -o $(COMPILE_SRC)/MFile.o

manutencaoAcesso.o: $(COMPILE_SRC)/manutencaoAcesso.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/manutencaoAcesso.cpp -o $(COMPILE_SRC)/manutencaoAcesso.o

manutencaoAcesso.cpp: $(COMPILE_SRC)/manutencaoAcesso.pcpp makefile
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/manutencaoAcesso.pcpp