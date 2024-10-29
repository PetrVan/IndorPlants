import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.format.FormatStyle;

public class Plant implements Comparable<Plant> {
    private String name;
    private String notes;
    private int frequencyOfWatering;
    private LocalDate watering;
    private LocalDate planted;
    private LocalDate frequency;

    public Plant(String name, String notes,int frequencyOfWatering, LocalDate watering, LocalDate planted ) throws PlantException{
        this.name = name;
        this.notes = notes;
        this.frequencyOfWatering = frequencyOfWatering;
        this.watering = watering;
        this.planted = planted;
        this.frequency = watering.plusDays(frequencyOfWatering);
        this.setFrequencyOfWatering(frequencyOfWatering);
    }

    public Plant(String name, int frequencyOfWatering) throws PlantException{
        this(name,  "", frequencyOfWatering, LocalDate.now(), LocalDate.now() );
    }

    public Plant(String name) throws PlantException{
        this(name,  "", 7, LocalDate.now(), LocalDate.now() );
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFrequencyOfWatering() {
        return frequencyOfWatering;
    }

    public void setFrequencyOfWatering(int frequencyOfWatering) throws PlantException {
        if(frequencyOfWatering <= 0){
            throw new PlantException("Frequency of Watering can not be negative or zero: " + frequencyOfWatering + "!");
        }
        this.frequencyOfWatering = frequencyOfWatering;
    }

    public LocalDate getWatering() {
        return watering;
    }

    public void setWatering(LocalDate watering) throws PlantException {
        if(watering.isBefore(planted)){
            throw new PlantException("Date of planted must be first than date of watering: " +
                    "Date of plented: " + planted.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) +
                    " Date of watering: " + watering.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)));
        }
        this.watering = watering;
    }

    public LocalDate getPlanted() {
        return planted;
    }

    public void setPlanted(LocalDate planted) {
        this.planted = planted;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }

    public String getWateringInfo(){
        return "Květina: " + name + " Zalita dne: " +
                watering.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM)) +
                " Příští zalití dne: " +
                frequency.format(DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM));
    }

    public void doWateringNow(){
        watering = LocalDate.now();
    }

    @Override
    public int compareTo(Plant other) {
        return this.name.compareTo(other.name);
    }

    @Override
    public String toString(){
        return name;
    }
}