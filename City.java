package sample;

public class City {
    private int population;
    private String name;
    private String code2;
    private String code3;


    public City(String name, int population, String code2, String code3) {
        this.name = name;
        this.population = population;
        this.code2 = code2;
        this.code3 = code3;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    public String getCode2() {
        return code2;
    }

    public String getCode3() {
        return code3;
    }


}
