import { Component, EventEmitter, Output } from '@angular/core';
import { CalcData } from "../../app.component";

@Component({
  selector: 'inputs',
  templateUrl: './inputs.component.html'
})

export class InputsComponent {
  @Output() calcDataEvent = new EventEmitter<CalcData>();
  @Output() resultEvent = new EventEmitter<string>();

  inputNum1: string = '1';
  inputNum2: string = '1';
  pattern: RegExp = /[]/

  operations = [
    "Сложить",
    "Вычесть",
    "Поделить",
    "Умножить"
  ];
  inputSelectedValue = (this.operations)[0];

  System: any = ['2', '8', '10', '16'];
  param3_val: any= this.System[0];

  changeSystem(e: any): string {
    console.log(e.target.value)
    this.param3_val = e.target.value
    return this.param3_val
  }

  keyPress(event: KeyboardEvent, pattern: any) {
    const inputChar = String.fromCharCode(event.charCode);
    if (!pattern.test(inputChar)) {
      event.preventDefault();
    }
  }

  getPatternParam() {
    if (this.param3_val == '2') {
      this.pattern = /[0-1]/
    }
    if (this.param3_val == '8') {
      this.pattern = /[0-7]/
    }
    if (this.param3_val == '16') {
      this.pattern = /[0-9A-F]/
    }
    if (this.param3_val == '10') {
      this.pattern = /[0-9]/
    }
    return this.pattern
  }

  emitCalcData() {
    this.inputNum2 = this.inputNum2;
    const calcData: CalcData = {
      num1: this["inputNum1"],
      num2: this["inputNum2"],
      selectedValue: this["inputSelectedValue"],
      system: this["param3_val"]
    };

    this.calcDataEvent.emit(calcData);

    const num1Decimal = parseInt(this.inputNum1, parseInt(this.param3_val, 10));
    const num2Decimal = parseInt(this.inputNum2, parseInt(this.param3_val, 10));

    let resultDecimal: number;

    switch (this.inputSelectedValue) {
      case 'Сложить':
        resultDecimal = num1Decimal + num2Decimal;
        break;
      case 'Вычесть':
        resultDecimal = num1Decimal - num2Decimal;
        break;
      case 'Поделить':
        resultDecimal = num1Decimal / num2Decimal;
        break;
      case 'Умножить':
        resultDecimal = num1Decimal * num2Decimal;
        break;
      default:
        resultDecimal = 0;
        break;
    }
    const resultInSelectedSystem = resultDecimal.toString(parseInt(this.param3_val, 10));
    this.resultEvent.emit(resultInSelectedSystem);
  }


}
