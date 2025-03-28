// src/app/app.routes.ts
import { Routes } from '@angular/router';
import { SatelliteViewerComponent } from './components/satelliteViewer/satelliteViewer.component';

export const routes: Routes = [
  { path: '', component: SatelliteViewerComponent }
];