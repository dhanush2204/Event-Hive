import { Component, EventEmitter, Input, Output } from '@angular/core';
import { Event } from '../models/event';

@Component({
  selector: 'app-event-card',
  standalone: true,
  templateUrl: './event-card.html',
  styleUrl: './event-card.css'
})
export class EventCard {

  @Input() event!: Event;

  @Output() registerClicked = new EventEmitter<Event>();

  register(): void {

    if (this.event.availableSeats <= 0) {
      return;
    }

    this.registerClicked.emit(this.event);
  }
}