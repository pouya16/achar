package com.example.esppad.calc2.Main;


import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import com.example.esppad.calc2.R;


/**
 * A simple {@link Fragment} subclass.
 */
public class CalcFragment extends Fragment implements View.OnClickListener{
    int acc = 0;
    int clear = 0;
    double mem = 0;
    int stackedOperator = 0;


    public CalcFragment() {
        // Required empty public constructor
    }
    Button btn1;Button btn2;Button btn3;
    Button btn4;Button btn5;Button btn6;
    Button btn7;Button btn8;Button btn9;
    Button btn0;Button btnPercent;Button btnEqual;
    Button btnClear;Button btnPlus;Button btnMinus;
    Button btnMultiply;Button btnDevide;Button btnDot;
    Button btnPm;
    TextView calcText;



    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_calc, container, false);
        btn1 = (Button) view.findViewById(R.id.b1);btn2 = (Button) view.findViewById(R.id.b2);btn3 = (Button) view.findViewById(R.id.b3);
        btn4 = (Button) view.findViewById(R.id.b4);btn5 = (Button) view.findViewById(R.id.b5);btn6 = (Button) view.findViewById(R.id.b6);
        btn7 = (Button) view.findViewById(R.id.b7);btn8 = (Button) view.findViewById(R.id.b8);btn9 = (Button) view.findViewById(R.id.b9);
        btn0 = (Button) view.findViewById(R.id.b0);
        btnDot = (Button) view.findViewById(R.id.dot);btnEqual = (Button) view.findViewById(R.id.equal);btnPlus = (Button) view.findViewById(R.id.plus);
        btnMinus = (Button) view.findViewById(R.id.minus);btnMultiply = (Button) view.findViewById(R.id.multiply);btnDevide = (Button) view.findViewById(R.id.devide);
        btnPercent = (Button) view.findViewById(R.id.percent);btnPm = (Button) view.findViewById(R.id.pm);btnClear = (Button) view.findViewById(R.id.c);
        calcText = (TextView) view.findViewById(R.id.calcText);

        btn1.setOnClickListener(this);btn2.setOnClickListener(this);btn3.setOnClickListener(this);
        btn4.setOnClickListener(this);btn5.setOnClickListener(this);btn6.setOnClickListener(this);
        btn7.setOnClickListener(this);btn8.setOnClickListener(this);btn9.setOnClickListener(this);
        btn0.setOnClickListener(this);btnDot.setOnClickListener(this);btnEqual.setOnClickListener(this);
        btnPlus.setOnClickListener(this);btnMinus.setOnClickListener(this);btnMultiply.setOnClickListener(this);
        btnDevide.setOnClickListener(this);btnPercent.setOnClickListener(this);btnPm.setOnClickListener(this);
        btnClear.setOnClickListener(this);


        return view;
    }

    @Override
    public void onClick(View v) {

        Log.i("Log1","mem is: " + mem + " - acc is: " + acc);
        switch (v.getId()){
            case R.id.b0:
                clear = setText("0",acc);
                acc=0;
                return;
            case R.id.b1:
                clear = setText("1",acc);
                acc=0;
                return;
            case R.id.b2:
                clear = setText("2",acc);
                acc=0;
                return;
            case R.id.b3:
                clear = setText("3",acc);
                acc = 0;
                return;
            case R.id.b4:
                clear = setText("4",acc);
                acc = 0;
                return;
            case R.id.b5:
                clear = setText("5",acc);
                acc = 0;
                return;
            case R.id.b6:
                clear = setText("6",acc);
                acc = 0;
                return;
            case R.id.b7:
                clear = setText("7",acc);
                acc = 0;
                return;
            case R.id.b8:
                clear = setText("8",acc);
                acc = 0;
                return;
            case R.id.b9:
                clear = setText("9",acc);
                acc = 0;
                return;
            case R.id.c:
                if(clear==0) {
                    calcText.setText("0");
                    clear++;
                }else{
                    calcText.setText("0");
                    acc =0;
                    mem = 0;
                    stackedOperator = 0;
                }
                return;
            case R.id.plus:
                Log.i("Log","plus btn is pressed");
                acc = 1;
                Log.i("Log","acc is: " + acc);
                if(mem==0){
                    mem = Double.parseDouble(calcText.getText().toString());
                }else if(mem!=0){
                    calculate();
                }
                stackedOperator = 1;
                return;
            case R.id.minus:
                Log.i("Log","minus btn is pressed");
                acc = 1;
                Log.i("Log","acc is: " + acc);
                if(mem==0){
                    mem = Double.parseDouble(calcText.getText().toString());
                }else if(mem!=0){
                    calculate();
                }
                stackedOperator = 2;
                return;
            case R.id.devide:
                Log.i("Log","devide btn is pressed");
                acc = 1;
                Log.i("Log","acc is: " + acc);
                if(mem==0){
                    mem = Double.parseDouble(calcText.getText().toString());
                }else if(mem!=0){
                    calculate();
                }
                stackedOperator = 3;
                return;
            case R.id.multiply:
                Log.i("Log","multiply btn is pressed");
                acc = 1;
                Log.i("Log","acc is: " + acc);
                if(mem==0){
                    mem = Double.parseDouble(calcText.getText().toString());
                }else if(mem!=0){
                    calculate();
                }
                stackedOperator = 4;
                return;
            case R.id.pm:
                double result = Double.parseDouble(calcText.getText().toString());
                if(result!=0) {
                    result = 0 - result;
                    updateCalc(result);
                }
                return;
            case R.id.dot:
                String s = calcText.getText().toString();
                if(s.contains(".")){
                }else{
                    setText(".",acc);
                    acc = 0;
                }
                return;
            case R.id.equal:
                if(mem!=0){
                    calculate();
                    stackedOperator =0;
                    acc =1;
                    mem = 0;
                    clear = 0;
                }else{

                }
                return;

        }
    }

    public int setText(String s,int acc){
        String text = calcText.getText().toString();
        if (acc == 0) {
            if (text.equals("0")) {
                calcText.setText(s);
            } else {
                calcText.setText(text + s);
            }
        }else if(acc ==1){
            String b = ".";
            if(s.compareTo(b)==0){
                calcText.setText("0.");
            }else {
                calcText.setText(s);
            }
        }

        return 0;

    }
    public void calculate(){
        switch (stackedOperator){
            case 0:
                return;
            case 1:
                mem = mem + Double.parseDouble(calcText.getText().toString());
                updateCalc(mem);
                return;
            case 2:
                mem = mem - Double.parseDouble(calcText.getText().toString());
                updateCalc(mem);
                return;
            case 3:
                mem = mem / Double.parseDouble(calcText.getText().toString());
                updateCalc(mem);
                return;
            case 4:
                mem = mem * Double.parseDouble(calcText.getText().toString());
                updateCalc(mem);
                return;
        }
    }
    public void updateCalc(Double x){
        int y = (int) Math.floor(x);
        double z = x - ((double) y);
        if(z!=0){
            calcText.setText(Double.toString(x));
        }else{
            calcText.setText(Integer.toString(y));
        }
    }
}
