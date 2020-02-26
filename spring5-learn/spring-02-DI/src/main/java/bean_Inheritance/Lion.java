package bean_Inheritance;

/**
 * @Auther: zhangmo
 * @Email : zhangmowx@163.com
 * @Date: 2020/2/23 11:10
 * @Description:
 */
public class Lion {

    private String name;
    private String animalType;
    private Double weight;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAnimalType() {
        return animalType;
    }

    public void setAnimalType(String animalType) {
        this.animalType = animalType;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    @Override
    public String toString() {
        return "Lion{" +
                "name='" + name + '\'' +
                ", animalType='" + animalType + '\'' +
                ", weight=" + weight +
                '}';
    }
}
