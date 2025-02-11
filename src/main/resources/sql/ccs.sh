#!/usr/bin/env bash

CONTAINER_NAME=ccs
ROOT_PASSWORD=secret
MYSQL_HOME=/home/mysql
JDBC_PORT=3306

SLEEP_INTERVAL=10s
MAX_NUM_OF_CONNECTION_TRIES=15

if [[ $# -ne 1 ]]
then
    printf "football.sh start|stop|cli|bash\n"
    exit 1
fi

if [ $1 == "bash" ]
then
    docker exec -it $CONTAINER_NAME bash
    exit 0
fi

if [ $1 == "cli" ]
then
    docker exec -it $CONTAINER_NAME mysql -u root -p$ROOT_PASSWORD
    exit 0
fi


if [ $1 == "stop" ]
then
    docker stop $CONTAINER_NAME
    docker container rm $CONTAINER_NAME
    exit 0
fi

if [ $1 == "start" ]
then

    docker run \
     --detach \
     --name $CONTAINER_NAME \
     --publish $JDBC_PORT:$JDBC_PORT \
     --volume $(pwd):$MYSQL_HOME \
     --env MYSQL_ROOT_PASSWORD=$ROOT_PASSWORD \
     mysql:8

    try=0
    until docker exec $CONTAINER_NAME mysql -u root -p$ROOT_PASSWORD -e ";" 1>/dev/null 2>/dev/null;
    do
        printf "Connection Refused #%d time\n" $try
        try=$(expr $try + 1)
        sleep $SLEEP_INTERVAL
        if [ $try -ge $MAX_NUM_OF_CONNECTION_TRIES ]
        then
            printf "Too many unsuccessful connection attempts!\n"
            exit 1
        fi
    done
    printf "Successfull Connection\n"
    printf "Importing Data...\n"

    docker exec $CONTAINER_NAME sh -c "mysql -u root -p$ROOT_PASSWORD < $MYSQL_HOME/dump.sql 2>/dev/null"

    printf "Data was imported!\n"

    exit 0
fi

