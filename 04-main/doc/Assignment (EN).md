# CDS Assignment

## Introduction

The Dutch railroad network is 3.2 thousand kilometers long. Of that network, 67 
percent is two- or multi-track and over 70 percent is electrified. Especially 
in the north and east of our country the railroads are not electrified. The 
network has about 400 train stations, which is on average one station for 
every 8.1  kilometers of track, see 
[CBS: How many railroads are there in the Netherlands?](https://www.cbs.nl/nl-nl/visualisaties/verkeer-en-vervoer/vervoermiddelen-en-infrastructuur/spoorwegen).

The cost of a trip by train depends on the number of units, see "the Fare 
Unit Map of the Netherlands" on the 
[NS Terms and Conditions site](https://www.ns.nl/voorwaarden.html)

## Requirements
It is up to you to build a proof of concept for a track manager application 
with the following tasks:
* Read in (at startup) all stations from [stations.csv](../resources/stations.csv) 
  and the corresponding connections from [tracks.csv](../resources/tracks.csv).
* Show information of a station based on its station code.
* Show information of a station based on (the beginning of) its name. Let 
  the user choose a station if more stations start with the searched part of 
  the name.
* Show all stations of a certain type, e.g. 'stopping train station', 
  'megastation' etc. and show an alphabetically sorted list of found station names.
* Determine the shortest route between two stations to be chosen by the user; 
  show the route and total length.
* Determine the minimum number of rail connections (and total length) needed 
  so that you can get from any station within the Netherlands to any other 
  station within the Netherlands (minimum cost spanning tree, MCST).
* Showing the rail network, the route found, and the MCST in a graphical 
  representation is grounds for extra points. Use the SaxionApp, JavaFX or 
  Swing for this purpose. An example map of the Netherlands that you may 
  use is in [resources](../resources/Nederland.png); examples for on this map 
  are:
  * HDR - Den Helder approximately at (600, 400),
  * MT - Maastricht approximately at (1024, 1840) and
  * DZ - Delfzijl approximately at (1490, 150).

Create a (console) application with a menu to invoke all functionalities.

## Implementation
The application must include the following implementations:
* A list implementation based on the [SaxList]
  (src/nl/saxion/cds/collection/SaxList.java).
* An ArrayList implementation based on the [SaxList](../src/nl/saxion/cds/collection/SaxList.java),
  the [SaxSearchable](../src/nl/saxion/cds/collection/SaxSearchable.java) 
  and the [SaxSortable](../src/nl/saxion/cds/collection/SaxSortable.java) 
  interfaces, so with;
  * a binary search algorithm,
  * a linear search algorithm,
  * a simple sorting algorithm e.g. SelectionSort or InsertionSort and
  * a QuickSort algorithm.
* A hash map implementation based on [SaxMap](../src/nl/saxion/cds/collection/SaxMap.java).
* A Binary Search Tree (BST), preferably a AVL-tree, based on 
  [SaxBinaryTree](../src/nl/saxion/cds/collection/SaxBinaryTree.java).
* A queue based on the [SaxQueue](../src/nl/saxion/cds/collection/SaxQueue.java) 
* A stack based on the [SaxStack](../src/nl/saxion/cds/collection/SaxStack.java)
* Based on the [SaxHeap](../src/nl/saxion/cds/collection/SaxHeap.java)
  interface a min-heap implementation **based on an array**
* A (generic) Graph based on [SaxGraph](../src/nl/saxion/cds/collection/SaxGraph.java), 
  with 
  * a depth first search (DFS) or a breadth first search (BFS) Iterator 
    implementation,
  * the A*-algorithm,
  * the Dijkstra-algorithm and 
  * a MCST-algorithm (Prim or Kruskal).  
* **Java collection classes and interfaces ARE NOT ALLOWED**, so
  use nothing from java.util, *except Comparator, Iterator and possibly 
  Scanner*.

## Preconditions
* Create documentation in Markdown. use [this template](Documentatie.md). 
  * For all algorithms and classes mentioned in section Implementation:
    * There is a link to the source code
    * For algorithms, include a Big-Oh qualification and a brief explanation 
      of why this is the correct classification.
      _You may look up this classification and explanation._
    * Describe in broad terms how it is implemented. Also describe which 
      tests prove that your algorithm works correctly and why.
  * There is a Technical Design chapter with
    * a class diagram with a brief description of the different classes and 
      main methods and
    * for each completed requirement, a brief description of the implementation and of the custom implementations of the `SaxCollection` class(es). _For every class used, include a well-founded reasoning for why that particular class was chosen, and always include at least one alternative in this reasoning_. 
    **Always include the Big-O classification for any algorithm** (this can be looked up).

* At least 75% of the test cases must be successfully developed with JUnit 5 (coverage over 90% will result in extra points).
  * **Coverage refers to the minimum coverage percentage of class, method, line, and branch coverage.**
  * For fields with restrictions, both good-weather and bad-weather tests must be provided.
  * Getters and setters may be excluded from test coverage, provided they only consist of returning the corresponding variable or modifying the corresponding variable. Mark these methods accordingly (see the slides) so that they do not count towards the coverage percentages.
  * When an exception is possible, that situation must be tested; at minimum, test for the correct exception type.
  * **Ensure that the examples in the course material are included in the tests.**

## Submission
A correct submission contains:
* The code of the developed program; create a zip export from GitLab, test this export (does everything still work?) and ensure that there are no build artifacts (out folder) or the .git folder, and that there are no absolute path names in the code.
* The [documentation as Markdown file](Documentatie.md) described above.

**An incomplete submission (code and/or documentation and/or code references are missing) will NOT be graded.**