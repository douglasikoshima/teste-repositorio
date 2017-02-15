#!/bin/sh

CFG='cargaDadosSRE.cfg'

DIR=$(dirname $0)

cd "$DIR"
. "${HOME}/.profile"
. "$DIR/$CFG"

bash executaRelCadManual.sh