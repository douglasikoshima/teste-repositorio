.SILENT:

APPNAME=enviarEmailProtocolo

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


CFLAGS= -L$(ORALIBDIR) -lclntsh -lld -lm -lm -lc_r -lpthreads
export CFLAGS

all:  build

build:$(APPNAME)

clean:
	rm -f $(COMPILE_SRC)/*.o $(COMPILE_SRC)/*.lis $(COMPILE_SRC)/enviarEmailProtocolo.cpp $(COMPILE_BIN)/enviarEmailProtocolo
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

	# copia executa.sh
	cp -pf $(COMPILE_DIR)/executa.sh $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/enviarEmailProtocolo.sh $(EXEC_DIR)
	# copia executavel
	cp -pf $(COMPILE_BIN)/$(APPNAME) $(EXEC_BIN)
	# copia arquivos de configuracao comuns
	cp -pf $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg
	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg
	
	chmod 0777 $(EXEC_DIR)/executa.sh
	chmod 0777 $(EXEC_DIR)/enviarEmailProtocolo.sh
	chmod 0777 $(EXEC_DIR)/log
	chmod 0777 $(EXEC_DIR)/bin
	chmod 0777 $(EXEC_CFG)/$(APPNAME).cfg
	chmod 0777 $(EXEC_BIN)/enviarEmailProtocolo

uninstall:
	rm -r -f $(EXEC_DIR)

enviarEmailProtocolo: enviarEmailProtocolo.cpp enviarEmailProtocolo.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	export CC=xlC;	\
	buildclient -w -f " -g " -f $(COMPILE_SRC)/enviarEmailProtocolo.o -f "$(XERCESCPATH)/lib/libxerces-c25.0.a $(PLIBPATH) -lxml2 -ltibems64 -lssl -lcrypto" -o $(COMPILE_BIN)/$(APPNAME)

enviarEmailProtocolo.o: $(COMPILE_SRC)/enviarEmailProtocolo.cpp makefile
	xlC -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/enviarEmailProtocolo.cpp -o $(COMPILE_SRC)/enviarEmailProtocolo.o

enviarEmailProtocolo.cpp: $(COMPILE_SRC)/enviarEmailProtocolo.pcpp makefile
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/enviarEmailProtocolo.pcpp
	