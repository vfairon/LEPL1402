        assertEquals(-1, list.remove(0));

        list.enqueue(0);
        assertEquals(list.remove(0),0);
        CircularLinkedList.Node current2 = list.first.get();
        System.out.println(current2.value);