import { Pipe, PipeTransform } from '@angular/core';
import { isNumber } from 'util';

@Pipe({
  name: 'price'
})
export class PricePipe implements PipeTransform {

  transform(value: any, args?: any): any {
    if (!isNumber(value)) {
      return value;
    }
    return (value / 1000) + 'K';
  }

}
