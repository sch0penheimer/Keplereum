import { ApplicationConfig } from '@angular/core';

export const config: ApplicationConfig & { serverUrl?: string, apiEndpoint?: string } = {
  providers: [], // Add any required providers here
  serverUrl: 'http://localhost:4200', 
  apiEndpoint: '/api', 
};
