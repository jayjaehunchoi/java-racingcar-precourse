package racingcar;

import java.util.ArrayList;
import java.util.List;

public class CarRepository {
    private final List<Car> carList = new ArrayList<>();
    private final static int DEFAULT_VALUE = 0;

    public void saveAll(List<Car> cars){
        for (Car car : cars) {
            carList.add(car);
        }
    }

    public List<Car> findAll(){
        return carList;
    }

    public void clear(){
        int size = carList.size();
        for(int i = 0; i < size; i++){
            carList.remove(DEFAULT_VALUE);
        }
    }

}