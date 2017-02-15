#!/apps/perl-5.005_03/bin/perl -w
#ident @(#) P2K: src/cacs/tools/loadDNE/centro_distribuicao.pm 70.3 10/01/21 10:23:47
# (c) 2007,2008,2009,2010, Convergys Information Management Group Inc. All Rights Reserved. Private and Confidential. May not be disclosed without permission.

################################################################################
#   centro_distribuicao.pm  -  04/05/2004 by aldebaran perseke
#
#   Brazil's address centro_distribuicao class
################################################################################

package centro_distribuicao;

use     strict;

################################################################################
#   constants
################################################################################
use constant SUCCESS => 0;
use constant ERROR => 1;

################################################################################
#   construct a new centro_distribuicao object
################################################################################

sub new {
    my  $self = {};

    $self->{ COD_CDD } = undef;
    $self->{ NOM_CDD } = undef;
    $self->{ COD_CEP_CDD } = undef;
    $self->{ COD_BAIRRO_CDD } = undef;
    $self->{ DSC_CDD } = undef;
    $self->{ NUM_TELEFONE_CDD } = undef;
    $self->{ DSC_COMPL01_CDD } = undef;
    $self->{ DSC_COMPL02_CDD } = undef;
    $self->{ DSC_COMPL03_CDD } = undef;
    $self->{ NUM_LOGRAD_CDD } = undef;
    $self->{ COD_TIPO_COMPL01_CDD } = undef;
    $self->{ COD_TIPO_COMPL02_CDD } = undef;
    $self->{ COD_TIPO_COMPL03_CDD } = undef;
    $self->{ COD_LOGRAD_CDD } = undef;
    $self->{ DAT_ATUALIZACAO } = undef;
    $self->{ USUARIO } = undef;
    $self->{ VER_NBR } = undef;
	$self->{ DAT_CARGA } = undef;
	
    bless( $self );

    return $self;
}

################################################################################
#   set/get COD_CDD attribute
################################################################################

sub codigo {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_CDD } = shift;
    }

    $self->{ COD_CDD };
}

################################################################################
#   set/get NOM_CDD attribute
################################################################################

sub nome {
    my  $self = shift;

    if( @_ ) {
        $self->{ NOM_CDD } = substr( shift, 0, 50 );
    }

    $self->{ NOM_CDD };
}

################################################################################
#   set/get COD_CEP_CDD attribute
################################################################################

sub codigoCEP {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_CEP_CDD } = shift;
    }

    $self->{ COD_CEP_CDD };
}

################################################################################
#   set/get COD_BAIRRO_CDD attribute
################################################################################

sub codigoBairro {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_BAIRRO_CDD } = shift;
    }

    $self->{ COD_BAIRRO_CDD };
}

################################################################################
#   set/get DSC_CDD attribute
################################################################################

sub descricao {
    my  $self = shift;

    if( @_ ) {
        $self->{ DSC_CDD } = substr( shift, 0, 30 );
    }

    $self->{ DSC_CDD };
}

################################################################################
#   set/get NUM_TELEFONE_CDD attribute
################################################################################

sub telefone {
    my  $self = shift;

    if( @_ ) {
        $self->{ NUM_TELEFONE_CDD } = substr( shift, 0, 12 );
    }

    $self->{ NUM_TELEFONE_CDD };
}

################################################################################
#   set/get DSC_COMPL01_CDD attribute
################################################################################

sub complemento1 {
    my  $self = shift;

    if( @_ ) {
        $self->{ DSC_COMPL01_CDD } = substr( shift, 0, 15 );
    }

    $self->{ DSC_COMPL01_CDD };
}

################################################################################
#   set/get DSC_COMPL02_CDD attribute
################################################################################

sub complemento2 {
    my  $self = shift;

    if( @_ ) {
        $self->{ DSC_COMPL02_CDD } = substr( shift, 0, 15 );
    }

    $self->{ DSC_COMPL02_CDD };
}

################################################################################
#   set/get DSC_COMPL03_CDD attribute
################################################################################

sub complemento3 {
    my  $self = shift;

    if( @_ ) {
        $self->{ DSC_COMPL03_CDD } = substr( shift, 0, 15 );
    }

    $self->{ DSC_COMPL03_CDD };
}

################################################################################
#   set/get NUM_LOGRAD_CDD attribute
################################################################################

sub numero {
    my  $self = shift;

    if( @_ ) {
        $self->{ NUM_LOGRAD_CDD } = substr( shift, 0, 7 );
    }

    $self->{ NUM_LOGRAD_CDD };
}

################################################################################
#   set/get COD_TIPO_COMPL01_CDD attribute
################################################################################

sub tipoComplemento1 {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_TIPO_COMPL01_CDD } = shift;
    }

    $self->{ COD_TIPO_COMPL01_CDD };
}

################################################################################
#   set/get COD_TIPO_COMPL02_CDD attribute
################################################################################

sub tipoComplemento2 {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_TIPO_COMPL02_CDD } = shift;
    }

    $self->{ COD_TIPO_COMPL02_CDD };
}

################################################################################
#   set/get COD_TIPO_COMPL03_CDD attribute
################################################################################

sub tipoComplemento3 {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_TIPO_COMPL03_CDD } = shift;
    }

    $self->{ COD_TIPO_COMPL03_CDD };
}

################################################################################
#   set/get COD_LOGRAD_CDD attribute
################################################################################

sub codigoLogradouro {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_LOGRAD_CDD } = shift;
    }

    $self->{ COD_LOGRAD_CDD };
}

################################################################################
#   set/get DAT_ATUALIZACAO attribute
################################################################################

sub dataAtualizacao {
    my  $self = shift;

    if( @_ ) {
        $self->{ DAT_ATUALIZACAO } = shift;
    }

    $self->{ DAT_ATUALIZACAO };
}

################################################################################
#   set/get DAT_CARGA attribute
################################################################################

sub dataCarga {
    my  $self = shift;

    if( @_ ) {
        $self->{ DAT_CARGA } = shift;
    }

    $self->{ DAT_CARGA };
}

################################################################################
#   set/get USUARIO attribute
################################################################################

sub usuarioAtualizacao {
    my  $self = shift;

    if( @_ ) {
        $self->{ USUARIO } = shift;
    }

    $self->{ USUARIO };
}

################################################################################
#   set/get VER_NBR attribute
################################################################################

sub verNbr {
    my  $self = shift;

    if( @_ ) {
        $self->{ VER_NBR } = shift;
    }

    $self->{ VER_NBR };
}

################################################################################
#   transform an object in a printable string
################################################################################

sub to_string {
    my  $self = shift;

    sprintf qq/codigo: %d\nnome: '%s'\nCEP: %d\nbairro: %d\ndescricao: '%s'\ntelefone: '%s'\ncomplemento1: '%s'\ncomplemento2: '%s'\ncomplemento3: '%s'\nnumero: %d\ntipo complemento1: %d\ntipo complemento2: %d\ntipo complemento3: %d\nlogradouro: %d\ndata: %s\nusuario: %d/
            , ( defined $self->{ COD_CDD } )?$self->{ COD_CDD }:0
            , ( defined $self->{ NOM_CDD } )?$self->{ NOM_CDD }:''
            , ( defined $self->{ COD_CEP_CDD } )?$self->{ COD_CEP_CDD }:0
            , ( defined $self->{ COD_BAIRRO_CDD } )?$self->{ COD_BAIRRO_CDD }:0
            , ( defined $self->{ DSC_CDD } )?$self->{ DSC_CDD }:''
            , ( defined $self->{ NUM_TELEFONE_CDD } )?$self->{ NUM_TELEFONE_CDD }:''
            , ( defined $self->{ DSC_COMPL01_CDD } )?$self->{ DSC_COMPL01_CDD }:''
            , ( defined $self->{ DSC_COMPL02_CDD } )?$self->{ DSC_COMPL02_CDD }:''
            , ( defined $self->{ DSC_COMPL03_CDD } )?$self->{ DSC_COMPL03_CDD }:''
            , ( defined $self->{ NUM_LOGRAD_CDD } )?$self->{ NUM_LOGRAD_CDD }:0
            , ( defined $self->{ COD_TIPO_COMPL01_CDD } )?$self->{ COD_TIPO_COMPL01_CDD }:0
            , ( defined $self->{ COD_TIPO_COMPL02_CDD } )?$self->{ COD_TIPO_COMPL02_CDD }:0
            , ( defined $self->{ COD_TIPO_COMPL03_CDD } )?$self->{ COD_TIPO_COMPL03_CDD }:0
            , ( defined $self->{ COD_LOGRAD_CDD } )?$self->{ COD_LOGRAD_CDD }:0
            , ( defined $self->{ DAT_ATUALIZACAO } )?$self->{ DAT_ATUALIZACAO }:0
            , ( defined $self->{ USUARIO } )?$self->{ USUARIO }:0
            , ( defined $self->{ VER_NBR } )?$self->{ VER_NBR }:0
            , ( defined $self->{ DAT_CARGA } )?$self->{ DAT_CARGA }:0;
}

################################################################################
#   delete an object from database
################################################################################
sub delete {
    
    my  $self = shift;
    my  $connection = shift;
    my  $returnCode = SUCCESS;

    if( defined $self->{ COD_CDD } ) {

        my  $SQLQuery = <<SQL_QUERY;
delete from CENTRO_DISTRIBUICAO
where COD_CDD = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_CDD } )
                or return ERROR;

        if ($recordSet->rows == 0) {
            $returnCode = ERROR;
        }
    } else {
        $returnCode = ERROR;
    }
    
    $returnCode;
}

################################################################################
#   persist the object in database
################################################################################

sub persist {
    my  $self = shift;
    my  $connection = shift;

#   if there's no codigo, insert it

    if( ! defined $self->{ COD_CDD }
            || 0 == $self->{ COD_CDD } ) {

        my  $SQLQuery = <<SQL_QUERY;
insert into
    CENTRO_DISTRIBUICAO
( COD_CDD
    , NOM_CDD
    , COD_CEP_CDD
    , COD_BAIRRO_CDD
    , DSC_CDD
    , NUM_TELEFONE_CDD
    , DSC_COMPL01_CDD
    , DSC_COMPL02_CDD
    , DSC_COMPL03_CDD
    , NUM_LOGRAD_CDD
    , COD_TIPO_COMPL01_CDD
    , COD_TIPO_COMPL02_CDD
    , COD_TIPO_COMPL03_CDD
    , COD_LOGRAD_CDD
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA )
values
( SEQ_CENTDIST_01.nextval
    , ?
    , ?
    , ?
    , ?
    , ?
    , ?
    , ?
    , ?
    , ?
    , ?
    , ?
    , ?
    , ?
    , $self->{ DAT_ATUALIZACAO }
    , ?
    , '1'
    , SYSDATE )
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ NOM_CDD }
                , $self->{ COD_CEP_CDD }
                , $self->{ COD_BAIRRO_CDD }
                , $self->{ DSC_CDD }
                , $self->{ NUM_TELEFONE_CDD }
                , $self->{ DSC_COMPL01_CDD }
                , $self->{ DSC_COMPL02_CDD }
                , $self->{ DSC_COMPL03_CDD }
                , $self->{ NUM_LOGRAD_CDD }
                , $self->{ COD_TIPO_COMPL01_CDD }
                , $self->{ COD_TIPO_COMPL02_CDD }
                , $self->{ COD_TIPO_COMPL03_CDD }
                , $self->{ COD_LOGRAD_CDD }
                , $self->{ USUARIO } )
				or return ERROR;

        $SQLQuery = <<SQL_QUERY;
select
    max( COD_CDD )
from
    CENTRO_DISTRIBUICAO
SQL_QUERY
        my  @resultRow;

        $recordSet = $connection->prepare( $SQLQuery );
		$recordSet->execute()
			or return ERROR;

        if( @resultRow = $recordSet->fetchrow_array() ) {
            $self->{ COD_CDD } = $resultRow[ 0 ];
        }
    } else {

        my  $SQLQuery = <<SQL_QUERY;
update
    CENTRO_DISTRIBUICAO
set
    NOM_CDD = ?
    , COD_CEP_CDD = ?
    , COD_BAIRRO_CDD = ?
    , DSC_CDD = ?
    , COD_LOGRAD_CDD = ?
    , DAT_ATUALIZACAO = $self->{ DAT_ATUALIZACAO }
    , USUARIO = ?
    , VER_NBR = ?
    , DAT_CARGA = SYSDATE
where
    COD_CDD = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ NOM_CDD }
                , $self->{ COD_CEP_CDD }
                , $self->{ COD_BAIRRO_CDD }
                , $self->{ DSC_CDD }
                , $self->{ COD_LOGRAD_CDD }
                , $self->{ USUARIO }
                , ($self->{ VER_NBR })+1
                , $self->{ COD_CDD } )
				or return ERROR;

		if ($recordSet->rows == 0) {
			return ERROR;
		}
    }
	SUCCESS;
}

################################################################################
#   get an object from database by codigo
################################################################################

sub queryByCodigo {
    my  $connection = shift;
    my  $COD_CDD = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_CDD
    , NOM_CDD
    , COD_CEP_CDD
    , COD_BAIRRO_CDD
    , nvl( DSC_CDD, NOM_CDD )
    , NUM_TELEFONE_CDD
    , DSC_COMPL01_CDD
    , DSC_COMPL02_CDD
    , DSC_COMPL03_CDD
    , NUM_LOGRAD_CDD
    , COD_TIPO_COMPL01_CDD
    , COD_TIPO_COMPL02_CDD
    , COD_TIPO_COMPL03_CDD
    , COD_LOGRAD_CDD
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    CENTRO_DISTRIBUICAO
where
    COD_CDD = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $COD_CDD )
          or die "error running the query\n$SQLQuery\non database server\n";


    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $self = {};

        $self->{ COD_CDD } = $resultRow[ 0 ];
        $self->{ NOM_CDD } = $resultRow[ 1 ];
        $self->{ COD_CEP_CDD } = $resultRow[ 2 ];
        $self->{ COD_BAIRRO_CDD } = $resultRow[ 3 ];
        $self->{ DSC_CDD } = $resultRow[ 4 ];
        $self->{ NUM_TELEFONE_CDD } = $resultRow[ 5 ];
        $self->{ DSC_COMPL01_CDD } = $resultRow[ 6 ];
        $self->{ DSC_COMPL02_CDD } = $resultRow[ 7 ];
        $self->{ DSC_COMPL03_CDD } = $resultRow[ 8 ];
        $self->{ NUM_LOGRAD_CDD } = $resultRow[ 9 ];
        $self->{ COD_TIPO_COMPL01_CDD } = $resultRow[ 10 ];
        $self->{ COD_TIPO_COMPL02_CDD } = $resultRow[ 11 ];
        $self->{ COD_TIPO_COMPL03_CDD } = $resultRow[ 12 ];
        $self->{ COD_LOGRAD_CDD } = $resultRow[ 13 ];
        $self->{ DAT_ATUALIZACAO } = $resultRow[ 14 ];
        $self->{ USUARIO } = $resultRow[ 15 ];
        $self->{ VER_NBR } = $resultRow[ 16 ];
        $self->{ DAT_CARGA } = $resultRow[ 17 ];

        $recordSet->finish();
        bless( $self );

        $self;
    }
}

################################################################################
#   get an object from database by nome
################################################################################

sub queryByNome {
    my  $connection = shift;
    my  $NOM_CDD = shift;

#   the select query

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_CDD
    , NOM_CDD
    , COD_CEP_CDD
    , COD_BAIRRO_CDD
    , nvl( DSC_CDD, NOM_CDD )
    , NUM_TELEFONE_CDD
    , DSC_COMPL01_CDD
    , DSC_COMPL02_CDD
    , DSC_COMPL03_CDD
    , NUM_LOGRAD_CDD
    , COD_TIPO_COMPL01_CDD
    , COD_TIPO_COMPL02_CDD
    , COD_TIPO_COMPL03_CDD
    , COD_LOGRAD_CDD
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    CENTRO_DISTRIBUICAO
where
    NOM_CDD = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $NOM_CDD )
            or die "error running the query\n$SQLQuery\non database server\n";

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $self = {};

        $self->{ COD_CDD } = $resultRow[ 0 ];
        $self->{ NOM_CDD } = $resultRow[ 1 ];
        $self->{ COD_CEP_CDD } = $resultRow[ 2 ];
        $self->{ COD_BAIRRO_CDD } = $resultRow[ 3 ];
        $self->{ DSC_CDD } = $resultRow[ 4 ];
        $self->{ NUM_TELEFONE_CDD } = $resultRow[ 5 ];
        $self->{ DSC_COMPL01_CDD } = $resultRow[ 6 ];
        $self->{ DSC_COMPL02_CDD } = $resultRow[ 7 ];
        $self->{ DSC_COMPL03_CDD } = $resultRow[ 8 ];
        $self->{ NUM_LOGRAD_CDD } = $resultRow[ 9 ];
        $self->{ COD_TIPO_COMPL01_CDD } = $resultRow[ 10 ];
        $self->{ COD_TIPO_COMPL02_CDD } = $resultRow[ 11 ];
        $self->{ COD_TIPO_COMPL03_CDD } = $resultRow[ 12 ];
        $self->{ COD_LOGRAD_CDD } = $resultRow[ 13 ];
        $self->{ DAT_ATUALIZACAO } = $resultRow[ 14 ];
        $self->{ USUARIO } = $resultRow[ 15 ];
        $self->{ VER_NBR } = $resultRow[ 16 ];
        $self->{ DAT_CARGA } = $resultRow[ 17 ];

        $recordSet->finish();
        bless( $self );

        $self;
    }
}

################################################################################
#   Check whether centro_distribuicao has relationship
################################################################################
sub hasRelationship {

    my $self = shift;
    my $connection = shift;
    my $returnCode = 0;

    if( defined $self->{ COD_CDD } ) {

        my  $SQLQuery = <<SQL_QUERY;
select nvl(count(*), 0) 
  from centro_distribuicao cdd
 where cdd.cod_cdd = ?
   and exists (select 1 from cep c
                where c.cod_cdd = cdd.cod_cdd)
SQL_QUERY

        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_CDD } )
                or die "error running the query\n$SQLQuery\non database server for object\n"
                        . $self->to_string()
                        . "\n";
        $returnCode = $recordSet->fetchrow_array();
    }
    $returnCode;
}


#   return 1 to module requiring or using it

1;

