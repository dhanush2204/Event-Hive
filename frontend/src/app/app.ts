import { Component, signal } from '@angular/core';
import { RouterOutlet } from '@angular/router';
import { EventList } from './event-list/event-list';

@Component({
  standalone: true,
  imports: [EventList],
  selector: 'app-root',
  styleUrl: './app.scss',
  templateUrl: './app.html',
})
export class App {
  protected readonly title = signal('frontend');
}
