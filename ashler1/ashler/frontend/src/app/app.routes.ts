import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { HomeComponent } from './home/home.component';
import { VoitureDetailComponent } from './voiture-detail/voiture-detail.component';
import { ProfilComponent } from './profil/profil.component';

const routes: Routes = [
  { path: '', component: HomeComponent },
  { path: 'voiture-detail/:id', component: VoitureDetailComponent },
  { path: 'profil/:id', component: ProfilComponent },
  { path: 'profil', component: ProfilComponent },
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
})
export class AppRoutingModule {}

export const routingComponents = [
  HomeComponent,
  VoitureDetailComponent,
  ProfilComponent,
];
export { routes };
