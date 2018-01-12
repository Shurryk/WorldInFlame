package remi.projet;

public enum State{
    Active("Active"),
    Inactive("Inactive"),
    Surrounded("Surrounded"),
    Occupied("Occupied"),
    Destroyed("Destroyed");

    private String name;

    private State(String s){
        this.name = s;
    }

    @Override public String toString(){
        return name;
    }
}