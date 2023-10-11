public class Lab4Program {
    public static void main(String[] args) {
        Lab4Program lab = new Lab4Program();
        // Make and display lists using array constructor
        LinkedList<Integer> intList = new LinkedList<>(new Integer[] {1, 2, 3, 4});
        System.out.print("Created intList: " + intList); // toString() has \n
        LinkedList<String> abcList = new LinkedList<>(new String[] {"A", "B", "C"});
        System.out.print("Created abcList: " + abcList);
        LinkedList<String> emptyList = new LinkedList<>();
        System.out.print("Created emptyList: " + emptyList);
        String[] array = null;
        LinkedList<String> nullList = new LinkedList<>(array);
        System.out.print("Created nullList: " + nullList);
        // Copy all using copy constructor
        LinkedList<Integer> intCopy = new LinkedList<>(intList);
        System.out.print("Created intCopy: " + intCopy);
        LinkedList<String> abcCopy = new LinkedList<>(abcList);
        System.out.print("Created abcCopy: " + abcCopy);
        LinkedList<String> emptyCopy = new LinkedList<>(emptyList);
        System.out.print("Created emptyCopy: " + emptyCopy);
        LinkedList<String> nullCopy = new LinkedList<>(nullList);
        System.out.print("Created nullCopy: " + nullCopy);
        // Test equals
        System.out.println("intList.equals(null): " + intList.equals(null));
        System.out.println("intList.equals(emptyList): " + intList.equals(emptyList));
        System.out.println("intList.equals(abcList): " + intList.equals(abcList));
        System.out.println("abcList.equals(intList): " + abcList.equals(intList));
        System.out.println("intList.equals(intList): " + intList.equals(intList));
        System.out.println("abcList.equals(abcList): " + abcList.equals(abcList));
        System.out.println("abcList.equals(abcCopy): " + abcList.equals(abcCopy));
        System.out.println("nullList.equals(nullCopy): " + nullList.equals(nullCopy));
        System.out.println("emptyList.equals(emptyCopy): " + emptyList.equals(emptyCopy));
        System.out.println("emptyList.equals(nullList): " + emptyList.equals(nullList));
        // Test spinlist
        abcList.spinList(0);
        System.out.print("abcList.spinList(0): " + abcList);
        abcList.spinList(1);
        System.out.print("abcList.spinList(1): " + abcList);
        abcList.spinList(2);
        System.out.print("abcList.spinList(2): " + abcList);
        abcList.spinList(3);
        System.out.print("abcList.spinList(3): " + abcList);
        intList.spinList(3);
        System.out.print("intList.spinList(3): " + intList);
        intList.spinList(5);
        System.out.print("intList.spinList(5): " + intList);
        emptyList.spinList(1);
        System.out.print("emptyList.spinList(1): " + emptyList);
        nullList.spinList(1);
        System.out.print("nullList.spinList(1): " + nullList);
        // Test altList
        LinkedList<Integer> list123 = new LinkedList<>(new Integer[] {1, 2, 3});
        System.out.print("Created list123: " + list123);
        LinkedList<Integer> list456 = new LinkedList<>(new Integer[] {4, 5, 6});
        System.out.print("Created list456: " + list456);
        System.out.print("list123.altLists(list456): " + list123.altLists(list456));
        LinkedList<Integer> list1234 = new LinkedList<>(new Integer[] {1, 2, 3, 4});
        System.out.print("Created list1234: " + list1234);
        LinkedList<Integer> list56 = new LinkedList<>(new Integer[] {5, 6});
        System.out.print("Created list56: " + list56);
        System.out.print("list1234.altLists(list56): " + list1234.altLists(list56));
        LinkedList<Integer> list12 = new LinkedList<>(new Integer[] {1, 2});
        System.out.print("Created list12: " + list12);
        LinkedList<Integer> list3456 = new LinkedList<>(new Integer[] {3, 4, 5, 6});
        System.out.print("Created list3456: " + list3456);
        System.out.print("list12.altLists(list3456): " + list12.altLists(list3456));
        System.out.print("abcList.altLists(emptyList): " + abcList.altLists(emptyList));
        System.out.print("abcList.altLists(nullList): " + abcList.altLists(nullList));
    }
}
