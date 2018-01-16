package remi.projet;

import android.graphics.Color;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class LeftDrawerController extends Controller {
    private static LeftDrawerController instance = null;

    protected LeftDrawerController(){

    }

    public static LeftDrawerController Instance(){
        if(instance == null)
            instance = new LeftDrawerController();
        return instance;
    }

    @Override
    public void onClick(View v){
        Log.d("myDebug", "cl_start");
        if(v.getTag() == "bAdd"){
            Log.d("myDebug", "cl_bAdd");
            String name = _d._etNameF.getText().toString();
            String code = _d._etCodeF.getText().toString();
            Log.d("myDebug", "cl_Code+Name_string");
            int color = Color.RED;
            if(name.trim().length() != 0 && code.trim().length() != 0) {
                _m.getCurrentCountry().addFactory(name, code, color, State.Active);
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
        else if(v.getTag() == "bBack"){
            LinearLayout ll = _d.findViewById(R.id.linearLayoutLeft);
            ll.setVisibility(View.INVISIBLE);
            _d.close();
        }
    }






}
