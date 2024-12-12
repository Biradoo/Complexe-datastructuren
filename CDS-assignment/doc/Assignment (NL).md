# CDS Opdracht

## Inleiding

Het Nederlandse spoorwegnet is 3,2 duizend kilometer lang. Van het spoor is 
67 procent twee- of meersporig, ruim 70 procent is geëlektrificeerd. Vooral 
in het noorden en het oosten van ons land zijn de spoorwegen niet 
geëlektrificeerd. Het netwerk telt ongeveer 400 treinstations, dat is gemiddeld 
één station per 8,1 kilometer spoor, zie [CBS: Hoeveel spoorwegen zijn er in 
Nederland?](https://www.cbs.nl/nl-nl/visualisaties/verkeer-en-vervoer/vervoermiddelen-en-infrastructuur/spoorwegen).

De kosten van een reis per trein zijn afhankelijk van het aantal eenheden, 
zie "de Tariefeenhedenkaart van Nederland" op de [NS Voorwaarden site](https://www.ns.nl/voorwaarden.html)

## Requirements
Aan jou de taak om een proof of concept voor een spoormanagerapplicatie te 
bouwen met de volgende taken:
* Inlezen (bij de start) van alle stations van [stations.csv](../resources/stations.csv) en de 
  bijbehorende verbindingen van [tracks.csv](../resources/tracks.csv).
* Toon informatie van een station op basis van de stationscode.
* Toon informatie van een station op basis van (het begin van) de naam. Laat 
  de gebruiker een station kiezen indien er meer stations beginnen met het 
  gezochte deel van de naam.
* Toon alle stations van een bepaald type, bijv. 'stoptreinstation', 
  'megastation' enz. en toon een alphabetisch gesorteerde lijst van gevonden 
  stationsnamen.
* Bepaal de kortste route tussen twee te kiezen stations; toon de route en 
  de totale lengte.
* Bepaal welke spoorverbindingen (en de totale lengte) er minimaal nodig 
  zijn, zodat je van ieder station binnen Nederland naar ieder ander station 
  binnen Nederland kunt komen (minimum cost spanning tree, MCST).
* Het tonen van het spoornetwerk, de gevonden route en de MCST in een grafische 
  weergave is reden voor extra punten. Gebruik hiervoor de SaxionApp, JavaFX 
  of Swing. Een voorbeeldkaart van Nederland die je mag gebruiken staat in 
  [resources](../resources/Nederland.png); voorbeelden voor op deze kaart zijn 
  * HDR - Den Helder ongeveer op (600, 400),
  * MT - Maastricht ongeveer op (1024, 1840) en 
  * DZ - Delfzijl ongeveer op (1490, 150).

Maak een (console) applicatie met een menu om alle functionaliteiten aan te 
roepen.

## Implementatie
De applicatie moet de volgende implementaties bevatten:
* Een list implementatie op basis van de [SaxList](../src/nl/saxion/cds/collection/SaxList.java).
* Een ArrayList implementatie op basis van de [SaxList](../src/nl/saxion/cds/collection/SaxList.java),
  de [SaxSearchable](../src/nl/saxion/cds/collection/SaxSearchable.java) 
  en de [SaxSortable](../src/nl/saxion/cds/collection/SaxSortable.java) interfaces, dus met;
  * een binair zoekalgoritme,
  * een lineair zoekalgoritme,
  * een eenvoudig sorteeralgoritme bijv. SelectionSort of InsertionSort en
  * een QuickSort algoritme.
* Een hash map implementatie op basis van [SaxMap](../src/nl/saxion/cds/collection/SaxMap.java).
* Een Binary Search Tree (BST), bij voorkeur een AVL-tree, op basis van
  [SaxBinaryTree](../src/nl/saxion/cds/collection/SaxBinaryTree.java)
* Een queue op basis van de [SaxQueue](../src/nl/saxion/cds/collection/SaxQueue.java) 
* Een stack op basis van de [SaxStack](../src/nl/saxion/cds/collection/SaxStack.java)
* Op basis van de [SaxHeap](../src/nl/saxion/cds/collection/SaxHeap.java)
  interface een min-heap implementatie **op basis van een array**
* Een (generieke) Graph op basis van [SaxGraph](../src/nl/saxion/cds/collection/SaxGraph.java), met 
  * een depth first search (DFS) of een breadth first search (BFS) Iterator 
    implementatie,
  * het A*-algoritme,
  * het Dijkstra-algoritme en 
  * een MCST-algoritme (Prim of Kruskal).  
* **Java collection classes en interfaces ZIJN NIET TOEGESTAAN**, dus 
  niets gebruiken uit java.util, *behalve Comparator, Iterator en eventueel 
  Scanner*.

## Randvoorwaarden
* Maak documentatie in Markdown. Gebruik hiervoor [deze template](Documentation.md). 
  * Voor alle in paragraaf Implementatie genoemde algoritmen en klassen geldt dat:
    * Er is een link naar de broncode
    * Voor algoritmen geldt dat er ook een Big-Oh kwalificatie moet worden 
      toegevoegd en een korte uitleg waarom dit de juiste classificatie is. 
      _Deze classificatie en verklaring mag je opzoeken._
    * Beschrijf op hoofdlijnen hoe het is geïmplementeerd. Beschrijf ook welke 
      tests bewijzen dat jouw algoritme correct werkt en waarom.
  * Er is een hoofdstuk Technisch Ontwerp met
    * een klassendiagram met een korte beschrijving van de verschillende 
      klassen en belangrijkste methodes en
    * per uitgewerkte requirement een korte beschrijving van de implementatie 
      en van de gebruikte (eigen) implementaties van de SaxCollection 
    klasse(n); _voor iedere klasse die er wordt gebruikt een onderbouwde keuze 
    waarom er juist voor die klasse is gekozen en dus moet er ook altijd 
    tenminste 1 alternatief in de onderbouwing zijn meegenomen_. 
    **Neem bij een algoritme altijd de Big-Oh classificatie hierin mee** 
    (deze mag opgezocht worden).  
* Er moeten voor tenminste 75% (>90% geeft extra punten) dekkende geslaagde 
  testcases zijn uitgewerkt met JUnit 5.
  * **Met dekkend wordt bedoeld het minimum dekkingspercentage van class, 
    method, line en branch coverage.**
  * Voor velden met een restrictie moeten de good-weather en bad-weather 
    tests voorhanden zijn.
  * Getters en setters mogen worden uitgesloten van testdekking, mits deze 
    alleen bestaan uit het retourneren van de bijbehorende variabele 
    respectievelijk het wijzigen van de bijbehorende variabele. Markeer deze 
    methodes dan al zodanig (zie de sheets), zodat ze niet meetellen in de 
    dekkingspercentages.
  * Wanneer een Exception mogelijk is, moet die situatie getest worden; 
    test minimaal het juiste exceptie type.
  * **Zorg ervoor dat de voorbeelden die in het lesmateriaal staan in ieder 
    geval worden meegenomen in de testen**.

## Inlevering
Een juiste inlevering bevat:
* De code van het ontwikkelde programma; maak een zip export vanuit GitLab, 
  test deze export (werkt het allemaal nog?) en zorg dat er geen build 
  artefacts (out map) of de .git map in zit en er geen absolute pad-namen in
  de code zitten.
* De hiervoor beschreven [documentatie als Markdown bestand](Documentatie.md). 

**Een incomplete inlevering (ontbreken van code en/of documentatie en/of 
code verwijzingen) wordt NIET beoordeeld.**
