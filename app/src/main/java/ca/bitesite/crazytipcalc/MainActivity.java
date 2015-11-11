package ca.bitesite.crazytipcalc;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    // State saving variables
    private static final String SI_BILL_AMOUNT = "SI_BILL_AMOUNT";
    private static final String SI_TIP_AMOUNT = "SI_TIP_AMOUNT";
    private static final String SI_FINAL_AMOUNT = "SI_FINAL_AMOUNT";

    private double billAmount;
    private double tipAmount;
    private double finalAmount;

    EditText billAmountEditText;
    EditText tipAmountEditText;
    EditText finalAmountEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if(savedInstanceState == null) {
            billAmount = 0.00;
            tipAmount = 0.15;
            finalAmount = 0.00;
        }
        else {
            billAmount = savedInstanceState.getDouble(SI_BILL_AMOUNT);
            tipAmount = savedInstanceState.getDouble(SI_TIP_AMOUNT);
            finalAmount = savedInstanceState.getDouble(SI_FINAL_AMOUNT);

        }

        billAmountEditText = (EditText) findViewById(R.id.billAmountEditText);
        tipAmountEditText = (EditText) findViewById(R.id.tipAmountEditText);
        finalAmountEditText = (EditText) findViewById(R.id.finalAmountEditText);

        tipAmountEditText.setText("0.15");

        billAmountEditText.addTextChangedListener(billAmountEditTextTextChanged);

    }

    private TextWatcher billAmountEditTextTextChanged = new TextWatcher(){

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            try {
                billAmount = Double.parseDouble(s.toString());
            }
            catch(NumberFormatException e) {
                billAmount = 0.00;
            }

            updateTipAndFinalBill();
        }

        @Override
        public void afterTextChanged(Editable s) {

        }
    };

    private void updateTipAndFinalBill() {
        try {
            tipAmount = Double.parseDouble(tipAmountEditText.getText().toString());
        }
        catch(NumberFormatException e) {
            tipAmount = 0.00;
        }

        finalAmount = billAmount + (billAmount * tipAmount);

        finalAmountEditText.setText(String.format("%.02f", finalAmount));
    }

    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putDouble(SI_BILL_AMOUNT, billAmount);
        outState.putDouble(SI_TIP_AMOUNT, tipAmount);
        outState.putDouble(SI_FINAL_AMOUNT, finalAmount);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
