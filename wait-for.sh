#!/bin/bash
# version 0.1
# Script created to get delay before dependent services up in started containers.
# Usage: sh wait-for.sh [options]
# Available options
# -e - (REQUIRED) health-check endpoints on dependence services, can add several args.
#      Implemented like chain-responsibility pattern, if one service is not availale
#      script will fail. (It does not check the correctness of the url)
# -c - execute command if all health-checks has been passed (one option, use double quotes)

loopCount=0
retries=10
sleepTime=6

unset ENDPOINT COMMAND

while getopts 'e:c:' opt;
do
  case "$opt" in
    e) ENDPOINT="$OPTARG" ;;
    c) COMMAND="$OPTARG" ;;
    ?) echo "ERROR Unexpected option!!! Available options: -e -c" && exit ;;
  esac

  if [ -n "$ENDPOINT" ] ;
    then
      until curl "$ENDPOINT" ;
        do
          if [ "$loopCount" -eq "$retries" ]
            then
              break
          fi

          loopCount=$((loopCount+1))
          echo "WARN Try to get response from $ENDPOINT. Retry $loopCount"
          sleep "$sleepTime"
      done
    else
      echo "ERROR -e option is required!!!"
      exit
  fi

  if [ "$loopCount" -eq "$retries" ]
    then
      echo "ERROR can't get response from $ENDPOINT"
      break
    else
      echo "SUCCESSFUL getting response from $ENDPOINT"
      loopCount=0
  fi

  if [ -n "$COMMAND" ];
      then
        echo "Try to execute command: $COMMAND"
        exec $COMMAND
  fi

done
