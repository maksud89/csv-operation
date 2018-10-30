# csv-operations
Generate CSV, based on user input


  What is it?
  -----------

This program is a java based CLI solution to-

1. Generate a CSV file and Feed data (First name, Last name and age) into this CSV. Number of lines of information and location of the CSV file will be user defined
2. A parser that will parse a CSV file and will insert this data into a Database, user will give input for the CSV file location and DB url(In this solution, just the DB name)

Solution Considerations:
------------------------

1. Derby file system database has been used to make it light weight
2. To generates random names and age, a 3 PP has been used, named Faker
3. Number of lines for CSV generation should be between 2 to 9999.
4. Maven has been used as a build tool
5. Development environemnt- Windows -10
6. Final jar has been tested on both Linux and Windows
7. Unit test has been included


How to run this
---------------------

1. Clone the https://github.com/maksud89/csv-operations repo
2. Build it with "mvn clean install"
3. Build process will generate 2 Jars, one without all external librarys and another one is fat jar that will include all external library, name - "csv-operations-1.0-jar-with-dependencies"
4. Run the generated jar as per the format below -

for CSV generating: 
-generate "\<FilePath\>/\<FileName\>.csv -\<NumberOfLines\>"

For Parsing:
-parse "\<FilePath\>/\<DbName\> -\<FilePath\>/\<FileName\>.csv"
 
 5. Every operation (generate/parse) type has 2 more inputs that are enclosed into double quote("") and separated by a space followed by a hyphen(-)  

6. An example to generate a CSV file,name- TEST_FILE.csv in the current directory with 100 lines:

> java -jar csv-operations-1.0-jar-with-dependencies.jar -generate "TEST_FILE.csv -100"

7. An example to parse a CSV file,name- TEST_FILE.csv in the current directory and a DB in the current directory name TEST_CSV_DB:

> java -jar csv-operations-1.0-jar-with-dependencies.jar -parse "TEST_CSV_DB -TEST_FILE.csv"



Performance Issue
---------------------

This program is not scalable, not suit to handle multiple request at the same time. Further imrpovement would be as below 
1. There will be total 4 services- CSV Data Generator, CSV Generator, Database Connection Pool service, Database Operation service. All those services will be exposed by the rest end point to make the service loosely coupled and scalable.
2. Instead of printing in the console, a logging framework (slf4j) should be used.
