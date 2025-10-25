export interface Trip {
  id?: number;
  name: string;
  description: string;
  destination: string;
  startDate: string;
  endDate: string;
  status: 'PLANNING' | 'CONFIRMED' | 'COMPLETED' | 'CANCELLED';
  flights?: Flight[];
  hotels?: Hotel[];
  activities?: Activity[];
  budget?: Budget;
  packingList?: PackingList;
}

export interface Flight {
  id?: number;
  airline: string;
  flightNumber: string;
  departureAirport: string;
  arrivalAirport: string;
  departureTime: string;
  arrivalTime: string;
  confirmationNumber?: string;
  price?: number;
  seatNumber?: string;
  notes?: string;
}

export interface Hotel {
  id?: number;
  name: string;
  address: string;
  city?: string;
  country?: string;
  checkInDate: string;
  checkOutDate: string;
  confirmationNumber?: string;
  pricePerNight?: number;
  numberOfNights?: number;
  roomType?: string;
  phoneNumber?: string;
  notes?: string;
}

export interface Activity {
  id?: number;
  name: string;
  description?: string;
  scheduledTime: string;
  location?: string;
  price?: number;
  category?: string;
  bookingReference?: string;
  durationMinutes?: number;
  notes?: string;
}

export interface Budget {
  id?: number;
  totalBudget: number;
  flightsBudget?: number;
  hotelsBudget?: number;
  activitiesBudget?: number;
  foodBudget?: number;
  transportationBudget?: number;
  shoppingBudget?: number;
  miscellaneousBudget?: number;
  spentAmount: number;
  notes?: string;
}

export interface PackingList {
  id?: number;
  items: PackingItem[];
}

export interface PackingItem {
  id?: number;
  name: string;
  category?: string;
  quantity: number;
  packed: boolean;
  notes?: string;
}
