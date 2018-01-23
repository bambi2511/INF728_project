#!/bin/bash

#################################################
###  INSTALLATION MONGODB (on Ubuntu 16.04 )  ###
#################################################

# Source = https://docs.mongodb.com/manual/tutorial/install-mongodb-on-ubuntu/


# 1) Import the public key used by the package management system
# ==============================================================
sudo apt-key adv --keyserver hkp://keyserver.ubuntu.com:80 --recv 2930ADAE8CAF5059EE73BB4B58712A2291FA4AD5


# 2) Create a list file for MongoDB
# =================================
echo "deb [ arch=amd64,arm64 ] https://repo.mongodb.org/apt/ubuntu xenial/mongodb-org/3.6 multiverse" | sudo tee /etc/apt/sources.list.d/mongodb-org-3.6.list


# 3) Reload local package database
# ================================
sudo apt-get update


# 4) Install the MongoDB packages (the latest stable version of MongoDB)
# ======================================================================
sudo apt-get install -y mongodb-org


# 5) Creat directory /data/db
# ===========================
sudo mkdir -p /data/db
