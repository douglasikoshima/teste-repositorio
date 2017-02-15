#!/apps/perl-5.005_03/bin/perl
#ident @(#) P2K: src/cacs/tools/loadDNE/titulo_logradouro.pm 70.2 09/12/14 14:48:39
# (c) 2007,2008,2009, Convergys Information Management Group Inc. All Rights Reserved. Private and Confidential. May not be disclosed without permission.

################################################################################
#   titulo_logradouro.pm  -  03/24/2004 by aldebaran perseke
#
#   Brazil's address titulo_logradouro class
################################################################################

package titulo_logradouro;

use     strict;

################################################################################
#   constants declaration
################################################################################

use constant SUCCESS => 0;
use constant ERROR   => 1;

################################################################################
#   construct a new titulo_logradouro object
################################################################################

sub new {
    my  $self = {};

    $self->{ COD_TITULO_LOGRAD } = undef;
    $self->{ DSC_ABREV_TITULO_LOGRAD } = undef;
    $self->{ DSC_TITULO_LOGRAD } = undef;
    $self->{ DAT_ATUALIZACAO } = undef;
    $self->{ USUARIO } = undef;
    $self->{ VER_NBR } = undef;
    $self->{ DAT_CARGA } = undef;

    bless( $self );

    return $self;
}

################################################################################
#   set/get COD_TITULO_LOGRAD attribute
################################################################################

sub codigo {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_TITULO_LOGRAD } = shift;
    }

    $self->{ COD_TITULO_LOGRAD };
}

################################################################################
#   set/get DSC_ABREV_TITULO_LOGRAD attribute
################################################################################

sub abreviatura {
    my  $self = shift;

    if( @_ ) {
        $self->{ DSC_ABREV_TITULO_LOGRAD } = substr( shift, 0, 7 );
    }

    $self->{ DSC_ABREV_TITULO_LOGRAD };
}

################################################################################
#   set/get DSC_TITULO_LOGRAD attribute
################################################################################

sub descricao {
    my  $self = shift;

    if( @_ ) {
        $self->{ DSC_TITULO_LOGRAD } = substr( shift, 0, 25 );
    }

    $self->{ DSC_TITULO_LOGRAD };
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

    sprintf qq/codigo: %d\nabreviacao: '%s'\ndescricao: '%s'\ndata: %s\nusuario: %d/
            , ( defined $self->{ COD_TITULO_LOGRAD } )?$self->{ COD_TITULO_LOGRAD }:0
            , ( defined $self->{ DSC_ABREV_TITULO_LOGRAD } )?$self->{ DSC_ABREV_TITULO_LOGRAD }:''
            , ( defined $self->{ DSC_TITULO_LOGRAD } )?$self->{ DSC_TITULO_LOGRAD }:''
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

    if( ! defined $self->{ COD_TITULO_LOGRAD }
            || 0 == $self->{ COD_TITULO_LOGRAD } ) {

        my  $SQLQuery = <<SQL_QUERY;
insert into
    TITULO_LOGRADOURO
( COD_TITULO_LOGRAD
    , DSC_ABREV_TITULO_LOGRAD
    , DSC_TITULO_LOGRAD
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA )
values
( SEQ_TITULOGR_01.nextval
    , ?
    , ?
    , $self->{ DAT_ATUALIZACAO }
    , ?
    , '1'
    , SYSDATE )
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ DSC_ABREV_TITULO_LOGRAD }
                , $self->{ DSC_TITULO_LOGRAD }
                , $self->{ USUARIO } )
                or return ERROR;

        $SQLQuery = <<SQL_QUERY;
select
    max( COD_TITULO_LOGRAD )
from
    TITULO_LOGRADOURO
SQL_QUERY
        my  @resultRow;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute()
                or return ERROR;

        if( @resultRow = $recordSet->fetchrow_array() ) {
            return SUCCESS;    
        }
    } else {

        my  $SQLQuery = <<SQL_QUERY;
update
    TITULO_LOGRADOURO
set
    DSC_ABREV_TITULO_LOGRAD = ?
    , DSC_TITULO_LOGRAD = ?
    , DAT_ATUALIZACAO = $self->{ DAT_ATUALIZACAO }
    , USUARIO = ?
    , VER_NBR = ?
    , DAT_CARGA = SYSDATE
where
    COD_TITULO_LOGRAD = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ DSC_ABREV_TITULO_LOGRAD }
                , $self->{ DSC_TITULO_LOGRAD }
                , $self->{ USUARIO }
                , ($self->{ VER_NBR })+1
                , $self->{ COD_TITULO_LOGRAD } )
                or return ERROR;
    }

    return SUCCESS; 
}

################################################################################
#   get an object from database by codigo
################################################################################

sub queryByCodigo {
    my  $connection = shift;
    my  $COD_TITULO_LOGRAD = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_TITULO_LOGRAD
    , DSC_ABREV_TITULO_LOGRAD
    , DSC_TITULO_LOGRAD
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    TITULO_LOGRADOURO
where
    COD_TITULO_LOGRAD = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $COD_TITULO_LOGRAD )
            or die "error running the query\n$SQLQuery\non database server\n";

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_TITULO_LOGRAD } = $resultRow[ 0 ];
        $new->{ DSC_ABREV_TITULO_LOGRAD } = $resultRow[ 1 ];
        $new->{ DSC_TITULO_LOGRAD } = $resultRow[ 2 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 3 ];
        $new->{ USUARIO } = $resultRow[ 4 ];
        $new->{ VER_NBR } = $resultRow[ 5 ];
        $new->{ DAT_CARGA } = $resultRow[ 6 ];

        $recordSet->finish();
        bless( $new );

        $new;
    }
}

################################################################################
#   get an object from database by abreviatura
################################################################################

sub queryByAbreviatura {
    my  $connection = shift;
    my  $DSC_ABREV_TITULO_LOGRAD = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_TITULO_LOGRAD
    , DSC_ABREV_TITULO_LOGRAD
    , DSC_TITULO_LOGRAD
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    TITULO_LOGRADOURO
where
    DSC_ABREV_TITULO_LOGRAD = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $DSC_ABREV_TITULO_LOGRAD )
            or die "error running the query\n$SQLQuery\non database server\n";

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_TITULO_LOGRAD } = $resultRow[ 0 ];
        $new->{ DSC_ABREV_TITULO_LOGRAD } = $resultRow[ 1 ];
        $new->{ DSC_TITULO_LOGRAD } = $resultRow[ 2 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 3 ];
        $new->{ USUARIO } = $resultRow[ 4 ];
        $new->{ VER_NBR } = $resultRow[ 5 ];
        $new->{ DAT_CARGA } = $resultRow[ 6 ];

        $recordSet->finish();
        bless( $new );

        $new;
    }
}

################################################################################
#   get a hash of objects from database
################################################################################

sub queryAll {
    my  $connection = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_TITULO_LOGRAD
    , DSC_ABREV_TITULO_LOGRAD
    , DSC_TITULO_LOGRAD
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    TITULO_LOGRADOURO
SQL_QUERY
    my  $recordSet;
    my  @resultRow;
    my  %container;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute()
            or die "error running the query\n$SQLQuery\non database server\n";

    while( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_TITULO_LOGRAD } = $resultRow[ 0 ];
        $new->{ DSC_ABREV_TITULO_LOGRAD } = $resultRow[ 1 ];
        $new->{ DSC_TITULO_LOGRAD } = $resultRow[ 2 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 3 ];
        $new->{ USUARIO } = $resultRow[ 4 ];
        $new->{ VER_NBR } = $resultRow[ 5 ];
        $new->{ DAT_CARGA } = $resultRow[ 6 ]; 

        bless( $new );

        $container{ $new->{ COD_TITULO_LOGRAD } } = $new;
    }

    $recordSet->finish();

    %container;
}

################################################################################
#   delete an object from database
################################################################################

sub delete {
    my $self       = shift;
    my $connection = shift;

#   nullable fields

    my $COD_TITULO_LOGRAD;
    my $returnCode        = SUCCESS;

    if( defined $self->{ COD_TITULO_LOGRAD } ) {
        $COD_TITULO_LOGRAD = $self->{ COD_TITULO_LOGRAD };

        my $SQLQuery = <<SQL_QUERY;
delete from 
    TITULO_LOGRADOURO
where 
    COD_TITULO_LOGRAD = ?
SQL_QUERY

        my $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_TITULO_LOGRAD } )
                or return ERROR;

        if( $recordSet->rows == 0 ) {
            $returnCode = ERROR;
        }

    } else {
        $returnCode = ERROR;
    }

    $returnCode;
}

################################################################################
#   isRelatedToLogradouro - checks if the object is related to any LOGRADOURO
################################################################################

sub isRelatedToLogradouro {
    my $connection        = shift;
    my $COD_TITULO_LOGRAD = shift;

    my  $SQLQuery         = <<SQL_QUERY;
select
    nvl( count(*), 0 )
from
    TITULO_LOGRADOURO TL
where
        TL.COD_TITULO_LOGRAD = ?
    and exists ( select
                     1
                 from
                     LOGRADOURO L
                 where
                     L.COD_TITULO_LOGRAD = TL.COD_TITULO_LOGRAD )
SQL_QUERY

    my  $recordSet;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $COD_TITULO_LOGRAD )
            or die "error running the query\n$SQLQuery\non database server\n";

    $recordSet->fetchrow_array();
}

#   return 1 to module requiring or using it

1;

