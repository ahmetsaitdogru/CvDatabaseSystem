public class CVNodes<AnyType> {

    public class Node{
        CV cv;
        Node next=null;
        int data;
        public Node(CV[] data){
            Node end=new Node(cv.array);
            Node n=this;
            while (n.next==null){
                n=n.next;
            }
            n.next=end;
        }
    }
}
