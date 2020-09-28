# Linked List

A linked list is a linear collection of data elements whose order is not given by their physical placement in memory. Instead, each element points to the next. It is a data structure consisting of a collection of nodes which together represent a sequence. In its most basic form, each node contains: data, and a reference (in other words, a link) to the next node in the sequence. This structure allows for efficient insertion or removal of elements from any position in the sequence during iteration. More complex variants add additional links, allowing more efficient insertion or removal of nodes at arbitrary positions. A drawback of linked lists is that access time is linear (and difficult to pipeline). Faster access, such as random access, is not feasible. Arrays have better cache locality compared to linked lists.

![Linked list image](https://upload.wikimedia.org/wikipedia/commons/thumb/6/6d/Singly-linked-list.svg/816px-Singly-linked-list.svg.png)

## Setup

In this project we will be looking into implementing linked lists. After
creating our Node and our MyLinkedList, we start adding methods that can
take full advantage of the linked list structure. We have to be careful when
setting our tail since failure to properly setting it up can cause null pointer
exception. As instructed we start with 10 objects in the list and test our
defined methods.

# Two Way Linked List

In a two way linked list (doubly linked list), each node contains, besides the next-node link, a second link field pointing to the 'previous' node in the sequence. The two links may be called 'forward('s') and 'backwards', or 'next' and 'prev'('previous').

![doubly linked list image](https://upload.wikimedia.org/wikipedia/commons/thumb/5/5e/Doubly-linked-list.svg/1220px-Doubly-linked-list.svg.png)

## Setup

In this project we are trying to test the two way linked list
by adding an extra data field in the node class. By adding the
"previous" data field, we are basically enabling a two way connections
between each of the nodes compered to a single forward way.
Each node is able to communicate with both the previous and the next element
of their neighbour nodes.

### Sources

[Linked List and images](https://en.wikipedia.org/wiki/Linked_list)
