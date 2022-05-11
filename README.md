## **Simple CRUD - MongoDB with Redis Cache**
### **MongoDB, Redis, Spring Boot**

* Follow the steps below to run/test this app.
1. Start MongoDB server
   * If it is installed, it already runs as a service in the background
2. Start Redis server
3. Use "Postman", "MongoDB Compass" and "redis-cli" to test and check all CRUD functionalities
   * If something is cached in Redis, you can check it by running "KEYS *" in redis-cli
   * Do not forget to run "flushall" in redis-cli if you need to delete all the keys of all the existing databases
