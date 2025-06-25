package com.example.calculator1;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
//working="2+3-5" --input
    //result ="0"  --output
    TextView resultTV;
    TextView workingsTV;
    String formula="";
    String workings="";
    String tempFormula="";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        initTextViews();
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;

        });
    }
    @SuppressLint("CutPasteId")
    private void initTextViews()
    {
        workingsTV= (TextView)findViewById(R.id.workingsTextView);
        resultTV= (TextView)findViewById(R.id.workingsTextView);
    }
    private void setWorkings(String givenValue){
        workings=workings+givenValue;
        workingsTV.setText(workings);
    }
    private void checkPowerOf()
    {
        ArrayList<Integer> indexOfPowers=new ArrayList<>();//1,5
        for(int i=0;i<workings.length();i++)
        {
            if(workings.charAt(i)=='^')
            {
                indexOfPowers.add(i);
            }
        }
        formula=workings;
        tempFormula=workings;
        for(int i=0;i<indexOfPowers.size();i++)
        {
            changeFormula(indexOfPowers.get(i));
        }
        formula=tempFormula;
    }
    private void changeFormula(Integer index)
    {
        String numberLeft="";
        String numberRight="";
        for(int i=index+1; i<workings.length();i++)
        {
            if(isNumeric(workings.charAt(i)))
            {
                numberRight=numberRight+workings.charAt(i);
            }
            else
                break;
        }
       for(int i=index-1;i>=0;i++)
       {
           if(isNumeric(workings.charAt(i)))
           {
               numberLeft=workings.charAt(i) +numberLeft;
           }
           else
               break;
       }
       //2+22^12-3
       String original= numberLeft + "^" + numberRight;//2^3
       String changed="Math.pow(" + numberLeft + "," + numberRight + ")";//Math.PI(2,3)
        tempFormula = tempFormula.replace(original, changed);
    }
    private boolean isNumeric(char c)
    {
        return (c <= '9' && c >= '0') || c == '.';
    }

    public void equalsOnClick(View view) {
       Double  result= null;
        ScriptEngine engine=new ScriptEngineManager().getEngineByName("rhino");
        checkPowerOf();
        try{
            result=(double) engine.eval(formula);
        }
        catch (ScriptException e)
        {
            Toast.makeText(this,"Invalid Input",Toast.LENGTH_SHORT).show();
        }
        if(result!= null)
        {
            resultTV.setText(String.valueOf(result.doubleValue()));
        }
    }

    public void clearOnClick(View view) {
        workingsTV.setText("");
        resultTV.setText("");
        workings="";
        leftBracket = true;
    }

    public void bracketsOnClick(View view) {
        if(leftBracket==true)
        {
            setWorkings("(");
            leftBracket=false;
        }
        else {
            setWorkings(")");
            leftBracket=true;
        }
    }

    public void powerOfOnClick(View view) {
        setWorkings("^");
    }

    public void divisionOnClick(View view) {
        setWorkings("/");
    }

    public void sevenOnClick(View view) {
        setWorkings("7");
    }

    public void eightOnClick(View view) {
        setWorkings("8");
    }

    public void nineOnClick(View view) {
        setWorkings("9");
    }

    public void timesOnClick(View view) {
        setWorkings("*");
    }

    public void fourOnClick(View view) {
        setWorkings("4");
    }

    public void fiveOnClick(View view) {
        setWorkings("5");
    }

    public void sixOnClick(View view) {
        setWorkings("6");
    }

    public void minusOnClick(View view) {
        setWorkings("-");
    }

    public void oneOnClick(View view) {
        setWorkings("1");
    }

    public void twoOnClick(View view) {
        setWorkings("2");
    }

    public void threeOnClick(View view) {
        setWorkings("3");
    }

    public void plusOnClick(View view) {
        setWorkings("+");
    }

    public void decimalOnClick(View view) {
        setWorkings(".");
    }

    public void zeroOnClick(View view) {
        setWorkings("0");
    }
}