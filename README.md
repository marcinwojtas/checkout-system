### Simple checkout system

## Items
Add new item:

`POST /item`

```
{  
   "name":"foo",
   "prices":[  
      {  
         "quantity":1,
         "price":5
      },
      {  
         "quantity":5,
         "price":10
      }
   ]
}
```

Get one item:

`GET /item/{id}`

Get all items:

`GET /item`

## Promotions
Add new promotion:

`POST /promotion`

```
{  
   "items":[  
      "b11bfef1-7088-4db8-b815-bf3cb8202806",
      "b11bfef1-7088-4db8-b815-bf3cb8202807"
   ],
   "discount":10
}
```

Get one promotion:

`GET /promotion/{id}`

Get all promotions:

`GET /promotion`

## Orders

Add new order:

`POST /order`

```
{  
   "items":[  
      {  
         "id":"b11bfef1-7088-4db8-b815-bf3cb8202806",
         "quantity":1
      },
      {  
         "id":"c11bfef1-7088-4db8-b815-bf3cb8222806",
         "quantity":4
      }
   ]
}
```

Update order:

`PUT /order/{id}`

```
{  
   "items":[  
      {  
         "id":"b11bfef1-7088-4db8-b815-bf3cb8202806",
         "quantity":1
      },
      {  
         "id":"c11bfef1-7088-4db8-b815-bf3cb8222806",
         "quantity":3
      }
   ]
}
```

Confirm order:

`PUT /order/{id}/confirm`

Get one order:

`GET /order/{id}`