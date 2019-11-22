package seaSimulations;

public abstract class Creature{
    public void setLocation(int row, int col){
        Location location = new Location(row, col);
    };
    public abstract void act(Field theField);

}

