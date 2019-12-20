# DBManager
This is the repository to execute queries on any JDBC supported Database.

# Currently Supported databases are:
- MySQL
- Postgres

# Supported operations:
- # Select
Returns List<LinkedHashMap<String, String>> while executing select queries
where first String refers to the column name and second String shows the value of that column.

- # Update:
Returns the no. of rows affected while executing update commands
