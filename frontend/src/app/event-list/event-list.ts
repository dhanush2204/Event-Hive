import { Component } from '@angular/core';
import { EventCard } from '../event-card/event-card';
import { Event } from '../models/event';

@Component({
  selector: 'app-event-list',
  standalone: true,
  imports: [EventCard],
  templateUrl: './event-list.html',
  styleUrl: './event-list.css'
})
export class EventList {

  events: Event[] = [
    {
      id: 1,
      name: 'Java Backend Meetup',
      location: 'Hyderabad',
      availableSeats: 25
    },
    {
      id: 2,
      name: 'Angular Workshop',
      location: 'Bangalore',
      availableSeats: 10
    },
    {
      id: 3,
      name: 'Spring Boot Conference',
      location: 'Chennai',
      availableSeats: 0
    }
  ];

  handleRegistration(event: Event): void {
    console.log('Registration requested for:', event.name);
  }
}