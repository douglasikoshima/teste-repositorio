#!/apps/perl-5.005_03/bin/perl
#ident @(#) P2K: src/cacs/tools/loadDNE/CEP.pm 70.2 09/12/15 07:51:08
# (c) 2007,2008,2009, Convergys Information Management Group Inc. All Rights Reserved. Private and Confidential. May not be disclosed without permission.

################################################################################
#   CEP.pm  -  03/24/2004 by aldebaran perseke
#
#   Brazil's address CEP class
################################################################################

package CEP;

use     strict;
use     centro_distribuicao;

################################################################################
#   constants
################################################################################
use constant SUCCESS => 0;
use constant ERROR => 1;

################################################################################
#   construct a new CEP object
################################################################################

sub new {
    my  $self = {};

    $self->{ COD_CEP } = undef;
    $self->{ NUM_CEP } = undef;
    $self->{ COD_CDD } = undef;
    $self->{ COD_LOCALIDADE } = undef;
    $self->{ DAT_ATUALIZACAO } = undef;
    $self->{ USUARIO } = undef;
    $self->{ VER_NBR } = undef;
    $self->{ DAT_CARGA } = undef;

    bless( $self );

    return $self;
}

################################################################################
#   set/get COD_CEP attribute
################################################################################

sub codigo {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_CEP } = shift;
    }

    $self->{ COD_CEP };
}

################################################################################
#   set/get NUM_CEP attribute
################################################################################

sub numero {
    my  $self = shift;

    if( @_ ) {
        $self->{ NUM_CEP } = substr( shift, 0, 10 );
    }

    $self->{ NUM_CEP };
}

################################################################################
#   set/get COD_CDD attribute
################################################################################

sub codigoCentroDistribuicao {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_CDD } = shift;
    }

    $self->{ COD_CDD };
}

################################################################################
#   set/get COD_LOCALIDADE attribute
################################################################################

sub codigoLocalidade {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_LOCALIDADE } = shift;
    }

    $self->{ COD_LOCALIDADE };
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
#   set/get USUARIO attribute
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

    sprintf qq/codigo: %d\nnumero: %d\nCDD: %d\nlocalidade: %d\ndata: %s\nusuario: %d/
            , ( defined $self->{ COD_CEP } )?$self->{ COD_CEP }:0
            , ( defined $self->{ NUM_CEP } )?$self->{ NUM_CEP }:0
            , ( defined $self->{ COD_CDD } )?$self->{ COD_CDD }:0
            , ( defined $self->{ COD_LOCALIDADE } )?$self->{ COD_LOCALIDADE }:0
            , ( defined $self->{ DAT_ATUALIZACAO } )?$self->{ DAT_ATUALIZACAO }:0
            , ( defined $self->{ USUARIO } )?$self->{ USUARIO }:0
            , ( defined $self->{ VER_NBR } )?$self->{ VER_NBR }:0
            , ( defined $self->{ DAT_CARGA } )?$self->{ DAT_CARGA }:0;
}

################################################################################
#   persist the object in database
################################################################################
sub persist {
    
    my  $self = shift;
    my  $connection = shift;

#   if there's no codigo, insert it

    if( ! defined $self->{ COD_CEP }
            || 0 == $self->{ COD_CEP } ) {

        my  $SQLQuery = <<SQL_QUERY;
insert into
    CEP
( COD_CEP
    , NUM_CEP
    , COD_CDD
    , COD_LOCALIDADE
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA )
values
( SEQ_CEP_01.nextval
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
        $recordSet->execute( $self->{ NUM_CEP }
                , $self->{ COD_CDD }
                , $self->{ COD_LOCALIDADE }
                , $self->{ USUARIO } )
              or return ERROR;

        $SQLQuery = <<SQL_QUERY;
select
    max( COD_CEP )
from
    CEP
SQL_QUERY
        my  @resultRow;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute()
              or return ERROR;

        if( @resultRow = $recordSet->fetchrow_array() ) {
            $self->{ COD_CEP } = $resultRow[ 0 ];
        }
    } else {

        my  $SQLQuery = <<SQL_QUERY;
update
    CEP
set
    NUM_CEP = ?
    , COD_CDD = ?
    , COD_LOCALIDADE = ?
    , DAT_ATUALIZACAO = $self->{ DAT_ATUALIZACAO }
    , USUARIO = ?
    , VER_NBR = ?
    , DAT_CARGA = SYSDATE
where
    COD_CEP = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ NUM_CEP }
                , $self->{ COD_CDD }
                , $self->{ COD_LOCALIDADE }
                , $self->{ USUARIO }
                , ($self->{ VER_NBR })+1
                , $self->{ COD_CEP } )
              or return ERROR;
        if ($recordSet->rows == 0) {
            return ERROR;
        }
    }
    
    SUCCESS;
}

################################################################################
#   delete an object from database
################################################################################
sub delete {

    my  $self = shift;
    my  $connection = shift;
    my $returnCode = SUCCESS;

    if( defined $self->{ COD_CEP } ) {

        my  $SQLQuery = <<SQL_QUERY;
delete from CEP
where COD_CEP = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_CEP } )
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
#   Check whether CEP has relationship
################################################################################
sub hasRelationship {

    my $self = shift;
    my $connection = shift;
    my $returnCode = 0;

    if( defined $self->{ COD_CEP } ) {

        my  $SQLQuery = <<SQL_QUERY;
select nvl(count(*), 0) 
  from cep c
 where c.cod_cep = ?
   and (exists (select 1 from rel_cep_lograd_bairro rclb
                where rclb.cod_cep = c.cod_cep)
    or exists (select 1 from centro_distribuicao cdd
                where cdd.cod_cep_cdd = c.cod_cep)
	or exists (select 1 from caixa_postal_comunitaria cpm
                where cpm.cod_cep = c.cod_cep))
SQL_QUERY

        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_CEP } )
                or die "error running the query\n$SQLQuery\non database server for object\n"
                        . $self->to_string()
                        . "\n";
        $returnCode = $recordSet->fetchrow_array();
    }
    $returnCode;
}
################################################################################
#   get an object from database by codigo
################################################################################

sub queryByCodigo {
    my  $connection = shift;
    my  $COD_CEP = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_CEP
    , NUM_CEP
    , COD_CDD
    , COD_LOCALIDADE
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    CEP
where
    COD_CEP = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $COD_CEP )
            or die "error running the query\n$SQLQuery\non database server\n";

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $self = {};

        $self->{ COD_CEP } = $resultRow[ 0 ];
        $self->{ NUM_CEP } = $resultRow[ 1 ];
        $self->{ COD_CDD } = $resultRow[ 2 ];
        $self->{ COD_LOCALIDADE } = $resultRow[ 3 ];
        $self->{ DAT_ATUALIZACAO } = $resultRow[ 4 ];
        $self->{ USUARIO } = $resultRow[ 5 ];
        $self->{ VER_NBR } = $resultRow[ 6 ];
        $self->{ DAT_CARGA } = $resultRow[ 7 ];

        $recordSet->finish();
        bless( $self );

        $self;
    }
}

################################################################################
#   get an object from database by numero
################################################################################

sub queryByNumero {
    my  $connection = shift;
    my  $NUM_CEP = shift;

#   the select query

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_CEP
    , NUM_CEP
    , COD_CDD
    , COD_LOCALIDADE
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    CEP
where
    NUM_CEP = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $NUM_CEP )
            or die "error running the query\n$SQLQuery\non database server\n";

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $self = {};

        $self->{ COD_CEP } = $resultRow[ 0 ];
        $self->{ NUM_CEP } = $resultRow[ 1 ];
        $self->{ COD_CDD } = $resultRow[ 2 ];
        $self->{ COD_LOCALIDADE } = $resultRow[ 3 ];
        $self->{ DAT_ATUALIZACAO } = $resultRow[ 4 ];
        $self->{ USUARIO } = $resultRow[ 5 ];
        $self->{ VER_NBR } = $resultRow[ 6 ];
        $self->{ DAT_CARGA } = $resultRow[ 7 ];

        $recordSet->finish();
        bless( $self );

        $self;
    }
}

#   return 1 to module requiring or using it

1;

