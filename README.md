# Execution steps:

Download the original TSV files from https://www.imdb.com/interfaces and place them in a directory 

> (Note: The directory name must not contain whitespace, e.g., avoid names like "New Folder").

> Run the JAR file with JDK 25:

#### On Windows:

`java --enable-preview -jar matin-interview-assignment.jar --converter.file.path=E:\\sample-data2\\`
(This directory contains all TSV files)

#### On Unix-based systems:

`java --enable-preview -jar matin-interview-assignment.jar --converter.file.path=//home//matin//sample-data//`
(This directory contains all TSV files)

After data conversion, the following endpoints are available:

#### Second assignment question:

`localhost:8080/api/title/v1/list?directorWriterIsSame=true`


#### Third assignment question:

`localhost:8080/api/title/v1/list?actorIdList=123,147`


#### Fourth assignment question:

`localhost:8080/api/title/v1/bestEachYear/list?genre=Documentary`


#### And the last one:

`localhost:8080/actuator/metrics/http.server.requests`
`localhost:8080/actuator/prometheus`

#### H2 web console with imdbdb database name
`http://localhost:8080/h2-console`