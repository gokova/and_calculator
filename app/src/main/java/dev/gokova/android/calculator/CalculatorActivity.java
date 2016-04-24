package dev.gokova.android.calculator;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import dev.gokova.calculator.Calculator;
import dev.gokova.calculator.Operator;

public class CalculatorActivity extends Activity {
	private static Double operand1;
	private static Double operand2;
	private static Operator operator;

	private static Double getOperand1() {
		return operand1;
	}

	private static void setOperand1(Double operand1) {
		CalculatorActivity.operand1 = operand1;
	}

	private static Double getOperand2() {
		return operand2;
	}

	private static void setOperand2(Double operand2) {
		CalculatorActivity.operand2 = operand2;
	}

	private static Operator getOperator() {
		return operator;
	}

	private static void setOperator(Operator operator) {
		CalculatorActivity.operator = operator;
	}

	private EditText calcScreen;
	private Button btn0, btn1, btn2, btn3, btn4, btn5, btn6, btn7, btn8, btn9;
	private Button btnPoint, btnEqual, btnAdd, btnSubtract, btnMultiply, btnDivide,
			btnFactorial, btnSqrRoot, btnSquare, btnPower;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_calculator);
		Init();
		InitValues();
		InitEvents();
	}

	private void Init() {
		calcScreen = (EditText) findViewById(R.id.editText1);
		btn0 = (Button) findViewById(R.id.button17);
		btn1 = (Button) findViewById(R.id.button13);
		btn2 = (Button) findViewById(R.id.button14);
		btn3 = (Button) findViewById(R.id.button15);
		btn4 = (Button) findViewById(R.id.button9);
		btn5 = (Button) findViewById(R.id.button10);
		btn6 = (Button) findViewById(R.id.button11);
		btn7 = (Button) findViewById(R.id.button5);
		btn8 = (Button) findViewById(R.id.button6);
		btn9 = (Button) findViewById(R.id.button7);
		btnPoint = (Button) findViewById(R.id.button18);
		btnEqual = (Button) findViewById(R.id.button19);
		btnAdd = (Button) findViewById(R.id.button20);
		btnSubtract = (Button) findViewById(R.id.button16);
		btnMultiply = (Button) findViewById(R.id.button12);
		btnDivide = (Button) findViewById(R.id.button8);
		btnFactorial = (Button) findViewById(R.id.button1);
		btnSqrRoot = (Button) findViewById(R.id.button2);
		btnSquare = (Button) findViewById(R.id.button3);
		btnPower = (Button) findViewById(R.id.button4);
	}

	private void InitValues() {
		calcScreen.setText("0");
		setOperand1(null);
		setOperand2(null);
		setOperator(null);
	}

	private void InitEvents() {
		btn0.setOnClickListener(onNumberClickListener);
		btn1.setOnClickListener(onNumberClickListener);
		btn2.setOnClickListener(onNumberClickListener);
		btn3.setOnClickListener(onNumberClickListener);
		btn4.setOnClickListener(onNumberClickListener);
		btn5.setOnClickListener(onNumberClickListener);
		btn6.setOnClickListener(onNumberClickListener);
		btn7.setOnClickListener(onNumberClickListener);
		btn8.setOnClickListener(onNumberClickListener);
		btn9.setOnClickListener(onNumberClickListener);
		btnPoint.setOnClickListener(onPointClickListener);
		btnAdd.setOnClickListener(onTwoOperandClickListener);
		btnSubtract.setOnClickListener(onTwoOperandClickListener);
		btnMultiply.setOnClickListener(onTwoOperandClickListener);
		btnDivide.setOnClickListener(onTwoOperandClickListener);
		btnPower.setOnClickListener(onTwoOperandClickListener);
		btnEqual.setOnClickListener(onEqualsClickListener);
		btnFactorial.setOnClickListener(onOneOperandClickListener);
		btnSqrRoot.setOnClickListener(onOneOperandClickListener);
		btnSquare.setOnClickListener(onOneOperandClickListener);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.calculator, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_clear) {
			InitValues();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	private final OnClickListener onNumberClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (calcScreen.getText().toString().equals("0")
					|| (getOperator() != null && getOperator().equals(
							Operator.EQUALS))) {
				calcScreen.setText(((Button) v).getText());
				if ((getOperator() != null && getOperator().equals(
						Operator.EQUALS))) {
					setOperator(null);
				}
			} else {
				String result = calcScreen.getText().toString() + ((Button) v).getText().toString();
				calcScreen.setText(result);
			}
		}
	};

	private final OnClickListener onPointClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (!calcScreen.getText().toString().contains(".")) {
				String result = calcScreen.getText().toString() + ((Button) v).getText().toString();
				calcScreen.setText(result);
			}
		}
	};

	private final OnClickListener onEqualsClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (getOperator() != null && !getOperator().equals(Operator.EQUALS)) {
				calcScreen.setText(String.valueOf(CalculateTwoOperand()));
				setOperator(Operator.EQUALS);
			}
		}
	};

	private final OnClickListener onTwoOperandClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			if (getOperator() == null || getOperator().equals(Operator.EQUALS)) {
				GetFirstOperand(v.getId());
			} else {
				calcScreen.setText(String.valueOf(CalculateTwoOperand()));
				GetFirstOperand(v.getId());
			}
		}
	};

	private void GetFirstOperand(int id) {
		setOperand1(Double.parseDouble(calcScreen.getText().toString()));
		calcScreen.setText("0");
		switch (id) {
		case R.id.button20:
			setOperator(Operator.ADD);
			break;
		case R.id.button16:
			setOperator(Operator.SUBTRACT);
			break;
		case R.id.button12:
			setOperator(Operator.MULTIPLY);
			break;
		case R.id.button8:
			setOperator(Operator.DIVIDE);
			break;
		case R.id.button1:
			setOperator(Operator.FACTORIAL);
			break;
		case R.id.button2:
			setOperator(Operator.SQUAREROOT);
			break;
		case R.id.button3:
			setOperator(Operator.SQUARE);
			break;
		case R.id.button4:
			setOperator(Operator.POWER);
			break;
		default:
			break;
		}
	}

	private Double CalculateTwoOperand() {
		setOperand2(Double.parseDouble(calcScreen.getText().toString()));
		return Calculator.calculate(getOperand1(), getOperand2(), getOperator());
	}

	private final OnClickListener onOneOperandClickListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			GetFirstOperand(v.getId());
			calcScreen.setText(String.valueOf(CalculateOneOperand()));
			setOperator(Operator.EQUALS);
		}
	};

	private Double CalculateOneOperand() {
		setOperand2(null);
		return Calculator.calculate(getOperand1(), getOperator());
	}
}
