.SILENT:

APPNAME=cargaCatalogo

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


CFLAGS= -L$(ORALIBDIR) -lclntsh -lld -lm -lm -lc_r -lpthreads
export CFLAGS

all:  build

build:$(APPNAME)

clean:
	rm -f $(COMPILE_SRC)/*.o $(COMPILE_SRC)/*.lis $(COMPILE_SRC)/cargaCatalogo.cpp $(COMPILE_BIN)/cargaCatalogo
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

	# copia executa.sh
	cp -pf $(COMPILE_DIR)/executa.sh $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/cargaCatalogo.sh $(EXEC_DIR)
	# copia executavel
	cp -pf $(COMPILE_BIN)/$(APPNAME) $(EXEC_BIN)
	# copia arquivos de configuracao comuns
	cp -pf $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg
	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg
	# copia arquivo de controle da aplicação
	cp -pf $(COMPILE_CFG)/cargaCatalogo.ctl $(EXEC_CFG)/cargaCatalogo.ctl
	
	chmod 0754 $(EXEC_DIR)/executa.sh
	chmod 0754 $(EXEC_DIR)/cargaCatalogo.sh
	chmod 0777 $(EXEC_DIR)/log
	chmod 0777 $(EXEC_DIR)/data
	chmod 0754 $(EXEC_BIN)/cargaCatalogo.cfg
	chmod 0754 $(EXEC_BIN)/cargaCatalogo.ctl

uninstall:
	rm -r -f $(EXEC_DIR)

cargaCatalogo: cargaCatalogo.cpp cargaCatalogo.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	export CC='xlC -g';	\
	buildclient -w -f " -g " -f $(COMPILE_SRC)/cargaCatalogo.o -o $(COMPILE_BIN)/$(APPNAME)

cargaCatalogo.o: $(COMPILE_SRC)/cargaCatalogo.cpp makefile
	xlC -g -c -I$(COMPILE_INC) $(PINCLUDE) $(COMPILE_SRC)/cargaCatalogo.cpp -o $(COMPILE_SRC)/cargaCatalogo.o

cargaCatalogo.cpp: $(COMPILE_SRC)/cargaCatalogo.pcpp makefile
	proc include=$(TUXDIR)/include  parse=none cpp_suffix=cpp code=cpp $(COMPILE_SRC)/cargaCatalogo.pcpp
	