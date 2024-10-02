import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TuiButton } from '@taiga-ui/core';

@Component({
  selector: 'lib-product-min',
  standalone: true,
  imports: [CommonModule, TuiButton],
  templateUrl: './product-min.component.html',
  styleUrl: './product-min.component.less',
})
export class ProductMinComponent {}
