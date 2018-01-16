package remi.projet;

import android.graphics.Color;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.LayoutInflater;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;

public class Drawer extends DrawerLayout{
    private static Drawer instance = null;
    public RightDrawerController _dController;

    public EditText _etNameF;
    public EditText _etCodeF;
    public Button _bAdd;
    public Button _bBack;

    public String currentCountry = "";
    public boolean _modify = false;
    public Factory _modifFactory = null;

    protected Drawer(){
        super(MainActivity.context);
        Log.d("myDebug","Constructor Drawer");

        LayoutInflater inflater = LayoutInflater.from(MainActivity.context);
        inflater.inflate(R.layout.activity_main,this);
        _dController = RightDrawerController.Instance();
        Log.d("myDebug","inflation MyDrawer");

        LinearLayout linearLayoutLeft = findViewById(R.id.linearLayoutLeft);
        Log.d("myDebug","LinearLayout getting");

        _etNameF = new EditText(MainActivity.context);
        _etCodeF = new EditText(MainActivity.context);
        _bAdd = new Button(MainActivity.context);
        _bBack = new Button(MainActivity.context);
        Log.d("myDebug","Input creation");

        _etNameF.setHint("Nom de l'usine");
        _etCodeF.setHint("Case de l'usine");
        _bAdd.setText("Ajout");
        _bAdd.setTag("bAdd");
        _bBack.setText("Retour");
        _bBack.setTag("bBack");
        Log.d("myDebug","Input modification");

        _etNameF.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        _etCodeF.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        _bAdd.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        _bBack.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Log.d("myDebug","Params setting");

        linearLayoutLeft.addView(_etNameF);
        linearLayoutLeft.addView(_etCodeF);
        linearLayoutLeft.addView(_bAdd);
        linearLayoutLeft.addView(_bBack);
        Log.d("myDebug","Input adding");

        _bAdd.setOnClickListener(_dController);
        _bBack.setOnClickListener(_dController);
        Log.d("myDebug","Buttons set to Controller");

        fill("");
        Log.d("myDebug","App Created");
    }

    public static Drawer Instance(){
        if(instance == null)
            instance = new Drawer();
        return instance;
    }

    public void fill(String s){
        fillRightDrawer(s);
        fillMainContent();
    }

    private void fillRightDrawer(String s){
        currentCountry = s;
        LinearLayout linearLayoutRight = findViewById(R.id.linearLayoutRight);
        linearLayoutRight.removeAllViews();
        Log.d("myDebug", "Layout Found and Views removed");

        ImageView imageView = new ImageView(MainActivity.context);
        imageView.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));

        Log.d("myDebug", "Flag set and added.");

        if(currentCountry != "") {
            String ls = currentCountry.toLowerCase();
            int imageResource = MainActivity.context.getResources().getIdentifier(
                    ls, "mipmap", MainActivity.context.getPackageName()
            );
            Log.d("myDebug", Integer.toString(imageResource));
            imageView.setImageResource(imageResource);
            imageView.setScaleType(ImageView.ScaleType.FIT_START);
        }

        //which adds the imageview to your layout
        linearLayoutRight.addView(imageView);
        Log.d("myDebug", "Flag set and added.");

        textViewSetting(linearLayoutRight, currentCountry, Color.DKGRAY);
        Log.d("myDebug", "Country name set and added.");
        if(Model.getCountry(currentCountry) != null) {
            for (Factory f : Model.getCountry(currentCountry).getFactories()) {
                LinearLayout ll = new LinearLayout(MainActivity.context);
                ll.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT));
                ll.setOrientation(LinearLayout.HORIZONTAL);
                textViewSettingFactory(ll, f);
                if(_modify == true && _modifFactory == f){
                    spinnerDefine(ll);
                }
                else {
                    textViewSetting(ll, " " + f.getStateFactory().toString(), f.getColorFactory());
                    buttonEditSetting(ll, f);
                }

                Log.d("myDebug", "TextView : " + f.toString() + " added to list.");
                linearLayoutRight.addView(ll);
            }
        }
    }

    private void fillMainContent(){
        Log.d("erreur","Main content filled");

        LayoutInflater inflater = LayoutInflater.from(MainActivity.context);
        inflater.inflate(R.layout.content_view,this);

        LinearLayout linearLayout = findViewById(R.id.content_view);
        linearLayout.removeAllViews();
        Log.d("erreur","LinearLayout get and cleaned");

        buttonSetting(linearLayout, "Add Factory in Left Drawer", "AddFactory");
        buttonSetting(linearLayout, "France", "bFrance");
        buttonSetting(linearLayout, "Germany", "bGermany");
        buttonSetting(linearLayout, "Spain", "bSpain");
        buttonSetting(linearLayout, "UK", "bUK");
    }

    private void buttonSetting(LinearLayout l, String text, String tag){
        Button b = new Button(MainActivity.context);
        b.setText(text);
        b.setTag(tag);
        b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Log.d("erreur","Params Setting");

        l.addView(b);
        Log.d("erreur","View Added");

        b.setOnClickListener(MainContentController.Instance());
        Log.d("erreur","Controller Setting");
    }

    private void buttonEditSetting(LinearLayout ll, Factory f){
        Button b = new Button(MainActivity.context);
        b.setText("✎");
        b.setTag(f.getCodeFactory());
        b.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Log.d("erreur","Params Setting");

        ll.addView(b);
        Log.d("erreur","View Added");

        b.setOnClickListener(EditController.Instance());
        Log.d("erreur","Controller Setting");
    }

    private void textViewSetting(LinearLayout l, String text, int color){
        TextView tv = new TextView(MainActivity.context);
        tv.setText(text);
        tv.setTextColor(color);
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Log.d("myDebug","Params Setting");
        l.addView(tv);
        Log.d("myDebug","View Added");
    }

    private void textViewSettingFactory(LinearLayout l, Factory f){
        TextView tv = new TextView(MainActivity.context);
        tv.setText(f.toString());
        tv.setTextColor(f.getColorFactory());
        tv.setTag(f.getCodeFactory());
        tv.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT));
        Log.d("myDebug","Params Setting");
        l.addView(tv);
        Log.d("myDebug","View Added");
    }

    private void spinnerDefine(LinearLayout ll){
        Spinner spinner = new Spinner(MainActivity.context);
        ArrayAdapter<String> aas = new ArrayAdapter<String>(
                MainActivity.context, android.R.layout.simple_dropdown_item_1line);
        aas.add("");

        for(State s : State.values()) {
            String ss = s.toString();
            if (ss != "Destroyed")
                aas.add(s.toString());
            else if (_modifFactory.getColorFactory() != Color.BLACK)
                aas.add(s.toString());
        }

        spinner.setAdapter(aas);
        SpinnerController.Instance().setUtils(spinner);
        spinner.setOnItemSelectedListener(SpinnerController.Instance());
        ll.addView(spinner);
    }

    public void close(){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START))
            drawer.closeDrawer(GravityCompat.START);
        if (drawer.isDrawerOpen(GravityCompat.END))
            drawer.closeDrawer(GravityCompat.END);
    }

    public void open(String s){
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        _modify = false;
        _modifFactory = null;
        if(s == "LEFT")
            if (!drawer.isDrawerOpen(GravityCompat.START))
                drawer.openDrawer(GravityCompat.START);
        if(s == "RIGHT")
            if (!drawer.isDrawerOpen(GravityCompat.END))
                drawer.openDrawer(GravityCompat.END);
    }
}