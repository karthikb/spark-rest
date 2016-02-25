# This page is all about Company REST API

**Get a list of all companies**
```
curl --request GET http://localhost:4567/api/v1/companies
```

**Get details about a company**
```
curl --request GET http://localhost:4567/api/v1/companies/0132de80-9635-459f-b5c2-7f21b9368214
```

This assumes that there is a company with '''id ''''0132de80-9635-459f-b5c2-7f21b9368214'

**Create new company**
```
curl --request POST -H "Content-Type: application/json" http://localhost:4567/api/v1/companies --data '{"name": "karthik3", "address": "120 The Terrace", "city": "wellington", "country": "new zealand", "employees": [ { "name": "jeff" }, { "name": "deon" } ]}'
```

**Able to update a company / Able to add employee or owner of the company**
```
curl --request PUT -H "Content-Type: application/json" http://localhost:4567/api/v1/companies --data '{"id":"be6932a0-7461-4ed7-8408-333b34fcbcff", "name": "karthik31", "address": "120 The Terrace1", "city": "wellington1", "country": "new zealand1", "employees": [ { "name": "jeff1" }, { "name": "deon1" } ]}'
```