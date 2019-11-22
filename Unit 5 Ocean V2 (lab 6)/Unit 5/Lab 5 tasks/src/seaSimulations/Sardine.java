package seaSimulations;

public class Sardine extends Fish {
    private Field fieldObject;
    private Location location;
    private Sardine Sardine;
    private Object Creature;

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

    public void act(Field theField, Location currentLocation){
        theField.freeAdjacentLocation(location);
        }
    }




}
