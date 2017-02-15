.SILENT:

APPNAME=manutencaoLogin

BATCH_DIR=$(HOME)/BATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_SRC=$(COMPILE_DIR)/src
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_BIN=$(COMPILE_DIR)/bin

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_BIN=$(EXEC_DIR)/bin
EXEC_CFG=$(EXEC_DIR)/bin
EXEC_LOG=$(EXEC_DIR)/log
EXEC_DATA=$(EXEC_DIR)/data
EXEC_ERROR=$(EXEC_DIR)/error

CFLAGS= -L$(ORALIBDIR) -lclntsh -lld -lm -lm -lc_r -lpthreads
export CFLAGS

all:  build

build:$(APPNAME)

clean:
	if [ -d $(COMPILE_BIN) ] ; then rm -r $(COMPILE_BIN); fi

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

	if [ ! -d $(COMPILE_BIN) ] ; then mkdir $(COMPILE_BIN); fi
	cp $(COMPILE_SRC)/manutencaoLogin.jar $(COMPILE_BIN)

	# copia executa.sh
	cp -pf $(COMPILE_DIR)/executa.sh $(EXEC_DIR)
	cp -pf $(COMPILE_DIR)/manutencaoLogin.sh $(EXEC_DIR)
	# copia executavel
	cp -pf $(COMPILE_BIN)/manutencaoLogin.jar $(EXEC_BIN)
	# copia arquivos de configuracao comuns
	cp -pf $(BATCH_DIR)/oracle.`hostname`.`whoami`.cfg $(PROCESSOS_DIR)/oracle.cfg
	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg
	# copia controle
	cp -pf $(COMPILE_CFG)/control.ctl $(EXEC_BIN)
	
	chmod 0755 $(EXEC_DIR)
	chmod 0755 $(EXEC_BIN)
	chmod 0755 $(EXEC_CFG) 
	chmod 0777 $(EXEC_DATA)
	chmod 0777 $(EXEC_ERROR)
	chmod 0755 $(EXEC_LOG)
	chmod 0754 $(EXEC_DIR)/executa.sh
	chmod 0754 $(EXEC_DIR)/manutencaoLogin.sh
	chmod 0664 $(EXEC_CFG)/$(APPNAME).cfg

uninstall:
	rm -r -f $(EXEC_DIR)

	