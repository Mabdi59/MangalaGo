import axios from 'axios';
import type { Trip, Flight, Hotel, Activity, Budget, PackingList, PackingItem } from '../types';

const API_BASE_URL = 'http://localhost:8080/api';

const api = axios.create({
  baseURL: API_BASE_URL,
  headers: {
    'Content-Type': 'application/json',
  },
});

// Trip API
export const tripAPI = {
  getAll: () => api.get<Trip[]>('/trips'),
  getById: (id: number) => api.get<Trip>(`/trips/${id}`),
  getByStatus: (status: string) => api.get<Trip[]>(`/trips/status/${status}`),
  search: (destination: string) => api.get<Trip[]>(`/trips/search?destination=${destination}`),
  getUpcoming: () => api.get<Trip[]>('/trips/upcoming'),
  create: (trip: Trip) => api.post<Trip>('/trips', trip),
  update: (id: number, trip: Trip) => api.put<Trip>(`/trips/${id}`, trip),
  delete: (id: number) => api.delete(`/trips/${id}`),
};

// Flight API
export const flightAPI = {
  getAll: () => api.get<Flight[]>('/flights'),
  getById: (id: number) => api.get<Flight>(`/flights/${id}`),
  getByTripId: (tripId: number) => api.get<Flight[]>(`/flights/trip/${tripId}`),
  create: (tripId: number, flight: Flight) => api.post<Flight>(`/flights/trip/${tripId}`, flight),
  update: (id: number, flight: Flight) => api.put<Flight>(`/flights/${id}`, flight),
  delete: (id: number) => api.delete(`/flights/${id}`),
};

// Hotel API
export const hotelAPI = {
  getAll: () => api.get<Hotel[]>('/hotels'),
  getById: (id: number) => api.get<Hotel>(`/hotels/${id}`),
  getByTripId: (tripId: number) => api.get<Hotel[]>(`/hotels/trip/${tripId}`),
  create: (tripId: number, hotel: Hotel) => api.post<Hotel>(`/hotels/trip/${tripId}`, hotel),
  update: (id: number, hotel: Hotel) => api.put<Hotel>(`/hotels/${id}`, hotel),
  delete: (id: number) => api.delete(`/hotels/${id}`),
};

// Activity API
export const activityAPI = {
  getAll: () => api.get<Activity[]>('/activities'),
  getById: (id: number) => api.get<Activity>(`/activities/${id}`),
  getByTripId: (tripId: number) => api.get<Activity[]>(`/activities/trip/${tripId}`),
  create: (tripId: number, activity: Activity) => api.post<Activity>(`/activities/trip/${tripId}`, activity),
  update: (id: number, activity: Activity) => api.put<Activity>(`/activities/${id}`, activity),
  delete: (id: number) => api.delete(`/activities/${id}`),
};

// Budget API
export const budgetAPI = {
  getByTripId: (tripId: number) => api.get<Budget>(`/budgets/trip/${tripId}`),
  create: (tripId: number, budget: Budget) => api.post<Budget>(`/budgets/trip/${tripId}`, budget),
  update: (id: number, budget: Budget) => api.put<Budget>(`/budgets/${id}`, budget),
};

// Packing List API
export const packingAPI = {
  getByTripId: (tripId: number) => api.get<PackingList>(`/packing/trip/${tripId}`),
  create: (tripId: number) => api.post<PackingList>(`/packing/trip/${tripId}`),
  getItems: (packingListId: number) => api.get<PackingItem[]>(`/packing/${packingListId}/items`),
  addItem: (packingListId: number, item: PackingItem) => 
    api.post<PackingItem>(`/packing/${packingListId}/items`, item),
  updateItem: (itemId: number, item: PackingItem) => 
    api.put<PackingItem>(`/packing/items/${itemId}`, item),
  deleteItem: (itemId: number) => api.delete(`/packing/items/${itemId}`),
};

// AI API
export const aiAPI = {
  getTripIdeas: (destination: string) => 
    api.get<string[]>(`/ai/trip-ideas?destination=${destination}`),
  getPackingSuggestions: (destination: string, durationDays: number, season?: string) => 
    api.get<string[]>(`/ai/packing-suggestions?destination=${destination}&durationDays=${durationDays}${season ? `&season=${season}` : ''}`),
  optimizeItinerary: (activities: string[]) => 
    api.post<string[]>('/ai/optimize-itinerary', activities),
  getBudgetAllocation: (totalBudget: number, tripType: string) => 
    api.get<{ allocation: string }>(`/ai/budget-allocation?totalBudget=${totalBudget}&tripType=${tripType}`),
};

export default api;
