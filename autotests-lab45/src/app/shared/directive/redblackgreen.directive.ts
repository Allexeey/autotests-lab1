import {Directive, ElementRef, Input, OnChanges, SimpleChanges} from '@angular/core';

@Directive({
  selector: '[redblackgreen]'
})
export class RedblackgreenDirective implements OnChanges {
  // @ts-ignore
  @Input() public redblackgreen: any;
  @Input() public input: any;

  constructor(private el: ElementRef) {
    this.highlight('red');
  }

  ngOnChanges(changes: SimpleChanges) {
    const inputValue = parseFloat(this.input);

    if (isNaN(inputValue)) {
      this.highlight('yellow');
      this.el.nativeElement.value = 'NaN';
    } else if (inputValue < 0) {
      this.highlight('red');
    } else if (inputValue === 0) {
      this.highlight('black');
    } else if (inputValue > 0) {
      this.highlight('green');
    }
    if (inputValue) {
      this.el.nativeElement.value = this.input.toUpperCase();
    }
  }

  private highlight(color: string) {
    this.el.nativeElement.style.backgroundColor = color;
  }
}
