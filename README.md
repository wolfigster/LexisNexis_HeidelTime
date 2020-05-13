![Java CI with Gradle](https://github.com/wolfigster/LexisNexis_HeidelTime/workflows/Java%20CI%20with%20Gradle/badge.svg?branch=master)

# LexisNexis_HeidelTime
Java Application for grabbing &amp; tagging briefs

## Directory structure
```
rootdirectory
├── files
│   ├── csv
│   │   ├── *.csv
│   │   └── ...
│   ├── txt
│   │   ├── *.txt
│   │   └── ...
│   └── xml
│       ├── *.xml
│       └── ...
├── config.xml
├── LexisNexis_HeidelTime-e273d44-1.0.jar
├── list.txt
└── overview.csv
```

### config.xml example
```xml
<?xml version="1.0" encoding="UTF-8" standalone="no"?>
<!DOCTYPE properties SYSTEM "http://java.sun.com/dtd/properties.dtd">
<properties>
<entry key="client.secret">IAKUTKMWCFFIGELL8S7FFLSG170Y45</entry>
<entry key="client.id">AV3A9BM0IT2AMB36I3DMRTL4E0ENDZNK7J0KGDWL</entry>
<entry key="access.token">will be generated</entry>
<entry key="access.expired">1588609920215</entry>
</properties>
```

### Input: list.txt example
```
https://services-api.lexisnexis.com/v1/...
https://services-api.lexisnexis.com/v1/...
```

### Output: overview.csv example
|URN|TITLE           |DATE                |WORDLENGTH|DOCUMENT                                                   |
|---|----------------|--------------------|----------|-----------------------------------------------------------|
|urn:contentItem:AAAA-BBBB-CCCC-1234-00000-00|Document 1 Title|2020-05-01T00:00:00Z|4321      |Documents(DocumentId='AAAA-BBBB-CCCC-1234-00000-00')/$value|
|urn:contentItem:DDDD-EEEE-FFFF-1234-00000-00|Document 2 Title|2020-05-03T00:00:00Z|7654      |Documents(DocumentId='DDDD-EEEE-FFFF-1234-00000-00')/$value|
|urn:contentItem:UUUU-VVVV-WWWW-9876-00000-00|Document 3 Title|2020-05-05T00:00:00Z|3456      |Documents(DocumentId='UUUU-VVVV-WWWW-9876-00000-00')/$value|
|urn:contentItem:XXXX-YYYY-ZZZZ-9876-00000-00|Document 4 Title|2020-05-02T00:00:00Z|6789      |Documents(DocumentId='XXXX-YYYY-ZZZZ-9876-00000-00')/$value|
