package avram.pop;

public class Controller {
    private Repo repository;

    public Controller(Repo repository){
        this.repository = repository;
    }

    public ICompW filterByWeight(int weight){
        ICompW[] filteredRepo = repository.getAll();
        for(int i = 0; i < repository.getNumberOfElements(); i++){
            if(filteredRepo[i].compareWeight(weight)){
                System.out.println(filteredRepo[i]);
            }
        }
    }
}
