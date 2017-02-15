
APPNAME=ValidarLinhaCPF

BATCH_DIR=$(HOME)/BATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_BIN=$(COMPILE_DIR)/bin
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_INC=$(COMPILE_DIR)/include
COMPILE_LIB=$(COMPILE_DIR)/lib

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_CFG=$(EXEC_DIR)/bin
EXEC_TMP=$(EXEC_DIR)/tmp
EXEC_LOG=$(EXEC_DIR)/log
EXEC_DAT=$(EXEC_DIR)/data
EXEC_ENV=$(EXEC_DIR)/enviados

CC=xlc -w -g
CXX=xlC -w -g
CFLAGS=-q64
CXXFLAGS=-q64 -qrtti=all -brtl 
LDFLAGS=-L$(BATCH_DIR)/commons/gc/lib -L$(COMPILE_LIB) -L$(ORACLE_HOME)/lib -L$(ORACLE_HOME)/rdbms/lib 
CPPFLAGS=-DAIX -D_AIX -I$(BATCH_DIR)/commons/gc/include -I$(COMPILE_INC) -I$(ORACLE_HOME)/rdbms/public
LIBS=-lgc -lgccpp -locci -lc -lm -lbsd -ldl -lpthread 
KERNEL_BITS=64
OBJECT_MODE=64

all: build

build: $(APPNAME)

clean:
	rm -f $(COMPILE_SRC)/ValidarLinhaCPF.o $(COMPILE_BIN)/ValidarLinhaCPF
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
	if [ ! -d $(EXEC_TMP) ] ; then mkdir $(EXEC_TMP); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(EXEC_DAT) ] ; then mkdir $(EXEC_DAT); fi
	if [ ! -d $(EXEC_ENV) ] ; then mkdir $(EXEC_ENV); fi

	# copia ValidarLinhaCPF.sh
	cp -pf $(COMPILE_DIR)/ValidarLinhaCPF.sh $(EXEC_DIR)

	# copia executavel
	cp -pf $(COMPILE_BIN)/$(APPNAME) $(EXEC_BIN)

	# copia arquivos de configuracao comuns
	cp -pf $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg

	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg

	# seta permissoes
	chmod a+rwx $(EXEC_DIR)/ValidarLinhaCPF.sh
	chmod a+rw $(EXEC_CFG)/$(APPNAME).cfg
	chmod a+rwx $(EXEC_TMP) $(EXEC_LOG) $(EXEC_DAT) $(EXEC_ENV)

uninstall:
	rm -r -f $(EXEC_DIR)

ValidarLinhaCPF: CValidarLinhaCPF.o
	if ! [ -d $(COMPILE_BIN) ]; then mkdir $(COMPILE_BIN); fi 
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(LDFLAGS) $(LIBS) \
		$(COMPILE_SRC)/CUtil.o \
		$(COMPILE_SRC)/CLock.o \
		$(COMPILE_SRC)/CException.o \
		$(COMPILE_SRC)/CLog.o \
		$(COMPILE_SRC)/CConfig.o \
		$(COMPILE_SRC)/CValidarLinhaCPF.o \
		$(COMPILE_SRC)/main.cpp -o $(COMPILE_BIN)/$(APPNAME)

CValidarLinhaCPF.o: \
CConfig.o \
CLog.o \
CException.o \
CLock.o \
CUtil.o \
	$(COMPILE_SRC)/CValidarLinhaCPF.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CValidarLinhaCPF.cpp -c -o $(COMPILE_SRC)/CValidarLinhaCPF.o

CConfig.o: $(COMPILE_SRC)/CConfig.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CConfig.cpp -c -o $(COMPILE_SRC)/CConfig.o

CLog.o: $(COMPILE_SRC)/CLog.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CLog.cpp -c -o $(COMPILE_SRC)/CLog.o

CException.o: $(COMPILE_SRC)/CException.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CException.cpp -c -o $(COMPILE_SRC)/CException.o

CLock.o: $(COMPILE_SRC)/CLock.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CLock.cpp -c -o $(COMPILE_SRC)/CLock.o

CUtil.o: $(COMPILE_SRC)/CUtil.cpp
	$(CXX) $(CXXFLAGS) $(CPPFLAGS) $(COMPILE_SRC)/CUtil.cpp -c -o $(COMPILE_SRC)/CUtil.o

