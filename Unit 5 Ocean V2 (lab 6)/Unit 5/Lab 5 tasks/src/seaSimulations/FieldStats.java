package seaSimulations;


import java.util.HashMap;
import java.util.Iterator;

/**
 * This class collects and provides some statistical data on the state 
 * of a field. It is flexible: it will create and maintain a counter 
 * for any class of object that is found within the field.
 * 
 * @author David J. Barnes and Michael Kolling
 * @version 2002-04-23
 * @version Ian T. Nabney
 * @version 01-02-2005
 */
public class FieldStats
{
    // Counters for each type of entity (plankton, sardine, etc.) in the simulation.
    private HashMap<Class, Counter> counters;
    // Whether the counters are currently up to date.
    private boolean countsValid;

    /**
     * Construct a field-statistics object.
     */
    public FieldStats()
    {
        // Set up a collection for counters for each type of creature that
        // we might find
        counters = new HashMap<Class, Counter>();
        countsValid = true;
    }

    /**
     * @return A string describing what creatures are in the field.
     */
    public String getPopulationDetails(Field field)
    {
        StringBuilder buffer = new StringBuilder();
        if(!countsValid) {
            generateCounts(field);
        }
        Iterator<Class> keys = counters.keySet().iterator();
        while(keys.hasNext()) {
            Counter info = counters.get(keys.next());
            buffer.append(info.getName());
            buffer.append(": ");
            buffer.append(info.getCount());
            buffer.append(' ');
        }
        return buffer.toString();
    }
    
    /**
     * Invalidate the current set of statistics; reset all 
     * counts to zero.
     */
    public void reset()
    {
        countsValid = false;
        Iterator<Class> keys = counters.keySet().iterator();
        while(keys.hasNext()) {
            Counter cnt = counters.get(keys.next());
            cnt.reset();
        }
    }

    /**
     * Increment the count for one class of animal.
     */
    public void incrementCount(Class animalClass)
    {
        Counter cnt = (Counter) counters.get(animalClass);
        if(cnt == null) {
            // we do not have a counter for this species yet - create one
            cnt = new Counter(animalClass.getName());
            counters.put(animalClass, cnt);
        }
        cnt.increment();
    }

    /**
     * Indicate that an creature count has been completed.
     */
    public void countFinished()
    {
        countsValid = true;
    }

    /**
     * Determine whether the simulation is still viable.
     * I.e., should it continue to run.
     * @return true If there is more than one species alive.
     */
    public boolean isViable(Field field)
    {
        // How many counts are non-zero.
        int nonZero = 0;
        if(!countsValid) {
            generateCounts(field);
        }
        Iterator<Class> keys = counters.keySet().iterator();
        while(keys.hasNext()) {
            Counter info = counters.get(keys.next());
            if(info.getCount() > 0) {
                nonZero++;
            }
        }
        return nonZero > 1;
    }
    
    /**
     * Generate counts of the number of creatures.
     * These are not kept up to date as creatures
     * are placed in the field, but only when a request
     * is made for the information.
     */
    private void generateCounts(Field field)
    {
        reset();
        for(int row = 0; row < field.getDepth(); row++) {
            for(int col = 0; col < field.getWidth(); col++) {
                Object animal = field.getObjectAt(row, col);
                if(animal != null) {
                    incrementCount(animal.getClass());
                }
            }
        }
        countsValid = true;
    }
}