#!/bin/bash
export GIT=`which git`
export PWD=`which pwd`
export LS=`which ls`
export CP=`which cp`
export CAT=`which cat`

export ROOT=`pwd`

export REPOLIST=repos.txt
#export REPOLIST=ava.txt

for REPONAME in $(cat $REPOLIST) ; do
  REPOPATH=https://github.com/$REPONAME
  UNAME=${REPONAME%/*}
  echo $REPOPATH
  if [ ! -d $REPONAME ]
  then
    echo "Making directory " $REPONAME
    mkdir -p $REPONAME
    echo "Cloning " $REPOPATH
    (cd $ROOT/$UNAME && $GIT clone $REPOPATH)
  else
    echo "Pulling in " $ROOT/$REPONAME
    (cd $ROOT/$REPONAME && $GIT pull)
  fi
  cd $ROOT
done;
