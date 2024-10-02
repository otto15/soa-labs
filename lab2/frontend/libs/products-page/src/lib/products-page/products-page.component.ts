import { ChangeDetectionStrategy, Component, inject } from '@angular/core';
import { CommonModule } from '@angular/common';
import { TuiAlertService, TuiButton, TuiDataList, TuiDialogService, TuiHint, TuiNumberFormat } from '@taiga-ui/core';
import { PolymorpheusContent } from '@taiga-ui/polymorpheus';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { TuiInputModule, TuiInputNumberModule, TuiSelectModule } from '@taiga-ui/legacy';
import { TuiDataListWrapper } from '@taiga-ui/kit/components/data-list-wrapper';
import { noEmptyStringValidator } from './no-empty-string.validator';
import { moreTenStringValidator } from './more-ten-string.validator';
import { Product, UnitOfMeasureRusMap } from '@soa2-front/shared';
import { ProductsService } from '@soa2-front/shared';
import { BehaviorSubject, switchMap } from 'rxjs';
import { ProductViewComponent } from '@soa2-front/product-view';
import { ProductEditComponent } from '@soa2-front/product-edit';
import { ProductsPriceSumComponent } from '@soa2-front/products-price-sum';
import { ProductMinComponent } from '@soa2-front/product-min';

@Component({
  selector: 'lib-products-page',
  standalone: true,
  imports: [
    CommonModule, 
    TuiButton, 
    ReactiveFormsModule,
    TuiInputModule,
    TuiInputNumberModule,
    TuiNumberFormat,
    TuiSelectModule,
    TuiDataList,
    TuiDataListWrapper,
    TuiHint,
    ProductViewComponent,
    ProductEditComponent,
    ProductsPriceSumComponent,
    ProductMinComponent,
  ],
  templateUrl: './products-page.component.html',
  styleUrl: './products-page.component.less',
  changeDetection: ChangeDetectionStrategy.OnPush,
})
export class ProductsPageComponent {
  private readonly dialogService = inject(TuiDialogService);
  private readonly productService = inject(ProductsService);
  private readonly alertService = inject(TuiAlertService);

  private readonly search$ = new BehaviorSubject<number>(0);

  protected readonly products$ = this.search$.pipe(
    switchMap(() => this.productService.getProducts())
  );

  protected readonly selectedProduct$ = new BehaviorSubject<Product | null>(null);

  protected readonly unitsOfMeasure = [
    'Килограммы',
    'Метры',
    'Миллиграммы',
    'Миллилитры'
  ];

  protected readonly productForm = new FormGroup({
    name: new FormControl<string>('', [noEmptyStringValidator()]),
    coordinateX: new FormControl<number>(0),
    coordinateY: new FormControl<number>(0),
    price: new FormControl<number>(1),
    partNumber: new FormControl<string | null>(null, [moreTenStringValidator()]),
    manufactureCost: new FormControl<number>(0),
    unitOfMeasure: new FormControl<string>('Килограммы'),
    personPassportId: new FormControl<string | null>(null),
  });

  protected openCreateForm(template: PolymorpheusContent): void {
    this.dialogService.open(template, {
      label: 'Создание продукта'
    }).subscribe()
  }

  
  // eslint-disable-next-line @typescript-eslint/no-explicit-any
  protected submitForm(observer: any): void {
    const name = this.productForm.controls.name.value;
    const coordinateX = this.productForm.controls.coordinateX.value;
    const coordinateY = this.productForm.controls.coordinateY.value;
    const price = this.productForm.controls.price.value;
    const partNumber = this.productForm.controls.partNumber.value;
    const manufactureCost = this.productForm.controls.manufactureCost.value;
    const unitOfMeasure = this.productForm.controls.unitOfMeasure.value;
    const personPassportId = this.productForm.controls.personPassportId.value;

    if (
      !name || coordinateX === null || coordinateY === null || price === null ||
      manufactureCost === null || !unitOfMeasure
    ) {
      return;
    }

    const mappedUnitOfMeasure = UnitOfMeasureRusMap.get(unitOfMeasure);

    if (!mappedUnitOfMeasure) {
      return;
    }

    this.productService.createProduct({
      name,
      coordinates: {
        x: coordinateX,
        y: coordinateY,
      },
      price,
      partNumber,
      manufactureCost,
      unitOfMeasure: mappedUnitOfMeasure,
      ownerPassportId: personPassportId,
    }).subscribe({
      next: () => {
        observer.complete();

        this.alertService.open("Вы успешно создали новый продукт", {
          appearance: "success",
          label: "Успешно!"
        }).subscribe();
      },
      error: () => {
        this.alertService.open("Не удалось создать новый продукт", {
          appearance: "error",
          label: "Ошибка!"
        }).subscribe();
      }
    });
  }

  protected setViewProduct(template: PolymorpheusContent, product: Product): void {
    this.selectedProduct$.next(product);
  
    this.dialogService.open(template, {
      label: 'Просмотр продукта'
    }).subscribe()
  }

  protected deleteProduct(productId: number): void {
    this.productService.deleteProduct(productId).subscribe({
      next: () => {
        this.search$.next(this.search$.value + 1);
  
        this.alertService.open("Вы успешно удалили продукт", {
          appearance: "success",
          label: "Успешно!"
        }).subscribe();
      }, 
      error: () => {
        this.alertService.open("Не удалось удалить продукт", {
          appearance: "error",
          label: "Ошибка!"
        }).subscribe();
      }
    })
  }

  protected setEditProduct(template: PolymorpheusContent, product: Product): void {
    this.selectedProduct$.next(product);
  
    this.dialogService.open(template, {
      label: 'Редактирование продукт'
    }).subscribe()
  }
}
