package remi.projet;

import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RightDrawerController implements View.OnClickListener{
    private static RightDrawerController instance = null;
    private Model _m;
    private Drawer _d;

    protected RightDrawerController(){

    }

    public static RightDrawerController Instance(){
        if(instance == null)
            instance = new RightDrawerController();
        return instance;
    }

    public void setUtils(){
        _m = Model.Instance();
        _d = Drawer.Instance();
    }

    @Override
    public void onClick(View v){
        if(v.getTag() == _d._bAdd.getTag()){
            String name = _d._etNameF.getText().toString();
            String code = _d._etCodeF.getText().toString();
            int color = Color.RED;
            if(name.trim().length() != 0 && code.trim().length() != 0) {
                _m.getCurrentCountry().addFactory(name, code, color);
                LinearLayout ll = _d.findViewById(R.id.linearLayoutLeft);
                ll.setVisibility(View.INVISIBLE);
                Toast.makeText(MainActivity.context,
                        "Factory added.",
                        Toast.LENGTH_SHORT).show();
                _d.close();
                _m.setScreen("Drawer");
            } else {
                Toast.makeText(MainActivity.context,
                        "The form isn't correctly filled.",
                        Toast.LENGTH_SHORT).show();
            }
        }
        else if(v.getTag() == _d._bBack.getTag()){
            LinearLayout ll = _d.findViewById(R.id.linearLayoutLeft);
            ll.setVisibility(View.INVISIBLE);
            _d.close();
        }
    }
}