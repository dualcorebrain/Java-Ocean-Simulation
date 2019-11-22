package seaSimulations;

public class Sardine extends Fish {
    private Field fieldObject;
    private Sardine Sardine;
    private Object Creature;
    private Location location;



    public void act(Field theField){
        theField.place(null, location);
        Location newLocation = theField.freeAdjacentLocation(location);
        location = newLocation;
        theField.place(Sardine, newLocation);



    }

}



/*    public  Sardine (){}

    public Sardine(int depthOfSardine , int widthOfSardine){
        fieldObject = new Field(depthOfSardine , widthOfSardine);
    }*/
/*    public void act(Field theField){
        if(location.equals(Creature)){
            theField.place(Sardine,location);
            theField.clear();;
        }
    }*/

/*    public void setLocation(int row, int col){
        Location location = new Location(row, col);
}

    public void act(Field theField){
    theField.place(null, location);
        Location newLocation = theField.freeAdjacentLocation(location);
        location = newLocation;
        theField.place(Sardine, newLocation);
        }
    }*/





