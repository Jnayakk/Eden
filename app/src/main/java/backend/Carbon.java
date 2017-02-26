package backend;

import java.util.ArrayList;

import static java.lang.System.in;

/**
 * Created by Jay on 2017-02-22.
 */

public class Carbon {

    public Carbon(){

    }

    public Double getCarbonEmissions(String item, double kg){
        double res = 0;
        if(item.equals("Lamb")){
            res = Constants.lambC02*kg;
        }
        else if (item.equals("Beef")) {
            res = Constants.beefC02*kg;
        }
        else if (item.equals("Cheese")) {
            res = Constants.cheeseC02*kg;
        }
        else if (item.equals("Pork")) {
            res = Constants.porkC02*kg;
        }
        else if (item.equals("Turkey")) {
            res = Constants.turkeyC02*kg;
        }
        else if (item.equals("Chicken")) {
            res = Constants.chickenC02*kg;
        }
        else if (item.equals("Tuna")) {
            res = Constants.tunaC02*kg;
        }
        else if (item.equals("Eggs")) {
            res = Constants.eggsC02*kg;
        }
        else if (item.equals("Potatoes")) {
            res = Constants.potatoesC02;
        }
        else if (item.equals("Rice")) {
            res = Constants.riceC02*kg;
        }
        else if (item.equals("Nuts")) {
            res = Constants.nutsC02*kg;
        }
        else if (item.equals("Beans")) {
            res = Constants.beansC02*kg;
        }
        else if (item.equals("Vegetables")) {
            res = Constants.vegetablesC02*kg;
        }
        return res;
    }

    public Double getTotalCarbon(ArrayList<Double> listofEmissions){
        double sum = 0;
        for (Double emission : listofEmissions){
            sum += emission;
        }
        return sum;
    }
}
