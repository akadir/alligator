# Alligator

[![Build Status](https://travis-ci.org/akadir/alligator.svg?branch=master)](https://travis-ci.org/akadir/alligator)
[![gradle-version](https://img.shields.io/badge/gradle-5.5.1-brightgreen)](https://img.shields.io/badge/gradle-5.5.1-brightgreen)

Removes favorites using their status id's given in file.

### Usage
clone project and set required properties in alligator.properties and alligator.auth files, and then execute: 

```groovy
gradle run
```

#### alligator.properties

```properties
source-type=0
status-id-list-file=/path/to/statusFile
```

#### alligator.auth
```properties
#Twitter API Auth keys and options
consumer-key =
consumer-secret =
access-token = 
access-token-secret =
```


#### example status-id-file

```text
1
2
3
4
5
```
