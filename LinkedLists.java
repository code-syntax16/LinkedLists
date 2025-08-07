
public class LinkedLists {
    public static class node{
        int data;
        node next;
        public node(int data){
            this.data=data;
            this.next=null;
        }
    }
    public static node head;
    public static node tail;
    public static int size;
    public void addFirst(int data){
        //step1
        node newNode=new node(data);
        size++;
        if(head==null){
          head=tail=newNode;
          return;
        }
        //step2
        newNode.next=head;
        //step3
       head=newNode;

    }
    public void addLast(int data){
         node newNode=new node(data);
         size++;
        if(head==null){
          head=tail=newNode;
          return;
        }
        tail.next=newNode;
       tail=newNode;
    }
    public void print(){
        if(head==null){
            System.out.println("ll is empty");
            return;
        }
        node temp=head;
        while(temp!=null){
            System.out.print(temp.data+ "--> ");
            temp=temp.next;
        }
        System.out.println("null");
    }
    public void add(int idx,int data){
        if(idx==0){
            addFirst(data);
            return;
        }
         
         node newNode=new node(data);
         size++;
         node temp=head;
         int i=0;
         while(i<idx-1){
            temp=temp.next;
            i++;
         }
         newNode.next=temp.next;
         temp.next=newNode;
    }
    public int removeFirst(){
        if(size==0){
            System.out.print("ll is empty");
            return Integer.MIN_VALUE;
        }else if(size==1){
            int val=head.data;
            head=tail=null;
            size=0;
            return val;
        }
        int val=head.data;
        head=head.next;
        size--;
        return val;
    }
   public int removeLast(){
    if(size==0){
         System.out.print("ll is empty");
        return Integer.MIN_VALUE;
        }else if(size==1){
            int val=head.data;
            head=tail=null;
            size=0;
            return val;
        }
        node prev=head;
        for(int i=0;i<size-2;i++){
            prev=prev.next;
        }
        int val=prev.next.data;
        prev.next=null;
        size--;
        tail=prev;
        return val;
   }
   public int itsearch(int key){
    node temp=head;
    int i=0;
    while(temp!=null){
        if(temp.data==key){
            return i;
        }
        temp=temp.next;
        i++;
    }
    return -1;
   }
   public int helper(node head,int key){
    if(head==null){
        return -1;
    }
    if(head.data==key){
        return 0;
    }
    int idx=helper(head.next, key);
    if(idx==-1){
        return -1;
    }
    return idx+1;
   }
   public int recSearch(int key){
    return helper(head, key);
   }
   public void reverse(){
    node prev=null;
    node curr=tail=head;
    node next;
    while(curr!=null){
        //step1 
        next=curr.next;
        //step2
        curr.next=prev;
        //step3
       prev=curr;
        //step4
      curr=next;     
    }
    head=prev;
   }
   public void removeNthFromEnd(int n){
    //claculate
    int sz=0;
    node temp=head;
    while(temp!=null){
        temp=temp.next;
        sz++;
    }
    if(n==sz){
        head=head.next;
        return;
    }
    int i=1;
    int itoFind=sz-n;
    node prev=head;
    while(i<itoFind){
        prev=prev.next;
        i++;
    }
    prev.next=prev.next.next;
    return;

   }
   public node findMid(node head){
    node slow=head;
    node fast=head;
    while(fast!=null && fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
    }
    return slow;
   }
   public boolean checkPalindrome(){
    if(head==null || head.next==null){
        return true;
    }
    //step1- find mid
   node mid= findMid(head);

    //step2 - 2nd half reverse
    node  prev=null;
    node curr=mid;
    node next;
    while(curr!=null){
        next=curr.next;
        curr.next=prev;
        prev=curr;
        curr=next;
    }
    node right=prev;
    node left=head;
    //step3- left and right part check
    while(right!=null){
        if(left.data!=right.data){
            return false;
        }
        left=left.next;
        right=right.next;
    }
  return true;
   }
   public static boolean detectCycle(){
    node slow=head;
    node fast=head;
    while(fast!=null &&  fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
       if(slow==fast){
        return true;
       }
    }
    return false;
   }
   public static void removeCycle(){
    node slow=head;
    node fast=head;
    boolean cycle=false;
    while(fast!=null &&  fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
       if(slow==fast){
       cycle=true;
       break;
       }
    }
    if(cycle==false){
        return;
    }
    slow=head;
    node prev=null;
    while(slow!=fast){
        prev=fast;
        slow=slow.next;
        fast=fast.next;
    }
    prev.next=null;
   }
   public node ergeSort(node head){
    if(head==null || head.next==null){
        return head;
    }
    //step1: ll->mid
    node slow=head;
    node fast=head.next;
    while(fast!=null && fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
    }
    node mid=slow;
    //step2: ll->lh & rf divide
   
    node rightHead=mid.next;
    mid.next=null;
    node leftHalf=mergeSort(head);//left half
    node rightHalf=mergeSort(rightHead);//right half
    //step3: lf & rf merge
   
    node h1=leftHalf;
    node h2=rightHalf;
    node mergedLL=new node(-1);
    node temp=mergedLL;
    while(h1!=null && h2!=null){
        if(h1.data<h2.data){
            temp.next=h1;
            h1=h1.next;
             temp=temp.next;
        }else{
            temp.next=h2;
            h2=h2.next;
            temp=temp.next;
        }
        
    }
    while(h1!=null){
            temp.next=h1;
            h1=h1.next;
             temp=temp.next;
        }
        while(h2!=null){
            temp.next=h2;
            h2=h2.next;
            temp=temp.next;
        }
       
    return mergedLL.next;
   }
   private node getMid(node head){
     node slow=head;
    node fast=head.next;
    while(fast!=null && fast.next!=null){
        slow=slow.next;
        fast=fast.next.next;
    }
    return slow;
   }
   public node mergeSort(node head){
    if(head==null || head.next==null){
        return head;
    }

       //find mid
       node mid=getMid(head);
       //step2 divide
       node rightHead=mid.next;
       mid.next=null;
     node leftHalf=  mergeSort(head);
       node rightHalf=mergeSort(rightHead);
       //step 3 merge
       return merge(leftHalf,rightHalf);
   }
   private node merge(node h1,node h2){
    node mergedLL=new node(-1);
    node temp=mergedLL;
    while(h1!=null && h2!=null){
        if(h1.data<h2.data){
            temp.next=h1;
            h1=h1.next;
             temp=temp.next;
        }else{
            temp.next=h2;
            h2=h2.next;
            temp=temp.next;
        }
        
    }
    while(h1!=null){
            temp.next=h1;
            h1=h1.next;
             temp=temp.next;
        }
        while(h2!=null){
            temp.next=h2;
            h2=h2.next;
            temp=temp.next;
        }
       
    return mergedLL.next;
   }
   
    public static void main(String args[]){
        LinkedLists ll=new LinkedLists();
        // ll.addFirst(2);
        // ll.addFirst(1);
        // ll.addLast(2);
        // ll.addLast(1);
        // ll.add(2, 1);
        // ll.print();
        // System.out.println(ll.size);
        // ll.removeFirst();
        //  ll.print();
        //  ll.removeLast();
        //  ll.print();
        //    System.out.println(ll.size);
       // System.out.print(ll.recSearch(10));
    //    ll.reverse();
    // ll.removeNthFromEnd(3);
    //    ll.print();
    //    System.out.print(ll.checkPalindrome());
    // head=new node(1);
    // node temp=new node(2);
    // head.next=temp;
    // head.next.next=new node(3);
    // head.next.next.next=temp;
    // System.out.print(detectCycle());
    // removeCycle();
    // System.out.print(detectCycle());
       ll.addFirst(1);
       ll.addFirst(2);
       ll.addFirst(3);
       ll.addFirst(4);
       ll.addFirst(5);
       ll.print();
      ll.head= ll.mergeSort(ll.head);
       ll.print();  
       

    }
    
}
