package remi.projet;

import android.view.View;

/**
 * Created by Rémi on 15/01/2018.
 */

public class Controller implements View.OnClickListener {
    protected Model _m;
    protected Drawer _d;

    public Controller(){}

    @Override
    public void onClick(View v){}

    public void setUtils(){
        _m = Model.Instance();
        _d = Drawer.Instance();
    }
}
