#!/bin/sh

mongo  --username root --password root && use wishlistDB  && db.init_db_test.insert()

