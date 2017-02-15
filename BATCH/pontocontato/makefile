APPNAME=pontocontato

BATCH_DIR=$(HOME)/BATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_BIN=$(COMPILE_DIR)/bin
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_CFG=$(EXEC_DIR)/cfg
EXEC_LOG=$(EXEC_DIR)/log
DATA_DIR=$(EXEC_DIR)/data

all:  build

clean:
	rm -r -f $(EXEC_DIR)

build:	VerificaPontoContato $(APPNAME)

uninstall:
	rm -r -f $(EXEC_DIR)

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
	if [ ! -d $(DATA_DIR) ] ; then mkdir $(DATA_DIR); fi
	cp -f $(COMPILE_SRC)/load_$(APPNAME).`hostname`.`whoami`.sh $(EXEC_BIN)/load_$(APPNAME).sh
	cp -f $(COMPILE_CFG)/VerificaPontoContato.`hostname`.`whoami`.cfg $(EXEC_CFG)/VerificaPontoContato.cfg
	cp -f $(COMPILE_SRC)/preload_pontocontato.sh $(EXEC_BIN)/preload_pontocontato.sh
	cp -f $(COMPILE_CFG)/load_pontocontato.ctl $(EXEC_BIN)/load_pontocontato.ctl
	cp -f $(COMPILE_CFG)/load_pontocontato.par $(EXEC_BIN)/load_pontocontato.par
	cp -f $(COMPILE_CFG)/preload.sql $(EXEC_BIN)/preload.sql
	cp -f $(COMPILE_CFG)/posload.sql $(EXEC_BIN)/posload.sql
	cp -f $(COMPILE_CFG)/errload.sql $(EXEC_BIN)/errload.sql
	cp -f $(COMPILE_CFG)/falhaload.sql $(EXEC_BIN)/falhaload.sql
	cp -f $(COMPILE_SRC)/executa.sh $(EXEC_DIR)/executa.sh
	cp -f $(COMPILE_SRC)/pontocontato.sh $(EXEC_DIR)/pontocontato.sh
	cp -f $(COMPILE_BIN)/VerificaPontoContato $(EXEC_BIN)/VerificaPontoContato
	chmod 0754 $(EXEC_BIN)/load_$(APPNAME).sh
	chmod 0754 $(EXEC_BIN)/preload_pontocontato.sh
	chmod 0754 $(EXEC_DIR)/executa.sh
	chmod 0754 $(EXEC_DIR)/pontocontato.sh

pontocontato:
	umask 077
	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi
	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_BIN) ] ; then mkdir $(EXEC_BIN); fi
	if [ ! -d $(EXEC_CFG) ] ; then mkdir $(EXEC_CFG); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(DATA_DIR) ] ; then mkdir $(DATA_DIR); fi
	cp -f $(COMPILE_SRC)/load_$(APPNAME).`hostname`.`whoami`.sh $(EXEC_BIN)/load_$(APPNAME).sh
	cp -f $(COMPILE_CFG)/VerificaPontoContato.`hostname`.`whoami`.cfg $(EXEC_CFG)/VerificaPontoContato.cfg
	cp -f $(COMPILE_SRC)/preload_pontocontato.sh $(EXEC_BIN)/preload_pontocontato.sh
	cp -f $(COMPILE_CFG)/load_pontocontato.ctl $(EXEC_BIN)/load_pontocontato.ctl
	cp -f $(COMPILE_CFG)/load_pontocontato.par $(EXEC_BIN)/load_pontocontato.par
	cp -f $(COMPILE_CFG)/preload.sql $(EXEC_BIN)/preload.sql
	cp -f $(COMPILE_CFG)/posload.sql $(EXEC_BIN)/posload.sql
	cp -f $(COMPILE_CFG)/errload.sql $(EXEC_BIN)/errload.sql
	cp -f $(COMPILE_CFG)/falhaload.sql $(EXEC_BIN)/falhaload.sql
	cp -f $(COMPILE_SRC)/executa.sh $(EXEC_DIR)/executa.sh
	cp -f $(COMPILE_BIN)/VerificaPontoContato $(EXEC_BIN)/VerificaPontoContato
	chmod 0754 $(EXEC_BIN)/load_$(APPNAME).sh
	chmod 0754 $(EXEC_BIN)/preload_pontocontato.sh
	chmod 0754 $(EXEC_DIR)/executa.sh

VerificaPontoContato: VerificaPontoContato.cpp
	if [ ! -d $(COMPILE_BIN) ] ; then mkdir $(COMPILE_BIN); fi
	cp -f $(COMPILE_SRC)/main.`hostname`.`whoami`.cpp $(COMPILE_SRC)/main.cpp
	xlC -bnoquiet -qrtti $(PINCLUDE) $(PLIBPATH) -q64 -brtl -Iinclude -I$(ORACLE_HOME)/precomp/public \
	-L$(ORALIBDIR) \
	-lclntsh -lld -lm -lm -lc_r -lpthreads -lpthread $(XERCES_CLIB) \
	src/VerificaPontoContato.cpp \
	src/main.cpp \
	../commons/Log/src/Log.cpp \
	../commons/Propriedade/src/MFile.cpp \
	../commons/Propriedade/src/Attribute.cpp \
	../commons/Collection/src/Collection.cpp \
	../commons/CFile/src/CFile.cpp \
	../commons/LerDados/src/LerDados.cpp \
	../commons/LerDados/src/SplitLine.cpp \
	../commons/LerDados/src/Attribute.cpp \
	../commons/LerDados/src/Properties.cpp \
	-o bin/VerificaPontoContato

VerificaPontoContato.cpp:
	proc parse=none cpp_suffix=cpp code=cpp include=include src/VerificaPontoContato.pcpp
