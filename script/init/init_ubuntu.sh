#!/bin/zsh

help() 
{
    echo "how to run: zsh init_ubuntu.sh -b 'zsh'"
}

while getopts b:a:f: flag
do
    case "${flag}" in
        b) b=${OPTARG};;
    esac
done

if [ "shell" = "$b" ];
then
    echo "bash"
    shellrc=$HOME/.bashrc
elif [ "zsh" = "$b" ];
then
    echo "zsh"
    shellrc=$HOME/.zshrc
fi

echo "shell: $b";
echo "shellrc: $shellrc";

source $shellrc


print_env()
{
    echo "python version:" | python -V
}


get_resource_info() {
    echo "# info"
    env | grep SHELL
    echo $HOSTNAME
    ifconfig |grep inet    
    apt-get update -y 
    apt-get upgrade -y 
}

# echo "# zsh setting"
set_java() {
    echo "# java setting"
    RPROMPT='%D{%L:%M:%S} '$RPROMPT
    echo "JAVA_HOME_11=$(/usr/libexec/java_home -v11)" | tee -a $shellrc
    echo "JAVA_HOME_8=$(/usr/libexec/java_home -v1.8)" | tee -a $shellrc
    echo "JAVA_HOME_14=$(/usr/libexec/java_home -v14)" | tee -a $shellrc
    echo "JAVA_HOME_17=/Library/Java/JavaVirtualMachines/temurin-17.jdk/Contents/Home" | tee -a $shellrc
    echo "JAVA_HOME_GRAAL=/Library/Java/JavaVirtualMachines/graalvm-ce-java17-22.3.0/Contents/Home" | tee -a $shellrc
    echo "JAVA_HOME=$JAVA_HOME_11" | tee -a $shellrc

    echo "alias j17="export JAVA_HOME=$JAVA_HOME_17"" | tee -a $shellrc
    echo "alias j14="export JAVA_HOME=$JAVA_HOME_14"" | tee -a $shellrc
    echo "alias j8="export JAVA_HOME=$JAVA_HOME_8"" | tee -a $shellrc
    echo "alias j11="export JAVA_HOME=$JAVA_HOME_11"" | tee -a $shellrc
}

set_python() {    
    echo "# python setting"
    echo "alias python=python3" | tee -a $shellrc
    echo "alias pip=pip3" | tee -a $shellrc
    sudo apt-get install python3-pip
    sudo apt-get install libffi-dev python-dev python3-dev
    python -m pip install --upgrade pip
    python -V
    eval "$(pyenv init -)"
}

set_directory() {
    echo "# directory setting"
    mkdir -p workspace
    mkdir -p workspace/privatespace
    mkdir -p workspace/publicspace
    mkdir -p Tools
}

set_docker() {
    echo "# docker setting"
    sudo apt-get update -y
    sudo apt-get install apt-transport-https ca-certificates curl gnupg-agent software-properties-common -y
    curl -fsSL https://download.docker.com/linux/ubuntu/gpg | sudo apt-key add -

    sudo add-apt-repository "deb [arch=amd64] https://download.docker.com/linux/ubuntu $(lsb_release -cs) stable"
    sudo apt-get install docker-ce docker-ce-cli containerd.io -y

    sudo usermod -aG docker $USER
    newgrp docker
}

set_git() {
    echo "# git setting"
}
 
set_k8s() {
    echo "# k8s setting"
    echo "alias k="kubectl"" | tee -a ~/.shellrc  
}


# get_resource_info
# set_java
# set_python
# set_directory
# set_k8s
print_env
get_resource_info
set_k8s

echo "# finish"