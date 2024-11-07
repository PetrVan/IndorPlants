public class Main {
    public static void main(String[] args) {
        try {
            Plants plants = new Plants();

            plants.loadFile();




            Plant gerbera = new Plant("Gerbera", 5);
            plants.addPlant(gerbera);
            System.out.println(gerbera.getWateringInfo());
            Plant tulipan2 = new Plant("BTulipán", 8);
            Plant tulipan = new Plant("ATulipán", 4);
            Plant narcis = new Plant("Narciska", 3);
            plants.addPlant(narcis);

            plants.addPlant(tulipan2);
            System.out.println(tulipan2.getWateringInfo());
            plants.addPlant(tulipan);
            System.out.println(tulipan.getWateringInfo());
            plants.sort();


            plants.safeFile(plants.getList());
        }
        catch (PlantException e){
            System.err.println(e.getMessage());
        }

    }
}

