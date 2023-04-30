print('Start #################################################################');

use wishlistDB;

db = db.getSiblingDB('wishlistDB');
db.insert("{}")
db.createUser(
  {
    user: 'root',
    pwd: 'root',
    roles: [{ role: 'readWrite', db: 'aaaaaaaaaaaaaaa' }],
  },
);

print('END #################################################################');