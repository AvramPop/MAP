package avram.pop;

public class Main {

    public static void main(String[] args) {
	    Book b1 = new Book("t1", "a1", 100);
        Book b2 = new Book("t2", "a2", 200);
        Book b3 = new Book("t3", "a3", 300);
        ArrayRepo repo = new ArrayRepo();
        Controller controller = new Controller(repo);
        try{
            controller.addProduct(b1);
            controller.addProduct(b2);
            controller.addProduct(b3);
        } catch(CustomException exception)
    }
}
