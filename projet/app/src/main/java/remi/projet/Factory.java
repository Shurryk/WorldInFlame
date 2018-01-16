package remi.projet;

public class Factory
{
    private String codeFactory;
    private String nameFactory;
    private State stateFactory;
    private int colorFactory;
    public int idFactory;

    public Factory (String code, String name, int color, State state)
    {
        setEverything(code, name, color, state);
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

    public void setEverything(String code, String name, int color, State state) {
        this.codeFactory = code;
        this.nameFactory = name;
        setStateFactory(state);
        this.colorFactory = color;
        this.idFactory = -1;
    }

    public String getCodeFactory() {
        return codeFactory;
    }
    public String getName() {
        return nameFactory;
    }
    public int getColorFactory(){ return colorFactory; }
    public State getStateFactory(){ return this.stateFactory; }
    public void setStateFactory(State state){ this.stateFactory = state; }
    public void setIdFactory(int idFactory){
        this.idFactory = idFactory;
    }
}
