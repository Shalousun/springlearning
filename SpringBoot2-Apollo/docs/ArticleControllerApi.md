
# Article Information Operation Interface
## Add article info
**URL:** http://spring.boot2.apollo.com/article/add

**Type:** POST

**Content-Type:** application/x-www-form-urlencoded


**Request-parameters:**

Parameter | Type|Description|Required
---|---|---|---
articleId|int|No comments found.|false
title|string|No comments found.|false
category|string|No comments found.|false


**Request-example:**
```
smart-doc currently cannot provide examples of parameters for the RequestParam request mode.
```
**Response-fields:**

Field | Type|Description
---|---|---
success|boolean|No comments found.
message|string|No comments found.
data|object|No comments found.
code|string|No comments found.
timestamp|string|No comments found.


**Response-example:**
```
{
	"success":true,
	"message":"success",
	"data":{
		"waring":"You may have used non-display generics."
	},
	"code":"00000",
	"timestamp":"2018-12-31 23:16:53"
}
```

## Update article info
**URL:** http://spring.boot2.apollo.com/article/update

**Type:** POST

**Content-Type:** application/x-www-form-urlencoded


**Request-parameters:**

Parameter | Type|Description|Required
---|---|---|---
articleId|int|No comments found.|false
title|string|No comments found.|false
category|string|No comments found.|false


**Request-example:**
```
smart-doc currently cannot provide examples of parameters for the RequestParam request mode.
```
**Response-fields:**

Field | Type|Description
---|---|---
success|boolean|No comments found.
message|string|No comments found.
data|object|No comments found.
code|string|No comments found.
timestamp|string|No comments found.


**Response-example:**
```
{
	"success":true,
	"message":"success",
	"data":{
		"waring":"You may have used non-display generics."
	},
	"code":"00000",
	"timestamp":"2018-12-31 23:16:53"
}
```

## Delete article by id
**URL:** http://spring.boot2.apollo.com/article/delete/{id}

**Type:** GET

**Content-Type:** application/x-www-form-urlencoded


**Request-parameters:**

Parameter | Type|Description|Required
---|---|---|---
id|int|article id|true


**Request-example:**
```
smart-doc currently cannot provide examples of parameters for the RequestParam request mode.
```
**Response-fields:**

Field | Type|Description
---|---|---
success|boolean|No comments found.
message|string|No comments found.
data|object|No comments found.
code|string|No comments found.
timestamp|string|No comments found.


**Response-example:**
```
{
	"success":true,
	"message":"success",
	"data":{
		"waring":"You may have used non-display generics."
	},
	"code":"00000",
	"timestamp":"2018-12-31 23:16:53"
}
```

## Query article by id
**URL:** http://spring.boot2.apollo.com/article/query/{id}

**Type:** GET

**Content-Type:** application/x-www-form-urlencoded


**Request-parameters:**

Parameter | Type|Description|Required
---|---|---|---
id|int|article id|true


**Request-example:**
```
smart-doc currently cannot provide examples of parameters for the RequestParam request mode.
```
**Response-fields:**

Field | Type|Description
---|---|---
success|boolean|No comments found.
message|string|No comments found.
data|object|No comments found.
└─articleId|int|No comments found.
└─title|string|No comments found.
└─category|string|No comments found.
code|string|No comments found.
timestamp|string|No comments found.


**Response-example:**
```
{
	"success":true,
	"message":"success",
	"data":{
		"articleId":630,
		"title":"m2mvcm",
		"category":"lz5p49"
	},
	"code":"00000",
	"timestamp":"2018-12-31 23:16:53"
}
```

## Pagination query article information
**URL:** http://spring.boot2.apollo.com/article/page/{offset}/{limit}

**Type:** GET

**Content-Type:** application/x-www-form-urlencoded


**Request-parameters:**

Parameter | Type|Description|Required
---|---|---|---
offset|int|page offset|true
limit|int|page limit|true


**Request-example:**
```
smart-doc currently cannot provide examples of parameters for the RequestParam request mode.
```
**Response-fields:**

Field | Type|Description
---|---|---
total|number|No comments found.
list|array|No comments found.


**Response-example:**
```
{
	"total":566,
	"list":{
		"object":"any object"
	}
}
```

