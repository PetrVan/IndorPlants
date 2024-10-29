import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IOFile{
    private String inputFile = Settings.INPUTFILE;
    private String outputFile = Settings.OUTPUTFILE;
    private String delimiter = Settings.DELIMITER;
    private Plants plants;

    public IOFile(){
        this.plants = new Plants();
    }

    public Plants getPlants(){
        return plants;
    }

    public void loadFile() throws PlantException{
        try(Scanner scanner = new Scanner(new BufferedReader(new FileReader(inputFile)))) {
            while (scanner.hasNextLine()) {
                int numberLine = 0;
                String line = scanner.nextLine();
                numberLine++;
                plants.addPlant(parse(line, numberLine, delimiter));
            }
        }catch(FileNotFoundException e) {
            throw new PlantException( "File: " + inputFile + " not found!\n" + e.getLocalizedMessage());
        }
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

    public void safeFile(List<Plant> plants) throws PlantException{
        try(PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(outputFile)))) {
            for (Plant plant : plants) {
                writer.println(plant.getName() + delimiter +
                        plant.getName() + delimiter +
                        plant.getFrequencyOfWatering() + delimiter +
                        plant.getWatering() + delimiter +
                        plant.getPlanted());
            }
        }catch (IOException e){
            throw new PlantException("File: " + outputFile + " not found.\n" + e.getLocalizedMessage());
        }
    }
}
