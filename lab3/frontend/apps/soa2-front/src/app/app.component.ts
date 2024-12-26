import { Component } from '@angular/core';
import { ReactiveFormsModule } from '@angular/forms';
import { RouterModule, RouterOutlet } from '@angular/router';
import { TuiRoot } from '@taiga-ui/core';
import { TuiTabs } from '@taiga-ui/kit';
import { TuiInputModule } from '@taiga-ui/legacy';

@Component({
  standalone: true,
  imports: [
    TuiRoot, 
    TuiInputModule, 
    ReactiveFormsModule, 
    RouterOutlet, 
    RouterModule,
    TuiTabs,
  ],
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrl: './app.component.less',
})
export class AppComponent {
  title = 'soa2-front';
}
