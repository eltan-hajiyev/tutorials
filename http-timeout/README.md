### This was done to demonstrate the behavior of readTimeout.

####We have:
1. Array of elements.
2. Service which returns first 3 elements with 5 second interval and rest of elements with 10 second interval.
3. Request to above service with HttpUrlConnection.

####Result:
1. For readTimeout less than 5 seconds, it will throw a SocketTimeoutException just after the timeout period.
2. For readTimeout between 5 and 10, a SocketTimeoutException will be thrown after the first 3 rows are returned.
3. Only for readTimeout greater than 10 seconds, it will return a success response.