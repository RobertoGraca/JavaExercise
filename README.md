# JavaExercise

## To run the application: 
$ cd code-with-quarkus
$ quarkus dev

The server starts running at http://localhost:8080

To test the API send GET requests to http://localhost:8080/labseq/{n}



## Code explanation:
The program makes use of two caches: one for the endpoint and another for the result computation. 
The program starts by ignoring anything that is not a non-negative number.
Then, checks the endpoint cache for the current request. If the cache has that key, it returns the corresponding value. Otherwise, it computes the result and stores it.

The result computation starts by initializing the result variable and teh second cache.
The second cache is initialized with the default values defined in the question sheet.
Then, it updates itself based on the current values that it holds. It always holds the most recent four values. That is, computes the newest value and discards the oldest. When the number of iterations finish the value present in the result variable is the solution.
