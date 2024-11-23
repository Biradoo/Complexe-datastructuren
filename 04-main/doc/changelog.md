Changelog

Verwijderde documenten:
Documentation.md
Assignment (NL).md
Assignment (EN).md

1. binarySearch algoritme in de [MyArrayList](../src/nl/saxion/cds/datastructures/MyArrayList.java)

In de while-loop had ik eerst:
```java
int mid = left + right / 2
```

Dit zorgde voor problemen als Integer overflow, maar ook een niet-logische formule. 
Division gaat voor addition. Hier hield ik geen rekening mee en telde ik eigenlijk left + de helft van right op.

```
int left = 2
int right = 34

int mid = 2 + 34 / 2
mid = 2 + 17 = 19
```

Dit heb ik gewijzigd naar
```java
int mid = left + (right-left) / 2
```

```
int left = 2
int right = 34

int mid = 2 + (34-2) / 2
mid = 2 + 16 = 18
```

Dit klopt wel en dit is het midden van de deze 2 getallen. 
Hierdoor zijn ook de unit tests weer reproduceerbaar. 