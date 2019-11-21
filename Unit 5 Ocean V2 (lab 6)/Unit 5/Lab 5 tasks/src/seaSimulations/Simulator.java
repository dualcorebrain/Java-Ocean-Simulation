package seaSimulations;

import com.sun.org.apache.xpath.internal.operations.Mod;

import javax.crypto.SealedObject;
import java.awt.*;
import java.lang.reflect.Array;
import java.util.*;

public class Simulator {
    private Field field;
    private SimulatorView simulatorViewContructor;

    private Plankton planktonContructor;
    private Sardine sardineContructor;
    private Shark sharkContructor;

    private ArrayList<Creature> creatureArrayList;
    private int currentStep;
    //

    public Simulator(int depth, int width){

        creatureArrayList = new ArrayList<Creature>();

        if(depth < 0 && width < 0){
            depth = ModelContants.DEFAULT_OCEAN_DEPTH;
            width = ModelContants.DEFAULT_OCEAN_WIDTH;

        }


        field = new Field(depth, width);
        simulatorViewContructor = new SimulatorView(depth, width);
        simulatorViewContructor.setColor(Plankton.class, Color.red);
        simulatorViewContructor.setColor(Sardine.class, Color.green);
        simulatorViewContructor.setColor(Shark.class, Color.blue);

        populate();
        startingSimulation();

        RandomGenerator.initialiseWithSeed(10);




    }

    public void populate(){
        planktonContructor = new Plankton();
        sardineContructor = new Sardine();
        sharkContructor = new Shark();

        //field.place(planktonContructor, 10,10);
        //field.place(sardineContructor, 20,20);
        //field.place(sharkContructor, 30,30);

        for(int row = 0; row<field.getWidth(); row++){
            for(int column = 0; column<field.getDepth(); column++){

                Random rand = RandomGenerator.getRandom();
                double randomNumber = rand.nextDouble();

                if(randomNumber < ModelContants.SHARK_CREATION_PROBABILITY){
                    Shark sharkObjectForArray = new Shark();
                    field.place(sharkObjectForArray, row, column);
                    creatureArrayList.add(sharkObjectForArray);
                }

                else if(randomNumber > ModelContants.SHARK_CREATION_PROBABILITY && randomNumber <= ModelContants.SARDINE_CREATION_PROBABILITY){
                    Sardine sardineObjectForArray = new Sardine();
                    field.place(sardineObjectForArray, row,column);
                    creatureArrayList.add(sardineObjectForArray);

                }

                else if(randomNumber > ModelContants.SARDINE_CREATION_PROBABILITY && randomNumber <= ModelContants.PLANKTON_CREATION_PROBABILITY) {
                    Plankton planktonObjectForArray = new Plankton();
                    field.place(planktonObjectForArray, row, column);
                    creatureArrayList.add(planktonObjectForArray);
                }

                //Up until this point was lab 05 tasks
            }
        }

        



    }

    public void simulate(int steps){
        int stepCounter = 0;
        while(stepCounter < steps){
            simulatorViewContructor.showStatus(steps, field);
            stepCounter++;
        }

    }

    public void simulateOneStep(){
        for(Creature creatureIterator : creatureArrayList ){
            if (creatureIterator instanceof )

        }
    }

    public void startingSimulation(){
        simulatorViewContructor.showStatus(1, field);

    }

    public static void main(String[] args) {
        Simulator s = new Simulator(100,100);
    }



/*    public static void main(String[] args){*/
/*        Plankton plankton = new Plankton();*/
/*        Sardine sardine = new Sardine();*/
/*        Shark shark = new Shark();*/
/*
*/

/*        SimulatorView simulatorview = new SimulatorView(50,50);*/
/*        Field field = new Field(50,50);*/
/*
*/

/*        simulatorview.setColor(Plankton.class, Color.red);*/
/*        simulatorview.setColor(Sardine.class, Color.green);*/
/*        simulatorview.setColor(Shark.class, Color.blue);*/
/*
*/

/*
*/

/*        field.place(plankton, 10,10);*/
/*        field.place(sardine, 20,20);*/
/*        field.place(shark, 30,30);*/
/*
*/

/*
*/

/*        simulatorview.showStatus(1,field);*/
/*
*/

/*
*/

/*
*/

/*
*/

    }

