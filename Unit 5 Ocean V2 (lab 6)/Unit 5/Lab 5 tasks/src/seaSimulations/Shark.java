package seaSimulations;

public class Shark extends Fish {
    private Location location;
    private Shark Shark;


    public void act(Field theField){
        theField.place(null, location);
        Location newLocation = theField.freeAdjacentLocation(location);
        location = newLocation;
        theField.place(Shark, newLocation);
    }
        
}
