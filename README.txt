# CustomerStatementProcessor
A console application to process the customer transaction records

Problem Statement:
  A bank receives monthly deliveries of customer statement records. This information is delivered in two formats, CSV and XML. These records need to be validated.
  There are two validations:
    * All transaction references should be unique
    * The end balance needs to be validated
  At the end of the processing, a report needs to be created which will display both the transaction reference and description of each of the failed records.
  
 Solution:
 Steps to execute:
  Step 1: Extract the CustomerStatementProcessor.rar 
  Step 2:Thare are two ways to execute the code:
          1 java -jar original-CustomerStatementProcessor-0.0.1-SNAPSHOT [path of the file to be processed]
          2. java -jar original-CustomerStatementProcessor-0.0.1-SNAPSHOT
   Note: Make sure that log4j.properties is in the same folder as that of the jar for a smooth execution.
   
Output: 
    The output file will be generated in case of a valid input file with the name failedTransactionReport[timestamp].csv
    A error_log.log file is generated containing exceptions generated during the execution.
   
Assumptions:
    1 It has been assumed that all the fields (Reference No, Account No, Description, Start Balance, Mutation and the End Balance) are            necessary for parsing.
    2. A failed transaction is one where either the reference numbers are not unique or the end balance is less than zero.
    
 Dependencies:
  Some of the main libraries used are:
    OpenCSV
    Log4j
    Junit5
    
 Improvements:
    1. Spring Boot could have been used to make a simple web application to take the file path from the user and provide the output csv           file for download.
    2. Concurrent processing can be used as large files will impact the performance of the code.
    3. A better way to handle the parsing of csv files with empty lines. The current solution is not the ideal solution.
    4. OpenCSV itself can be used for creating the output file as well.
    5. The property file can be made to get copied automatically to the location where the jar file is present.
