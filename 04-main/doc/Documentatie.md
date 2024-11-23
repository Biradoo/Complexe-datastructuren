# Algorithms and classes

## MyArrayList
Implementation: [MyArrayList](../src/nl/saxion/cds/datastructures/MyArrayList.java)

### Mijn binair zoekalgoritme
Classificatie: O(Log(N))

Dit komt omdat het algoritme steeds het midden van het zoekbereik kiest en vervolgens het zoekbereik halveert.
Hierdoor wordt bij elke stap het zoekbereik gehalveerd. Deze logaritmische complexiteit is ideaal voor gesorteerde lijsten

Implementation: left en right zijn de grenzen van de lijst. Terwijl left <= right wordt in de loop een binaire zoekstap uitgevoerd.
midElement wordt opgehaald en die wordt vergeleken met de gegeven element. Best-case is de midElement gelijk aan element en wordt deze teruggegeven.

Als midElement kleiner is dan het gezochte element, wordt de linkergrens left aangepast naar mid + 1 (de rechterhelft wordt doorzocht).

Als midElement groter is, wordt de rechtergrens right aangepast naar mid - 1 (de linkerhelft wordt doorzocht).

Proof of Tests: [GivenLargeList_WhenMakingChanges_ConfirmStateRemainsCorrect()](../test/collection/TestMyArrayList.java) (laatste test van de file)
LET OP: Om de test te runnen, moet je het gedeelte linearSearch uit commenten. Op 1 of andere manier kunnen ze niet beide tegelijk getest worden.

Deze test was ons gegeven door de docent en confirmed de werking van binarySearch()

### Mijn lineair zoekalgoritme
Classificatie: O(N)

Dit komt doordat het algoritme door elke index in de lijst loopt om te controleren of het element overeenkomt met het gezochte element. 
In het slechtste geval moet het algoritme elk element doorzoeken totdat het het juiste vindt of de lijst volledig heeft doorlopen zonder een match te vinden. 
Hierdoor is het zoeken lineair afhankelijk van het aantal elementen in de lijst.

Implementatie: het algoritme begint bij het eerste index en loopt met een for-loop door elk element in de lijst.
Bij elk element vergelijkt het gezochte element met de element in de index. 

Proof of Tests: [GivenLargeList_WhenMakingChanges_ConfirmStateRemainsCorrect()](../test/collection/TestMyArrayList.java) (laatste test van de file)

Deze test was ons gegeven door de docent en confirmed de werkingg van linearSearch()

### Mijn QuickSort algoritme
Classificatie: O(n log n) en een worst-case complexiteit van O(n²)

Implementatie: quickSort is een recursieve methode en doet 3 dingen:

1. Recursieve sortering: 
   1. Voor elk opgegeven bereik (begin tot end) wordt een “pivot” geselecteerd met splitInPlace. 
   2. De lijst wordt zo gesplitst dat alle elementen links van de pivot kleiner zijn en alle elementen rechts groter. 
   3. Vervolgens wordt quickSort opnieuw aangeroepen voor de subreeksen links en rechts van de pivot, wat de lijst opdeelt in steeds kleinere gesorteerde deelsegmenten totdat het volledig gesorteerd is.
2. Splitsing (splitInPlace):
   1. De splitInPlace-methode kiest het eerste element in het bereik als pivot. 
   2. Twee indices (left en right) bewegen door het bereik om elementen te sorteren ten opzichte van de pivot. 
   3. Als elements[left] groter is dan de pivot en elements[right] kleiner, worden deze twee geswapt om de elementen aan de goede kant van de pivot te plaatsen. 
   4. Nadat de indices elkaar kruisen, wordt de pivot naar de juiste positie geswapt, en retourneert splitInPlace de nieuwe index van de pivot.
3. Swaps
   1. De elementen worden uitgewisseld met de swap-methode om de ordening rondom de pivot aan te passen, zodat elementen correct gepositioneerd zijn.

Proof of Tests: [GivenListWithIntegers_WhenQuicksorted_ThenListIsSorted()](../test/collection/TestMyArrayList.java)

In deze test geven we een ongesorteerde lijst mee. Daarna wordt quickSort aangeroepen en wordt er opnieuw gekeken met de isSorted method of de lijst is gesorteerd.
De test slaagt. Ook loopen we door de gehele lijst heen en checken we dat elke waarde groter is dan de vorige waarde.

### Mijn simpleSort algoritme
Classificatie: O(n^2)

Dit komt doordat de algoritme 2 geneste loops bevat. De buitenste loop doorloopt elk element (n iteraties),
en voor elk element zoekt de binnenste loop naar het kleinste element in het resterende bereik (ook n iteraties). 
Hierdoor wordt het aantal vergelijkingen en swaps kwadratisch naarmate de lijst groeit. 

Implementatie: 

1. Loop door de lijst: Bij elk stap wordt een index smallest gekozen om het kleinste element vanaf die positie bij te houden. 
2. Zoeken naar kleinste element: voor elk element index zoekt de binnenste loop naar het kleinste element tussen index en het einde van de lijst. 
De comparator vergelijkt en past zo nodig smallest-index aan.
3. Swappen: Nadat de binnenste loop klaar is, is smallest de index van het kleinste element. Het kleinste element wordt geswapt met het element op index om het in de juiste volgorde te plaatsen.
Dit proces wordt herhaald voor elk element in de lijst

Proof of Test: [GivenLargeList_WhenMakingChanges_ConfirmStateRemainsCorrect()](../test/collection/TestMyArrayList.java)

In deze test hebben we een ongesorteerde lijst. Nadat simpleSort wordt gebruikt voor deze lijst is de lijst gesorteerd. 
Dit checken we met de methode isSorted()

## MyBST (AVL-Tree)
Implementatie:  [MyAVLTree](../src/nl/saxion/cds/datastructures/MyAVLTree.java)

MyAVLTree is een zelfbalancerende binaire zoekboom (AVL Tree) die het opslaan, zoeken, en verwijderen van key-value paren ondersteunt. Door zelfbalancering blijft de AVL Tree efficiënt, 
zelfs bij grote hoeveelheden gegevens, omdat deze ervoor zorgt dat het verschil in hoogte tussen de linker- en rechterkant van elke node maximaal één blijft. Dit garandeert een logaritmische tijdscomplexiteit van O(log N) voor zoekopdrachten, toevoegingen en verwijderingen.

### Balancering en Hoogtebeheer
MyAVLTree houdt bij elke node de hoogte bij om balansfactoren te berekenen. De balansfactor van een node wordt bepaald door het verschil tussen de hoogte van de linker- en rechterkinderen. Een balansfactor buiten het bereik van -1 tot 1 duidt op een onbalans, die met een rotatie wordt opgelost.

Er zijn vier mogelijke rotaties om de balans te herstellen:

1. Rechtsrotatie - voor een linker-linkerkant onbalans.
2.	Linksrotatie - voor een rechter-rechterkant onbalans.
3.	Links-Rechts rotatie - voor een linker-rechterkant onbalans.
4.	Rechts-Links rotatie - voor een rechter-linkerkant onbalans.

De AVL Tree gebruikt de balansfactor en hoogte bij iedere wijziging (toevoegen/verwijderen) om zo nodig de boom in balans te brengen met de juiste rotatie.

### Zoekfunctionaliteit
Om een element te vinden, begint MyAVLTree bij de root en gebruikt het vergelijkingen om door de boom te navigeren. Elke node bevat een key die vergeleken wordt met de target key; 
MyAVLTree zoekt links voor kleinere keys en rechts voor grotere keys, totdat het element gevonden is of er een null-referentie wordt bereikt.

### Toevoegen van Elementen
Bij het toevoegen van een element wordt een nieuwe node op de juiste positie ingevoegd zoals bij een binaire zoekboom. Na het invoegen worden de hoogtes bijgewerkt en controleert de boom de balans van elke node in het pad terug naar de root. 
Indien nodig wordt een rotatie toegepast om de boom in balans te houden.

### Verwijderen van Elementen en Rehashing
Bij het verwijderen van een element worden drie scenario’s behandeld:

1. Geen child nodes: de node wordt direct verwijderd.
2. Eén child node: de node wordt verwijderd en de child node vervangt de verwijderde node.	
3. Twee kinderen: de node wordt vervangen door de in-order opvolger (het kleinste element in het rechteronderdeel) of voorganger (het grootste element in het linkeronderdeel). De opvolger wordt vervolgens uit de boom verwijderd.

Na het verwijderen controleert en herstelt de boom de balans door rotaties toe te passen, indien nodig.

Proof of Test [TestMyAVLTree](../test/collection/TestMyAVLTree.java)

De MyAVLTree-implementatie is uitgebreid getest met JUnit-tests. De geteste scenario’s omvatten:

1. Balancering: Validatie van de correcte uitvoering van rotaties (links, rechts, links-rechts, rechts-links) bij toevoegen en verwijderen.
2. Opzoeken: Tests om te controleren of bestaande keys worden gevonden en niet-bestaande keys null retourneren.
3. Toevoegen en Verwijderen: Verificatie dat de AVL Tree correct werkt na invoegen en verwijderen, waarbij de balans van de boom behouden blijft.
4. Exception Handling: Tests voor het afhandelen van uitzonderingen zoals het zoeken of verwijderen van elementen in een lege boom.

Deze testdekking verzekert dat de kernfunctionaliteit van MyAVLTree werkt zoals verwacht en dat de boom gebalanceerd blijft na elke operatie.

## MyHashMap
Implementatie: [MyHashMap](../src/nl/saxion/cds/datastructures/MyHashMap.java)

MyHashMap maakt gebruik van een array van Bucket-objecten. Elk Bucket object bevat een Key en een value die samen een pair vormen. Deze array met buckets is de basisopslag voor de key-value pairs. 
In deze implementatie wordt een initiële capaciteit van 16 buckets gebruikt, en dit kan worden uitgebreid indien nodig.

Om de locatie van een key-value pair te bepalen, gebruikt MyHashMap de hashcode van de sleutel. De hashcode wordt omgezet in een index binnen de grenzen van de array door het modulo te nemen met de lengte van de array met buckets. Hierdoor wordt een unieke index berekend waar de pair opgeslagen of gevonden kan worden. 

Wanneer twee keys dezelfde index opleveren (een collision), wordt lineaire probing toegepast. Dit betekent dat, als de berekende index al bezet is, het algoritme de volgende index controleert (en steeds verder zoekt als nodig) totdat een lege plek is gevonden. Zo blijft de structuur werken zonder aparte “chains” of extra arrays binnen een bucket.
 
Om een opgeslagen waarde te vinden, berekent MyHashMap opnieuw de hash-index van de sleutel en zoekt lineair verder bij botsingen totdat het gewenste paar gevonden is.
 
Bij het verwijderen van een key-value pair wordt de positie van de pair op null gezet. Hierna wordt een “rehashing” uitgevoerd voor de elementen die na de verwijderde positie staan. 
Dit is nodig om te zorgen dat bij lineaire probing de vindbaarheid van elementen intact blijft en geen gaten ontstaan die het zoeken verstoren.

Proof of Test [TestMyHashMap](../test/collection/TestMyHashMap.java)

Alle methodes binnen MyHashMap zijn getest met Junit. Hierdoor kunnen we ervan uitgaan dat de kernfunctionaliteiten werken zoals behoren. Hetgeen wat onder andere is getest:
- Basisfunctionaliteit van een Hashmap
- Exception handling
- Toevoegen en opzoeken van elementen
- Verwijdering en consistentie na het rehashen
- Het teruggeven van keys en values

## MyMinHeap
Implementatie: [MyMinHeap](../src/nl/saxion/cds/datastructures/MyMinHeap.java)

De MyMinHeap klasse is een generieke implementatie van een min-heap datastructuur. Deze datastructuur wordt gebruikt om het kleinste element efficiënt te vinden en te verwijderen. De implementatie gebruikt een array-gebaseerde aanpak, 
waarbij de heap-eigenschap wordt gebruikt bij het invoegen en verwijderen van elementen.

### Methodes

- insert(V item): O(log n), omdat het element na het toevoegen aan het einde van de array naar boven “bubblet” om de heap-eigenschap te behouden. Het aantal vergelijkingen en mogelijke swaps is logaritmisch met betrekking tot het aantal elementen in de heap.
- extractMin(): O(log n), omdat het verwijderen van het minimale element (de root) vereist dat het laatste element in de array naar de root wordt verplaatst en vervolgens “bubble down” wordt uitgevoerd om de heap-eigenschap te behouden. 
Dit proces heeft een logaritmische tijdscomplexiteit, aangezien het in het ergste geval nodig is om de tree tot aan de leafs door te lopen.
- decreaseKey(int i, V key): O(log n), omdat deze methode de waarde verlaagt van een key op een gegeven index. Nadat de key is bijgewerkt, kan “bubble up” nodig zijn om de heap-eigenschap te behouden. Dit proces kost logaritmische tijd vanwege het mogelijk omhoog gaan door de boom.

Proof of Test [TestMyMinHeap](../test/collection/TestMyMinHeap.java)

Alle methodes binnen MyMinHeap zijn getest met Junit. Hierdoor kunnen we ervan uitgaan dat de kernfunctionaliteiten werken zoals behoren. Hetgeen wat onder andere is getest:
- Basisfunctionaliteit van een MinHeap
- Exception handling

## MyStack
Implementatie: [MyStack](../src/nl/saxion/cds/datastructures/MyStack.java)

MyStack is een datastructuur die elementen volgt volgens het Last-In-First-Out (LIFO)-principe. De implementatie maakt gebruik van MyArraylist om elementen op te slaan en biedt de kernfunctionaliteit van een stack: 
elementen toevoegen (push), verwijderen (pop), en het bovenste element bekijken zonder verwijdering (peek). Hieronder een overzicht van de werking:

- Push: De push-methode voegt een nieuw element aan het einde van de lijst toe, wat overeenkomt met de bovenkant van de stack in een LIFO-context.
- Pop: De pop-methode verwijdert het laatste element van de lijst, oftewel het bovenste element van de stack. Als de stack leeg is, gooit de methode een EmptyCollectionException om aan te geven dat er geen elementen zijn om te verwijderen.
- Peek: De peek-methode returned het laatste element van de lijst zonder dit te verwijderen. Als de stack leeg is, wordt een EmptyCollectionException gegooid.

Proof of Test [TestMyStack](../test/collection/TestMyStack.java)

Alle methodes in MyStack zijn getest met JUnit, wat bewijst dat de kernfunctionaliteit correct werkt onder verschillende scenario’s. Hieronder een overzicht van wat door de tests is bewezen:

- Basisfunctionaliteit van een Stack
- Exception handling
- Pushen van een element
- Poppen van een element
- Peeking

## MyQueue
Implementatie: [MyQueue](../src/nl/saxion/cds/datastructures/MyQueue.java)

MyQueue is een datastructuur die werkt volgens het First-In-First-Out (FIFO)-principe. De implementatie maakt gebruik van een MyLinkedList. Er is gekozen voor een LinkedList, omdat een LinkedList zeer geschikt is om bewerking te maken, omdat het O(1) tijd kost en een LinkedList dynamisch groeit, zonder dat er een resizing nodig is.
De implementatie biedt de mogelijkheid om de elementen op te slaan, en biedt de kernfunctionaliteiten van een queue: 
elementen toevoegen (enqueue), verwijderen (dequeue), en het voorste element bekijken zonder verwijdering (peek). Hieronder een overzicht van de werking:

- Enqueue: De enqueue-methode voegt een nieuw element toe aan het einde van de linked list. Dit representeert het achteraan toevoegen in de queue met het FIFO-principe.
- Dequeue: De dequeue-methode verwijdert het eerste element uit de lijst, oftewel het voorste element van de queue. Als de queue leeg is, gooit de methode een EmptyCollectionException om aan te geven dat er geen elementen zijn om te verwijderen.
- Peek: De peek-methode returned het eerste element van de lijst zonder dit te verwijderen. Als de queue leeg is, wordt een EmptyCollectionException gegooid.

Proof of Test [TestMyQueue](../test/collection/TestMyQueue.java)

De functionaliteit van MyQueue is getest met JUnit-tests, die bewijzen dat de kernfunctionaliteiten werken zoals bedoeld. Deze tests omvatten onder andere:

- Basisfunctionaliteit van een Queue
- Exception handling
- enqueueing van een element 
- dequeueing van een element
- Peeking

## MyPriorityQueue
Implementatie: [MyPriorityQueue](../src/nl/saxion/cds/datastructures/MyPriorityQueue.java)

MyPriorityQueue is een generieke implementatie van een priority queue, gebaseerd op de min-heap datastructuur. Dit maakt het mogelijk om elementen in prioriteitsvolgorde efficiënt in te voegen en te verwijderen. 
De prioriteit wordt bepaald door de waarde van de elementen, waarbij de kleinste waarde als eerste wordt verwijderd.

### Methodes

- enqueue(V value): O(log n). Bij het toevoegen van een element aan de heap wordt het toegevoegd aan het einde van de interne array en vervolgens omhoog gebubbeld om de heap-eigenschap te behouden.
- dequeue(): O(log n). Haalt het element met de hoogste prioriteit (het kleinste element) uit de queue en verplaatst het laatste element naar de top, waarna het naar beneden bubbelt om de heap-eigenschap te behouden.

Proof of Test [TestMyPriorityQueue](../test/collection/TestMyPriorityQueue.java)

Alle methodes in de MyPriorityQueue zijn getest met JUnit. De tests omvatten verschillende scenario’s om te zorgen dat de functionaliteiten zoals verwacht werken. De belangrijkste aspecten die getest zijn:

- De correcte werking van enqueue, dequeue en peek.
- Exception handling.

## MyGraph
Implementatie: [MyGraph](../src/nl/saxion/cds/datastructures/graph/MyGraph.java)

### Mijn iteratieve depth first search algoritme
Classificatie: **O(V + E)**

De tijdcomplexiteit van de iteratieve Depth-First Search (DFS) in deze implementatie is O(V + E), waarbij:

- V het aantal nodes (vertices) in de grafiek is.
- E het aantal verbindingen (edges) tussen de nodes is.

Deze classificatie komt voort uit de volgende redenering:
- Elke node wordt slechts één keer bezocht, waarbij alle aangrenzende nodes worden geëvalueerd, wat O(V) tijd kost.
- Elk van de verbindingen (edges) wordt slechts één keer verwerkt tijdens de zoektocht, wat O(E) tijd kost.
- Omdat zowel nodes als verbindingen slechts één keer worden verwerkt, is de totale complexiteit O(V + E).

### Implementatie

[DFSIterator](../src/nl/saxion/cds/datastructures/graph/DFSIterator.java)

De DFSIterator-klasse implementeert de iteratieve DFS door gebruik te maken van een stack (MyStack<V>) en een visited-map om bij te houden welke nodes al zijn bezocht. Dit voorkomt dubbele bezoeken aan nodes en zorgt ervoor dat elke node en verbinding efficiënt wordt verwerkt.

Belangrijkste componenten van de implementatie:
1. Initialisatie:
- Bij het aanmaken van de DFSIterator wordt een startnode op de stack geplaatst. Als er geen expliciete startnode is, wordt de eerste beschikbare node uit de adjacencyList gebruikt.
- De visited-map houdt bij welke nodes al zijn doorlopen.
2. Iteratie door de graph:
- Met de next()-methode worden de nodes in een DFS volgorde doorlopen.
- Bij het bezoeken van elke node worden alle niet-bezochte aangrenzende nodes op de stack geplaatst voor verdere verkenning, wat de DFS-structuur van de iteratie behoudt.

MyGraph en depthFirstTraversal

De depthFirstTraversal-methode in de MyGraph-klasse maakt gebruik van de DFSIterator om de DFS-traversie iteratief uit te voeren en de bezochte nodes in een lijst (SaxList<V>) te verzamelen. Dit zorgt ervoor dat nodes in de volgorde van een diepte-eerst-zoektocht worden doorlopen en geretourneerd.

Proof of Test [TestMyDFSAlgo](../test/collection/graph/TestMyDFSAlgo.java)

De correctheid van het DFS-algoritme wordt geverifieerd door middel van unittests in de klasse TestMyDFSAlgo. Deze tests omvatten:

1. Initiële configuratie: Een graph wordt opgezet met nodes en verbindingen om verschillende scenario’s te testen, zoals verbonden en geïsoleerde nodes.
2. Correctheid van DFS-volgorde: De tests verifiëren dat het algoritme de nodes in de juiste diepte-eerst-volgorde doorloopt en teruggeeft.
3. Testgevallen met geïsoleerde nodes: Er wordt getest dat geïsoleerde nodes correct worden afgehandeld en alleen zichzelf als resultaat teruggeven wanneer ze worden doorlopen.

### Mijn Dijkstra algoritme
Classificatie: **O((V + E) * log(V))**

De tijdcomplexiteit van het Dijkstra-algoritme in deze implementatie is **O((V + E) * log(V))**, waarbij:
- **V** het aantal nodes (vertices) in de grafiek is.
- **E** het aantal verbindingen (edges) tussen de nodes is.

Deze classificatie komt voort uit de volgende redenering:
- Elke node wordt één keer verwerkt, wat **O(log V)** tijd kost vanwege de prioriteitswachtrij-operaties (invoegen en verwijderen).
- Elke verbinding wordt één keer relaxed, wat een update in de prioriteitswachtrij vereist, wat ook **O(log V)** kost.
- Omdat elke node en elke verbinding slechts één keer wordt verwerkt, wordt de totale complexiteit **O((V + E) * log(V))**.

### Implementatie

[DijkstraNode](../src/nl/saxion/cds/datastructures/graph/DijkstraNode.java)

De MyGraph-klasse implementeert Dijkstra's algoritme in de shortestPathsDijkstra-methode. De belangrijkste componenten van deze implementatie zijn:

1. Initialisatie van Afstanden:
   - Voor elke node in de grafiek wordt een afstandswaarde geïnitialiseerd naar oneindig (Double.POSITIVE_INFINITY) om aan te geven dat de node nog niet bezocht of berekend is.
   - De afstand van de startNode naar zichzelf wordt ingesteld op 0.0, omdat er geen reistijden zijn om de startNode vanuit zichzelf te bereiken.

2. PriorityQueue voor Optimale Padselectie:
   - Het algoritme maakt gebruik van een PriorityQueue om altijd de node met de kortst bekende afstand als eerste te verwerken.
   - Deze PriorityQueue wordt beheerd met DijkstraNode<V>-objecten, die elke node en zijn huidige kortste afstand vanaf de startNode opslaan.
   - Nodes met kortere afstanden worden als eerste verwerkt, wat ervoor zorgt dat de kortste paden efficiënt worden berekend.

3. Edge Relaxatie:
   - Voor elke Node die uit de PriorityQueue wordt verwijderd, worden alle aangrenzende nodes bekeken.
   - Voor elke buur berekent het algoritme de mogelijke nieuwe afstand door de afstand van de huidige node en het gewicht van de verbinding naar de buurman op te tellen.
   - Als deze nieuwe afstand korter is dan de eerder geregistreerde afstand voor de buurman, werkt het algoritme de afstand van de buurman bij en voegt deze opnieuw toe aan de PriorityQueue met de bijgewerkte afstand.

4. Constructie van het Resultaatgrafiek:
   - Terwijl het algoritme voortschrijdt, bouwt het een resultaatgrafiek (een andere instantie van MyGraph) op die alleen de verbindingen en paden bevat die de kortste padenboom vormen vanaf de startNode.
   - Dit maakt het efficiënt ophalen en weergeven van de kortste paden mogelijk zodra het algoritme voltooid is.

Proof of Test [TestMyDijkstraAlgo](../test/collection/graph/TestMyDijkstraAlgo.java)

De correctheid van Dijkstra's algoritme wordt geverifieerd door een reeks unittests, specifiek de test GivenALG_WhenCalculatingShortestPathFromStartingNode_ThenPathsAreCorrectlyCalculatedUsingDijkstra. Deze test omvat:

- Initialisatie van de Graph: Een graph wordt opgezet met knopen (X, Y, Z, enz.) en gewogen verbindingen, zoals gespecificeerd in de setup.
- Verwachte Kortste Paden: De test berekent de kortste paden vanaf een gegeven startNode (X) naar andere nodes en verifieert de verwachte afstanden.
- Assertions: Elke afstandsberekening wordt gecontroleerd met assertions om ervoor te zorgen dat de afstanden overeenkomen met de verwachte waarden. Dit bewijst dat het algoritme de kortste paden correct identificeert in de gegeven grafiekstructuur.

### Mijn A* algoritme
Classificatie: **O((V + E) * log(V))**

De tijdcomplexiteit van het A*-algoritme in deze implementatie is O((V + E) * log(V)), waarbij:

- V het aantal nodes (vertices) in de grafiek is.
- E het aantal verbindingen (edges) tussen de nodes is.

Deze complexiteit komt voort uit de volgende redenering:

- Elke node wordt slechts één keer verwerkt. Dit vergt O(log V) tijd vanwege de bewerkingen met de PriorityQueue (invoegen en verwijderen).
- Elke verbinding wordt één keer geanalyseerd, wat ook een update in de PriorityQueue kan vereisen en daardoor O(log V) tijd kost.
- Aangezien zowel de nodes als de verbindingen maar één keer worden verwerkt, resulteert dit in een totale complexiteit van O((V + E) * log(V)).

### Implementatie

[HeuristicEstimator](../src/nl/saxion/cds/datastructures/graph/HeuristicEstimator.java)

De MyGraph-klasse implementeert het A*-algoritme in de shortestPathAStar-methode. De belangrijkste onderdelen van deze implementatie zijn:

1.	Initialisatie van G-scores en PriorityQueue:
- Elke node krijgt een initiële g-waarde (de afstand vanaf de startNode) van oneindig (Double.POSITIVE_INFINITY), waarmee wordt aangegeven dat de knoop nog niet berekend of bezocht is.
- De startNode krijgt een g-waarde van 0.0, aangezien er geen kosten zijn om vanuit de startNode zelf te bereiken.
- De PriorityQueue, openSet, bevat nodes die moeten worden verwerkt, waarbij de prioriteit wordt bepaald door de som van de g-waarde en de heuristische geschatte afstand tot de endNode (f-waarde).
 2.	Heuristische Schatting en Padselectie:
 •	Het algoritme maakt gebruik van een heuristische schatter (een Estimator-implementatie) om de afstand van elke node naar de endNode te schatten. Deze schatting (h-waarde) beïnvloedt de prioriteit van elke node in de wachtrij.
 •	Door deze f-waarde (g + h) te minimaliseren, selecteert het algoritme de node die het snelste naar de endNode kan leiden.
 3.	Relaxatie van Verbindingen (Edges):
 •	Voor elke node die uit de wachtrij wordt verwijderd, worden de aangrenzende nodes (buren) geanalyseerd.
 •	Voor elke buur berekent het algoritme de mogelijke nieuwe g-waarde door de huidige g-waarde van de huidige node op te tellen bij het gewicht van de verbinding naar de buur.
 •	Als deze nieuwe g-waarde korter is dan de reeds geregistreerde g-waarde voor die buur, werkt het algoritme deze g-waarde bij en voegt de buur opnieuw toe aan de queue met de bijgewerkte f-waarde.
 4.	Padreconstructie:
 •	Zodra de endNode wordt bereikt, reconstrueert het algoritme het kortste pad door terug te gaan via de verwijzingen van elke node in de queue. Dit vormt het resulterende kortste pad van de startNode naar de endNode.

Proof of Test [TestMyAStarAlgo](../test/collection/graph/TestMyAStarAlgo.java)

- Initialisatie van de Grafiek: Er wordt een grafiek opgezet met nodes (bijvoorbeeld X, Y, Z, enz.) en gewogen verbindingen volgens de setup van de test.
- Verwachte Paden: De tests berekenen de kortste paden vanaf een gegeven startNode naar andere knopen en controleren of deze paden overeenkomen met de verwachte routes.
- Assertions: Elk berekend pad wordt gecontroleerd met assertions om te verifiëren of de juiste nodes en gewichten worden gebruikt. Dit bewijst dat het algoritme de kortste paden correct identificeert in de opgegeven grafiekstructuur.

### Mijn MCST algoritme  

### Niet gelukt, misschien tijdens de herkansing
Classificatie:

Implementatie:

# Technisch ontwerp Mijn Applicatie

## Klassendiagram en het inlezen van de data

### Class: Console

[Console](../src/nl/saxion/cds/application/console/console.java)

* **Fields**
    * `MyHashMap<String, Station> stations`
        - Map met alle stations.
    * `MyGraph<String> tracks`
        - Graph met track connections tussen stations.
    * `InputReader inputReader`
        - Helper voor user input.
* **Methods**
    * `void start()`
        - Start de applicatie, laat alle opties zien aan user.
    * `void showRouteBetweenStations()`
        - Vraagt 2 stations en laat kortste route zien.
    * `void printStationInfo()`
        - Laat informatie zien over een specifiek station.
    * `void printStationsBasedOnTypeAlphabetical()`
        - Laat stations alfabetisch zien op type.
    * `void printMenu()`
        - Print de main menu opties.
    * `private void determineShortestRoute()`
        - Calculeert en laat de kortste route zien tussen 2 tussens gebruik makend van de A* algoritme.

### Class: StationsCSVReader

[StationsCSVReader](../src/nl/saxion/cds/application/readers/StationsCSVReader.java)

* **Methods**
    * `static MyHashMap<String, Station> readCSV(String filePath)`
        - CSV-reader.
        - Initialiseert een `MyHashMap<String, Station>` waar elk station's code de key is en de Station object de value.
        - Returns een `MyHashMap` met station informatie d.m.v. stationcode.


### Class: TracksCSVReader

[TracksCSVReader](../src/nl/saxion/cds/application/readers/TracksCSVReader.java)

* **Fields**
    * None

* **Methods**
    * `static MyGraph<String> readCSV(String filePath)`
        - CSV-reader.
        - Initialiseert een `MyGraph<String>`, waar elk station een Node is en elke track een edge met een afstand (weight).
        - Returns een `MyGraph` met stations connected met tracks.

# Station zoeken op basis van de stationscode

## Implementatie
De `Console`-klasse implementeert het zoeken naar stations op basis van de stationscode. In de `printStationInfo`-methode wordt het ingevoerde station opgezocht in de `MyHashMap` die alle stations bevat, met hun unieke code als key. Als de stationscode bestaat, wordt het bijbehorende `Station`-object opgehaald en weergegeven.

## Class Keuze
De keuze voor `MyHashMap` is gebaseerd op de constante (O(1)) tijdcomplexiteit voor zoekoperaties in hashmaps, wat belangrijk is omdat er potentieel honderden stations zijn. Een alternatief zou een `MyArrayList` kunnen zijn, maar de tijdcomplexiteit voor zoeken in een niet-gesorteerde lijst is lineair (O(n)).

## Tijdcomplexiteit
Het zoeken naar een stationscode in een `MyHashMap` is gemiddeld O(1) dankzij het gebruik van hashing. In het ergste geval, bij hash-collisies, kan het O(n) zijn, maar dit komt zelden voor.

# Station zoeken op basis van het begin van de naam

## Implementatie
Bij het zoeken op basis van het begin van de naam worden alle stations doorlopen om na te gaan of hun naam overeenkomt met het ingevoerde deel. De `findMatchingStations`-methode in `Console` doorzoekt de `MyArrayList` van stationsnamen. Als er meerdere stations overeenkomen, kan de gebruiker kiezen welk station hij wil bekijken.

## Class Keuze
Voor het opslaan van meerdere mogelijke matches wordt een `MyArrayList` gebruikt. `MyArrayList` is een geschikte keuze omdat de resultaten iteratief worden doorzocht. Alternatief zou een gesorteerde `MyTreeSet` kunnen worden gebruikt om sneller door de resultaten te zoeken, maar de lineaire zoekmethode is in dit geval eenvoudig en efficiënt genoeg.

## Tijdcomplexiteit
De tijdcomplexiteit voor het doorzoeken van alle stations in een `MyArrayList` is O(n), waarbij `n` het aantal stations is. De complexiteit voor het zoeken naar een deel van een naam in een niet-gesorteerde lijst is eveneens O(n), omdat elk station moet worden gecontroleerd.

# Alle stations van een bepaald type weergeven

## Implementatie
Deze functie gebruikt de `filterStationByType`-methode in `Console`. Hier wordt een nieuwe `MyArrayList` van stations samengesteld op basis van hun type. Vervolgens worden de resultaten alfabetisch gesorteerd met de `simpleSort`-methode.

## Class Keuze
Er is gekozen voor `MyArrayList` vanwege de eenvoud van toevoegen en itereren. Voor een enkelvoudige filtering en sortering is de performantie acceptabel. Alternatief zou een `HashMap` van lijsten per type sneller zoeken ondersteunen, maar de huidige oplossing blijft overzichtelijk.

## Tijdcomplexiteit
Het filteren van stations per type heeft een tijdcomplexiteit van O(n), omdat elk station gecontroleerd moet worden. De sorteeroperatie (`simpleSort`) heeft een tijdcomplexiteit van O(n log n), omdat deze gebruikmaakt van een vergelijkingsgebaseerde sorteermethode.

# Implementatie kortste route

## Implementatie
De kortste route tussen twee stations wordt berekend met behulp van het A*-algoritme in de `MyGraph`-klasse, waarbij de `showRouteBetweenStations`-methode in `Console` de interactie verzorgt. Het A*-algoritme maakt gebruik van een `HeuristicEstimator`, gebaseerd op de Haversine-afstand. Het algoritme voegt de routes op basis van afstand en heuristiek toe aan een prioriteitswachtrij (`MyPriorityQueue`), die de kortste route prioriteit geeft.

## Class Keuze
Voor de grafimplementatie wordt `MyGraph` gebruikt om de verbindingen tussen stations als knopen en gewogen paden voor te stellen. `MyPriorityQueue` houdt de knopen op volgorde van geschatte kortste route, wat zorgt voor een efficiënte implementatie van A*. Als alternatief zou Dijkstra's algoritme zonder heuristiek kunnen worden gebruikt, maar A* biedt een betere performantie voor specifieke bestemmingen vanwege de heuristiek.

## Tijdcomplexiteit
De complexiteit van A* is gemiddeld O(b^d), waarbij `b` de breedtefactor en `d` de diepte is, afhankelijk van het aantal routes en tussenliggende stations.

## Implementatie minimum cost spanning tree 

## Implementatie grafische representatie(s)