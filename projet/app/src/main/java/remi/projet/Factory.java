package remi.projet;

import java.util.ArrayList;

import android.content.ActivityNotFoundException;
import android.graphics.Color;

public class Factory
{
    private String codeFactory;
    private String nameFactory;
    private State stateFactory;
    private int colorFactory;
    public int idFactory;

    private static ArrayList<Factory> factories;

    public Factory (String code, String name, int color)
    {
        setEverything(code, name, color, State.Active);
    }

    public String toString()
    {
        StringBuffer ret = new StringBuffer ("<");
        ret.append(codeFactory);
        ret.append("> ");
        ret.append(nameFactory);
        ret.append(" -");

        return ret.toString();
    }

    public static ArrayList<Factory> getFactories(){
        if(factories == null)
            factories = new ArrayList<Factory>();
        return factories;
    }

    public void addFactory(ArrayList<Factory> listFactory) { listFactory.add(this); }

    public void setEverything(String code, String name, int color, State state) {
        setCodeFactory(code);
        this.nameFactory = name;
        setStateFactory(state);
        this.colorFactory = color;
        this.idFactory = -1;
    }

    public String getCodeFactory() {
        return codeFactory;
    }
    public void setCodeFactory(String code) { this.codeFactory = code; }
    public String getName() {
        return nameFactory;
    }
    public int getColorFactory(){
        return colorFactory;
    }
    public State getStateFactory(){ return this.stateFactory; }
    public void setStateFactory(State state){ this.stateFactory = state; }
    public void setIdFactory(int idFactory){
        this.idFactory = idFactory;
    }
}
