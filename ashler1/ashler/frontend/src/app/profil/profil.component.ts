import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UserService } from '../services/user.service';

@Component({
  selector: 'app-profile',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css'],
})
export class ProfilComponent implements OnInit {
  user: any;

  constructor(
    private userService: UserService,
    private route: ActivatedRoute
  ) {}

  ngOnInit(): void {
    const userId = this.route.snapshot.params['id'];

    this.userService.getUserById(userId).subscribe({
      next: (data) => {
        this.user = data;
      },
      error: (error) => {
        console.error(
          'Erreur lors de la récupération des données utilisateur:',
          error
        );
      },
    });
  }
}
