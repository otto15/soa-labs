import { Component } from '@angular/core';
import { FormControl, FormGroup, ReactiveFormsModule } from '@angular/forms';
import { TuiRoot } from '@taiga-ui/core';
import { TuiInputModule } from '@taiga-ui/legacy';

@Component({
  standalone: true,
  imports: [TuiRoot, TuiInputModule, ReactiveFormsModule],
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.less',
})
export class AppComponent {
  title = 'soa2-front';

  testForm = new FormGroup({
    testValue: new FormControl('mail@mail.ru'),
  });
}
