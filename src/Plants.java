import java.time.LocalDate;
import java.time.Period;
import java.util.*;

public class Plants{
    private  List<Plant> plants = new ArrayList<>();


    public void add(Plant plant) {
        plants.add(plant);
    }

    public List<Plant> getList(){
        return new ArrayList<>(plants);
    }

    public Collection<Plant> getCollection(){
        return plants;
    }


    public Plant getPlantByIndex(int index){
        return plants.get(index);
    }

    public void addPlant(Plant plant){
        plants.add(plant);
    }

    public List<Plant> getPlantList(){
        return new ArrayList<>();
    }

    public void addList(List<Plant> plants){
        this.plants.addAll(plants);
    }

    public void remove(Plant plant){
        plants.remove(plant);
    }

    public List<Plant> getPlants() {
        return new ArrayList<>();
    }
    public void sort(){
        Collections.sort(plants);
    }

    public void sortByDate(){
        plants.sort(Comparator.comparing(Plant::getWatering));
    }

    public List<Plant> getPlantsWatering(){
        List<Plant> plantsWatering = new ArrayList<>();
        for(Plant plant : plants){
            Period dny = plant.getWatering().until(LocalDate.now());
            int days = dny.getDays();
            if(days > plant.getFrequencyOfWatering()){
                plantsWatering.add(plant);
            }
        }
        return plantsWatering;
    }
}

