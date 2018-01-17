package remi.projet;

import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

public class RightDrawerController extends Controller {
    private static RightDrawerController instance = null;
    
    protected RightDrawerController(){

    }

    public static RightDrawerController Instance(){
        if(instance == null)
            instance = new RightDrawerController();
        return instance;
    }

    @Override
    public void onClick(View v){

    }
}