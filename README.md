## Supported cron expression
I used `https://crontab.guru/` as an inspiration for cron values.
The format is `<minute> <hour> <day of month> <month> <day of week>`

### Supported values for minute
```
*	any value
,	value list separator
-	range of values
/	step values
0-59	allowed values
```
### Supported values for hour
```
*	any value
,	value list separator
-	range of values
/	step values
0-24	allowed values
```
### Supported values for day of month
```
*	any value
,	value list separator
-	range of values
/	step values
0-31	allowed values
```
### Supported values for month
```
*	any value
,	value list separator
-	range of values
/	step values
0-12	allowed values
Jan-Dec	alternative single values
```
### Supported values for day of week
```
*	any value
,	value list separator
-	range of values
/	step values
0-12	allowed values
Sun-Sat	alternative single values
```

## Tests
You can run tests with `sbt test` command

## Executable artifact
You can build executable artifact with `sbt assembly` command. 
It will build a jar file named `cron-parser.jar` which will be located in `/target/scala-2.12/` directory

## Running the program
You can either run the artifact with `java -jar target/scala-2.12/cron-parser.jar "*/15 0 1,15 * 1-5 /usr/bin/find"` command 
or by running `com.dl.CronParser` from your IDE with a single program argument which should contain a cron expression with a command.

The reason to have it as a single program argument with space-separated values (instead of providing 6 arguments) is related to the `*` character:
when it is specified as one of a program's argument it will replace this argument with names of the files in current directory. 


## Examples

for `*/15 0 1,15 * 1-5 /usr/bin/find`:
```
minute         0 15 30 45
hour           0
day of month   1 15
month          1 2 3 4 5 6 7 8 9 10 11 12
day of week    1 2 3 4 5
command        /usr/bin/find
```

for `*/8 1,3,14,23 * Mar-Sep/2 Thu-Sat /usr/bin/find`:
```
minute         0 8 16 24 32 40 48 56
hour           1 3 14 23
day of month   1 2 3 4 5 6 7 8 9 10 11 12 13 14 15 16 17 18 19 20 21 22 23 24 25 26 27 28 29 30 31
month          Mar May Jul Sep
day of week    Thu Fri Sat
command        /usr/bin/find
```
