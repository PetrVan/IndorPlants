import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
public class Main {
    public static void main(String[] args) {
        try {
            Plants plants = new Plants();
            Plant gerbera = new Plant("Gerbera", 5);
            plants.add(gerbera);
            System.out.println(gerbera.getWateringInfo());
            Plant tulipan2 = new Plant("BTulipán", 8);
            Plant tulipan = new Plant("ATulipán", 4);
            Plant narcis = new Plant("Narciska", 3);
            plants.add(narcis);

            plants.add(tulipan2);
            System.out.println(tulipan2.getWateringInfo());
            plants.add(tulipan);
            System.out.println(tulipan.getWateringInfo());

            IOFile file = new IOFile();
            file.loadFile();
            file.getPlants().getCollection().forEach(System.out::println);
            file.safeFile(plants.getList());

        }
        catch (PlantException e){
            System.err.println(e.getMessage());
        }

    }
}