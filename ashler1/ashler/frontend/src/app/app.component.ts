import { Component, OnInit } from '@angular/core';
import { VoitureService } from './voiture.service';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css'],
  standalone: true,
  imports: [CommonModule, RouterModule],
})
export class AppComponent implements OnInit {
  voitures: any[] = [];

  constructor(private voitureService: VoitureService) {}

  ngOnInit(): void {
    this.voitureService.getVoitures().subscribe((data) => {
      this.voitures = data;
    });

    // Ajouter un écouteur d'événements pour le bouton du menu mobile
    const menuButton = document.getElementById('menu-button');
    const menu = document.getElementById('menu');

    menuButton?.addEventListener('click', () => {
      menu?.classList.toggle('hidden');
    });
  }
}
