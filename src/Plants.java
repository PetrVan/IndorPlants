import java.io.*;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeParseException;
import java.util.*;

public class Plants{
    private String file = Settings.FILE;
    private String delimiter = Settings.DELIMITER;
    private  List<Plant> plants = new ArrayList<>();


    public void addPlant(Plant plant) {
        plants.add(plant);
    }

    public List<Plant> getList(){
        return new ArrayList<>(plants);
    }

    public Plant getPlantByIndex(int index){
        return plants.get(index);
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

    public Plant parse(String line, int lineNumber, String delimiter) throws PlantException{
        int itemRequire = 5;
        String[] parts = line.split(delimiter);
        if(parts.length != itemRequire){
            throw new PlantException("Nesprávný počet na řádku: " + lineNumber + "! Očekáváme: " + itemRequire +
                    " položek\n" + line);
        }
        String name = parts[0].trim();
        String notes = parts[1].trim();
        try {
            int frequencyOfWatering = Integer.parseInt(parts[2].trim());
            LocalDate watering = LocalDate.parse(parts[3].trim());
            LocalDate planted = LocalDate.parse(parts[4].trim());
            return new Plant(name, notes, frequencyOfWatering, watering, planted);
        }catch(DateTimeParseException | IllegalArgumentException e){
            throw new PlantException("Chybné údaje: " + Arrays.toString(parts) + " na řádku číslo: " + lineNumber);
        }
    }

    public void loadFile() throws PlantException{
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(file)))) {
            while (scanner.hasNextLine()) {
                int numberLine = 0;
                String line = scanner.nextLine();
                numberLine++;
                plants.add(parse(line, numberLine, delimiter));
            }
        }catch(FileNotFoundException e) {
            throw new PlantException( "File: " + file + " not found!\n" + e.getLocalizedMessage());
        }
    }

    public void safeFile(List<Plant> plants) throws PlantException{
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
            for (Plant plant : plants) {
                writer.println(plant.getName() + delimiter +
                        plant.getNotes() + delimiter +
                        plant.getFrequencyOfWatering() + delimiter +
                        plant.getWatering() + delimiter +
                        plant.getPlanted());
            }
        }catch (IOException e){
            throw new PlantException("File: " + file + " not found.\n" + e.getLocalizedMessage());
        }
    }
}

