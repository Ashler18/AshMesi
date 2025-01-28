import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule, routingComponents } from './app.routes'; // Importez le module de routage et les composants de routage
import { AppComponent } from './app.component';
import { CommonModule } from '@angular/common'; // Importez CommonModule

@NgModule({
  declarations: [
    AppComponent,
    routingComponents, // Ajoutez les composants de routage ici
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, // Ajoutez le module de routage ici
    CommonModule, // Ajoutez CommonModule ici
  ],
  providers: [],
  bootstrap: [AppComponent],
})
export class AppModule {}
