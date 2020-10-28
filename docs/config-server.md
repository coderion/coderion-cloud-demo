### Encryption and Decryption

https://cloud.spring.io/spring-cloud-config/reference/html/#_encryption_and_decryption

1. Download [Java Cryptography Extension (JCE) Unlimited Strength Jurisdiction Policy Files](https://www.oracle.com/java/technologies/javase-jce8-downloads.html)
2. Copy both jars to `<JAVA>/jre/lib/security`
3. Set encryption key:
    1. Variable `encrypt.key` in file `bootstrap.yml` or
    2. `export ENCRYPT_KEY=YOURKEY`
4. Encrypt your password: `curl localhost:8888/encrypt -d mysecret`
5. Encrypt your password: `curl localhost:8888/decrypt -d fb4432df22`
6. Store encrypted password preceded by `{cipher}`:
```
spring:
  datasource:
    username: dbuser
    password: '{cipher}FKSAJDFGYOS8F7GLHAKERGFHLSAJ'
```