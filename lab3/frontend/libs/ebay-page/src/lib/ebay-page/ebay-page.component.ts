import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { EbayDecreaseComponent } from '@soa2-front/ebay-decrease';
import { EbayIncreaseComponent } from '@soa2-front/ebay-increase';

@Component({
  selector: 'lib-ebay-page',
  standalone: true,
  imports: [
    CommonModule,
    EbayDecreaseComponent,
    EbayIncreaseComponent,
  ],
  templateUrl: './ebay-page.component.html',
  styleUrl: './ebay-page.component.less',
})
export class EbayPageComponent {}
