APPNAME=resetaIndisponibilidadeUsuario

BATCH_DIR=$(HOME)/BATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_BIN=$(COMPILE_DIR)/bin
COMPILE_CFG=$(COMPILE_DIR)/cfg

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_CFG=$(EXEC_DIR)/bin
EXEC_LOG=$(EXEC_DIR)/log


all:  build

clean:
	rm -f $(COMPILE_SRC)/*.o $(COMPILE_SRC)/*.lis $(COMPILE_SRC)/resetaIndisponibilidadeUsuarioPC.cpp
	rm -r -f $(COMPILE_BIN)

build:	$(APPNAME)

uninstall:
	rm -r -f $(EXEC_DIR)

install:
	umask 077
	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi
	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then echo "criando " $EXEC_DIR;mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_BIN) ] ; then echo "criando " $EXEC_BIN;mkdir $(EXEC_BIN); fi
	if [ ! -d $(EXEC_CFG) ] ; then echo "criando " $EXEC_CFG;mkdir $(EXEC_CFG); fi
	if [ ! -d $(EXEC_LOG) ] ; then echo "criando " $EXEC_LOG;mkdir $(EXEC_LOG); fi
	# copia executa.sh
	cp -p $(COMPILE_DIR)/executa.sh $(EXEC_DIR)
	cp -p $(COMPILE_DIR)/resetaIndisponibilidadeUsuario.sh $(EXEC_DIR)
	# copia executavel
	cp -p $(COMPILE_BIN)/$(APPNAME) $(EXEC_BIN)
	# copia arquivos de configuracao comuns
	cp -p $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg
	# copia arquivo de configuracao da aplicacao
	cp -p $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg
	# QUEBRA GALHO: copia para arquivo de configuracao com nome fora do padrao
	cp -p $(EXEC_CFG)/$(APPNAME).cfg $(EXEC_CFG)/priorizacao.cfg
	# cp -p $(EXEC_CFG)/$(APPNAME).cfg $(EXEC_CFG)/resetaIndisponibilidadeUsuario.cfg

resetaIndisponibilidadeUsuario: resetaIndisponibilidadeUsuarioPC.cpp
	if [ ! -d $(COMPILE_BIN) ] ; then mkdir $(COMPILE_BIN); fi
	xlC -bnoquiet -qrtti $(PINCLUDE) $(PLIBPATH) -q64 -brtl -Iinclude -I$(ORACLE_HOME)/precomp/public \
	-L$(ORALIBDIR) \
	-lclntsh -lld -lm -lm -lc_r -lpthreads -lpthread \
	src/resetaIndisponibilidadeUsuarioPC.cpp \
	../commons/Log/src/Log.cpp \
	../commons/Propriedade/src/MFile.cpp \
	../commons/Propriedade/src/Attribute.cpp \
	../commons/Propriedade/src/Properties.cpp \
	../commons/Collection/src/Collection.cpp \
	src/resetaIndisponibilidadeUsuario.cpp \
	-o bin/resetaIndisponibilidadeUsuario

resetaIndisponibilidadeUsuarioPC.cpp:
	proc parse=none cpp_suffix=cpp code=cpp include=include src/resetaIndisponibilidadeUsuarioPC.pcpp
