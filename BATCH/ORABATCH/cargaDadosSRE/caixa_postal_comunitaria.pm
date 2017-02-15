#!/apps/perl-5.005_03/bin/perl
#ident @(#) P2K: src/cacs/tools/loadDNE/caixa_postal_comunitaria.pm 70.1 09/12/14 15:19:01
# (c) 2007,2008,2009 Convergys Information Management Group Inc. All Rights Reserved. Private and Confidential. May not be disclosed without permission.

################################################################################
#   caixa_postal_comunitaria.pm  -  03/24/2004 by aldebaran perseke
#
#   Brazil's address caixa_postal_comunitaria class
################################################################################

package caixa_postal_comunitaria;

use     strict;

################################################################################
#   constants declaration
################################################################################

use constant SUCCESS => 0;
use constant ERROR   => 1;

################################################################################
#   construct a new caixa_postal_comunitaria object
################################################################################

sub new {
    my  $self = {};

    $self->{ COD_CX_POSTAL_COMUNIT } = undef;
    $self->{ COD_CEP } = undef;
    $self->{ NOM_PT_CX_POSTAL_COMUNIT } = undef;
    $self->{ END_PT_CX_POSTAL_COMUNIT } = undef;
    $self->{ NUM_INI_CX_POSTAL_COMUNIT } = undef;
    $self->{ NUM_FIN_CX_POSTAL_COMUNIT } = undef;
    $self->{ AREA_ABRAN_CX_POSTAL_COMUNIT } = undef;
    $self->{ DAT_ATUALIZACAO } = undef;
    $self->{ USUARIO } = undef;
    $self->{ VER_NBR } = undef;
    $self->{ DAT_CARGA } = undef;

    bless( $self );

    return $self;
}

################################################################################
#   set/get COD_CX_POSTAL_COMUNIT attribute
################################################################################

sub codigo {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_CX_POSTAL_COMUNIT } = shift;
    }

    $self->{ COD_CX_POSTAL_COMUNIT };
}

################################################################################
#   set/get COD_CEP attribute
################################################################################

sub cep {
    my  $self = shift;

    if( @_ ) {
        $self->{ COD_CEP } = substr( shift, 0, 7 );
    }

    $self->{ COD_CEP };
}

################################################################################
#   set/get NOM_PT_CX_POSTAL_COMUNIT attribute
################################################################################

sub nome {
    my  $self = shift;

    if( @_ ) {
        $self->{ NOM_PT_CX_POSTAL_COMUNIT } = substr( shift, 0, 72 );
    }

    $self->{ NOM_PT_CX_POSTAL_COMUNIT };
}

################################################################################
#   set/get END_PT_CX_POSTAL_COMUNIT attribute
################################################################################

sub endereco {
    my  $self = shift;

    if( @_ ) {
        $self->{ END_PT_CX_POSTAL_COMUNIT } = substr( shift, 0, 72 );
    }

    $self->{ END_PT_CX_POSTAL_COMUNIT };
}

################################################################################
#   set/get NUM_INI_CX_POSTAL_COMUNIT attribute
################################################################################

sub numeroInicial {
    my  $self = shift;

    if( @_ ) {
        $self->{ NUM_INI_CX_POSTAL_COMUNIT } = substr( shift, 0, 6 );
    }

    $self->{ NUM_INI_CX_POSTAL_COMUNIT };
}

################################################################################
#   set/get NUM_FIN_CX_POSTAL_COMUNIT attribute
################################################################################

sub numeroFinal {
    my  $self = shift;

    if( @_ ) {
        $self->{ NUM_FIN_CX_POSTAL_COMUNIT } = substr( shift, 0, 6 );
    }

    $self->{ NUM_FIN_CX_POSTAL_COMUNIT };
}

################################################################################
#   set/get AREA_ABRAN_CX_POSTAL_COMUNIT attribute
################################################################################

sub area {
    my  $self = shift;

    if( @_ ) {
        $self->{ AREA_ABRAN_CX_POSTAL_COMUNIT } = substr( shift, 0, 72 );
    }

    $self->{ AREA_ABRAN_CX_POSTAL_COMUNIT };
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
#   set/get DAT_ATUALIZACAO attribute
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
        $self->{ VER_NBR } = substr( shift, 0, 3 );
    }

    $self->{ VER_NBR };
}

################################################################################
#   transform an object in a printable string
################################################################################

sub to_string {
    my  $self = shift;

    sprintf qq/codigo: %d\nabreviacao: '%s'\ndescricao: '%s'\ndata: %s\nusuario: %d/
            , ( defined $self->{ COD_CX_POSTAL_COMUNIT } )?$self->{ COD_CX_POSTAL_COMUNIT }:0
            , ( defined $self->{ COD_CEP } )?$self->{ COD_CEP }:''
            , ( defined $self->{ NOM_PT_CX_POSTAL_COMUNIT } )?$self->{ NOM_PT_CX_POSTAL_COMUNIT }:''
            , ( defined $self->{ END_PT_CX_POSTAL_COMUNIT } )?$self->{ END_PT_CX_POSTAL_COMUNIT }:''
            , ( defined $self->{ NUM_INI_CX_POSTAL_COMUNIT } )?$self->{ NUM_INI_CX_POSTAL_COMUNIT }:0
            , ( defined $self->{ NUM_FIN_CX_POSTAL_COMUNIT } )?$self->{ NUM_FIN_CX_POSTAL_COMUNIT }:0
            , ( defined $self->{ AREA_ABRAN_CX_POSTAL_COMUNIT } )?$self->{ AREA_ABRAN_CX_POSTAL_COMUNIT }:''
            , ( defined $self->{ DAT_ATUALIZACAO } )?$self->{ DAT_ATUALIZACAO }:0
            , ( defined $self->{ USUARIO } )?$self->{ USUARIO }:''
            , ( defined $self->{ VER_NBR } )?$self->{ VER_NBR }:''
            , ( defined $self->{ DAT_CARGA } )?$self->{ DAT_CARGA }:0;
}

################################################################################
#   persist the object in database
################################################################################

sub persist {
    my  $self = shift;
    my  $connection = shift;

#   nullable fields

    my  $COD_CEP;

    if( defined $self->{ COD_CEP } ) {
        $COD_CEP = $self->{ COD_CEP };
    } else {
        $COD_CEP = undef;
    }
    
#   if there's no codigo, insert it

    if( ! defined $self->{ COD_CX_POSTAL_COMUNIT }
            || 0 == $self->{ COD_CX_POSTAL_COMUNIT } ) {

        my  $SQLQuery = <<SQL_QUERY;
insert into
    caixa_postal_comunitaria
( COD_CX_POSTAL_COMUNIT
    , COD_CEP
    , NOM_PT_CX_POSTAL_COMUNIT
    , END_PT_CX_POSTAL_COMUNIT
    , NUM_INI_CX_POSTAL_COMUNIT
    , NUM_FIN_CX_POSTAL_COMUNIT
    , AREA_ABRAN_CX_POSTAL_COMUNIT
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR 
    , DAT_CARGA )
values
( SEQ_CXPOSTALCOM_01.nextval
    , ?
    , ?
    , ?
    , ?
    , ?
    , ?
    , $self->{ DAT_ATUALIZACAO }
    , ? 
    , '1'
    , SYSDATE)
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $COD_CEP
                , $self->{ NOM_PT_CX_POSTAL_COMUNIT }
                , $self->{ END_PT_CX_POSTAL_COMUNIT }
                , $self->{ NUM_INI_CX_POSTAL_COMUNIT }
                , $self->{ NUM_FIN_CX_POSTAL_COMUNIT }
                , $self->{ AREA_ABRAN_CX_POSTAL_COMUNIT }
                , $self->{ USUARIO } )
                or return ERROR;

        $SQLQuery = <<SQL_QUERY;

select
    max( COD_CX_POSTAL_COMUNIT )
from
    caixa_postal_comunitaria
SQL_QUERY
        my  @resultRow;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute()
                or return ERROR;

        if( @resultRow = $recordSet->fetchrow_array() ) {
            $self->{ COD_CX_POSTAL_COMUNIT } = $resultRow[ 0 ];
            return SUCCESS;
        }
    } else {

        my  $SQLQuery = <<SQL_QUERY;
update
    caixa_postal_comunitaria
set
    COD_CEP = ?
    , NOM_PT_CX_POSTAL_COMUNIT = ?
    , END_PT_CX_POSTAL_COMUNIT = ?
    , NUM_INI_CX_POSTAL_COMUNIT = ?
    , NUM_FIN_CX_POSTAL_COMUNIT = ?
    , AREA_ABRAN_CX_POSTAL_COMUNIT = ?
    , DAT_ATUALIZACAO = $self->{ DAT_ATUALIZACAO }
    , USUARIO = ?
    , VER_NBR = ?
    , DAT_CARGA = SYSDATE
where
    COD_CX_POSTAL_COMUNIT = ?
SQL_QUERY
        my  $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_CEP }
                , $self->{ NOM_PT_CX_POSTAL_COMUNIT }
                , $self->{ END_PT_CX_POSTAL_COMUNIT }
                , $self->{ NUM_INI_CX_POSTAL_COMUNIT }
                , $self->{ NUM_FIN_CX_POSTAL_COMUNIT }
                , $self->{ AREA_ABRAN_CX_POSTAL_COMUNIT }
                , $self->{ USUARIO }
                , ($self->{ VER_NBR })+1
                , $self->{ COD_CX_POSTAL_COMUNIT } )
                or return ERROR;
    }

    return SUCCESS;
}

################################################################################
#   get an object from database by codigo
################################################################################

sub queryByNome {
    my  $connection = shift;
    my  $NOM_PT_CX_POSTAL_COMUNIT = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_CX_POSTAL_COMUNIT
    , COD_CEP
    , NOM_PT_CX_POSTAL_COMUNIT
    , END_PT_CX_POSTAL_COMUNIT
    , NUM_INI_CX_POSTAL_COMUNIT
    , NUM_FIN_CX_POSTAL_COMUNIT
    , AREA_ABRAN_CX_POSTAL_COMUNIT
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    caixa_postal_comunitaria
where
    NOM_PT_CX_POSTAL_COMUNIT = ?
SQL_QUERY
    my  $recordSet;
    my  @resultRow;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $NOM_PT_CX_POSTAL_COMUNIT )
            or die "error running the query\n$SQLQuery\non database server\n";

    if( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_CX_POSTAL_COMUNIT } = $resultRow[ 0 ];
        $new->{ COD_CEP } = $resultRow[ 1 ];
        $new->{ NOM_PT_CX_POSTAL_COMUNIT } = $resultRow[ 2 ];
        $new->{ END_PT_CX_POSTAL_COMUNIT } = $resultRow[ 3 ];
        $new->{ NUM_INI_CX_POSTAL_COMUNIT } = $resultRow[ 4 ];
        $new->{ NUM_FIN_CX_POSTAL_COMUNIT } = $resultRow[ 5 ];
        $new->{ AREA_ABRAN_CX_POSTAL_COMUNIT } = $resultRow[ 6 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 7 ];
        $new->{ USUARIO } = $resultRow[ 8 ];
        $new->{ VER_NBR } = $resultRow[ 9 ];
        $new->{ DAT_CARGA } = $resultRow[ 10 ];

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
    COD_CX_POSTAL_COMUNIT
    , COD_CEP
    , NOM_PT_CX_POSTAL_COMUNIT
    , END_PT_CX_POSTAL_COMUNIT
    , NUM_INI_CX_POSTAL_COMUNIT
    , NUM_FIN_CX_POSTAL_COMUNIT
    , AREA_ABRAN_CX_POSTAL_COMUNIT
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    caixa_postal_comunitaria
--where
--  COD_CEP !='.'
SQL_QUERY
    my  $recordSet;
    my  @resultRow;
    my  %container;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute()
            or die "error running the query\n$SQLQuery\non database server\n";

    while( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_CX_POSTAL_COMUNIT } = $resultRow[ 0 ];
        $new->{ COD_CEP } = $resultRow[ 1 ];
        $new->{ NOM_PT_CX_POSTAL_COMUNIT } = $resultRow[ 2 ];
        $new->{ END_PT_CX_POSTAL_COMUNIT } = $resultRow[ 3 ];
        $new->{ NUM_INI_CX_POSTAL_COMUNIT } = $resultRow[ 4 ];
        $new->{ NUM_FIN_CX_POSTAL_COMUNIT } = $resultRow[ 5 ];
        $new->{ AREA_ABRAN_CX_POSTAL_COMUNIT } = $resultRow[ 6 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 7 ];
        $new->{ USUARIO } = $resultRow[ 8 ];
        $new->{ VER_NBR } = $resultRow[ 9 ];
        $new->{ DAT_CARGA } = $resultRow[ 10 ];

        bless( $new );

        $container{ $new->{ COD_CX_POSTAL_COMUNIT } } = $new;
    }

    $recordSet->finish();

    %container;
}

################################################################################
#   get all objects from database that has non relatinpship with cdd
################################################################################

sub queryNonRelated {
    
    my  $connection = shift;

    my  $SQLQuery = <<SQL_QUERY;
select
    COD_CX_POSTAL_COMUNIT
    , COD_CEP
    , NOM_PT_CX_POSTAL_COMUNIT
    , END_PT_CX_POSTAL_COMUNIT
    , NUM_INI_CX_POSTAL_COMUNIT
    , NUM_FIN_CX_POSTAL_COMUNIT
    , AREA_ABRAN_CX_POSTAL_COMUNIT
    , DAT_ATUALIZACAO
    , USUARIO
    , VER_NBR
    , DAT_CARGA
from
    CAIXA_POSTAL_COMUNITARIA CPM
where
    not exists (select 1 from CEP c 
                 where c.NUM_CEP = CPM.COD_CEP)
SQL_QUERY
    my  $recordSet;
    my  @resultRow;
    my  @container = ();
    
    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute()
            or die "error running the query\n$SQLQuery\non database server\n";

    while( @resultRow = $recordSet->fetchrow_array() ) {
        my  $new = {};

        $new->{ COD_CX_POSTAL_COMUNIT } = $resultRow[ 0 ];
        $new->{ COD_CEP } = $resultRow[ 1 ];
        $new->{ NOM_PT_CX_POSTAL_COMUNIT } = $resultRow[ 2 ];
        $new->{ END_PT_CX_POSTAL_COMUNIT } = $resultRow[ 3 ];
        $new->{ NUM_INI_CX_POSTAL_COMUNIT } = $resultRow[ 4 ];
        $new->{ NUM_FIN_CX_POSTAL_COMUNIT } = $resultRow[ 5 ];
        $new->{ AREA_ABRAN_CX_POSTAL_COMUNIT } = $resultRow[ 6 ];
        $new->{ DAT_ATUALIZACAO } = $resultRow[ 7 ];
        $new->{ USUARIO } = $resultRow[ 8 ];
        $new->{ VER_NBR } = $resultRow[ 9 ];
        $new->{ DAT_CARGA } = $resultRow[ 10 ];

        bless( $new );

        push(@container, $new);
    }

    $recordSet->finish();

    @container;
}

################################################################################
#   delete an object from database
################################################################################

sub delete {
    my $self       = shift;
    my $connection = shift;

#   nullable fields

    my $COD_CX_POSTAL_COMUNIT;
    my $returnCode        = SUCCESS;

    if( defined $self->{ COD_CX_POSTAL_COMUNIT } ) {
        $COD_CX_POSTAL_COMUNIT = $self->{ COD_CX_POSTAL_COMUNIT };

        my $SQLQuery = <<SQL_QUERY;
delete from caixa_postal_comunitaria
where COD_CX_POSTAL_COMUNIT = ?
SQL_QUERY

        my $recordSet;

        $recordSet = $connection->prepare( $SQLQuery );
        $recordSet->execute( $self->{ COD_CX_POSTAL_COMUNIT } )
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
#   isRelatedToCEP - checks if the object is related to any CEP
################################################################################

sub isRelatedToCEP {
    my $connection      = shift;
    my $COD_CEP = shift;
    my $returnCode      = SUCCESS;

    my  $SQLQuery       = <<SQL_QUERY;
select
    nvl( count(*), 0 )
from
    caixa_postal_comunitaria CPM
where
        CPM.COD_CEP = ?
    and exists ( select
                     1
                 from
                     CEP C
                 where
                     C.NUM_CEP = CPM.COD_CEP )
SQL_QUERY

    my  $recordSet;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute( $COD_CEP )
            or die "error running the query\n$SQLQuery\non database server\n";

    $recordSet->fetchrow_array();
}

#   return 1 to module requiring or using it

1;

################################################################################
#   delete all non related objects
################################################################################

sub deleteNonRelated {
    
    my  $connection = shift;
    my $returnCode = SUCCESS;
    
    my  $SQLQuery = <<SQL_QUERY;
delete from
    CAIXA_POSTAL_COMUNITARIA CPM
where
    not exists (select 1 from CEP c 
                 where c.NUM_CEP = CPM.COD_CEP)
SQL_QUERY

    my $recordSet;

    $recordSet = $connection->prepare( $SQLQuery );
    $recordSet->execute()
            or return ERROR;

    if( $recordSet->rows == 0 ) {
        $returnCode = ERROR;
    }

    $returnCode;
}

#   return 1 to module requiring or using it

1;

