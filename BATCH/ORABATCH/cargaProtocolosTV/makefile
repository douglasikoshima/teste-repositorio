
APPNAME=cargaProtocolosTV

BATCH_DIR=$(HOME)/BATCH/ORABATCH

COMPILE_DIR=$(BATCH_DIR)/$(APPNAME)
COMPILE_CFG=$(COMPILE_DIR)/cfg
COMPILE_SQL=$(COMPILE_DIR)/sql

DEPLOY_DIR=$(HOME)/deploy
PROCESSOS_DIR=$(DEPLOY_DIR)/processos/ORABATCH

EXEC_DIR=$(PROCESSOS_DIR)/$(APPNAME)
EXEC_CFG=$(EXEC_DIR)/cfg
EXEC_LOG=$(EXEC_DIR)/log
EXEC_ERR=$(EXEC_DIR)/bad
EXEC_DAT=$(EXEC_DIR)/data_in
EXEC_TMP=$(EXEC_DIR)/tmp
EXEC_PRC=$(EXEC_DIR)/prc
EXEC_SQL=$(EXEC_DIR)/sql

all:

clean:
	rm -f $(EXEC_DIR)/$(APPNAME).sh
	rm -f $(EXEC_CFG)/$(APPNAME).cfg
	rm -f $(EXEC_SQL)/$(APPNAME).cliente.ctl
	rm -f $(EXEC_SQL)/$(APPNAME).protocolo.ctl
	rm -f $(EXEC_SQL)/$(APPNAME).atendimento.ctl
	rm -f $(EXEC_SQL)/$(APPNAME).par
	rm -f $(EXEC_SQL)/$(APPNAME).cliente.sql
	rm -f $(EXEC_SQL)/$(APPNAME).protocolo.sql
	rm -f $(EXEC_SQL)/$(APPNAME).atendimento.sql
	rm -f $(EXEC_SQL)/$(APPNAME).trunc_cliente.sql
	rm -f $(EXEC_SQL)/$(APPNAME).trunc_protocolo.sql
	rm -f $(EXEC_SQL)/$(APPNAME).trunc_atendimento.sql

install:
	umask 077

	# cria estrutura inicial
	if [ ! -d $(DEPLOY_DIR) ] ; then mkdir $(DEPLOY_DIR); fi
	if [ ! -d $(PROCESSOS_DIR) ] ; then mkdir $(PROCESSOS_DIR); fi

	# cria diretorios de aplicacao
	if [ ! -d $(EXEC_DIR) ] ; then mkdir $(EXEC_DIR); fi
	if [ ! -d $(EXEC_CFG) ] ; then mkdir $(EXEC_CFG); fi
	if [ ! -d $(EXEC_LOG) ] ; then mkdir $(EXEC_LOG); fi
	if [ ! -d $(EXEC_ERR) ] ; then mkdir $(EXEC_ERR); fi
	if [ ! -d $(EXEC_DAT) ] ; then mkdir $(EXEC_DAT); fi
	if [ ! -d $(EXEC_TMP) ] ; then mkdir $(EXEC_TMP); fi
	if [ ! -d $(EXEC_PRC) ] ; then mkdir $(EXEC_PRC); fi
	if [ ! -d $(EXEC_SQL) ] ; then mkdir $(EXEC_SQL); fi

	# copia arquivo de configuracao da aplicacao
	cp -pf $(COMPILE_CFG)/$(APPNAME).`hostname`.`whoami`.cfg $(EXEC_CFG)/$(APPNAME).cfg

	# copia .sh
	cp -pf $(COMPILE_DIR)/$(APPNAME).sh $(EXEC_DIR)
	cp -pf $(COMPILE_SQL)/$(APPNAME).cliente.ctl $(EXEC_SQL)
	cp -pf $(COMPILE_SQL)/$(APPNAME).protocolo.ctl $(EXEC_SQL)
	cp -pf $(COMPILE_SQL)/$(APPNAME).atendimento.ctl $(EXEC_SQL)
	cp -pf $(COMPILE_SQL)/$(APPNAME).par $(EXEC_SQL)
	cp -pf $(COMPILE_SQL)/$(APPNAME).cliente.sql $(EXEC_SQL)
	cp -pf $(COMPILE_SQL)/$(APPNAME).protocolo.sql $(EXEC_SQL)
	cp -pf $(COMPILE_SQL)/$(APPNAME).atendimento.sql $(EXEC_SQL)
	cp -pf $(COMPILE_SQL)/$(APPNAME).trunc_cliente.sql $(EXEC_SQL)
	cp -pf $(COMPILE_SQL)/$(APPNAME).trunc_protocolo.sql $(EXEC_SQL)
	cp -pf $(COMPILE_SQL)/$(APPNAME).trunc_atendimento.sql $(EXEC_SQL)

	# seta permissoes
	chmod ug=rwx,o=rx $(EXEC_DIR)/$(APPNAME).sh
	chmod ug=rw,o=r $(EXEC_CFG)/$(APPNAME).cfg
	chmod ug=rw,o=r $(EXEC_SQL)/$(APPNAME).cliente.ctl
	chmod ug=rw,o=r $(EXEC_SQL)/$(APPNAME).protocolo.ctl
	chmod ug=rw,o=r $(EXEC_SQL)/$(APPNAME).atendimento.ctl
	chmod ug=rw,o=r $(EXEC_SQL)/$(APPNAME).par
	chmod ug=rw,o=r $(EXEC_SQL)/$(APPNAME).cliente.sql
	chmod ug=rw,o=r $(EXEC_SQL)/$(APPNAME).protocolo.sql
	chmod ug=rw,o=r $(EXEC_SQL)/$(APPNAME).atendimento.sql
	chmod ug=rw,o=r $(EXEC_SQL)/$(APPNAME).trunc_cliente.sql
	chmod ug=rw,o=r $(EXEC_SQL)/$(APPNAME).trunc_protocolo.sql
	chmod ug=rw,o=r $(EXEC_SQL)/$(APPNAME).trunc_atendimento.sql
	chmod ug=rwx,o=rx $(EXEC_SQL)
	chmod ug=rwx,o=rwx $(EXEC_LOG) $(EXEC_ERR) $(EXEC_DAT) $(EXEC_TMP) $(EXEC_PRC)

uninstall:
	rm -r -f $(EXEC_DIR)

