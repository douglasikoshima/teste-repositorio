.SILENT:

APPNAME=blacklistdados

BATCH_DIR=$(HOME)/BATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_BIN=$(COMPILE_DIR)/bin
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_INC=$(COMPILE_DIR)/include

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_CFG=$(EXEC_DIR)/bin
EXEC_LOG=$(EXEC_DIR)/log
EXEC_DATA=$(EXEC_DIR)/data
EXEC_PRC=$(EXEC_DIR)/prc

CFLAGS= -L$(ORALIBDIR) -lclntsh -lld -lm -lm -lc_r -lpthreads
export CFLAGS

all:  build

build:$(APPNAME)

clean:
	rm -f $(COMPILE_SRC)/*.o $(COMPILE_SRC)/*.lis $(COMPILE_SRC)/blacklistdados.cpp $(COMPILE_BIN)/blacklistdados
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
	if [ ! -d $(EXEC_PRC) ] ; then mkdir $(EXEC_PRC); fi

	# copia executa.sh
	cp -pf $(COMPILE_DIR)/blacklistdados.sh $(EXEC_DIR)
	# copia executavel
	cp -pf $(COMPILE_BIN)/$(APPNAME) $(EXEC_BIN)
	# copia arquivos de configuracao comuns
	cp -pf $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg
	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg
	# copia arquivo de controle da aplicação
	cp -pf $(COMPILE_CFG)/blacklistdados.ctl $(EXEC_CFG)/blacklistdados.ctl
	
	chmod 0755 $(EXEC_CFG) 
	chmod 0777 $(EXEC_DATA)
	chmod 0777 $(EXEC_PRC)
	chmod 0755 $(EXEC_LOG)
	chmod 0755 $(EXEC_BIN)
	chmod 0755 $(EXEC_DIR)/blacklistdados.sh
	chmod 0754 $(EXEC_CFG)/blacklistdados.ctl
	chmod 0754 $(EXEC_CFG)/$(APPNAME).cfg

uninstall:
	rm -r -f $(EXEC_DIR)

blacklistdados: blacklistdados.cpp blacklistdados.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	export CC=xlC;	\
	buildclient -w -f " -g " -f $(COMPILE_SRC)/blacklistdados.o -o $(COMPILE_BIN)/$(APPNAME)

blacklistdados.o: $(COMPILE_SRC)/blacklistdados.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/blacklistdados.cpp -o $(COMPILE_SRC)/blacklistdados.o

blacklistdados.cpp: $(COMPILE_SRC)/blacklistdados.pcpp makefile
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/blacklistdados.pcpp
