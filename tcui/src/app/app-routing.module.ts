import {HomeComponent} from "./home/home.component";
import {RouterModule, Routes} from "@angular/router";
import {TournamentComponent} from "./tournament/tournament.component";
import {NgModule} from "@angular/core";

const routes: Routes = [
  { path: '', redirectTo: '/home', pathMatch: 'full' },
  { path: 'home', component: HomeComponent },
  { path: 'tournament/:id', component: TournamentComponent},
  { path: '**', component: HomeComponent }
];

@NgModule({
  imports: [
    RouterModule.forRoot(routes)
  ],
  exports: [
    RouterModule
  ]
})
export class AppRoutingModule {}
