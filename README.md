# Locking PoC

## Optimistic locking

Here we are presenting a very simple sample of how optimistic locking works with Spring Data Jdbc.

Just by marking a variable in our model class (User) with @Version we get this functionality (See https://docs.spring.io/spring-data/jdbc/docs/current/reference/html/#jdbc.entity-persistence.optimistic-locking).

### How to use

Send a http POST to http://localhost:8080/userOptimistics/optimistic with the following json body:

```json
{
    "name": "john",
    "lastname": "doe"
}
```
You will get the resulting saved element: 

```json
{
    "id": 1,
    "name": "john",
    "lastname": "doe",
    "version": 0
}
```
As you can see, both the id (auto increment from the DB), and the version were populated automatically.

To modify this you have to specify the very same version of the element you're trying to update. Using the following json:

```json
{
    "id": 1,
    "name": "john",
    "lastname": "doe",
    "version": null
}
```
Will get you an exception since this will be taken as if you try to add a new element. And if you use a version number 
different than the one from the record it will throw an OptimisticLockingFailureException.

In order to successfully update this record use this:

```json
{
    "id": 1,
    "name": "john",
    "lastname": "doe_edited",
    "version": 0
}
```

You will get a http 200 and the following response body: 

```json
{
    "id": 1,
    "name": "john",
    "lastname": "doe_edited",
    "version": 1
}
```

Notice how the version was incremented automatically for us. Now that will be the version number to use to update
this record.

## Pessimistic locking

We have also a brief example of how pessimistic locking works. To achieve this we created a query using
"SELECT FOR UPDATE" which locks the selected rows. Until such lock is released, no one can either read or write over it.

### How to use

Send a POST request to the endpoint http://localhost:8080/users/pessimistic like thw following one:

```json
{
    "name": "john",
    "lastname": "doe"
}
```

Then send two PUT requests, one to http://localhost:8080/users/pessimistic/10000 and the other
one to http://localhost:8080/users/pessimistic/3000

Request one: 
```json
{
    "id": 1,
    "name": "foo",
    "lastname": "aaa"
}
```
Request two:

```json
{
    "id": 1,
    "name": "foo_2",
    "lastname": "doo_2"
}
```

The numbers in the path are milliseconds to make the thread wait. The idea is to make the slower one first (10000) and
then the faster one (3000). The slower one should complete OK but the second one will throw an exception since the lock
was not released in time. This lock is way more severe than the other one: its goal is to prevent data inconsistence at
any means, even if it means blocking other operations and throwing exceptions.

#### Extra

More on locking (and pessimistic locking comparisons):

https://www.baeldung.com/jpa-optimistic-locking

https://stackoverflow.com/questions/129329/optimistic-vs-pessimistic-locking