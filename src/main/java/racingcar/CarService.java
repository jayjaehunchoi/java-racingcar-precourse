package racingcar;

import camp.nextstep.edu.missionutils.Randoms;
import racingcar.validator.NameValidator;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import static util.CarConstant.*;

public class CarService {

    private final static int MAX_DEFAULT = 0;
    private final CarRepository carRepository;

    public CarService(CarRepository carRepository){
        this.carRepository = carRepository;
    }

    public int saveCars(String input){
        String[] tempCars = input.split(DELIMITER);
        NameValidator.isRightName(tempCars);
        return carRepository.saveAll(createCarList(tempCars));
    }

    private List<Car> createCarList(String[] tempCars) {
        return Arrays.asList(tempCars)
                .stream()
                .map(Car::new)
                .collect(Collectors.toList());
    }

    public void updateCarPosition(){
        List<Car> cars = carRepository.findAll();
        cars.forEach(car -> moveForward(car));
    }

    private void moveForward(Car car) {
        if(checkMoveForward()){
            car.goForward();
        }
    }

    private boolean checkMoveForward() {
        int randomNumber = Randoms.pickNumberInRange(MIN, MAX);
        if(randomNumber >= NUMBER_MOVE_FORWARD){
            return true;
        }
        return false;
    }

    public List<Car> findAllCars(){
        return carRepository.findAll();
    }

    public List<Car> findWinners(){
        List<Car> findCars = carRepository.findAll();
        int maxPosition = findMaxPosition(findCars);

        return findCars.stream()
                .filter(car -> car.getPosition() == maxPosition)
                .collect(Collectors.toList());
    }

    private int findMaxPosition(List<Car> findCars) {
        int max = MAX_DEFAULT;
        for (Car findCar : findCars) {
            max = Math.max(findCar.getPosition(), max);
        }
        return max;
    }

}
